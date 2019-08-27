package com.babydays.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babydays.dao.BGardenDao;
import com.babydays.model.BGarden;
import com.babydays.model.BGardenExample;
import com.babydays.model.BGardenExample.Criteria;
import com.babydays.model.ListResult;
import com.babydays.service.GardenService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class GardenServiceImpl implements GardenService {

	
	@Autowired
	private BGardenDao gardenDao;
	
	
	@Override
	public void addGarden(BGarden garden) throws Exception {
		gardenDao.insertSelective(garden);
	}


	@Override
	public void deleteGarden(Integer id) throws Exception {
		BGarden garden = gardenDao.selectByPrimaryKey(id);
		Integer status = garden.getStatus();
		BGarden changeGarden = new BGarden();
		changeGarden.setId(id);
		if (status == 0) {
			changeGarden.setStatus(1);
		}
		if (status == 1) {
			changeGarden.setStatus(0);
		}
		gardenDao.updateByPrimaryKeySelective(changeGarden);
	}


	@Override
	public void editGarden(BGarden garden) throws Exception {
		gardenDao.updateByPrimaryKeySelective(garden);
	}


	@Override
	public ListResult gardenList(HashMap<String, Object> valMap) {
		int pageIndex = (int) valMap.get("pageIndex");
		int pageSize = (int) valMap.get("pageSize");
		String query = (String) valMap.get("query");
		PageHelper.startPage(pageIndex+1,pageSize);
		BGardenExample gardenExample = new BGardenExample();
		gardenExample.setOrderByClause("id DESC");
		Criteria criteria = gardenExample.createCriteria();
		if (query != null && query != "") {
			criteria.andGardennameLike("%"+query+"%");
		}
		List<BGarden> list = gardenDao.selectByExample(gardenExample);
		PageInfo<BGarden> pageInfo = new PageInfo<BGarden>(list);
		ListResult listResult = new ListResult();
		listResult.setList(pageInfo.getList());
		listResult.setTotal((int) pageInfo.getTotal());
		return listResult;
	}


	@Override
	public List<BGarden> getAllGarden() {
		BGardenExample gardenExample = new BGardenExample();
		gardenExample.setOrderByClause("id DESC");
		List<BGarden> list = gardenDao.selectByExample(gardenExample);
		return list;
	}


	@Override
	public PageInfo<BGarden> getGardens(String query, Integer pageNum, Integer pageSize) throws Exception {
		PageHelper.startPage(pageNum,pageSize);
		BGardenExample gardenExample = new BGardenExample();
		gardenExample.setOrderByClause("id DESC");
		Criteria criteria = gardenExample.createCriteria();
		if (query != null && query != "") {
			criteria.andGardennameLike("%"+query+"%");
		}
		List<BGarden> list = gardenDao.selectByExample(gardenExample);
		PageInfo<BGarden> pageInfo = new PageInfo<BGarden>(list);
		return pageInfo;
	}


	@Override
	public BGarden directorAppointIsOrNot(Integer gardenId) {
		if (gardenId == null || gardenId<=0) {
			return null;
		}else {
			BGardenExample gardenExample = new BGardenExample();
			Criteria criteria = gardenExample.createCriteria();
			criteria.andIdEqualTo(gardenId);
			List<BGarden> list = gardenDao.selectByExample(gardenExample);
			if (list.size()>0) {
				return list.get(0);
			}
			return null;
		}
	}


	@Override
	public BGarden getGardenById(Integer gardenId) {
		BGardenExample gardenExample = new BGardenExample();
		Criteria criteria = gardenExample.createCriteria();
		criteria.andIdEqualTo(gardenId);
		List<BGarden> list = gardenDao.selectByExample(gardenExample);
		if (list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	
	

}
