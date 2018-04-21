package com.kkwrite.gallery.mapper.product;

import com.kkwrite.gallery.pojo.product.GlyProductType;
import com.kkwrite.gallery.pojo.product.GlyRProductTypeProductExample;
import com.kkwrite.gallery.pojo.product.GlyRProductTypeProductKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GlyRProductTypeProductMapper {
    int countByExample(GlyRProductTypeProductExample example);

    int deleteByExample(GlyRProductTypeProductExample example);

    int deleteByPrimaryKey(GlyRProductTypeProductKey key);

    int insert(GlyRProductTypeProductKey record);

    int insertSelective(GlyRProductTypeProductKey record);
    
    List<GlyRProductTypeProductKey> selectProductIdsByProductTypes(List<GlyProductType> records);

    List<GlyRProductTypeProductKey> selectByExample(GlyRProductTypeProductExample example);

    int updateByExampleSelective(@Param("record") GlyRProductTypeProductKey record, @Param("example") GlyRProductTypeProductExample example);

    int updateByExample(@Param("record") GlyRProductTypeProductKey record, @Param("example") GlyRProductTypeProductExample example);
}