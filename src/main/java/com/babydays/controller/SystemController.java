package com.babydays.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.babydays.model.BAdmin;
import com.babydays.model.BClass;
import com.babydays.model.BDirector;
import com.babydays.model.BDoctor;
import com.babydays.model.BGarden;
import com.babydays.model.BStudent;
import com.babydays.model.BTeacher;
import com.babydays.model.BUser;
import com.babydays.model.QueryModel;
import com.babydays.service.AdminService;
import com.babydays.service.ClassService;
import com.babydays.service.DirectorService;
import com.babydays.service.DoctorService;
import com.babydays.service.GardenService;
import com.babydays.service.StudentService;
import com.babydays.service.TeacherService;
import com.babydays.service.UserService;
import com.babydays.util.ResultUtil;
import com.github.pagehelper.PageInfo;
/**
* @ClassName: SystemController
* @Description: TODO(系统管理控制类)
* @author chaiqianjin
* @date 2018年8月21日
*
*/
@Controller
@RequestMapping("/system")
public class SystemController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private DirectorService directorService;
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private GardenService gardenService;
	
	@Autowired
	private ClassService classService;
	
	
	/**
	* @Title: addAdmin
	* @Description: TODO(添加admin用户)
	* @param @param admin
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/admin/addAdmin",method= RequestMethod.POST)
	@ResponseBody
	public Object addAdmin(@RequestBody BAdmin admin) {
		String username = admin.getUsername();
		String password = admin.getPassword();
		String name = admin.getName();
		String email = admin.getEmail();
		String tel = admin.getTel();
		if (username == null || username == "") {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		if (password == null || password == "") {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		if (name == null || name == "") {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		if (email == null || email == "") {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		if (tel == null || tel == "") {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		//校验username是否重复
		List<BUser> list = userService.selectAdminByUsername(username);
		if (list.size()>0) {
			return ResultUtil.error(400, "注册失败，用户名被占用");
		}
		//填充用户信息
		BUser user = new BUser();
		user.setUsername(username);
		user.setPassword(password);
		user.setRole(0);
		//添加超级管理员详细信息,和用户信息
		try {
			adminService.insertSelective(admin,user);
			return ResultUtil.success();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultUtil.error(400, "注册失败，错误原因："+e.getMessage());
		}
		
	}
	
	
	/**
	* @Title: resetStatusAdmin
	* @Description: TODO(更改admin用户可用状态 0可用 1禁用)
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/admin/resetStatusAdmin",method= RequestMethod.POST)
	@ResponseBody
	public Object resetStatusAdmin(@RequestBody Integer id) {
		try {
			adminService.resetStatusAdmin(id);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "删除失败，错误原因："+e.getMessage());
		}
	}
	
	
	
	/**
	* @Title: updateAdmin
	* @Description: TODO(更改admin用户信息)
	* @param @param admin
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/admin/updateAdmin",method= RequestMethod.POST)
	@ResponseBody
	public Object updateAdmin(@RequestBody BAdmin admin) {
		Integer id = admin.getId();
		String password = admin.getPassword();
		String name = admin.getName();
		String email = admin.getEmail();
		String tel = admin.getTel();
		if (id == null) {
			return ResultUtil.error(400, "更改失败，缺少必填参数");
		}
		if (password == null || password == "") {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		if (name == null || name == "") {
			return ResultUtil.error(400, "更改失败，缺少必填参数");
		}
		if (email == null || email == "") {
			return ResultUtil.error(400, "更改失败，缺少必填参数");
		}
		if (tel == null || tel == "") {
			return ResultUtil.error(400, "更改失败，缺少必填参数");
		}
		
		try {
			adminService.updateAdmin(admin);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "更改失败，错误原因："+e.getMessage());
		}
		
	}
	
	
	/**
	* @Title: getAdmins
	* @Description: TODO(获取所有admin用户)
	* @param @param query
	* @param @param pageNum
	* @param @param pageSize
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/admin/getAdmins",method= RequestMethod.POST)
	@ResponseBody
	public Object getAdmins(@RequestBody QueryModel queryModel) {
		String query = queryModel.getQuery();
		Integer pageNum = queryModel.getPageNum();
		Integer pageSize = queryModel.getPageSize();
		if (pageNum <= 0 || pageSize <= 0) {
			return ResultUtil.error(400, "请求参数错误，提示：需要pageNum>0 pageSize>0");
		}
		PageInfo<BAdmin> admins;
		try {
			admins = adminService.getAdmins(query,pageNum,pageSize);
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("admins", admins);
			return ResultUtil.success(map);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "获取数据失败，失败原因："+e.getMessage());
		}
	}
	
	
	/**
	* @Title: addDirector
	* @Description: TODO(添加director用户，根据参数role的实际值判断是那类用户<1:园长；2:教学园长；3:行政园长；>)
	* @param @param director
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/director/addDirector",method= RequestMethod.POST)
	@ResponseBody
	public Object addDirector(@RequestBody BDirector director) {
		String username = director.getUsername();
		String password = director.getPassword();
		Integer role = director.getRole();
		String name = director.getName();
		String email = director.getEmail();
		String tel = director.getTel();
		Integer gardenId = director.getGardenId();
		if (username == null || username.equals("")) {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		if (password == null || password.equals("")) {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		if (role == null) {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}else {
			if (role>3||role<1) {
				return ResultUtil.error(400, "注册失败，角色参数错误");
			}
		}
		if (name == null || name.equals("")) {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		if (email == null || email.equals("")) {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		if (tel == null || tel.equals("")) {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		if (gardenId == null || gardenId <= 0) {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		
		//校验校长是否被指定
		if (role == 1) {
			BGarden existGarden = gardenService.directorAppointIsOrNot(gardenId);
			if (existGarden == null) {
				return ResultUtil.error(400, "注册失败，幼儿园不存在");
			}else {
				String directorName = existGarden.getDirectorName();
				if (directorName != null && directorName != "") {
					return ResultUtil.error(400, "注册失败，该幼儿园园长已被指定");
				}
			}
			
		}
		
		//校验username是否重复
		List<BUser> list = userService.selectAdminByUsername(username);
		if (list.size()>0) {
			return ResultUtil.error(400, "注册失败，用户名被占用");
		}
		//填充用户信息
		BUser user = new BUser();
		user.setUsername(username);
		user.setPassword(password);
		user.setRole(role);
		//添加director详细信息,和用户信息
		try {
			directorService.insertSelective(director,user);
			return ResultUtil.success();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultUtil.error(400, "注册失败，错误原因："+e.getMessage());
		}
		
	}
	
	
	
	/**
	* @Title: resetStatusDirector
	* @Description: TODO(更改director用户可用状态 0可用 1禁用)
	* @param @param id
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/director/resetStatusDirector",method= RequestMethod.POST)
	@ResponseBody
	public Object resetStatusDirector(@RequestBody Integer id) {
		try {
			directorService.resetStatusDirector(id);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "删除失败，错误原因："+e.getMessage());
		}
	}
	
	
	/**
	* @Title: updateDirector
	* @Description: TODO(更改director用户详细信息)
	* @param @param director
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/director/updateDirector",method= RequestMethod.POST)
	@ResponseBody
	public Object updateDirector(@RequestBody BDirector director) {
		Integer id = director.getId();
		String password = director.getPassword();
		Integer role = director.getRole();
		String name = director.getName();
		String email = director.getEmail();
		String tel = director.getTel();
		Integer gardenId = director.getGardenId();
		if (id == null) {
			return ResultUtil.error(400, "更改失败，缺少必填参数");
		}
		if (password == null || password == "") {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		if (role == null) {
			return ResultUtil.error(400, "更改失败，缺少必填参数");
		}else {
			if (role>3||role<1) {
				return ResultUtil.error(400, "更改失败，角色参数错误");
			}
		}
		if (name == null || name == "") {
			return ResultUtil.error(400, "更改失败，缺少必填参数");
		}
		if (email == null || email == "") {
			return ResultUtil.error(400, "更改失败，缺少必填参数");
		}
		if (tel == null || tel == "") {
			return ResultUtil.error(400, "更改失败，缺少必填参数");
		}
		if (gardenId == null || gardenId <= 0) {
			return ResultUtil.error(400, "更改失败，缺少必填参数");
		}
		//校验校长是否被指定
		if (role == 1) {
			BGarden existGarden = gardenService.directorAppointIsOrNot(gardenId);
			if (existGarden == null) {
				return ResultUtil.error(400, "更改失败，幼儿园不存在");
			}else {
				if (existGarden.getDirectorId() == id) {
					//园长没有变化
				} else {
					String directorName = existGarden.getDirectorName();
					if (directorName != null && directorName != "") {
						return ResultUtil.error(400, "更改失败，该幼儿园园长已被指定");
					}
				}
			}
			
		}
		try {
			directorService.updateDirector(director);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "更改失败，错误原因："+e.getMessage());
		}
		
	}
	
	
	/**
	* @Title: getDirectors
	* @Description: TODO(根据role获取不同角色下的所有director用户<1:园长；2:教学园长；3:行政园长；>)
	* @param @param query
	* @param @param pageNum
	* @param @param pageSize
	* @param @param role
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/director/getDirectors",method= RequestMethod.POST)
	@ResponseBody
	public Object getDirectors(@RequestBody QueryModel queryModel) {
		String query = queryModel.getQuery();
		Integer pageNum = queryModel.getPageNum();
		Integer pageSize = queryModel.getPageSize();
		Integer gardenId = queryModel.getGardenId();
		Integer role = queryModel.getRole();
		if (pageNum <= 0 || pageSize <= 0) {
			return ResultUtil.error(400, "请求参数错误，提示：需要pageNum>0 pageSize>0");
		}
		if (role == null) {
			return ResultUtil.error(400, "请求失败，缺少必填参数");
		}else {
			if (role>3||role<1) {
				return ResultUtil.error(400, "请求失败，角色参数错误");
			}
		}
		PageInfo<BDirector> directors;
		try {
			directors = directorService.getDirectors(query,pageNum,pageSize,role,gardenId);
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("directors", directors);
			return ResultUtil.success(map);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "获取数据失败，失败原因："+e.getMessage());
		}
	}
	
	
	
	/**
	* @Title: getAllDirectors
	* @Description: TODO(获取所有的director用户)
	* @param @param queryModel
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/director/getAllDirectors",method= RequestMethod.POST)
	@ResponseBody
	public Object getAllDirectors(@RequestBody QueryModel queryModel) {
		String query = queryModel.getQuery();
		Integer pageNum = queryModel.getPageNum();
		Integer pageSize = queryModel.getPageSize();
		Integer gardenId = queryModel.getGardenId();
		if (pageNum <= 0 && pageSize <= 0) {
			return ResultUtil.error(400, "请求参数错误，提示：需要pageNum>0 pageSize>0");
		}
		PageInfo<BDirector> directors;
		try {
			directors = directorService.getAllDirectors(query,pageNum,pageSize,gardenId);
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("directors", directors);
			return ResultUtil.success(map);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "获取数据失败，失败原因："+e.getMessage());
		}
	}
	
	
	
	
	/**
	* @Title: addTeacher
	* @Description: TODO(添加teacher用户，根据参数role的实际值判断是那类用户<4:班主任；5教师；>)
	* @param @param teacher
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/teacher/addTeacher",method= RequestMethod.POST)
	@ResponseBody
	public Object addTeacher(@RequestBody BTeacher teacher) {
		String username = teacher.getUsername();
		String password = teacher.getPassword();
		Integer role = teacher.getRole();
		String name = teacher.getName();
		String email = teacher.getEmail();
		String tel = teacher.getTel();
		Integer gardenId = teacher.getGardenId();
		Integer classId = teacher.getClassId();
		if (username == null || username == "") {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		if (password == null || password == "") {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		if (role == null) {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}else {
			if (role>5||role<4) {
				return ResultUtil.error(400, "注册失败，角色参数错误");
			}
		}
		if (name == null || name == "") {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		if (email == null || email == "") {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		if (tel == null || tel == "") {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		if (gardenId == null) {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		if (classId == null) {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		//校验班主任是否被指定
		if (role == 4) {
			BClass existClass = classService.teacherAppointIsOrNot(classId);
			if (existClass == null) {
				return ResultUtil.error(400, "注册失败，班级不存在");
			}else {
				String teacherName = existClass.getTeacherName();
				if (teacherName != null && teacherName != "") {
					return ResultUtil.error(400, "注册失败，该班级班主任已被指定");
				}
			}
		}
		//校验username是否重复
		List<BUser> list = userService.selectAdminByUsername(username);
		if (list.size()>0) {
			return ResultUtil.error(400, "注册失败，用户名被占用");
		}
		//填充用户信息
		BUser user = new BUser();
		user.setUsername(username);
		user.setPassword(password);
		user.setRole(role);
		//添加teacher详细信息,和用户信息
		try {
			teacherService.insertSelective(teacher,user);
			return ResultUtil.success();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultUtil.error(400, "注册失败，错误原因："+e.getMessage());
		}
		
	}
	
	
	

	/**
	* @Title: resetStatusTeacher
	* @Description: TODO(更改teacher用户可用状态 0可用 1禁用)
	* @param @param id
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/teacher/resetStatusTeacher",method= RequestMethod.POST)
	@ResponseBody
	public Object resetStatusTeacher(@RequestBody Integer id) {
		try {
			teacherService.resetStatusTeacher(id);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "删除失败，错误原因："+e.getMessage());
		}
	}
	
	
	
	/**
	* @Title: updateTeacher
	* @Description: TODO(更改teacher用户详细信息)
	* @param @param teacher
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/teacher/updateTeacher",method= RequestMethod.POST)
	@ResponseBody
	public Object updateTeacher(@RequestBody BTeacher teacher) {
		Integer id = teacher.getId();
		String password = teacher.getPassword();
		Integer role = teacher.getRole();
		String name = teacher.getName();
		String email = teacher.getEmail();
		String tel = teacher.getTel();
		Integer gardenId = teacher.getGardenId();
		Integer classId = teacher.getClassId();
		if (id == null) {
			return ResultUtil.error(400, "更改失败，缺少必填参数");
		}
		if (password == null || password == "") {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		if (role == null) {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}else {
			if (role>5||role<4) {
				return ResultUtil.error(400, "注册失败，角色参数错误");
			}
		}
		if (name == null || name == "") {
			return ResultUtil.error(400, "更改失败，缺少必填参数");
		}
		if (email == null || email == "") {
			return ResultUtil.error(400, "更改失败，缺少必填参数");
		}
		if (tel == null || tel == "") {
			return ResultUtil.error(400, "更改失败，缺少必填参数");
		}
		if (gardenId == null) {
			return ResultUtil.error(400, "更改失败，缺少必填参数");
		}
		if (classId == null) {
			return ResultUtil.error(400, "更改失败，缺少必填参数");
		}
		//校验班主任是否被指定
		if (role == 4) {
			BClass existClass = classService.teacherAppointIsOrNot(classId);
			if (existClass == null) {
				return ResultUtil.error(400, "更改失败，班级不存在");
			}else {
				if (existClass.getTeacherId() == id) {
					//班主任没有变化
				} else {
					String teacherName = existClass.getTeacherName();
					if (teacherName != null && teacherName != "") {
						return ResultUtil.error(400, "更改失败，该班级班主任已被指定");
					}
				}
			}
		}
		try {
			teacherService.updateTeacher(teacher);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "更改失败，错误原因："+e.getMessage());
		}
		
	}
	
	
	
	
	/**
	* @Title: getTeachers
	* @Description: TODO(根据role获取不同角色下的所有teacher用户<4:班主任；5教师；>)
	* @param @param query
	* @param @param pageNum
	* @param @param pageSize
	* @param @param role
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/teacher/getTeachers",method= RequestMethod.POST)
	@ResponseBody
	public Object getTeachers(@RequestBody QueryModel queryModel) {
		String query = queryModel.getQuery();
		Integer pageNum = queryModel.getPageNum();
		Integer pageSize = queryModel.getPageSize();
		Integer gardenId = queryModel.getGardenId();
		Integer classId = queryModel.getClassId();
		Integer role = queryModel.getRole();
		if (pageNum <= 0 || pageSize <= 0) {
			return ResultUtil.error(400, "请求参数错误，提示：需要pageNum>0 pageSize>0");
		}
		if (role == null) {
			return ResultUtil.error(400, "请求失败，缺少必填参数");
		}else {
			if (role>5||role<4) {
				return ResultUtil.error(400, "请求失败，角色参数错误");
			}
		}
		PageInfo<BTeacher> teachers;
		try {
			teachers = teacherService.getTeachers(query,pageNum,pageSize,role,gardenId,classId);
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("teachers", teachers);
			return ResultUtil.success(map);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "获取数据失败，失败原因："+e.getMessage());
		}
	}
	
	
	
	/**
	* @Title: getAllTeachers
	* @Description: TODO(获取所有的teacher用户)
	* @param @param queryModel
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/teacher/getAllTeachers",method= RequestMethod.POST)
	@ResponseBody
	public Object getAllTeachers(@RequestBody QueryModel queryModel) {
		String query = queryModel.getQuery();
		Integer pageNum = queryModel.getPageNum();
		Integer pageSize = queryModel.getPageSize();
		Integer gardenId = queryModel.getGardenId();
		Integer classId = queryModel.getClassId();
		if (pageNum <= 0 && pageSize <= 0) {
			return ResultUtil.error(400, "请求参数错误，提示：需要pageNum>0 pageSize>0");
		}
		PageInfo<BTeacher> teachers;
		try {
			teachers = teacherService.getAllTeachers(query,pageNum,pageSize,gardenId,classId);
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("teachers", teachers);
			return ResultUtil.success(map);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "获取数据失败，失败原因："+e.getMessage());
		}
	}
	
	
	
	
	
	/**
	* @Title: addStudent
	* @Description: TODO(添加student用户)
	* @param @param student
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/student/addStudent",method= RequestMethod.POST)
	@ResponseBody
	public Object addStudent(@RequestBody BStudent student) {
		String username = student.getUsername();
		String password = student.getPassword();
		String name = student.getName();
		String petname = student.getPetname();
		String home = student.getHome();
		Integer sex = student.getSex();
		String photo = student.getPhoto();
		Date birth = student.getBirth();
		String allergy = student.getAllergy();
		Integer diapers = student.getDiapers();
		Integer type = student.getType();
		String parentName = student.getParentName();
		String parentType = student.getParentType();
		String telOne = student.getTelOne();
		String telTwo = student.getTelTwo();
		String address = student.getAddress();
		Integer gardenId = student.getGardenId();
		Integer classId = student.getClassId();
		if (username == null || username == "") {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		if (password == null || password == "") {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		if (name == null || name == "") {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		if (petname == null || petname == "") {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		if (home == null || home == "") {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		if (sex == null) {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		} else {
			if (sex < 0 || sex >1) {
				return ResultUtil.error(400, "注册失败，参数sex错误");
			}
		}
		if (photo == null || photo == "") {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		if (birth == null) {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		if (allergy == null || allergy == "") {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		if (diapers == null) {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		} else {
			if (diapers <0 || diapers >1) {
				return ResultUtil.error(400, "注册失败，参数diapers错误");
			}
		}
		if (type == null) {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		} else {
			if (type <0 || type >2) {
				return ResultUtil.error(400, "注册失败，参数type错误");
			}
		}
		if (parentName == null || parentName == "") {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		if (parentType == null || parentType == "") {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		if (telOne == null || telOne == "") {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		if (telTwo == null || telTwo == "") {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		if (address == null || address == "") {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		if (gardenId == null) {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		if (classId == null) {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		//校验username是否重复
		List<BUser> list = userService.selectAdminByUsername(username);
		if (list.size()>0) {
			return ResultUtil.error(400, "注册失败，用户名被占用");
		}
		//填充用户信息
		BUser user = new BUser();
		user.setUsername(username);
		user.setPassword(password);
		user.setRole(7);
		//添加超级管理员详细信息,和用户信息
		try {
			studentService.insertSelective(student,user);
			return ResultUtil.success();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultUtil.error(400, "注册失败，错误原因："+e.getMessage());
		}
		
	}
	
	
	
	/**
	* @Title: resetStatusStudent
	* @Description: TODO(更改student用户可用状态 0可用 1禁用)
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/student/resetStatusStudent",method= RequestMethod.POST)
	@ResponseBody
	public Object resetStatusStudent(@RequestBody Integer id) {
		try {
			studentService.resetStatusStudent(id);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "删除失败，错误原因："+e.getMessage());
		}
	}
	
	
	
	/**
	* @Title: updateStudent
	* @Description: TODO(更改student用户信息)
	* @param @param student
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/student/updateStudent",method= RequestMethod.POST)
	@ResponseBody
	public Object updateStudent(@RequestBody BStudent student) {
		Integer id = student.getId();
		String password = student.getPassword();
		String name = student.getName();
		String petname = student.getPetname();
		String home = student.getHome();
		Integer sex = student.getSex();
		String photo = student.getPhoto();
		Date birth = student.getBirth();
		String allergy = student.getAllergy();
		Integer diapers = student.getDiapers();
		Integer type = student.getType();
		String parentName = student.getParentName();
		String parentType = student.getParentType();
		String telOne = student.getTelOne();
		String telTwo = student.getTelTwo();
		String address = student.getAddress();
		Integer gardenId = student.getGardenId();
		Integer classId = student.getClassId();
		if (id == null) {
			return ResultUtil.error(400, "更改失败，缺少必填参数");
		}
		if (password == null || password == "") {
			return ResultUtil.error(400, "更改失败，缺少必填参数");
		}
		if (name == null || name == "") {
			return ResultUtil.error(400, "更改失败，缺少必填参数");
		}
		if (petname == null || petname == "") {
			return ResultUtil.error(400, "更改失败，缺少必填参数");
		}
		if (home == null || home == "") {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		if (sex == null) {
			return ResultUtil.error(400, "更改失败，缺少必填参数");
		} else {
			if (sex < 0 || sex >1) {
				return ResultUtil.error(400, "更改失败，参数sex错误");
			}
		}
		if (photo == null || photo == "") {
			return ResultUtil.error(400, "更改失败，缺少必填参数");
		}
		if (birth == null) {
			return ResultUtil.error(400, "更改失败，缺少必填参数");
		}
		if (allergy == null || allergy == "") {
			return ResultUtil.error(400, "更改失败，缺少必填参数");
		}
		if (diapers == null) {
			return ResultUtil.error(400, "更改失败，缺少必填参数");
		} else {
			if (diapers <0 || diapers >1) {
				return ResultUtil.error(400, "更改失败，参数diapers错误");
			}
		}
		if (type == null) {
			return ResultUtil.error(400, "更改失败，缺少必填参数");
		} else {
			if (type <0 || type >2) {
				return ResultUtil.error(400, "更改失败，参数type错误");
			}
		}
		if (parentName == null || parentName == "") {
			return ResultUtil.error(400, "更改失败，缺少必填参数");
		}
		if (parentType == null || parentType == "") {
			return ResultUtil.error(400, "更改失败，缺少必填参数");
		}
		if (telOne == null || telOne == "") {
			return ResultUtil.error(400, "更改失败，缺少必填参数");
		}
		if (telTwo == null || telTwo == "") {
			return ResultUtil.error(400, "更改失败，缺少必填参数");
		}
		if (address == null || address == "") {
			return ResultUtil.error(400, "更改失败，缺少必填参数");
		}
		if (gardenId == null) {
			return ResultUtil.error(400, "更改失败，缺少必填参数");
		}
		if (classId == null) {
			return ResultUtil.error(400, "更改失败，缺少必填参数");
		}
		
		try {
			student.setRole(7);
			studentService.updateStudent(student);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "更改失败，错误原因："+e.getMessage());
		}
		
	}
	
	
	/**
	* @Title: getStudents
	* @Description: TODO(获取所有student用户)
	* @param @param query
	* @param @param pageNum
	* @param @param pageSize
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/student/getStudents",method= RequestMethod.POST)
	@ResponseBody
	public Object getStudents(@RequestBody QueryModel queryModel) {
		String query = queryModel.getQuery();
		Integer pageNum = queryModel.getPageNum();
		Integer pageSize = queryModel.getPageSize();
		Integer gardenId = queryModel.getGardenId();
		Integer classId = queryModel.getClassId();
		if (pageNum <= 0 || pageSize <= 0) {
			return ResultUtil.error(400, "请求参数错误，提示：需要pageNum>0 pageSize>0");
		}
		PageInfo<BStudent> students;
		try {
			students = studentService.getStudents(query,pageNum,pageSize,gardenId,classId);
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("students", students);
			return ResultUtil.success(map);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "获取数据失败，失败原因："+e.getMessage());
		}
	}
	
	
	
	
	/**
	* @Title: addDoctor
	* @Description: TODO(添加doctor用户)
	* @param @param doctor
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/doctor/addDoctor",method= RequestMethod.POST)
	@ResponseBody
	public Object addDoctor(@RequestBody BDoctor doctor) {
		String username = doctor.getUsername();
		String password = doctor.getPassword();
		String name = doctor.getName();
		String email = doctor.getEmail();
		String tel = doctor.getTel();
		Integer gardenId = doctor.getGardenId();
		if (username == null || username == "") {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		if (password == null || password == "") {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		if (name == null || name == "") {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		if (email == null || email == "") {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		if (tel == null || tel == "") {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		if (gardenId == null) {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		//校验username是否重复
		List<BUser> list = userService.selectAdminByUsername(username);
		if (list.size()>0) {
			return ResultUtil.error(400, "注册失败，用户名被占用");
		}
		//填充用户信息
		BUser user = new BUser();
		user.setUsername(username);
		user.setPassword(password);
		user.setRole(6);
		//添加超级管理员详细信息,和用户信息
		try {
			doctorService.insertSelective(doctor,user);
			return ResultUtil.success();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultUtil.error(400, "注册失败，错误原因："+e.getMessage());
		}
		
	}
	
	
	/**
	* @Title: resetStatusDoctor
	* @Description: TODO(更改doctor用户可用状态 0可用 1禁用)
	* @param @param id
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/doctor/resetStatusDoctor",method= RequestMethod.POST)
	@ResponseBody
	public Object resetStatusDoctor(@RequestBody Integer id) {
		try {
			doctorService.resetStatusDoctor(id);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "删除失败，错误原因："+e.getMessage());
		}
	}
	
	
	
	/**
	* @Title: updateDoctor
	* @Description: TODO(更改doctor用户信息)
	* @param @param doctor
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/doctor/updateDoctor",method= RequestMethod.POST)
	@ResponseBody
	public Object updateDoctor(@RequestBody BDoctor doctor) {
		Integer id = doctor.getId();
		String password = doctor.getPassword();
		String name = doctor.getName();
		String email = doctor.getEmail();
		String tel = doctor.getTel();
		Integer gardenId = doctor.getGardenId();
		if (id == null) {
			return ResultUtil.error(400, "更改失败，缺少必填参数");
		}
		if (password == null || password == "") {
			return ResultUtil.error(400, "注册失败，缺少必填参数");
		}
		if (name == null || name == "") {
			return ResultUtil.error(400, "更改失败，缺少必填参数");
		}
		if (email == null || email == "") {
			return ResultUtil.error(400, "更改失败，缺少必填参数");
		}
		if (tel == null || tel == "") {
			return ResultUtil.error(400, "更改失败，缺少必填参数");
		}
		if (gardenId == null) {
			return ResultUtil.error(400, "更改失败，缺少必填参数");
		}
		try {
			doctorService.updateDoctor(doctor);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "更改失败，错误原因："+e.getMessage());
		}
		
	}
	

	
	/**
	* @Title: getDcotors
	* @Description: TODO(获取所有doctor用户)
	* @param @param query
	* @param @param pageNum
	* @param @param pageSize
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/doctor/getDoctors",method= RequestMethod.POST)
	@ResponseBody
	public Object getDoctors(@RequestBody QueryModel queryModel) {
		String query = queryModel.getQuery();
		Integer pageNum = queryModel.getPageNum();
		Integer pageSize = queryModel.getPageSize();
		if (pageNum <= 0 || pageSize <= 0) {
			return ResultUtil.error(400, "请求参数错误，提示：需要pageNum>0 pageSize>0");
		}
		PageInfo<BDoctor> doctors;
		try {
			doctors = doctorService.getDoctors(query,pageNum,pageSize);
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("doctors", doctors);
			return ResultUtil.success(map);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "获取数据失败，失败原因："+e.getMessage());
		}
	}
	
	
	
	/**
	* @Title: addGarden
	* @Description: TODO(添加幼儿园)
	* @param @param garden
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/garden/addGarden",method=RequestMethod.POST)
	@ResponseBody
	public Object addGarden(@RequestBody BGarden garden) {
		String gardenname = garden.getGardenname();
		Date createtime = garden.getCreatetime();
		String position = garden.getPosition();
		String remark = garden.getRemark();
		if (gardenname==null||gardenname=="") {
			return ResultUtil.error(400, "添加失败，缺少必填参数");
		}
		if (createtime==null) {
			return ResultUtil.error(400, "添加失败，缺少必填参数");
		}
		if (position==null||position=="") {
			return ResultUtil.error(400, "添加失败，缺少必填参数");
		}
		if (remark==null||remark=="") {
			return ResultUtil.error(400, "添加失败，缺少必填参数");
		}
		try {
			gardenService.addGarden(garden);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "添加失败，失败原因："+e.getMessage());
		}
		
		
	}
	
	
	
	
	/**
	* @Title: editGarden
	* @Description: TODO(修改幼儿园)
	* @param @param garden
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/garden/updateGarden",method=RequestMethod.POST)
	@ResponseBody
	public Object editGarden(@RequestBody BGarden garden) {
		Integer id = garden.getId();
		String gardenname = garden.getGardenname();
		Date createtime = garden.getCreatetime();
		String position = garden.getPosition();
		String remark = garden.getRemark();
		if (id==null) {
			return ResultUtil.error(400, "添加失败，缺少id");
		}
		if (gardenname==null||gardenname=="") {
			return ResultUtil.error(400, "添加失败，缺少必填参数");
		}
		if (createtime==null) {
			return ResultUtil.error(400, "添加失败，缺少必填参数");
		}
		if (position==null||position=="") {
			return ResultUtil.error(400, "添加失败，缺少必填参数");
		}
		if (remark==null||remark=="") {
			return ResultUtil.error(400, "添加失败，缺少必填参数");
		}
		try {
			gardenService.editGarden(garden);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "添加失败，失败原因："+e.getMessage());
		}
		
		
	}
	
	
	
	/**
	* @Title: getGardens
	* @Description: TODO(获取所有的garden)
	* @param @param queryModel
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/garden/getGardens",method= RequestMethod.POST)
	@ResponseBody
	public Object getGardens(@RequestBody QueryModel queryModel) {
		String query = queryModel.getQuery();
		Integer pageNum = queryModel.getPageNum();
		Integer pageSize = queryModel.getPageSize();
		if (pageNum <= 0 || pageSize <= 0) {
			return ResultUtil.error(400, "请求参数错误，提示：需要pageNum>0 pageSize>0");
		}
		PageInfo<BGarden> gardens;
		try {
			gardens = gardenService.getGardens(query,pageNum,pageSize);
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("gardens", gardens);
			return ResultUtil.success(map);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "获取数据失败，失败原因："+e.getMessage());
		}
	}
	
	
	/**
	* @Title: addClass
	* @Description: TODO(添加班级)
	* @param @param bClass
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/class/addClass",method=RequestMethod.POST)
	@ResponseBody
	public Object addClass(@RequestBody BClass bClass) {
		String className = bClass.getClassName();
		Integer gardenId = bClass.getGardenId();
		Integer size = bClass.getSize();
		if (className==null||className=="") {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (gardenId==null) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (size==null) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		try {
			classService.addClass(bClass);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "添加失败，失败原因："+e.getMessage());
		}
	}
	
	
	
	/**
	* @Title: eidtClass
	* @Description: TODO(修改班级信息)
	* @param @param bClass
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/class/updateClass",method=RequestMethod.POST)
	@ResponseBody
	public Object eidtClass(@RequestBody BClass bClass) {
		Integer id = bClass.getId();
		String className = bClass.getClassName();
		Integer gardenId = bClass.getGardenId();
		Integer size = bClass.getSize();
		if (id==null) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (className==null||className=="") {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (gardenId==null) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (size==null) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		try {
			classService.eidtClass(bClass);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "修改失败，失败原因："+e.getMessage());
		}
	}
	
	
	
	/**
	* @Title: getClasses
	* @Description: TODO(获取所有的班级)
	* @param @param queryModel
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/class/getClasses",method= RequestMethod.POST)
	@ResponseBody
	public Object getClasses(@RequestBody QueryModel queryModel) {
		String query = queryModel.getQuery();
		Integer pageNum = queryModel.getPageNum();
		Integer pageSize = queryModel.getPageSize();
		Integer gardenId = queryModel.getGardenId();
		if (pageNum <= 0 || pageSize <= 0) {
			return ResultUtil.error(400, "请求参数错误，提示：需要pageNum>0 pageSize>0");
		}
		PageInfo<BClass> classes;
		try {
			classes = classService.getClasses(query,pageNum,pageSize,gardenId);
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("classes", classes);
			return ResultUtil.success(map);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "获取数据失败，失败原因："+e.getMessage());
		}
	}
	
	
	
	
	
	
}
