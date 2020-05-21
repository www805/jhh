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
        Object user = principalCollection.getPrimaryPrincipal();

        //获取角色，权限

        //资源对象
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        //添加资源的授权字符串
        simpleAuthorizationInfo.addStringPermission("user:getUser");
        simpleAuthorizationInfo.addRole("aRoleName");

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
        return null;
    }
}
