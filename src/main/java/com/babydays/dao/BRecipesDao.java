package com.babydays.dao;

import com.babydays.model.BRecipes;
import com.babydays.model.BRecipesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BRecipesDao {
    int countByExample(BRecipesExample example);

    int deleteByExample(BRecipesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BRecipes record);

    int insertSelective(BRecipes record);

    List<BRecipes> selectByExample(BRecipesExample example);

    BRecipes selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BRecipes record, @Param("example") BRecipesExample example);

    int updateByExample(@Param("record") BRecipes record, @Param("example") BRecipesExample example);

    int updateByPrimaryKeySelective(BRecipes record);

    int updateByPrimaryKey(BRecipes record);
}