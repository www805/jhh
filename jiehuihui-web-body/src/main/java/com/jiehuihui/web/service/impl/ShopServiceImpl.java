package com.jiehuihui.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jiehuihui.admin.mapper.UserMapper;
import com.jiehuihui.admin.mapper.shop.ShopinfoMapper;
import com.jiehuihui.admin.req.AddUpdateBlinddateinfoParam;
import com.jiehuihui.admin.req.AddUpdateFriendsParam;
import com.jiehuihui.admin.req.DeleteSpecialParam;
import com.jiehuihui.admin.req.GetFriendsPageParam;
import com.jiehuihui.admin.req.shop.AddUpdateShopinfoParam;
import com.jiehuihui.admin.req.shop.DeleteShopinfoParam;
import com.jiehuihui.admin.service.BlinddateService;
import com.jiehuihui.admin.service.BlinddateinfoService;
import com.jiehuihui.admin.service.FriendsService;
import com.jiehuihui.admin.service.home.HomespecialService;
import com.jiehuihui.admin.service.shop.ShopinfoService;
import com.jiehuihui.common.entity.Concern;
import com.jiehuihui.common.entity.User;
import com.jiehuihui.common.entity.shop.Shopinfo;
import com.jiehuihui.common.utils.RResult;
import com.jiehuihui.config.RedisUtil;
import com.jiehuihui.web.mapper.ConcernMapper;
import com.jiehuihui.web.req.GetShopFriendInfoParam;
import com.jiehuihui.web.req.SetGuanzhuParam;
import com.jiehuihui.web.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private HomespecialService homespecialService;//从admin那边获取数据

    @Autowired
    private ShopinfoService shopinfoService;

    @Autowired
    private FriendsService friendsService;

    @Autowired
    private BlinddateinfoService blinddateinfoService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ConcernMapper concernMapper;

    @Autowired
    private ShopinfoMapper shopinfoMapper;

    @Autowired
    private UserMapper userMapper;


    @Override
    public RResult getSpecialByid(RResult result, DeleteSpecialParam param) {
        return homespecialService.getSpecialByssid(result, param);
    }

    @Override
    public RResult getShopByid(RResult result, DeleteShopinfoParam param) {
        return shopinfoService.getShopinfoByssid(result, param);
    }

    @Override
    public RResult getShopFriendInfo(RResult result, GetShopFriendInfoParam param) {
        GetFriendsPageParam getFriendsPageParam = new GetFriendsPageParam();
        getFriendsPageParam.setUserssid(param.getUserssid());
        getFriendsPageParam.setTypeid(param.getTypeid());
        getFriendsPageParam.setPageSize(param.getPageSize());
        getFriendsPageParam.setCurrPage(param.getCurrPage());
        return friendsService.getFriendsPage(result, getFriendsPageParam);
    }

    @Transactional
    @Override
    public RResult setGuanzhu(RResult result, SetGuanzhuParam param) {

        UpdateWrapper<Shopinfo> uws = new UpdateWrapper<>();
        uws.eq("ssid", param.getSsid());
        List<Shopinfo> shopinfos = shopinfoMapper.selectList(uws);
        if (shopinfos.size() == 0) {
            result.setMessage("该店铺不存在，不能关注");
            return result;
        }
        UpdateWrapper<User> uwu = new UpdateWrapper<>();
        uwu.eq("ssid", param.getUserssid());
        List<User> users = userMapper.selectList(uwu);
        if (users.size() == 0) {
            result.setMessage("该用户不存在，不能关注");
            return result;
        }

        //修改关注表，如果修改成功，修改店铺表的关注数
        //如果失败：新增一条到关注表，自增店铺表的关注数
        UpdateWrapper<Concern> uw = new UpdateWrapper<>();
        uw.eq("shopssid",param.getSsid());
        uw.eq("userssid",param.getUserssid());
        Concern concern = new Concern();
        concern.setState(param.getState());

        int update = concernMapper.update(concern, uw);
        if(update == 0){
            concern.setShopssid(param.getSsid());
            concern.setUserssid(param.getUserssid());
            update = concernMapper.insert(concern);
        }

        //修改店铺关注数
        if(update > 0){
            if(param.getState() == 1){
                shopinfoMapper.upGZcount(param.getSsid(), 1);
            }else{
                shopinfoMapper.upGZcount(param.getSsid(), 0);
            }
            redisUtil.del(param.getSsid());
        }

        result.changeToTrue();
        return result;
    }

    @Override
    public RResult addShopinfo(RResult result, AddUpdateShopinfoParam param) {
        return shopinfoService.addShopinfo(result, param);
    }

    @Override
    public RResult addFriends(RResult result, AddUpdateFriendsParam param) {
        return friendsService.addFriends(result, param);
    }

    @Override
    public RResult addBlinddateinfo(RResult result, AddUpdateBlinddateinfoParam param) {
        return blinddateinfoService.addBlinddateinfo(result, param);
    }


}
