package com.babydays.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babydays.dao.BCalendarDao;
import com.babydays.model.BCalendar;
import com.babydays.model.BCalendarExample;
import com.babydays.model.BCalendarExample.Criteria;
import com.babydays.model.ListResult;
import com.babydays.service.CalendarService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class CalendarServiceImpl implements CalendarService {

	
	@Autowired
	private BCalendarDao calendarDao;
	
	
	@Override
	public void addCalendar(BCalendar calendar) throws Exception {
		calendarDao.insertSelective(calendar);
	}


	@Override
	public void updateCalendar(BCalendar calendar) throws Exception {
		calendarDao.updateByPrimaryKeySelective(calendar);
	}


	@Override
	public void deleteCalendarById(Integer id) throws Exception {
		calendarDao.deleteByPrimaryKey(id);
	}


	@Override
	public ListResult calendarList(HashMap<String, Object> valMap) {
		Integer pageIndex = (int) valMap.get("pageIndex");
		Integer pageSize = (int) valMap.get("pageSize");
		Integer gardenId = (int) valMap.get("gardenId");
		String query = (String) valMap.get("query");
		PageHelper.startPage(pageIndex+1,pageSize);
		BCalendarExample calendarExample = new BCalendarExample();
		calendarExample.setOrderByClause("id desc");
		Criteria criteria = calendarExample.createCriteria();
		if (query != null && query != "") {
			criteria.andTitleLike("%"+query+"%");
		}
		if (gardenId != null) {
			if (gardenId>0) {
				criteria.andGardenIdEqualTo(gardenId);
			}
		}
		List<BCalendar> list = calendarDao.selectByExample(calendarExample);
		PageInfo<BCalendar> pageInfo = new PageInfo<BCalendar>(list);
		ListResult listResult = new ListResult();
		listResult.setList(pageInfo.getList());
		listResult.setTotal((int) pageInfo.getTotal());
		return listResult;
	}


	@Override
	public PageInfo<BCalendar> getCalendars(String query, Integer pageNum, Integer pageSize, Integer gardenId) throws Exception {
		PageHelper.startPage(pageNum, pageSize);
		BCalendarExample calendarExample = new BCalendarExample();
		calendarExample.setOrderByClause("id desc");
		Criteria criteria = calendarExample.createCriteria();
		if (query != null && query != "") {
			criteria.andTitleLike("%"+query+"%");
		}
		if (gardenId != null) {
			if (gardenId > 0) {
				criteria.andGardenIdEqualTo(gardenId);
			}
		}
		List<BCalendar> list = calendarDao.selectByExample(calendarExample);
		PageInfo<BCalendar> pageInfo = new PageInfo<BCalendar>(list);
		return pageInfo;
	}
	
	
	
	
	
	
	

}
