package com.babydays.controller;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.babydays.model.BProgram;
import com.babydays.model.QueryModel;
import com.babydays.service.GardenService;
import com.babydays.service.ProgramService;
import com.babydays.util.ResultUtil;
import com.github.pagehelper.PageInfo;

/**
* @ClassName: TeachProgramController
* @Description: TODO(教学计划)
* @author chaiqianjin
* @date 2018年11月7日
*
*/
@Controller
@RequestMapping("/teachprogram")
public class TeachProgramController {

	
	
	@Autowired
	private ProgramService programService;
	
	
	
	/**
	* @Title: addProgram
	* @Description: TODO(添加教学计划)
	* @param @param program
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/addProgram",method=RequestMethod.POST)
	@ResponseBody
	public Object addProgram(@RequestBody BProgram program) {
		String author = program.getAuthor();
		Integer classId = program.getClassId();
		Integer gardenId = program.getGardenId();
		String image = program.getImage();
		Integer uid = program.getUid();
		Date createtime = program.getCreatetime();
		if (author == null || author == "") {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (classId == null || classId <=0) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (gardenId == null || gardenId <=0) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (image == null || image == "") {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (uid == null || uid <=0) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (createtime == null) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		try {
			programService.addProgram(program);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "添加失败，错误原因："+e.getMessage());
		}
		
		
	}
	
	
	
	/**
	* @Title: deleteProgramById
	* @Description: TODO(根据id删除教学计划)
	* @param @param program
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/deleteProgramById",method=RequestMethod.POST)
	@ResponseBody
	public Object  deleteProgramById(@RequestBody BProgram program) {
		Integer id = program.getId();
		if (id == null || id<=0) {
			return ResultUtil.error(400, "删除失败，缺少必要参数");
		}
		try {
			programService.deleteProgramById(id);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "删除失败，失败原因："+e.getMessage());
		}
	
	}
	
	
	
	
	/**
	* @Title: updateProgram
	* @Description: TODO(修改教学计划)
	* @param @param program
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/updateProgram",method=RequestMethod.POST)
	@ResponseBody
	public Object updateProgram(@RequestBody BProgram program) {
		Integer id = program.getId();
		String author = program.getAuthor();
		Integer classId = program.getClassId();
		Integer gardenId = program.getGardenId();
		String image = program.getImage();
		Integer uid = program.getUid();
		Date createtime = program.getCreatetime();
		if (id == null || id<=0) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (author == null || author == "") {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (classId == null || classId <=0) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (gardenId == null || gardenId <=0) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (image == null || image == "") {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (uid == null || uid <=0) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (createtime == null) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		try {
			programService.updateProgram(program);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "修改失败，失败原因："+e.getMessage());
		}
		
		
	}
	
	
	@RequestMapping(value="/getPrograms",method=RequestMethod.POST)
	@ResponseBody
	public Object getPrograms(@RequestBody QueryModel queryModel) {
		Integer pageSize = queryModel.getPageSize();
		Integer pageNum = queryModel.getPageNum();
		String query = queryModel.getQuery();
		Integer classId = queryModel.getClassId();
		Integer gardenId = queryModel.getGardenId();
		if (pageNum <= 0 || pageSize <= 0) {
			return ResultUtil.error(400, "请求参数错误，提示：需要pageNum>0 pageSize>0");
		}
		PageInfo<BProgram> programs;
		try {
			programs = programService.getPrograms(query,pageNum,pageSize,classId,gardenId);
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("programs", programs);
			return ResultUtil.success(map);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "获取数据失败，失败原因："+e.getMessage());
		}
		
		
	}
	
	
	
	
	
	
	
	
	
}
