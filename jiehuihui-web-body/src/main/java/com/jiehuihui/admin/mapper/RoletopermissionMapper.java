package com.jiehuihui.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiehuihui.common.entity.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Role)表数据库访问层
 *
 * @author zhuang
 * @since 2020-04-16 21:56:13
 */
public interface RoletopermissionMapper extends BaseMapper<RolePermission> {

    int addRoletopermission(@Param("id") Long id, @Param("RolePermissionList") List<Long> RolePermissionList);


}