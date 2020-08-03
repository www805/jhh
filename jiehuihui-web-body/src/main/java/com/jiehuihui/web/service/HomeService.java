package com.jiehuihui.web.service;


import com.jiehuihui.common.utils.RResult;
import com.jiehuihui.web.req.GetHomeWebParam;

public interface HomeService {

    //获取首页公告
    RResult getHomegg(RResult result, GetHomeWebParam param);

    //获取首页类型
    RResult getHometype(RResult result, GetHomeWebParam param);

    //获取首页轮播图
    RResult getHomelb(RResult result, GetHomeWebParam param);

    //获取全部城市输出接口
    RResult getCityAllList(RResult result);
}
