package com.babydays.controller;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

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
import com.babydays.model.BValidate;
import com.babydays.service.AdminService;
import com.babydays.service.ClassService;
import com.babydays.service.DirectorService;
import com.babydays.service.DoctorService;
import com.babydays.service.GardenService;
import com.babydays.service.StudentService;
import com.babydays.service.TeacherService;
import com.babydays.service.UserService;
import com.babydays.service.ValidateService;
import com.babydays.util.MD5Util;
import com.babydays.util.ResultUtil;

/**
* @ClassName: UserLoginController
* @Description: TODO(用户控制层)
* @author chaiqianjin
* @date 2018年8月16日
*
*/
@Controller
@RequestMapping("/user")
public class UserLoginController {

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
	private ValidateService validateService;
	
	@Autowired
	private GardenService gardenService;
	
	@Autowired
	private ClassService classService;
	
	
	
	/**
	* @Title: login
	* @Description: TODO(用户登陆接口)
	* @param @param request
	* @param @param user
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Object login(HttpServletRequest request,@RequestBody BUser user){
		if (user.getUsername()!= null && user.getPassword() != null) {
			BUser existUsr = userService.selectUserByUandP(user);
			if (existUsr != null) {
				//用户存在，登陆成功
				//返回对应的用户对象
				Integer detailsId = existUsr.getDetailsId();
				if (detailsId == null) {
					ResultUtil.error(400, "登陆失败，用户详细信息绑定错误");
				}else {
					Integer role = existUsr.getRole();
					HashMap<String, Object> userMap = new HashMap<String, Object>();
					BValidate validate = new BValidate();
					if (role == 0) {
						//超级管理角色
						BAdmin admin = adminService.selectAdminById(detailsId);
						if (admin == null) {
							return ResultUtil.error(400, "用户已被停用");
						}
						userMap.put("loginUser", admin);
						String token = MD5Util.md5Password(userMap.toString());
						try {
							BValidate existValidate = validateService.selectValidateByToken(token);
							if (existValidate == null) {
								validate.setToken(token);
								validate.setUserid(admin.getUid());
								validateService.addValidate(validate);
							} else {
								if (existValidate.getUserid()==admin.getUid()) {
									existValidate.setCreatetime(new Date());
									validateService.updateValidate(existValidate);
								} else {
									validate.setToken(token);
									validate.setUserid(admin.getUid());
									validateService.addValidate(validate);
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
							return ResultUtil.error(400, "token生成异常");
						}
						userMap.put("token", token);
						return ResultUtil.success(userMap);
					}
					if (role == 1) {
						//园长角色
						BDirector director = directorService.selectDirectorById(detailsId);
						if (director == null) {
							return ResultUtil.error(400, "用户已被停用");
						}
						userMap.put("loginUser", director);
						//返回当前用户所属幼儿园的全部信息
						Integer gardenId = director.getGardenId();
						if (gardenId >= 0) {
							BGarden garden = gardenService.getGardenById(gardenId);
							userMap.put("garden", garden);
						}
						String token = MD5Util.md5Password(userMap.toString());
						try {
							BValidate existValidate = validateService.selectValidateByToken(token);
							if (existValidate == null) {
								validate.setToken(token);
								validate.setUserid(director.getUid());
								validateService.addValidate(validate);
							} else {
								if (existValidate.getUserid()==director.getUid()) {
									existValidate.setCreatetime(new Date());
									validateService.updateValidate(existValidate);
								} else {
									validate.setToken(token);
									validate.setUserid(director.getUid());
									validateService.addValidate(validate);
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
							return ResultUtil.error(400, "token生成异常");
						}
						userMap.put("token", token);
						return ResultUtil.success(userMap);
					}
					if (role == 2) {
						//教学园长
						BDirector director = directorService.selectDirectorById(detailsId);
						if (director == null) {
							return ResultUtil.error(400, "用户已被停用");
						}
						userMap.put("loginUser", director);
						//返回当前用户所属幼儿园的全部信息
						Integer gardenId = director.getGardenId();
						if (gardenId >= 0) {
							BGarden garden = gardenService.getGardenById(gardenId);
							userMap.put("garden", garden);
						}
						String token = MD5Util.md5Password(userMap.toString());
						try {
							BValidate existValidate = validateService.selectValidateByToken(token);
							if (existValidate == null) {
								validate.setToken(token);
								validate.setUserid(director.getUid());
								validateService.addValidate(validate);
							} else {
								if (existValidate.getUserid()==director.getUid()) {
									existValidate.setCreatetime(new Date());
									validateService.updateValidate(existValidate);
								} else {
									validate.setToken(token);
									validate.setUserid(director.getUid());
									validateService.addValidate(validate);
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
							return ResultUtil.error(400, "token生成异常");
						}
						userMap.put("token", token);
						return ResultUtil.success(userMap);
						
					}
					if (role == 3) {
						//行政园长
						BDirector director = directorService.selectDirectorById(detailsId);
						if (director == null) {
							return ResultUtil.error(400, "用户已被停用");
						}
						userMap.put("loginUser", director);
						//返回当前用户所属幼儿园的全部信息
						Integer gardenId = director.getGardenId();
						if (gardenId >= 0) {
							BGarden garden = gardenService.getGardenById(gardenId);
							userMap.put("garden", garden);
						}
						String token = MD5Util.md5Password(userMap.toString());
						try {
							BValidate existValidate = validateService.selectValidateByToken(token);
							if (existValidate == null) {
								validate.setToken(token);
								validate.setUserid(director.getUid());
								validateService.addValidate(validate);
							} else {
								if (existValidate.getUserid()==director.getUid()) {
									existValidate.setCreatetime(new Date());
									validateService.updateValidate(existValidate);
								} else {
									validate.setToken(token);
									validate.setUserid(director.getUid());
									validateService.addValidate(validate);
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
							return ResultUtil.error(400, "token生成异常");
						}
						userMap.put("token", token);
						return ResultUtil.success(userMap);
					}
					if (role == 4) {
						//班主任
						BTeacher teacher = teacherService.selectTeacherById(detailsId);
						if (teacher == null) {
							return ResultUtil.error(400, "用户已被停用");
						}
						userMap.put("loginUser", teacher);
						//返回当前用户所属幼儿园的全部信息
						Integer gardenId = teacher.getGardenId();
						if (gardenId >= 0) {
							BGarden garden = gardenService.getGardenById(gardenId);
							userMap.put("garden", garden);
						}
						//返回当前用户所属班级的全部信息
						Integer classId = teacher.getClassId();
						if (classId >= 0) {
							BClass bClass = classService.getClassById(classId);
							userMap.put("class", bClass);
						}
						String token = MD5Util.md5Password(userMap.toString());
						try {
							BValidate existValidate = validateService.selectValidateByToken(token);
							if (existValidate == null) {
								validate.setToken(token);
								validate.setUserid(teacher.getUid());
								validateService.addValidate(validate);
							} else {
								if (existValidate.getUserid()==teacher.getUid()) {
									existValidate.setCreatetime(new Date());
									validateService.updateValidate(existValidate);
								} else {
									validate.setToken(token);
									validate.setUserid(teacher.getUid());
									validateService.addValidate(validate);
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
							return ResultUtil.error(400, "token生成异常");
						}
						userMap.put("token", token);
						return ResultUtil.success(userMap);
					}
					if (role == 5) {
						//教师
						BTeacher teacher = teacherService.selectTeacherById(detailsId);
						if (teacher == null) {
							return ResultUtil.error(400, "用户已被停用");
						}
						userMap.put("loginUser", teacher);
						//返回当前用户所属幼儿园的全部信息
						Integer gardenId = teacher.getGardenId();
						if (gardenId >= 0) {
							BGarden garden = gardenService.getGardenById(gardenId);
							userMap.put("garden", garden);
						}
						//返回当前用户所属班级的全部信息
						Integer classId = teacher.getClassId();
						if (classId >= 0) {
							BClass bClass = classService.getClassById(classId);
							userMap.put("class", bClass);
						}
						String token = MD5Util.md5Password(userMap.toString());
						try {
							BValidate existValidate = validateService.selectValidateByToken(token);
							if (existValidate == null) {
								validate.setToken(token);
								validate.setUserid(teacher.getUid());
								validateService.addValidate(validate);
							} else {
								if (existValidate.getUserid()==teacher.getUid()) {
									existValidate.setCreatetime(new Date());
									validateService.updateValidate(existValidate);
								} else {
									validate.setToken(token);
									validate.setUserid(teacher.getUid());
									validateService.addValidate(validate);
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
							return ResultUtil.error(400, "token生成异常");
						}
						userMap.put("token", token);
						return ResultUtil.success(userMap);
					}
					if (role == 6) {
						//保健医生
						BDoctor doctor = doctorService.selectDoctorById(detailsId);
						if (doctor == null) {
							return ResultUtil.error(400, "用户已被停用");
						}
						userMap.put("loginUser", doctor);
						//返回当前用户所属幼儿园的全部信息
						Integer gardenId = doctor.getGardenId();
						if (gardenId >= 0) {
							BGarden garden = gardenService.getGardenById(gardenId);
							userMap.put("garden", garden);
						}
						String token = MD5Util.md5Password(userMap.toString());
						try {
							BValidate existValidate = validateService.selectValidateByToken(token);
							if (existValidate == null) {
								validate.setToken(token);
								validate.setUserid(doctor.getUid());
								validateService.addValidate(validate);
							} else {
								if (existValidate.getUserid()==doctor.getUid()) {
									existValidate.setCreatetime(new Date());
									validateService.updateValidate(existValidate);
								} else {
									validate.setToken(token);
									validate.setUserid(doctor.getUid());
									validateService.addValidate(validate);
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
							return ResultUtil.error(400, "token生成异常");
						}
						userMap.put("token", token);
						return ResultUtil.success(userMap);
					}
					if (role == 7) {
						//家长
						BStudent student = studentService.selectStudentById(detailsId);
						if (student == null) {
							return ResultUtil.error(400, "用户已被停用");
						}
						userMap.put("loginUser", student);
						//返回当前用户所属幼儿园的全部信息
						Integer gardenId = student.getGardenId();
						if (gardenId >= 0) {
							BGarden garden = gardenService.getGardenById(gardenId);
							userMap.put("garden", garden);
						}
						//返回当前用户所属班级的全部信息
						Integer classId = student.getClassId();
						if (classId >= 0) {
							BClass bClass = classService.getClassById(classId);
							userMap.put("class", bClass);
						}
						String token = MD5Util.md5Password(userMap.toString());
						try {
							BValidate existValidate = validateService.selectValidateByToken(token);
							if (existValidate == null) {
								validate.setToken(token);
								validate.setUserid(student.getUid());
								validateService.addValidate(validate);
							} else {
								if (existValidate.getUserid()==student.getUid()) {
									existValidate.setCreatetime(new Date());
									validateService.updateValidate(existValidate);
								} else {
									validate.setToken(token);
									validate.setUserid(student.getUid());
									validateService.addValidate(validate);
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
							return ResultUtil.error(400, "token生成异常");
						}
						userMap.put("token", token);
						return ResultUtil.success(userMap);
					}
					return ResultUtil.error(400, "登陆失败，登陆用户角色信息错误");
				}
				
			}else {
				//用户不存在，登陆失败
				return ResultUtil.error(400, "登陆失败，用户名或密码错误");
			}
		}
		return ResultUtil.error(400, "登陆失败，用户名或密码不能为空");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
