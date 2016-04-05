package com.pianostudy.ui.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileUtil {

	 /** 
     * 根据URL直接读文件内容，前提是这个文件当中的内容是文本，函数的返回值就是文件当中的内容 
     */  
    public static String readTxtFile(String urlStr, String encoding)  
            throws Exception {  
    	
    	
        StringBuffer sb = new StringBuffer();  
        String line = null;  
        BufferedReader buffer = null;  
        try {  
            // 创建一个URL对象  
            URL url = new URL(urlStr);  
            // 创建一个Http连接  
            HttpURLConnection urlConn = (HttpURLConnection) url  
                    .openConnection();  
            // 使用IO流读取数据  
            buffer = new BufferedReader(new InputStreamReader(urlConn  
                    .getInputStream(), encoding));  
            while ((line = buffer.readLine()) != null) {  
                sb.append(line);  
                sb.append("\n");
            }  
        } catch (Exception e) {  
            throw e;  
        } finally {  
            try {  
            	if(buffer != null){
            		buffer.close();  
            	}
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return sb.toString();  
    }  
}
