package com.babydays.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.babydays.dao.BAdminDao;
import com.babydays.dao.BUserDao;
import com.babydays.model.BAdmin;
import com.babydays.model.BAdminExample;
import com.babydays.model.BAdminExample.Criteria;
import com.babydays.model.BUser;
import com.babydays.model.BUserExample;
import com.babydays.model.ListResult;
import com.babydays.service.AdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private BAdminDao adminDao;
	
	@Autowired
	private BUserDao userDao;
	

	@Override
	public BAdmin selectAdminById(Integer detailsId) {
		BAdminExample bAdminExample = new BAdminExample();
		Criteria criteria = bAdminExample.createCriteria();
		criteria.andIdEqualTo(detailsId);
		criteria.andStatusEqualTo(0);
		List<BAdmin> list = adminDao.selectByExample(bAdminExample);
		if (list.size() > 0) {
			BUser user = new BUser();
			user.setLasttime(new Date());
			user.setUid(list.get(0).getUid());
			userDao.updateByPrimaryKeySelective(user);
			return list.get(0);
		}
		return null;
	}


	@Override
	public void insertSelective(BAdmin admin, BUser user) throws Exception{
		adminDao.insertSelective(admin);
		user.setDetailsId(admin.getId());
		
		//事务测试
		//user.setDetailsId(1/0);
		
		userDao.insertSelective(user);
	}


	@Override
	public void resetStatusAdmin(Integer id) throws Exception {
		BAdmin admin = adminDao.selectByPrimaryKey(id);
		Integer status = admin.getStatus();
		BAdmin newAdmin = new BAdmin();
		newAdmin.setId(admin.getId());
		if (status==0) {
			newAdmin.setStatus(1);
		}else {
			newAdmin.setStatus(0);
		}
		adminDao.updateByPrimaryKeySelective(newAdmin);
	}


	@Override
	public void updateAdmin(BAdmin admin) throws Exception {
		BUserExample bUserExample = new BUserExample();
		BUserExample.Criteria criteria = bUserExample.createCriteria();
		criteria.andDetailsIdEqualTo(admin.getId());
		criteria.andRoleEqualTo(admin.getRole());
		List<BUser> list = userDao.selectByExample(bUserExample);
		if (list.size()>0) {
			BUser user = list.get(0);
			if (!user.getPassword().equals(admin.getPassword())) {
				user.setPassword(admin.getPassword());
				userDao.updateByPrimaryKeySelective(user);
			}
			adminDao.updateByPrimaryKeySelective(admin);
		}else {
			throw new Exception("user for admin is not exist");
		}
		
	}


	@Override
	public PageInfo<BAdmin> getAdmins(String query, Integer pageNum, Integer pageSize) throws Exception {
		PageHelper.startPage(pageNum, pageSize);
		BAdminExample bAdminExample = new BAdminExample();
		bAdminExample.setOrderByClause("id desc");
		if (query == null || query == "") {
			
		}else {
			Criteria criteria = bAdminExample.createCriteria();
			criteria.andNameLike("%"+query+"%");
		}
		List<BAdmin> list = adminDao.selectByExample(bAdminExample);
		PageInfo<BAdmin> pageInfo = new PageInfo<BAdmin>(list);
		return pageInfo;
	}


	@Override
	public ListResult adminList(HashMap<String, Object> valMap) {
		int pageIndex = (int) valMap.get("pageIndex");
		int pageSize = (int) valMap.get("pageSize");
		String query = (String) valMap.get("query");
		PageHelper.startPage(pageIndex+1,pageSize);
		BAdminExample adminExample = new BAdminExample();
		adminExample.setOrderByClause("id desc");
		Criteria criteria = adminExample.createCriteria();
		if (query != null && query != "") {
			criteria.andNameLike("%"+query+"%");
		}
		List<BAdmin> list = adminDao.selectByExample(adminExample);
		PageInfo<BAdmin> pageInfo = new PageInfo<BAdmin>(list);
		ListResult listResult = new ListResult();
		listResult.setList(pageInfo.getList());
		listResult.setTotal((int) pageInfo.getTotal());
		return listResult;
	}





	
	
	
	
}
