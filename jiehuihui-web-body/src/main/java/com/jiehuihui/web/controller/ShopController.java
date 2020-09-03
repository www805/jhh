package com.jiehuihui.web.controller;

import com.jiehuihui.admin.req.AddUpdateBlinddateinfoParam;
import com.jiehuihui.admin.req.AddUpdateFriendsParam;
import com.jiehuihui.admin.req.DeleteSpecialParam;
import com.jiehuihui.admin.req.shop.AddUpdateShopinfoParam;
import com.jiehuihui.admin.req.shop.DeleteShopinfoParam;
import com.jiehuihui.common.utils.RResult;
import com.jiehuihui.web.req.GetShopFriendInfoParam;
import com.jiehuihui.web.req.SetGuanzhuParam;
import com.jiehuihui.web.service.ShopService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Api(value = "店铺模块", description = "店铺的接口信息(前台)")
@RestController
@CrossOrigin
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    //获取单个特价
    @PostMapping("/getSpecialByid")
    public RResult getSpecialByid(@RequestBody @Validated DeleteSpecialParam param){
        RResult result = new RResult<>();
        return shopService.getSpecialByid(result, param);
    }


    //获取单个店铺
    @PostMapping("/getShopByid")
    public RResult getShopByid(@RequestBody @Validated DeleteShopinfoParam param){
        RResult result = new RResult<>();
        return shopService.getShopByid(result, param);
    }

    //获取日常朋友圈信息
    @PostMapping("/getShopFriendInfo")
    public RResult getShopFriendInfo(@RequestBody GetShopFriendInfoParam param){
        RResult result = new RResult<>();
        return shopService.getShopFriendInfo(result, param);
    }

    //关注店铺
    @PostMapping("/setGuanzhu")
    public RResult setGuanzhu(@RequestBody @Validated SetGuanzhuParam param){
        RResult result = new RResult<>();
        return shopService.setGuanzhu(result, param);
    }


    /**
     * 发布店铺
     * @param param
     * @return
     */
    @PostMapping("/addShopinfo")
    public RResult addShopinfo(@RequestBody @Validated AddUpdateShopinfoParam param){
        RResult result = new RResult<>();
        return shopService.addShopinfo(result, param);
    }


    /**
     * 发布朋友圈
     * @param param
     * @return
     */
    @PostMapping("/addFriends")
    public RResult addFriends(@RequestBody @Validated AddUpdateFriendsParam param){
        RResult result = new RResult<>();
        return shopService.addFriends(result, param);
    }


    /**
     * 发布相亲
     * @param param
     * @return
     */
    @PostMapping("/addBlinddateinfo")
    public RResult addBlinddateinfo(@RequestBody @Validated AddUpdateBlinddateinfoParam param){
        RResult result = new RResult<>();
        return shopService.addBlinddateinfo(result, param);
    }
}
