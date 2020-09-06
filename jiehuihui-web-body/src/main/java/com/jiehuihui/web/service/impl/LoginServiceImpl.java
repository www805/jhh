package com.jiehuihui.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jiehuihui.admin.mapper.UserMapper;
import com.jiehuihui.common.entity.User;
import com.jiehuihui.common.utils.LogUtil;
import com.jiehuihui.common.utils.OpenUtil;
import com.jiehuihui.common.utils.RResult;
import com.jiehuihui.web.req.LoginParam;
import com.jiehuihui.web.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {


    @Autowired
    private UserMapper userMapper;

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

            User user = (User) SecurityUtils.getSubject().getPrincipal();
            user.setPassword("");//不在前台显示密码
            HashMap<String, Object> map = new HashMap<>();
            map.put("user", user);
            map.put("token", sessionId);

            result.changeToTrue(map);
            result.setMessage("登录成功！");
            LogUtil.intoLog("用户：" + userlogin + " 登录成功！");
        } catch (DisabledAccountException e) {
            result.setMessage("该用户已被禁用");
            LogUtil.intoLog("用户：" + userlogin + " 登录失败，该用户已被禁用");
        }catch (Exception e) {
            e.printStackTrace();
            result.setMessage("用户名或密码错误");
            LogUtil.intoLog("用户：" + userlogin + " 登录失败，用户名或密码错误");
        }

        return result;
    }

    @Override
    public RResult getlogout(RResult result) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        result.changeToTrue();
        return result;
    }

    @Override
    public RResult getregister(RResult<User> result, LoginParam param) {

        if(!param.getPassword().equals(param.getPassword2())){
            result.setMessage("两次密码必须一致");
            return result;
        }

        UpdateWrapper<User> ew = new UpdateWrapper<>();
        ew.eq("userlogin", param.getUserlogin());
        List<User> users = userMapper.selectList(ew);
        if(users.size() > 0){
            result.setMessage("该用户名已经存在!");
            return result;
        }

        //密码加密
        String password = param.getPassword();
        password = new Md5Hash(password, param.getUserlogin(), 10).toString();

        User user = new User();
        user.setUserlogin(param.getUserlogin());
        user.setPassword(password);
        user.setSsid(OpenUtil.getUUID_32());
        user.setState(1);

        int insert = userMapper.insert(user);
        if(insert == 1){
            result.changeToTrue();
            result.setMessage("用户注册成功！即将跳转到个人中心...");
        }

        return result;
    }
}
