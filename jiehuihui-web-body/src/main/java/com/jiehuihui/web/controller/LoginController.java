package com.jiehuihui.web.controller;

import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.entity.User;
import com.jiehuihui.shiro.ShiroSessionListener;
import com.jiehuihui.common.utils.RResult;
import com.jiehuihui.web.req.LoginParam;
import com.jiehuihui.web.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/login")
//@CrossOrigin(origins = {"http://localhost:9012","http://localhost:8080"}, maxAge = 3600)
@CrossOrigin
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private ShiroSessionListener shiroSessionListener;

    /**
     * 用户登录
     * @param param
     * @return
     */
    @PostMapping("/getlogin")
    public RResult getlogin(HttpServletRequest request, @RequestBody @Validated LoginParam param){
        RResult<User> result = new RResult<>();
        String authorization = request.getHeader("Authorization");
        if(StringUtils.isNoneBlank(authorization)){
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
        }
        return loginService.getlogin(result,param);
    }

    /**
     * 注册
     * @param request
     * @param param
     * @return
     */
    @PostMapping("/getregister")
    public RResult getregister(HttpServletRequest request, @RequestBody @Validated(Create.class) LoginParam param){
        RResult<User> result = new RResult<>();
        return loginService.getregister(result,param);
    }

    /**
     * 用户退出
     * @return
     */
    @PostMapping("/getlogout")
    public RResult getlogout(){
        RResult<User> result = new RResult<>();
        return loginService.getlogout(result);
    }




    /**
     * 获取当前在线人数
     * @return
     */
    @RequiresRoles("aRoleName2")
    @GetMapping("/getUserCount")
    public RResult getUserCount(){
        RResult result = new RResult<>();
        result.changeToTrue(shiroSessionListener.getSessionCount());
        return result;
    }

}
