package com.kkwrite.gallery.mapper.cart;

import com.kkwrite.gallery.pojo.cart.GlyCart;
import com.kkwrite.gallery.pojo.cart.GlyCartExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GlyCartMapper {
    int countByExample(GlyCartExample example);

    int deleteByExample(GlyCartExample example);

    int deleteByPrimaryKey(String cartCode);

    int insert(GlyCart record);

    int insertSelective(GlyCart record);

    List<GlyCart> selectByExample(GlyCartExample example);

    GlyCart selectByPrimaryKey(String cartCode);
    
    GlyCart selectByUserId(int userId);

    int updateByExampleSelective(@Param("record") GlyCart record, @Param("example") GlyCartExample example);

    int updateByExample(@Param("record") GlyCart record, @Param("example") GlyCartExample example);

    int updateByPrimaryKeySelective(GlyCart record);

    int updateByPrimaryKey(GlyCart record);
}