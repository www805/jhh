package com.jiehuihui.admin.mapper;

import com.jiehuihui.common.entity.Role;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiehuihui.common.entity.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Role)表数据库访问层
 *
 * @author zhuang
 * @since 2020-04-16 21:56:13
 */
public interface RoleMapper extends BaseMapper<Role> {

    IPage<Role> getRolePage(Page<?> page, @Param("ew") Wrapper wrapper);

    IPage<Role> selectRoleToPage(Page<?> page, @Param("ew") Wrapper wrapper);

    int selectRoleCount(@Param("ew") Wrapper wrapper);

    List<Permission> getRoleToPermission(@Param("ew") Wrapper wrapper);
}