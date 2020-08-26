package com.jiehuihui.web.service.impl;

import com.jiehuihui.admin.mapper.city.CityMapper;
import com.jiehuihui.admin.req.GetFriendsPageParam;
import com.jiehuihui.admin.req.GetSpecialPageParam;
import com.jiehuihui.admin.service.FriendsService;
import com.jiehuihui.admin.service.FriendstypeService;
import com.jiehuihui.admin.service.home.HomeGgService;
import com.jiehuihui.admin.service.home.HomeSlideshowService;
import com.jiehuihui.admin.service.home.HomeTypeService;
import com.jiehuihui.admin.service.home.HomespecialService;
import com.jiehuihui.common.entity.city.City;
import com.jiehuihui.common.utils.RResult;
import com.jiehuihui.web.req.GetFriendstypeParam;
import com.jiehuihui.web.req.GetHomeWebParam;
import com.jiehuihui.web.service.HomeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Autowired
    private FriendstypeService friendstypeService;//从admin那边获取数据

    @Autowired
    private FriendsService friendsService;//从admin那边获取数据

    @Autowired
    private HomespecialService homespecialService;//从admin那边获取数据

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

    @Override
    public RResult getHomespecial(RResult result, GetSpecialPageParam param) {
        param.setState(1);
        //如果没设置日期，就用当天时间
        if(StringUtils.isBlank(param.getWeek())){
            SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
            param.setWeek(simple.format((new Date()).getTime()));
        }

        RResult specialPageResult = homespecialService.getSpecialPage(result, param);
        return specialPageResult;
    }

    @Override
    public RResult getFriendstype(RResult result, GetFriendstypeParam param) {
        RResult friendstypeResult = friendstypeService.getFriendstype(result);
        return friendstypeResult;
    }

    @Override
    public RResult getFriends(RResult result, GetFriendsPageParam param) {
        param.setTopnum(1);
        param.setState(1);
        RResult friendsPageResult = friendsService.getFriendsPage(result, param);
        return friendsPageResult;
    }


}
