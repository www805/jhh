package com.jiehuihui.shiro;

import com.jiehuihui.common.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class ShiroRealm extends AuthorizingRealm {

    @Override
    public void setName(String name) {
        super.setName("ShiroRealm");
    }

    /**
     * 执行授权逻辑（权限配置）
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取安全数据
        User user = (User) principalCollection.getPrimaryPrincipal();

        //获取角色，权限

        //资源对象
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        //添加资源的授权字符串
        simpleAuthorizationInfo.addStringPermission("user:getUser");
        simpleAuthorizationInfo.addRole("user");

        return simpleAuthorizationInfo;
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
        User user = new User();
        user.setUserlogin(username);
        user.setPassword(password);
        user.setState(1);

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

        return null;
    }
}
