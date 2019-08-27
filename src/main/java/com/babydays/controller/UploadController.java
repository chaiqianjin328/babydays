package com.babydays.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.babydays.util.ImageUploadUtil;
import com.babydays.util.VideoUploadUtil;
import com.babydays.util.VoiceUploadUtil;

@Controller
@RequestMapping("/upload")
public class UploadController {

	
	/**
	* @Title: imageUpload
	* @Description: TODO(图片上传接口)
	* @param @param request
	* @param @param file
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping("/imageUpload")
	@ResponseBody
	public Object imageUpload(HttpServletRequest request,@RequestParam(value="image",required=false)MultipartFile file) throws Exception{
		//System.out.println("=========图片=========="+file.getSize());
		return ImageUploadUtil.ImageUpload(file, request);
	}
	
	
	/**
	* @Title: image
	* @Description: TODO(音频上传接口)
	* @param @param request
	* @param @param file
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping("/voiceUpload")
	@ResponseBody
	public Object voiceUpload(HttpServletRequest request,@RequestParam(value="voice",required=false)MultipartFile file) throws Exception{
		//System.out.println("=========音频=========="+file.getSize());
		return VoiceUploadUtil.VoiceUpload(file, request);
	}
	
	
	
	
	/**
	* @Title: videoUpload
	* @Description: TODO(视频上传接口)
	* @param @param request
	* @param @param file
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/videoUpload")
	@ResponseBody
	public Object videoUpload(HttpServletRequest request,@RequestParam(value="video",required=false)MultipartFile file) throws Exception{
		//System.out.println("=========视频=========="+file.getSize());
		return VideoUploadUtil.VideoUpload(file, request);
	}
	
	
	
	/**
	* @Title: videoUploadNew
	* @Description: TODO(视频上传接口)
	* @param @param request
	* @param @param file
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/videoUploadNew")
	@ResponseBody
	public Object videoUploadNew(HttpServletRequest request) throws Exception{
		return VideoUploadUtil.VideoUpload(request);
	}
	
	
	
	
}
