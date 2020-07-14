package com.jiehuihui.admin.controller;

import com.jiehuihui.admin.req.shop.AddUpdateShopinfoParam;
import com.jiehuihui.admin.req.shop.DeleteShopinfoParam;
import com.jiehuihui.admin.req.shop.GetShopinfoPageParam;
import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import com.jiehuihui.common.entity.shop.Shopinfo;
import com.jiehuihui.admin.service.shop.ShopinfoService;
import com.jiehuihui.common.utils.RResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * (JhhShopinfo)店铺表控制层
 *
 * @author zhuang
 * @since 2020-07-12 16:40:14
 */
@Api(value = "店铺模块", description = "店铺的接口信息(后台)")
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/admin/shopinfo")
public class ShopinfoController {
    /**
     * 服务对象
     */
    @Autowired
    private ShopinfoService shopinfoService;

    /**
     * 通过主键查询单条数据
     * @param param 主键
     * @return 单条数据
     */
    @ApiOperation(value = "获取一条店铺信息", notes = "获取一条店铺信息")
    @PostMapping("/getShopinfoByssid")
    public RResult getShopinfoByssid(@ApiParam(name="分页获取店铺信息参数",value="传入json格式",required=true) @RequestBody DeleteShopinfoParam param){
        RResult<Shopinfo> result = new RResult<>();
        return shopinfoService.getShopinfoByssid(result, param);
    }

    //获取店铺信息，分页
    @ApiOperation(value = "获取店铺信息", notes = "分页获取所有店铺信息")
    @PostMapping("/getShopinfoPage")
    public RResult getShopinfoPage(@ApiParam(name="分页获取店铺信息参数",value="传入json格式",required=true) @RequestBody GetShopinfoPageParam param){
        RResult<Shopinfo> result = new RResult<>();
        return shopinfoService.getShopinfoPage (result, param);
    }

    //新增店铺
    @ApiOperation(value = "新增店铺", notes = "新增店铺")
    @PostMapping("/addShopinfo")
    public RResult addShopinfo(@ApiParam(name="新增店铺参数",value="传入json格式",required=true) @RequestBody @Validated(Create.class) AddUpdateShopinfoParam param){
        RResult<Shopinfo> result = new RResult<>();
        return shopinfoService.addShopinfo (result, param);
    }

    //修改店铺
    @ApiOperation(value = "修改店铺", notes = "修改店铺")
    @PostMapping("/updateShopinfo")
    public RResult updateShopinfo(@ApiParam(name="修改店铺参数",value="传入json格式",required=true) @RequestBody @Validated(Update.class) AddUpdateShopinfoParam param){
        RResult<Shopinfo> result = new RResult<>();
        return shopinfoService.updateShopinfo (result,param);
    }

    //删除一条店铺
    @ApiOperation(value = "删除店铺", notes = "删除一条店铺")
    @PostMapping("/deleteShopinfo")
    public RResult deleteShopinfo(@ApiParam(name="店铺删除参数",value="传入json格式",required=true) @RequestBody DeleteShopinfoParam param){
        RResult<Shopinfo> result = new RResult<>();
        return shopinfoService.deleteShopinfo (result, param);
    }

}