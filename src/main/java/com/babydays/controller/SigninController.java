package com.babydays.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.babydays.model.BSignin;
import com.babydays.model.QueryModel;
import com.babydays.service.SigninService;
import com.babydays.util.ResultUtil;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/signin")
public class SigninController {

	
	
	@Autowired
	private SigninService signinService;
	
	
	
	
	
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
	public Object inGarden(@RequestBody BSignin signin) {
		try {
			Integer stuId = signin.getStuId();
			if (stuId == null || stuId <= 0) {
				return ResultUtil.error(400, "入园失败，缺少必要参数");
			}
			signin.setIntime(new Date());
			signin.setCreatetime(new Date());
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
	public Object outGarden(@RequestBody BSignin signin) {
		try {
			Integer id = signin.getId();
			if (id == null || id <= 0) {
				return ResultUtil.error(400, "离园失败，缺少必要参数");
			}
			signin.setOuttime(new Date());
			signinService.outGarden(signin);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "离园失败，失败原因："+e.getMessage());
		}
	}
	
	
	
	
	/**
	* @Title: getSignins
	* @Description: TODO(根据时间获取签到列表)
	* @param @param queryModel
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/getSignins",method=RequestMethod.POST)
	@ResponseBody
	public Object getSignins(@RequestBody QueryModel queryModel) {
		Integer pageNum = queryModel.getPageNum();
		Integer pageSize = queryModel.getPageSize();
		Integer gardenId = queryModel.getGardenId();
		Integer classId = queryModel.getClassId();
		String createtime = queryModel.getCreatetime();
		if (pageNum <= 0 || pageSize <= 0) {
			return ResultUtil.error(400, "请求参数错误，提示：需要pageNum>0 pageSize>0");
		}
		PageInfo<BSignin> signins;
		try {
			signins = signinService.getSignins(pageNum,pageSize,gardenId,classId,createtime);
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("signins", signins);
			return ResultUtil.success(map);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "获取数据失败，失败原因："+e.getMessage());
		}
	}
	
	
	
	
	/**
	* @Title: getStudentSignins
	* @Description: TODO(获取学生个人签到列表)
	* @param @param queryModel
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/getStudentSignins",method=RequestMethod.POST)
	@ResponseBody
	public Object getStudentSignins(@RequestBody QueryModel queryModel) {
		Integer pageNum = queryModel.getPageNum();
		Integer pageSize = queryModel.getPageSize();
		Integer stuId = queryModel.getStuId();
		String createtime = queryModel.getCreatetime();
		if (pageNum <= 0 || pageSize <= 0) {
			return ResultUtil.error(400, "请求参数错误，提示：需要pageNum>0 pageSize>0");
		}
		if (stuId <= 0) {
			return ResultUtil.error(400, "请求参数错误，提示：stuId参数错误");
		}
		if (createtime == null || createtime == "") {
			return ResultUtil.error(400, "请求参数错误，提示：createtime不能为空");
		} else {
			try {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				format.parse(createtime);
			} catch (ParseException e1) {
				e1.printStackTrace();
				return ResultUtil.error(400, "请求参数错误，提示：日期格式错误");
			}
		}
		PageInfo<BSignin> signins;
		try {
			signins = signinService.getStudentSignins(pageNum,pageSize,stuId,createtime);
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("signins", signins);
			return ResultUtil.success(map);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "获取数据失败，失败原因："+e.getMessage());
		}
	}
	
	
	
	
	
	
}
