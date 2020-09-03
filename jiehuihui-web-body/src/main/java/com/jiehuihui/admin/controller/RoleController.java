package com.jiehuihui.admin.controller;

import com.jiehuihui.common.entity.Role;
import com.jiehuihui.admin.req.AddUpdateRolePermissionParam;
import com.jiehuihui.admin.service.RoleService;
import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import org.springframework.web.bind.annotation.*;

import com.jiehuihui.admin.req.AddUpdateRoleParam;
import com.jiehuihui.admin.req.DeleteRoleParam;
import com.jiehuihui.admin.req.GetRolePageParam;
import com.jiehuihui.common.utils.RResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

/**
 * (Role)表控制层
 *
 * @author zhuang
 * @since 2020-04-16 21:56:13
 */
@Api(value = "角色模块", description = "角色的接口信息(后台)")
//@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@CrossOrigin
@RestController
@RequestMapping("/admin/role")
public class RoleController {

    
    /**
     * 服务对象
     */
    @Resource
    private RoleService roleService;

    //获取角色
    @ApiOperation(value = "获取角色", notes = "获取所有角色,没分页")
    @GetMapping("/getRole")
    public RResult getRole(){
        RResult<Role> result = new RResult<>();
        return roleService.getRole (result);
    }

    //获取一条角色
    @ApiOperation(value = "获取一条角色", notes = "获取一条角色")
    @PostMapping("/getRoleByssid")
    public RResult getRoleByssid(@ApiParam(name="首页获取一条分类参数",value="传入json格式",required=true) @RequestBody @Validated DeleteRoleParam param){
        RResult<Role> result = new RResult<>();
        return roleService.getRoleByssid (result,param);
    }

    //获取角色，分页
    @ApiOperation(value = "获取角色", notes = "分页获取所有角色")
    @PostMapping("/getRolePage")
    public RResult getRolePage(@ApiParam(name="分页获取角色参数",value="传入json格式",required=true) @RequestBody GetRolePageParam param){
        RResult<Role> result = new RResult<>();
        return roleService.getRolePage (result, param);
    }

    //新增角色
    @ApiOperation(value = "新增角色", notes = "新增角色")
    @PostMapping("/addRole")
    public RResult addRole(@ApiParam(name="新增角色参数",value="传入json格式",required=true) @RequestBody @Validated(Create.class) AddUpdateRoleParam param){
        RResult<Role> result = new RResult<>();
        return roleService.addRole (result, param);
    }

    //修改角色
    @ApiOperation(value = "修改角色", notes = "修改角色")
    @PostMapping("/updateRole")
    public RResult updateRole(@ApiParam(name="修改角色参数",value="传入json格式",required=true) @RequestBody @Validated(Update.class) AddUpdateRoleParam param){
        RResult<Role> result = new RResult<>();
        return roleService.updateRole (result,param);
    }

    //删除一条角色
    @ApiOperation(value = "删除角色", notes = "删除一条角色")
    @PostMapping("/deleteRole")
    public RResult deleteRole(@ApiParam(name="角色删除参数",value="传入json格式",required=true) @RequestBody @Validated DeleteRoleParam param){
        RResult<Role> result = new RResult<>();
        return roleService.deleteRole (result, param);
    }

    //新增角色权限
    @ApiOperation(value = "新增角色权限", notes = "新增角色权限")
    @PostMapping("/addRolePermission")
    public RResult addRolePermission(@ApiParam(name="新增角色权限参数",value="传入json格式",required=true) @RequestBody @Validated AddUpdateRolePermissionParam param){
        RResult<Role> result = new RResult<>();
        return roleService.addRolePermission (result, param);
    }

}