package com.babydays.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babydays.dao.BClassDao;
import com.babydays.dao.BTeacherDao;
import com.babydays.dao.BUserDao;
import com.babydays.model.BClass;
import com.babydays.model.BTeacher;
import com.babydays.model.BTeacherExample;
import com.babydays.model.BTeacherExample.Criteria;
import com.babydays.model.BUser;
import com.babydays.model.BUserExample;
import com.babydays.model.ListResult;
import com.babydays.service.TeacherService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class TeacherServiceImpl implements TeacherService {

	
	@Autowired
	private BTeacherDao teacherDao;
	
	@Autowired
	private BUserDao userDao;
	
	@Autowired
	private BClassDao classDao;
	
	
	@Override
	public BTeacher selectTeacherById(Integer detailsId) {
		BTeacherExample bTeacherExample = new BTeacherExample();
		Criteria criteria = bTeacherExample.createCriteria();
		criteria.andIdEqualTo(detailsId);
		criteria.andStatusEqualTo(0);
		criteria.andRoleBetween(4, 5);
		List<BTeacher> list = teacherDao.selectByExample(bTeacherExample);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}


	@Override
	public void insertSelective(BTeacher teacher, BUser user) throws Exception {
		Integer role = teacher.getRole();
		Integer classId = teacher.getClassId();
		teacherDao.insertSelective(teacher);
		if (role==4) {
			BClass bClass = new BClass();
			bClass.setId(classId);
			bClass.setTeacherId(teacher.getId());
			classDao.updateByPrimaryKeySelective(bClass);
		}
		user.setDetailsId(teacher.getId());
		userDao.insertSelective(user);
	}


	@Override
	public void resetStatusTeacher(Integer id) throws Exception {
		BTeacher teacher = teacherDao.selectByPrimaryKey(id);
		Integer status = teacher.getStatus();
		BTeacher newTeacher = new BTeacher();
		newTeacher.setId(teacher.getId());
		if (status==0) {
			newTeacher.setStatus(1);
		} else {
			newTeacher.setStatus(0);
		}
		teacherDao.updateByPrimaryKeySelective(newTeacher);
	}


	@Override
	public void updateTeacher(BTeacher teacher) throws Exception {
		BUserExample bUserExample = new BUserExample();
		BUserExample.Criteria criteria = bUserExample.createCriteria();
		criteria.andDetailsIdEqualTo(teacher.getId());
		criteria.andRoleEqualTo(teacher.getRole());
		List<BUser> list = userDao.selectByExample(bUserExample);
		if (list.size()>0) {
			BUser user = list.get(0);
			if (!user.getPassword().equals(teacher.getPassword())) {
				user.setPassword(teacher.getPassword());
				userDao.updateByPrimaryKeySelective(user);
			}
			teacherDao.updateByPrimaryKeySelective(teacher);
		}else {
			throw new Exception("user for teacher is not exist");
		}
		Integer role = teacher.getRole();
		Integer classId = teacher.getClassId();
		if (role==4) {
			BClass bClass = new BClass();
			bClass.setId(classId);
			bClass.setTeacherId(teacher.getId());
			classDao.updateByPrimaryKeySelective(bClass);
		}
		
	}


	@Override
	public PageInfo<BTeacher> getTeachers(String query, Integer pageNum, Integer pageSize, Integer role, Integer gardenId, Integer classId) throws Exception {
		PageHelper.startPage(pageNum, pageSize);
		BTeacherExample bTeacherExample = new BTeacherExample();
		bTeacherExample.setOrderByClause("id DESC");
		Criteria criteria = bTeacherExample.createCriteria();
		if (query == null || query == "") {
			
		} else {
			criteria.andNameLike("%"+query+"%");
		}
		if (classId != null) {
			if (classId>0) {
				criteria.andClassIdEqualTo(classId);
			}
		}else {
			if (gardenId != null) {
				if (gardenId>0) {
					criteria.andGardenIdEqualTo(gardenId);
				}
			}
		}
		criteria.andRoleEqualTo(role);
		List<BTeacher> list = teacherDao.selectByExample(bTeacherExample);
		PageInfo<BTeacher> pageInfo = new PageInfo<BTeacher>(list);
		return pageInfo;
	}


	@Override
	public PageInfo<BTeacher> getAllTeachers(String query, Integer pageNum, Integer pageSize, Integer gardenId, Integer classId) throws Exception {
		PageHelper.startPage(pageNum, pageSize);
		BTeacherExample bTeacherExample = new BTeacherExample();
		bTeacherExample.setOrderByClause("id DESC");
		Criteria criteria = bTeacherExample.createCriteria();
		if (query == null || query == "") {
			
		} else {
			criteria.andNameLike("%"+query+"%");
		}
		if (classId != null) {
			if (classId>0) {
				criteria.andClassIdEqualTo(classId);
			}
		}else {
			if (gardenId != null) {
				if (gardenId>0) {
					criteria.andGardenIdEqualTo(gardenId);
				}
			}
		}
		criteria.andRoleBetween(4, 5);
		List<BTeacher> list = teacherDao.selectByExample(bTeacherExample);
		PageInfo<BTeacher> pageInfo = new PageInfo<BTeacher>(list);
		return pageInfo;
	}


	@Override
	public ListResult teacherList(HashMap<String, Object> valMap) {
		int pageIndex = (int) valMap.get("pageIndex");
		int pageSize = (int) valMap.get("pageSize");
		int gardenId = (int) valMap.get("gardenId");
		int classId = (int) valMap.get("classId");
		String query = (String) valMap.get("query");
		PageHelper.startPage(pageIndex+1,pageSize);
		BTeacherExample teacherExample = new BTeacherExample();
		teacherExample.setOrderByClause("id DESC");
		Criteria criteria = teacherExample.createCriteria();
		if (query != null && query != "") {
			criteria.andNameLike("%"+query+"%");
		}
		if (gardenId>0) {
			criteria.andGardenIdEqualTo(gardenId);
		}
		if (classId>0) {
			criteria.andClassIdEqualTo(classId);
		}
		criteria.andRoleBetween(4, 5);
		List<BTeacher> list = teacherDao.selectByExample(teacherExample);
		PageInfo<BTeacher> pageInfo = new PageInfo<BTeacher>(list);
		ListResult listResult = new ListResult();
		listResult.setList(pageInfo.getList());
		listResult.setTotal((int) pageInfo.getTotal());
		return listResult;
	}
	
	
	
	
	
	
	

}
