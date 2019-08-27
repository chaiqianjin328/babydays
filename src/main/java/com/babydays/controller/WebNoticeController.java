package com.babydays.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.babydays.model.BCalendar;
import com.babydays.model.BGarden;
import com.babydays.model.BNotice;
import com.babydays.model.BRecipes;
import com.babydays.model.ListResult;
import com.babydays.service.CalendarService;
import com.babydays.service.GardenService;
import com.babydays.service.NoticeService;
import com.babydays.service.RecipesService;
import com.babydays.util.CommonUrlUtil;
import com.babydays.util.PicUpload;
import com.babydays.util.ResultUtil;

/**
* @ClassName: WebNoticeController
* @Description: TODO(公告栏)
* @author chaiqianjin
* @date 2018年10月9日
*
*/
@Controller
@RequestMapping("/web/notice")
public class WebNoticeController {

	@Autowired
	private CalendarService calendarService;
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private RecipesService recipesService;
	
	@Autowired
	private GardenService gardenService;
	
	
	/**
	* @Title: toCalendar
	* @Description: TODO(跳转到校历管理)
	* @param @return    参数
	* @return String    返回类型
	* @throws
	*/
	@RequestMapping(value="/calendar",method=RequestMethod.GET)
	public String toCalendar(HttpServletRequest request) {
		List<BGarden> list = gardenService.getAllGarden();
		request.setAttribute("list", list);
		request.setAttribute("noticeBtn","noticeBtn");
		request.setAttribute("calendarBtn","calendarBtn");
		return "web/2-mgt-calendar";
	}
	
	
	
