package com.jiehuihui.admin.controller;

import com.jiehuihui.common.entity.Friends;
import com.jiehuihui.admin.service.FriendsService;
import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import org.springframework.web.bind.annotation.*;

import com.jiehuihui.admin.req.AddUpdateFriendsParam;
import com.jiehuihui.admin.req.DeleteFriendsParam;
import com.jiehuihui.admin.req.GetFriendsPageParam;
import com.jiehuihui.common.utils.RResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

/**
 * (Friends)表控制层
 *
 * @author zhuang
 * @since 2020-05-02 14:39:37
 */
@Api(value = "朋友圈模块", description = "朋友圈的接口信息(后台)")
//@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@CrossOrigin
@RestController
@RequestMapping("/admin/friends")
public class FriendsController {

    
    /**
     * 服务对象
     */
    @Resource
    private FriendsService friendsService;

    //获取朋友圈
    @ApiOperation(value = "获取朋友圈", notes = "获取所有朋友圈,没分页")
    @GetMapping("/getFriends")
    public RResult getFriends(){
        RResult<Friends> result = new RResult<>();
        return friendsService.getFriends (result);
    }

    //获取一条朋友圈
    @ApiOperation(value = "获取一条朋友圈", notes = "获取一条朋友圈")
    @PostMapping("/getFriendsByssid")
    public RResult getFriendsByssid(@ApiParam(name="朋友圈获取一条参数",value="传入json格式",required=true) @RequestBody @Validated DeleteFriendsParam param){
        RResult<Friends> result = new RResult<>();
        return friendsService.getFriendsByssid (result,param);
    }

    //获取朋友圈，分页
    @ApiOperation(value = "获取朋友圈", notes = "分页获取所有朋友圈")
    @PostMapping("/getFriendsPage")
    public RResult getFriendsPage(@ApiParam(name="分页获取朋友圈参数",value="传入json格式",required=true) @RequestBody GetFriendsPageParam param){
        RResult<Friends> result = new RResult<>();
        return friendsService.getFriendsPage (result, param);
    }

    //新增朋友圈
    @ApiOperation(value = "新增朋友圈", notes = "新增朋友圈")
    @PostMapping("/addFriends")
    public RResult addFriends(@ApiParam(name="新增朋友圈参数",value="传入json格式",required=true) @RequestBody @Validated(Create.class) AddUpdateFriendsParam param){
        RResult<Friends> result = new RResult<>();
        return friendsService.addFriends (result, param);
    }

    //修改朋友圈
    @ApiOperation(value = "修改朋友圈", notes = "修改朋友圈")
    @PostMapping("/updateFriends")
    public RResult updateFriends(@ApiParam(name="修改朋友圈参数",value="传入json格式",required=true) @RequestBody @Validated(Update.class) AddUpdateFriendsParam param){
        RResult<Friends> result = new RResult<>();
        return friendsService.updateFriends (result,param);
    }

    //删除一条朋友圈
    @ApiOperation(value = "删除朋友圈", notes = "删除一条朋友圈")
    @PostMapping("/deleteFriends")
    public RResult deleteFriends(@ApiParam(name="朋友圈删除参数",value="传入json格式",required=true) @RequestBody @Validated DeleteFriendsParam param){
        RResult<Friends> result = new RResult<>();
        return friendsService.deleteFriends (result, param);
    }

    //置顶朋友圈
    @ApiOperation(value = "置顶一条朋友圈", notes = "置顶一条朋友圈")
    @PostMapping("/gettopfriend")
    public RResult gettopfriend(@ApiParam(name="置顶一条朋友圈参数",value="传入json格式",required=true) @RequestBody @Validated DeleteFriendsParam param){
        RResult<Friends> result = new RResult<>();
        return friendsService.gettopfriend (result,param);
    }

}