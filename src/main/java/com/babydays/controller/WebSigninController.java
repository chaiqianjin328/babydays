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

import com.babydays.model.BGarden;
import com.babydays.model.BSignin;
import com.babydays.model.ListResult;
import com.babydays.service.GardenService;
import com.babydays.service.SigninService;
import com.babydays.service.StudentService;
import com.babydays.util.ResultUtil;

@Controller
@RequestMapping("/web/signin")
public class WebSigninController {
	
	
	@Autowired
	private SigninService signinService;
	
	
	@Autowired
	private GardenService gardenService;

	
	@RequestMapping(value="/signin_headmaster",method=RequestMethod.GET)
	public String toSignin_headmaster(HttpServletRequest request) {
		List<BGarden> list = gardenService.getAllGarden();
		request.setAttribute("list", list);
		request.setAttribute("signinBtn", "signinBtn");
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String queryDate = format.format(date);
		request.setAttribute("queryDate", queryDate);
		return "web/7-signin-headmaster";
	}
	
	
	@RequestMapping(value="/signin_director",method=RequestMethod.GET)
	public String toSignin_director(HttpServletRequest request) {
		request.setAttribute("signinBtn", "signinBtn");
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String queryDate = format.format(date);
		request.setAttribute("queryDate", queryDate);
		return "web/7-signin-director";
	}
	
	@RequestMapping(value="/signin_student",method=RequestMethod.GET)
	public String toSignin_student(HttpServletRequest request) {
		request.setAttribute("signinBtn", "signinBtn");
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String queryDate = format.format(date);
		request.setAttribute("queryDate", queryDate);
		return "web/7-signin-student";
	}
	
	
	
	/**
	* @Title: signinList
	* @Description: TODO(获取签到列表)
	* @param @param request
	* @param @param response
	* @param @param pageIndex
	* @param @param pageSize
	* @param @param query
	* @param @param gardenId
	* @param @param classId
	* @param @param type
	* @param @return    参数
	* @return ListResult    返回类型
	* @throws
	*/
	@RequestMapping(value="/signinList",method=RequestMethod.GET)
	@ResponseBody
	public ListResult signinList(HttpServletRequest request,HttpServletResponse response,int pageIndex,int pageSize,String query,int gardenId,int classId,String createtime){
		HashMap<String,Object> valMap = new HashMap<String,Object>();
		valMap.put("pageIndex", pageIndex);
		valMap.put("pageSize", pageSize);
		valMap.put("query", query);
		valMap.put("gardenId", gardenId);
		valMap.put("classId", classId);
		valMap.put("createtime", createtime);
		try {
			return signinService.signinList(valMap);
		} catch (ParseException e) {
			e.printStackTrace();
			return new ListResult();
		}
	}
	
	
	
	
	/**
	* @Title: inGarden
	* @Description: TODO(学生入园)
	* @param @param signin
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/inGarden",method=RequestMethod.POST)
	@ResponseBody
	public Object inGarden(BSignin signin) {
		signin.setIntime(new Date());
		signin.setCreatetime(new Date());
		try {
			signinService.inGarden(signin);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "入园失败，失败原因："+e.getMessage());
		}
	}
	
	
	
	
	/**
	* @Title: outGarden
	* @Description: TODO(学生离园)
	* @param @param signin
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/outGarden",method=RequestMethod.POST)
	@ResponseBody
	public Object outGarden(BSignin signin) {
		signin.setOuttime(new Date());
		try {
			signinService.outGarden(signin);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "离园失败，失败原因："+e.getMessage());
		}
	}
	
	
	
	
	/**
	* @Title: studenSigninList
	* @Description: TODO(获取签到列表)
	* @param @param request
	* @param @param response
	* @param @param pageIndex
	* @param @param pageSize
	* @param @param query
	* @param @param gardenId
	* @param @param classId
	* @param @param type
	* @param @return    参数
	* @return ListResult    返回类型
	* @throws
	*/
	@RequestMapping(value="/studenSigninList",method=RequestMethod.GET)
	@ResponseBody
	public ListResult studenSigninList(HttpServletRequest request,HttpServletResponse response,int pageIndex,int pageSize,String query,int stuId,String createtime){
		HashMap<String,Object> valMap = new HashMap<String,Object>();
		valMap.put("pageIndex", pageIndex);
		valMap.put("pageSize", pageSize);
		valMap.put("query", query);
		valMap.put("createtime", createtime);
		valMap.put("stuId", stuId);
		try {
			return signinService.studenSigninList(valMap);
		} catch (ParseException e) {
			e.printStackTrace();
			return new ListResult();
		}
	}
	
	
	
	
	
	
	
	
	
}
