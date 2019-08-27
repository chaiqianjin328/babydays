package com.babydays.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.babydays.model.BAbilitiesCata;
import com.babydays.model.BDocument;
import com.babydays.model.BGarden;
import com.babydays.model.BStudent;
import com.babydays.model.ListResult;
import com.babydays.service.AbilitiesCataService;
import com.babydays.service.DocumentService;
import com.babydays.service.GardenService;
import com.babydays.service.StudentService;
import com.babydays.util.CommonUrlUtil;
import com.babydays.util.HtmlToPdf;
import com.babydays.util.MyCalendar;
import com.babydays.util.ResultUtil;
import com.babydays.util.TestUrl;

@Controller
@RequestMapping("/web/growth")
public class WebGrowthController {

	
	@Autowired
	private GardenService gardenService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private AbilitiesCataService abilitiesCataService;
	
	@Autowired
	private DocumentService documentService;

	private ListResult listResult;
	
	
	@RequestMapping(value="/advice_headmaster")
	public String toAdvice_headmaster(HttpServletRequest request) {
		List<BGarden> list = gardenService.getAllGarden();
		request.setAttribute("list", list);
		request.setAttribute("growthBtn", "growthBtn");
		return "web/4-advice-headmaster";
	}
	
	
	@RequestMapping(value="/advice_director")
	public String toAdvice_director(HttpServletRequest request) {
		request.setAttribute("growthBtn", "growthBtn");
		return "web/4-advice-director";
	}
	
