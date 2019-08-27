package com.babydays.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.babydays.model.BComment;
import com.babydays.model.BDocument;
import com.babydays.model.BGarden;
import com.babydays.model.BStudent;
import com.babydays.model.ListResult;
import com.babydays.service.CommentService;
import com.babydays.service.DocumentService;
import com.babydays.service.GardenService;
import com.babydays.service.StudentService;
import com.babydays.util.ResultUtil;

@Controller
@RequestMapping("/web/picvid")
public class WebPicAndVidController {

	
	@Autowired
	private GardenService gardenService;
	
	@Autowired
	private DocumentService documentService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private StudentService studentService;
	
	
	@RequestMapping(value="/img_headmaster",method=RequestMethod.GET)
	public String toImg_headmaster(HttpServletRequest request) {
		List<BGarden> list = gardenService.getAllGarden();
		request.setAttribute("list", list);
		request.setAttribute("picvidBtn", "picvidBtn");
		request.setAttribute("imgBtn", "imgBtn");
		return "web/6-img-headmaster";
	}
	

	@RequestMapping(value="/img_director",method=RequestMethod.GET)
	public String toImg_director(HttpServletRequest request) {
		Integer stuId = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		request.setAttribute("stuId", stuId);
		request.setAttribute("stuName", name);
		request.setAttribute("picvidBtn", "picvidBtn");
		request.setAttribute("imgBtn", "imgBtn");
		return "web/6-img-director";
	}
	
	
	@RequestMapping(value="/img_parents",method=RequestMethod.GET)
	public String toImg_parents(HttpServletRequest request) {
		request.setAttribute("picvidBtn", "picvidBtn");
		request.setAttribute("imgBtn", "imgBtn");
		return "web/6-img-parents";
	}
	
	
	@RequestMapping(value="/video_headmaster",method=RequestMethod.GET)
	public String toVideo_headmaster(HttpServletRequest request) {
		List<BGarden> list = gardenService.getAllGarden();
		request.setAttribute("list", list);
		request.setAttribute("picvidBtn", "picvidBtn");
		request.setAttribute("vidBtn", "vidBtn");
		return "web/6-video-headmaster";
	}
	
	
	@RequestMapping(value="/video_director",method=RequestMethod.GET)
	public String toVideo_director(HttpServletRequest request) {
		Integer stuId = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		request.setAttribute("stuId", stuId);
		request.setAttribute("stuName", name);
		request.setAttribute("picvidBtn", "picvidBtn");
		request.setAttribute("vidBtn", "vidBtn");
		return "web/6-video-director";
	}
	
	@RequestMapping(value="/video_parents",method=RequestMethod.GET)
	public String toVideo_parents(HttpServletRequest request) {
		request.setAttribute("picvidBtn", "picvidBtn");
		request.setAttribute("vidBtn", "vidBtn");
		return "web/6-video-parents";
	}
	
	
	
	
	
