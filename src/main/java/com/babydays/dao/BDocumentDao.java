package com.babydays.dao;

import com.babydays.model.BDocument;
import com.babydays.model.BDocumentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BDocumentDao {
    int countByExample(BDocumentExample example);

    int deleteByExample(BDocumentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BDocument record);

    int insertSelective(BDocument record);

    List<BDocument> selectByExample(BDocumentExample example);

    BDocument selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BDocument record, @Param("example") BDocumentExample example);

    int updateByExample(@Param("record") BDocument record, @Param("example") BDocumentExample example);

    int updateByPrimaryKeySelective(BDocument record);

    int updateByPrimaryKey(BDocument record);

	int insertForeach(List<BDocument> documentList);
    
    
}