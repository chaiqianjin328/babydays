package com.babydays.service;

import java.util.HashMap;

import com.babydays.model.BTeacher;
import com.babydays.model.BUser;
import com.babydays.model.ListResult;
import com.github.pagehelper.PageInfo;

public interface TeacherService {

	BTeacher selectTeacherById(Integer detailsId);

	void insertSelective(BTeacher teacher, BUser user) throws Exception;

	void resetStatusTeacher(Integer id) throws Exception;

	void updateTeacher(BTeacher teacher) throws Exception;

	PageInfo<BTeacher> getTeachers(String query, Integer pageNum, Integer pageSize, Integer role, Integer gardenId, Integer classId) throws Exception;

	PageInfo<BTeacher> getAllTeachers(String query, Integer pageNum, Integer pageSize, Integer gardenId, Integer classId) throws Exception;

	ListResult teacherList(HashMap<String, Object> valMap);

}
