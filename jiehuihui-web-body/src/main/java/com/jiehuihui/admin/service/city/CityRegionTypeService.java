package com.jiehuihui.admin.service.city;

import com.jiehuihui.admin.req.city.AddUpdateCityRegionTypeParam;
import com.jiehuihui.admin.req.city.DeleteCityRegionTypeParam;
import com.jiehuihui.admin.req.city.GetCityRegionTypePageParam;
import com.jiehuihui.common.utils.RResult;

public interface CityRegionTypeService {

    //分页获取城市类型
    RResult getCityRegionTypePage(RResult result, GetCityRegionTypePageParam param);

    //根据ssid获取城市分类
    RResult getCityRegionTypeByssid(RResult result, DeleteCityRegionTypeParam param);

    //添加城市分类
    RResult addCityRegionType(RResult result, AddUpdateCityRegionTypeParam param);

    //修改城市分类
    RResult updateCityRegionType(RResult result, AddUpdateCityRegionTypeParam param);

    //删除城市分类
    RResult deleteCityRegionType(RResult result, DeleteCityRegionTypeParam param);

    //获取所有城市类型,没分页
    RResult getCityRegionType(RResult result);
}
