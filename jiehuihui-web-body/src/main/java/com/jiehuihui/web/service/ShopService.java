package com.jiehuihui.web.service;

import com.jiehuihui.admin.req.AddUpdateBlinddateinfoParam;
import com.jiehuihui.admin.req.AddUpdateFriendsParam;
import com.jiehuihui.admin.req.DeleteSpecialParam;
import com.jiehuihui.admin.req.shop.AddUpdateShopinfoParam;
import com.jiehuihui.admin.req.shop.DeleteShopinfoParam;
import com.jiehuihui.common.utils.RResult;
import com.jiehuihui.web.req.GetShopFriendInfoParam;
import com.jiehuihui.web.req.SetGuanzhuParam;

public interface ShopService {

    //获取单个特价
    RResult getSpecialByid(RResult result, DeleteSpecialParam param);

    //获取单个店铺
    RResult getShopByid(RResult result, DeleteShopinfoParam param);

    //获取日常朋友圈信息
    RResult getShopFriendInfo(RResult result, GetShopFriendInfoParam param);

    //关注店铺
    RResult setGuanzhu(RResult result, SetGuanzhuParam param);

    //发布店铺
    RResult addShopinfo(RResult result, AddUpdateShopinfoParam param);

    //发布朋友圈
    RResult addFriends(RResult result, AddUpdateFriendsParam param);

    //发布相亲
    RResult addBlinddateinfo(RResult result, AddUpdateBlinddateinfoParam param);
}
