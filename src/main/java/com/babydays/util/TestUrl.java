package com.babydays.util;

import java.net.URL;
import java.net.URLConnection;

public class TestUrl {

	public static void main(String[] args) {
        //最好使用下面这个，上面那个超时时间不定，所以可能会导致卡住的情况
        testUrlWithTimeOut("http://1.3.3.3", 2000);
    }
	
	
	
	public static Boolean testUrlWithTimeOut(String urlString,int timeOutMillSeconds){
        long lo = System.currentTimeMillis();
        URL url;  
        try {  
             url = new URL(urlString);  
             URLConnection co =  url.openConnection();
             co.setConnectTimeout(timeOutMillSeconds);
             co.connect();
             System.out.println("连接可用");
             System.out.println(System.currentTimeMillis()-lo);
             return true;
        } catch (Exception e1) {  
             System.out.println("连接打不开!");  
             System.out.println(System.currentTimeMillis()-lo);
             url = null;
             return false;
        }  
    }

	
	
	
}
