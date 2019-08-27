package com.babydays.dao;

import com.babydays.model.BClass;
import com.babydays.model.BClassExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BClassDao {
    int countByExample(BClassExample example);

    int deleteByExample(BClassExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BClass record);

    int insertSelective(BClass record);

    List<BClass> selectByExample(BClassExample example);

    BClass selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BClass record, @Param("example") BClassExample example);

    int updateByExample(@Param("record") BClass record, @Param("example") BClassExample example);

    int updateByPrimaryKeySelective(BClass record);

    int updateByPrimaryKey(BClass record);
}