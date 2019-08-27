package com.babydays.service;

import java.util.List;

import com.babydays.model.BUser;

public interface UserService {

	BUser selectUserByUandP(BUser user);

	List<BUser> selectAdminByUsername(String username);

	
	
	
	
}
