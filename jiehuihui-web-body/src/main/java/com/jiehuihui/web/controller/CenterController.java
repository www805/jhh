package com.jiehuihui.web.controller;


import com.jiehuihui.admin.req.AddUpdateFriendsParam;
import com.jiehuihui.admin.req.AddUpdateShopinfoupParam;
import com.jiehuihui.admin.req.DeleteFriendsParam;
import com.jiehuihui.admin.req.shop.AddUpdateShopinfoParam;
import com.jiehuihui.admin.req.shop.DeleteShopinfoParam;
import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import com.jiehuihui.common.utils.RResult;
import com.jiehuihui.web.req.GetSignParam;
import com.jiehuihui.web.req.GetUserInfoParam;
import com.jiehuihui.web.req.UpdateUserInfoParam;
import com.jiehuihui.web.req.UpdateUserPasswordParam;
import com.jiehuihui.web.service.CenterService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Api(value = "个人中心模块", description = "个人中心的接口信息(前台)")
@RestController
@CrossOrigin
@RequestMapping("/center")
public class CenterController {

    @Autowired
    private CenterService centerService;

    /**
     * 获取当前登录用户信息
     * @param param
     * @return
     */
    @PostMapping("/getUserInfo")
    public RResult getUserInfo(@RequestBody @Validated GetUserInfoParam param){
        RResult result = new RResult<>();
        return centerService.getUserInfo(result, param);
    }

    /**
     * 获取所有省市区
     * @return
     */
    @PostMapping("/getCityList")
    public RResult getCityList(){
        RResult result = new RResult<>();
        return centerService.getCityList(result);
    }

    /**
     * 获取店铺类型
     * @return
     */
    @PostMapping("/getShopType")
    public RResult getShopType(){
        RResult result = new RResult<>();
        return centerService.getShopType(result);
    }

    /**
     * 标签颜色
     * @return
     */
    @PostMapping("/getShopcolor")
    public RResult getShopcolor(){
        RResult result = new RResult<>();
        return centerService.getShopcolor(result);
    }

    /**
     * 标签文字
     * @return
     */
    @PostMapping("/getShoplabel")
    public RResult getShoplabel(){
        RResult result = new RResult<>();
        return centerService.getShoplabel(result);
    }

    /**
     * 修改密码
     * @param param
     * @return
     */
    @PostMapping("/updateUserPassword")
    public RResult updateUserPassword(@RequestBody @Validated UpdateUserPasswordParam param){
        RResult result = new RResult<>();
        return centerService.updatePassword(result, param);
    }

    /**
     * 申请加入店铺
     * @param param
     * @return
     */
    @PostMapping("/addShopinfoup")
    public RResult addShopinfoup(@RequestBody @Validated(Create.class) AddUpdateShopinfoupParam param){
        RResult result = new RResult<>();
        return centerService.addShopinfoup(result, param);
    }

    /**
     * 新增、发布/修改店铺
     * @param param
     * @return
     */
    @PostMapping("/addOrUpdateShopinfo")
    public RResult addOrUpdateShopinfo(@RequestBody @Validated({Create.class, Update.class}) AddUpdateShopinfoParam param){
        RResult result = new RResult<>();
        return centerService.addOrUpdateShopinfo(result, param);
    }

    /**
     * 删除店铺
     * @param param
     * @return
     */
    @PostMapping("/delShopinfo")
    public RResult delShopinfo(@RequestBody @Validated DeleteShopinfoParam param){
        RResult result = new RResult<>();
        return centerService.delShopinfo(result, param);
    }

    /**
     * 获取一条朋友圈
     * @param param
     * @return
     */
    @PostMapping("/getFriendsByssid")
    public RResult getFriendsByssid(@RequestBody @Validated DeleteFriendsParam param){
        RResult result = new RResult<>();
        return centerService.getFriendsByssid(result, param);
    }

    /**
     * 新增、修改朋友圈
     * @param param
     * @return
     */
    @PostMapping("/addOrUpdateFriends")
    public RResult addOrUpdateFriends(@RequestBody @Validated({Create.class, Update.class}) AddUpdateFriendsParam param){
        RResult result = new RResult<>();
        return centerService.addOrUpdateFriends(result, param);
    }

    /***
     * 删除一条朋友圈
     * @param param
     * @return
     */
    @PostMapping("/delFriends")
    public RResult delFriends(@RequestBody @Validated DeleteFriendsParam param){
        RResult result = new RResult<>();
        return centerService.delFriends(result, param);
    }

    /**
     * 修改个人信息
     * @param param
     * @return
     */
    @PostMapping("/updateUserInfo")
    public RResult updateUserInfo(@RequestBody @Validated UpdateUserInfoParam param){
        RResult result = new RResult<>();
        return centerService.updateUserInfo(result, param);
    }

    /**
     * 获取个人签到记录
     * @param param
     * @return
     */
    @PostMapping("/getSign")
    public RResult getSign(@RequestBody @Validated GetSignParam param){
        RResult result = new RResult<>();
        return centerService.getSign(result, param);
    }

    /**
     * 立即签到
     * @param param
     * @return
     */
    @PostMapping("/putSign")
    public RResult putSign(@RequestBody @Validated GetSignParam param){
        RResult result = new RResult<>();
        return centerService.putSign(result, param);
    }



}
