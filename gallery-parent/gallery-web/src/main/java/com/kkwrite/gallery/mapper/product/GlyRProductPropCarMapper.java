package com.kkwrite.gallery.mapper.product;

import com.kkwrite.gallery.pojo.product.GlyRProductPropCarExample;
import com.kkwrite.gallery.pojo.product.GlyRProductPropCarKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GlyRProductPropCarMapper {
    int countByExample(GlyRProductPropCarExample example);

    int deleteByExample(GlyRProductPropCarExample example);

    int deleteByPrimaryKey(GlyRProductPropCarKey key);

    int insert(GlyRProductPropCarKey record);

    int insertSelective(GlyRProductPropCarKey record);

    List<GlyRProductPropCarKey> selectByExample(GlyRProductPropCarExample example);
    
    List<GlyRProductPropCarKey> selectSelective(GlyRProductPropCarKey glyRProductPropCarKey);

    int updateByExampleSelective(@Param("record") GlyRProductPropCarKey record, @Param("example") GlyRProductPropCarExample example);

    int updateByExample(@Param("record") GlyRProductPropCarKey record, @Param("example") GlyRProductPropCarExample example);
}