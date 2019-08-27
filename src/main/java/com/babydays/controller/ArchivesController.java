package com.babydays.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

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
import com.babydays.model.BHealth;
import com.babydays.model.BTooth;
import com.babydays.model.QueryModel;
import com.babydays.service.CommentService;
import com.babydays.service.DocumentService;
import com.babydays.service.HealthService;
import com.babydays.service.ToothService;
import com.babydays.util.CommonUrlUtil;
import com.babydays.util.PicUpload;
import com.babydays.util.ResultUtil;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/archives")
public class ArchivesController {

	
	@Autowired
	private DocumentService documentService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private HealthService healthService;
	
	@Autowired
	private ToothService toothService;
	
	
	
	
	/**
	* @Title: addDocument
	* @Description: TODO(添加成长记录)
	* @param @param document
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/document/addDocument",method=RequestMethod.POST)
	@ResponseBody
	public Object addDocument(@RequestBody BDocument document) {
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
	@RequestMapping(value="/document/deleteDocumentById",method=RequestMethod.POST)
	@ResponseBody
	public Object deleteDocumentById(@RequestBody BDocument document) {
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
	@RequestMapping(value="/document/updateDocument",method=RequestMethod.POST)
	@ResponseBody
	public Object updateDocument(@RequestBody BDocument document) {
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
	* @Title: getDocuments
	* @Description: TODO(根据学生id获取所有的成长文档)
	* @param @param queryModel
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/document/getDocuments",method= RequestMethod.POST)
	@ResponseBody
	public Object getDocuments(@RequestBody QueryModel queryModel) {
		String query = queryModel.getQuery();
		Integer pageNum = queryModel.getPageNum();
		Integer pageSize = queryModel.getPageSize();
		Integer stuId = queryModel.getStuId();
		String createtime = queryModel.getCreatetime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (pageNum <= 0 || pageSize <= 0) {
			return ResultUtil.error(400, "请求参数错误，提示：需要pageNum>0 pageSize>0");
		}
		if (createtime != null && createtime != "") {
			try {
				format.parse(createtime);
			} catch (ParseException e1) {
				e1.printStackTrace();
				return ResultUtil.error(400, "请求参数错误，提示：日期格式错误");
			}
		}
		
		PageInfo<BDocument> documents;
		try {
			documents = documentService.getDocuments(query,pageNum,pageSize,stuId,createtime);
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("documents", documents);
			return ResultUtil.success(map);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "获取数据失败，失败原因："+e.getMessage());
		}
	}
	
	
	
	
	/**
	* @Title: addComment
	* @Description: TODO(添加评论)
	* @param @param comment
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/comment/addComment",method=RequestMethod.POST)
	@ResponseBody
	public Object addComment(@RequestBody BComment comment) {
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
	@RequestMapping(value="/comment/deleteCommentById",method=RequestMethod.POST)
	@ResponseBody
	public Object deleteCommentById(@RequestBody BComment comment) {
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
	@RequestMapping(value="/comment/updateComment",method=RequestMethod.POST)
	@ResponseBody
	public Object updateComment(@RequestBody BComment comment) {
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
	* @Title: getComments
	* @Description: TODO(根据成长档案的id获取所有的评论)
	* @param @param queryModel
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/comment/getComments",method= RequestMethod.POST)
	@ResponseBody
	public Object getComments(@RequestBody QueryModel queryModel) {
		String query = queryModel.getQuery();
		Integer pageNum = queryModel.getPageNum();
		Integer pageSize = queryModel.getPageSize();
		Integer docId = queryModel.getDocId();
		if (pageNum <= 0 || pageSize <= 0) {
			return ResultUtil.error(400, "请求参数错误，提示：需要pageNum>0 pageSize>0");
		}
		PageInfo<BComment> comments;
		try {
			comments = commentService.getComments(query,pageNum,pageSize,docId);
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("comments", comments);
			return ResultUtil.success(map);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "获取数据失败，失败原因："+e.getMessage());
		}
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
	@RequestMapping(value="/health/addHealth",method=RequestMethod.POST)
	@ResponseBody
	public Object addHealth(@RequestBody BHealth health) {
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
	@RequestMapping(value="/health/deleteHealthById",method=RequestMethod.POST)
	@ResponseBody
	public Object deleteHealthById(@RequestBody BHealth health) {
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
	* @Title: getHealths
	* @Description: TODO(根据学生id获取体检信息)
	* @param @param queryModel
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/health/getHealths",method= RequestMethod.POST)
	@ResponseBody
	public Object getHealths(@RequestBody QueryModel queryModel) {
		String query = queryModel.getQuery();
		Integer pageNum = queryModel.getPageNum();
		Integer pageSize = queryModel.getPageSize();
		Integer stuId = queryModel.getStuId();
		if (pageNum <= 0 || pageSize <= 0) {
			return ResultUtil.error(400, "请求参数错误，提示：需要pageNum>0 pageSize>0");
		}
		PageInfo<BHealth> healths;
		try {
			healths = healthService.getHealths(query,pageNum,pageSize,stuId);
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("healths", healths);
			return ResultUtil.success(map);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "获取数据失败，失败原因："+e.getMessage());
		}
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
	@RequestMapping(value="/tooth/addTooth",method=RequestMethod.POST)
	@ResponseBody
	public Object addTooth(@RequestBody BTooth tooth) {
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
		}else {
			if (fluorine == 1) {
				if (toothImg == null || toothImg == "") {
					return ResultUtil.error(400, "添加失败，缺少必要参数");
				}
			}
		}
		if (checktime == null) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		try {
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
	@RequestMapping(value="/tooth/deleteToothById",method=RequestMethod.POST)
	@ResponseBody
	public Object deleteToothById(@RequestBody BTooth tooth) {
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
	* @Title: getTooths
	* @Description: TODO(根据学生id获取牙齿涂氟信息)
	* @param @param queryModel
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/tooth/getTooths",method= RequestMethod.POST)
	@ResponseBody
	public Object getTooths(@RequestBody QueryModel queryModel) {
		String query = queryModel.getQuery();
		Integer pageNum = queryModel.getPageNum();
		Integer pageSize = queryModel.getPageSize();
		Integer stuId = queryModel.getStuId();
		if (pageNum <= 0 || pageSize <= 0) {
			return ResultUtil.error(400, "请求参数错误，提示：需要pageNum>0 pageSize>0");
		}
		PageInfo<BTooth> tooths;
		try {
			tooths = toothService.getTooths(query,pageNum,pageSize,stuId);
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("tooths", tooths);
			return ResultUtil.success(map);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "获取数据失败，失败原因："+e.getMessage());
		}
	}
	
	
	
	
	
	
	
}
