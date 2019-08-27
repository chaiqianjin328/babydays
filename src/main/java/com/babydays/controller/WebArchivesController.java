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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.babydays.model.BComment;
import com.babydays.model.BDocument;
import com.babydays.model.BGarden;
import com.babydays.model.BHealth;
import com.babydays.model.BTooth;
import com.babydays.model.ListResult;
import com.babydays.service.CommentService;
import com.babydays.service.DocumentService;
import com.babydays.service.GardenService;
import com.babydays.service.HealthService;
import com.babydays.service.ToothService;
import com.babydays.util.CommonUrlUtil;
import com.babydays.util.PicUpload;
import com.babydays.util.ResultUtil;

/**
* @ClassName: WebArchivesController
* @Description: TODO(成长档案)
* @author chaiqianjin
* @date 2018年10月9日
*
*/
@Controller
@RequestMapping("/web/archives")
public class WebArchivesController {

	@Autowired
	private GardenService gardenService;
	
	@Autowired
	private DocumentService documentService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private HealthService healthService;
	
	@Autowired
	private ToothService toothService;
	
	
	
	@RequestMapping(value="/archives_headmaster",method=RequestMethod.GET)
	public String toArchives_headmaster(HttpServletRequest request) {
		List<BGarden> list = gardenService.getAllGarden();
		request.setAttribute("list", list);
		request.setAttribute("archivesBtn","archivesBtn");
		request.setAttribute("diaryBtn", "diaryBtn");
		return "web/3-archives-headmaster";
	}
	
	@RequestMapping(value="/archives_director",method=RequestMethod.GET)
	public String toArchives_director(HttpServletRequest request) {
		request.setAttribute("archivesBtn","archivesBtn");
		request.setAttribute("diaryBtn", "diaryBtn");
		return "web/3-archives-director";
	}
	
	
	@RequestMapping(value="/archives_school",method=RequestMethod.GET)
	public String toArchives_school(HttpServletRequest request) {
		Integer stuId = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		request.setAttribute("stuId", stuId);
		request.setAttribute("stuName", name);
		request.setAttribute("archivesBtn","archivesBtn");
		request.setAttribute("diaryBtn", "diaryBtn");
		return "web/3-archives-school";
	}

