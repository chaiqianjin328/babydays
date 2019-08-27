package com.babydays.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babydays.dao.BClassDao;
import com.babydays.model.BClass;
import com.babydays.model.BClassExample;
import com.babydays.model.BClassExample.Criteria;
import com.babydays.model.ListResult;
import com.babydays.service.ClassService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class ClassServiceImpl implements ClassService {

	
	@Autowired
	private BClassDao classDao;
	
	
	@Override
	public void addClass(BClass bClass) throws Exception {
		classDao.insertSelective(bClass);
	}


	@Override
	public void eidtClass(BClass bClass) throws Exception {
		classDao.updateByPrimaryKeySelective(bClass);
	}


	@Override
	public PageInfo<BClass> getClasses(String query, Integer pageNum, Integer pageSize, Integer gardenId) throws Exception {
		PageHelper.startPage(pageNum, pageSize);
		BClassExample classExample = new BClassExample();
		classExample.setOrderByClause("id desc");
		Criteria criteria = classExample.createCriteria();
		if (query != null && query != "") {
			criteria.andClassNameLike("%"+query+"%");
		}
		if (gardenId != null) {
			if (gardenId > 0) {
				criteria.andGardenIdEqualTo(gardenId);
			}
		}
		List<BClass> list = classDao.selectByExample(classExample);
		PageInfo<BClass> pageInfo = new PageInfo<BClass>(list);
		return pageInfo;
	}


	@Override
	public ListResult classList(HashMap<String, Object> valMap) {
		int pageIndex = (int) valMap.get("pageIndex");
		int pageSize = (int) valMap.get("pageSize");
		int gardenId = (int) valMap.get("gardenId");
		String query = (String) valMap.get("query");
		PageHelper.startPage(pageIndex+1,pageSize);
		BClassExample classExample = new BClassExample();
		classExample.setOrderByClause("id desc");
		Criteria criteria = classExample.createCriteria();
		if (query != null && query != "") {
			criteria.andClassNameLike("%"+query+"%");
		}
		if (gardenId > 0) {
			criteria.andGardenIdEqualTo(gardenId);
		}
		List<BClass> list = classDao.selectByExample(classExample);
		PageInfo<BClass> pageInfo = new PageInfo<BClass>(list);
		ListResult listResult = new ListResult();
		listResult.setList(pageInfo.getList());
		listResult.setTotal((int) pageInfo.getTotal());
		return listResult;
	}


	@Override
	public List<BClass> getClassesByGardenId(HashMap<String, Object> valMap) throws Exception {
		int gardenId = (int) valMap.get("gardenId");
		String query = (String) valMap.get("query");
		BClassExample classExample = new BClassExample();
		Criteria criteria = classExample.createCriteria();
		if (query != null && query != "") {
			criteria.andClassNameLike("%"+query+"%");
		}
		if (gardenId > 0) {
			criteria.andGardenIdEqualTo(gardenId);
		}
		List<BClass> list = classDao.selectByExample(classExample);
		return list;
	}


	@Override
	public BClass teacherAppointIsOrNot(Integer classId) {
		if (classId == null || classId <=0) {
			return null;
		}else {
			BClassExample classExample = new BClassExample();
			Criteria criteria = classExample.createCriteria();
			criteria.andIdEqualTo(classId);
			List<BClass> list = classDao.selectByExample(classExample);
			if (list.size()>0) {
				return list.get(0);
			}
			return null;
		}
	}


	@Override
	public void deleteClass(Integer id) throws Exception {
		BClass bClass = classDao.selectByPrimaryKey(id);
		Integer status = bClass.getStatus();
		BClass changeClass = new BClass();
		changeClass.setId(id);
		if (status == 0) {
			changeClass.setStatus(1);
		}
		if (status == 1) {
			changeClass.setStatus(0);
		}
		classDao.updateByPrimaryKeySelective(changeClass);
		
	}


	@Override
	public BClass getClassById(Integer classId) {
		BClassExample classExample = new BClassExample();
		Criteria criteria = classExample.createCriteria();
		criteria.andIdEqualTo(classId);
		List<BClass> list = classDao.selectByExample(classExample);
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}

}
