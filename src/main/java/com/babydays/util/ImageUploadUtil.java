package com.babydays.util;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.babydays.model.Result;

/**
* @ClassName: ImageUploadUtil
* @Description: TODO(图片上传并返回路径)
* @author chaiqianjin
* @date 2018年8月17日
*
*/
public class ImageUploadUtil {
	
	private static Logger loggerFactory = LoggerFactory.getLogger(ImageUploadUtil.class);
	
	/**
	* @Title: ImageUpload
	* @Description: TODO(图片上传方法)
	* @param @param file 图片资源
	* @param @param request 
	* @param @param position 图片实际存储位置前缀
	* @param @return    参数
	* @return Result    返回类型
	* @throws
	*/
	public static Result ImageUpload(MultipartFile file,HttpServletRequest request){
		if (file.getSize() > 0) {
			boolean checkFile = checkFile(file.getOriginalFilename());
			if (checkFile) {
				loggerFactory.info("图片上传开始...");
				Random random = new Random();
				int ran1 = random.nextInt(10);
		        int ran2 = random.nextInt(10);
		        int ran3 = random.nextInt(10);
		        int ran4 = random.nextInt(10);
				String imageName = new Date().getTime()+ran1+ran2+ran3+ran4+".jpg";
				
				//图片存储的实际路径
				String position = CommonUrlUtil.UPLOAD_PICTURE_PATH;
				
				File targeImage = new File(position, imageName);
				if (!targeImage.exists()) {
					targeImage.mkdirs();
				}
				//保存
				try {
					file.transferTo(targeImage);
				} catch (Exception e) {
					e.printStackTrace();
					return ResultUtil.error(400, "上传失败，错误原因：图片上传异常，"+e.getMessage());
				}
				loggerFactory.info("图片上传结束...");
				HashMap<String, Object> map = new HashMap<String, Object>();
				//拼接图片访问路径
				imageName = CommonUrlUtil.PREFIX_PICTURE_URL+imageName;
				map.put("imageUrl", imageName);
				return ResultUtil.success(map);
			} else {
				return ResultUtil.error(400, "上传失败，错误原因：文件类型错误");
			}
			
			
		} else {
			return ResultUtil.error(400, "上传失败，错误原因：图片资源不存在");
		}
	}
	
	
	
	
	
	
	
	
	
	/**
     * 判断是否为允许的上传文件类型,true表示允许
     */
    private static boolean checkFile(String fileName) {
        //设置允许上传文件类型
        String suffixList = "jpg,gif,png,ico,bmp,jpeg";
        // 获取文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf(".")
                + 1, fileName.length());
        if (suffixList.contains(suffix.trim().toLowerCase())) {
            return true;
        }
        return false;
    }
	
	
	
	
	
}
