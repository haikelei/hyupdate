package com.empathy.service.impl;

import com.empathy.ExcelUtil;
import com.empathy.common.RspResult;
import com.empathy.dao.BaseDealDao;
import com.empathy.domain.deal.BaseDeal;
import com.empathy.domain.deal.bo.DealFindByUserIdBo;
import com.empathy.domain.deal.bo.DealFindPageBo;
import com.empathy.domain.deal.vo.DealFindVo;
import com.empathy.domain.user.BaseMember;
import com.empathy.service.AbstractBaseService;
import com.empathy.service.IBaseDealService;
import com.empathy.utils.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by MI on 2018/4/17.
 */
@Service
public class BaseDealService extends AbstractBaseService implements IBaseDealService {

    @Autowired
    private BaseDealDao baseDealDao;

    @Autowired
    private BaseMemberService memberService;


    @Override
    public RspResult findByUserId(DealFindByUserIdBo bo) {
        try {

            List<BaseDeal> list = baseDealDao.list(bo);
            int count = baseDealDao.count(bo.getId());
            return success(count, list);
        }catch (Exception e){
            e.printStackTrace();
            return errorNo();
        }
    }

    @Override
    public String findAllDealCount(DealFindPageBo bo) {
        return baseDealDao.findAllDealCount(bo) + "";
    }

    @Override
    public RspResult findAllDeal(DealFindPageBo bo) {
        try {
            List<DealFindVo> list = list(bo);

            int count = baseDealDao.findAllDealCount(bo);
            return success(count, list);
        }catch (Exception e){
            e.printStackTrace();
            return errorNo();
        }
    }

    public List<DealFindVo> list(DealFindPageBo bo) {
        List<DealFindVo> list = baseDealDao.findAllDeal(bo);

        for (DealFindVo vo : list) {
            BaseMember baseMember = memberService.findById(vo.getUserId());
            if (baseMember != null) {
                vo.setUsername(baseMember.getUsername());
                vo.setPhone(baseMember.getPhone());
            }

            if (vo.getMoney() != null) {
                vo.setMoney(vo.getMoney().divide(new BigDecimal(10)));
            }
        }

        return list;
    }

    @Override
    public void getExcel(DealFindPageBo bo, HttpServletResponse response) {

        //获取数据
        List<DealFindVo> list = list(bo);

        //excel标题
        String[] title = {"编号", "用户名", "电话", "交易流水号", "交易类型", "金额(元)", "创建时间"};

        //excel文件名
        String fileName = "财务信息表"+DateUtils.getDateStr(new Date(),"yyyyMMddHHmmss")+".xls";

        //sheet名
        String sheetName = "财务信息表";

        String[][] content = new String[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            DealFindVo obj = list.get(i);
            content[i] = new String[title.length];

            content[i][0] = obj.getId().toString();
            content[i][1] = obj.getUsername();
            content[i][2] = obj.getPhone();
            content[i][3] = obj.getCode();

            if (obj.getType() == 0) {
                content[i][4] = "充值";
            } else if (obj.getType() == 1) {
                content[i][4] = "提现";
            }  else if (obj.getType() == 2) {
                content[i][4] = "发出打赏";
            }  else if (obj.getType() == 3) {
                content[i][4] = "接受礼物";
            }  else if (obj.getType() == 4) {
                content[i][4] = "返回";
            }  else if (obj.getType() == 5) {
                content[i][4] = "开通会员";
            }

            content[i][5] = obj.getMoney().toString();
            content[i][6] = DateUtils.getDateStr(new Date(obj.getCreateTime()),"yyyy-MM-dd HH:mm:ss");
        }

        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);

        //响应到客户端
        try {
            this.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //发送响应流方法
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(),"ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public RspResult save(BaseDeal entity) {
        return null;
    }

    @Override
    public BaseDeal findById(Long aLong) {
        return null;
    }

    @Override
    public RspResult update(BaseDeal entity) {
        return null;
    }

    @Override
    public RspResult delById(Long aLong) {
        return null;
    }
}
