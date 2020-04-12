package com.jiehuihui.admin.controller.home;

import com.jiehuihui.admin.req.home.AddUpdateHomeSlideshowParam;
import com.jiehuihui.admin.req.home.DeleteHomeSlideshowParam;
import com.jiehuihui.admin.req.home.GetHomeSlideshowPageParam;
import com.jiehuihui.admin.service.home.HomeSlideshowService;
import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import com.jiehuihui.common.entity.home.HomeSlideshow;
import com.jiehuihui.common.utils.RResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(value = "首页轮播图模块", description = "首页轮播图的接口信息(后台)")
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/admin/homeSlideshow")
public class HomeSlideshowController {

    @Resource
    private HomeSlideshowService homeSlideshowService;

    //获取首页轮播图
//    @RequiresAuthentication
    @ApiOperation(value = "获取首页轮播图", notes = "获取所有首页轮播图,没分页")
    @GetMapping("/getHomeSlideshow")
    public RResult getHomeSlideshow(){
        RResult<HomeSlideshow> result = new RResult<>();
        return homeSlideshowService.getHomeSlideshow(result);
    }

    //获取一条首页轮播图
//    @RequiresAuthentication
    @ApiOperation(value = "获取一条首页轮播图", notes = "获取一条首页轮播图")
    @PostMapping("/getHomeSlideshowByssid")
    public RResult getHomeSlideshowByssid(@ApiParam(name="首页获取一条轮播图参数",value="传入json格式",required=true) @RequestBody DeleteHomeSlideshowParam param){
        RResult<HomeSlideshow> result = new RResult<>();
        return homeSlideshowService.getHomeSlideshowByssid(result, param);
    }

    //获取首页轮播图，分页
//    @RequiresAuthentication
    @ApiOperation(value = "获取首页轮播图", notes = "分页获取所有首页轮播图")
    @PostMapping("/getHomeSlideshowPage")
    public RResult getHomeSlideshowPage(@ApiParam(name="分页获取首页轮播图参数",value="传入json格式",required=true) @RequestBody GetHomeSlideshowPageParam param){
        RResult<HomeSlideshow> result = new RResult<>();
        return homeSlideshowService.getHomeSlideshowPage(result, param);
    }

    //新增首页轮播图
    @ApiOperation(value = "新增首页轮播图", notes = "新增首页轮播图")
    @PostMapping("/addHomeSlideshow")
    public RResult addHomeSlideshow(@ApiParam(name="新增首页轮播图参数",value="传入json格式",required=true) @RequestBody @Validated(Create.class) AddUpdateHomeSlideshowParam param){
        RResult<HomeSlideshow> result = new RResult<>();
        return homeSlideshowService.addHomeSlideshow(result, param);
    }

    //修改首页轮播图
    @ApiOperation(value = "修改首页轮播图", notes = "修改首页轮播图")
    @PostMapping("/updateHomeSlideshow")
    public RResult updateHomeSlideshow(@ApiParam(name="修改首页轮播图参数",value="传入json格式",required=true) @RequestBody @Validated(Update.class) AddUpdateHomeSlideshowParam param){
        RResult<HomeSlideshow> result = new RResult<>();
        return homeSlideshowService.updateHomeSlideshow(result,param);
    }

    //删除一条首页轮播图
    @ApiOperation(value = "删除首页轮播图", notes = "删除一条首页轮播图")
    @PostMapping("/deleteHomeSlideshow")
    public RResult deleteHomeSlideshow(@ApiParam(name="首页轮播图删除参数",value="传入json格式",required=true) @RequestBody DeleteHomeSlideshowParam param){
        RResult<HomeSlideshow> result = new RResult<>();
        return homeSlideshowService.deleteHomeSlideshow(result, param);
    }




}
