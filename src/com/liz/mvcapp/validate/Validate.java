package com.liz.mvcapp.validate;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
	public static boolean checkPhone(String phone){
		Pattern pattern = Pattern.compile("^1[3|4|5|8][0-9]\\d{8}$");
		Matcher matcher = pattern.matcher(phone);
		return matcher.matches();
	}
	public static boolean checkPassword(){
		//var phonePattern = /^1[3|4|5|8][0-9]\d{8}$/;
		return false;
	}
	/** 
     * @param str 
     * @return 
     * @Date: 2013-9-6   
     * @Author: lulei   
     * @Description:  32位小写MD5 
     */  
    public static String parseStrToMd5L32(String str){  
        String reStr = null;  
        try {  
            MessageDigest md5 = MessageDigest.getInstance("MD5");  
            byte[] bytes = md5.digest(str.getBytes());  
            StringBuffer stringBuffer = new StringBuffer();  
            for (byte b : bytes){  
                int bt = b&0xff;  
                if (bt < 16){  
                    stringBuffer.append(0);  
                }   
                stringBuffer.append(Integer.toHexString(bt));  
            }  
            reStr = stringBuffer.toString();  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        }  
        return reStr;  
    }  
      
    /** 
     * @param str 
     * @return 
     * @Date: 2013-9-6   
     * @Author: lulei   
     * @Description: 32位大写MD5 
     */  
    public static String parseStrToMd5U32(String str){  
        String reStr = parseStrToMd5L32(str);  
        if (reStr != null){  
            reStr = reStr.toUpperCase();  
        }  
        return reStr;  
    }  
      
    /** 
     * @param str 
     * @return 
     * @Date: 2013-9-6   
     * @Author: lulei   
     * @Description: 16位小写MD5 
     */  
    public static String parseStrToMd5U16(String str){  
        String reStr = parseStrToMd5L32(str);  
        if (reStr != null){  
            reStr = reStr.toUpperCase().substring(8, 24);  
        }  
        return reStr;  
    }  
      
    /** 
     * @param str 
     * @return 
     * @Date: 2013-9-6   
     * @Author: lulei   
     * @Description: 16位大写MD5 
     */  
    public static String parseStrToMd5L16(String str){  
        String reStr = parseStrToMd5L32(str);  
        if (reStr != null){  
            reStr = reStr.substring(8, 24);  
        }  
        return reStr;  
    }  
}
