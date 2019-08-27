package com.babydays.service;



import java.util.HashMap;

import com.babydays.model.BAdmin;
import com.babydays.model.BUser;
import com.babydays.model.ListResult;
import com.github.pagehelper.PageInfo;

/**
* @ClassName: AdminService
* @Description: TODO(AdminService接口)
* @author chaiqianjin
* @date 2018年8月8日
*
*/
public interface AdminService {

	
	BAdmin selectAdminById(Integer detailsId);

	void insertSelective(BAdmin admin, BUser user) throws Exception;

	void resetStatusAdmin(Integer id) throws Exception;

	void updateAdmin(BAdmin admin) throws Exception;

	PageInfo<BAdmin> getAdmins(String query, Integer pageNum, Integer pageSize) throws Exception;

	ListResult adminList(HashMap<String, Object> valMap);



}
