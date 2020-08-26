package com.jiehuihui.web.controller;

import com.jiehuihui.admin.req.GetFriendsPageParam;
import com.jiehuihui.admin.req.GetSpecialPageParam;
import com.jiehuihui.common.entity.home.HomeGg;
import com.jiehuihui.common.entity.city.ProvinceVO;
import com.jiehuihui.common.utils.RResult;
import com.jiehuihui.web.req.GetFriendstypeParam;
import com.jiehuihui.web.req.GetHomeWebParam;
import com.jiehuihui.web.service.HomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(value = "首页模块", description = "首页的接口信息(前台)")
@RestController
@CrossOrigin
@RequestMapping("/home")
public class HomeController {

    @Resource
    private HomeService homeService;

    //获取首页公告
    @PostMapping("/getHomegg")
    public RResult getHomegg(@RequestBody GetHomeWebParam param){
        RResult<HomeGg> result = new RResult<>();
        return homeService.getHomegg(result, param);
    }


    //获取首页类型
    @PostMapping("/getHometype")
    public RResult getHometype(@RequestBody GetHomeWebParam param){
        RResult<HomeGg> result = new RResult<>();
        return homeService.getHometype(result, param);
    }


    //获取首页轮播图
    @PostMapping("/getHomelb")
    public RResult getHomelb(@RequestBody GetHomeWebParam param){
        RResult<HomeGg> result = new RResult<>();
        return homeService.getHomelb(result, param);
    }


    //获取全部城市输出接口
    @ApiOperation(value = "获取全部城市", notes = "获取全部城市输出接口，并获取城市大写字母")
    @GetMapping("/getCityAllList")
    public RResult getCityAllList(){
        RResult<ProvinceVO> result = new RResult<>();
        return homeService.getCityAllList(result);
    }

    //获取天天特价（首页）
    @PostMapping("/getHomespecial")
    public RResult getHomespecial(@RequestBody GetSpecialPageParam param){
        RResult<HomeGg> result = new RResult<>();
        return homeService.getHomespecial(result, param);
    }

    //获取朋友圈类型（首页）
    @PostMapping("/getFriendstype")
    public RResult getFriendstype(@RequestBody GetFriendstypeParam param){
        RResult<HomeGg> result = new RResult<>();
        return homeService.getFriendstype(result, param);
    }

    //获取朋友圈信息（首页）
    @PostMapping("/getFriends")
    public RResult getFriends(@RequestBody GetFriendsPageParam param){
        RResult<HomeGg> result = new RResult<>();
        return homeService.getFriends(result, param);
    }

    //403类
    @GetMapping("/geterrorinfo")
    public RResult geterrorinfo(){
        RResult result = new RResult<>();
        result.setMessage("你的权限不足");
        return result;
    }
}
