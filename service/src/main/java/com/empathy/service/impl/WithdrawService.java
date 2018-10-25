package com.empathy.service.impl;

import com.empathy.ExcelUtil;
import com.empathy.common.RspResult;
import com.empathy.dao.*;
import com.empathy.domain.baseCode.BaseCode;
import com.empathy.domain.configuration.Configuration;
import com.empathy.domain.deal.BaseDeal;
import com.empathy.domain.user.UserMoney;
import com.empathy.domain.user.Userinfo;
import com.empathy.domain.withdraw.Withdraw;
import com.empathy.domain.withdraw.bo.*;
import com.empathy.service.AbstractBaseService;
import com.empathy.service.IWithdrawService;
import com.empathy.utils.DateUtils;
import com.empathy.utils.MD5Utils;
import com.empathy.utils.sms.SmsNewUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by MI on 2018/4/17.
 */
@Service
public class WithdrawService extends AbstractBaseService implements IWithdrawService {

    @Autowired
    private UserMoneyDao userMoneyDao;

    @Autowired
    private ConfigurationDao configurationDao;

    @Autowired
    private WithdrawDao withdrawDao;

    @Autowired
    private BaseCodeDao baseCodeDao;

    @Autowired
    private BaseDealDao baseDealDao;

    @Autowired
    UserinfoDao userinfoDao;

    @Override
    public String findWithdrawCount(WithdrawFindBo bo) {
        int count = withdrawDao.countByBo(bo);

        return count + "";
    }

    @Override
    public RspResult findWithdraw(WithdrawFindBo bo) {
        List<Withdraw> list = withdrawDao.list(bo);
        int count = withdrawDao.count();

        return success(count, list);
    }


    //  修改提现密码
    @Override
    public RspResult modifyPayPassword(ModifyPasswordBo bo) {
        Userinfo userinfo = userinfoDao.findByPhone(bo.getPhone());
        if (userinfo == null) {
            return new RspResult("手机号不存在", 1);
        }
        if (SmsNewUtils.checkCode(SmsNewUtils.CHANGE_WITHDRAW_PWS,bo.getCode(), bo.getPhone())) {
            return new RspResult("验证码错误", 1);
        }
        UserMoney userMoney = userMoneyDao.findByUserId(userinfo.getMemberId());
        if (userMoney.getPassword().equals(MD5Utils.encrypt(bo.getPassword()))) {
            return new RspResult("密码不能与上次密码相同", 1);
        }
        userMoney.setPassword(MD5Utils.encrypt(bo.getPassword()));
        userMoney.setLastRevampTime(System.currentTimeMillis());
        userMoneyDao.update(userMoney);
        return new RspResult();
    }

    @Override
    public RspResult getPayPasswordCode(ModifyPasswordCodeBo bo) {
        SmsNewUtils.sendTemplate(SmsNewUtils.CHANGE_WITHDRAW_PWS,bo.getPhone());
        return null;
    }

    //审核提现
    @Override
    public RspResult updWithdraw(WithdrawUpdBo bo) {
        if (bo.getType() == 0) {
            //提现成功
            Withdraw withdraw = withdrawDao.findById(bo.getId());
            withdraw.setWithdrawStatus(200);
            withdraw.setLastRevampTime(System.currentTimeMillis());
            withdrawDao.update(withdraw);
        } else if (bo.getType() == 1) {
            //提现失败
            Withdraw withdraw = withdrawDao.findById(bo.getId());
            withdraw.setWithdrawStatus(300);
            withdraw.setLastRevampTime(System.currentTimeMillis());
            withdrawDao.update(withdraw);
            UserMoney userMoney = userMoneyDao.findByUserId(withdraw.getUserId());
            userMoney.setMoney(BigDecimal.valueOf(userMoney.getMoney().doubleValue() + withdraw.getLastMoney().doubleValue()));
            userMoney.setLastRevampTime(System.currentTimeMillis());
            userMoneyDao.update(userMoney);

            BaseDeal baseDeal = new BaseDeal();
            baseDeal.setUserId(withdraw.getUserId());
            baseDeal.setMoney(withdraw.getLastMoney());
            baseDeal.setCode(withdraw.getCode());
            baseDeal.setType(4);
            baseDealDao.save(baseDeal);


        }


        return new RspResult();
    }