	@RequestMapping(value="/archives_parents",method=RequestMethod.GET)
	public String toArchives_parents(HttpServletRequest request) {
		request.setAttribute("archivesBtn","archivesBtn");
		request.setAttribute("diaryBtn", "diaryBtn");
		return "web/3-archives-parents";
	}
	
	
	@RequestMapping(value="/archives_detail",method=RequestMethod.GET)
	public String toArchives_detail(HttpServletRequest request) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		BDocument document = documentService.selectDocumentById(id);
		request.setAttribute("doc", document);
		request.setAttribute("archivesBtn","archivesBtn");
		request.setAttribute("diaryBtn", "diaryBtn");
		return "web/3-archives-detail";
	}
	
	@RequestMapping(value="/component",method=RequestMethod.GET)
	public String toComponent(HttpServletRequest request) {
		request.setAttribute("archivesBtn","archivesBtn");
		request.setAttribute("diaryBtn", "diaryBtn");
		return "web/component";
	}
	
	
	@RequestMapping(value="/archives_headmaster_health",method=RequestMethod.GET)
	public String toArchives_headmaster_health(HttpServletRequest request) {
		List<BGarden> list = gardenService.getAllGarden();
		request.setAttribute("list", list);
		request.setAttribute("archivesBtn","archivesBtn");
		request.setAttribute("healthBtn","healthBtn");
		return "web/3-archives-headmaster-health";
	}
	
	
	@RequestMapping(value="/archives_health",method=RequestMethod.GET)
	public String toArchives_health(HttpServletRequest request) {
		Integer stuId = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		Integer sex = Integer.parseInt(request.getParameter("sex"));
		request.setAttribute("stuId", stuId);
		request.setAttribute("stuName", name);
		request.setAttribute("sex", sex);
		request.setAttribute("archivesBtn","archivesBtn");
		request.setAttribute("healthBtn","healthBtn");
		return "web/3-archives-health";
	}
	
	@RequestMapping(value="/archives_headmaster_tooth",method=RequestMethod.GET)
	public String toArchives_headmaster_tooth(HttpServletRequest request) {
		List<BGarden> list = gardenService.getAllGarden();
		request.setAttribute("list", list);
		request.setAttribute("archivesBtn","archivesBtn");
		request.setAttribute("toothBtn","toothBtn");
		return "web/3-archives-headmaster-tooth";
	}
	
	@RequestMapping(value="/archives_tooth",method=RequestMethod.GET)
	public String toArchives_tooth(HttpServletRequest request) {
		Integer stuId = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		request.setAttribute("stuId", stuId);
		request.setAttribute("stuName", name);
		request.setAttribute("archivesBtn","archivesBtn");
		request.setAttribute("toothBtn","toothBtn");
		return "web/3-archives-tooth";
	}
	
	
	
	/**
	* @Title: addDocument
	* @Description: TODO(添加成长记录)
	* @param @param document
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/addDocument",method=RequestMethod.POST)
	@ResponseBody
	public Object addDocument(BDocument document,HttpServletRequest request) {
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
		if (stuId == null) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (createtime == null) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		
		//文档类型 1是文字文档
		document.setType(1);
		try {
			documentService.addDocument(document);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "添加失败，错误原因："+e.getMessage());
		}
		
	}
	
	
	
	
	/**
	* @Title: deleteDocumentById
	* @Description: TODO(根据成长记录id删除对应成长记录)
	* @param @param document
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/deleteDocumentById",method=RequestMethod.POST)
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
	* @Title: updateDocument
	* @Description: TODO(修改成长记录)
	* @param @param document
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/updateDocument",method=RequestMethod.POST)
	@ResponseBody
	public Object updateDocument(BDocument document,HttpServletRequest request) {
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
		
		//文档类型 1是文字文档
		document.setType(1);
		try {
			documentService.updateDocument(document);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "修改失败，错误原因："+e.getMessage());
		}
		
	}
	
	
	
	
	/**
	* @Title: documentList
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
	@RequestMapping(value="/documentList",method=RequestMethod.GET)
	@ResponseBody
	public ListResult documentList(HttpServletRequest request,HttpServletResponse response,int pageIndex,int pageSize,String query,Integer stuId){
		HashMap<String,Object> valMap = new HashMap<String,Object>();
		valMap.put("pageIndex", pageIndex);
		valMap.put("pageSize", pageSize);
		valMap.put("query", query);
		valMap.put("stuId", stuId);
		valMap.put("type", 1);
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
	@RequestMapping(value="/commentList",method=RequestMethod.GET)
	@ResponseBody
	public ListResult commentList(HttpServletRequest request,HttpServletResponse response,int pageIndex,int pageSize,String query,Integer docId){
		HashMap<String,Object> valMap = new HashMap<String,Object>();
		valMap.put("pageIndex", pageIndex);
		valMap.put("pageSize", pageSize);
		valMap.put("query", query);
		valMap.put("docId", docId);
		return commentService.commentList(valMap);
	}
	
	
	
	
	/**
	* @Title: addHealth
	* @Description: TODO(添加体检信息)
	* @param @param health
	* @param @param request
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/addHealth",method=RequestMethod.POST)
	@ResponseBody
	public Object addHealth(BHealth health,HttpServletRequest request) {
		String timeStr = request.getParameter("checkTime");
		if (timeStr==null||timeStr=="") {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		try {
			Date date = format.parse(timeStr);
			health.setChecktime(date);
		} catch (ParseException e1) {
			e1.printStackTrace();
			return ResultUtil.error(400, "添加失败，错误原因："+e1.getMessage());
		}
		Integer uid = health.getUid();
		Integer stuId = health.getStuId();
		Integer realAge = health.getRealAge();
		Double weight = health.getWeight();
		Double height = health.getHeight();
		Double visionLeft = health.getVisionLeft();
		Double visionRight = health.getVisionRight();
		Date checktime = health.getChecktime();
		if (uid == null || uid <= 0) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (stuId == null || stuId <= 0) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (realAge==null || realAge <= 0) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (weight==null || weight <= 0) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (height==null || height <= 0) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (visionLeft==null || visionLeft <= 0) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (visionRight==null || visionRight <= 0) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (checktime==null) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		try {
			healthService.addHealth(health);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "添加失败，错误原因："+e.getMessage());
		}
	}
	
	
	
	/**
	* @Title: deleteHealthById
	* @Description: TODO(根据id删除体检信息)
	* @param @param health
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/deleteHealthById",method=RequestMethod.POST)
	@ResponseBody
	public Object deleteHealthById(BHealth health) {
		Integer id = health.getId();
		if (id == null) {
			return ResultUtil.error(400, "删除失败，缺少必要参数");
		}
		try {
			healthService.deleteHealthById(id);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "删除失败，错误原因："+e.getMessage());
		}
	
	}
	
	
	
	/**
	* @Title: healthList
	* @Description: TODO(根据学生id获取体检信息)
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
	@RequestMapping(value="/healthList",method=RequestMethod.GET)
	@ResponseBody
	public ListResult healthList(HttpServletRequest request,HttpServletResponse response,int pageIndex,int pageSize,String query,Integer stuId){
		HashMap<String,Object> valMap = new HashMap<String,Object>();
		valMap.put("pageIndex", pageIndex);
		valMap.put("pageSize", pageSize);
		valMap.put("query", query);
		valMap.put("stuId", stuId);
		return healthService.healthList(valMap);
	}
	
	
	
	
	
	/**
	* @Title: addTooth
	* @Description: TODO(添加牙齿涂氟信息)
	* @param @param request
	* @param @param tooth
	* @param @param toothPic
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/addTooth",method=RequestMethod.POST)
	@ResponseBody
	public Object addTooth(HttpServletRequest request,BTooth tooth,@RequestParam(value = "toothPic", required = false) MultipartFile toothPic) {
		String timeStr = request.getParameter("checkTime");
		if (timeStr==null||timeStr=="") {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		try {
			Date date = format.parse(timeStr);
			tooth.setChecktime(date);
		} catch (ParseException e1) {
			e1.printStackTrace();
			return ResultUtil.error(400, "添加失败，错误原因："+e1.getMessage());
		}
		Integer stuId = tooth.getStuId();
		Integer uid = tooth.getUid();
		Integer fluorine = tooth.getFluorine();
		String toothImg = tooth.getToothImg();
		Date checktime = tooth.getChecktime();
		if (stuId == null || stuId <= 0) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (uid == null || uid <= 0) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (fluorine < 0 || fluorine > 1) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (checktime == null) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		try {
			//保存地址
			String position = CommonUrlUtil.UPLOAD_PICTURE_PATH;
			//图片访问路径前缀
			String picPath = CommonUrlUtil.PREFIX_PICTURE_URL;
			if (toothPic.getSize()>0) {
				String picUrl = PicUpload.picUploadAndReturnURL(toothPic, request, position);
				System.out.println(picUrl);
				tooth.setToothImg(picPath+picUrl);
			}
			toothService.addTooth(tooth);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "添加失败，错误原因："+e.getMessage());
		}
	}
	
	
	/**
	* @Title: deleteToothById
	* @Description: TODO(根据id删除牙齿涂氟记录)
	* @param @param tooth
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/deleteToothById",method=RequestMethod.POST)
	@ResponseBody
	public Object deleteToothById(BTooth tooth) {
		Integer id = tooth.getId();
		if (id == null) {
			return ResultUtil.error(400, "删除失败，缺少必要参数");
		}
		try {
			toothService.deleteToothById(id);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "删除失败，错误原因："+e.getMessage());
		}
	
	}
	
	
	
	
	/**
	* @Title: toothList
	* @Description: TODO(根据学生id获取牙齿涂氟信息)
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
	@RequestMapping(value="/toothList",method=RequestMethod.GET)
	@ResponseBody
	public ListResult toothList(HttpServletRequest request,HttpServletResponse response,int pageIndex,int pageSize,String query,Integer stuId){
		HashMap<String,Object> valMap = new HashMap<String,Object>();
		valMap.put("pageIndex", pageIndex);
		valMap.put("pageSize", pageSize);
		valMap.put("query", query);
		valMap.put("stuId", stuId);
		return toothService.toothList(valMap);
	}
	
	
	
	
	
	
}
