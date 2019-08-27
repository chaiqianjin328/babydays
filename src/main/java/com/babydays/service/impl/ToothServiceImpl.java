package com.babydays.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babydays.dao.BToothDao;
import com.babydays.model.BTooth;
import com.babydays.model.BToothExample;
import com.babydays.model.BToothExample.Criteria;
import com.babydays.model.ListResult;
import com.babydays.service.ToothService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ToothServiceImpl implements ToothService {

	@Autowired
	private BToothDao toothDao;

	@Override
	public void addTooth(BTooth tooth) throws Exception {
		toothDao.insertSelective(tooth);
	}

	@Override
	public ListResult toothList(HashMap<String, Object> valMap) {
		Integer pageIndex = (int) valMap.get("pageIndex");
		Integer pageSize = (int) valMap.get("pageSize");
		Integer stuId = (int) valMap.get("stuId");
		String query = (String) valMap.get("query");
		PageHelper.startPage(pageIndex+1,pageSize);
		BToothExample toothExample = new BToothExample();
		toothExample.setOrderByClause("id DESC");
		Criteria criteria = toothExample.createCriteria();
		if (query != null && query != "") {
			//暂时没有
		}
		if (stuId != null) {
			if (stuId > 0) {
				criteria.andStuIdEqualTo(stuId);
			}
		}
		List<BTooth> list = toothDao.selectByExample(toothExample);
		PageInfo<BTooth> pageInfo = new PageInfo<BTooth>(list);
		ListResult listResult = new ListResult();
		listResult.setList(pageInfo.getList());
		listResult.setTotal((int) pageInfo.getTotal());
		return listResult;
	}

	@Override
	public void deleteToothById(Integer id) throws Exception {
		toothDao.deleteByPrimaryKey(id);
	}

	@Override
	public PageInfo<BTooth> getTooths(String query, Integer pageNum, Integer pageSize, Integer stuId) throws Exception {
		PageHelper.startPage(pageNum, pageSize);
		BToothExample toothExample = new BToothExample();
		toothExample.setOrderByClause("id DESC");
		Criteria criteria = toothExample.createCriteria();
		if (query != null && query != "") {
			//暂时没有
		}
		if (stuId != null) {
			criteria.andStuIdEqualTo(stuId);
		}
		List<BTooth> list = toothDao.selectByExample(toothExample);
		PageInfo<BTooth> pageInfo = new PageInfo<BTooth>(list);
		return pageInfo;
	}
	
	
}
