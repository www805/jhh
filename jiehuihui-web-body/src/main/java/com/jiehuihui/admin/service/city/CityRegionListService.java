package com.jiehuihui.admin.service.city;

import com.jiehuihui.admin.req.city.UpdateAddCityParam;
import com.jiehuihui.admin.req.city.GetCityListParam;
import com.jiehuihui.common.utils.RResult;

public interface CityRegionListService {


    //获取所有城市区域，后台分页
    RResult getCityList(RResult result, GetCityListParam param);

    //添加城市区域
    RResult addCityRegion(RResult result, UpdateAddCityParam param);

    //修改城市区域
    RResult updateCityRegion(RResult result, UpdateAddCityParam param);

    //删除城市区域
    RResult deleteCity(RResult result, UpdateAddCityParam param);

    //获取父区域城市
    RResult getCityParentList(RResult result);

    //获取全部城市输出接口
    RResult getCityAllList(RResult result);
}
