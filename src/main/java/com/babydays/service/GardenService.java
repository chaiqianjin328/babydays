package com.babydays.service;

import java.util.HashMap;
import java.util.List;

import com.babydays.model.BGarden;
import com.babydays.model.ListResult;
import com.github.pagehelper.PageInfo;

public interface GardenService {

	void addGarden(BGarden garden) throws Exception;

	void deleteGarden(Integer id) throws Exception;

	void editGarden(BGarden garden) throws Exception;

	ListResult gardenList(HashMap<String, Object> valMap);

	List<BGarden> getAllGarden();

	PageInfo<BGarden> getGardens(String query, Integer pageNum, Integer pageSize) throws Exception ;

	BGarden directorAppointIsOrNot(Integer gardenId);

	BGarden getGardenById(Integer gardenId);

}
