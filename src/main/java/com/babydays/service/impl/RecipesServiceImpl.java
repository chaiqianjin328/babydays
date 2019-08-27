package com.babydays.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babydays.dao.BRecipesDao;
import com.babydays.model.BRecipes;
import com.babydays.model.BRecipesExample;
import com.babydays.model.BRecipesExample.Criteria;
import com.babydays.model.ListResult;
import com.babydays.service.RecipesService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class RecipesServiceImpl implements RecipesService {

	@Autowired
	private BRecipesDao recipesDao;
	
	
	@Override
	public void addRecipes(BRecipes recipes) throws Exception {
		recipesDao.insertSelective(recipes);
	}


	@Override
	public void updateRecipes(BRecipes recipes) throws Exception {
		recipesDao.updateByPrimaryKeySelective(recipes);
	}


	@Override
	public void deleteRecipesById(Integer id) throws Exception {
		recipesDao.deleteByPrimaryKey(id);
	}


	@SuppressWarnings("unused")
	@Override
	public ListResult recipesList(HashMap<String, Object> valMap) {
		Integer pageIndex = (int) valMap.get("pageIndex");
		Integer pageSize = (int) valMap.get("pageSize");
		Integer gardenId = (int) valMap.get("gardenId");
		Integer classId = (int) valMap.get("classId");
		String query = (String) valMap.get("query");
		PageHelper.startPage(pageIndex+1,pageSize);
		BRecipesExample recipesExample = new BRecipesExample();
		recipesExample.setOrderByClause("id DESC");
		Criteria criteria = recipesExample.createCriteria();
		if (query != null && query != "") {
			criteria.andAuthorLike("%"+query+"%");
		}
		if (classId != null) {
			if (classId>0) {
				criteria.andClassIdEqualTo(classId);
			}else if (gardenId != null) {
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
		List<BRecipes> list = recipesDao.selectByExample(recipesExample);
		PageInfo<BRecipes> pageInfo = new PageInfo<BRecipes>(list);
		ListResult listResult = new ListResult();
		listResult.setList(pageInfo.getList());
		listResult.setTotal((int) pageInfo.getTotal());
		return listResult;
	}


	@Override
	public PageInfo<BRecipes> getRecipes(String query, Integer pageNum, Integer pageSize, Integer gardenId,Integer classId) throws Exception {
		PageHelper.startPage(pageNum, pageSize);
		BRecipesExample recipesExample = new BRecipesExample();
		recipesExample.setOrderByClause("id DESC");
		Criteria criteria = recipesExample.createCriteria();
		if (query != null && query != "") {
			criteria.andAuthorLike("%"+query+"%");
		}
		if (classId != null) {
			if (classId>0) {
				criteria.andClassIdEqualTo(classId);
			}else if (gardenId != null) {
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
		List<BRecipes> list = recipesDao.selectByExample(recipesExample);
		PageInfo<BRecipes> pageInfo = new PageInfo<BRecipes>(list);
		return pageInfo;
	}

}
