package com.jiehuihui.admin.controller.shop;

import com.jiehuihui.common.entity.shop.Shoplabel;
import com.jiehuihui.admin.service.shop.ShoplabelService;
import org.springframework.web.bind.annotation.*;

import com.jiehuihui.admin.req.shop.AddUpdateShoplabelParam;
import com.jiehuihui.admin.req.shop.DeleteShoplabelParam;
import com.jiehuihui.admin.req.shop.GetShoplabelPageParam;
import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import com.jiehuihui.common.utils.RResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

/**
 * (Shoplabel)店铺标签文字表控制层
 *
 * @author zhuang
 * @since 2020-04-11 12:41:26
 */
@Api(value = "店铺标签文字模块", description = "店铺标签文字的接口信息(后台)")
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/admin/shoplabel")
public class ShoplabelController {

    
    /**
     * 服务对象
     */
    @Resource
    private ShoplabelService shoplabelService;

    //获取店铺标签文字
    @ApiOperation(value = "获取店铺标签文字", notes = "获取所有店铺标签文字,没分页")
    @GetMapping("/getShoplabel")
    public RResult getShoplabel(){
        RResult<Shoplabel> result = new RResult<>();
        return shoplabelService.getShoplabel (result);
    }

    //获取一条店铺标签文字
    @ApiOperation(value = "获取一条店铺标签文字", notes = "获取一条店铺标签文字")
    @PostMapping("/getShoplabelByssid")
    public RResult getShoplabelByssid(@ApiParam(name="首页获取一条分类参数",value="传入json格式",required=true) @RequestBody DeleteShoplabelParam param){
        RResult<Shoplabel> result = new RResult<>();
        return shoplabelService.getShoplabelByssid (result,param);
    }

    //获取店铺标签文字，分页
    @ApiOperation(value = "获取店铺标签文字", notes = "分页获取所有店铺标签文字")
    @PostMapping("/getShoplabelPage")
    public RResult getShoplabelPage(@ApiParam(name="分页获取店铺标签文字参数",value="传入json格式",required=true) @RequestBody GetShoplabelPageParam param){
        RResult<Shoplabel> result = new RResult<>();
        return shoplabelService.getShoplabelPage (result, param);
    }

    //新增店铺标签文字
    @ApiOperation(value = "新增店铺标签文字", notes = "新增店铺标签文字")
    @PostMapping("/addShoplabel")
    public RResult addShoplabel(@ApiParam(name="新增店铺标签文字参数",value="传入json格式",required=true) @RequestBody @Validated(Create.class) AddUpdateShoplabelParam param){
        RResult<Shoplabel> result = new RResult<>();
        return shoplabelService.addShoplabel (result, param);
    }

    //修改店铺标签文字
    @ApiOperation(value = "修改店铺标签文字", notes = "修改店铺标签文字")
    @PostMapping("/updateShoplabel")
    public RResult updateShoplabel(@ApiParam(name="修改店铺标签文字参数",value="传入json格式",required=true) @RequestBody @Validated(Update.class) AddUpdateShoplabelParam param){
        RResult<Shoplabel> result = new RResult<>();
        return shoplabelService.updateShoplabel (result,param);
    }

    //删除一条店铺标签文字
    @ApiOperation(value = "删除店铺标签文字", notes = "删除一条店铺标签文字")
    @PostMapping("/deleteShoplabel")
    public RResult deleteShoplabel(@ApiParam(name="店铺标签文字删除参数",value="传入json格式",required=true) @RequestBody DeleteShoplabelParam param){
        RResult<Shoplabel> result = new RResult<>();
        return shoplabelService.deleteShoplabel (result, param);
    }

}