	/**
	* @Title: addCalender
	* @Description: TODO(添加校历)
	* @param @param request
	* @param @param calendar
	* @param @param calendarPic
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/addCalendar",method={ RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Object addCalendar(HttpServletRequest request,BCalendar calendar,@RequestParam(value = "calendarPic", required = false) MultipartFile calendarPic) {
		try {
			String timeStr = request.getParameter("createTime");
			if (timeStr==null||timeStr=="") {
				return ResultUtil.error(400, "添加校历失败");
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			Date date = format.parse(timeStr);
			calendar.setCreatetime(date);
			
			//保存地址
			String position = CommonUrlUtil.UPLOAD_PICTURE_PATH;
			//图片访问路径前缀
			String picPath = CommonUrlUtil.PREFIX_PICTURE_URL;
			if (calendarPic.getSize()>0) {
				String picUrl = PicUpload.picUploadAndReturnURL(calendarPic, request, position);
				System.out.println(picUrl);
				calendar.setImage(picPath+picUrl);
			}
			
			calendarService.addCalendar(calendar);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "添加失败，错误原因："+e.getMessage());
		}
	}
	
	
	/**
	* @Title: deleteCalendarById
	* @Description: TODO(删除校历)
	* @param @param id
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/deleteCalendarById",method=RequestMethod.GET)
	@ResponseBody
	public Object deleteCalendarById(Integer id) {
		try {
			calendarService.deleteCalendarById(id);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "删除失败，失败原因："+e.getMessage());
		}
	}
	
	
	
	
	/**
	* @Title: updateCalender
	* @Description: TODO(修改校历)
	* @param @param request
	* @param @param calendar
	* @param @param calendarPic
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/updateCalendar",method={ RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Object updateCalendar(HttpServletRequest request,BCalendar calendar,@RequestParam(value = "calendarPic", required = false) MultipartFile calendarPic) {
		try {
			String timeStr = request.getParameter("createTime");
			if (timeStr==null||timeStr=="") {
				return ResultUtil.error(400, "修改校历失败");
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			Date date = format.parse(timeStr);
			calendar.setCreatetime(date);
			
			//保存地址
			String position = CommonUrlUtil.UPLOAD_PICTURE_PATH;
			//图片访问路径前缀
			String picPath = CommonUrlUtil.PREFIX_PICTURE_URL;
			if (calendarPic.getSize()>0) {
				String picUrl = PicUpload.picUploadAndReturnURL(calendarPic, request, position);
				System.out.println(picUrl);
				calendar.setImage(picPath+picUrl);
			}
			
			calendarService.updateCalendar(calendar);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "修改失败，错误原因："+e.getMessage());
		}
	}
	
	
	
	
	/**
	* @Title: calendarList
	* @Description: TODO(获取所有的校历)
	* @param @param request
	* @param @param response
	* @param @param pageIndex
	* @param @param pageSize
	* @param @param query
	* @param @param gardenId
	* @param @return    参数
	* @return ListResult    返回类型
	* @throws
	*/
	@RequestMapping(value="/calendarList",method=RequestMethod.GET)
	@ResponseBody
	public ListResult calendarList(HttpServletRequest request,HttpServletResponse response,int pageIndex,int pageSize,String query,Integer gardenId){
		HashMap<String,Object> valMap = new HashMap<String,Object>();
		valMap.put("pageIndex", pageIndex);
		valMap.put("pageSize", pageSize);
		valMap.put("query", query);
		valMap.put("gardenId", gardenId);
		return calendarService.calendarList(valMap);
	}
	
	
	
	
	/**
	* @Title: toInform
	* @Description: TODO(跳转到通知管理)
	* @param @return    参数
	* @return String    返回类型
	* @throws
	*/
	@RequestMapping(value="/inform",method=RequestMethod.GET)
	public String toInform(HttpServletRequest request) {
		List<BGarden> list = gardenService.getAllGarden();
		request.setAttribute("list", list);
		request.setAttribute("noticeBtn","noticeBtn");
		request.setAttribute("informBtn","informBtn");
		return "web/2-mgt-inform";
	}
	
	
	/**
	* @Title: addNotice
	* @Description: TODO(添加通知)
	* @param @param request
	* @param @param notice
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/addNotice",method= {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Object addNotice(HttpServletRequest request,BNotice notice) {
		try {
			String timeStr = request.getParameter("createTime");
			if (timeStr==null||timeStr=="") {
				return ResultUtil.error(400, "添加通知失败");
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			Date date = format.parse(timeStr);
			notice.setCreatetime(date);
			noticeService.addNotice(notice);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "添加失败，错误原因："+e.getMessage());
		}
	}
	
	
	/**
	* @Title: deleteNoticeById
	* @Description: TODO(删除通知)
	* @param @param id
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/deleteNoticeById",method=RequestMethod.GET)
	@ResponseBody
	public Object deleteNoticeById(Integer id) {
		try {
			noticeService.deleteNoticeById(id);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "删除失败，错误原因："+e.getMessage());
		}
	}
	
	
	
	
	/**
	* @Title: updateNotice
	* @Description: TODO(修改通知)
	* @param @param request
	* @param @param notice
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/updateNotice",method= {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Object updateNotice(HttpServletRequest request,BNotice notice) {
		try {
			String timeStr = request.getParameter("createTime");
			if (timeStr==null||timeStr=="") {
				return ResultUtil.error(400, "修改通知失败");
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			Date date = format.parse(timeStr);
			notice.setCreatetime(date);
			noticeService.updateNotice(notice);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "修改失败，错误原因："+e.getMessage());
		}
	}
	
	
	
	
	/**
	* @Title: noticeList
	* @Description: TODO(获取所有的通知)
	* @param @param request
	* @param @param response
	* @param @param pageIndex
	* @param @param pageSize
	* @param @param query
	* @param @param gardenId
	* @param @return    参数
	* @return ListResult    返回类型
	* @throws
	*/
	@RequestMapping(value="/noticeList",method=RequestMethod.GET)
	@ResponseBody
	public ListResult noticeList(HttpServletRequest request,HttpServletResponse response,int pageIndex,int pageSize,String query,Integer gardenId){
		HashMap<String,Object> valMap = new HashMap<String,Object>();
		valMap.put("pageIndex", pageIndex);
		valMap.put("pageSize", pageSize);
		valMap.put("query", query);
		valMap.put("gardenId", gardenId);
		return noticeService.noticeList(valMap);
	}
	
	
	
	
	/**
	* @Title: toRecipe
	* @Description: TODO(跳转到食谱管理)
	* @param @return    参数
	* @return String    返回类型
	* @throws
	*/
	@RequestMapping(value="/recipe",method=RequestMethod.GET)
	public String toRecipe(HttpServletRequest request) {
		List<BGarden> list = gardenService.getAllGarden();
		request.setAttribute("list", list);
		request.setAttribute("noticeBtn","noticeBtn");
		request.setAttribute("recipeBtn","recipeBtn");
		return "web/2-mgt-recipe";
	}
	
	
	
	
	/**
	* @Title: addRecipes
	* @Description: TODO(添加食谱)
	* @param @param request
	* @param @param recipes
	* @param @param recipesPic
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/addRecipes",method= {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Object addRecipes(HttpServletRequest request,BRecipes recipes,@RequestParam(value = "recipesPic", required = false) MultipartFile recipesPic) {
		try {
			String timeStr = request.getParameter("createTime");
			if (timeStr==null||timeStr=="") {
				return ResultUtil.error(400, "添加食谱失败");
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			Date date = format.parse(timeStr);
			recipes.setCreatetime(date);
			
			
			//服务器保存地址
			String position = CommonUrlUtil.UPLOAD_PICTURE_PATH;
			//图片访问路径前缀
			String picPath = CommonUrlUtil.PREFIX_PICTURE_URL;
			if (recipesPic.getSize()>0) {
				String picUrl = PicUpload.picUploadAndReturnURL(recipesPic, request, position);
				System.out.println(picUrl);
				recipes.setImage(picPath+picUrl);
			}
			
			
			recipesService.addRecipes(recipes);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "添加失败，错误原因："+e.getMessage());
		}
	}
	
	
	/**
	* @Title: deleteRecipesById
	* @Description: TODO(删除食谱)
	* @param @param id
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/deleteRecipesById",method=RequestMethod.GET)
	@ResponseBody
	public Object deleteRecipesById(Integer id) {
		try {
			recipesService.deleteRecipesById(id);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "删除失败，失败原因："+e.getMessage());
		}
	}
	
	
	
	
	/**
	* @Title: updateRecipes
	* @Description: TODO(修改食谱)
	* @param @param request
	* @param @param recipes
	* @param @param recipesPic
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/updateRecipes",method= {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Object updateRecipes(HttpServletRequest request,BRecipes recipes,@RequestParam(value = "recipesPic", required = false) MultipartFile recipesPic) {
		try {
			String timeStr = request.getParameter("createTime");
			if (timeStr==null||timeStr=="") {
				return ResultUtil.error(400, "修改食谱失败");
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			Date date = format.parse(timeStr);
			recipes.setCreatetime(date);
			
			
			//保存地址
			String position = CommonUrlUtil.UPLOAD_PICTURE_PATH;
			//图片访问路径前缀
			String picPath = CommonUrlUtil.PREFIX_PICTURE_URL;
			if (recipesPic.getSize()>0) {
				String picUrl = PicUpload.picUploadAndReturnURL(recipesPic, request, position);
				System.out.println(picUrl);
				recipes.setImage(picPath+picUrl);
			}
			
			
			recipesService.updateRecipes(recipes);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "修改失败，错误原因："+e.getMessage());
		}
	}
	
	
	
	/**
	* @Title: recipesList
	* @Description: TODO(获取所有的食谱)
	* @param @param request
	* @param @param response
	* @param @param pageIndex
	* @param @param pageSize
	* @param @param query
	* @param @param gardenId
	* @param @param classId
	* @param @return    参数
	* @return ListResult    返回类型
	* @throws
	*/
	@RequestMapping(value="/recipesList",method=RequestMethod.GET)
	@ResponseBody
	public ListResult recipesList(HttpServletRequest request,HttpServletResponse response,int pageIndex,int pageSize,String query,Integer gardenId,Integer classId){
		HashMap<String,Object> valMap = new HashMap<String,Object>();
		valMap.put("pageIndex", pageIndex);
		valMap.put("pageSize", pageSize);
		valMap.put("query", query);
		valMap.put("gardenId", gardenId);
		valMap.put("classId", classId);
		return recipesService.recipesList(valMap);
	}
	
	
	
	
	
	
}
