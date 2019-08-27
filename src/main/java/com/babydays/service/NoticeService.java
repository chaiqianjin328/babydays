package com.babydays.service;

import java.util.HashMap;

import com.babydays.model.BNotice;
import com.babydays.model.ListResult;
import com.github.pagehelper.PageInfo;

public interface NoticeService {

	void addNotice(BNotice notice) throws Exception;

	void updateNotice(BNotice notice) throws Exception;

	void deleteNoticeById(Integer id) throws Exception;

	ListResult noticeList(HashMap<String, Object> valMap);

	PageInfo<BNotice> getNotices(String query, Integer pageNum, Integer pageSize, Integer gardenId) throws Exception;

}
