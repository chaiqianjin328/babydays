package com.babydays.util;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class JspToHtml {
	private static long star = 0;  
    private static long end = 0;  
    private static long ttime = 0;  
  
    // 返回html代码  
    public static String getHtmlCode(String httpUrl, String bianma) {  
        Date before = new Date();  
        star = before.getTime();  
        String htmlCode = "";  
        try {  
            InputStream in;  
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();  
            connection = (HttpURLConnection) url.openConnection();  
            connection.setRequestProperty("User-Agent", "Mozilla/4.0");  
            connection.connect();  
            in = connection.getInputStream();  
            BufferedReader breader = new BufferedReader(new InputStreamReader(in, bianma));
            String currentLine;  
            while ((currentLine = breader.readLine()) != null) {  
                htmlCode += currentLine;  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            Date after = new Date();  
            end = after.getTime();  
            ttime = end - star;  
            System.out.println("执行时间:"+ttime +"秒");  
        }  
        return htmlCode;  
    }  
  
    // 存储文件  
    public static synchronized void writeHtml(String filePath, String info) {  
        try {  
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8");  
            out.write(info);  
            out.flush();  
        } catch (Exception ex) {  
            ex.getMessage();  
        }  
    }  
      
    //main调用  
    public static void main(String[] args) {  
        String url = "http://localhost:8080/babydays/web/growth/advice_detail?detailBtn=CL&parentId=1&stuId=4";  
         writeHtml("/Users/chaiqianjin/babydays-pdf/index.html",getHtmlCode(url,"utf-8"));  
  
        long tmp = Long.parseLong("0");  
        Date before = new Date();
        long currtime = before.getTime();  
        System.out.println(currtime - tmp);  
    }  
	
}
