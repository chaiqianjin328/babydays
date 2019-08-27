package com.babydays.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babydays.dao.BHealthDao;
import com.babydays.model.BHealth;
import com.babydays.model.BHealthExample;
import com.babydays.model.BHealthExample.Criteria;
import com.babydays.model.ListResult;
import com.babydays.service.HealthService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class HealthServiceImpl implements HealthService {

	@Autowired
	private BHealthDao healthDao;
	
	
	@Override
	public void addHealth(BHealth health) throws Exception {
		healthDao.insertSelective(health);
	}


	@Override
	public ListResult healthList(HashMap<String, Object> valMap) {
		Integer pageIndex = (int) valMap.get("pageIndex");
		Integer pageSize = (int) valMap.get("pageSize");
		Integer stuId = (int) valMap.get("stuId");
		String query = (String) valMap.get("query");
		PageHelper.startPage(pageIndex+1,pageSize);
		BHealthExample healthExample = new BHealthExample();
		healthExample.setOrderByClause("id DESC");
		Criteria criteria = healthExample.createCriteria();
		if (query != null && query != "") {
			//暂时没有
		}
		if (stuId != null) {
			if (stuId > 0) {
				criteria.andStuIdEqualTo(stuId);
			}
		}
		List<BHealth> list = healthDao.selectByExample(healthExample);
		PageInfo<BHealth> pageInfo = new PageInfo<BHealth>(list);
		ListResult listResult = new ListResult();
		listResult.setList(pageInfo.getList());
		listResult.setTotal((int) pageInfo.getTotal());
		return listResult;
	}


	@Override
	public void deleteHealthById(Integer id) throws Exception {
		healthDao.deleteByPrimaryKey(id);
	}


	@Override
	public PageInfo<BHealth> getHealths(String query, Integer pageNum, Integer pageSize, Integer stuId) throws Exception {
		PageHelper.startPage(pageNum, pageSize);
		BHealthExample healthExample = new BHealthExample();
		healthExample.setOrderByClause("id DESC");
		Criteria criteria = healthExample.createCriteria();
		if (query != null && query != "") {
			//暂时没有
		}
		if (stuId != null) {
			criteria.andStuIdEqualTo(stuId);
		}
		List<BHealth> list = healthDao.selectByExample(healthExample);
		PageInfo<BHealth> pageInfo = new PageInfo<BHealth>(list);
		return pageInfo;
	}

}
