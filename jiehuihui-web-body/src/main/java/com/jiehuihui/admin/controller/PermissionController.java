package com.jiehuihui.admin.controller;

import com.jiehuihui.common.entity.Permission;
import com.jiehuihui.admin.service.PermissionService;
import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import org.springframework.web.bind.annotation.*;

import com.jiehuihui.admin.req.AddUpdatePermissionParam;
import com.jiehuihui.admin.req.DeletePermissionParam;
import com.jiehuihui.admin.req.GetPermissionPageParam;
import com.jiehuihui.common.utils.RResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

/**
 * (Permission)权限表控制层
 *
 * @author zhuang
 * @since 2020-04-12 20:58:38
 */
@Api(value = "权限模块", description = "权限的接口信息(后台)")
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/admin/permission")
public class PermissionController {

    
    /**
     * 服务对象
     */
    @Resource
    private PermissionService permissionService;

    //获取权限
    @ApiOperation(value = "获取权限", notes = "获取所有权限,没分页")
    @GetMapping("/getPermission")
    public RResult getPermission(){
        RResult<Permission> result = new RResult<>();
        return permissionService.getPermission (result);
    }

    //获取一条权限
    @ApiOperation(value = "获取一条权限", notes = "获取一条权限")
    @PostMapping("/getPermissionByssid")
    public RResult getPermissionByssid(@ApiParam(name="首页获取一条分类参数",value="传入json格式",required=true) @RequestBody DeletePermissionParam param){
        RResult<Permission> result = new RResult<>();
        return permissionService.getPermissionByssid (result,param);
    }

    //获取权限，分页
    @ApiOperation(value = "获取权限", notes = "分页获取所有权限")
    @PostMapping("/getPermissionPage")
    public RResult getPermissionPage(@ApiParam(name="分页获取权限参数",value="传入json格式",required=true) @RequestBody GetPermissionPageParam param){
        RResult<Permission> result = new RResult<>();
        return permissionService.getPermissionPage (result, param);
    }

    //新增权限
    @ApiOperation(value = "新增权限", notes = "新增权限")
    @PostMapping("/addPermission")
    public RResult addPermission(@ApiParam(name="新增权限参数",value="传入json格式",required=true) @RequestBody @Validated(Create.class) AddUpdatePermissionParam param){
        RResult<Permission> result = new RResult<>();
        return permissionService.addPermission (result, param);
    }

    //修改权限
    @ApiOperation(value = "修改权限", notes = "修改权限")
    @PostMapping("/updatePermission")
    public RResult updatePermission(@ApiParam(name="修改权限参数",value="传入json格式",required=true) @RequestBody @Validated(Update.class) AddUpdatePermissionParam param){
        RResult<Permission> result = new RResult<>();
        return permissionService.updatePermission (result,param);
    }

    //删除一条权限
    @ApiOperation(value = "删除权限", notes = "删除一条权限")
    @PostMapping("/deletePermission")
    public RResult deletePermission(@ApiParam(name="权限删除参数",value="传入json格式",required=true) @RequestBody DeletePermissionParam param){
        RResult<Permission> result = new RResult<>();
        return permissionService.deletePermission (result, param);
    }

}