    //申请提现
    @Override
    public RspResult addWithdraw(WithdrawAddBo bo) {
        UserMoney userMoney = userMoneyDao.findByUserId(bo.getUserId());
        if (!userMoney.getPassword().equals(MD5Utils.encrypt(bo.getPassword()))) {
            return new RspResult("密码错误", 1);
        }
        Configuration configuration = configurationDao.findByType("withdraw");

        if ((userMoney.getMoney().doubleValue() * configuration.getConversion()) < bo.getMoney().doubleValue()) {
            return new RspResult("金额不足", 1);
        }
        userMoney.setMoney(BigDecimal.valueOf(userMoney.getMoney().doubleValue() - bo.getMoney().doubleValue() / configuration.getConversion()));
        userMoney.setLastRevampTime(System.currentTimeMillis());

        Withdraw withdraw = new Withdraw();
        //String code = CodeUtils.getCode();
        String code = DateUtils.getDateStr(new Date(),"yyyyMMddHHmmss");
        withdraw.setCode("HY" + code + userMoney.getUserId());
        withdraw.setMoney(bo.getMoney());
        withdraw.setWithdrawType(bo.getWithdrawType());
        withdraw.setLastMoney(BigDecimal.valueOf(bo.getMoney().doubleValue() / configuration.getConversion()));
        withdraw.setUserId(bo.getUserId());
        withdraw.setName(bo.getName());
        withdraw.setWithdrawStatus(100);
        withdraw.setCreateTime(System.currentTimeMillis());
        withdraw.setLastRevampTime(System.currentTimeMillis());

        if (bo.getWithdrawType() == 0) {
            withdraw.setAlipayAccount(bo.getAlipayAccount());
        } else if (bo.getWithdrawType() == 1) {
            withdraw.setBank(bo.getBank());
            withdraw.setCard(bo.getCard());
        } else {
            return new RspResult("无此提现方式", 1);
        }
        userMoneyDao.update(userMoney);
        withdrawDao.save(withdraw);
        BaseCode baseCode = new BaseCode();
        baseCode.setCodeType("withdraw");
        baseCode.setCode("+" + code);
        baseCode.setCreateTime(System.currentTimeMillis());
        baseCode.setLastRevampTime(System.currentTimeMillis());
        baseCodeDao.save(baseCode);

        BaseDeal baseDeal = new BaseDeal();
        baseDeal.setCode(code);
        baseDeal.setMoney(BigDecimal.valueOf(bo.getMoney().doubleValue() / configuration.getConversion()));
        baseDeal.setType(1);
        baseDeal.setUserId(bo.getUserId());
        baseDeal.setCreateTime(System.currentTimeMillis());
        baseDeal.setLastRevampTime(System.currentTimeMillis());
        baseDealDao.save(baseDeal);

        return new RspResult();
    }