	/**
	* @Title: addImage
	* @Description: TODO(添加成长记录)
	* @param @param document
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/addImage",method=RequestMethod.POST)
	@ResponseBody
	public Object addImage(BDocument document,HttpServletRequest request,Integer gardenId,Integer classId) {
		String timeStr = request.getParameter("createTime");
		if (timeStr==null||timeStr=="") {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		try {
			Date date = format.parse(timeStr);
			document.setCreatetime(date);
		} catch (ParseException e1) {
			e1.printStackTrace();
			return ResultUtil.error(400, "添加失败，错误原因："+e1.getMessage());
		}
		
		String title = document.getTitle();
		String content = document.getContent();
		String imgs = document.getImgs();
		Integer uid = document.getUid();
		String author = document.getAuthor();
		Integer stuId = document.getStuId();
		Date createtime = document.getCreatetime();
		if (title == null || title == "") {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (content == null || content == "") {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (uid == null) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (author == null || author == "") {
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
		if (imgs == null || imgs == "") {
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
	* @Title: deleteImageById
	* @Description: TODO(根据成长记录id删除对应成长记录)
	* @param @param document
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/deleteImageById",method=RequestMethod.POST)
	@ResponseBody
	public Object deleteDocumentById(BDocument document) {
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
	* @Title: updateImage
	* @Description: TODO(修改成长记录)
	* @param @param document
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/updateImage",method=RequestMethod.POST)
	@ResponseBody
	public Object updateImage(BDocument document,HttpServletRequest request) {
		String timeStr = request.getParameter("createTime");
		if (timeStr==null||timeStr=="") {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		try {
			Date date = format.parse(timeStr);
			document.setCreatetime(date);
		} catch (ParseException e1) {
			e1.printStackTrace();
			return ResultUtil.error(400, "修改失败，错误原因："+e1.getMessage());
		}
		Integer id = document.getId();
		String title = document.getTitle();
		String content = document.getContent();
		String imgs = document.getImgs();
		Integer uid = document.getUid();
		String author = document.getAuthor();
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
		if (uid == null) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (author == null || author == "") {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (stuId == null) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (createtime == null) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (imgs == null || imgs == "") {
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
	* @Title: imageList
	* @Description: TODO(根据学生id获取所有类型为纯文档的成长档案)
	* @param @param request
	* @param @param response
	* @param @param pageIndex
	* @param @param pageSize
	* @param @param query
	* @param @param stuId
	* @param @return    参数
	* @return ListResult    返回类型
	* @throws
	*/
	@RequestMapping(value="/imageList",method=RequestMethod.GET)
	@ResponseBody
	public ListResult imageList(HttpServletRequest request,HttpServletResponse response,int pageIndex,int pageSize,String query,Integer stuId){
		HashMap<String,Object> valMap = new HashMap<String,Object>();
		valMap.put("pageIndex", pageIndex);
		valMap.put("pageSize", pageSize);
		valMap.put("query", query);
		valMap.put("stuId", stuId);
		valMap.put("type", 2);
		return documentService.documentList(valMap);
	}
	
	
	
	
	/**
	* @Title: addVideo
	* @Description: TODO(添加成长记录)
	* @param @param document
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/addVideo",method=RequestMethod.POST)
	@ResponseBody
	public Object addVideo(BDocument document,HttpServletRequest request,Integer gardenId,Integer classId) {
		String timeStr = request.getParameter("createTime");
		if (timeStr==null||timeStr=="") {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		try {
			Date date = format.parse(timeStr);
			document.setCreatetime(date);
		} catch (ParseException e1) {
			e1.printStackTrace();
			return ResultUtil.error(400, "添加失败，错误原因："+e1.getMessage());
		}
		
		String title = document.getTitle();
		String content = document.getContent();
		String videos = document.getVideos();
		Integer uid = document.getUid();
		String author = document.getAuthor();
		Integer stuId = document.getStuId();
		Date createtime = document.getCreatetime();
		if (title == null || title == "") {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (content == null || content == "") {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (uid == null) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (author == null || author == "") {
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
		if (videos == null || videos == "") {
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
	* @Description: TODO(根据成长记录id删除对应成长记录)
	* @param @param document
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/deleteVideoById",method=RequestMethod.POST)
	@ResponseBody
	public Object deleteVideoById(BDocument document) {
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
	* @Description: TODO(修改成长记录)
	* @param @param document
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/updateVideo",method=RequestMethod.POST)
	@ResponseBody
	public Object updateVideo(BDocument document,HttpServletRequest request) {
		String timeStr = request.getParameter("createTime");
		if (timeStr==null||timeStr=="") {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		try {
			Date date = format.parse(timeStr);
			document.setCreatetime(date);
		} catch (ParseException e1) {
			e1.printStackTrace();
			return ResultUtil.error(400, "修改失败，错误原因："+e1.getMessage());
		}
		Integer id = document.getId();
		String title = document.getTitle();
		String content = document.getContent();
		String videos = document.getVideos();
		Integer uid = document.getUid();
		String author = document.getAuthor();
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
		if (uid == null) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (author == null || author == "") {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (stuId == null) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (createtime == null) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (videos == null || videos == "") {
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
	* @Title: videoList
	* @Description: TODO(根据学生id获取所有类型为纯文档的成长档案)
	* @param @param request
	* @param @param response
	* @param @param pageIndex
	* @param @param pageSize
	* @param @param query
	* @param @param stuId
	* @param @return    参数
	* @return ListResult    返回类型
	* @throws
	*/
	@RequestMapping(value="/videoList",method=RequestMethod.GET)
	@ResponseBody
	public ListResult videoList(HttpServletRequest request,HttpServletResponse response,int pageIndex,int pageSize,String query,Integer stuId){
		HashMap<String,Object> valMap = new HashMap<String,Object>();
		valMap.put("pageIndex", pageIndex);
		valMap.put("pageSize", pageSize);
		valMap.put("query", query);
		valMap.put("stuId", stuId);
		valMap.put("type", 3);
		return documentService.documentList(valMap);
	}
	
	
	
	
	
