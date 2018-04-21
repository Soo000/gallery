package com.kkwrite.gallery.mapper.evaluate;

import com.kkwrite.gallery.pojo.evaluate.GlyEvaluate;
import com.kkwrite.gallery.pojo.evaluate.GlyEvaluateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GlyEvaluateMapper {
    int countByExample(GlyEvaluateExample example);

    int deleteByExample(GlyEvaluateExample example);

    int deleteByPrimaryKey(Integer evaluateId);

    int insert(GlyEvaluate record);

    int insertSelective(GlyEvaluate record);

    List<GlyEvaluate> selectByExample(GlyEvaluateExample example);

    GlyEvaluate selectByPrimaryKey(Integer evaluateId);

    int updateByExampleSelective(@Param("record") GlyEvaluate record, @Param("example") GlyEvaluateExample example);

    int updateByExample(@Param("record") GlyEvaluate record, @Param("example") GlyEvaluateExample example);

    int updateByPrimaryKeySelective(GlyEvaluate record);

    int updateByPrimaryKey(GlyEvaluate record);
}