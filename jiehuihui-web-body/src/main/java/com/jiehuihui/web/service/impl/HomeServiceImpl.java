package com.jiehuihui.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jiehuihui.admin.mapper.city.CityMapper;
import com.jiehuihui.admin.service.home.HomeGgService;
import com.jiehuihui.admin.service.home.HomeSlideshowService;
import com.jiehuihui.admin.service.home.HomeTypeService;
import com.jiehuihui.common.entity.home.HomeSlideshow;
import com.jiehuihui.common.entity.home.HomeType;
import com.jiehuihui.common.entity.city.City;
import com.jiehuihui.common.utils.RResult;
import com.jiehuihui.admin.mapper.home.HomeGgMapper;
import com.jiehuihui.admin.mapper.home.HomeSlideshowMapper;
import com.jiehuihui.admin.mapper.home.HomeTypeMapper;
import com.jiehuihui.web.req.GetHomeWebParam;
import com.jiehuihui.web.service.HomeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {

    @Resource
    private CityMapper cityMapper;

    @Autowired
    private HomeGgService homeGgService;//从admin那边获取数据

    @Autowired
    private HomeSlideshowService homeSlideshowService;//从admin那边获取数据

    @Autowired
    private HomeTypeService homeTypeService;//从admin那边获取数据

    @Override
    public RResult getHomegg(RResult result, GetHomeWebParam param) {
        RResult homeggResult = homeGgService.getHomegg(result, param);
        return homeggResult;
    }

    @Override
    public RResult getHometype(RResult result, GetHomeWebParam param) {
        RResult homeTypeResult = homeTypeService.getHomeType(result, param);
        return homeTypeResult;
    }

    @Override
    public RResult getHomelb(RResult result, GetHomeWebParam param) {
        RResult homeSlideshowResult = homeSlideshowService.getHomeSlideshow(result, param);
        return homeSlideshowResult;
    }

    @Override
    public RResult getCityAllList(RResult result) {

        List<City> cityList = cityMapper.selectList(null);
        result.changeToTrue(cityList);
        return result;
    }
}
