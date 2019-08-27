package com.babydays.service;

import java.util.HashMap;

import com.babydays.model.BTooth;
import com.babydays.model.ListResult;
import com.github.pagehelper.PageInfo;

public interface ToothService {

	void addTooth(BTooth tooth) throws Exception;

	ListResult toothList(HashMap<String, Object> valMap);

	void deleteToothById(Integer id) throws Exception;

	PageInfo<BTooth> getTooths(String query, Integer pageNum, Integer pageSize, Integer stuId) throws Exception;

}
