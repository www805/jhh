package com.jiehuihui.admin.controller;

import com.jiehuihui.common.entity.Friendstype;
import com.jiehuihui.admin.service.FriendstypeService;
import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import org.springframework.web.bind.annotation.*;

import com.jiehuihui.admin.req.AddUpdateFriendstypeParam;
import com.jiehuihui.admin.req.DeleteFriendstypeParam;
import com.jiehuihui.admin.req.GetFriendstypePageParam;
import com.jiehuihui.common.utils.RResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

/**
 * (Friendstype)表控制层
 *
 * @author zhuang
 * @since 2020-04-11 23:09:49
 */
@Api(value = "朋友圈类型模块", description = "朋友圈类型的接口信息(后台)")
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/admin/friendstype")
public class FriendstypeController {

    
    /**
     * 服务对象
     */
    @Resource
    private FriendstypeService friendstypeService;

    //获取朋友圈类型
    @ApiOperation(value = "获取朋友圈类型", notes = "获取所有朋友圈类型,没分页")
    @GetMapping("/getFriendstype")
    public RResult getFriendstype(){
        RResult<Friendstype> result = new RResult<>();
        return friendstypeService.getFriendstype (result);
    }

    //获取一条朋友圈类型
    @ApiOperation(value = "获取一条朋友圈类型", notes = "获取一条朋友圈类型")
    @PostMapping("/getFriendstypeByssid")
    public RResult getFriendstypeByssid(@ApiParam(name="首页获取一条分类参数",value="传入json格式",required=true) @RequestBody DeleteFriendstypeParam param){
        RResult<Friendstype> result = new RResult<>();
        return friendstypeService.getFriendstypeByssid (result,param);
    }

    //获取朋友圈类型，分页
    @ApiOperation(value = "获取朋友圈类型", notes = "分页获取所有朋友圈类型")
    @PostMapping("/getFriendstypePage")
    public RResult getFriendstypePage(@ApiParam(name="分页获取朋友圈类型参数",value="传入json格式",required=true) @RequestBody GetFriendstypePageParam param){
        RResult<Friendstype> result = new RResult<>();
        return friendstypeService.getFriendstypePage (result, param);
    }

    //新增朋友圈类型
    @ApiOperation(value = "新增朋友圈类型", notes = "新增朋友圈类型")
    @PostMapping("/addFriendstype")
    public RResult addFriendstype(@ApiParam(name="新增朋友圈类型参数",value="传入json格式",required=true) @RequestBody @Validated(Create.class) AddUpdateFriendstypeParam param){
        RResult<Friendstype> result = new RResult<>();
        return friendstypeService.addFriendstype (result, param);
    }

    //修改朋友圈类型
    @ApiOperation(value = "修改朋友圈类型", notes = "修改朋友圈类型")
    @PostMapping("/updateFriendstype")
    public RResult updateFriendstype(@ApiParam(name="修改朋友圈类型参数",value="传入json格式",required=true) @RequestBody @Validated(Update.class) AddUpdateFriendstypeParam param){
        RResult<Friendstype> result = new RResult<>();
        return friendstypeService.updateFriendstype (result,param);
    }

    //删除一条朋友圈类型
    @ApiOperation(value = "删除朋友圈类型", notes = "删除一条朋友圈类型")
    @PostMapping("/deleteFriendstype")
    public RResult deleteFriendstype(@ApiParam(name="朋友圈类型删除参数",value="传入json格式",required=true) @RequestBody DeleteFriendstypeParam param){
        RResult<Friendstype> result = new RResult<>();
        return friendstypeService.deleteFriendstype (result, param);
    }

}