package com.babydays.dao;

import com.babydays.model.BPermission;
import com.babydays.model.BPermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BPermissionDao {
    int countByExample(BPermissionExample example);

    int deleteByExample(BPermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BPermission record);

    int insertSelective(BPermission record);

    List<BPermission> selectByExample(BPermissionExample example);

    BPermission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BPermission record, @Param("example") BPermissionExample example);

    int updateByExample(@Param("record") BPermission record, @Param("example") BPermissionExample example);

    int updateByPrimaryKeySelective(BPermission record);

    int updateByPrimaryKey(BPermission record);
}