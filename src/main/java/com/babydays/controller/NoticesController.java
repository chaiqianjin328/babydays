package com.babydays.controller;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.babydays.model.BCalendar;
import com.babydays.model.BNotice;
import com.babydays.model.BRecipes;
import com.babydays.model.QueryModel;
import com.babydays.service.CalendarService;
import com.babydays.service.NoticeService;
import com.babydays.service.RecipesService;
import com.babydays.util.ResultUtil;
import com.github.pagehelper.PageInfo;
@Controller
@RequestMapping("/notices")
public class NoticesController {
	
	
	
	@Autowired
	private CalendarService calendarService;
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private RecipesService recipesService;
	
	
	
	/**
	* @Title: addCalendar
	* @Description: TODO(添加校历)
	* @param @param calendar
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/calendar/addCalendar",method=RequestMethod.POST)
	@ResponseBody
	public Object addCalendar(@RequestBody BCalendar calendar) {
		String title = calendar.getTitle();
		String author = calendar.getAuthor();
		Date createtime = calendar.getCreatetime();
		String image = calendar.getImage();
		Integer uid = calendar.getUid();
		Integer gardenId = calendar.getGardenId();
		if (title == null || title == "") {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (author == null || author == "") {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (createtime == null) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (image == null || image == "") {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (uid == null) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (gardenId == null) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		try {
			calendarService.addCalendar(calendar);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "添加失败，错误原因："+e.getMessage());
		}
		
	}	
	
	
	
	/**
	* @Title: deleteCalendarById
	* @Description: TODO(根据校历id删除校历)
	* @param @param calendar
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/calendar/deleteCalendarById",method=RequestMethod.POST)
	@ResponseBody
	public Object deleteCalendarById(@RequestBody BCalendar calendar) {
		Integer id = calendar.getId();
		if (id == null) {
			return ResultUtil.error(400, "删除失败，缺少必要参数");
		}
		try {
			calendarService.deleteCalendarById(id);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "删除失败，失败原因："+e.getMessage());
		}
		
	}
	
	
	
	
	/**
	* @Title: updateCalendar
	* @Description: TODO(修改校历)
	* @param @param calendar
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/calendar/updateCalendar",method=RequestMethod.POST)
	@ResponseBody
	public Object updateCalendar(@RequestBody BCalendar calendar) {
		Integer id = calendar.getId();
		String title = calendar.getTitle();
		String author = calendar.getAuthor();
		Date createtime = calendar.getCreatetime();
		String image = calendar.getImage();
		Integer uid = calendar.getUid();
		Integer gardenId = calendar.getGardenId();
		if (id == null) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (title == null || title == "") {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (author == null || author == "") {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (createtime == null) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (image == null || image == "") {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (uid == null) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (gardenId == null) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		try {
			calendarService.updateCalendar(calendar);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "修改失败，错误原因："+e.getMessage());
		}
		
	}	
	
	
	
	/**
	* @Title: getCalendars
	* @Description: TODO(获取所有的校历信息)
	* @param @param queryModel
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/calendar/getCalendars",method=RequestMethod.POST)
	@ResponseBody
	public Object getCalendars(@RequestBody QueryModel queryModel) {
		String query = queryModel.getQuery();
		Integer pageNum = queryModel.getPageNum();
		Integer pageSize = queryModel.getPageSize();
		Integer gardenId = queryModel.getGardenId();
		if (pageNum <= 0 || pageSize <= 0) {
			return ResultUtil.error(400, "请求参数错误，提示：需要pageNum>0 pageSize>0");
		}
		PageInfo<BCalendar> calendars;
		try {
			calendars = calendarService.getCalendars(query,pageNum,pageSize,gardenId);
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("calendars", calendars);
			return ResultUtil.success(map);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "获取数据失败，失败原因："+e.getMessage());
		}
		
	}
	
	
	
	
	/**
	* @Title: addNotice
	* @Description: TODO(添加通知)
	* @param @param notice
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/notice/addNotice",method=RequestMethod.POST)
	@ResponseBody
	public Object addNotice(@RequestBody BNotice notice) {
		String title = notice.getTitle();
		String descript = notice.getDescript();
		String author = notice.getAuthor();
		Date createtime = notice.getCreatetime();
		Integer uid = notice.getUid();
		Integer gardenId = notice.getGardenId();
		if (title == null || title == "") {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (descript == null || descript == "") {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (author == null || author == "") {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (createtime == null) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (uid == null) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (gardenId == null) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		
		try {
			noticeService.addNotice(notice);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "添加失败，错误原因："+e.getMessage());
		}
		
	}
	
	
	
	
	/**
	* @Title: deleteNoticeById
	* @Description: TODO(根据id删除通知)
	* @param @param notice
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/notice/deleteNoticeById",method=RequestMethod.POST)
	@ResponseBody
	public Object deleteNoticeById(@RequestBody BNotice notice) {
		Integer id = notice.getId();
		if (id == null) {
			return ResultUtil.error(400, "删除失败，缺少必要参数");
		}
		try {
			noticeService.deleteNoticeById(id);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "删除失败，失败原因："+e.getMessage());
		}
		
	}
	
	
	
	/**
	* @Title: updateNotice
	* @Description: TODO(修改通知)
	* @param @param notice
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/notice/updateNotice",method=RequestMethod.POST)
	@ResponseBody
	public Object updateNotice(@RequestBody BNotice notice) {
		Integer id = notice.getId();
		String title = notice.getTitle();
		String descript = notice.getDescript();
		String author = notice.getAuthor();
		Date createtime = notice.getCreatetime();
		Integer uid = notice.getUid();
		Integer gardenId = notice.getGardenId();
		if (id == null) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (title == null || title == "") {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (descript == null || descript == "") {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (author == null || author == "") {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (createtime == null) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (uid == null) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (gardenId == null) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		
		try {
			noticeService.updateNotice(notice);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "修改失败，错误原因："+e.getMessage());
		}
		
	}
	
	
	
	
	
	/**
	* @Title: getNotices
	* @Description: TODO(获取所有的通知)
	* @param @param queryModel
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/notice/getNotices",method=RequestMethod.POST)
	@ResponseBody
	public Object getNotices(@RequestBody QueryModel queryModel) {
		String query = queryModel.getQuery();
		Integer pageNum = queryModel.getPageNum();
		Integer pageSize = queryModel.getPageSize();
		Integer gardenId = queryModel.getGardenId();
		if (pageNum <= 0 || pageSize <= 0) {
			return ResultUtil.error(400, "请求参数错误，提示：需要pageNum>0 pageSize>0");
		}
		PageInfo<BNotice> notices;
		try {
			notices = noticeService.getNotices(query,pageNum,pageSize,gardenId);
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("notices", notices);
			return ResultUtil.success(map);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "获取数据失败，失败原因："+e.getMessage());
		}
		
	}
	
	
	
	
	
	
	
	/**
	* @Title: addRecipes
	* @Description: TODO(添加菜谱)
	* @param @param recipes
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/recipes/addRecipes",method=RequestMethod.POST)
	@ResponseBody
	public Object addRecipes(@RequestBody BRecipes recipes) {
		String author = recipes.getAuthor();
		Integer uid = recipes.getUid();
		Integer classId = recipes.getClassId();
		Integer gardenId = recipes.getGardenId();
		String image = recipes.getImage();
		Date createtime = recipes.getCreatetime();
		if (author == null || author == "") {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (uid == null) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (classId == null) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (gardenId == null) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (image == null || image == "") {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (createtime == null) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		try {
			recipesService.addRecipes(recipes);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "添加失败，失败原因："+e.getMessage());
		}

		
	}
	
	
	
	/**
	* @Title: deleteRecipesById
	* @Description: TODO(根据id删除食谱)
	* @param @param recipes
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/recipes/deleteRecipesById",method=RequestMethod.POST)
	@ResponseBody
	public Object deleteRecipesById(@RequestBody BRecipes recipes) {
		Integer id = recipes.getId();
		if (id == null) {
			return ResultUtil.error(400, "删除失败，缺少必要参数");
		}
		try {
			recipesService.deleteRecipesById(id);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "删除失败，错误原因："+e.getMessage());
		}
		
	}
	
	
	
	
	
	/**
	* @Title: updateRecipes
	* @Description: TODO(修改食谱)
	* @param @param recipes
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/recipes/updateRecipes",method=RequestMethod.POST)
	@ResponseBody
	public Object updateRecipes(@RequestBody BRecipes recipes) {
		Integer id = recipes.getId();
		String author = recipes.getAuthor();
		Integer uid = recipes.getUid();
		Integer classId = recipes.getClassId();
		Integer gardenId = recipes.getGardenId();
		String image = recipes.getImage();
		Date createtime = recipes.getCreatetime();
		if (id == null) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (author == null || author == "") {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (uid == null) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (classId == null) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (gardenId == null) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (image == null || image == "") {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (createtime == null) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		try {
			recipesService.updateRecipes(recipes);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "修改失败，失败原因："+e.getMessage());
		}

		
	}
	
	
	
	
	@RequestMapping(value="/recipes/getRecipes",method=RequestMethod.POST)
	@ResponseBody
	public Object getRecipes(@RequestBody QueryModel queryModel) {
		String query = queryModel.getQuery();
		Integer pageNum = queryModel.getPageNum();
		Integer pageSize = queryModel.getPageSize();
		Integer gardenId = queryModel.getGardenId();
		Integer classId = queryModel.getClassId();
		if (pageNum <= 0 || pageSize <= 0) {
			return ResultUtil.error(400, "请求参数错误，提示：需要pageNum>0 pageSize>0");
		}
		PageInfo<BRecipes> recipes;
		try {
			recipes = recipesService.getRecipes(query,pageNum,pageSize,gardenId,classId);
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("recipes", recipes);
			return ResultUtil.success(map);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "获取数据失败，失败原因："+e.getMessage());
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
