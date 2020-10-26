package com.artPark.common.mapper;

import com.artPark.common.model.SysUserModel;
import com.artPark.common.model.SysUserModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserModelMapper {
    int countByExample(SysUserModelExample example);

    int deleteByExample(SysUserModelExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(SysUserModel record);

    int insertSelective(SysUserModel record);

    List<SysUserModel> selectByExample(SysUserModelExample example);

    SysUserModel selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") SysUserModel record, @Param("example") SysUserModelExample example);

    int updateByExample(@Param("record") SysUserModel record, @Param("example") SysUserModelExample example);

    int updateByPrimaryKeySelective(SysUserModel record);

    int updateByPrimaryKey(SysUserModel record);
}