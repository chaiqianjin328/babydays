package com.babydays.dao;

import com.babydays.model.BCalendar;
import com.babydays.model.BCalendarExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BCalendarDao {
    int countByExample(BCalendarExample example);

    int deleteByExample(BCalendarExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BCalendar record);

    int insertSelective(BCalendar record);

    List<BCalendar> selectByExample(BCalendarExample example);

    BCalendar selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BCalendar record, @Param("example") BCalendarExample example);

    int updateByExample(@Param("record") BCalendar record, @Param("example") BCalendarExample example);

    int updateByPrimaryKeySelective(BCalendar record);

    int updateByPrimaryKey(BCalendar record);
}