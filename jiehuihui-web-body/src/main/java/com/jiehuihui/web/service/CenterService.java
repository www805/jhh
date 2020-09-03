package com.jiehuihui.web.service;

import com.jiehuihui.admin.req.AddUpdateShopinfoupParam;
import com.jiehuihui.common.utils.RResult;
import com.jiehuihui.web.req.GetSignParam;
import com.jiehuihui.web.req.GetUserInfoParam;
import com.jiehuihui.web.req.UpdateUserInfoParam;
import com.jiehuihui.web.req.UpdateUserPasswordParam;

public interface CenterService {

    /**
     * 获取当前登录用户信息
     * @param result
     * @return
     */
    RResult getUserInfo(RResult result, GetUserInfoParam param);

    /**
     * 获取所有省市区
     * @param result
     * @return
     */
    RResult getCityList(RResult result);

    /**
     * 修改密码
     * @param result
     * @param param
     * @return
     */
    RResult updatePassword(RResult result, UpdateUserPasswordParam param);

    /**
     * 修改个人信息
     * @param result
     * @param param
     * @return
     */
    RResult updateUserInfo(RResult result, UpdateUserInfoParam param);

    /**
     * 获取个人签到记录
     * @param result
     * @param param
     * @return
     */
    RResult getSign(RResult result, GetSignParam param);

    /**
     * 立即签到
     * @param result
     * @param param
     * @return
     */
    RResult putSign(RResult result, GetSignParam param);

    /**
     * 申请加入店铺
     * @param result
     * @param param
     * @return
     */
    RResult addShopinfoup(RResult result, AddUpdateShopinfoupParam param);

    /**
     * 获取店铺类型
     * @param result
     * @return
     */
    RResult getShopType(RResult result);

    /**
     * 标签颜色
     * @param result
     * @return
     */
    RResult getShopcolor(RResult result);

    /**
     * 标签文字
     * @param result
     * @return
     */
    RResult getShoplabel(RResult result);
}
