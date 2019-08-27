package com.babydays.dao;

import com.babydays.model.BAdmin;
import com.babydays.model.BAdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BAdminDao {
    int countByExample(BAdminExample example);

    int deleteByExample(BAdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BAdmin record);

    int insertSelective(BAdmin record);

    List<BAdmin> selectByExample(BAdminExample example);

    BAdmin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BAdmin record, @Param("example") BAdminExample example);

    int updateByExample(@Param("record") BAdmin record, @Param("example") BAdminExample example);

    int updateByPrimaryKeySelective(BAdmin record);

    int updateByPrimaryKey(BAdmin record);
}