	@RequestMapping(value="/advice_detail")
	public String toAdvice_detail(String detailBtn,Integer stuId,HttpServletRequest request,Integer parentId) throws Exception {
		BStudent student = null;
		Integer monthSpace = 0;
		if (stuId != null && stuId >0) {
			 student = studentService.selectStudentById(stuId);
			 if (student != null) {
				if (student.getBirth() != null) {
					Date birth = student.getBirth();
					Date date = new Date();
					monthSpace = MyCalendar.getMonthDiff(date, birth);
				}
			}
			 //获取小圆点
			 try {
					HashMap<String,Object> valMap = new HashMap<String,Object>();
					valMap.put("pageIndex", 0);
					valMap.put("pageSize", 10000000);
					valMap.put("query", "");
					valMap.put("stuId", stuId);
					valMap.put("abcataId", 0);
					listResult = documentService.allDocumentList(valMap);
					List<BDocument> list = listResult.getList();
					HashMap<Integer,Integer> pointMap = new HashMap<Integer,Integer>();
					for (BDocument bDocument : list) {
						if (pointMap.containsKey(bDocument.getAbcataId())) {
							int nowSize = pointMap.get(bDocument.getAbcataId())+1;
							pointMap.put(bDocument.getAbcataId(), nowSize);
						}else {
							pointMap.put(bDocument.getAbcataId(), 1);
						}
					}
					request.setAttribute("pointMap", JSONObject.parse(JSON.toJSONString(pointMap)));
					System.err.println(JSONObject.parse(JSON.toJSONString(pointMap)));
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			
		}
		if (parentId != null && parentId >0) {
			List<BAbilitiesCata> abilitiesCata = abilitiesCataService.getAbilitiesCata(parentId);
			request.setAttribute("abilitiesCata", abilitiesCata);
		}
		
		request.setAttribute("monthSpace", monthSpace+1);
		request.setAttribute("student", student);
		request.setAttribute("detailBtn", detailBtn);
		request.setAttribute("growthBtn", "growthBtn");
		return "web/4-advice-detail";
		
	}
	
	
	
	
	@RequestMapping(value="/abilitiesCata/getAbilitiesCata",method= {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Object getAbilitiesCata(BAbilitiesCata abCata) {
		Integer parentId = abCata.getParentId();
		List<BAbilitiesCata> abilitiesCata;
		try {
			abilitiesCata = abilitiesCataService.getAbilitiesCata(parentId);
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("abilitiesCata", abilitiesCata);
			return ResultUtil.success(map);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "获取数据失败，失败原因："+e.getMessage());
		}
		
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
	public Object addImage(BDocument document,HttpServletRequest request) {
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
		Integer abcataId = document.getAbcataId();
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
		if (imgs == null || imgs == "") {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (abcataId == null) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		
		//文档类型 2是图片文档
		document.setType(2);
		try {
			documentService.addDocument(document);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "添加失败，错误原因："+e.getMessage());
		}
		
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
	public Object addVideo(BDocument document,HttpServletRequest request) {
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
		Integer abcataId = document.getAbcataId();
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
		if (videos == null || videos == "") {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (abcataId == null) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		
		//文档类型 3是视频文档
		document.setType(3);
		try {
			documentService.addDocument(document);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "添加失败，错误原因："+e.getMessage());
		}
		
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
		Integer abcataId = document.getAbcataId();
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
		if (abcataId == null) {
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
	* @Title: allDocumentList
	* @Description: TODO(根据学生id获取所有类型的成长记录)
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
	@RequestMapping(value="/allDocumentList",method=RequestMethod.GET)
	@ResponseBody
	public ListResult allDocumentList(HttpServletRequest request,HttpServletResponse response,int pageIndex,int pageSize,String query,Integer stuId,Integer abcataId){
		HashMap<String,Object> valMap = new HashMap<String,Object>();
		valMap.put("pageIndex", pageIndex);
		valMap.put("pageSize", pageSize);
		valMap.put("query", query);
		valMap.put("stuId", stuId);
		valMap.put("abcataId", abcataId);
		return documentService.allDocumentList(valMap);
	}
	
	
	
	
	/**
	* @Title: convertAdvicePdf
	* @Description: TODO(将参数所示链接的网页转换成PDF文件)
	* @param @param htmlUrl
	* @param @param request
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/convertAdvicePdf",method=RequestMethod.POST)
	@ResponseBody
	public Object convertAdvicePdf(String htmlUrl,HttpServletRequest request) {
		if (htmlUrl != null && htmlUrl != "") {
			Boolean testUrlWithTimeOut = TestUrl.testUrlWithTimeOut(htmlUrl, 2000);
			if (testUrlWithTimeOut) {
				Date date = new Date();
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
				String code1 = format.format(date);
				Random ran = new Random();
				int int1 = ran.nextInt(10);
				int int2 = ran.nextInt(10);
				String code2 = String.valueOf(int1)+String.valueOf(int2);
				String code = code1+code2;
				//服务器路径
				String uploadPdfPath = CommonUrlUtil.UPLOAD_PDF_PATH;
				String destPath = uploadPdfPath+code+".pdf";
				
				HtmlToPdf.convert(htmlUrl, destPath);
				HashMap<String,Object> map = new HashMap<String,Object>();
				String prefixPdfUrl = CommonUrlUtil.PREFIX_PDF_URL;
				
				String path = request.getContextPath(); 
				String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
				String babydaysPdfUrl = basePath+prefixPdfUrl+code+".pdf";
				map.put("babydaysPdfUrl", babydaysPdfUrl);
				return ResultUtil.success(map);
			} else {
				return ResultUtil.error(400, "转换失败，路径不可访问");
			}
		} else {
			return ResultUtil.error(400, "转换失败,ID错误");
		}
		
	}
	
	
	/**
	* @Title: convertAdviceHtml
	* @Description: TODO(将连接网页静态化处理)
	* @param @param htmlUrl
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/convertHTML")
	@ResponseBody
	public Object convertAdviceHtml(String htmlUrl) {
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value="/route_headmaster")
	public String toRoute_headmaster() {
		return "web/4-route-headmaster";
	}
	
	
	@RequestMapping(value="/route_director")
	public String toRoute_director() {
		return "web/4-route-director";
	}
	
	@RequestMapping(value="/route_detail")
	public String toRoute_detail() {
		return "web/4-route-detail";
	}
	
	
	@RequestMapping(value="/statistical")
	public String toStatistical() {
		return "web/4-statistical";
	}
	
	@RequestMapping(value="/statistical_detail_ji")
	public String toStatistical_detail_ji() {
		return "web/4-statistical-dtail-ji";
	}
	
	
	@RequestMapping(value="/statistical_detail_yue")
	public String toStatistical_detail_yue() {
		return "web/4-statistical-dtail-yue";
	}
	
	
	
}
