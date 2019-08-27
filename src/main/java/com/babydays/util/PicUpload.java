package com.babydays.util;

import java.io.File;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class PicUpload {

	
	public static String picUploadAndReturnURL(MultipartFile file,HttpServletRequest request,String position){
		if (file.getSize()>0) {
			System.out.println("图片接收开始");  
	        //String path = request.getSession().getServletContext().getRealPath("");  
	        Random random = new Random();
	        int ran1 = random.nextInt(10);
	        int ran2 = random.nextInt(10);
	        int ran3 = random.nextInt(10);
	        int ran4 = random.nextInt(10);
	        String fileName = new java.util.Date().getTime()+ran1+ran2+ran3+ran4+".jpg";  
	        
	        /*System.out.println(path+position);
	        File targetFile = new File(path+position, fileName); */ 
	       
	        File targetFile = new File(position, fileName);
	        
	        if(!targetFile.exists()){  
	            targetFile.mkdirs();  
	        }  
	        //保存  
	        try {  
	        	file.transferTo(targetFile);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }
	        System.out.println(fileName);
	        System.out.println("图片接收結束");
	        //String picUrl = position+fileName;
	        
	        String picUrl = fileName;
	        
	        return picUrl;
		}
		return "no img!";
	}
	
	
	
}
