package com.jiehuihui.admin.controller.search;

import com.jiehuihui.common.entity.search.Soutype;
import com.jiehuihui.admin.service.search.SoutypeService;
import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import org.springframework.web.bind.annotation.*;

import com.jiehuihui.admin.req.search.AddUpdateSoutypeParam;
import com.jiehuihui.admin.req.search.DeleteSoutypeParam;
import com.jiehuihui.admin.req.search.GetSoutypePageParam;
import com.jiehuihui.common.utils.RResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

/**
 * (Soutype)表控制层
 *
 * @author zhuang
 * @since 2020-04-11 21:27:45
 */
@Api(value = "搜索类型模块", description = "搜索类型的接口信息(后台)")
//@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@CrossOrigin
@RestController
@RequestMapping("/admin/soutype")
public class SoutypeController {

    
    /**
     * 服务对象
     */
    @Resource
    private SoutypeService soutypeService;

    //获取搜索类型
    @ApiOperation(value = "获取搜索类型", notes = "获取所有搜索类型,没分页")
    @GetMapping("/getSoutype")
    public RResult getSoutype(){
        RResult<Soutype> result = new RResult<>();
        return soutypeService.getSoutype (result);
    }

    //获取一条搜索类型
    @ApiOperation(value = "获取一条搜索类型", notes = "获取一条搜索类型")
    @PostMapping("/getSoutypeByssid")
    public RResult getSoutypeByssid(@ApiParam(name="首页获取一条分类参数",value="传入json格式",required=true) @RequestBody DeleteSoutypeParam param){
        RResult<Soutype> result = new RResult<>();
        return soutypeService.getSoutypeByssid (result,param);
    }

    //获取搜索类型，分页
    @ApiOperation(value = "获取搜索类型", notes = "分页获取所有搜索类型")
    @PostMapping("/getSoutypePage")
    public RResult getSoutypePage(@ApiParam(name="分页获取搜索类型参数",value="传入json格式",required=true) @RequestBody GetSoutypePageParam param){
        RResult<Soutype> result = new RResult<>();
        return soutypeService.getSoutypePage (result, param);
    }

    //新增搜索类型
    @ApiOperation(value = "新增搜索类型", notes = "新增搜索类型")
    @PostMapping("/addSoutype")
    public RResult addSoutype(@ApiParam(name="新增搜索类型参数",value="传入json格式",required=true) @RequestBody @Validated(Create.class) AddUpdateSoutypeParam param){
        RResult<Soutype> result = new RResult<>();
        return soutypeService.addSoutype (result, param);
    }

    //修改搜索类型
    @ApiOperation(value = "修改搜索类型", notes = "修改搜索类型")
    @PostMapping("/updateSoutype")
    public RResult updateSoutype(@ApiParam(name="修改搜索类型参数",value="传入json格式",required=true) @RequestBody @Validated(Update.class) AddUpdateSoutypeParam param){
        RResult<Soutype> result = new RResult<>();
        return soutypeService.updateSoutype (result,param);
    }

    //删除一条搜索类型
    @ApiOperation(value = "删除搜索类型", notes = "删除一条搜索类型")
    @PostMapping("/deleteSoutype")
    public RResult deleteSoutype(@ApiParam(name="搜索类型删除参数",value="传入json格式",required=true) @RequestBody DeleteSoutypeParam param){
        RResult<Soutype> result = new RResult<>();
        return soutypeService.deleteSoutype (result, param);
    }

}