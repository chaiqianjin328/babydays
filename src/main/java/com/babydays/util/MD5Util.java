package com.babydays.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {


	/**
	* @Title: md5Password
	* @Description: TODO(MD5加密方法)
	* @param @param password
	* @param @return    参数
	* @return String    返回类型
	* @throws
	*/
	public static String md5Password(String context) {

       try {
           // 得到一个信息摘要器
           MessageDigest digest = MessageDigest.getInstance("md5");
           byte[] result = digest.digest(context.getBytes());
           StringBuffer buffer = new StringBuffer();
           // 把每一个byte 做一个与运算 0xff;
           for (byte b : result) {
               // 与运算
               int number = b & 0xff;// 加盐
               String str = Integer.toHexString(number);
               if (str.length() == 1) {
                   buffer.append("0");
               }
               buffer.append(str);
           }

           // 标准的md5加密后的结果
           return buffer.toString();
       } catch (NoSuchAlgorithmException e) {
           e.printStackTrace();
           return "errortoken";
       }

	}
	
}
