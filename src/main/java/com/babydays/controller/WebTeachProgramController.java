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

import com.babydays.model.BGarden;
import com.babydays.model.BNotice;
import com.babydays.model.BProgram;
import com.babydays.model.BRecipes;
import com.babydays.model.ListResult;
import com.babydays.service.GardenService;
import com.babydays.service.ProgramService;
import com.babydays.util.CommonUrlUtil;
import com.babydays.util.PicUpload;
import com.babydays.util.ResultUtil;

@Controller
@RequestMapping("/web/teachprogram")
public class WebTeachProgramController {

	
	@Autowired
	private ProgramService programService;
	
	
	@Autowired
	private GardenService gardenService;
	
	
	@RequestMapping(value="/teach_program")
	public String toTeach_program(HttpServletRequest request) {
		List<BGarden> list = gardenService.getAllGarden();
		request.setAttribute("list", list);
		request.setAttribute("teachprogramBtn","teachprogramBtn");
		return "web/5-teaching-program";
	}
	
	
	
	/**
	* @Title: addProgram
	* @Description: TODO(添加教学计划)
	* @param @param request
	* @param @param program
	* @param @param programPic
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/addProgram",method= {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Object addProgram(HttpServletRequest request,BProgram program,@RequestParam(value = "programPic", required = false) MultipartFile programPic) {
		try {
			String timeStr = request.getParameter("createTime");
			if (timeStr==null||timeStr=="") {
				return ResultUtil.error(400, "添加食谱失败");
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			Date date = format.parse(timeStr);
			program.setCreatetime(date);
			
			//保存地址
			String position = CommonUrlUtil.UPLOAD_PICTURE_PATH;
			//图片访问路径前缀
			String picPath = CommonUrlUtil.PREFIX_PICTURE_URL;
			if (programPic.getSize()>0) {
				String picUrl = PicUpload.picUploadAndReturnURL(programPic, request, position);
				System.out.println(picUrl);
				program.setImage(picPath+picUrl);
			}
			
			
			programService.addProgram(program);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "添加失败，错误原因："+e.getMessage());
		}
	}
	
	
	/**
	* @Title: deleteProgramById
	* @Description: TODO(删除教学计划)
	* @param @param id
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/deleteProgramById",method=RequestMethod.GET)
	@ResponseBody
	public Object deleteProgramById(Integer id) {
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
	* @param @param request
	* @param @param program
	* @param @param programPic
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/updateProgram",method= {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Object updateProgram(HttpServletRequest request,BProgram program,@RequestParam(value = "programPic", required = false) MultipartFile programPic) {
		try {
			String timeStr = request.getParameter("createTime");
			if (timeStr==null||timeStr=="") {
				return ResultUtil.error(400, "修改食谱失败");
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			Date date = format.parse(timeStr);
			program.setCreatetime(date);
			
			//服务器保存地址
			String position = CommonUrlUtil.UPLOAD_PICTURE_PATH;
			//图片访问路径前缀
			String picPath = CommonUrlUtil.PREFIX_PICTURE_URL;
			if (programPic.getSize()>0) {
				String picUrl = PicUpload.picUploadAndReturnURL(programPic, request, position);
				System.out.println(picUrl);
				program.setImage(picPath+picUrl);
			}
			
			
			programService.updateProgram(program);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "修改失败，错误原因："+e.getMessage());
		}
	}
	
	
	
	/**
	* @Title: programList
	* @Description: TODO(获取所有的教学计划)
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
	@RequestMapping(value="/programList",method=RequestMethod.GET)
	@ResponseBody
	public ListResult programList(HttpServletRequest request,HttpServletResponse response,int pageIndex,int pageSize,String query,Integer gardenId,Integer classId){
		HashMap<String,Object> valMap = new HashMap<String,Object>();
		valMap.put("pageIndex", pageIndex);
		valMap.put("pageSize", pageSize);
		valMap.put("query", query);
		valMap.put("gardenId", gardenId);
		valMap.put("classId", classId);
		return programService.programList(valMap);
	}
	
	
	
	
	
	
	
}
