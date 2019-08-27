package com.babydays.dao;

import com.babydays.model.BTeacher;
import com.babydays.model.BTeacherExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BTeacherDao {
    int countByExample(BTeacherExample example);

    int deleteByExample(BTeacherExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BTeacher record);

    int insertSelective(BTeacher record);

    List<BTeacher> selectByExample(BTeacherExample example);

    BTeacher selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BTeacher record, @Param("example") BTeacherExample example);

    int updateByExample(@Param("record") BTeacher record, @Param("example") BTeacherExample example);

    int updateByPrimaryKeySelective(BTeacher record);

    int updateByPrimaryKey(BTeacher record);
}