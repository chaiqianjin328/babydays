package com.babydays.controller;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.babydays.model.BLeave;
import com.babydays.model.QueryModel;
import com.babydays.service.LeaveService;
import com.babydays.util.ResultUtil;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/leave")
public class LeaveController {

	@Autowired
	private LeaveService leaveService;
	
	
	@RequestMapping(value="/addLeave",method=RequestMethod.POST)
	@ResponseBody
	public Object addLeave(@RequestBody BLeave leave) {
		Integer stuId = leave.getStuId();
		Integer leaveday = leave.getLeaveday();
		Date startTime = leave.getStartTime();
		Date endTime = leave.getEndTime();
		String leaveContent = leave.getLeaveContent();
		if (stuId==null || stuId <= 0) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (leaveday==null || leaveday <= 0) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (startTime == null) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (endTime == null) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (leaveContent == null || leaveContent == "") {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		try {
			leaveService.addLeave(leave);
			return ResultUtil.success();
		} catch (Exception e) {
			return ResultUtil.error(400, "添加失败，错误原因："+e.getMessage());
		}
	}
	
	
	
	/**
	* @Title: deleteLeaveById
	* @Description: TODO(根据id删除请假记录)
	* @param @param comment
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/deleteLeaveById",method=RequestMethod.POST)
	@ResponseBody
	public Object deleteLeaveById(@RequestBody BLeave leave) {
		Integer id = leave.getId();
		if (id == null) {
			return ResultUtil.error(400, "删除失败，缺少必要参数");
		}
		try {
			leaveService.deleteLeaveById(id);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "删除失败，错误原因："+e.getMessage());
		}

	
	}
	
	
	
	/**
	* @Title: getLeaves
	* @Description: TODO(获取学生的请假记录)
	* @param @param queryModel
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/getLeaves",method= RequestMethod.POST)
	@ResponseBody
	public Object getLeaves(@RequestBody QueryModel queryModel) {
		String query = queryModel.getQuery();
		Integer pageNum = queryModel.getPageNum();
		Integer pageSize = queryModel.getPageSize();
		Date start = queryModel.getStart();
		Date end = queryModel.getEnd();
		Integer stuId = queryModel.getStuId();
		Integer leaveday = queryModel.getLeaveday();
		if (pageNum <= 0 || pageSize <= 0) {
			return ResultUtil.error(400, "请求参数错误，提示：需要pageNum>0 pageSize>0");
		}
		PageInfo<BLeave> leaves;
		try {
			leaves = leaveService.getLeaves(query,pageNum,pageSize,start,end,stuId,leaveday);
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("leaves", leaves);
			return ResultUtil.success(map);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "获取数据失败，失败原因："+e.getMessage());
		}
	}
	
	
	
	
	
	
}
