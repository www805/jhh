package com.jiehuihui.admin.controller.cityregion;

import com.jiehuihui.admin.req.city.AddUpdateCityRegionTypeParam;
import com.jiehuihui.admin.req.city.DeleteCityRegionTypeParam;
import com.jiehuihui.admin.req.city.GetCityRegionTypePageParam;
import com.jiehuihui.admin.service.city.CityRegionTypeService;
import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import com.jiehuihui.common.entity.city.CityRegionType;
import com.jiehuihui.common.utils.RResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(value = "城市类型模块", description = "城市类型的接口信息(后台)")
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/admin/cityRegionType")
public class CityRegionTypeController {

    @Resource
    private CityRegionTypeService cityRegionTypeService;

    @ApiOperation(value = "获取城市类型", notes = "获取所有城市类型,没分页")
    @GetMapping("/getCityRegionType")
    public RResult getCityRegionType(){
        RResult<CityRegionType> result = new RResult<>();
        return cityRegionTypeService.getCityRegionType(result);
    }

    //获取城市类型，分页
//    @RequiresAuthentication
    @ApiOperation(value = "获取城市类型", notes = "分页获取所有城市类型")
    @PostMapping("/getCityRegionTypePage")
    public RResult getCityRegionTypePage(@ApiParam(name="分页获取城市类型参数",value="传入json格式",required=true) @RequestBody GetCityRegionTypePageParam param){
        RResult<CityRegionType> result = new RResult<>();
        return cityRegionTypeService.getCityRegionTypePage(result, param);
    }

    //获取一条城市类型
//    @RequiresAuthentication
    @ApiOperation(value = "获取一条城市类型", notes = "获取一条城市类型")
    @PostMapping("/getCityRegionTypeByssid")
    public RResult getCityRegionTypeByssid(@ApiParam(name="首页获取一条公告参数",value="传入json格式",required=true) @RequestBody DeleteCityRegionTypeParam param){
        RResult<CityRegionType> result = new RResult<>();
        return cityRegionTypeService.getCityRegionTypeByssid(result, param);
    }

    //新增城市类型
    @ApiOperation(value = "新增城市类型", notes = "新增城市类型")
    @PostMapping("/addCityRegionType")
    public RResult addCityRegionType(@ApiParam(name="新增城市类型参数",value="传入json格式",required=true) @RequestBody @Validated(Create.class) AddUpdateCityRegionTypeParam param){
        RResult<CityRegionType> result = new RResult<>();
        return cityRegionTypeService.addCityRegionType(result, param);
    }

    //修改城市类型
    @ApiOperation(value = "修改城市类型", notes = "修改城市类型")
    @PostMapping("/updateCityRegionType")
    public RResult updateCityRegionType(@ApiParam(name="修改城市类型参数",value="传入json格式",required=true) @RequestBody @Validated(Update.class) AddUpdateCityRegionTypeParam param){
        RResult<CityRegionType> result = new RResult<>();
        return cityRegionTypeService.updateCityRegionType(result,param);
    }

    //删除一条城市类型
    @ApiOperation(value = "删除城市类型", notes = "删除一条城市类型")
    @PostMapping("/deleteCityRegionType")
    public RResult deleteCityRegionType(@ApiParam(name="城市类型删除参数",value="传入json格式",required=true) @RequestBody DeleteCityRegionTypeParam param){
        RResult<CityRegionType> result = new RResult<>();
        return cityRegionTypeService.deleteCityRegionType(result, param);
    }




}