    @Override
    public void getExcel(WithdrawFindBo bo, HttpServletResponse response) {

        //获取数据
        List<Withdraw> list = withdrawDao.list(bo);

        //excel标题
        String[] title = {"编号","提现编号","用户名","电话","提现方式","支付宝账户","银行名称","银行卡号","提现金额","提现状态","提现时间","操作(成功填1，失败填0)"};

        //excel文件名
        String fileName = "提现信息表"+DateUtils.getDateStr(new Date(),"yyyyMMddHHmmss")+".xls";

        //sheet名
        String sheetName = "提现信息表";

        String[][] content = new String[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            Withdraw obj = list.get(i);
            content[i] = new String[title.length];

            content[i][0] = obj.getId().toString();
            content[i][1] = obj.getCode();
            content[i][2] = obj.getUsername();
            content[i][3] = obj.getPhone();
            if(obj.getWithdrawType() == 0){
                content[i][4] = "支付宝";
            }else{
                content[i][4] = "银行卡";
            }
            content[i][5] = obj.getAlipayAccount();
            content[i][6] = obj.getBank();
            content[i][7] = obj.getCard();
            content[i][8] = obj.getMoney().toString();
            if(obj.getWithdrawStatus() == 100){
                content[i][9] = "待审核";
            }else if(obj.getWithdrawStatus() == 200){
                content[i][9] = "成功";
            }else if(obj.getWithdrawStatus() == 300){
                content[i][9] = "失败";
            }
            content[i][10] = DateUtils.getDateStr(new Date(obj.getCreateTime()),"yyyy-MM-dd HH:mm:ss");
            if(obj.getWithdrawStatus() == 100){
                content[i][11] = "";
            }else if(obj.getWithdrawStatus() == 200){
                content[i][11] = "1";
            }else if(obj.getWithdrawStatus() == 300){
                content[i][11] = "0";
            }

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
                // TODO Auto-generated catch block
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
    public RspResult exportExcle(MultipartFile file) {
        WithdrawExcleBo withdrawExcleBo = new WithdrawExcleBo();
        try{
            List<WithdrawExcleBo> lists = importDataFromExcel(withdrawExcleBo,file.getInputStream(),file.getOriginalFilename());

            for (WithdrawExcleBo withdrawExcle:lists) {
                if(StringUtils.isEmpty(withdrawExcle.getOperato()) || StringUtils.isEmpty(withdrawExcle.getId())){
                    return error("操作状态不能为空！");
                }
                int operato = Integer.parseInt(withdrawExcle.getOperato());
                Withdraw withdraw = withdrawDao.findById(Long.parseLong(withdrawExcle.getId()));
                if(operato == 1){
                    withdraw.setWithdrawStatus(200);
                }else if(operato == 0){
                    withdraw.setWithdrawStatus(300);
                }
                withdrawDao.editStatus(withdraw);
            }
        }catch (Exception e){
            e.printStackTrace();
            return error("获取数据错误，请重新导入");
        }
        return success();
    }

    private List<WithdrawExcleBo> importDataFromExcel(WithdrawExcleBo vo, InputStream is, String excelFileName) {
        List<WithdrawExcleBo> list = new ArrayList<>();
        try {
            //创建工作簿
            Workbook workbook = ExcelUtil.createWorkbook(is, excelFileName);
            //创建工作表sheet
            Sheet sheet = ExcelUtil.getSheet(workbook, 0);
            //获取sheet中数据的行数
            int rows = sheet.getPhysicalNumberOfRows();
            //获取表头单元格个数
            int cells = sheet.getRow(0).getPhysicalNumberOfCells();
            //利用反射，给JavaBean的属性进行赋值
            Field[] fields = vo.getClass().getDeclaredFields();
            for (int i = 1; i < rows; i++) {//第一行为标题栏，从第二行开始取数据
                vo = new WithdrawExcleBo();
                Row row = sheet.getRow(i);
                int index = 0;
                while (index < cells) {
                    Cell cell = row.getCell(index);
                    if (null == cell) {
                        cell = row.createCell(index);
                    }
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    String value = null == cell.getStringCellValue() ? "" : cell.getStringCellValue();

                    Field field = fields[index];
                    String fieldName = field.getName();
                    String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    Method setMethod = vo.getClass().getMethod(methodName, new Class[]{String.class});
                    setMethod.invoke(vo, new Object[]{value});
                    index++;
                }
                if (ExcelUtil.isHasValues(vo)) {//判断对象属性是否有值
                    list.add(vo);
                    vo.getClass().getConstructor(new Class[]{}).newInstance(new Object[]{});//重新创建一个vo对象
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();//关闭流
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return list;

    }

    @Override
    public RspResult save(Withdraw entity) {
        return null;
    }

    @Override
    public Withdraw findById(Long aLong) {
        return null;
    }

    @Override
    public RspResult update(Withdraw entity) {
        return null;
    }

    @Override
    public RspResult delById(Long aLong) {
        return null;
    }
}
