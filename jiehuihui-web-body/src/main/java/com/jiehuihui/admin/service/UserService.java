package com.jiehuihui.admin.service;

import com.jiehuihui.admin.req.AddUpdateUserParam;
import com.jiehuihui.admin.req.DeleteUserParam;
import com.jiehuihui.admin.req.GetUserPageParam;
import com.jiehuihui.common.utils.RResult;

/**
 * (User)表服务接口
 *
 * @author zhuang
 * @since 2020-04-19 09:54:34
 */
public interface UserService {

    //获取用户信息
    RResult getUserList(RResult result);

    //获取一条用户信息
    RResult getUserByssid(RResult result, DeleteUserParam param);

    //获取用户信息，分页
    RResult getUserPage(RResult result, GetUserPageParam param);

    //添加用户信息
    RResult addUser(RResult result, AddUpdateUserParam param);

    //修改用户信息
    RResult updateUser(RResult result, AddUpdateUserParam param);

    //删除用户信息
    RResult deleteUser(RResult result, DeleteUserParam param);

}