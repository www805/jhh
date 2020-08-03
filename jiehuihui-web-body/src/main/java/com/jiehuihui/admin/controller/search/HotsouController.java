package com.jiehuihui.admin.controller.search;

import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import com.jiehuihui.common.entity.search.Hotsou;
import com.jiehuihui.admin.service.search.HotsouService;
import org.springframework.web.bind.annotation.*;

import com.jiehuihui.admin.req.search.AddUpdateHotsouParam;
import com.jiehuihui.admin.req.search.DeleteHotsouParam;
import com.jiehuihui.admin.req.search.GetHotsouPageParam;
import com.jiehuihui.common.utils.RResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

/**
 * (Hotsou)表控制层
 *
 * @author zhuang
 * @since 2020-04-11 19:41:22
 */
@Api(value = "热门搜素模块", description = "热门搜素的接口信息(后台)")
//@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@CrossOrigin
@RestController
@RequestMapping("/admin/hotsou")
public class HotsouController {

    
    /**
     * 服务对象
     */
    @Resource
    private HotsouService hotsouService;

    //获取热门搜素
    @ApiOperation(value = "获取热门搜素", notes = "获取所有热门搜素,没分页")
    @GetMapping("/getHotsou")
    public RResult getHotsou(){
        RResult<Hotsou> result = new RResult<>();
        return hotsouService.getHotsou (result);
    }

    //获取一条热门搜素
    @ApiOperation(value = "获取一条热门搜素", notes = "获取一条热门搜素")
    @PostMapping("/getHotsouByssid")
    public RResult getHotsouByssid(@ApiParam(name="首页获取一条分类参数",value="传入json格式",required=true) @RequestBody DeleteHotsouParam param){
        RResult<Hotsou> result = new RResult<>();
        return hotsouService.getHotsouByssid (result,param);
    }

    //获取热门搜素，分页
    @ApiOperation(value = "获取热门搜素", notes = "分页获取所有热门搜素")
    @PostMapping("/getHotsouPage")
    public RResult getHotsouPage(@ApiParam(name="分页获取热门搜素参数",value="传入json格式",required=true) @RequestBody GetHotsouPageParam param){
        RResult<Hotsou> result = new RResult<>();
        return hotsouService.getHotsouPage (result, param);
    }

    //新增热门搜素
    @ApiOperation(value = "新增热门搜素", notes = "新增热门搜素")
    @PostMapping("/addHotsou")
    public RResult addHotsou(@ApiParam(name="新增热门搜素参数",value="传入json格式",required=true) @RequestBody @Validated(Create.class) AddUpdateHotsouParam param){
        RResult<Hotsou> result = new RResult<>();
        return hotsouService.addHotsou (result, param);
    }

    //修改热门搜素
    @ApiOperation(value = "修改热门搜素", notes = "修改热门搜素")
    @PostMapping("/updateHotsou")
    public RResult updateHotsou(@ApiParam(name="修改热门搜素参数",value="传入json格式",required=true) @RequestBody @Validated(Update.class) AddUpdateHotsouParam param){
        RResult<Hotsou> result = new RResult<>();
        return hotsouService.updateHotsou (result,param);
    }

    //删除一条热门搜素
    @ApiOperation(value = "删除热门搜素", notes = "删除一条热门搜素")
    @PostMapping("/deleteHotsou")
    public RResult deleteHotsou(@ApiParam(name="热门搜素删除参数",value="传入json格式",required=true) @RequestBody DeleteHotsouParam param){
        RResult<Hotsou> result = new RResult<>();
        return hotsouService.deleteHotsou (result, param);
    }

}