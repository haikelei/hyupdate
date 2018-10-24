package com.empathy.security;

import com.alibaba.fastjson.JSON;
import com.empathy.cache.CacheUtils;
import com.empathy.common.Constants;
import com.empathy.common.Required;
import com.empathy.ex.BizBaseException;
import com.empathy.ex.NotLoginException;
import com.empathy.service.ITaskService;
import com.google.common.util.concurrent.RateLimiter;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;


/**
 * 请求拦截
 */
public class SecurityInterceptor {

    private List<String> excludePaths;

    public List<String> getExcludePaths() {
        return excludePaths;
    }

    private static final int permitsPerSecond = 1000;
    private static final RateLimiter limter = RateLimiter.create(permitsPerSecond);

    public void setExcludePaths(List<String> excludePaths) {
        this.excludePaths = excludePaths;
    }

    private static final Logger LOG = LoggerFactory.getLogger(SecurityInterceptor.class);
    private static final long def_expire = 5 * 60 * 1000;//5分钟
    private static final String GET_METHOD = "GET";
    private ITaskService taskService;

    public ITaskService getTaskService() {
        return taskService;
    }

    public void setTaskService(ITaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * 拦截所有请求并进行预处理
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    public Object execute(ProceedingJoinPoint pjp) throws Throwable {
//	    if(!limter.tryAcquire()){//防止系统崩溃
//            throw new BizBaseException("系统繁忙，请重试");
//        }
        final HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes())
                .getRequest();
        if (excludes(req)) {
            return pjp.proceed();
        }
        final MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        final Method method = methodSignature.getMethod();
        final Class<?>[] ptypes = method.getParameterTypes();
        if (ptypes.length != 2 || ptypes[1] != ReqHeader.class) {
            return pjp.proceed();
        }
        final String ctype = req.getContentType();
        if (StringUtils.isNotBlank(ctype)) {
            if (ctype.toLowerCase().contains("form-data")) {
                throw new BizBaseException("请求错误：不支持form提交的数据，请换成JSON格式");
            }
        }
        final String reqMethod = req.getMethod();
        String receive = ReqUtils.reqToStr(req);
        Object reqBo = null;
        if (GET_METHOD.equals(reqMethod)) {
            reqBo = method.getParameterTypes()[0].newInstance();
            StringBuilder sb = new StringBuilder();
            ReqUtils.initGetParam(req, reqBo, sb);
            receive = sb.toString();
        } else {
            if (StringUtils.isNotBlank(receive)) {
                reqBo = JSON.parseObject(receive, method.getParameterTypes()[0]);
            } else {
                reqBo = method.getParameterTypes()[0].newInstance();
            }
        }
        if (null != reqBo) {
            checkRequired(reqBo);
        }
        String uri = req.getRequestURI();
//		LOG.info(uri+" : "+receive);
        final ReqHeader header = new ReqHeader();
        ReqUtils.fillHeader(header, req);
        //校验当天是否有活动
        final Long userId = header.getUid();
        if (userId != null) {
            if (CheckActiveUtils.checkActive(userId)) {//活跃用户
                taskService.fireLog(userId, userId, Constants.LogType.login.getType(), 0);
            }
        }
        IgnoreSecurity ignore = method.getAnnotation(IgnoreSecurity.class);
        if (ignore == null) {
            checkLogin(header, req);
        }
        final String path = req.getContextPath();
        if (uri.contains(path)) {
            uri = uri.replaceAll(path, "");
        }
        //触发日志活动
        String desc = LogUtils.getDesc(uri);
        if (StringUtils.isNotBlank(desc) && userId != null) {
            taskService.fireLog(userId, desc);
        }
        return pjp.proceed(new Object[]{reqBo, header});
    }

    private boolean excludes(HttpServletRequest req) {
        String reqPath = req.getRequestURI();
        String cp = req.getContextPath();
        if (!"/".equals(cp)) {
            reqPath = reqPath.replace(req.getContextPath(), "");
        }
        if (excludePaths != null) {
            for (String exclude : excludePaths) {
                if (reqPath.startsWith(exclude)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 验证登录
     *
     * @param header
     * @param request
     */
    private void checkLogin(final ReqHeader header, final HttpServletRequest request) {
        Long acctime = header.getAccesstime();
        if (acctime != null) {
            long acc = acctime;
            long now = System.currentTimeMillis();
            if (now > acc + def_expire) {
                throw new BizBaseException("请求已过期!");
            }
        }
        Long uid = header.getUid();
        String token = header.getToken();
        if (Constants.IGNORE_TOKEN.equals(token)) {//忽略登录验证
            return;
        }
        Object oldToken = CacheUtils.get(Constants.API_LOGIN_KEY + header.getUid());
        if (uid == null || uid.compareTo(0L) == 0 || StringUtils.isBlank(token) || oldToken == null) {
            throw new NotLoginException();
        }
        final String ot = String.valueOf(oldToken);
        if (StringUtils.isBlank(ot)) {
            throw new NotLoginException();
        }
        if (!ot.equals(token)) {
            throw new NotLoginException("您的账户在其他地方登录，请重新登录并及时修改密码!");
            //oldToken.setValue(token);
            //CacheManager.put(""+uid, token);//覆盖token，可以重复登陆
        }
    }

    /**
     * @param target
     * @Requied 注解
     */
    private static void checkRequired(Object target) {
        Field[] fields = target.getClass().getDeclaredFields();//不能读取集成的field
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                Required ann = field.getAnnotation(Required.class);
                final String name = field.getName();
                if (ann != null) {
                    try {
                        Object value = getValue(name, target);
                        if (value == null) {
                            throw new BizBaseException(name + " 必填!");
                        }
                    } catch (IllegalArgumentException e) {
                        throw new BizBaseException(name + " 传值非法,请输入合法的内容!");
                    }
                }
            }
        }
    }

    /**
     * 获取属性对应的值
     *
     * @param name
     * @param target
     * @return
     */
    private static Object getValue(String name, Object target) {
        String method = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
        try {
            return target.getClass().getMethod(method, new Class[]{}).invoke(target);
        } catch (Exception e) {
            LOG.error("获取值错误：{}", e);
        }
        return null;
    }

}
