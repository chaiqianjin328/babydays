package com.babydays.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.babydays.model.BComment;
import com.babydays.model.BGarden;
import com.babydays.model.BLeave;
import com.babydays.model.ListResult;
import com.babydays.service.GardenService;
import com.babydays.service.LeaveService;
import com.babydays.util.ResultUtil;

@Controller
@RequestMapping("/web/leave")
public class WebLeaveController {
	
	@Autowired
	private LeaveService leaveService;
	
	
	@Autowired
	private GardenService gardenService;

	
	@RequestMapping(value="/leave_headmaster",method=RequestMethod.GET)
	public String toLeave_headmaster(HttpServletRequest request) {
		List<BGarden> list = gardenService.getAllGarden();
		request.setAttribute("list", list);
		request.setAttribute("leaveBtn", "leaveBtn");
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String queryDate = format.format(date);
		request.setAttribute("queryDate", queryDate);
		return "web/7-leave-headmaster";
	}
	
	
	
	@RequestMapping(value="/leave_student",method=RequestMethod.GET)
	public String toLeave_student(HttpServletRequest request) {
		request.setAttribute("leaveBtn", "leaveBtn");
		Integer stuId = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		Integer sex = Integer.parseInt(request.getParameter("sex"));
		request.setAttribute("stuId", stuId);
		request.setAttribute("stuName", name);
		request.setAttribute("sex", sex);
		return "web/7-leave-student";
	}
	
	@RequestMapping(value="/leave_student_list",method=RequestMethod.GET)
	public String toLeave_student_list(HttpServletRequest request) {
		request.setAttribute("leaveBtn", "leaveBtn");
		Integer stuId = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		Integer sex = Integer.parseInt(request.getParameter("sex"));
		request.setAttribute("stuId", stuId);
		request.setAttribute("stuName", name);
		request.setAttribute("sex", sex);
		return "web/7-leave-student-list";
	}
	
	
	
	@RequestMapping(value="/addLeave",method=RequestMethod.POST)
	@ResponseBody
	public Object addLeave(BLeave leave) {
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
	public Object deleteLeaveById(BLeave leave) {
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
	* @Title: leaveList
	* @Description: TODO(获取学生的请假记录)
	* @param @param request
	* @param @param response
	* @param @param pageIndex
	* @param @param pageSize
	* @param @param start
	* @param @param end
	* @param @param stuId
	* @param @param leaveday
	* @param @return    参数
	* @return ListResult    返回类型
	* @throws
	*/
	@RequestMapping(value="/leaveList",method=RequestMethod.GET)
	@ResponseBody
	public ListResult leaveList(HttpServletRequest request,HttpServletResponse response,int pageIndex,int pageSize,@DateTimeFormat(pattern="yyyy-MM-dd")@RequestParam(defaultValue="2018-01-01")Date start,@DateTimeFormat(pattern="yyyy-MM-dd")@RequestParam(defaultValue="2500-01-01")Date end,Integer stuId,@RequestParam(defaultValue="0")Integer leaveday){
		HashMap<String,Object> valMap = new HashMap<String,Object>();
		valMap.put("pageIndex", pageIndex);
		valMap.put("pageSize", pageSize);
		valMap.put("start", start);
		valMap.put("end", end);
		valMap.put("stuId", stuId);
		valMap.put("leaveday", leaveday);
		return leaveService.leaveList(valMap);
	}
	
	
	
	
	
}
