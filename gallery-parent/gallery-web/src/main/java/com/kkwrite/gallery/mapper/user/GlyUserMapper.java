package com.kkwrite.gallery.mapper.user;

import com.kkwrite.gallery.pojo.user.GlyUser;
import com.kkwrite.gallery.pojo.user.GlyUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GlyUserMapper {
    int countByExample(GlyUserExample example);

    int deleteByExample(GlyUserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(GlyUser record);

    int insertSelective(GlyUser record);

    List<GlyUser> selectByExample(GlyUserExample example);

    GlyUser selectByPrimaryKey(Integer userId);
    
    List<GlyUser> selectSelective(GlyUser record);

    int updateByExampleSelective(@Param("record") GlyUser record, @Param("example") GlyUserExample example);

    int updateByExample(@Param("record") GlyUser record, @Param("example") GlyUserExample example);

    int updateByPrimaryKeySelective(GlyUser record);

    int updateByPrimaryKey(GlyUser record);
}