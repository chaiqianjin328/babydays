package com.babydays.dao;

import com.babydays.model.BComment;
import com.babydays.model.BCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BCommentDao {
    int countByExample(BCommentExample example);

    int deleteByExample(BCommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BComment record);

    int insertSelective(BComment record);

    List<BComment> selectByExample(BCommentExample example);

    BComment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BComment record, @Param("example") BCommentExample example);

    int updateByExample(@Param("record") BComment record, @Param("example") BCommentExample example);

    int updateByPrimaryKeySelective(BComment record);

    int updateByPrimaryKey(BComment record);
}