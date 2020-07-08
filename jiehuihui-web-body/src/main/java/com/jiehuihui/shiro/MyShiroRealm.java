package com.jiehuihui.shiro;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jiehuihui.admin.mapper.UserMapper;
import com.jiehuihui.common.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MyShiroRealm extends ShiroRealm {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void setName(String name) {
        super.setName("MyShiroRealm");
    }

    /**
     * 执行认证逻辑（身份验证，登录）
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //获取用户名和密码
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        //根据登录用户名查询用户
        String username = usernamePasswordToken.getUsername();
        String password = new String(usernamePasswordToken.getPassword());

        //判断用户是否存在，用户密码是否和输入的一致
//        if(!"root".equalsIgnoreCase(username) || !"90b79457e57f27851fed6b010288fdf7".equalsIgnoreCase(password)){
//            return null;
//        }

        UpdateWrapper<User> ew = new UpdateWrapper<>();
        ew.eq("userlogin", username);
        ew.eq("password", password);
        List<User> users = userMapper.getUserList(ew);

//        User user = new User();
//        user.setUserlogin(username);
//        user.setPassword(password);
//        user.setState(1);

        if(users.size() > 0){
            User user = users.get(0);
            //判断账号状态是否正常
            if (user.getState() == 1) {
                //返回构造安全数据构造方法
                ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUserlogin());
                SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                        user,
                        user.getPassword(),
                        credentialsSalt,
                        getName()
                );
                return simpleAuthenticationInfo;
            }
        }

        return null;
    }

}
