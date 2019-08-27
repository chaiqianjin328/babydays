package com.babydays.service;

import java.util.List;

import com.babydays.model.BAbilitiesCata;

public interface AbilitiesCataService {

	List<BAbilitiesCata> getAbilitiesCata(Integer parentId) throws Exception;

}
