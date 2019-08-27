package com.babydays.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babydays.dao.BDirectorDao;
import com.babydays.dao.BGardenDao;
import com.babydays.dao.BUserDao;
import com.babydays.model.BDirector;
import com.babydays.model.BDirectorExample;
import com.babydays.model.BDirectorExample.Criteria;
import com.babydays.model.BGarden;
import com.babydays.model.BUser;
import com.babydays.model.BUserExample;
import com.babydays.model.ListResult;
import com.babydays.service.DirectorService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class DirectorServiceImpl implements DirectorService {

	@Autowired
	private BDirectorDao directorDao;
	
	@Autowired
	private BUserDao userDao;
	
	@Autowired
	private BGardenDao gardenDao;
	
	
	
	@Override
	public BDirector selectDirectorById(Integer detailsId) {
		BDirectorExample bDirectorExample = new BDirectorExample();
		Criteria criteria = bDirectorExample.createCriteria();
		criteria.andIdEqualTo(detailsId);
		criteria.andStatusEqualTo(0);
		criteria.andRoleBetween(1, 3);
		List<BDirector> list = directorDao.selectByExample(bDirectorExample);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}


	@Override
	public void insertSelective(BDirector director, BUser user) throws Exception {
		Integer role = director.getRole();
		Integer gardenId = director.getGardenId();
		directorDao.insertSelective(director);
		if (role==1) {
			BGarden bGarden = new BGarden();
			bGarden.setId(gardenId);
			bGarden.setDirectorId(director.getId());
			gardenDao.updateByPrimaryKeySelective(bGarden);
		}
		user.setDetailsId(director.getId());
		userDao.insertSelective(user);
	}


	@Override
	public void resetStatusDirector(Integer id) throws Exception {
		BDirector director = directorDao.selectByPrimaryKey(id);
		Integer status = director.getStatus();
		BDirector newDirector = new BDirector();
		newDirector.setId(director.getId());
		if (status==0) {
			newDirector.setStatus(1);
		}else {
			newDirector.setStatus(0);
		}
		directorDao.updateByPrimaryKeySelective(newDirector);
	}


	@Override
	public void updateDirector(BDirector director) throws Exception {
		BUserExample bUserExample = new BUserExample();
		BUserExample.Criteria criteria = bUserExample.createCriteria();
		criteria.andDetailsIdEqualTo(director.getId());
		criteria.andRoleEqualTo(director.getRole());
		List<BUser> list = userDao.selectByExample(bUserExample);
		if (list.size()>0) {
			BUser user = list.get(0);
			if (!user.getPassword().equals(director.getPassword())) {
				user.setPassword(director.getPassword());
				userDao.updateByPrimaryKeySelective(user);
			}
			directorDao.updateByPrimaryKeySelective(director);
		}else {
			throw new Exception("user for director is not exist");
		}
		Integer role = director.getRole();
		Integer gardenId = director.getGardenId();
		if (role==1) {
			BGarden bGarden = new BGarden();
			bGarden.setId(gardenId);
			bGarden.setDirectorId(director.getId());
			gardenDao.updateByPrimaryKeySelective(bGarden);
		}
		
	}


	@Override
	public PageInfo<BDirector> getDirectors(String query, Integer pageNum, Integer pageSize, Integer role,Integer gardenId) throws Exception {
		PageHelper.startPage(pageNum, pageSize);
		BDirectorExample bDirectorExample = new BDirectorExample();
		bDirectorExample.setOrderByClause("id DESC");
		Criteria criteria = bDirectorExample.createCriteria();
		if (query == null || query == "") {
			
		} else {
			criteria.andNameLike("%"+query+"%");
		}
		if (gardenId != null) {
			if (gardenId>0) {
				criteria.andGardenIdEqualTo(gardenId);
			}
		}
		criteria.andRoleEqualTo(role);
		List<BDirector> list = directorDao.selectByExample(bDirectorExample);
		PageInfo<BDirector> pageInfo = new PageInfo<BDirector>(list);
		return pageInfo;
	}


	@Override
	public PageInfo<BDirector> getAllDirectors(String query, Integer pageNum, Integer pageSize,Integer gardenId) throws Exception {
		PageHelper.startPage(pageNum, pageSize);
		BDirectorExample bDirectorExample = new BDirectorExample();
		bDirectorExample.setOrderByClause("id DESC");
		Criteria criteria = bDirectorExample.createCriteria();
		if (query == null || query == "") {
			
		} else {
			criteria.andNameLike("%"+query+"%");
		}
		if (gardenId != null) {
			if (gardenId>0) {
				criteria.andGardenIdEqualTo(gardenId);
			}
		}
		criteria.andRoleBetween(1, 3);
		List<BDirector> list = directorDao.selectByExample(bDirectorExample);
		PageInfo<BDirector> pageInfo = new PageInfo<BDirector>(list);
		return pageInfo;
	}


	@Override
	public ListResult directorList(HashMap<String, Object> valMap) {
		int pageIndex = (int) valMap.get("pageIndex");
		int pageSize = (int) valMap.get("pageSize");
		int gardenId = (int) valMap.get("gardenId");
		String query = (String) valMap.get("query");
		PageHelper.startPage(pageIndex+1,pageSize);
		BDirectorExample directorExample = new BDirectorExample();
		directorExample.setOrderByClause("id DESC");
		Criteria criteria = directorExample.createCriteria();
		if (query != null && query != "") {
			criteria.andNameLike("%"+query+"%");
		}
		if (gardenId>0) {
			criteria.andGardenIdEqualTo(gardenId);
		}
		criteria.andRoleBetween(1, 3);
		List<BDirector> list = directorDao.selectByExample(directorExample);
		PageInfo<BDirector> pageInfo = new PageInfo<BDirector>(list);
		ListResult listResult = new ListResult();
		listResult.setList(pageInfo.getList());
		listResult.setTotal((int) pageInfo.getTotal());
		return listResult;
	}
	
	
	

}
