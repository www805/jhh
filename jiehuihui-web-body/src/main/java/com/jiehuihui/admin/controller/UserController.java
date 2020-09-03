package com.jiehuihui.admin.controller;

import com.jiehuihui.admin.service.UserService;
import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import com.jiehuihui.common.entity.User;
import org.springframework.web.bind.annotation.*;

import com.jiehuihui.admin.req.AddUpdateUserParam;
import com.jiehuihui.admin.req.DeleteUserParam;
import com.jiehuihui.admin.req.GetUserPageParam;
import com.jiehuihui.common.utils.RResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * (User)用户信息表控制层
 *
 * @author zhuang
 * @since 2020-04-19 09:54:34
 */
@Api(value = "用户信息模块", description = "用户信息的接口信息(后台)")
//@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@CrossOrigin
@RestController
@RequestMapping("/admin/user")
public class UserController {

    
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    //获取用户信息
    @ApiOperation(value = "获取用户信息", notes = "获取所有用户信息,没分页")
    @GetMapping("/getUserList")
    public RResult getUserList(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        RResult<User> result = new RResult<>();
        return userService.getUserList (result);
    }

    //获取一条用户信息
    @ApiOperation(value = "获取一条用户信息", notes = "获取一条用户信息")
    @PostMapping("/getUserByssid")
    public RResult getUserByssid(@ApiParam(name="首页获取一条分类参数",value="传入json格式",required=true) @RequestBody @Validated DeleteUserParam param){
        RResult<User> result = new RResult<>();
        return userService.getUserByssid (result,param);
    }

    //获取用户信息，分页
    @ApiOperation(value = "获取用户信息", notes = "分页获取所有用户信息")
    @PostMapping("/getUserPage")
    public RResult getUserPage(@ApiParam(name="分页获取用户信息参数",value="传入json格式",required=true) @RequestBody GetUserPageParam param){
        RResult<User> result = new RResult<>();
        return userService.getUserPage (result, param);
    }

    //新增用户信息
    @ApiOperation(value = "新增用户信息", notes = "新增用户信息")
    @PostMapping("/addUser")
    public RResult addUser(@ApiParam(name="新增用户信息参数",value="传入json格式",required=true) @RequestBody @Validated(Create.class) AddUpdateUserParam param){
        RResult<User> result = new RResult<>();
        return userService.addUser (result, param);
    }

    //修改用户信息
    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    @PostMapping("/updateUser")
    public RResult updateUser(@ApiParam(name="修改用户信息参数",value="传入json格式",required=true) @RequestBody @Validated(Update.class) AddUpdateUserParam param){
        RResult<User> result = new RResult<>();
        return userService.updateUser (result,param);
    }

    //删除一条用户信息
    @ApiOperation(value = "删除用户信息", notes = "删除一条用户信息")
    @PostMapping("/deleteUser")
    public RResult deleteUser(@ApiParam(name="用户信息删除参数",value="传入json格式",required=true) @RequestBody @Validated DeleteUserParam param){
        RResult<User> result = new RResult<>();
        return userService.deleteUser (result, param);
    }

}