	/*
	* @Title: addComment
	* @Description: TODO(添加评论)
	* @param @param comment
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/addComment",method=RequestMethod.POST)
	@ResponseBody
	public Object addComment(BComment comment) {
		String author = comment.getAuthor();
		Integer uid = comment.getUid();
		String content = comment.getContent();
		Integer docId = comment.getDocId();
		if (author == null || author == "") {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (uid == null) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (content == null || content == "") {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (docId == null) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		try {
			commentService.addComment(comment);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return  ResultUtil.error(400, "添加失败，错误原因："+e.getMessage());
		}
		
	}
	
	
	
	
	/**
	* @Title: deleteCommentById
	* @Description: TODO(根据评论id删除评论)
	* @param @param comment
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/deleteCommentById",method=RequestMethod.POST)
	@ResponseBody
	public Object deleteCommentById(BComment comment) {
		Integer id = comment.getId();
		if (id == null) {
			return ResultUtil.error(400, "删除失败，缺少必要参数");
		}
		try {
			commentService.deleteCommentById(id);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "删除失败，错误原因："+e.getMessage());
		}

	
	}
	
	
	
	
	
	/**
	* @Title: updateComment
	* @Description: TODO(修改评论)
	* @param @param comment
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/updateComment",method=RequestMethod.POST)
	@ResponseBody
	public Object updateComment(BComment comment) {
		Integer id = comment.getId();
		String author = comment.getAuthor();
		Integer uid = comment.getUid();
		String content = comment.getContent();
		Integer docId = comment.getDocId();
		if (id == null) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (author == null || author == "") {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (uid == null) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (content == null || content == "") {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (docId == null) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		try {
			commentService.updateComment(comment);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return  ResultUtil.error(400, "修改失败，错误原因："+e.getMessage());
		}
		
	}
	
	
	
	
	
	/**
	* @Title: commentList
	* @Description: TODO(根据成长档案的ID获取所有评论)
	* @param @param request
	* @param @param response
	* @param @param pageIndex
	* @param @param pageSize
	* @param @param query
	* @param @param docId
	* @param @return    参数
	* @return ListResult    返回类型
	* @throws
	*/
	@RequestMapping(value="/commentList",method= {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ListResult commentList(HttpServletRequest request,HttpServletResponse response,int pageIndex,int pageSize,String query,Integer docId){
		HashMap<String,Object> valMap = new HashMap<String,Object>();
		valMap.put("pageIndex", pageIndex);
		valMap.put("pageSize", pageSize);
		valMap.put("query", query);
		valMap.put("docId", docId);
		return commentService.commentList(valMap);
	}
	
	
	
	
	
	
	
	
	
}
