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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.babydays.model.BAdmin;
import com.babydays.model.BClass;
import com.babydays.model.BDirector;
import com.babydays.model.BDoctor;
import com.babydays.model.BGarden;
import com.babydays.model.BStudent;
import com.babydays.model.BTeacher;
import com.babydays.model.BUser;
import com.babydays.model.ListResult;
import com.babydays.service.AdminService;
import com.babydays.service.ClassService;
import com.babydays.service.DirectorService;
import com.babydays.service.DoctorService;
import com.babydays.service.GardenService;
import com.babydays.service.StudentService;
import com.babydays.service.TeacherService;
import com.babydays.service.UserService;
import com.babydays.util.CommonUrlUtil;
import com.babydays.util.PicUpload;
import com.babydays.util.ResultUtil;


/**
 * @author chaiqianjin
 * @ClassName: WebSystemController
 * @Description: TODO(系统管理)
 * @date 2018年10月9日
 */
@Controller
@RequestMapping("/web/system")
public class WebSystemController {

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
     * @Title: toGarden
     * @Description: TODO(跳转到幼儿园)
     * @param @return    参数
     * @return String    返回类型
     * @throws
     */
    @RequestMapping(value = "/garden", method = RequestMethod.GET)
    public String toGarden(HttpServletRequest request) {
        request.setAttribute("systemBtn", "systemBtn");
        request.setAttribute("gardenBtn", "gardenBtn");
        return "web/1-mgt-garden";
    }


