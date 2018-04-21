package com.kkwrite.gallery.mapper.product;

import com.kkwrite.gallery.pojo.product.GlyProductType;
import com.kkwrite.gallery.pojo.product.GlyProductTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GlyProductTypeMapper {
    int countByExample(GlyProductTypeExample example);

    int deleteByExample(GlyProductTypeExample example);

    int deleteByPrimaryKey(Integer productTypeId);

    int insert(GlyProductType record);

    int insertSelective(GlyProductType record);

    List<GlyProductType> selectByExample(GlyProductTypeExample example);

    GlyProductType selectByPrimaryKey(Integer productTypeId);
    
    List<GlyProductType> selectByPrimaryKeys(int[] productTypeId);
    
    List<GlyProductType> selectSelective(GlyProductType param);

    int updateByExampleSelective(@Param("record") GlyProductType record, @Param("example") GlyProductTypeExample example);

    int updateByExample(@Param("record") GlyProductType record, @Param("example") GlyProductTypeExample example);

    int updateByPrimaryKeySelective(GlyProductType record);

    int updateByPrimaryKey(GlyProductType record);
}