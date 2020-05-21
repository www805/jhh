package com.jiehuihui.admin.controller;

import com.jiehuihui.common.entity.Shopinfoup;
import com.jiehuihui.admin.service.ShopinfoupService;
import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import org.springframework.web.bind.annotation.*;

import com.jiehuihui.admin.req.AddUpdateShopinfoupParam;
import com.jiehuihui.admin.req.DeleteShopinfoupParam;
import com.jiehuihui.admin.req.GetShopinfoupPageParam;
import com.jiehuihui.common.utils.RResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

/**
 * (Shopinfoup)表控制层
 *
 * @author zhuang
 * @since 2020-05-05 20:57:00
 */
@Api(value = "店铺申请模块", description = "店铺申请的接口信息(后台)")
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/admin/shopinfoup")
public class ShopinfoupController {

    
    /**
     * 服务对象
     */
    @Resource
    private ShopinfoupService shopinfoupService;

    //获取店铺申请
    @ApiOperation(value = "获取店铺申请", notes = "获取所有店铺申请,没分页")
    @GetMapping("/getShopinfoup")
    public RResult getShopinfoup(){
        RResult<Shopinfoup> result = new RResult<>();
        return shopinfoupService.getShopinfoup (result);
    }

    //获取一条店铺申请
    @ApiOperation(value = "获取一条店铺申请", notes = "获取一条店铺申请")
    @PostMapping("/getShopinfoupByssid")
    public RResult getShopinfoupByssid(@ApiParam(name="首页获取一条分类参数",value="传入json格式",required=true) @RequestBody DeleteShopinfoupParam param){
        RResult<Shopinfoup> result = new RResult<>();
        return shopinfoupService.getShopinfoupByssid (result,param);
    }

    //获取店铺申请，分页
    @ApiOperation(value = "获取店铺申请", notes = "分页获取所有店铺申请")
    @PostMapping("/getShopinfoupPage")
    public RResult getShopinfoupPage(@ApiParam(name="分页获取店铺申请参数",value="传入json格式",required=true) @RequestBody GetShopinfoupPageParam param){
        RResult<Shopinfoup> result = new RResult<>();
        return shopinfoupService.getShopinfoupPage (result, param);
    }

    //新增店铺申请
    @ApiOperation(value = "新增店铺申请", notes = "新增店铺申请")
    @PostMapping("/addShopinfoup")
    public RResult addShopinfoup(@ApiParam(name="新增店铺申请参数",value="传入json格式",required=true) @RequestBody @Validated(Create.class) AddUpdateShopinfoupParam param){
        RResult<Shopinfoup> result = new RResult<>();
        return shopinfoupService.addShopinfoup (result, param);
    }

    //修改店铺申请
    @ApiOperation(value = "修改店铺申请", notes = "修改店铺申请")
    @PostMapping("/updateShopinfoup")
    public RResult updateShopinfoup(@ApiParam(name="修改店铺申请参数",value="传入json格式",required=true) @RequestBody @Validated(Update.class) AddUpdateShopinfoupParam param){
        RResult<Shopinfoup> result = new RResult<>();
        return shopinfoupService.updateShopinfoup (result,param);
    }

    //删除一条店铺申请
    @ApiOperation(value = "删除店铺申请", notes = "删除一条店铺申请")
    @PostMapping("/deleteShopinfoup")
    public RResult deleteShopinfoup(@ApiParam(name="店铺申请删除参数",value="传入json格式",required=true) @RequestBody DeleteShopinfoupParam param){
        RResult<Shopinfoup> result = new RResult<>();
        return shopinfoupService.deleteShopinfoup (result, param);
    }

}