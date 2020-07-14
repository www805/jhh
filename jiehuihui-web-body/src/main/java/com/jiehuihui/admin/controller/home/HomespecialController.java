package com.jiehuihui.admin.controller.home;

import com.jiehuihui.admin.req.*;
import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import com.jiehuihui.common.entity.Homespecial;
import com.jiehuihui.admin.service.home.HomespecialService;
import com.jiehuihui.common.utils.RResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (JhhBaseHomespecial)特价表控制层
 *
 * @author zhuang
 * @since 2020-07-09 18:29:16
 */
@Api(value = "特价模块", description = "特价的接口信息(后台)")
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/admin/homespecial")
public class HomespecialController {
    /**
     * 服务对象
     */
    @Resource
    private HomespecialService homespecialService;

    /**
     * 通过主键查询单条数据
     * @param param 主键
     * @return 单条数据
     */
    @ApiOperation(value = "获取一条特价", notes = "获取一条特价")
    @PostMapping("/getSpecialByssid")
    public RResult getSpecialByssid(@ApiParam(name="分页获取特价参数",value="传入json格式",required=true) @RequestBody DeleteSpecialParam param){
        RResult<Homespecial> result = new RResult<>();
        return homespecialService.getSpecialByssid(result, param);
    }

    //获取特价，分页
    @ApiOperation(value = "获取特价", notes = "分页获取所有特价")
    @PostMapping("/getSpecialPage")
    public RResult getSpecialPage(@ApiParam(name="分页获取特价参数",value="传入json格式",required=true) @RequestBody GetSpecialPageParam param){
        RResult<Homespecial> result = new RResult<>();
        return homespecialService.getSpecialPage (result, param);
    }

    //新增特价
    @ApiOperation(value = "新增特价", notes = "新增特价")
    @PostMapping("/addSpecial")
    public RResult addSpecial(@ApiParam(name="新增特价参数",value="传入json格式",required=true) @RequestBody @Validated(Create.class) AddUpdateSpecialParam param){
        RResult<Homespecial> result = new RResult<>();
        return homespecialService.addSpecial (result, param);
    }

    //修改特价
    @ApiOperation(value = "修改特价", notes = "修改特价")
    @PostMapping("/updateSpecial")
    public RResult updateSpecial(@ApiParam(name="修改特价参数",value="传入json格式",required=true) @RequestBody @Validated(Update.class) AddUpdateSpecialParam param){
        RResult<Homespecial> result = new RResult<>();
        return homespecialService.updateSpecial (result,param);
    }

    //删除一条特价
    @ApiOperation(value = "删除特价", notes = "删除一条特价")
    @PostMapping("/deleteSpecial")
    public RResult deleteSpecial(@ApiParam(name="特价删除参数",value="传入json格式",required=true) @RequestBody DeleteSpecialParam param){
        RResult<Homespecial> result = new RResult<>();
        return homespecialService.deleteSpecial (result, param);
    }


}