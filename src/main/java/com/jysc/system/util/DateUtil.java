package com.jysc.system.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtil {
	
	
	/**
	 * Description:{ 获取系统时间字符串     格式：yyyy-MM-dd HH:mm:ss }
	 * @return
	 */
	public static String getNowDateTime(){
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	/**
	 * Description:{ 获取系统时间字符串     格式：yyyy/MM/dd HH:mm:ss}
	 * @return
	 */
	public static String getDateTimeNow(){
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	
	/**
	 * Description:{ 获取系统时间字符串     格式：yyyy-MM-dd }
	 * @return
	 */
	public static String getStringDateNum(){
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	 /** 
		 * 获得指定日期的后一天 
		 * @param specifiedDay 
		 * @return 
		 */ 
		 public static String getSpecifiedDayAfter(String specifiedDay){ 
			 Calendar c = Calendar.getInstance(); 
			 Date date=null; 
			 try { 
			 date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay); 
			 } catch (ParseException e) { 
			 e.printStackTrace(); 
			 } 
			 c.setTime(date); 
			 int day=c.get(Calendar.DATE); 
			 c.set(Calendar.DATE,day+1); 
	
			 String dayAfter=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()); 
			 return dayAfter; 
		 } 
		 /** 
		 * 获得指定日期的前一天 
		 * @param specifiedDay 
		 * @return 
		 * @throws Exception 
		 */ 
		 public static String getSpecifiedDayBefore(String specifiedDay){ 
			 //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
			 Calendar c = Calendar.getInstance(); 
			 Date date=null; 
			 try { 
			 date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay); 
			 } catch (ParseException e) { 
			 e.printStackTrace(); 
			 } 
			 c.setTime(date); 
			 int day=c.get(Calendar.DATE); 
			 c.set(Calendar.DATE,day-1); 
	
			 String dayBefore=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()); 
			 return dayBefore; 
		 }
		 static String datetimeFormat = "yyyy-MM-dd";
		 public static Date getDateFromString(String s)  {
		    	Date returnDate = new Date();
		        try {
		            SimpleDateFormat sdf = new SimpleDateFormat(datetimeFormat);
		            returnDate = sdf.parse(s);
		            
		        } catch (ParseException e) {
		            e.printStackTrace();
		        }
		        return returnDate;
		    }
			
		    /*
		     * 该方法用于比较两个用字符串表示的日期
		     * param strDate1,strDate2 要求的格式为'yyyy-mm-dd'
		     * 返回值为两个日期相差的天数
		     */
		    public static int compareDate(String strDate1, String strDate2)
		                            {
		        int returnVal = 0;
		 
		        try {
		            Date date1 = getDateFromString(strDate1);
		            Date date2 = getDateFromString(strDate2);
		            returnVal = (int) ((date2.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24));
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		 
		        return returnVal;
		    }
		    /**
			 * Description:{ 获取系统时间字符串     格式：yyyy年MM月dd }
			 * @return
			 */
			public static String getDate(){
				Date currentTime = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd");
				String dateString = formatter.format(currentTime);
				return dateString;
			}
			/**
			 * 系统开始时间
			 * @return
			 */
			public static String getSystemStartDate(){
				String dateString ="2019-01-01";
				return dateString;
			}
			
}
