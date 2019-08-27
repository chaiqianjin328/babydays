package com.babydays.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babydays.dao.BProgramDao;
import com.babydays.model.BProgram;
import com.babydays.model.BProgramExample;
import com.babydays.model.BProgramExample.Criteria;
import com.babydays.model.ListResult;
import com.babydays.service.ProgramService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class ProgramServiceImpl implements ProgramService {

	
	@Autowired
	private BProgramDao programDao;

	@Override
	public void addProgram(BProgram program) throws Exception {
		programDao.insertSelective(program);
	}

	@Override
	public void deleteProgramById(Integer id) throws Exception {
		programDao.deleteByPrimaryKey(id);
	}

	@Override
	public void updateProgram(BProgram program) throws Exception {
		programDao.updateByPrimaryKeySelective(program);
	}

	@SuppressWarnings("unused")
	@Override
	public ListResult programList(HashMap<String, Object> valMap) {
		Integer pageIndex = (int) valMap.get("pageIndex");
		Integer pageSize = (int) valMap.get("pageSize");
		Integer gardenId = (int) valMap.get("gardenId");
		Integer classId = (int) valMap.get("classId");
		String query = (String) valMap.get("query");
		PageHelper.startPage(pageIndex+1,pageSize);
		BProgramExample programExample = new BProgramExample();
		programExample.setOrderByClause("id DESC");
		Criteria criteria = programExample.createCriteria();
		if (query != null && query != "") {
			criteria.andAuthorLike("%"+query+"%");
		}
		if (classId != null) {
			if (classId>0) {
				criteria.andClassIdEqualTo(classId);
			} else if (gardenId != null) {
				if (gardenId>0) {
					criteria.andGardenIdEqualTo(gardenId);
				}
			}
		}else {
			if (gardenId != null) {
				if (gardenId>0) {
					criteria.andGardenIdEqualTo(gardenId);
				}
			}
		}
		List<BProgram> list = programDao.selectByExample(programExample);
		PageInfo<BProgram> pageInfo = new PageInfo<BProgram>(list);
		ListResult listResult = new ListResult();
		listResult.setList(pageInfo.getList());
		listResult.setTotal((int) pageInfo.getTotal());
		return listResult;
	}

	
	
	@Override
	public PageInfo<BProgram> getPrograms(String query, Integer pageNum, Integer pageSize, Integer classId,Integer gardenId) throws Exception {
		PageHelper.startPage(pageNum,pageSize);
		BProgramExample programExample = new BProgramExample();
		programExample.setOrderByClause("id DESC");
		Criteria criteria = programExample.createCriteria();
		if (query != null && query != "") {
			criteria.andAuthorLike("%"+query+"%");
		}
		if (classId != null) {
			if (classId>0) {
				criteria.andClassIdEqualTo(classId);
			} else if (gardenId != null) {
				if (gardenId>0) {
					criteria.andGardenIdEqualTo(gardenId);
				}
			}
		}else {
			if (gardenId != null) {
				if (gardenId>0) {
					criteria.andGardenIdEqualTo(gardenId);
				}
			}
		}
		List<BProgram> list = programDao.selectByExample(programExample);
		PageInfo<BProgram> pageInfo = new PageInfo<BProgram>(list);
		return pageInfo;
	}
	
	
	
	
	
	
	
}
