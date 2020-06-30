package com.jysc.system.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class Constants {
    public static final String SESSION_CURRENT_USER_KEY = "Userinfo";

    public static final String UPLOAD_PATH = "E:/Yhdz/ExcelOut/";

    public static File UPLOD(String basePath, String filename) {
        File file = new File(basePath);
        File newFile = null;
        File[] fileList = file.listFiles();
        if (file.exists()) {
            if (fileList != null) {
                for (File f : fileList) {
                    f.delete();
                }
                String path = basePath + filename;
                newFile = new File(path);
            } else {

                String path = basePath + filename;
                newFile = new File(path);
            }
        } else {
            file.mkdir();
            String path = basePath + filename;
            newFile = new File(path);
        }
        return newFile;
    }

    public static Workbook READ_EXCEL(File file,String fileName){
        Workbook wb = null;
        if(fileName==null){
            return null;
        }
        String extString = fileName.substring(fileName.lastIndexOf("."));
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            if(".xls".equals(extString)){
                return wb = new HSSFWorkbook(inputStream);
            }else if(".xlsx".equals(extString)){
                return wb = new XSSFWorkbook(inputStream);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }


}
