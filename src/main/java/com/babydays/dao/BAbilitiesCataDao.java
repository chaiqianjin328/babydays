package com.babydays.dao;

import com.babydays.model.BAbilitiesCata;
import com.babydays.model.BAbilitiesCataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BAbilitiesCataDao {
    int countByExample(BAbilitiesCataExample example);

    int deleteByExample(BAbilitiesCataExample example);

    int deleteByPrimaryKey(Integer cataId);

    int insert(BAbilitiesCata record);

    int insertSelective(BAbilitiesCata record);

    List<BAbilitiesCata> selectByExample(BAbilitiesCataExample example);

    BAbilitiesCata selectByPrimaryKey(Integer cataId);

    int updateByExampleSelective(@Param("record") BAbilitiesCata record, @Param("example") BAbilitiesCataExample example);

    int updateByExample(@Param("record") BAbilitiesCata record, @Param("example") BAbilitiesCataExample example);

    int updateByPrimaryKeySelective(BAbilitiesCata record);

    int updateByPrimaryKey(BAbilitiesCata record);
}