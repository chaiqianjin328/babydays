package com.babydays.dao;

import com.babydays.model.BDoctor;
import com.babydays.model.BDoctorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BDoctorDao {
    int countByExample(BDoctorExample example);

    int deleteByExample(BDoctorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BDoctor record);

    int insertSelective(BDoctor record);

    List<BDoctor> selectByExample(BDoctorExample example);

    BDoctor selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BDoctor record, @Param("example") BDoctorExample example);

    int updateByExample(@Param("record") BDoctor record, @Param("example") BDoctorExample example);

    int updateByPrimaryKeySelective(BDoctor record);

    int updateByPrimaryKey(BDoctor record);
}