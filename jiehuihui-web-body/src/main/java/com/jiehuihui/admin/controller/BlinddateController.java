package com.jiehuihui.admin.controller;

import com.jiehuihui.common.entity.Blinddate;
import com.jiehuihui.admin.service.BlinddateService;
import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import org.springframework.web.bind.annotation.*;

import com.jiehuihui.admin.req.AddUpdateBlinddateParam;
import com.jiehuihui.admin.req.DeleteBlinddateParam;
import com.jiehuihui.admin.req.GetBlinddatePageParam;
import com.jiehuihui.common.utils.RResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

/**
 * (Blinddate)表控制层
 *
 * @author zhuang
 * @since 2020-05-04 18:12:51
 */
@Api(value = "相亲申请模块", description = "相亲申请的接口信息(后台)")
//@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@CrossOrigin
@RestController
@RequestMapping("/admin/blinddate")
public class BlinddateController {

    
    /**
     * 服务对象
     */
    @Resource
    private BlinddateService blinddateService;

    //获取相亲申请
    @ApiOperation(value = "获取相亲申请", notes = "获取所有相亲申请,没分页")
    @GetMapping("/getBlinddate")
    public RResult getBlinddate(){
        RResult<Blinddate> result = new RResult<>();
        return blinddateService.getBlinddate (result);
    }

    //获取一条相亲申请
    @ApiOperation(value = "获取一条相亲申请", notes = "获取一条相亲申请")
    @PostMapping("/getBlinddateByssid")
    public RResult getBlinddateByssid(@ApiParam(name="首页获取一条分类参数",value="传入json格式",required=true) @RequestBody DeleteBlinddateParam param){
        RResult<Blinddate> result = new RResult<>();
        return blinddateService.getBlinddateByssid (result,param);
    }

    //获取相亲申请，分页
    @ApiOperation(value = "获取相亲申请", notes = "分页获取所有相亲申请")
    @PostMapping("/getBlinddatePage")
    public RResult getBlinddatePage(@ApiParam(name="分页获取相亲申请参数",value="传入json格式",required=true) @RequestBody GetBlinddatePageParam param){
        RResult<Blinddate> result = new RResult<>();
        return blinddateService.getBlinddatePage (result, param);
    }

    //新增相亲申请
    @ApiOperation(value = "新增相亲申请", notes = "新增相亲申请")
    @PostMapping("/addBlinddate")
    public RResult addBlinddate(@ApiParam(name="新增相亲申请参数",value="传入json格式",required=true) @RequestBody @Validated(Create.class) AddUpdateBlinddateParam param){
        RResult<Blinddate> result = new RResult<>();
        return blinddateService.addBlinddate (result, param);
    }

    //修改相亲申请
    @ApiOperation(value = "修改相亲申请", notes = "修改相亲申请")
    @PostMapping("/updateBlinddate")
    public RResult updateBlinddate(@ApiParam(name="修改相亲申请参数",value="传入json格式",required=true) @RequestBody @Validated(Update.class) AddUpdateBlinddateParam param){
        RResult<Blinddate> result = new RResult<>();
        return blinddateService.updateBlinddate (result,param);
    }

    //删除一条相亲申请
    @ApiOperation(value = "删除相亲申请", notes = "删除一条相亲申请")
    @PostMapping("/deleteBlinddate")
    public RResult deleteBlinddate(@ApiParam(name="相亲申请删除参数",value="传入json格式",required=true) @RequestBody DeleteBlinddateParam param){
        RResult<Blinddate> result = new RResult<>();
        return blinddateService.deleteBlinddate (result, param);
    }

}