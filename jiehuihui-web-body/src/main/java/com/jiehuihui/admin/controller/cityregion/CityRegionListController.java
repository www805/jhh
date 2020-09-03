package com.jiehuihui.admin.controller.cityregion;

import com.jiehuihui.admin.req.city.UpdateAddCityParam;
import com.jiehuihui.admin.req.city.GetCityListParam;
import com.jiehuihui.admin.service.city.CityRegionListService;
import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Delete;
import com.jiehuihui.common.base.check.Update;
import com.jiehuihui.common.entity.city.ProvinceVO;
import com.jiehuihui.common.utils.RResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(value = "城市区域模块", description = "城市区域的接口信息(后台)")
//@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@CrossOrigin
@RestController
@RequestMapping("/admin/cityRegion")
public class CityRegionListController {

    @Resource
    private CityRegionListService cityRegionListService;

    @ApiOperation(value = "获取所有城市区域", notes = "获取所有城市区域,带分页")
    @PostMapping("/getCityList")
    public RResult getCityList(@RequestBody @Validated GetCityListParam param){
        RResult<ProvinceVO> result = new RResult<>();
        return cityRegionListService.getCityList(result, param);
    }


    //获取所有城市区域（带分页）
//    @RequiresAuthentication
    @ApiOperation(value = "获取所有城市区域", notes = "获取所有城市区域,带分页")
    @PostMapping("/getCityListPage")
    public RResult getCityListPage(@RequestBody @Validated GetCityListParam param){
        RResult<ProvinceVO> result = new RResult<>();
        return cityRegionListService.getCityListPage(result, param);
    }

    //添加城市区域
    @ApiOperation(value = "添加城市区域", notes = "用作添加城市区域")
    @PostMapping("/addCity")
    public RResult addCity(@ApiParam(name="添加城市区域参数",value="传入json格式",required=true) @RequestBody @Validated(Create.class) UpdateAddCityParam param){
        RResult<ProvinceVO> result = new RResult<>();
        return cityRegionListService.addCityRegion(result, param);
    }


    //修改
    @ApiOperation(value = "修改城市区域", notes = "用作修改城市区域")
    @PostMapping("/updateCity")
    public RResult updateCity(@ApiParam(name="修改城市区域参数",value="传入json格式",required=true) @RequestBody @Validated(Update.class) UpdateAddCityParam param){
        RResult<ProvinceVO> result = new RResult<>();
        return cityRegionListService.updateCityRegion(result, param);
    }


    //删除
    @ApiOperation(value = "删除城市区域", notes = "用作删除城市区域")
    @PostMapping("/deleteCity")
    public RResult deleteCity(@ApiParam(name="删除城市区域参数",value="传入json格式",required=true) @RequestBody @Validated(Delete.class) UpdateAddCityParam param){
        RResult<ProvinceVO> result = new RResult<>();
        return cityRegionListService.deleteCity(result, param);
    }


    //获取所有父区域
    @ApiOperation(value = "获取所有父区域", notes = "获取所有城市区域的父区域")
    @GetMapping("/getCityParentList")
    public RResult getCityParentList(){
        RResult<ProvinceVO> result = new RResult<>();
        return cityRegionListService.getCityParentList(result);
    }

    //获取全部城市输出接口
    @ApiOperation(value = "获取全部城市", notes = "获取全部城市输出接口，并获取城市大写字母")
    @GetMapping("/getCityAllList")
    public RResult getCityAllList(){
        RResult<ProvinceVO> result = new RResult<>();
        return cityRegionListService.getCityAllList(result);
    }

}
