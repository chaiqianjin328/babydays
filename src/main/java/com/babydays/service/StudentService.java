package com.babydays.service;

import java.util.HashMap;

import com.babydays.model.BStudent;
import com.babydays.model.BUser;
import com.babydays.model.ListResult;
import com.github.pagehelper.PageInfo;

public interface StudentService {

	BStudent selectStudentById(Integer detailsId);

	void insertSelective(BStudent student, BUser user) throws Exception;

	void resetStatusStudent(Integer id) throws Exception;

	void updateStudent(BStudent student) throws Exception;

	PageInfo<BStudent> getStudents(String query, Integer pageNum, Integer pageSize, Integer gardenId, Integer classId) throws Exception;

	ListResult studentList(HashMap<String, Object> valMap);


}
