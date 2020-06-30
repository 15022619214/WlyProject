package com.jysc.system.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

public class FileUtils {
    public static final String basePath = "E:/Yhdz/ExcelOut";
    public static File getUploadFile(String filename){
        String datePath = getUploadPath();
        String path = basePath + datePath + filename;
        File newFile=new File(path);
        String proattach = datePath + filename;
        while (newFile.exists()){
            proattach = datePath + new Date().getTime() + '-' +  filename;
            path= basePath + proattach;
            newFile=new File(path);
        }
    
        return newFile;
    }
    public static File changeMultipleFileToFile(MultipartFile file) {
        File path = null;
        if (file != null) { 
            try {
                String fileRealName = file.getOriginalFilename();//获得原始文件名; 
                int pointIndex =  fileRealName.lastIndexOf(".");//点号的位置     
                String fileSuffix = fileRealName.substring(pointIndex);//截取文件后缀  
                String fileNewName = DateUtil.getNowDateTime();//文件new名称时间戳
                String saveFileName = fileNewName.concat(fileSuffix);//文件存取名 
                String filePath  = "E:/img/yhdz" ;
                path = new File(filePath); //判断文件路径下的文件夹是否存在，不存在则创建
                if (!path.exists()) {
                    path.mkdirs();
                }           
                File savedFile = new File(filePath);
                boolean isCreateSuccess = savedFile.createNewFile(); // 是否创建文件成功
                System.out.println(isCreateSuccess);
               // if(isCreateSuccess){      //将文件写入      
                    //第一种         
                    System.out.println("e");
                    file.transferTo(savedFile); 
                     //第二种
                   /* savedFile = new File(filePath,saveFileName);
                    FileUtils.copyInputStreamToFile(mufile.getInputStream(),savedFile);*/
                    return savedFile;
                //} 
              
            } catch (Exception e) {
                
                e.printStackTrace();   
                return null;
            }
        }else {
           
            System.out.println("文件是空的");
            return null;
        }
        
    }

    /*
     * 获取文件上传的路径
     * @return 上传的相对路径,以时间来表示，如/2017/03/02/
     */
    public static String getUploadPath(){
        Date d = new Date();
        Calendar calender = Calendar.getInstance();  
        calender.setTime(d);    
        String year = String.valueOf(calender.get(Calendar.YEAR));
        String month = "";
        String day = "";
        int month_int = calender.get(Calendar.MONTH)+1;
        if(month_int < 10){
            month = "0" + String.valueOf(month_int);
        }
        else{
            month = String.valueOf(month_int);
        }
        int day_int = calender.get(Calendar.DAY_OF_MONTH);
        if (day_int < 10){
            day = "0" + String.valueOf(day_int);
        }
        else{
            day = String.valueOf(day_int);
        }
        String datePath = '/' + year+'/'+month+'/'+day+'/';
        return datePath;
    }

}
