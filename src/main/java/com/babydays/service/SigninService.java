package com.babydays.service;

import java.text.ParseException;
import java.util.HashMap;

import com.babydays.model.BSignin;
import com.babydays.model.ListResult;
import com.github.pagehelper.PageInfo;

public interface SigninService {

	ListResult signinList(HashMap<String, Object> valMap) throws ParseException;

	void inGarden(BSignin signin) throws Exception;

	void outGarden(BSignin signin) throws Exception;

	PageInfo<BSignin> getSignins(Integer pageNum, Integer pageSize, Integer gardenId, Integer classId, String createtime) throws Exception;

	ListResult studenSigninList(HashMap<String, Object> valMap) throws ParseException;

	PageInfo<BSignin> getStudentSignins(Integer pageNum, Integer pageSize, Integer stuId, String createtime) throws Exception;

}
