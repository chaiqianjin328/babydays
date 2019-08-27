package com.babydays.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babydays.dao.BSigninDao;
import com.babydays.model.BSignin;
import com.babydays.model.BSigninExample;
import com.babydays.model.BSigninExample.Criteria;
import com.babydays.model.ListResult;
import com.babydays.service.SigninService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class SigninServiceImpl implements SigninService {

	
	@Autowired
	private BSigninDao signinDao;
	
	
	@Override
	public ListResult signinList(HashMap<String, Object> valMap) throws ParseException {
		BSignin signin = new BSignin();
		int pageIndex = (int) valMap.get("pageIndex");
		int pageSize = (int) valMap.get("pageSize");
		int gardenId = (int) valMap.get("gardenId");
		int classId = (int) valMap.get("classId");
		String query = (String) valMap.get("query");
		String createtime = (String) valMap.get("createtime");
		PageHelper.startPage(pageIndex+1,pageSize);
		if (gardenId>0) {
			signin.setGardenId(gardenId);
		}
		if (classId>0) {
			signin.setClassId(classId);
		}
		//criteria.andTypeEqualTo(1);
		if (createtime != null && createtime != "") {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date querydate = format.parse(createtime);
			signin.setCreatetime(querydate);
		}else {
			signin.setCreatetime(new Date());
		}
		List<BSignin> list = signinDao.selectBySiginSelective(signin);
		PageInfo<BSignin> pageInfo = new PageInfo<BSignin>(list);
		ListResult listResult = new ListResult();
		listResult.setList(pageInfo.getList());
		listResult.setTotal((int) pageInfo.getTotal());
		return listResult;
	}


	@Override
	public void inGarden(BSignin signin) throws Exception {
		signinDao.insertSelective(signin);
	}


	@Override
	public void outGarden(BSignin signin) throws Exception {
		signinDao.updateByPrimaryKeySelective(signin);
	}


	@Override
	public PageInfo<BSignin> getSignins(Integer pageNum, Integer pageSize, Integer gardenId, Integer classId, String createtime) throws Exception {
		PageHelper.startPage(pageNum, pageSize);
		BSignin signin = new BSignin();
		if (gardenId != null && gardenId>0) {
			signin.setGardenId(gardenId);
		}
		if (classId != null && classId>0) {
			signin.setClassId(classId);
		}
		//criteria.andTypeEqualTo(1);
		if (createtime != null && createtime != "") {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date querydate = format.parse(createtime);
			signin.setCreatetime(querydate);
		}else {
			signin.setCreatetime(new Date());
		}
		List<BSignin> list = signinDao.selectBySiginSelective(signin);
		PageInfo<BSignin> pageInfo = new PageInfo<BSignin>(list);
		return pageInfo;
	}


	@Override
	public ListResult studenSigninList(HashMap<String, Object> valMap) throws ParseException {
		BSignin signin = new BSignin();
		int pageIndex = (int) valMap.get("pageIndex");
		int pageSize = (int) valMap.get("pageSize");
		int stuId = (int) valMap.get("stuId");
		String query = (String) valMap.get("query");
		String createtime = (String) valMap.get("createtime");
		PageHelper.startPage(pageIndex+1,pageSize);
		if (stuId>0) {
			signin.setStuId(stuId);
		}
		if (createtime != null && createtime != "") {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date querydate = format.parse(createtime);
			signin.setCreatetime(querydate);
		}else {
			signin.setCreatetime(new Date());
		}
		List<BSignin> list = signinDao.selectStudentSigninBySiginSelective(signin);
		PageInfo<BSignin> pageInfo = new PageInfo<BSignin>(list);
		ListResult listResult = new ListResult();
		listResult.setList(pageInfo.getList());
		listResult.setTotal((int) pageInfo.getTotal());
		return listResult;
	}


	@Override
	public PageInfo<BSignin> getStudentSignins(Integer pageNum, Integer pageSize, Integer stuId, String createtime)
			throws Exception {
		PageHelper.startPage(pageNum, pageSize);
		BSignin signin = new BSignin();
		if (stuId>0) {
			signin.setStuId(stuId);
		}
		if (createtime != null && createtime != "") {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date querydate = format.parse(createtime);
			signin.setCreatetime(querydate);
		}else {
			signin.setCreatetime(new Date());
		}
		List<BSignin> list = signinDao.selectStudentSigninBySiginSelective(signin);
		PageInfo<BSignin> pageInfo = new PageInfo<BSignin>(list);
		return pageInfo;
	}

}
