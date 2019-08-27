package com.babydays.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babydays.dao.BValidateDao;
import com.babydays.model.BValidate;
import com.babydays.model.BValidateExample;
import com.babydays.model.BValidateExample.Criteria;
import com.babydays.service.ValidateService;
@Service
public class ValidateServiceImpl implements ValidateService {

	
	@Autowired
	private BValidateDao validateDao;
	
	

	@Override
	public BValidate selectValidateByToken(String token) throws Exception {
		BValidateExample validateExample = new BValidateExample();
		Criteria criteria = validateExample.createCriteria();
		criteria.andTokenEqualTo(token);
		List<BValidate> list = validateDao.selectByExample(validateExample);
		if (list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void addValidate(BValidate validate) throws Exception {
		validateDao.insertSelective(validate);
	}

	@Override
	public void updateValidate(BValidate existValidate) throws Exception {
		validateDao.updateByPrimaryKeySelective(existValidate);
	}

	@Override
	public void deleteByToken(String token) throws Exception {
		BValidateExample validateExample = new BValidateExample();
		Criteria criteria = validateExample.createCriteria();
		criteria.andTokenEqualTo(token);
		validateDao.deleteByExample(validateExample);
	}
	
	
	
}
