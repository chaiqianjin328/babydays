package com.babydays.service;

import com.babydays.model.BValidate;

public interface ValidateService {

	BValidate selectValidateByToken(String token) throws Exception ;

	void addValidate(BValidate validate) throws Exception;

	void updateValidate(BValidate existValidate) throws Exception;

	void deleteByToken(String token) throws Exception;

}
