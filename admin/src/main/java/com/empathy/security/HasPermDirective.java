package com.empathy.security;

import com.empathy.common.Constants;
import com.empathy.common.LoginSession;
import com.empathy.service.IPermissionService;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author tybest
 * @email tybest@163.com
 * @date 2017/6/19 16:03
 * @desc
 */
public class HasPermDirective implements TemplateDirectiveModel {

    private static final String PARAM_KEY = "value";

    @Autowired
    private IPermissionService permissionService;

    @Override
    public void execute(Environment environment, Map map,
                        TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        if (map.isEmpty()) {
            throw new TemplateModelException("This directive doesn't allow empty parameters!");
        }
        final String perms = String.valueOf(map.get(PARAM_KEY));
        final int index = perms.indexOf(",");
        String[] ps = null;
        if (index > -1) {
            ps = perms.split(",");
        } else {
            ps = new String[]{perms};
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Object obj = request.getSession().getAttribute(Constants.LOGIN_KEY);
        if (null == obj) {
            return;
        }
        LoginSession session = (LoginSession) obj;
        if (hasPerm(session.getPermissiones(), ps)) {
            templateDirectiveBody.render(environment.getOut());
        }
    }

    private boolean hasPerm(List<String> perms, String[] ps) {
        if (perms.isEmpty()) {
            return false;
        }
        List<String> subs = Arrays.asList(ps);
        return perms.containsAll(subs);
    }
}
