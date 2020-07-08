package com.jiehuihui.web.service.impl;

import com.jiehuihui.common.utils.LogUtil;
import com.jiehuihui.common.utils.RResult;
import com.jiehuihui.web.req.LoginParam;
import com.jiehuihui.web.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public RResult getlogin(RResult result, LoginParam param) {



        String userlogin = param.getUserlogin();
        String password = param.getPassword();

        try {
            //md5加密密码
            password = new Md5Hash(password, userlogin, 10).toString();
            //构造登录令牌
            UsernamePasswordToken token = new UsernamePasswordToken(userlogin, password);
            //获取Subject
            Subject subject = SecurityUtils.getSubject();
            //调用Login方法，进入realm认证
            subject.login(token);
            //获取sessionId
            String sessionId = (String) subject.getSession().getId();
            //登录成功，返回sessionId

            Object principal = SecurityUtils.getSubject().getPrincipal();
            HashMap<String, Object> map = new HashMap<>();
            map.put("user", principal);
            map.put("token", sessionId);

            result.changeToTrue(map);
            result.setMessage("登录成功！");
            LogUtil.intoLog("用户：" + userlogin + " 登录成功！");
        } catch (DisabledAccountException e) {
            result.setMessage("该用户已被禁用");
            LogUtil.intoLog("用户：" + userlogin + " 登录失败，该用户已被禁用");
        }catch (Exception e) {
            result.setMessage("用户名或密码错误");
            LogUtil.intoLog("用户：" + userlogin + " 登录失败，用户名或密码错误");
        }

        return result;
    }

    @Override
    public RResult getlogout(RResult result, LoginParam param) {
        return null;
    }
}
