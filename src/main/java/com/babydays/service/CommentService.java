package com.babydays.service;

import java.util.HashMap;

import com.babydays.model.BComment;
import com.babydays.model.ListResult;
import com.github.pagehelper.PageInfo;

public interface CommentService {

	void addComment(BComment comment) throws Exception;

	void updateComment(BComment comment) throws Exception;

	void deleteCommentById(Integer id) throws Exception;

	PageInfo<BComment> getComments(String query, Integer pageNum, Integer pageSize, Integer docId) throws Exception;

	ListResult commentList(HashMap<String, Object> valMap);

}
