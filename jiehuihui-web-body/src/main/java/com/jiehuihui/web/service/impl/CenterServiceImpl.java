package com.jiehuihui.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiehuihui.admin.mapper.SignMapper;
import com.jiehuihui.admin.mapper.UserMapper;
import com.jiehuihui.admin.mapper.city.CityzhongMapper;
import com.jiehuihui.admin.req.AddUpdateShopinfoupParam;
import com.jiehuihui.admin.req.city.GetCityListParam;
import com.jiehuihui.admin.req.shop.GetShopcolorPageParam;
import com.jiehuihui.admin.service.ShopinfoupService;
import com.jiehuihui.admin.service.city.CityRegionListService;
import com.jiehuihui.admin.service.shop.ShopcolorService;
import com.jiehuihui.admin.service.shop.ShoplabelService;
import com.jiehuihui.admin.service.shop.ShoptypeService;
import com.jiehuihui.common.entity.Sign;
import com.jiehuihui.common.entity.User;
import com.jiehuihui.common.entity.city.Cityzhong;
import com.jiehuihui.common.utils.RResult;
import com.jiehuihui.web.req.GetSignParam;
import com.jiehuihui.web.req.GetUserInfoParam;
import com.jiehuihui.web.req.UpdateUserInfoParam;
import com.jiehuihui.web.req.UpdateUserPasswordParam;
import com.jiehuihui.web.service.CenterService;
import com.jiehuihui.web.vo.GetSignVO;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CenterServiceImpl implements CenterService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SignMapper signMapper;

    @Autowired
    private CityzhongMapper cityzhongMapper;

    @Autowired
    private CityRegionListService cityRegionListService;

    @Autowired
    private ShopinfoupService shopinfoupService;

    @Autowired
    private ShoptypeService shoptypeService;

    @Autowired
    private ShoplabelService shoplabelService;

    @Autowired
    private ShopcolorService shopcolorService;

    @Override
    public RResult getUserInfo(RResult result, GetUserInfoParam param) {

        UpdateWrapper<User> ew = new UpdateWrapper<>();
        ew.eq("u.ssid", param.getSsid());
        List<User> userList = userMapper.getUserList(ew);
        if (userList.size() > 0) {
            User user = userList.get(0);
            result.changeToTrue(user);
        }else {
            result.setMessage("当前尚未登录，请登录");
        }
        return result;
    }

    @Override
    public RResult getCityList(RResult result) {
        GetCityListParam getCityListParam = new GetCityListParam();
        return cityRegionListService.getCityList(result, getCityListParam);
    }

    @Override
    public RResult updatePassword(RResult result, UpdateUserPasswordParam param) {

        if (!param.getNewpassword().equals(param.getNewpassword2())) {
            result.setMessage("确认密码必须一致");
            return result;
        }
        if (param.getNewpassword().equals(param.getOldpassword())) {
            result.setMessage("新旧密码不能一样");
            return result;
        }

        String oldpwd = new Md5Hash(param.getOldpassword(), param.getUserlogin(), 10).toString();

        UpdateWrapper<User> ew = new UpdateWrapper<>();
        ew.eq("userlogin", param.getUserlogin());
        ew.eq("password", oldpwd);
        ew.eq("ssid", param.getUserssid());
        User user = new User();
        //密码加密
        String password = param.getNewpassword();
        password = new Md5Hash(password, param.getUserlogin(), 10).toString();
        user.setPassword(password);

        int update = userMapper.update(user, ew);
        if(update == 1){
            result.changeToTrue();
            result.setMessage("密码修改成功！");
        }else{
            result.setMessage("修改失败！请检查旧密码是否错误");
        }

        return result;
    }

    @Override
    public RResult updateUserInfo(RResult result, UpdateUserInfoParam param) {

        UpdateWrapper<User> ue = new UpdateWrapper<>();
        ue.eq("username", param.getUsername());
        if(userMapper.selectCount(ue) > 0){
            result.setMessage("昵称已存在，不能重复");
            return result;
        }

        UpdateWrapper<User> ew = new UpdateWrapper<>();
        ew.eq("ssid", param.getUserssid());

        User user = new User();
        user.setUsername(param.getUsername());
        user.setTximg(param.getTximg());
        user.setSex(param.getSex());

        List<String> cityList = param.getCityList();
        if(cityList.size() == 3){
            UpdateWrapper<Cityzhong> zew = new UpdateWrapper<>();
            zew.eq("provinceid", cityList.get(0));
            zew.eq("cityid", cityList.get(1));
            zew.eq("areaid", cityList.get(2));
            List<Cityzhong> cityzhongs = cityzhongMapper.selectList(zew);
            if(cityzhongs.size() > 0){
                Cityzhong cityzhong = cityzhongs.get(0);
//                ew.eq("cityzhongid", cityzhong.getId());
                user.setCityzhongid(cityzhong.getId() + "");
            }
        }

        int update = userMapper.update(user, ew);
        if(update == 1){
            result.changeToTrue();
            result.setMessage("个人信息修改成功！");
        }else{
            result.setMessage("更新个人信息失败");
        }

        return result;
    }

    @Override
    public RResult getSign(RResult result, GetSignParam param) {

        GetSignVO getSignVO = new GetSignVO();

        UpdateWrapper<Sign> ew = new UpdateWrapper<>();
        ew.eq("userid", param.getUserid());
        ew.orderByDesc("createtime");

        Integer count = signMapper.selectCount(ew);
        param.setRecordCount(count);

        Page<Sign> page = new Page<>(param.getCurrPage(), param.getPageSize());
        Page<Sign> signPage = signMapper.selectPage(page, ew);
        //获取积分
        Long jifen = userMapper.getjifen(param.getUserid());
        getSignVO.setJifen(jifen);
        getSignVO.setPagelist(signPage.getRecords());
        getSignVO.setPageparam(param);

        result.changeToTrue(getSignVO);
        return result;
    }

    @Transactional
    @Override
    public RResult putSign(RResult result, GetSignParam param) {

        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
        String week = simple.format((new Date()).getTime());
        UpdateWrapper<Sign> ew = new UpdateWrapper<>();
        ew.eq("userid", param.getUserid());
        ew.like("createtime", week);
        int count = signMapper.getSignNowCount(ew);
        if (count > 0) {
            result.setMessage("今天已经签到过，不能再签了");
            return result;
        }

        int jifen = 1;
        Sign sign = new Sign();
        sign.setNum(jifen);
        sign.setUserid(param.getUserid());
        sign.setSdescribe("签到+" + jifen + "积分");
        sign.setState(1);
        int insert = signMapper.insert(sign);
        if (insert > 0) {
            userMapper.addjifen(param.getUserid(), jifen);
            result.changeToTrue(insert);
            result.setMessage("今天已签到，获得积分" + jifen);
        }else{
            result.setMessage("签到失败！");
        }

        return result;
    }

    @Override
    public RResult addShopinfoup(RResult result, AddUpdateShopinfoupParam param) {
        return shopinfoupService.addShopinfoup(result, param);
    }

    @Override
    public RResult getShopType(RResult result) {
        return shoptypeService.getShoptype(result);
    }

    @Override
    public RResult getShopcolor(RResult result) {
        GetShopcolorPageParam getShopcolorPageParam = new GetShopcolorPageParam();
        getShopcolorPageParam.setColortype(0);
        return shopcolorService.getShopcolor(result, getShopcolorPageParam);
    }

    @Override
    public RResult getShoplabel(RResult result) {
        return shoplabelService.getShoplabel(result);
    }
}
