package com.jiehuihui.admin.controller;

import com.jiehuihui.common.entity.Blinddateinfo;
import com.jiehuihui.admin.service.BlinddateinfoService;
import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import org.springframework.web.bind.annotation.*;

import com.jiehuihui.admin.req.AddUpdateBlinddateinfoParam;
import com.jiehuihui.admin.req.DeleteBlinddateinfoParam;
import com.jiehuihui.admin.req.GetBlinddateinfoPageParam;
import com.jiehuihui.common.utils.RResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

/**
 * (Blinddateinfo)表控制层
 *
 * @author zhuang
 * @since 2020-05-03 22:48:58
 */
@Api(value = "相亲模块", description = "相亲的接口信息(后台)")
//@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@CrossOrigin
@RestController
@RequestMapping("/admin/blinddateinfo")
public class BlinddateinfoController {

    
    /**
     * 服务对象
     */
    @Resource
    private BlinddateinfoService blinddateinfoService;

    //获取相亲
    @ApiOperation(value = "获取相亲", notes = "获取所有相亲,没分页")
    @GetMapping("/getBlinddateinfo")
    public RResult getBlinddateinfo(){
        RResult<Blinddateinfo> result = new RResult<>();
        return blinddateinfoService.getBlinddateinfo (result);
    }

    //获取一条相亲
    @ApiOperation(value = "获取一条相亲", notes = "获取一条相亲")
    @PostMapping("/getBlinddateinfoByssid")
    public RResult getBlinddateinfoByssid(@ApiParam(name="首页获取一条分类参数",value="传入json格式",required=true) @RequestBody @Validated DeleteBlinddateinfoParam param){
        RResult<Blinddateinfo> result = new RResult<>();
        return blinddateinfoService.getBlinddateinfoByssid (result,param);
    }

    //获取相亲，分页
    @ApiOperation(value = "获取相亲", notes = "分页获取所有相亲")
    @PostMapping("/getBlinddateinfoPage")
    public RResult getBlinddateinfoPage(@ApiParam(name="分页获取相亲参数",value="传入json格式",required=true) @RequestBody GetBlinddateinfoPageParam param){
        RResult<Blinddateinfo> result = new RResult<>();
        return blinddateinfoService.getBlinddateinfoPage (result, param);
    }

    //新增相亲
    @ApiOperation(value = "新增相亲", notes = "新增相亲")
    @PostMapping("/addBlinddateinfo")
    public RResult addBlinddateinfo(@ApiParam(name="新增相亲参数",value="传入json格式",required=true) @RequestBody @Validated(Create.class) AddUpdateBlinddateinfoParam param){
        RResult<Blinddateinfo> result = new RResult<>();
        return blinddateinfoService.addBlinddateinfo (result, param);
    }

    //修改相亲
    @ApiOperation(value = "修改相亲", notes = "修改相亲")
    @PostMapping("/updateBlinddateinfo")
    public RResult updateBlinddateinfo(@ApiParam(name="修改相亲参数",value="传入json格式",required=true) @RequestBody @Validated(Update.class) AddUpdateBlinddateinfoParam param){
        RResult<Blinddateinfo> result = new RResult<>();
        return blinddateinfoService.updateBlinddateinfo (result,param);
    }

    //删除一条相亲
    @ApiOperation(value = "删除相亲", notes = "删除一条相亲")
    @PostMapping("/deleteBlinddateinfo")
    public RResult deleteBlinddateinfo(@ApiParam(name="相亲删除参数",value="传入json格式",required=true) @RequestBody @Validated DeleteBlinddateinfoParam param){
        RResult<Blinddateinfo> result = new RResult<>();
        return blinddateinfoService.deleteBlinddateinfo (result, param);
    }


    //置顶相亲
    @ApiOperation(value = "置顶一条相亲", notes = "置顶一条相亲")
    @PostMapping("/gettopBlinddate")
    public RResult gettopBlinddate(@ApiParam(name="置顶一条相亲参数",value="传入json格式",required=true) @RequestBody @Validated DeleteBlinddateinfoParam param){
        RResult<Blinddateinfo> result = new RResult<>();
        return blinddateinfoService.gettopBlinddate (result,param);
    }

}