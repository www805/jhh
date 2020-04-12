package com.jiehuihui.web.service;


import com.jiehuihui.common.utils.RResult;

public interface HomeService {

    //获取首页公告
    RResult getHomegg(RResult result);

    //获取首页类型
    RResult getHometype(RResult result);

    //获取首页轮播图
    RResult getHomelb(RResult result);

    //获取全部城市输出接口
    RResult getCityAllList(RResult result);
}
