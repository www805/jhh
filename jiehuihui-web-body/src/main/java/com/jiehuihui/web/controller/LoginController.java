package com.jiehuihui.web.controller;

import com.jiehuihui.common.entity.User;
import com.jiehuihui.shiro.ShiroSessionListener;
import com.jiehuihui.common.utils.RResult;
import com.jiehuihui.web.req.LoginParam;
import com.jiehuihui.web.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
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
    public RResult getlogin(@RequestBody LoginParam param){
        RResult<User> result = new RResult<>();
        return loginService.getlogin(result,param);
    }


    /**
     * 用户退出
     * @return
     */
    @PostMapping("/getlogout")
    public RResult getlogout(){
        RResult<User> result = new RResult<>();
        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        result.changeToTrue();
        return result;
    }




    /**
     * 获取当前在线人数
     * @return
     */
    @GetMapping("/getUserCount")
    public RResult getUserCount(){
        RResult result = new RResult<>();
        result.changeToTrue(shiroSessionListener.getSessionCount());
        return result;
    }

}
