package com.babydays.service;

import java.util.HashMap;

import com.babydays.model.BDirector;
import com.babydays.model.BUser;
import com.babydays.model.ListResult;
import com.github.pagehelper.PageInfo;

public interface DirectorService {

	BDirector selectDirectorById(Integer detailsId);

	void insertSelective(BDirector director, BUser user) throws Exception;

	void resetStatusDirector(Integer id) throws Exception;

	void updateDirector(BDirector director) throws Exception;

	PageInfo<BDirector> getDirectors(String query, Integer pageNum, Integer pageSize, Integer role, Integer gardenId) throws Exception;

	PageInfo<BDirector> getAllDirectors(String query, Integer pageNum, Integer pageSize, Integer gardenId) throws Exception;

	ListResult directorList(HashMap<String, Object> valMap);

}
