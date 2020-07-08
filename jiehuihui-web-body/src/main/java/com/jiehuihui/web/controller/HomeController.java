package com.jiehuihui.web.controller;

import com.jiehuihui.common.entity.home.HomeGg;
import com.jiehuihui.common.entity.city.ProvinceVO;
import com.jiehuihui.common.utils.RResult;
import com.jiehuihui.web.service.HomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(value = "首页公告模块", description = "首页公告的接口信息(前台)")
@RestController
@RequestMapping("/home")
public class HomeController {

    @Resource
    private HomeService homeService;

    //获取首页公告
    @GetMapping("/getHomegg")
    public RResult getHomegg(){
        RResult<HomeGg> result = new RResult<>();
        return homeService.getHomegg(result);
    }


    //获取首页类型
    @GetMapping("/getHometype")
    public RResult getHometype(){
        RResult<HomeGg> result = new RResult<>();
        return homeService.getHometype(result);
    }


    //获取首页轮播图
    @GetMapping("/getHomelb")
    public RResult getHomelb(){
        RResult<HomeGg> result = new RResult<>();
        return homeService.getHomelb(result);
    }


    //获取全部城市输出接口
    @ApiOperation(value = "获取全部城市", notes = "获取全部城市输出接口，并获取城市大写字母")
    @GetMapping("/getCityAllList")
    public RResult getCityAllList(){
        RResult<ProvinceVO> result = new RResult<>();
        return homeService.getCityAllList(result);
    }

    //403类
    @GetMapping("/geterrorinfo")
    public RResult geterrorinfo(){
        RResult result = new RResult<>();
        result.setMessage("你的权限不足");
        return result;
    }
}
