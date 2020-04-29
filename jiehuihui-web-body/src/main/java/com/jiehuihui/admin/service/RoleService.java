package com.jiehuihui.admin.service;

import com.jiehuihui.admin.req.AddUpdateRoleParam;
import com.jiehuihui.admin.req.AddUpdateRolePermissionParam;
import com.jiehuihui.admin.req.DeleteRoleParam;
import com.jiehuihui.admin.req.GetRolePageParam;
import com.jiehuihui.common.utils.RResult;

/**
 * (Role)表服务接口
 *
 * @author zhuang
 * @since 2020-04-16 21:56:13
 */
public interface RoleService {

    //获取角色
    RResult getRole(RResult result);

    //获取一条角色
    RResult getRoleByssid(RResult result, DeleteRoleParam param);

    //获取角色，分页
    RResult getRolePage(RResult result, GetRolePageParam param);

    //添加角色
    RResult addRole(RResult result, AddUpdateRoleParam param);

    //修改角色
    RResult updateRole(RResult result, AddUpdateRoleParam param);

    //删除角色
    RResult deleteRole(RResult result, DeleteRoleParam param);

    //新增角色权限
    RResult addRolePermission(RResult result, AddUpdateRolePermissionParam param);
}