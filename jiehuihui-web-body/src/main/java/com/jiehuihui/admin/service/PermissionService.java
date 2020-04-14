package com.jiehuihui.admin.service;

import com.jiehuihui.admin.req.AddUpdatePermissionParam;
import com.jiehuihui.admin.req.DeletePermissionParam;
import com.jiehuihui.admin.req.GetPermissionPageParam;
import com.jiehuihui.common.utils.RResult;

/**
 * (Permission)权限表服务接口
 *
 * @author zhuang
 * @since 2020-04-12 20:58:38
 */
public interface PermissionService {

    //获取权限
    RResult getPermission(RResult result);

    //获取一条权限
    RResult getPermissionByssid(RResult result, DeletePermissionParam param);

    //获取权限，分页
    RResult getPermissionPage(RResult result, GetPermissionPageParam param);

    //添加权限
    RResult addPermission(RResult result, AddUpdatePermissionParam param);

    //修改权限
    RResult updatePermission(RResult result, AddUpdatePermissionParam param);

    //删除权限
    RResult deletePermission(RResult result, DeletePermissionParam param);

}