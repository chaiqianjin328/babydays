package com.babydays.dao;

import com.babydays.model.BLeave;
import com.babydays.model.BLeaveExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BLeaveDao {
    int countByExample(BLeaveExample example);

    int deleteByExample(BLeaveExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BLeave record);

    int insertSelective(BLeave record);

    List<BLeave> selectByExample(BLeaveExample example);

    BLeave selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BLeave record, @Param("example") BLeaveExample example);

    int updateByExample(@Param("record") BLeave record, @Param("example") BLeaveExample example);

    int updateByPrimaryKeySelective(BLeave record);

    int updateByPrimaryKey(BLeave record);
}