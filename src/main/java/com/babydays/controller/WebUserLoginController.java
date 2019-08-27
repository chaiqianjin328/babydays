package com.babydays.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.babydays.model.BAdmin;
import com.babydays.model.BDirector;
import com.babydays.model.BDoctor;
import com.babydays.model.BStudent;
import com.babydays.model.BTeacher;
import com.babydays.model.BUser;
import com.babydays.model.BValidate;
import com.babydays.service.AdminService;
import com.babydays.service.DirectorService;
import com.babydays.service.DoctorService;
import com.babydays.service.StudentService;
import com.babydays.service.TeacherService;
import com.babydays.service.UserService;
import com.babydays.util.ResultUtil;
import com.babydays.util.ValidateCode;
@Controller
@RequestMapping("/web/user")
public class WebUserLoginController {

	
	
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
	
	
	
	@RequestMapping(value="/toLogin",method=RequestMethod.GET)
	public String toLogin() {
		return "web/login";
	}
	
	
	
	/**
	* @Title: logout
	* @Description: TODO(用户退出登陆状态)
	* @param @return    参数
	* @return String    返回类型
	* @throws
	*/
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute("loginUser");
		return "redirect:web/user/toLogin";
	}
	
	
	
	
    /**
    * @Title: validateCode
    * @Description: TODO(验证码生成)
    * @param @param request
    * @param @param response
    * @param @return
    * @param @throws Exception    参数
    * @return String    返回类型
    * @throws
    */
    @RequestMapping(value="/validateCode", method = RequestMethod.GET)  
    public String validateCode(HttpServletRequest request,HttpServletResponse response) throws Exception{  
        // 设置响应的类型格式为图片格式  
        response.setContentType("image/jpeg");  
        //禁止图像缓存。  
        response.setHeader("Pragma", "no-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
      
        HttpSession session = request.getSession();  
        System.out.println("saveSession:"+request.getRequestURL());
        ValidateCode vCode = new ValidateCode(100,40,4,100);  
        session.setAttribute("code", vCode.getCode()); 
        System.out.println((String) session.getAttribute("code"));
        vCode.write(response.getOutputStream());  
        return null;  
    } 
	
	
	
	
	
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
	public Object login(HttpServletRequest request,BUser user){
		String code = request.getParameter("code");
		String existCode = (String) request.getSession().getAttribute("code");
		if (!existCode.equalsIgnoreCase(code)) {
			return ResultUtil.error(400, "登陆失败，验证码错误");
		}
		if (user.getUsername()!= null && user.getPassword() != null) {
			BUser existUsr = userService.selectUserByUandP(user);
			HttpSession session = request.getSession();
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
						session.setAttribute("loginUser", admin);
						return ResultUtil.success(userMap);
					}
					if (role == 1 || role == 2 || role == 3) {
						//园长角色 教学园长 行政园长
						BDirector director = directorService.selectDirectorById(detailsId);
						if (director == null) {
							return ResultUtil.error(400, "用户已被停用");
						}
						userMap.put("loginUser", director);
						session.setAttribute("loginUser", director);
						return ResultUtil.success(userMap);
					}
					if (role == 4 || role == 5) {
						//班主任
						BTeacher teacher = teacherService.selectTeacherById(detailsId);
						if (teacher == null) {
							return ResultUtil.error(400, "用户已被停用");
						}
						userMap.put("loginUser", teacher);
						session.setAttribute("loginUser", teacher);
						return ResultUtil.success(userMap);
					}
					if (role == 6) {
						//保健医生
						BDoctor doctor = doctorService.selectDoctorById(detailsId);
						if (doctor == null) {
							return ResultUtil.error(400, "用户已被停用");
						}
						userMap.put("loginUser", doctor);
						session.setAttribute("loginUser", doctor);
						return ResultUtil.success(userMap);
					}
					if (role == 7) {
						//家长
						BStudent student = studentService.selectStudentById(detailsId);
						if (student == null) {
							return ResultUtil.error(400, "用户已被停用");
						}
						userMap.put("loginUser", student);
						session.setAttribute("loginUser", student);
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
