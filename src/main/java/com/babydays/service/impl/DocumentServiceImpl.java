package com.babydays.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babydays.dao.BDocumentDao;
import com.babydays.model.BDocument;
import com.babydays.model.BDocumentExample;
import com.babydays.model.BDocumentExample.Criteria;
import com.babydays.model.BStudent;
import com.babydays.model.ListResult;
import com.babydays.service.DocumentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class DocumentServiceImpl implements DocumentService {
	
	
	@Autowired
	private BDocumentDao documentDao;
	
	

	@Override
	public void addDocument(BDocument document) throws Exception {
		documentDao.insertSelective(document);
	}



	@Override
	public void updateDocument(BDocument document) throws Exception {
		documentDao.updateByPrimaryKeySelective(document);
	}



	@Override
	public void deleteDocumentById(Integer id) throws Exception {
		documentDao.deleteByPrimaryKey(id);
		
		//需要添加级联删除
		
	}



	@Override
	public PageInfo<BDocument> getDocuments(String query, Integer pageNum, Integer pageSize, Integer stuId,String createtime) throws Exception {
		PageHelper.startPage(pageNum, pageSize);
		BDocumentExample documentExample = new BDocumentExample();
		documentExample.setOrderByClause("id DESC");
		Criteria criteria = documentExample.createCriteria();
		if (query != null && query != "") {
			criteria.andTitleLike("%"+query+"%");
		}
		if (stuId != null) {
			criteria.andStuIdEqualTo(stuId);
		}
		if (createtime != null && createtime != "") {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateA = createtime + " 00:00:00";
			String dateB = createtime + " 23:59:59";
			Date queryDateA= format.parse(dateA);
			Date queryDateB= format.parse(dateB);
			criteria.andCreatetimeBetween(queryDateA, queryDateB);
		}
		//获取类型为文档档案
		criteria.andTypeEqualTo(1);
		criteria.andAbcataIdEqualTo(0);
		List<BDocument> list = documentDao.selectByExample(documentExample);
		PageInfo<BDocument> pageInfo = new PageInfo<BDocument>(list);
		return pageInfo;
	}



	@Override
	public ListResult documentList(HashMap<String, Object> valMap) {
		Integer pageIndex = (int) valMap.get("pageIndex");
		Integer pageSize = (int) valMap.get("pageSize");
		Integer stuId = (int) valMap.get("stuId");
		Integer type = (int) valMap.get("type");
		String query = (String) valMap.get("query");
		PageHelper.startPage(pageIndex+1,pageSize);
		BDocumentExample documentExample = new BDocumentExample();
		documentExample.setOrderByClause("id DESC");
		Criteria criteria = documentExample.createCriteria();
		if (query != null && query != "") {
			criteria.andTitleLike("%"+query+"%");
		}
		if (stuId != null) {
			if (stuId > 0) {
				criteria.andStuIdEqualTo(stuId);
			}
		}
		criteria.andTypeEqualTo(type);
		criteria.andAbcataIdEqualTo(0);
		List<BDocument> list = documentDao.selectByExample(documentExample);
		PageInfo<BDocument> pageInfo = new PageInfo<BDocument>(list);
		ListResult listResult = new ListResult();
		listResult.setList(pageInfo.getList());
		listResult.setTotal((int) pageInfo.getTotal());
		return listResult;
	}



	@Override
	public BDocument selectDocumentById(Integer id) {
		BDocumentExample documentExample = new BDocumentExample();
		Criteria criteria = documentExample.createCriteria();
		criteria.andIdEqualTo(id);
		List<BDocument> list = documentDao.selectByExample(documentExample);
		if (list.size()>0) {
			return list.get(0);
		}
		return null;
	}



	@Override
	public PageInfo<BDocument> getPictures(String query, Integer pageNum, Integer pageSize, Integer stuId) throws Exception {
		PageHelper.startPage(pageNum, pageSize);
		BDocumentExample documentExample = new BDocumentExample();
		documentExample.setOrderByClause("id DESC");
		Criteria criteria = documentExample.createCriteria();
		if (query != null && query != "") {
			criteria.andTitleLike("%"+query+"%");
		}
		if (stuId != null) {
			criteria.andStuIdEqualTo(stuId);
		}
		//获取类型为图片档案
		criteria.andTypeEqualTo(2);
		criteria.andAbcataIdEqualTo(0);
		List<BDocument> list = documentDao.selectByExample(documentExample);
		PageInfo<BDocument> pageInfo = new PageInfo<BDocument>(list);
		return pageInfo;
	}



	@Override
	public PageInfo<BDocument> getVideos(String query, Integer pageNum, Integer pageSize, Integer stuId) throws Exception {
		PageHelper.startPage(pageNum, pageSize);
		BDocumentExample documentExample = new BDocumentExample();
		documentExample.setOrderByClause("id DESC");
		Criteria criteria = documentExample.createCriteria();
		if (query != null && query != "") {
			criteria.andTitleLike("%"+query+"%");
		}
		if (stuId != null) {
			criteria.andStuIdEqualTo(stuId);
		}
		//获取类型为视频档案
		criteria.andTypeEqualTo(3);
		criteria.andAbcataIdEqualTo(0);
		List<BDocument> list = documentDao.selectByExample(documentExample);
		PageInfo<BDocument> pageInfo = new PageInfo<BDocument>(list);
		return pageInfo;
	}



	@Override
	public PageInfo<BDocument> getAbilities(String query, Integer pageNum, Integer pageSize, Integer stuId,Integer type,Integer abcataId) throws Exception {
		PageHelper.startPage(pageNum, pageSize);
		BDocumentExample documentExample = new BDocumentExample();
		Criteria criteria = documentExample.createCriteria();
		if (query != null && query != "") {
			criteria.andTitleLike("%"+query+"%");
		}
		if (stuId != null) {
			criteria.andStuIdEqualTo(stuId);
		}
		if (type != null) {
			criteria.andTypeEqualTo(type);
		}
		if (abcataId  != null && abcataId >0) {
			criteria.andAbcataIdEqualTo(abcataId);
		}
		List<BDocument> list = documentDao.selectByExample(documentExample);
		PageInfo<BDocument> pageInfo = new PageInfo<BDocument>(list);
		return pageInfo;
	}



	@Override
	public ListResult allDocumentList(HashMap<String, Object> valMap) {
		Integer pageIndex = (int) valMap.get("pageIndex");
		Integer pageSize = (int) valMap.get("pageSize");
		Integer stuId = (int) valMap.get("stuId");
		Integer abcataId = (int) valMap.get("abcataId");
		String query = (String) valMap.get("query");
		PageHelper.startPage(pageIndex+1,pageSize);
		BDocumentExample documentExample = new BDocumentExample();
		documentExample.setOrderByClause("id DESC");
		Criteria criteria = documentExample.createCriteria();
		if (query != null && query != "") {
			criteria.andTitleLike("%"+query+"%");
		}
		if (stuId != null) {
			if (stuId > 0) {
				criteria.andStuIdEqualTo(stuId);
			}
		}
		if (abcataId != null) {
			if (abcataId > 0) {
				criteria.andAbcataIdEqualTo(abcataId);
			}
		}
		List<BDocument> list = documentDao.selectByExample(documentExample);
		PageInfo<BDocument> pageInfo = new PageInfo<BDocument>(list);
		ListResult listResult = new ListResult();
		listResult.setList(pageInfo.getList());
		listResult.setTotal((int) pageInfo.getTotal());
		return listResult;
	}



	@Override
	public void addDocumentByStudentList(List<BStudent> list, BDocument document) throws Exception {
		List<BDocument> documentList = new ArrayList<BDocument>();
		for (int i = 0; i < list.size(); i++) {
			BDocument currentDocument = new BDocument();
			currentDocument.setId(document.getId());
			currentDocument.setTitle(document.getTitle());
			currentDocument.setContent(document.getContent());
			currentDocument.setImgs(document.getImgs());
			currentDocument.setVoices(document.getVoices());
			currentDocument.setVideos(document.getVideos());
			currentDocument.setVimages(document.getVimages());
			currentDocument.setUid(document.getUid());
			currentDocument.setAuthor(document.getAuthor());
			currentDocument.setPosition(document.getPosition());
			currentDocument.setStuId(list.get(i).getId());
			currentDocument.setType(document.getType());
			currentDocument.setCreatetime(document.getCreatetime());
			currentDocument.setAbcataId(document.getAbcataId());
			documentList.add(currentDocument);
		}
		documentDao.insertForeach(documentList);
	}
	
	

}
