package com.babydays.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babydays.dao.BLeaveDao;
import com.babydays.model.BLeave;
import com.babydays.model.BLeaveExample;
import com.babydays.model.BLeaveExample.Criteria;
import com.babydays.model.ListResult;
import com.babydays.service.LeaveService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class LeaveServiceImpl implements LeaveService {

	
	@Autowired
	private BLeaveDao leaveDao;

	@Override
	public void addLeave(BLeave leave) throws Exception {
		leaveDao.insertSelective(leave);
	}

	@Override
	public ListResult leaveList(HashMap<String, Object> valMap) {
		Integer pageIndex = (int) valMap.get("pageIndex");
		Integer pageSize = (int) valMap.get("pageSize");
		Integer stuId = (int) valMap.get("stuId");
		Integer leaveday = (int) valMap.get("leaveday");
		Date start = (Date)valMap.get("start");
		Date end = (Date)valMap.get("end");
		PageHelper.startPage(pageIndex+1,pageSize);
		BLeaveExample leaveExample = new BLeaveExample();
		leaveExample.setOrderByClause("id DESC");
		Criteria criteria = leaveExample.createCriteria();
		if (stuId != null) {
			if (stuId > 0) {
				criteria.andStuIdEqualTo(stuId);
			}
		}
		if (leaveday != null) {
			if (leaveday > 0) {
				criteria.andLeavedayGreaterThanOrEqualTo(leaveday);
			}
		}
		if (start != null) {
			criteria.andEndTimeGreaterThanOrEqualTo(start);
		}
		if (end != null) {
			criteria.andStartTimeLessThanOrEqualTo(end);
		}
		List<BLeave> list = leaveDao.selectByExample(leaveExample);
		PageInfo<BLeave> pageInfo = new PageInfo<BLeave>(list);
		ListResult listResult = new ListResult();
		listResult.setList(pageInfo.getList());
		listResult.setTotal((int) pageInfo.getTotal());
		return listResult;
	}

	@Override
	public void deleteLeaveById(Integer id) throws Exception {
		leaveDao.deleteByPrimaryKey(id);
	}

	@Override
	public PageInfo<BLeave> getLeaves(String query, Integer pageNum, Integer pageSize, Date start, Date end,
			Integer stuId, Integer leaveday) throws Exception {
		PageHelper.startPage(pageNum, pageSize);
		BLeaveExample leaveExample = new BLeaveExample();
		leaveExample.setOrderByClause("id DESC");
		Criteria criteria = leaveExample.createCriteria();
		if (query != null && query != "") {
			//暂时没有
		}
		if (stuId != null) {
			if (stuId > 0) {
				criteria.andStuIdEqualTo(stuId);
			}
		}
		if (leaveday != null) {
			if (leaveday > 0) {
				criteria.andLeavedayGreaterThanOrEqualTo(leaveday);
			}
		}
		if (start != null) {
			criteria.andEndTimeGreaterThanOrEqualTo(start);
		}
		if (end != null) {
			criteria.andStartTimeLessThanOrEqualTo(end);
		}
		List<BLeave> list = leaveDao.selectByExample(leaveExample);
		PageInfo<BLeave> pageInfo = new PageInfo<BLeave>(list);
		return pageInfo;
	}
	
	
	
}
