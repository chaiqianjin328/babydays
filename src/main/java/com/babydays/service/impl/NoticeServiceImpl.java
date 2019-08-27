package com.babydays.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babydays.dao.BNoticeDao;
import com.babydays.model.BNotice;
import com.babydays.model.BNoticeExample;
import com.babydays.model.BNoticeExample.Criteria;
import com.babydays.model.ListResult;
import com.babydays.service.NoticeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class NoticeServiceImpl implements NoticeService {

	
	@Autowired
	private BNoticeDao noticeDao;
	
	
	@Override
	public void addNotice(BNotice notice) throws Exception {
		noticeDao.insertSelective(notice);
	}


	@Override
	public void updateNotice(BNotice notice) throws Exception {
		noticeDao.updateByPrimaryKeySelective(notice);
	}


	@Override
	public void deleteNoticeById(Integer id) throws Exception {
		noticeDao.deleteByPrimaryKey(id);
	}


	@Override
	public ListResult noticeList(HashMap<String, Object> valMap) {
		Integer pageIndex = (int) valMap.get("pageIndex");
		Integer pageSize = (int) valMap.get("pageSize");
		Integer gardenId = (int) valMap.get("gardenId");
		String query = (String) valMap.get("query");
		PageHelper.startPage(pageIndex+1,pageSize);
		BNoticeExample noticeExample = new BNoticeExample();
		noticeExample.setOrderByClause("id DESC");
		Criteria criteria = noticeExample.createCriteria();
		if (query != null && query != "") {
			criteria.andTitleLike("%"+query+"%");
		}
		if (gardenId != null) {
			if (gardenId>0) {
				criteria.andGardenIdEqualTo(gardenId);
				Criteria criteria2 = noticeExample.createCriteria();
				criteria2.andGardenIdEqualTo(0);
				noticeExample.or(criteria2);
			}
		}
		List<BNotice> list = noticeDao.selectByExample(noticeExample);
		PageInfo<BNotice> pageInfo = new PageInfo<BNotice>(list);
		ListResult listResult = new ListResult();
		listResult.setList(pageInfo.getList());
		listResult.setTotal((int) pageInfo.getTotal());
		return listResult;
	}


	@Override
	public PageInfo<BNotice> getNotices(String query, Integer pageNum, Integer pageSize, Integer gardenId) throws Exception {
		PageHelper.startPage(pageNum, pageSize);
		BNoticeExample noticeExample = new BNoticeExample();
		noticeExample.setOrderByClause("id DESC");
		Criteria criteria = noticeExample.createCriteria();
		if (query != null && query != "") {
			criteria.andTitleLike("%"+query+"%");
		}
		if (gardenId != null) {
			if (gardenId > 0) {
				criteria.andGardenIdEqualTo(gardenId);
				Criteria criteria2 = noticeExample.createCriteria();
				criteria2.andGardenIdEqualTo(0);
				noticeExample.or(criteria2);
			}
		}
		List<BNotice> list = noticeDao.selectByExample(noticeExample);
		PageInfo<BNotice> pageInfo = new PageInfo<BNotice>(list);
		return pageInfo;
	}

}
