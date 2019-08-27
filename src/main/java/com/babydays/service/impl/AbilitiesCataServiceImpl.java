package com.babydays.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babydays.dao.BAbilitiesCataDao;
import com.babydays.model.BAbilitiesCata;
import com.babydays.model.BAbilitiesCataExample;
import com.babydays.model.BAbilitiesCataExample.Criteria;
import com.babydays.service.AbilitiesCataService;
@Service
public class AbilitiesCataServiceImpl implements AbilitiesCataService {

	
	@Autowired
	private BAbilitiesCataDao abilitiesCataDao;

	@Override
	public List<BAbilitiesCata> getAbilitiesCata(Integer parentId) throws Exception {
		BAbilitiesCataExample abilitiesCataExample = new BAbilitiesCataExample();
		Criteria criteria = abilitiesCataExample.createCriteria();
		if (parentId>=0) {
			criteria.andParentIdEqualTo(parentId);
			List<BAbilitiesCata> list = abilitiesCataDao.selectByExample(abilitiesCataExample);
			return list;
		}
		return null;
	}
	
	
	
}
