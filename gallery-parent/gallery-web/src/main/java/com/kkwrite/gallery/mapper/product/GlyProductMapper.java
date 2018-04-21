package com.kkwrite.gallery.mapper.product;

import com.kkwrite.gallery.pojo.product.GlyProduct;
import com.kkwrite.gallery.pojo.product.GlyProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GlyProductMapper {
    int countByExample(GlyProductExample example);

    int deleteByExample(GlyProductExample example);

    int deleteByPrimaryKey(Integer productId);

    int insert(GlyProduct record);

    int insertSelective(GlyProduct record);

    List<GlyProduct> selectByExample(GlyProductExample example);

    GlyProduct selectByPrimaryKey(Integer productId);

    int updateByExampleSelective(@Param("record") GlyProduct record, @Param("example") GlyProductExample example);

    int updateByExample(@Param("record") GlyProduct record, @Param("example") GlyProductExample example);

    int updateByPrimaryKeySelective(GlyProduct record);

    int updateByPrimaryKey(GlyProduct record);
}