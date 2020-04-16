package com.jiehuihui.admin.mapper;

import com.jiehuihui.common.entity.Permission;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiehuihui.common.entity.PermissionEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Permission)表数据库访问层
 *
 * @author zhuang
 * @since 2020-04-12 20:58:38
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    //分页查询
    IPage<Permission> getPermissionPage(Page<?> page, @Param("ew") Wrapper wrapper);

    //列表查询
    List<PermissionEntity> getPermissionList(@Param("ew") Wrapper wrapper);

    int selectPermissionCount(@Param("ew") Wrapper wrapper);

}