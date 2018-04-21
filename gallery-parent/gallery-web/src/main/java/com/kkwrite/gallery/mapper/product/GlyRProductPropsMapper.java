package com.kkwrite.gallery.mapper.product;

import com.kkwrite.gallery.pojo.product.GlyRProductPropsExample;
import com.kkwrite.gallery.pojo.product.GlyRProductPropsKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GlyRProductPropsMapper {
    int countByExample(GlyRProductPropsExample example);

    int deleteByExample(GlyRProductPropsExample example);

    int deleteByPrimaryKey(GlyRProductPropsKey key);

    int insert(GlyRProductPropsKey record);

    int insertSelective(GlyRProductPropsKey record);

    List<GlyRProductPropsKey> selectByExample(GlyRProductPropsExample example);

    int updateByExampleSelective(@Param("record") GlyRProductPropsKey record, @Param("example") GlyRProductPropsExample example);

    int updateByExample(@Param("record") GlyRProductPropsKey record, @Param("example") GlyRProductPropsExample example);
}