package com.babydays.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.babydays.model.BDocument;
import com.babydays.model.BStudent;
import com.babydays.model.ListResult;
import com.babydays.model.QueryModel;
import com.babydays.service.DocumentService;
import com.babydays.service.StudentService;
import com.babydays.util.ResultUtil;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/picvid")
public class PicAndVidController {
	
	
	@Autowired
	private DocumentService documentService;
	
	@Autowired
	private StudentService studentService;
	
	
	
	/**
	* @Title: addPicture
	* @Description: TODO(添加图片记录)
	* @param @param document
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/picture/addPicture",method=RequestMethod.POST)
	@ResponseBody
	public Object addPicture(@RequestBody BDocument document) {
		String title = document.getTitle();
		String content = document.getContent();
		String imgs = document.getImgs();
		Integer uid = document.getUid();
		String author = document.getAuthor();
		String position = document.getPosition();
		Integer stuId = document.getStuId();
		Integer classId = document.getClassId();
		Date createtime = document.getCreatetime();
		if (title == null || title == "") {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (content == null || content == "") {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (imgs == null || imgs == "") {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (uid == null) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (author == null || author == "") {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (position == null || position == "") {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (classId == null || classId <= 0) {
			if (stuId == null) {
				return ResultUtil.error(400, "添加失败，缺少必要参数");
			}
		}
		if (createtime == null) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		
		//文档类型 2是图片文档
		document.setType(2);
		try {
			if (classId == null || classId <= 0) {
				documentService.addDocument(document);
				return ResultUtil.success();
			}else {
				//按照班级添加
				HashMap<String,Object> valMap = new HashMap<String,Object>();
				valMap.put("pageIndex", 0);
				valMap.put("pageSize", 10000);
				valMap.put("query", "");
				valMap.put("gardenId", 0);
				valMap.put("classId", classId);
				valMap.put("type", -1);
				ListResult studentList = studentService.studentList(valMap);
				List<BStudent> list = studentList.getList();
				if (studentList.getTotal()>0) {
					documentService.addDocumentByStudentList(list,document);
					return ResultUtil.success();
				}else {
					return ResultUtil.error(400, "添加失败,当前班级没有学生");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "添加失败，错误原因："+e.getMessage());
		}
		
	}
	
	
	
	/**
	* @Title: deletePictureById
	* @Description: TODO(根据id删除对应图片记录)
	* @param @param document
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/picture/deletePictureById",method=RequestMethod.POST)
	@ResponseBody
	public Object deletePictureById(@RequestBody BDocument document) {
		Integer id = document.getId();
		if (id == null || id <=0) {
			return ResultUtil.error(400, "删除失败，缺少必要参数");
		}
		try {
			documentService.deleteDocumentById(id);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "删除失败，错误原因："+e.getMessage());
		}
		
	}
	
	
	
	
	
	/**
	* @Title: updatePicture
	* @Description: TODO(修改图片记录)
	* @param @param document
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/picture/updatePicture",method=RequestMethod.POST)
	@ResponseBody
	public Object updatePicture(@RequestBody BDocument document) {
		Integer id = document.getId();
		String title = document.getTitle();
		String content = document.getContent();
		String imgs = document.getImgs();
		Integer uid = document.getUid();
		String author = document.getAuthor();
		String position = document.getPosition();
		Integer stuId = document.getStuId();
		Date createtime = document.getCreatetime();
		if (id == null) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (title == null || title == "") {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (content == null || content == "") {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (imgs == null || imgs == "") {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (uid == null) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (author == null || author == "") {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (position == null || position == "") {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (stuId == null) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (createtime == null) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		
		//文档类型 2是图片文档
		document.setType(2);
		try {
			documentService.updateDocument(document);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "修改失败，错误原因："+e.getMessage());
		}
		
	}
	
	
	
	/**
	* @Title: getPictures
	* @Description: TODO(根据学生id获取所有的图片文档)
	* @param @param queryModel
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/picture/getPictures",method= RequestMethod.POST)
	@ResponseBody
	public Object getPictures(@RequestBody QueryModel queryModel) {
		String query = queryModel.getQuery();
		Integer pageNum = queryModel.getPageNum();
		Integer pageSize = queryModel.getPageSize();
		Integer stuId = queryModel.getStuId();
		if (pageNum <= 0 || pageSize <= 0) {
			return ResultUtil.error(400, "请求参数错误，提示：需要pageNum>0 pageSize>0");
		}
		PageInfo<BDocument> pictures;
		try {
			pictures = documentService.getPictures(query,pageNum,pageSize,stuId);
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("pictures", pictures);
			return ResultUtil.success(map);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "获取数据失败，失败原因："+e.getMessage());
		}
	}
	
	
	
	
	/**
	* @Title: addVideo
	* @Description: TODO(添加视频记录)
	* @param @param document
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/video/addVideo",method=RequestMethod.POST)
	@ResponseBody
	public Object addVideo(@RequestBody BDocument document) {
		String title = document.getTitle();
		String content = document.getContent();
		String videos = document.getVideos();
		Integer uid = document.getUid();
		String author = document.getAuthor();
		String position = document.getPosition();
		Integer stuId = document.getStuId();
		Integer classId = document.getClassId();
		Date createtime = document.getCreatetime();
		if (title == null || title == "") {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (content == null || content == "") {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (videos == null || videos == "") {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (uid == null) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (author == null || author == "") {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (position == null || position == "") {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (classId == null || classId <= 0) {
			if (stuId == null) {
				return ResultUtil.error(400, "添加失败，缺少必要参数");
			}
		}
		if (createtime == null) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		
		//文档类型 3是视频文档
		document.setType(3);
		try {
			if (classId == null || classId <= 0) {
				documentService.addDocument(document);
				return ResultUtil.success();
			}else {
				//按照班级添加
				HashMap<String,Object> valMap = new HashMap<String,Object>();
				valMap.put("pageIndex", 0);
				valMap.put("pageSize", 10000);
				valMap.put("query", "");
				valMap.put("gardenId", 0);
				valMap.put("classId", classId);
				valMap.put("type", -1);
				ListResult studentList = studentService.studentList(valMap);
				List<BStudent> list = studentList.getList();
				if (studentList.getTotal()>0) {
					documentService.addDocumentByStudentList(list,document);
					return ResultUtil.success();
				}else {
					return ResultUtil.error(400, "添加失败,当前班级没有学生");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "添加失败，错误原因："+e.getMessage());
		}
		
	}
	
	
	
	
	/**
	* @Title: deleteVideoById
	* @Description: TODO(根据id删除对应视频记录)
	* @param @param document
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/video/deleteVideoById",method=RequestMethod.POST)
	@ResponseBody
	public Object deleteVideoById(@RequestBody BDocument document) {
		Integer id = document.getId();
		if (id == null || id <=0) {
			return ResultUtil.error(400, "删除失败，缺少必要参数");
		}
		try {
			documentService.deleteDocumentById(id);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "删除失败，错误原因："+e.getMessage());
		}
		
	}
	
	
	
	
	
	/**
	* @Title: updateVideo
	* @Description: TODO(修改视频记录)
	* @param @param document
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/video/updateVideo",method=RequestMethod.POST)
	@ResponseBody
	public Object updateVideo(@RequestBody BDocument document) {
		Integer id = document.getId();
		String title = document.getTitle();
		String content = document.getContent();
		String videos = document.getVideos();
		Integer uid = document.getUid();
		String author = document.getAuthor();
		String position = document.getPosition();
		Integer stuId = document.getStuId();
		Date createtime = document.getCreatetime();
		if (id == null) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (title == null || title == "") {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (content == null || content == "") {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (videos == null || videos == "") {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (uid == null) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (author == null || author == "") {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (position == null || position == "") {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (stuId == null) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (createtime == null) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		
		//文档类型 3是视频文档
		document.setType(3);
		try {
			documentService.updateDocument(document);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "修改失败，错误原因："+e.getMessage());
		}
		
	}
	
	
	/**
	* @Title: getVideos
	* @Description: TODO(根据学生id获取所有的视频文档)
	* @param @param queryModel
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/video/getVideos",method= RequestMethod.POST)
	@ResponseBody
	public Object getVideos(@RequestBody QueryModel queryModel) {
		String query = queryModel.getQuery();
		Integer pageNum = queryModel.getPageNum();
		Integer pageSize = queryModel.getPageSize();
		Integer stuId = queryModel.getStuId();
		if (pageNum <= 0 || pageSize <= 0) {
			return ResultUtil.error(400, "请求参数错误，提示：需要pageNum>0 pageSize>0");
		}
		PageInfo<BDocument> videos;
		try {
			videos = documentService.getVideos(query,pageNum,pageSize,stuId);
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("videos", videos);
			return ResultUtil.success(map);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "获取数据失败，失败原因："+e.getMessage());
		}
	}
	
	
	
	
	
	
	
	
	

}
