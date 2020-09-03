package com.jiehuihui.admin.controller.shop;

import com.jiehuihui.common.entity.shop.Shoptype;
import com.jiehuihui.admin.service.shop.ShoptypeService;
import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import org.springframework.web.bind.annotation.*;

import com.jiehuihui.admin.req.shop.AddUpdateShoptypeParam;
import com.jiehuihui.admin.req.shop.DeleteShoptypeParam;
import com.jiehuihui.admin.req.shop.GetShoptypePageParam;
import com.jiehuihui.common.utils.RResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

/**
 * (Shoptype)表控制层
 *
 * @author zhuang
 * @since 2020-04-11 21:16:54
 */
@Api(value = "首页店铺类型模块", description = "店铺类型的接口信息(后台)")
//@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@CrossOrigin
@RestController
@RequestMapping("/admin/shoptype")
public class ShoptypeController {

    
    /**
     * 服务对象
     */
    @Resource
    private ShoptypeService shoptypeService;

    //获取店铺类型
    @ApiOperation(value = "获取店铺类型", notes = "获取所有店铺类型,没分页")
    @GetMapping("/getShoptype")
    public RResult getShoptype(){
        RResult<Shoptype> result = new RResult<>();
        return shoptypeService.getShoptype (result);
    }

    //获取一条店铺类型
    @ApiOperation(value = "获取一条店铺类型", notes = "获取一条店铺类型")
    @PostMapping("/getShoptypeByssid")
    public RResult getShoptypeByssid(@ApiParam(name="首页获取一条分类参数",value="传入json格式",required=true) @RequestBody @Validated DeleteShoptypeParam param){
        RResult<Shoptype> result = new RResult<>();
        return shoptypeService.getShoptypeByssid (result,param);
    }

    //获取店铺类型，分页
    @ApiOperation(value = "获取店铺类型", notes = "分页获取所有店铺类型")
    @PostMapping("/getShoptypePage")
    public RResult getShoptypePage(@ApiParam(name="分页获取店铺类型参数",value="传入json格式",required=true) @RequestBody GetShoptypePageParam param){
        RResult<Shoptype> result = new RResult<>();
        return shoptypeService.getShoptypePage (result, param);
    }

    //新增店铺类型
    @ApiOperation(value = "新增店铺类型", notes = "新增店铺类型")
    @PostMapping("/addShoptype")
    public RResult addShoptype(@ApiParam(name="新增店铺类型参数",value="传入json格式",required=true) @RequestBody @Validated(Create.class) AddUpdateShoptypeParam param){
        RResult<Shoptype> result = new RResult<>();
        return shoptypeService.addShoptype (result, param);
    }

    //修改店铺类型
    @ApiOperation(value = "修改店铺类型", notes = "修改店铺类型")
    @PostMapping("/updateShoptype")
    public RResult updateShoptype(@ApiParam(name="修改店铺类型参数",value="传入json格式",required=true) @RequestBody @Validated(Update.class) AddUpdateShoptypeParam param){
        RResult<Shoptype> result = new RResult<>();
        return shoptypeService.updateShoptype (result,param);
    }

    //删除一条店铺类型
    @ApiOperation(value = "删除店铺类型", notes = "删除一条店铺类型")
    @PostMapping("/deleteShoptype")
    public RResult deleteShoptype(@ApiParam(name="店铺类型删除参数",value="传入json格式",required=true) @RequestBody @Validated DeleteShoptypeParam param){
        RResult<Shoptype> result = new RResult<>();
        return shoptypeService.deleteShoptype (result, param);
    }

}