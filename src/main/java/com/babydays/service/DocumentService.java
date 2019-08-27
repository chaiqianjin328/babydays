package com.babydays.service;

import java.util.HashMap;
import java.util.List;

import com.babydays.model.BDocument;
import com.babydays.model.BStudent;
import com.babydays.model.ListResult;
import com.github.pagehelper.PageInfo;

public interface DocumentService {

	void addDocument(BDocument document) throws Exception;

	void updateDocument(BDocument document) throws Exception;

	void deleteDocumentById(Integer id) throws Exception;

	PageInfo<BDocument> getDocuments(String query, Integer pageNum, Integer pageSize, Integer stuId, String createtime) throws Exception;

	ListResult documentList(HashMap<String, Object> valMap);

	BDocument selectDocumentById(Integer id);

	PageInfo<BDocument> getPictures(String query, Integer pageNum, Integer pageSize, Integer stuId) throws Exception;

	PageInfo<BDocument> getVideos(String query, Integer pageNum, Integer pageSize, Integer stuId) throws Exception;

	PageInfo<BDocument> getAbilities(String query, Integer pageNum, Integer pageSize, Integer stuId, Integer type, Integer abcataId) throws Exception;

	ListResult allDocumentList(HashMap<String, Object> valMap);

	void addDocumentByStudentList(List<BStudent> list, BDocument document) throws Exception;

}