    /**
     * @param @param  garden
     * @param @return 参数
     * @return Object    返回类型
     * @throws
     * @Title: addGarden
     * @Description: TODO(添加幼儿园)
     */
    @RequestMapping(value = "/addGarden", method = RequestMethod.POST)
    @ResponseBody
    public Object addGarden(HttpServletRequest request, BGarden garden) {
        try {
            String timeStr = request.getParameter("createTime");
            if (timeStr == null || timeStr == "") {
                return ResultUtil.error(400, "添加幼儿园失败");
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(timeStr);
            garden.setCreatetime(date);
            gardenService.addGarden(garden);
            return ResultUtil.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(400, "添加幼儿园失败");
        }

    }

    /**
     * @param @param  id
     * @param @return 参数
     * @return Object    返回类型
     * @throws
     * @Title: deleteGarden
     * @Description: TODO(幼儿园状态修改)
     */
    @RequestMapping(value = "/deleteGarden", method = RequestMethod.GET)
    @ResponseBody
    public Object deleteGarden(Integer id) {
        try {
            gardenService.deleteGarden(id);
            return ResultUtil.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(400, "删除幼儿园失败");
        }

    }


    /**
     * @param @param  garden
     * @param @return 参数
     * @return Object    返回类型
     * @throws
     * @Title: editGarden
     * @Description: TODO(修改幼儿园)
     */
    @RequestMapping(value = "/editGarden", method = RequestMethod.POST)
    @ResponseBody
    public Object editGarden(HttpServletRequest request, BGarden garden) {
        try {
            String timeStr = request.getParameter("createTime");
            if (timeStr == null || timeStr == "") {
                return ResultUtil.error(400, "修改幼儿园失败");
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(timeStr);
            garden.setCreatetime(date);
            gardenService.editGarden(garden);
            return ResultUtil.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(400, "修改幼儿园失败");
        }

    }


    /**
     * @param @param  request
     * @param @param  response
     * @param @param  pageIndex
     * @param @param  pageSize
     * @param @param  query
     * @param @return 参数
     * @return ListResult    返回类型
     * @throws
     * @Title: gardenList
     * @Description: TODO(获取所有的幼儿园)
     */
    @RequestMapping(value = "/gardenList", method = RequestMethod.GET)
    @ResponseBody
    public ListResult gardenList(HttpServletRequest request, HttpServletResponse response, int pageIndex, int pageSize, String query) {
        HashMap<String, Object> valMap = new HashMap<String, Object>();
        valMap.put("pageIndex", pageIndex);
        valMap.put("pageSize", pageSize);
        valMap.put("query", query);
        return gardenService.gardenList(valMap);
    }


    /**
     * @param @return 参数
     * @return String    返回类型
     * @throws
     * @Title: toClass
     * @Description: TODO(跳转到班级)
     */
    @RequestMapping(value = "/class", method = RequestMethod.GET)
    public String toClass(HttpServletRequest request) {
        List<BGarden> list = gardenService.getAllGarden();
        request.setAttribute("list", list);
        request.setAttribute("systemBtn", "systemBtn");
        request.setAttribute("classBtn", "classBtn");
        return "web/1-mgt-class";
    }


    /**
     * @param @param  bClass
     * @param @return 参数
     * @return Object    返回类型
     * @throws
     * @Title: addClass
     * @Description: TODO(添加班级class)
     */
    @RequestMapping(value = "/addClass", method = RequestMethod.POST)
    @ResponseBody
    public Object addClass(BClass bClass) {
        try {
            classService.addClass(bClass);
            return ResultUtil.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(400, "添加失败，错误原因：" + e.getMessage());
        }
    }


    /**
     * @param @param  id
     * @param @return 参数
     * @return Object    返回类型
     * @throws
     * @Title: deleteClass
     * @Description: TODO(班级状态修改)
     */
    @RequestMapping(value = "/deleteClass", method = RequestMethod.GET)
    @ResponseBody
    public Object deleteClass(Integer id) {
        try {
            classService.deleteClass(id);
            return ResultUtil.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(400, "删除班级失败");
        }

    }


    /**
     * @param @param  bClass
     * @param @return 参数
     * @return Object    返回类型
     * @throws
     * @Title: editClass
     * @Description: TODO(修改班级class)
     */
    @RequestMapping(value = "/editClass", method = RequestMethod.POST)
    @ResponseBody
    public Object editClass(BClass bClass) {
        try {
            classService.eidtClass(bClass);
            return ResultUtil.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(400, "修改失败，错误原因：" + e.getMessage());
        }
    }


    /**
     * @param @param  request
     * @param @param  response
     * @param @param  pageIndex
     * @param @param  pageSize
     * @param @param  query
     * @param @return 参数
     * @return ListResult    返回类型
     * @throws
     * @Title: classList
     * @Description: TODO(返回所有的班级)
     */
    @RequestMapping(value = "/classList", method = RequestMethod.GET)
    @ResponseBody
    public ListResult classList(HttpServletRequest request, HttpServletResponse response, int pageIndex, int pageSize, String query, int gardenId) {
        HashMap<String, Object> valMap = new HashMap<String, Object>();
        valMap.put("pageIndex", pageIndex);
        valMap.put("pageSize", pageSize);
        valMap.put("query", query);
        valMap.put("gardenId", gardenId);
        return classService.classList(valMap);
    }


    @RequestMapping(value = "/getClassesByGardenId", method = RequestMethod.GET)
    @ResponseBody
    public Object getClassesByGardenId(HttpServletRequest request, HttpServletResponse response, String query, int gardenId) {
        HashMap<String, Object> valMap = new HashMap<String, Object>();
        valMap.put("query", query);
        valMap.put("gardenId", gardenId);
        try {
            List<BClass> classes = classService.getClassesByGardenId(valMap);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("classes", classes);
            return ResultUtil.success(classes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(400, "获取数据失败，失败原因：" + e.getMessage());
        }
    }


    /**
     * @param @return 参数
     * @return String    返回类型
     * @throws
     * @Title: toAdmin
     * @Description: TODO(跳转到管理员管理页面)
     */
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String toAdmin(HttpServletRequest request) {
        request.setAttribute("systemBtn", "systemBtn");
        request.setAttribute("adminBtn", "adminBtn");
        return "web/1-mgt-admin";
    }


    /**
     * @param @param  admin
     * @param @return 参数
     * @return Object    返回类型
     * @throws
     * @Title: addAdmin
     * @Description: TODO(添加admin用户)
     */
    @RequestMapping(value = "/addAdmin", method = RequestMethod.POST)
    @ResponseBody
    public Object addAdmin(BAdmin admin) {
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
        if (list.size() > 0) {
            return ResultUtil.error(401, "注册失败，用户名被占用");
        }
        //填充用户信息
        BUser user = new BUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(0);
        //添加超级管理员详细信息,和用户信息
        try {
            adminService.insertSelective(admin, user);
            return ResultUtil.success();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return ResultUtil.error(400, "注册失败，错误原因：" + e.getMessage());
        }
    }


    /**
     * @param @return 参数
     * @return Object    返回类型
     * @throws
     * @Title: resetStatusAdmin
     * @Description: TODO(更改admin用户可用状态 0可用 1禁用)
     */
    @RequestMapping(value = "resetStatusAdmin", method = RequestMethod.POST)
    @ResponseBody
    public Object resetStatusAdmin(Integer id) {
        try {
            adminService.resetStatusAdmin(id);
            return ResultUtil.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(400, "删除失败，错误原因：" + e.getMessage());
        }
    }


    /**
     * @param @param  admin
     * @param @return 参数
     * @return Object    返回类型
     * @throws
     * @Title: updateAdmin
     * @Description: TODO(更改admin用户信息)
     */
    @RequestMapping(value = "/updateAdmin", method = RequestMethod.POST)
    @ResponseBody
    public Object updateAdmin(BAdmin admin) {
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
            return ResultUtil.error(400, "更改失败，错误原因：" + e.getMessage());
        }

    }


    /**
     * @param @param  request
     * @param @param  response
     * @param @param  pageIndex
     * @param @param  pageSize
     * @param @param  query
     * @param @return 参数
     * @return ListResult    返回类型
     * @throws
     * @Title: adminList
     * @Description: TODO(获取所有的admin用户)
     */
    @RequestMapping(value = "/adminList", method = RequestMethod.GET)
    @ResponseBody
    public ListResult adminList(HttpServletRequest request, HttpServletResponse response, int pageIndex, int pageSize, String query) {
        HashMap<String, Object> valMap = new HashMap<String, Object>();
        valMap.put("pageIndex", pageIndex);
        valMap.put("pageSize", pageSize);
        valMap.put("query", query);
        return adminService.adminList(valMap);
    }


    /**
     * @param @return 参数
     * @return String    返回类型
     * @throws
     * @Title: toDirector
     * @Description: TODO(跳转到园长管理页面)
     */
    @RequestMapping(value = "/director", method = RequestMethod.GET)
    public String toDirector(HttpServletRequest request) {
        List<BGarden> list = gardenService.getAllGarden();
        request.setAttribute("list", list);
        request.setAttribute("systemBtn", "systemBtn");
        request.setAttribute("directorBtn", "directorBtn");
        return "web/1-mgt-director";
    }


    /**
     * @param @param  director
     * @param @return 参数
     * @return Object    返回类型
     * @throws
     * @Title: addDirector
     * @Description: TODO(添加director用户 ， 根据参数role的实际值判断是那类用户 < 1 : 园长 ； 2 : 教学园长 ； 3 : 行政园长 ； >)
     */
    @RequestMapping(value = "/addDirector", method = RequestMethod.POST)
    @ResponseBody
    public Object addDirector(BDirector director) {
        String username = director.getUsername();
        String password = director.getPassword();
        Integer role = director.getRole();
        String name = director.getName();
        String email = director.getEmail();
        String tel = director.getTel();
        Integer gardenId = director.getGardenId();
        if (username == null || username == "") {
            return ResultUtil.error(400, "注册失败，缺少必填参数");
        }
        if (password == null || password == "") {
            return ResultUtil.error(400, "注册失败，缺少必填参数");
        }
        if (role == null) {
            return ResultUtil.error(400, "注册失败，缺少必填参数");
        } else {
            if (role > 3 || role < 1) {
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
        if (gardenId == null || gardenId <= 0) {
            return ResultUtil.error(400, "注册失败，缺少必填参数");
        }
        //校验校长是否被指定
        if (role == 1) {
            BGarden existGarden = gardenService.directorAppointIsOrNot(gardenId);
            if (existGarden == null) {
                return ResultUtil.error(400, "注册失败，幼儿园不存在");
            } else {
                String directorName = existGarden.getDirectorName();
                if (directorName != null && directorName != "") {
                    return ResultUtil.error(400, "注册失败，该幼儿园园长已被指定");
                }
            }

        }
        //校验username是否重复
        List<BUser> list = userService.selectAdminByUsername(username);
        if (list.size() > 0) {
            return ResultUtil.error(401, "注册失败，用户名被占用");
        }
        //填充用户信息
        BUser user = new BUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        //添加director详细信息,和用户信息
        try {
            directorService.insertSelective(director, user);
            return ResultUtil.success();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return ResultUtil.error(400, "注册失败，错误原因：" + e.getMessage());
        }

    }


    /**
     * @param @param  id
     * @param @return 参数
     * @return Object    返回类型
     * @throws
     * @Title: resetStatusDirector
     * @Description: TODO(更改director用户可用状态 0可用 1禁用)
     */
    @RequestMapping(value = "/resetStatusDirector", method = RequestMethod.POST)
    @ResponseBody
    public Object resetStatusDirector(Integer id) {
        try {
            directorService.resetStatusDirector(id);
            return ResultUtil.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(400, "删除失败，错误原因：" + e.getMessage());
        }
    }


    /**
     * @param @param  director
     * @param @return 参数
     * @return Object    返回类型
     * @throws
     * @Title: updateDirector
     * @Description: TODO(更改director用户详细信息)
     */
    @RequestMapping(value = "/updateDirector", method = RequestMethod.POST)
    @ResponseBody
    public Object updateDirector(BDirector director) {
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
        } else {
            if (role > 3 || role < 1) {
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
            } else {
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
            return ResultUtil.error(400, "更改失败，错误原因：" + e.getMessage());
        }

    }


    /**
     * @param @param  request
     * @param @param  response
     * @param @param  pageIndex
     * @param @param  pageSize
     * @param @param  query
     * @param @param  gardenId
     * @param @return 参数
     * @return ListResult    返回类型
     * @throws
     * @Title: directorList
     * @Description: TODO(获取所有的director用户)
     */
    @RequestMapping(value = "/directorList", method = RequestMethod.GET)
    @ResponseBody
    public ListResult directorList(HttpServletRequest request, HttpServletResponse response, int pageIndex, int pageSize, String query, int gardenId) {
        HashMap<String, Object> valMap = new HashMap<String, Object>();
        valMap.put("pageIndex", pageIndex);
        valMap.put("pageSize", pageSize);
        valMap.put("query", query);
        valMap.put("gardenId", gardenId);
        return directorService.directorList(valMap);
    }


    /**
     * @param @return 参数
     * @return String    返回类型
     * @throws
     * @Title: toTeacher
     * @Description: TODO(跳转到教师页面)
     */
    @RequestMapping(value = "/teacher", method = RequestMethod.GET)
    public String toTeacher(HttpServletRequest request) {
        List<BGarden> list = gardenService.getAllGarden();
        request.setAttribute("list", list);
        request.setAttribute("systemBtn", "systemBtn");
        request.setAttribute("teacherBtn", "teacherBtn");
        return "web/1-mgt-teacher";
    }


    /**
     * @param @param  teacher
     * @param @return 参数
     * @return Object    返回类型
     * @throws
     * @Title: addTeacher
     * @Description: TODO(添加teacher用户 ， 根据参数role的实际值判断是那类用户 < 4 : 班主任 ； 5教师 ； >)
     */
    @RequestMapping(value = "/addTeacher", method = RequestMethod.POST)
    @ResponseBody
    public Object addTeacher(BTeacher teacher) {
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
        } else {
            if (role > 5 || role < 4) {
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
        if (classId == null || classId <= 0) {
            return ResultUtil.error(400, "注册失败，缺少必填参数");
        }
        //校验班主任是否被指定
        if (role == 4) {
            BClass existClass = classService.teacherAppointIsOrNot(classId);
            if (existClass == null) {
                return ResultUtil.error(400, "注册失败，班级不存在");
            } else {
                String teacherName = existClass.getTeacherName();
                if (teacherName != null && teacherName != "") {
                    return ResultUtil.error(400, "注册失败，该班级班主任已被指定");
                }
            }
        }
        //校验username是否重复
        List<BUser> list = userService.selectAdminByUsername(username);
        if (list.size() > 0) {
            return ResultUtil.error(401, "注册失败，用户名被占用");
        }
        //填充用户信息
        BUser user = new BUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        //添加teacher详细信息,和用户信息
        try {
            teacherService.insertSelective(teacher, user);
            return ResultUtil.success();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return ResultUtil.error(400, "注册失败，错误原因：" + e.getMessage());
        }

    }


    /**
     * @param @param  id
     * @param @return 参数
     * @return Object    返回类型
     * @throws
     * @Title: resetStatusTeacher
     * @Description: TODO(更改teacher用户可用状态 0可用 1禁用)
     */
    @RequestMapping(value = "/resetStatusTeacher", method = RequestMethod.POST)
    @ResponseBody
    public Object resetStatusTeacher(Integer id) {
        try {
            teacherService.resetStatusTeacher(id);
            return ResultUtil.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(400, "删除失败，错误原因：" + e.getMessage());
        }
    }


    /**
     * @param @param  teacher
     * @param @return 参数
     * @return Object    返回类型
     * @throws
     * @Title: updateTeacher
     * @Description: TODO(更改teacher用户详细信息)
     */
    @RequestMapping(value = "/updateTeacher", method = RequestMethod.POST)
    @ResponseBody
    public Object updateTeacher(BTeacher teacher) {
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
        } else {
            if (role > 5 || role < 4) {
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
            } else {
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
            return ResultUtil.error(400, "更改失败，错误原因：" + e.getMessage());
        }

    }


    /**
     * @param @param  request
     * @param @param  response
     * @param @param  pageIndex
     * @param @param  pageSize
     * @param @param  query
     * @param @param  gardenId
     * @param @param  classId
     * @param @return 参数
     * @return ListResult    返回类型
     * @throws
     * @Title: teacherList
     * @Description: TODO(获取所有的teacher用户)
     */
    @RequestMapping(value = "/teacherList", method = RequestMethod.GET)
    @ResponseBody
    public ListResult teacherList(HttpServletRequest request, HttpServletResponse response, int pageIndex, int pageSize, String query, int gardenId, int classId) {
        HashMap<String, Object> valMap = new HashMap<String, Object>();
        valMap.put("pageIndex", pageIndex);
        valMap.put("pageSize", pageSize);
        valMap.put("query", query);
        valMap.put("gardenId", gardenId);
        valMap.put("classId", classId);
        return teacherService.teacherList(valMap);
    }


    /**
     * @param @return 参数
     * @return String    返回类型
     * @throws
     * @Title: toStudent
     * @Description: TODO(跳转到学生管理页面)
     */
    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public String toStudent(HttpServletRequest request) {
        List<BGarden> list = gardenService.getAllGarden();
        request.setAttribute("list", list);
        request.setAttribute("systemBtn", "systemBtn");
        request.setAttribute("studentBtn", "studentBtn");
        return "web/1-mgt-student";
    }


    /**
     * @param @param  student
     * @param @return 参数
     * @return Object    返回类型
     * @throws
     * @Title: addStudent
     * @Description: TODO(添加student用户)
     */
    @RequestMapping(value = "/addStudent", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object addStudent(BStudent student, HttpServletRequest request, @RequestParam(value = "studentPic", required = false) MultipartFile studentPic) {
        try {
            String timeStr = request.getParameter("birthStr");
            if (timeStr == null || timeStr == "") {
                return ResultUtil.error(400, "注册失败，缺少必填参数");
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(timeStr);
            student.setBirth(date);
        } catch (ParseException e1) {
            e1.printStackTrace();
            return ResultUtil.error(400, "注册失败，出事日期转换错误");
        }
        String username = student.getUsername();
        String password = student.getPassword();
        String name = student.getName();
        String petname = student.getPetname();
        String home = student.getHome();
        Integer sex = student.getSex();
        Date birth = student.getBirth();
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
            if (sex < 0 || sex > 1) {
                return ResultUtil.error(400, "注册失败，参数sex错误");
            }
        }
        if (birth == null) {
            return ResultUtil.error(400, "注册失败，缺少必填参数");
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
        if (list.size() > 0) {
            return ResultUtil.error(401, "注册失败，用户名被占用");
        }
        //填充用户信息
        BUser user = new BUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(7);
        //添加超级管理员详细信息,和用户信息
        try {
            //保存地址
            String position = CommonUrlUtil.UPLOAD_PICTURE_PATH;
            //图片访问路径前缀
            String picPath = CommonUrlUtil.PREFIX_PICTURE_URL;
            if (studentPic.getSize() > 0) {
                String picUrl = PicUpload.picUploadAndReturnURL(studentPic, request, position);
                System.out.println(picUrl);
                student.setPhoto(picPath + picUrl);
            }

            studentService.insertSelective(student, user);
            return ResultUtil.success();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return ResultUtil.error(400, "注册失败，错误原因：" + e.getMessage());
        }

    }


    /**
     * @param @return 参数
     * @return Object    返回类型
     * @throws
     * @Title: resetStatusStudent
     * @Description: TODO(更改student用户可用状态 0可用 1禁用)
     */
    @RequestMapping(value = "/resetStatusStudent", method = RequestMethod.POST)
    @ResponseBody
    public Object resetStatusStudent(Integer id) {
        try {
            studentService.resetStatusStudent(id);
            return ResultUtil.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(400, "删除失败，错误原因：" + e.getMessage());
        }
    }


    /**
     * @param @param  student
     * @param @return 参数
     * @return Object    返回类型
     * @throws
     * @Title: updateStudent
     * @Description: TODO(更改student用户信息)
     */
    @RequestMapping(value = "/updateStudent", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object updateStudent(BStudent student, HttpServletRequest request, @RequestParam(value = "studentPic", required = false) MultipartFile studentPic) {
        try {
            String timeStr = request.getParameter("birthStr");
            if (timeStr == null || timeStr == "") {
                return ResultUtil.error(400, "修改失败，缺少必填参数");
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(timeStr);
            student.setBirth(date);
        } catch (ParseException e1) {
            e1.printStackTrace();
            return ResultUtil.error(400, "修改失败，出事日期转换错误");
        }
        Integer id = student.getId();
        String password = student.getPassword();
        String name = student.getName();
        String petname = student.getPetname();
        String home = student.getHome();
        Integer sex = student.getSex();
        String photo = student.getPhoto();
        Date birth = student.getBirth();
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
            if (sex < 0 || sex > 1) {
                return ResultUtil.error(400, "更改失败，参数sex错误");
            }
        }
        if (birth == null) {
            return ResultUtil.error(400, "更改失败，缺少必填参数");
        }
        if (parentName == null || parentName == "") {
            return ResultUtil.error(400, "更改失败，缺少必填参数");
        }
        if (parentType == null || parentType.equals("")) {
            return ResultUtil.error(400, "更改失败，缺少必填参数");
        }
        if (telOne == null || telOne.equals("")) {
            return ResultUtil.error(400, "更改失败，缺少必填参数");
        }
        if (telTwo == null || telTwo.equals("")) {
            return ResultUtil.error(400, "更改失败，缺少必填参数");
        }
        if (address == null || address.equals("")) {
            return ResultUtil.error(400, "更改失败，缺少必填参数");
        }
        if (gardenId == null) {
            return ResultUtil.error(400, "更改失败，缺少必填参数");
        }
        if (classId == null) {
            return ResultUtil.error(400, "更改失败，缺少必填参数");
        }

        try {
            //服务器保存地址
            String position = CommonUrlUtil.UPLOAD_PICTURE_PATH;
            //图片访问路径前缀
            String picPath = CommonUrlUtil.PREFIX_PICTURE_URL;
            if (studentPic.getSize() > 0) {
                String picUrl = PicUpload.picUploadAndReturnURL(studentPic, request, position);
                System.out.println(picUrl);
                student.setPhoto(picPath + picUrl);
            }
            student.setRole(7);
            studentService.updateStudent(student);
            return ResultUtil.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(400, "更改失败，错误原因：" + e.getMessage());
        }

    }


    /**
     * @param @param  request
     * @param @param  response
     * @param @param  pageIndex
     * @param @param  pageSize
     * @param @param  query
     * @param @param  gardenId
     * @param @param  classId
     * @param @return 参数
     * @return ListResult    返回类型
     * @throws
     * @Title: studentList
     * @Description: TODO(获取所有的student用户)
     */
    @RequestMapping(value = "/studentList", method = RequestMethod.GET)
    @ResponseBody
    public ListResult studentList(HttpServletRequest request, HttpServletResponse response, int pageIndex, int pageSize, String query, int gardenId, int classId, int type) {
        HashMap<String, Object> valMap = new HashMap<String, Object>();
        valMap.put("pageIndex", pageIndex);
        valMap.put("pageSize", pageSize);
        valMap.put("query", query);
        valMap.put("gardenId", gardenId);
        valMap.put("classId", classId);
        valMap.put("type", type);
        return studentService.studentList(valMap);
    }


    /**
     * @param @return 参数
     * @return String    返回类型
     * @throws
     * @Title: toDoctor
     * @Description: TODO(跳转到园长管理页面)
     */
    @RequestMapping(value = "/doctor", method = RequestMethod.GET)
    public String toDoctor(HttpServletRequest request) {
        List<BGarden> list = gardenService.getAllGarden();
        request.setAttribute("list", list);
        request.setAttribute("systemBtn", "systemBtn");
        request.setAttribute("doctorBtn", "doctorBtn");
        return "/web/1-mgt-doctor";
    }


    /**
     * @param @param  doctor
     * @param @return 参数
     * @return Object    返回类型
     * @throws
     * @Title: addDoctor
     * @Description: TODO(添加doctor用户)
     */
    @RequestMapping(value = "/addDoctor", method = RequestMethod.POST)
    @ResponseBody
    public Object addDoctor(BDoctor doctor) {
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
        if (list.size() > 0) {
            return ResultUtil.error(400, "注册失败，用户名被占用");
        }
        //填充用户信息
        BUser user = new BUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(6);
        //添加超级管理员详细信息,和用户信息
        try {
            doctorService.insertSelective(doctor, user);
            return ResultUtil.success();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return ResultUtil.error(400, "注册失败，错误原因：" + e.getMessage());
        }

    }


    /**
     * @param @param  id
     * @param @return 参数
     * @return Object    返回类型
     * @throws
     * @Title: resetStatusDoctor
     * @Description: TODO(更改doctor用户可用状态 0可用 1禁用)
     */
    @RequestMapping(value = "/resetStatusDoctor", method = RequestMethod.POST)
    @ResponseBody
    public Object resetStatusDoctor(Integer id) {
        try {
            doctorService.resetStatusDoctor(id);
            return ResultUtil.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(400, "删除失败，错误原因：" + e.getMessage());
        }
    }


    /**
     * @param @param  doctor
     * @param @return 参数
     * @return Object    返回类型
     * @throws
     * @Title: updateDoctor
     * @Description: TODO(更改doctor用户信息)
     */
    @RequestMapping(value = "/updateDoctor", method = RequestMethod.POST)
    @ResponseBody
    public Object updateDoctor(BDoctor doctor) {
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
            return ResultUtil.error(400, "更改失败，错误原因：" + e.getMessage());
        }

    }


    /**
     * @param @param  request
     * @param @param  response
     * @param @param  pageIndex
     * @param @param  pageSize
     * @param @param  query
     * @param @param  gardenId
     * @param @return 参数
     * @return ListResult    返回类型
     * @throws
     * @Title: doctorList
     * @Description: TODO(获取所有的doctor用户)
     */
    @RequestMapping(value = "/doctorList", method = RequestMethod.GET)
    @ResponseBody
    public ListResult doctorList(HttpServletRequest request, HttpServletResponse response, int pageIndex, int pageSize, String query, int gardenId) {
        HashMap<String, Object> valMap = new HashMap<String, Object>();
        valMap.put("pageIndex", pageIndex);
        valMap.put("pageSize", pageSize);
        valMap.put("query", query);
        valMap.put("gardenId", gardenId);
        return doctorService.doctorList(valMap);
    }


}
