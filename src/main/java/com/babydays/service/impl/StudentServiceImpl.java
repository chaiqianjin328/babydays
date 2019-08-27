package com.babydays.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babydays.dao.BClassDao;
import com.babydays.dao.BStudentDao;
import com.babydays.dao.BUserDao;
import com.babydays.model.BClass;
import com.babydays.model.BStudent;
import com.babydays.model.BStudentExample;
import com.babydays.model.BStudentExample.Criteria;
import com.babydays.model.BUser;
import com.babydays.model.BUserExample;
import com.babydays.model.ListResult;
import com.babydays.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private BStudentDao studentDao;
	
	@Autowired
	private BUserDao userDao;
	
	@Autowired
	private BClassDao classDao;
	
	
	@Override
	public BStudent selectStudentById(Integer detailsId) {
		BStudentExample bStudentExample = new BStudentExample();
		Criteria criteria = bStudentExample.createCriteria();
		criteria.andIdEqualTo(detailsId);
		criteria.andStatusEqualTo(0);
		List<BStudent> list = studentDao.selectByExample(bStudentExample);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}


	@Override
	public void insertSelective(BStudent student, BUser user) throws Exception {
		Integer classId = student.getClassId();
		BClass bClass = classDao.selectByPrimaryKey(classId);
		if (bClass!=null) {
			BClass newbClass = new BClass();
			newbClass.setId(bClass.getId());
			newbClass.setRealSize(bClass.getRealSize()+1);
			classDao.updateByPrimaryKeySelective(newbClass);
		}
		studentDao.insertSelective(student);
		user.setDetailsId(student.getId());
		userDao.insertSelective(user);
	}


	@Override
	public void resetStatusStudent(Integer id) throws Exception {
		BStudent student = studentDao.selectByPrimaryKey(id);
		Integer status = student.getStatus();
		BStudent newStudent = new BStudent();
		newStudent.setId(student.getId());
		if (status==0) {
			newStudent.setStatus(1);
		} else {
			newStudent.setStatus(0);
		}
		studentDao.updateByPrimaryKeySelective(newStudent);
	}


	@Override
	public void updateStudent(BStudent student) throws Exception {
		BUserExample bUserExample = new BUserExample();
		BUserExample.Criteria criteria = bUserExample.createCriteria();
		criteria.andDetailsIdEqualTo(student.getId());
		criteria.andRoleEqualTo(student.getRole());
		List<BUser> list = userDao.selectByExample(bUserExample);
		if (list.size()>0) {
			BUser user = list.get(0);
			if (!user.getPassword().equals(student.getPassword())) {
				user.setPassword(student.getPassword());
				userDao.updateByPrimaryKeySelective(user);
			}
			studentDao.updateByPrimaryKeySelective(student);
		} else {
			throw new Exception("user for student is not exist");
		}
		
	}


	@Override
	public PageInfo<BStudent> getStudents(String query, Integer pageNum, Integer pageSize, Integer gardenId, Integer classId) throws Exception {
		PageHelper.startPage(pageNum, pageSize);
		BStudentExample bStudentExample = new BStudentExample();
		bStudentExample.setOrderByClause("id DESC");
		Criteria criteria = bStudentExample.createCriteria();
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
		List<BStudent> list = studentDao.selectByExample(bStudentExample);
		PageInfo<BStudent> pageInfo = new PageInfo<BStudent>(list);
		return pageInfo;
	}


	@Override
	public ListResult studentList(HashMap<String, Object> valMap) {
		int pageIndex = (int) valMap.get("pageIndex");
		int pageSize = (int) valMap.get("pageSize");
		int gardenId = (int) valMap.get("gardenId");
		int classId = (int) valMap.get("classId");
		int type = (int) valMap.get("type");
		String query = (String) valMap.get("query");
		PageHelper.startPage(pageIndex+1,pageSize);
		BStudentExample studentExample = new BStudentExample();
		studentExample.setOrderByClause("id DESC");
		Criteria criteria = studentExample.createCriteria();
		if (query != null && query != "") {
			criteria.andNameLike("%"+query+"%");
		}
		if (gardenId>0) {
			criteria.andGardenIdEqualTo(gardenId);
		}
		if (classId>0) {
			criteria.andClassIdEqualTo(classId);
		}
		if (type>=0) {
			criteria.andTypeEqualTo(type);
		}
		List<BStudent> list = studentDao.selectByExample(studentExample);
		PageInfo<BStudent> pageInfo = new PageInfo<BStudent>(list);
		ListResult listResult = new ListResult();
		listResult.setList(pageInfo.getList());
		listResult.setTotal((int) pageInfo.getTotal());
		return listResult;
	}


	
	

}
