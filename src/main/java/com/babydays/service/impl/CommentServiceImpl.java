package com.babydays.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babydays.dao.BCommentDao;
import com.babydays.model.BComment;
import com.babydays.model.BCommentExample;
import com.babydays.model.BCommentExample.Criteria;
import com.babydays.model.ListResult;
import com.babydays.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class CommentServiceImpl implements CommentService {

	
	@Autowired
	private BCommentDao commentDao;	
	
	
	@Override
	public void addComment(BComment comment) throws Exception {
		commentDao.insertSelective(comment);
	}


	@Override
	public void updateComment(BComment comment) throws Exception {
		commentDao.updateByPrimaryKeySelective(comment);
	}


	@Override
	public void deleteCommentById(Integer id) throws Exception {
		commentDao.deleteByPrimaryKey(id);
	}


	@Override
	public PageInfo<BComment> getComments(String query, Integer pageNum, Integer pageSize, Integer docId) throws Exception {
		PageHelper.startPage(pageNum, pageSize);
		BCommentExample commentExample = new BCommentExample();
		commentExample.setOrderByClause("id DESC");
		Criteria criteria = commentExample.createCriteria();
		if (query != null && query != "") {
			criteria.andAuthorLike("%"+query+"%");
		}
		if (docId != null) {
			criteria.andDocIdEqualTo(docId);
		}
		List<BComment> list = commentDao.selectByExample(commentExample);
		PageInfo<BComment> pageInfo = new PageInfo<BComment>(list);
		return pageInfo;
	}


	@Override
	public ListResult commentList(HashMap<String, Object> valMap) {
		Integer pageIndex = (int) valMap.get("pageIndex");
		Integer pageSize = (int) valMap.get("pageSize");
		Integer docId = (int) valMap.get("docId");
		String query = (String) valMap.get("query");
		PageHelper.startPage(pageIndex+1,pageSize);
		BCommentExample commentExample = new BCommentExample();
		commentExample.setOrderByClause("id DESC");
		Criteria criteria = commentExample.createCriteria();
		if (query != null && query != "") {
			criteria.andAuthorLike("%"+query+"%");
		}
		if (docId != null) {
			if (docId > 0) {
				criteria.andDocIdEqualTo(docId);
			}
		}
		List<BComment> list = commentDao.selectByExample(commentExample);
		PageInfo<BComment> pageInfo = new PageInfo<BComment>(list);
		ListResult listResult = new ListResult();
		listResult.setList(pageInfo.getList());
		listResult.setTotal((int) pageInfo.getTotal());
		return listResult;
	}

	
	
	
}
