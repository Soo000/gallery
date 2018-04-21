package com.kkwrite.gallery.mapper.product;

import com.kkwrite.gallery.pojo.product.GlyProductTypeProductExample;
import com.kkwrite.gallery.pojo.product.GlyProductTypeProductKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GlyProductTypeProductMapper {
    int countByExample(GlyProductTypeProductExample example);

    int deleteByExample(GlyProductTypeProductExample example);

    int deleteByPrimaryKey(GlyProductTypeProductKey key);

    int insert(GlyProductTypeProductKey record);

    int insertSelective(GlyProductTypeProductKey record);

    List<GlyProductTypeProductKey> selectByExample(GlyProductTypeProductExample example);

    int updateByExampleSelective(@Param("record") GlyProductTypeProductKey record, @Param("example") GlyProductTypeProductExample example);

    int updateByExample(@Param("record") GlyProductTypeProductKey record, @Param("example") GlyProductTypeProductExample example);
}