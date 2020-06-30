//package com.jysc.system.util;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import org.apache.poi.hssf.usermodel.*;
//import org.apache.poi.ss.util.CellRangeAddress;
//import org.apache.poi.ss.util.RegionUtil;
//
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.Calendar;
//import java.util.GregorianCalendar;
//
//public class ImportExcel {
//    /**
//     * 对账数据导出
//     */
//    public static JSONObject getExcelPath_Work(JSONArray jsa, String[] shetitle, String[] shetitlecode, String filename) throws IOException {
//        // 创建excel
//        HSSFWorkbook wb = new HSSFWorkbook();
//        // 创建标题栏样式
//        HSSFCellStyle styleTitle = wb.createCellStyle();
//        styleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 居中
//        styleTitle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
//        styleTitle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
//        styleTitle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
//        styleTitle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
//        HSSFFont fontTitle = wb.createFont();
//        // 宋体加粗
//        fontTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//        fontTitle.setFontName("宋体");
//        fontTitle.setFontHeight((short) 200);
//        styleTitle.setFont(fontTitle);
//        // 创建sheet
//        HSSFSheet sheet = wb.createSheet(filename);
//        sheet.autoSizeColumn(1);
//        sheet.createFreezePane( 0, 1, 0, 1 );
//
//        for (int i = 0; i < shetitle.length; i++) {
//            sheet.setColumnWidth(i,20*256);
//        }
//        // 创建一行
//        HSSFRow rowTitle = sheet.createRow(0);
//        HSSFCell cells = null;
//        for (int i = 0; i < shetitle.length; i++) {
//            cells = rowTitle.createCell(i);
//            cells.setCellValue(shetitle[i]);
//            cells.setCellStyle(styleTitle);
//        }
//        HSSFCellStyle styleCenter = wb.createCellStyle();
//        styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 居中
//        styleCenter.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
//        styleCenter.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
//        styleCenter.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
//        styleCenter.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
//        int lasnum = 0;
//        double jjeMax = 0;
//        double djeMax = 0;
//        for (int i = 0; i < jsa.size(); i++) {
//            JSONObject jsObject = jsa.getJSONObject(i);
//            HSSFRow row = sheet.createRow(i + 1);
//
//            HSSFCell cell = row.createCell(0);
//            cell.setCellValue(i+1);
//            cell.setCellStyle(styleTitle);
//            for (int j = 1; j < shetitlecode.length; j++) {
//                cell = row.createCell(j);
//                if(j == 1){
//                    cell.setCellValue(Constants.GET_DATE(jsObject.getString(shetitlecode[j])));
//                    cell.setCellStyle(styleCenter);
//                }else if(j == 2){
//                    cell.setCellValue(Constants.GET_JSFS(jsObject.getString(shetitlecode[j])));
//                    cell.setCellStyle(styleCenter);
//                }else if(j == 5){
//                    cell.setCellValue(jsObject.getDouble(shetitlecode[j]));
//                    styleCenter.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
//                    cell.setCellStyle(styleCenter);
//                }else if(j == 6){
//                    cell.setCellValue(jsObject.getDouble(shetitlecode[j]));
//                    styleCenter.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
//                    cell.setCellStyle(styleCenter);
//                }else{
//                    cell.setCellValue(jsObject.getString(shetitlecode[j]));
//                    cell.setCellStyle(styleCenter);
//                }
//            }
//            lasnum++;
//            jjeMax += jsa.getJSONObject(i).getDouble("jje");
//            djeMax += jsa.getJSONObject(i).getDouble("dje");
//        }
//        HSSFRow row = sheet.createRow(lasnum+1);
//
//        HSSFCell cell = row.createCell(0);
//        cell.setCellValue("合计");
//        cell.setCellStyle(styleTitle);
//
//        for (int i = 1; i < shetitle.length; i++) {
//            if(i!=5||i!=6){
//                cell = row.createCell(i);
//                cell.setCellValue(new HSSFRichTextString(""));
//                cell.setCellStyle(styleCenter);
//            }
//        }
//
//        cell = row.createCell(5);
//        cell.setCellValue(jjeMax);
//        styleCenter.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
//        cell.setCellStyle(styleCenter);
//
//        cell = row.createCell(6);
//        cell.setCellValue(djeMax);
//        styleCenter.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
//        cell.setCellStyle(styleCenter);
//
//        Calendar calendar=Calendar.getInstance();
//        String s=filename+"["+calendar.get(GregorianCalendar.SECOND)+calendar.get(GregorianCalendar.MINUTE)+"]"+calendar.get(GregorianCalendar.YEAR)+"-"+(calendar.get(GregorianCalendar.MONTH)+1)+"-"+calendar.get(GregorianCalendar.DAY_OF_MONTH)+".xls";
//        FileOutputStream fout = new FileOutputStream(Constants.UPLOAD_PATH+s);
//        wb.write(fout);
//        fout.close();
//        fout.flush();
//        JSONObject jon = new JSONObject();
//        jon.put("info", "success");
//        jon.put("path", Constants.UPLOAD_PATH+s);
//        return jon;
//    }
//
//}
