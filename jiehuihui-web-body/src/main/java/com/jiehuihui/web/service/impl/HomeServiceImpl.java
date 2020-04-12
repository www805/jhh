package com.jiehuihui.web.service.impl;

import com.jiehuihui.admin.mapper.city.CityMapper;
import com.jiehuihui.admin.service.home.HomeGgService;
import com.jiehuihui.common.entity.home.HomeSlideshow;
import com.jiehuihui.common.entity.home.HomeType;
import com.jiehuihui.common.entity.city.City;
import com.jiehuihui.common.utils.RResult;
import com.jiehuihui.admin.mapper.home.HomeGgMapper;
import com.jiehuihui.admin.mapper.home.HomeSlideshowMapper;
import com.jiehuihui.admin.mapper.home.HomeTypeMapper;
import com.jiehuihui.web.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {

    @Resource
    private HomeGgMapper homeggMapper;

    @Resource
    private HomeTypeMapper hometypeMapper;

    @Resource
    private HomeSlideshowMapper homeSlideshowMapper;

    @Resource
    private CityMapper cityMapper;

    @Autowired
    private HomeGgService homeGgService;//从admin那边获取数据

    @Override
    public RResult getHomegg(RResult result) {
        RResult resultHomegg = homeGgService.getHomegg(result);
        return resultHomegg;
    }

    @Override
    public RResult getHometype(RResult result) {
        List<HomeType> homeTypes = hometypeMapper.selectList(null);
        result.changeToTrue(homeTypes);
        return result;
    }

    @Override
    public RResult getHomelb(RResult result) {
        List<HomeSlideshow> homeSlideshows = homeSlideshowMapper.selectList(null);
        result.changeToTrue(homeSlideshows);
        return result;
    }

    @Override
    public RResult getCityAllList(RResult result) {
        List<City> cityList = cityMapper.selectList(null);
        result.changeToTrue(cityList);
        return result;
    }
}
