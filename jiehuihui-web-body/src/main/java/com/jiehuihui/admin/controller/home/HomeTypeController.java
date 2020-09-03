package com.jiehuihui.admin.controller.home;

import com.jiehuihui.admin.req.home.AddUpdateHomeTypeParam;
import com.jiehuihui.admin.req.home.DeleteHomeTypeParam;
import com.jiehuihui.admin.req.home.GetHomeTypePageParam;
import com.jiehuihui.admin.service.home.HomeTypeService;
import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import com.jiehuihui.common.entity.home.HomeType;
import com.jiehuihui.common.utils.RResult;
import com.jiehuihui.web.req.GetHomeWebParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(value = "首页分类模块", description = "首页分类的接口信息(后台)")
//@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@CrossOrigin
@RestController
@RequestMapping("/admin/homeType")
public class HomeTypeController {

    @Resource
    private HomeTypeService homeTypeService;

    //获取首页分类
//    @RequiresAuthentication
    @ApiOperation(value = "获取首页分类", notes = "获取所有首页分类,没分页")
    @PostMapping("/getHomeType")
    public RResult getHomeType(@RequestBody GetHomeWebParam param){
        RResult<HomeType> result = new RResult<>();
        return homeTypeService.getHomeType(result, param);
    }

    //获取一条首页分类
//    @RequiresAuthentication
    @ApiOperation(value = "获取一条首页分类", notes = "获取一条首页分类")
    @PostMapping("/getHomeTypeByssid")
    public RResult getHomeTypeByssid(@ApiParam(name="首页获取一条分类参数",value="传入json格式",required=true) @RequestBody @Validated DeleteHomeTypeParam param){
        RResult<HomeType> result = new RResult<>();
        return homeTypeService.getHomeTypeByssid(result, param);
    }

    //获取首页分类，分页
//    @RequiresAuthentication
    @ApiOperation(value = "获取首页分类", notes = "分页获取所有首页分类")
    @PostMapping("/getHomeTypePage")
    public RResult getHomeTypePage(@ApiParam(name="分页获取首页分类参数",value="传入json格式",required=true) @RequestBody GetHomeTypePageParam param){
        RResult<HomeType> result = new RResult<>();
        return homeTypeService.getHomeTypePage(result, param);
    }

    //新增首页分类
    @ApiOperation(value = "新增首页分类", notes = "新增首页分类")
    @PostMapping("/addHomeType")
    public RResult addHomeType(@ApiParam(name="新增首页分类参数",value="传入json格式",required=true) @RequestBody @Validated(Create.class) AddUpdateHomeTypeParam param){
        RResult<HomeType> result = new RResult<>();
        return homeTypeService.addHomeType(result, param);
    }

    //修改首页分类
    @ApiOperation(value = "修改首页分类", notes = "修改首页分类")
    @PostMapping("/updateHomeType")
    public RResult updateHomeType(@ApiParam(name="修改首页分类参数",value="传入json格式",required=true) @RequestBody @Validated(Update.class) AddUpdateHomeTypeParam param){
        RResult<HomeType> result = new RResult<>();
        return homeTypeService.updateHomeType(result,param);
    }

    //删除一条首页分类
    @ApiOperation(value = "删除首页分类", notes = "删除一条首页分类")
    @PostMapping("/deleteHomeType")
    public RResult deleteHomeType(@ApiParam(name="首页分类删除参数",value="传入json格式",required=true) @RequestBody @Validated DeleteHomeTypeParam param){
        RResult<HomeType> result = new RResult<>();
        return homeTypeService.deleteHomeType(result, param);
    }




}
