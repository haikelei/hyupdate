package com.empathy;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {

    /**
     * 导出Excel
     * @param sheetName sheet名称
     * @param title 标题
     * @param values 内容
     * @param wb HSSFWorkbook对象
     * @return
     */
    public static HSSFWorkbook getHSSFWorkbook(String sheetName, String []title, String [][]values, HSSFWorkbook wb){

        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if(wb == null){
            wb = new HSSFWorkbook();
        }

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        //声明列对象
        HSSFCell cell = null;

        //创建标题
        for(int i=0;i<title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }

        //创建内容
        for(int i=0;i<values.length;i++){
            row = sheet.createRow(i + 1);
            for(int j=0;j<values[i].length;j++){
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(values[i][j]);
            }
        }
        return wb;
    }


    /**
     * @param @param  is
     * @param @param  excelFileName
     * @param @return
     * @param @throws IOException
     * @return Workbook
     * @throws
     * @Title: createWorkbook
     * @Description: 判断excel文件后缀名，生成不同的workbook
     */
    public static Workbook createWorkbook(InputStream is, String excelFileName) throws IOException {
        if (excelFileName.endsWith(".xls")) {
            return new HSSFWorkbook(is);
        } else if (excelFileName.endsWith(".xlsx")) {
            return new XSSFWorkbook(is);
        }
        return null;
    }

    /**
     * @param @param  workbook
     * @param @param  sheetIndex
     * @param @return
     * @return Sheet
     * @throws
     * @Title: getSheet
     * @Description: 根据sheet索引号获取对应的sheet
     */
    public static Sheet getSheet(Workbook workbook, int sheetIndex) {
        return workbook.getSheetAt(0);
    }


    /**
     * @param @param  vo javaBean
     * @param @param  is 输入流
     * @param @param  excelFileName
     * @param @return
     * @return List<Object>
     * @throws
     * @Title: importDataFromExcel
     * @Description: 将sheet中的数据保存到list中，
     * 1、调用此方法时，vo的属性个数必须和excel文件每行数据的列数相同且一一对应，vo的所有属性都为String
     * 2、在action调用此方法时，需声明
     * private File excelFile;上传的文件
     * private String excelFileName;原始文件的文件名
     * 3、页面的file控件name需对应File的文件名
     */
    public static List<Object> importDataFromExcel(Object vo, InputStream is, String excelFileName) {
        List<Object> list = new ArrayList<>();
        try {
            //创建工作簿
            Workbook workbook = createWorkbook(is, excelFileName);
            //创建工作表sheet
            Sheet sheet = getSheet(workbook, 0);
            //获取sheet中数据的行数
            int rows = sheet.getPhysicalNumberOfRows();
            //获取表头单元格个数
            int cells = sheet.getRow(0).getPhysicalNumberOfCells();
            //利用反射，给JavaBean的属性进行赋值
            Field[] fields = vo.getClass().getDeclaredFields();
            for (int i = 1; i < rows; i++) {//第一行为标题栏，从第二行开始取数据
                vo = new Object();
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
                if (isHasValues(vo)) {//判断对象属性是否有值
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

    /**
     * @param @param  object
     * @param @return
     * @return boolean
     * @throws
     * @Title: isHasValues
     * @Description: 判断一个对象所有属性是否有值，如果一个属性有值(分空)，则返回true
     */
    public static boolean isHasValues(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        boolean flag = false;
        for (int i = 0; i < fields.length; i++) {
            String fieldName = fields[i].getName();
            String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            Method getMethod;
            try {
                getMethod = object.getClass().getMethod(methodName);
                Object obj = getMethod.invoke(object);
                if (null != obj && "".equals(obj)) {
                    flag = true;
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return flag;

    }


    public static <T> void exportDataToExcel(List<T> list, String[] headers, String title, OutputStream os) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        //生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        //设置表格默认列宽15个字节
        sheet.setDefaultColumnWidth(15);
        //生成一个样式
        HSSFCellStyle style = getCellStyle(workbook);
        //生成一个字体
        HSSFFont font = getFont(workbook);
        //把字体应用到当前样式
        style.setFont(font);

        //生成表格标题
        HSSFRow row = sheet.createRow(0);
        row.setHeight((short) 300);
        HSSFCell cell = null;

        for (int i = 0; i < headers.length; i++) {
            cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //将数据放入sheet中
        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i + 1);
            T t = list.get(i);
            //利用反射，根据JavaBean属性的先后顺序，动态调用get方法得到属性的值
            Field[] fields = t.getClass().getFields();
            try {
                for (int j = 0; j < fields.length; j++) {
                    cell = row.createCell(j);
                    Field field = fields[j];
                    String fieldName = field.getName();
                    String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    Method getMethod = t.getClass().getMethod(methodName, new Class[]{});
                    Object value = getMethod.invoke(t, new Object[]{});

                    if (null == value)
                        value = "";
                    cell.setCellValue(value.toString());

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            workbook.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                os.flush();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * @param @param  workbook
     * @param @return
     * @return HSSFCellStyle
     * @throws
     * @Title: getCellStyle
     * @Description: 获取单元格格式
     */
    public static HSSFCellStyle getCellStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setLeftBorderColor(HSSFCellStyle.BORDER_THIN);
        style.setRightBorderColor(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        return style;
    }

    /**
     * @param @param  workbook
     * @param @return
     * @return HSSFFont
     * @throws
     * @Title: getFont
     * @Description: 生成字体样式
     */
    public static HSSFFont getFont(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.WHITE.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        return font;
    }

    public static boolean isIE(HttpServletRequest request) {
        return request.getHeader("USER-AGENT").toLowerCase().indexOf("msie") > 0 ? true : false;
    }













}
