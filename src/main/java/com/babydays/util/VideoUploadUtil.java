package com.babydays.util;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.babydays.model.Result;

/**
* @ClassName: VideoUploadUtil
* @Description: TODO(视频上传并返回路径)
* @author chaiqianjin
* @date 2018年8月17日
*
*/
public class VideoUploadUtil {
	
	private static Logger loggerFactory = LoggerFactory.getLogger(VideoUploadUtil.class);
	
	/**
	* @Title: VideoUpload
	* @Description: TODO(视频上传方法)
	* @param @param file 视频资源
	* @param @param request 
	* @param @param position 视频实际存储位置前缀
	* @param @return    参数
	* @return Result    返回类型
	* @throws
	*/
	public static Result VideoUpload(MultipartFile file,HttpServletRequest request){
		if (file.getSize() > 0) {
			String fileName = file.getOriginalFilename();
			boolean checkFile = checkFile(fileName);
			if (checkFile) {
				loggerFactory.info("视频上传开始...");
				Random random = new Random();
				int ran1 = random.nextInt(10);
		        int ran2 = random.nextInt(10);
		        int ran3 = random.nextInt(10);
		        int ran4 = random.nextInt(10);
		        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
				String videoName = new Date().getTime()+ran1+ran2+ran3+ran4+"."+suffix;
				
				//视频存储的实际路径
				String position = CommonUrlUtil.UPLOAD_VIDEO_PATH;
				
				File targeVideo = new File(position, videoName);
				if (!targeVideo.exists()) {
					targeVideo.mkdirs();
				}
				//保存
				try {
					file.transferTo(targeVideo);
				} catch (Exception e) {
					e.printStackTrace();
					return ResultUtil.error(400, "上传失败，错误原因：视频上传异常，"+e.getMessage());
				}
				loggerFactory.info("视频上传结束...");
				HashMap<String, Object> map = new HashMap<String, Object>();
				//拼接视频访问路径
				videoName = CommonUrlUtil.PREFIX_VIDEO_URL+videoName;
				map.put("videoUrl", videoName);
				return ResultUtil.success(map);
			} else {
				return ResultUtil.error(400, "上传失败，错误原因：文件格式错误");
			}
			
		} else {
			return ResultUtil.error(400, "上传失败，错误原因：视频资源不存在");
		}
	}
	
	
	public static Result VideoUpload(HttpServletRequest request){
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			MultipartHttpServletRequest multipartHttpServletRequest =(MultipartHttpServletRequest)request;
			Iterator<String> fileNames = multipartHttpServletRequest.getFileNames();
			Map<String, MultipartFile> fileMap = multipartHttpServletRequest.getFileMap();
			HashMap<String, Object> map = new HashMap<String, Object>();
			int fileIndex = 1;
			while (fileNames.hasNext()) {
				String string = (String) fileNames.next();
				MultipartFile file = fileMap.get(string);
				if (file.getSize() > 0) {
					String fileName = file.getOriginalFilename();
					boolean checkFile = checkFile(fileName);
					if (checkFile) {
						loggerFactory.info("视频上传开始...");
						Random random = new Random();
						int ran1 = random.nextInt(10);
				        int ran2 = random.nextInt(10);
				        int ran3 = random.nextInt(10);
				        int ran4 = random.nextInt(10);
				        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
						String videoName = new Date().getTime()+ran1+ran2+ran3+ran4+"."+suffix;
						
						//视频存储的实际路径
						String position = CommonUrlUtil.UPLOAD_VIDEO_PATH;
						
						File targeVideo = new File(position, videoName);
						if (!targeVideo.exists()) {
							targeVideo.mkdirs();
						}
						//保存
						try {
							file.transferTo(targeVideo);
						} catch (Exception e) {
							e.printStackTrace();
							return ResultUtil.error(400, "上传失败，错误原因：视频上传异常，"+e.getMessage());
						}
						loggerFactory.info("视频上传结束...");
						//拼接视频访问路径
						videoName = CommonUrlUtil.PREFIX_VIDEO_URL+videoName;
						map.put("videoUrl"+fileIndex, videoName);
					} else {
						//return ResultUtil.error(400, "上传失败，错误原因：文件格式错误");
						map.put("videoUrl"+fileIndex, "上传失败，错误原因：文件格式错误");
					}
				} else {
					map.put("videoUrl"+fileIndex, "上传失败，错误原因：视频资源不存在");
				}
				fileIndex++;
			}
			return ResultUtil.success(map);
		}else {
			return ResultUtil.error(400, "上传失败，错误原因：请求方式错误");
		}
	}
	
	
	
	
	/**
     * 判断是否为允许的上传文件类型,true表示允许
     */
    private static boolean checkFile(String fileName) {
        //设置允许上传文件类型
        String suffixList = "avi,wmv,mpeg,mp4,mov,mkv,flv,f4v,m4v,rmvb,rm,3gp,dat,ts,mts,vob";
        // 获取文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        if (suffixList.contains(suffix.trim().toLowerCase())) {
            return true;
        }
        return false;
    }
	
	
	
	
	
}
