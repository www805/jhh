package com.jiehuihui.web.service;

import com.jiehuihui.admin.req.DeleteSpecialParam;
import com.jiehuihui.admin.req.GetFriendsPageParam;
import com.jiehuihui.admin.req.GetSpecialPageParam;
import com.jiehuihui.common.utils.RResult;
import com.jiehuihui.web.req.GetFriendstypeParam;
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

    RResult getHomespecial(RResult result, GetSpecialPageParam param);

    RResult getFriendstype(RResult result, GetFriendstypeParam param);

    RResult getFriends(RResult result, GetFriendsPageParam param);

    //获取特价类型
    RResult getShoptype(RResult result);

    //获取单个特价
    RResult getSpecialByid(RResult result, DeleteSpecialParam param);
}
