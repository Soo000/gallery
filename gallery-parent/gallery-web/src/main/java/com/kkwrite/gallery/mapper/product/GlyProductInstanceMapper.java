package com.kkwrite.gallery.mapper.product;

import com.kkwrite.gallery.pojo.product.GlyProductInstance;
import com.kkwrite.gallery.pojo.product.GlyProductInstanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GlyProductInstanceMapper {
    int countByExample(GlyProductInstanceExample example);

    int deleteByExample(GlyProductInstanceExample example);

    int deleteByPrimaryKey(String productInstanceId);
    
    int deleteByOrderCode(String orderCode);

    int insert(GlyProductInstance record);

    int insertSelective(GlyProductInstance record);

    List<GlyProductInstance> selectByExample(GlyProductInstanceExample example);

    GlyProductInstance selectByPrimaryKey(String productInstanceId);

    int updateByExampleSelective(@Param("record") GlyProductInstance record, @Param("example") GlyProductInstanceExample example);

    int updateByExample(@Param("record") GlyProductInstance record, @Param("example") GlyProductInstanceExample example);

    int updateByPrimaryKeySelective(GlyProductInstance record);

    int updateByPrimaryKey(GlyProductInstance record);
}