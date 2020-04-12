package com.jiehuihui.admin.controller.shop;

import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import com.jiehuihui.common.entity.shop.Shopcolor;
import com.jiehuihui.admin.service.shop.ShopcolorService;
import org.springframework.web.bind.annotation.*;

import com.jiehuihui.admin.req.shop.AddUpdateShopcolorParam;
import com.jiehuihui.admin.req.shop.DeleteShopcolorParam;
import com.jiehuihui.admin.req.shop.GetShopcolorPageParam;
import com.jiehuihui.common.utils.RResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

/**
 * (Shopcolor)店铺标签表控制层
 *
 * @author zhuang
 * @since 2020-04-11 17:27:28
 */
@Api(value = "店铺标签颜色模块", description = "店铺标签颜色的接口信息(后台)")
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/admin/shopcolor")
public class ShopcolorController {

    
    /**
     * 服务对象
     */
    @Resource
    private ShopcolorService shopcolorService;

    //获取店铺标签颜色
    @ApiOperation(value = "获取店铺标签颜色", notes = "获取所有店铺标签颜色,没分页")
    @GetMapping("/getShopcolor")
    public RResult getShopcolor(){
        RResult<Shopcolor> result = new RResult<>();
        return shopcolorService.getShopcolor (result);
    }

    //获取一条店铺标签颜色
    @ApiOperation(value = "获取一条店铺标签颜色", notes = "获取一条店铺标签颜色")
    @PostMapping("/getShopcolorByssid")
    public RResult getShopcolorByssid(@ApiParam(name="首页获取一条分类参数",value="传入json格式",required=true) @RequestBody DeleteShopcolorParam param){
        RResult<Shopcolor> result = new RResult<>();
        return shopcolorService.getShopcolorByssid (result,param);
    }

    //获取店铺标签颜色，分页
    @ApiOperation(value = "获取店铺标签颜色", notes = "分页获取所有店铺标签颜色")
    @PostMapping("/getShopcolorPage")
    public RResult getShopcolorPage(@ApiParam(name="分页获取店铺标签颜色参数",value="传入json格式",required=true) @RequestBody GetShopcolorPageParam param){
        RResult<Shopcolor> result = new RResult<>();
        return shopcolorService.getShopcolorPage (result, param);
    }

    //新增店铺标签颜色
    @ApiOperation(value = "新增店铺标签颜色", notes = "新增店铺标签颜色")
    @PostMapping("/addShopcolor")
    public RResult addShopcolor(@ApiParam(name="新增店铺标签颜色参数",value="传入json格式",required=true) @RequestBody @Validated(Create.class) AddUpdateShopcolorParam param){
        RResult<Shopcolor> result = new RResult<>();
        return shopcolorService.addShopcolor (result, param);
    }

    //修改店铺标签颜色
    @ApiOperation(value = "修改店铺标签颜色", notes = "修改店铺标签颜色")
    @PostMapping("/updateShopcolor")
    public RResult updateShopcolor(@ApiParam(name="修改店铺标签颜色参数",value="传入json格式",required=true) @RequestBody @Validated(Update.class) AddUpdateShopcolorParam param){
        RResult<Shopcolor> result = new RResult<>();
        return shopcolorService.updateShopcolor (result,param);
    }

    //删除一条店铺标签颜色
    @ApiOperation(value = "删除店铺标签颜色", notes = "删除一条店铺标签颜色")
    @PostMapping("/deleteShopcolor")
    public RResult deleteShopcolor(@ApiParam(name="店铺标签颜色删除参数",value="传入json格式",required=true) @RequestBody DeleteShopcolorParam param){
        RResult<Shopcolor> result = new RResult<>();
        return shopcolorService.deleteShopcolor (result, param);
    }

}