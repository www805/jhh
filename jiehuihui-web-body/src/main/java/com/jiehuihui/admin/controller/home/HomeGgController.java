package com.jiehuihui.admin.controller.home;

import com.jiehuihui.admin.req.home.AddUpdateHomeGgParam;
import com.jiehuihui.admin.req.home.DeleteHomeGgParam;
import com.jiehuihui.admin.req.home.GetHomeggPageParam;
import com.jiehuihui.admin.service.home.HomeGgService;
import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import com.jiehuihui.common.entity.home.HomeGg;
import com.jiehuihui.common.utils.RResult;
import com.jiehuihui.web.req.GetHomeWebParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(value = "首页公告模块", description = "首页公告的接口信息(后台)")
//@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@CrossOrigin
@RestController
@RequestMapping("/admin/homegg")
public class HomeGgController {

    @Resource
    private HomeGgService homeGgService;

    //获取首页公告
//    @RequiresAuthentication
    @ApiOperation(value = "获取首页公告", notes = "获取所有首页公告,没分页")
    @PostMapping("/getHomegg")
    public RResult getHomegg(@RequestBody GetHomeWebParam param){
        RResult<HomeGg> result = new RResult<>();
        return homeGgService.getHomegg(result, param);
    }

    //获取一条首页公告
//    @RequiresAuthentication
    @ApiOperation(value = "获取一条首页公告", notes = "获取一条首页公告")
    @PostMapping("/getHomeggByssid")
    public RResult getHomeggByssid(@ApiParam(name="首页获取一条公告参数",value="传入json格式",required=true) @RequestBody @Validated DeleteHomeGgParam param){
        RResult<HomeGg> result = new RResult<>();
        return homeGgService.getHomeggByssid(result, param);
    }

    //获取首页公告，分页
//    @RequiresAuthentication
    @ApiOperation(value = "获取首页公告", notes = "分页获取所有首页公告")
    @PostMapping("/getHomeggPage")
    public RResult getHomeggPage(@ApiParam(name="分页获取首页公告参数",value="传入json格式",required=true) @RequestBody GetHomeggPageParam param){
        RResult<HomeGg> result = new RResult<>();
        return homeGgService.getHomeggPage(result, param);
    }

    //新增首页公告
    @ApiOperation(value = "新增首页公告", notes = "新增首页公告")
    @PostMapping("/addHomegg")
    public RResult addHomegg(@ApiParam(name="新增首页公告参数",value="传入json格式",required=true) @RequestBody @Validated(Create.class) AddUpdateHomeGgParam param){
        RResult<HomeGg> result = new RResult<>();
        return homeGgService.addHomegg(result, param);
    }

    //修改首页公告
    @ApiOperation(value = "修改首页公告", notes = "修改首页公告")
    @PostMapping("/updateHomegg")
    public RResult updateHomegg(@ApiParam(name="修改首页公告参数",value="传入json格式",required=true) @RequestBody @Validated(Update.class) AddUpdateHomeGgParam param){
        RResult<HomeGg> result = new RResult<>();
        return homeGgService.updateHomegg(result,param);
    }

    //删除一条首页公告
    @ApiOperation(value = "删除首页公告", notes = "删除一条首页公告")
    @PostMapping("/deleteHomegg")
    public RResult deleteHomegg(@ApiParam(name="首页公告删除参数",value="传入json格式",required=true) @RequestBody @Validated DeleteHomeGgParam param){
        RResult<HomeGg> result = new RResult<>();
        return homeGgService.deleteHomegg(result, param);
    }




}
