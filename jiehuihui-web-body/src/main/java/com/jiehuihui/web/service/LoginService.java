package com.jiehuihui.web.service;


import com.jiehuihui.common.entity.User;
import com.jiehuihui.common.utils.RResult;
import com.jiehuihui.web.req.LoginParam;

public interface LoginService {

    //用户登录
    public RResult getlogin(RResult result, LoginParam param);

    //用户退出
    public RResult getlogout(RResult result);

    RResult getregister(RResult<User> result, LoginParam param);
}
