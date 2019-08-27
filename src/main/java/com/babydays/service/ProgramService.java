package com.babydays.service;

import java.util.HashMap;

import com.babydays.model.BProgram;
import com.babydays.model.ListResult;
import com.github.pagehelper.PageInfo;

public interface ProgramService {

	void addProgram(BProgram program) throws Exception;

	void deleteProgramById(Integer id) throws Exception;

	void updateProgram(BProgram program) throws Exception;

	ListResult programList(HashMap<String, Object> valMap);

	PageInfo<BProgram> getPrograms(String query, Integer pageNum, Integer pageSize, Integer classId, Integer gardenId) throws Exception;

}
