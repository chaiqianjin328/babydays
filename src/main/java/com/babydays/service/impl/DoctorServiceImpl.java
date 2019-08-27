package com.babydays.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babydays.dao.BDoctorDao;
import com.babydays.dao.BUserDao;
import com.babydays.model.BDoctor;
import com.babydays.model.BDoctorExample;
import com.babydays.model.BDoctorExample.Criteria;
import com.babydays.model.BUser;
import com.babydays.model.BUserExample;
import com.babydays.model.ListResult;
import com.babydays.service.DoctorService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	private BDoctorDao doctorDao;
	
	@Autowired
	private BUserDao userDao;
	
	
	@Override
	public BDoctor selectDoctorById(Integer detailsId) {
		BDoctorExample bDoctorExample = new BDoctorExample();
		Criteria criteria = bDoctorExample.createCriteria();
		criteria.andIdEqualTo(detailsId);
		criteria.andStatusEqualTo(0);
		List<BDoctor> list = doctorDao.selectByExample(bDoctorExample);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}


	@Override
	public void insertSelective(BDoctor doctor, BUser user) throws Exception {
		doctorDao.insertSelective(doctor);
		user.setDetailsId(doctor.getId());
		userDao.insertSelective(user);
	}


	@Override
	public void resetStatusDoctor(Integer id) throws Exception {
		BDoctor doctor = doctorDao.selectByPrimaryKey(id);
		Integer status = doctor.getStatus();
		BDoctor newDoctor = new BDoctor();
		newDoctor.setId(doctor.getId());
		if (status==0) {
			newDoctor.setStatus(1);
		} else {
			newDoctor.setStatus(0);
		}
		doctorDao.updateByPrimaryKeySelective(newDoctor);
	}


	@Override
	public void updateDoctor(BDoctor doctor) throws Exception {
		BUserExample bUserExample = new BUserExample();
		BUserExample.Criteria criteria = bUserExample.createCriteria();
		criteria.andDetailsIdEqualTo(doctor.getId());
		criteria.andRoleEqualTo(6);
		List<BUser> list = userDao.selectByExample(bUserExample);
		if (list.size()>0) {
			BUser user = list.get(0);
			if (!user.getPassword().equals(doctor.getPassword())) {
				user.setPassword(doctor.getPassword());
				userDao.updateByPrimaryKeySelective(user);
			}
			doctorDao.updateByPrimaryKeySelective(doctor);
		} else {
			throw new Exception("user for doctor is not exist");
		}
		
	}


	@Override
	public PageInfo<BDoctor> getDoctors(String query, Integer pageNum, Integer pageSize) throws Exception {
		PageHelper.startPage(pageNum, pageSize);
		BDoctorExample bDoctorExample = new BDoctorExample();
		bDoctorExample.setOrderByClause("id DESC");
		if (query == null || query == "") {
			
		} else {
			Criteria criteria = bDoctorExample.createCriteria();
			criteria.andNameLike("%"+query+"%");
		}
		List<BDoctor> list = doctorDao.selectByExample(bDoctorExample);
		PageInfo<BDoctor> pageInfo = new PageInfo<BDoctor>(list);
		return pageInfo;
	}


	@Override
	public ListResult doctorList(HashMap<String, Object> valMap) {
		int pageIndex = (int) valMap.get("pageIndex");
		int pageSize = (int) valMap.get("pageSize");
		int gardenId = (int) valMap.get("gardenId");
		String query = (String) valMap.get("query");
		PageHelper.startPage(pageIndex+1,pageSize);
		BDoctorExample doctorExample = new BDoctorExample();
		doctorExample.setOrderByClause("id DESC");
		Criteria criteria = doctorExample.createCriteria();
		if (query != null && query != "") {
			criteria.andNameLike("%"+query+"%");
		}
		if (gardenId>0) {
			criteria.andGardenIdEqualTo(gardenId);
		}
		List<BDoctor> list = doctorDao.selectByExample(doctorExample);
		PageInfo<BDoctor> pageInfo = new PageInfo<BDoctor>(list);
		ListResult listResult = new ListResult();
		listResult.setList(pageInfo.getList());
		listResult.setTotal((int) pageInfo.getTotal());
		return listResult;
	}
	
	
	
	
	
	
	

}
