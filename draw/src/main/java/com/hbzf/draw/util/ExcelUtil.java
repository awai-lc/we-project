package com.hbzf.draw.util;

import com.google.common.collect.Lists;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ExcelUtil {
    private static  final Logger log = LoggerFactory.getLogger(ExcelUtil.class);
    /**
     * 解析Excel
     *
     * @param
     */
    public static List<List<String>> parseExcelByUrl(InputStream inputStream, String fileName) {
        List<List<String>> rows = Lists.newArrayList();
        try {
            Workbook wb = null;
            String extString = fileName.substring(fileName.lastIndexOf("."));
            if (".xls".equals(extString)) {
                wb = new HSSFWorkbook(inputStream);
            } else if (".xlsx".equals(extString)) {
                wb = new XSSFWorkbook(inputStream);
            }
            if(null == wb){
                return rows;
            }
            Sheet sheet = wb.getSheetAt(0);
            //取第一列数据表头
            Row head = sheet.getRow(0);
            int cellNum = 0;
            if (head != null) {
                cellNum = head.getLastCellNum();
            }
            //取数据
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) {
                    continue;
                }
                List<String> rowValue = Lists.newArrayList();
                for (int cellIndex = 0; cellIndex < cellNum; cellIndex++) {
                    //列
                    Cell cell = row.getCell(cellIndex);
                    if (cell == null) {
                        rowValue.add(StringUtils.EMPTY);
                        continue;
                    }
                    String value = getCellValue(cell);
                    rowValue.add(value);
                }
                rows.add(rowValue);
            }
        } catch (Exception e) {
            log.error("parseExcelByUrl error fileName:{}", fileName);
        }
        return rows;
    }

    /**
     * 获取单元格内容
     *
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            // 数字类型
            case Cell.CELL_TYPE_NUMERIC:
                // 处理日期格式、时间格式
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = null;
                    if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
                        sdf = new SimpleDateFormat("HH:mm");
                    } else {// 日期
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                    }
                    Date date = cell.getDateCellValue();
                    return sdf.format(date);
                } else if (cell.getCellStyle().getDataFormat() == 58) {
                    // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    double value = cell.getNumericCellValue();
                    Date date = org.apache.poi.ss.usermodel.DateUtil
                            .getJavaDate(value);
                    return sdf.format(date);
                } else {
                    double value = cell.getNumericCellValue();
                    CellStyle style = cell.getCellStyle();
                    DecimalFormat format = new DecimalFormat();
                    String temp = style.getDataFormatString();
                    // 单元格设置成常规
                    if (temp.equals("General")) {
                        format.applyPattern("#");
                    }
                    return format.format(value);
                }
                // String类型
            case Cell.CELL_TYPE_STRING:
                return cell.getRichStringCellValue().toString();
            // 空类型
            case Cell.CELL_TYPE_BLANK:
                return "";
            default:
                return "";
        }
    }

    /**
     * 创建excel
     *
     * @return
     */
    public static Workbook createExcelByList(List<List<String>> values) {
        Workbook workbook = new XSSFWorkbook();
        Sheet excelSheet0 = workbook.createSheet("result");

        int record = 0;
        for (List<String> value : values) {
            createRow(excelSheet0, value, record);
            record++;
        }
        return workbook;
    }

    private static void createRow(Sheet excelSheet0, List<String> value, int record) {
        Row excelRow = excelSheet0.createRow(record);
        for (int i = 0; i < value.size(); i++) {
            createCell(excelRow, value.get(i), i);
        }
    }

    /**
     * 创建列
     *
     * @param value
     * @param i
     */
    private static void createCell(Row excelRow, String value, int i) {
        Cell cell0 = excelRow.createCell(i);
        cell0.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell0.setCellValue(new XSSFRichTextString(value));
    }

    /**
     * excel 转base64
     *
     * @param workbook
     * @return
     */
    public static String getBase64ByWorkbook(Workbook workbook) {
        byte[] data = null;
        try {
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            workbook.write(swapStream);
            data = swapStream.toByteArray();
        } catch (Exception e) {
            log.error("getBase64ByWorkbook", e);
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (Objects.isNull(data)) {
            return StringUtils.EMPTY;
        }
        return new String(Base64.encodeBase64(data));
    }
}
