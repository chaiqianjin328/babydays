package com.babydays.dao;

import com.babydays.model.BStudent;
import com.babydays.model.BStudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BStudentDao {
    int countByExample(BStudentExample example);

    int deleteByExample(BStudentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BStudent record);

    int insertSelective(BStudent record);

    List<BStudent> selectByExample(BStudentExample example);

    BStudent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BStudent record, @Param("example") BStudentExample example);

    int updateByExample(@Param("record") BStudent record, @Param("example") BStudentExample example);

    int updateByPrimaryKeySelective(BStudent record);

    int updateByPrimaryKey(BStudent record);
    
}