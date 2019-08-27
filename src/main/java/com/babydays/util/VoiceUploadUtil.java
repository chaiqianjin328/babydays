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
* @ClassName: VideoUploadUtil
* @Description: TODO(视频上传并返回路径)
* @author chaiqianjin
* @date 2018年8月17日
*
*/
public class VoiceUploadUtil {
	
	private static Logger loggerFactory = LoggerFactory.getLogger(VoiceUploadUtil.class);
	
	/**
	* @Title: VoiceUpload
	* @Description: TODO(音频上传方法)
	* @param @param file 音频资源
	* @param @param request 
	* @param @param position 音频实际存储位置前缀
	* @param @return    参数
	* @return Result    返回类型
	* @throws
	*/
	public static Result VoiceUpload(MultipartFile file,HttpServletRequest request){
		if (file.getSize() > 0) {
			String fileName = file.getOriginalFilename();
			boolean checkFile = checkFile(fileName);
			if (checkFile) {
				loggerFactory.info("音频上传开始...");
				Random random = new Random();
				int ran1 = random.nextInt(10);
		        int ran2 = random.nextInt(10);
		        int ran3 = random.nextInt(10);
		        int ran4 = random.nextInt(10);
		        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
				String voiceName = new Date().getTime()+ran1+ran2+ran3+ran4+"."+suffix;
				
				//音频存储的实际路径
				String position = CommonUrlUtil.UPLOAD_VOICE_PATH;
				
				File targeVideo = new File(position, voiceName);
				if (!targeVideo.exists()) {
					targeVideo.mkdirs();
				}
				//保存
				try {
					file.transferTo(targeVideo);
				} catch (Exception e) {
					e.printStackTrace();
					return ResultUtil.error(400, "上传失败，错误原因：音频上传异常，"+e.getMessage());
				}
				loggerFactory.info("视频上传结束...");
				HashMap<String, Object> map = new HashMap<String, Object>();
				//拼接视频访问路径
				voiceName = CommonUrlUtil.PREFIX_VOICE_URL+voiceName;
				map.put("videoUrl", voiceName);
				return ResultUtil.success(map);
			} else {
				return ResultUtil.error(400, "上传失败，错误原因：文件格式错误");
			}
			
		} else {
			return ResultUtil.error(400, "上传失败，错误原因：音频资源不存在");
		}
	}
	
	
	/**
     * 判断是否为允许的上传文件类型,true表示允许
     */
    private static boolean checkFile(String fileName) {
        //设置允许上传文件类型
        String suffixList = "mp3,aac,wma,ogg,m4a,flac,ape,wav,amr,3gpp,acc,ac3";
        // 获取文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        if (suffixList.contains(suffix.trim().toLowerCase())) {
            return true;
        }
        return false;
    }
	
	
	
	
	
}
