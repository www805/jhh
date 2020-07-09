package com.jiehuihui.admin.controller;

import com.jiehuihui.admin.req.*;
import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import com.jiehuihui.common.entity.Friendstype;
import com.jiehuihui.common.entity.Homespecial;
import com.jiehuihui.admin.service.HomespecialService;
import com.jiehuihui.common.utils.RResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (JhhBaseHomespecial)表控制层
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
    @GetMapping("/getSpecialByssid")
    public RResult getSpecialByssid(@ApiParam(name="分页获取朋友圈类型参数",value="传入json格式",required=true) @RequestBody DeleteSpecialParam param){
        RResult<Homespecial> result = new RResult<>();
        return homespecialService.getSpecialByssid(result, param);
    }

    //获取朋友圈类型，分页
    @ApiOperation(value = "获取朋友圈类型", notes = "分页获取所有朋友圈类型")
    @PostMapping("/getSpecialPage")
    public RResult getSpecialPage(@ApiParam(name="分页获取朋友圈类型参数",value="传入json格式",required=true) @RequestBody GetSpecialPageParam param){
        RResult<Homespecial> result = new RResult<>();
        return homespecialService.getSpecialPage (result, param);
    }

    //新增朋友圈类型
    @ApiOperation(value = "新增朋友圈类型", notes = "新增朋友圈类型")
    @PostMapping("/addSpecial")
    public RResult addSpecial(@ApiParam(name="新增朋友圈类型参数",value="传入json格式",required=true) @RequestBody @Validated(Create.class) AddUpdateSpecialParam param){
        RResult<Homespecial> result = new RResult<>();
        return homespecialService.addSpecial (result, param);
    }

    //修改朋友圈类型
    @ApiOperation(value = "修改朋友圈类型", notes = "修改朋友圈类型")
    @PostMapping("/updateSpecial")
    public RResult updateSpecial(@ApiParam(name="修改朋友圈类型参数",value="传入json格式",required=true) @RequestBody @Validated(Update.class) AddUpdateSpecialParam param){
        RResult<Homespecial> result = new RResult<>();
        return homespecialService.updateSpecial (result,param);
    }

    //删除一条朋友圈类型
    @ApiOperation(value = "删除朋友圈类型", notes = "删除一条朋友圈类型")
    @PostMapping("/deleteSpecial")
    public RResult deleteSpecial(@ApiParam(name="朋友圈类型删除参数",value="传入json格式",required=true) @RequestBody DeleteSpecialParam param){
        RResult<Homespecial> result = new RResult<>();
        return homespecialService.deleteSpecial (result, param);
    }


}