package com.babydays.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.babydays.model.BAbilitiesCata;
import com.babydays.model.BDocument;
import com.babydays.model.BStudent;
import com.babydays.model.ListResult;
import com.babydays.model.QueryModel;
import com.babydays.service.AbilitiesCataService;
import com.babydays.service.DocumentService;
import com.babydays.util.ResultUtil;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/growth")
public class GrowthController {
	
	
	@Autowired
	private DocumentService documentService;
	
	
	@Autowired
	private AbilitiesCataService abilitiesCataService;
	
	
	/**
	* @Title: addAbility
	* @Description: TODO(添加能力记录)
	* @param @param document
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/ability/addAbility",method=RequestMethod.POST)
	@ResponseBody
	public Object addAbility(@RequestBody BDocument document) {
		Integer type = document.getType();
		String title = document.getTitle();
		String content = document.getContent();
		String imgs = document.getImgs();
		String videos = document.getVideos();
		Integer uid = document.getUid();
		String author = document.getAuthor();
		Integer stuId = document.getStuId();
		Date createtime = document.getCreatetime();
		Integer abcataId = document.getAbcataId();
		if (type == null) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (type == 2) {
			if (imgs == null || imgs == "") {
				return ResultUtil.error(400, "添加失败，缺少必要参数");
			}
		}
		if (type == 3) {
			if (videos == null || videos == "") {
				return ResultUtil.error(400, "添加失败，缺少必要参数");
			}
		}
		if (title == null || title == "") {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (content == null || content == "") {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (uid == null) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (author == null || author == "") {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (stuId == null) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (createtime == null) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		if (abcataId == null || abcataId <= 0) {
			return ResultUtil.error(400, "添加失败，缺少必要参数");
		}
		
		try {
			documentService.addDocument(document);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "添加失败，错误原因："+e.getMessage());
		}
		
	}
	
	
	
	/**
	* @Title: deleteAbilityById
	* @Description: TODO(根据id删除对应能力记录)
	* @param @param document
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/ability/deleteAbilityById",method=RequestMethod.POST)
	@ResponseBody
	public Object deleteAbilityById(@RequestBody BDocument document) {
		Integer id = document.getId();
		if (id == null || id <=0) {
			return ResultUtil.error(400, "删除失败，缺少必要参数");
		}
		try {
			documentService.deleteDocumentById(id);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "删除失败，错误原因："+e.getMessage());
		}
		
	}
	
	
	
	
	/**
	* @Title: updateAbility
	* @Description: TODO(修改能力记录)
	* @param @param document
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/ability/updateAbility",method=RequestMethod.POST)
	@ResponseBody
	public Object updatePicture(@RequestBody BDocument document) {
		Integer id = document.getId();
		Integer type = document.getType();
		String title = document.getTitle();
		String content = document.getContent();
		String imgs = document.getImgs();
		String videos = document.getVideos();
		Integer uid = document.getUid();
		String author = document.getAuthor();
		Integer stuId = document.getStuId();
		Date createtime = document.getCreatetime();
		if (id == null) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (type == null) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (type == 2) {
			if (imgs == null || imgs == "") {
				return ResultUtil.error(400, "修改失败，缺少必要参数");
			}
		}
		if (type == 3) {
			if (videos == null || videos == "") {
				return ResultUtil.error(400, "修改失败，缺少必要参数");
			}
		}
		if (title == null || title == "") {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (content == null || content == "") {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (uid == null) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (author == null || author == "") {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (stuId == null) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		if (createtime == null) {
			return ResultUtil.error(400, "修改失败，缺少必要参数");
		}
		
		try {
			documentService.updateDocument(document);
			return ResultUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "修改失败，错误原因："+e.getMessage());
		}
		
	}
	
	
	
	/**
	* @Title: getAbilities
	* @Description: TODO(根据学生id和类型获取各种能力记录)
	* @param @param queryModel
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	*/
	@RequestMapping(value="/ability/getAbilities",method= RequestMethod.POST)
	@ResponseBody
	public Object getAbilities(@RequestBody QueryModel queryModel) {
		String query = queryModel.getQuery();
		Integer pageNum = queryModel.getPageNum();
		Integer pageSize = queryModel.getPageSize();
		Integer stuId = queryModel.getStuId();
		Integer type = queryModel.getType();
		Integer abcataId = queryModel.getAbcataId();
		if (pageNum <= 0 || pageSize <= 0) {
			return ResultUtil.error(400, "请求参数错误，提示：需要pageNum>0 pageSize>0");
		}
		if (stuId <= 0) {
			return ResultUtil.error(400, "请求参数错误，提示：stuId错误");
		}
		if (type != null) {
			if (type > 3 || type < 1) {
				return ResultUtil.error(400, "请求参数错误，提示：type参数错误");
			}
		}
		if (abcataId == null || abcataId <= 0) {
			return ResultUtil.error(400, "请求参数错误，提示：abcataId参数缺少");
		}
		PageInfo<BDocument> abilities;
		try {
			abilities = documentService.getAbilities(query,pageNum,pageSize,stuId,type,abcataId);
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("abilities", abilities);
			return ResultUtil.success(map);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "获取数据失败，失败原因："+e.getMessage());
		}
	}
	
	
	@RequestMapping(value="/abilitiesCata/getAbilitiesCata",method= RequestMethod.POST)
	@ResponseBody
	public Object getAbilitiesCata(@RequestBody QueryModel queryModel) {
		Integer parentId = queryModel.getParentId();
		List<BAbilitiesCata> abilitiesCata;
		try {
			abilitiesCata = abilitiesCataService.getAbilitiesCata(parentId);
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("abilitiesCata", abilitiesCata);
			return ResultUtil.success(map);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "获取数据失败，失败原因："+e.getMessage());
		}
		
	}

	
	
	@RequestMapping(value="/ability/getAllAbilitiesCount",method= RequestMethod.POST)
	@ResponseBody
	public Object getAllAbilitiesCount(@RequestBody QueryModel queryModel) {
		Integer stuId = queryModel.getStuId();
		if (stuId == null || stuId <= 0) {
			return ResultUtil.error(400, "请求参数错误，提示：stuId错误");
		}
		try {
			HashMap<String,Object> valMap = new HashMap<String,Object>();
			valMap.put("pageIndex", 0);
			valMap.put("pageSize", 10000000);
			valMap.put("query", "");
			valMap.put("stuId", stuId);
			valMap.put("abcataId", 0);
			ListResult listResult = documentService.allDocumentList(valMap);
			List<BDocument> list = listResult.getList();
			HashMap<Integer,Integer> pointMap = new HashMap<Integer,Integer>();
			for (BDocument bDocument : list) {
				if (pointMap.containsKey(bDocument.getAbcataId())) {
					int nowSize = pointMap.get(bDocument.getAbcataId())+1;
					pointMap.put(bDocument.getAbcataId(), nowSize);
				}else {
					pointMap.put(bDocument.getAbcataId(), 1);
				}
			}
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("abilitiesCount", pointMap);
			return ResultUtil.success(map);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400, "获取数据失败，失败原因："+e.getMessage());
		}
		
	}
	
	
	
	
	
	
	
	

}
