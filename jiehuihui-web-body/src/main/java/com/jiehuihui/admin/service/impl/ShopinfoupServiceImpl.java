package com.jiehuihui.admin.service.impl;

import com.jiehuihui.common.entity.Shopinfoup;
import com.jiehuihui.admin.mapper.shop.ShopinfoupMapper;
import com.jiehuihui.admin.mapper.UserMapper;
import com.jiehuihui.admin.mapper.city.CityzhongMapper;
import com.jiehuihui.admin.service.ShopinfoupService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiehuihui.admin.req.AddUpdateShopinfoupParam;
import com.jiehuihui.admin.req.DeleteShopinfoupParam;
import com.jiehuihui.admin.req.GetShopinfoupPageParam;
import com.jiehuihui.admin.vo.GetShopinfoupVO;
import com.jiehuihui.common.entity.User;
import com.jiehuihui.common.entity.city.Cityzhong;
import com.jiehuihui.common.entity.shop.Shoplabel;
import com.jiehuihui.common.utils.LogUtil;
import com.jiehuihui.common.utils.OpenUtil;
import com.jiehuihui.common.utils.RResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Shopinfoup)表服务实现类
 *
 * @author zhuang
 * @since 2020-05-05 20:57:00
 */
@Service("shopinfoupService")
public class ShopinfoupServiceImpl implements ShopinfoupService {
    @Resource
    private ShopinfoupMapper shopinfoupMapper;

    @Autowired
    private CityzhongMapper cityzhongMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public RResult getShopinfoup(RResult result) {
        UpdateWrapper<Shopinfoup> ew = new UpdateWrapper<>();
        ew.eq("state", 1);
        List<Shopinfoup> shopinfoups = shopinfoupMapper.selectList(ew);
        result.changeToTrue(shopinfoups);
        return result;
    }

    @Override
    public RResult getShopinfoupByssid(RResult result, DeleteShopinfoupParam param) {
        UpdateWrapper<Shopinfoup> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());
        List<Shopinfoup> shopinfoups = shopinfoupMapper.selectList(ew);
        if (null != shopinfoups && shopinfoups.size() > 0) {
            Shopinfoup shopinfoup = shopinfoups.get(0);
            result.changeToTrue(shopinfoup);
        }else{
            result.setMessage("获取失败，该条数据不存在");
        }
        return result;
    }

    @Override
    public RResult getShopinfoupPage(RResult result, GetShopinfoupPageParam param) {

        GetShopinfoupVO shopinfoupVO = new GetShopinfoupVO();

        UpdateWrapper<Shopinfoup> ew = new UpdateWrapper<>();
        if(StringUtils.isNotEmpty(param.getShopname())){
            ew.like("s.shopname", param.getShopname());
        }
        if(StringUtils.isNotEmpty(param.getPhone())){
            ew.like("s.phone", param.getPhone());
        }
        if(null != param.getState() && param.getState() >= 0 && param.getState() != 2){
            ew.eq("s.state", param.getState());
        }else{
            ew.ne("s.state", 2);
        }

        List<String> cityList = param.getCityList();
        if(null != cityList && cityList.size() == 3){
            UpdateWrapper<Cityzhong> zew = new UpdateWrapper<>();
            zew.eq("provinceid", cityList.get(0));
            zew.eq("cityid", cityList.get(1));
            zew.eq("areaid", cityList.get(2));
            List<Cityzhong> cityzhongs = cityzhongMapper.selectList(zew);
            if(cityzhongs.size() > 0){
                Cityzhong cityzhong = cityzhongs.get(0);
                ew.eq("s.cityzhongid", cityzhong.getId());
            }
        }

        ew.orderByDesc("s.createtime");

        Integer count = shopinfoupMapper.selectShopinfoupCount(ew);
        param.setRecordCount(count);

        Page<Shopinfoup> page = new Page<>(param.getCurrPage(), param.getPageSize());
        page.setTotal(count);
        IPage<Shopinfoup> sqCacheList = shopinfoupMapper.getShopinfoupPage(page, ew);

        shopinfoupVO.setPagelist(sqCacheList.getRecords());
        shopinfoupVO.setPageparam(param);

        result.changeToTrue(shopinfoupVO);
        return result;
    }

    @Override
    public RResult addShopinfoup(RResult result, AddUpdateShopinfoupParam param) {
        //先校验添加的ssid是否已经存在，在判断添加的参数是否已经存在
        String ssid = OpenUtil.getUUID_32();
        if(StringUtils.isNoneBlank(param.getSsid())){
            UpdateWrapper<Shopinfoup> ew = new UpdateWrapper<>();
            ew.eq("ssid", param.getSsid());
            List<Shopinfoup> shopinfoups = shopinfoupMapper.selectList(ew);
            if (null != shopinfoups && shopinfoups.size() > 0) {
                result.setMessage("该ssid已经存在，不能添加");
                return result;
            }
            ssid = param.getSsid();
        }

        UpdateWrapper<Shopinfoup> ew = new UpdateWrapper<>();
        ew.eq("shopname", param.getShopname());
        List<Shopinfoup> TypeList = shopinfoupMapper.selectList(ew);
        if (null != TypeList && TypeList.size() > 0) {
            result.setMessage("该店铺已经存在，不能添加");
            return result;
        }

        Shopinfoup shopinfoup = new Shopinfoup();

        //查询关联用户信息
        UpdateWrapper<User> muew = new UpdateWrapper<User>();
        muew.eq("username", param.getUsername().trim());
        List<User> musers = userMapper.selectList(muew);
        if(musers.size() == 0){
            result.setMessage("没找到关联用户，申请店铺失败");
            return result;
        }
        shopinfoup.setUserid(musers.get(0).getSsid());
        shopinfoup.setUsername(param.getUsername().trim());

        List<String> cityList = param.getCityList();
        if(null != cityList && cityList.size() == 3){
            UpdateWrapper<Cityzhong> zew = new UpdateWrapper<>();
            zew.eq("provinceid", cityList.get(0));
            zew.eq("cityid", cityList.get(1));
            zew.eq("areaid", cityList.get(2));
            List<Cityzhong> cityzhongs = cityzhongMapper.selectList(zew);
            if(cityzhongs.size() > 0){
                Cityzhong cityzhong = cityzhongs.get(0);
                shopinfoup.setCityzhongid(cityzhong.getId() + "");
            }else{
                result.setMessage("城市没找到，无法处理");
                return result;
            }
        }

        if(param.getSfzimg().size() > 0){
            shopinfoup.setSfzimg(param.getSfzimg().toString().trim());
        }
        if(param.getYyzzimg().size() > 0){
            shopinfoup.setYyzzimg(param.getYyzzimg().toString().trim());
        }

        shopinfoup.setShoptypessid(param.getShoptypessid());
        shopinfoup.setShopname(param.getShopname());
        shopinfoup.setAddress(param.getAddress());
        shopinfoup.setPhone(param.getPhone());
        shopinfoup.setDpdescribe(param.getDpdescribe());
        shopinfoup.setSsid(ssid);
        shopinfoup.setState(param.getState());
        int insert = shopinfoupMapper.insert(shopinfoup);
        if (insert > 0) {
            result.changeToTrue(insert);
        }
        return result;
    }

    @Override
    public RResult updateShopinfoup(RResult result, AddUpdateShopinfoupParam param) {
        //先校验是否已经存在
        UpdateWrapper<Shopinfoup> ewcheck = new UpdateWrapper<>();
        ewcheck.eq("shopname", param.getShopname());
        ewcheck.ne("ssid", param.getSsid());
        List<Shopinfoup> shopinfoups = shopinfoupMapper.selectList(ewcheck);
        if (null != shopinfoups && shopinfoups.size() > 0) {
            result.setMessage("修改店名已经存在，不能修改");
            return result;
        }

        UpdateWrapper<Shopinfoup> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());

        Shopinfoup shopinfoup = new Shopinfoup();

        //查询关联用户信息
        UpdateWrapper<User> muew = new UpdateWrapper<User>();
        muew.eq("username", param.getUsername().trim());
        List<User> musers = userMapper.selectList(muew);
        if(musers.size() == 0){
            result.setMessage("没找到关联用户，修改店铺申请失败");
            return result;
        }
        shopinfoup.setUserid(musers.get(0).getSsid());
        shopinfoup.setUsername(param.getUsername().trim());

        List<String> cityList = param.getCityList();
        if(null != cityList && cityList.size() == 3){
            UpdateWrapper<Cityzhong> zew = new UpdateWrapper<>();
            zew.eq("provinceid", cityList.get(0));
            zew.eq("cityid", cityList.get(1));
            zew.eq("areaid", cityList.get(2));
            List<Cityzhong> cityzhongs = cityzhongMapper.selectList(zew);
            if(cityzhongs.size() > 0){
                Cityzhong cityzhong = cityzhongs.get(0);
                shopinfoup.setCityzhongid(cityzhong.getId() + "");
            }else{
                result.setMessage("城市没找到，无法处理");
                return result;
            }
        }

        if(param.getSfzimg().size() > 0){
            shopinfoup.setSfzimg(param.getSfzimg().toString().trim());
        }
        if(param.getYyzzimg().size() > 0){
            shopinfoup.setYyzzimg(param.getYyzzimg().toString().trim());
        }

        shopinfoup.setShoptypessid(param.getShoptypessid());
        shopinfoup.setShopname(param.getShopname());
        shopinfoup.setAddress(param.getAddress());
        shopinfoup.setPhone(param.getPhone());
        shopinfoup.setDpdescribe(param.getDpdescribe());
        shopinfoup.setState(param.getState());

        int update = shopinfoupMapper.update(shopinfoup, ew);
        if (update > 0) {
            result.changeToTrue(update);
            result.setMessage("修改成功！");
        }

        LogUtil.intoLog("用户：修改了数据！" + param.getShopname());

        return result;
    }

    @Override
    public RResult deleteShopinfoup(RResult result, DeleteShopinfoupParam param) {
        UpdateWrapper<Shopinfoup> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());

        Shopinfoup shopinfoup = shopinfoupMapper.selectOne(ew);
        if(null == shopinfoup){
            result.setMessage("删除的店铺不存在");
            return result;
        }

        Shopinfoup shop = new Shopinfoup();
        shop.setState(2);

        int delete = shopinfoupMapper.update(shop, ew);
        if (delete > 0) {
            result.changeToTrue(delete);
            result.setMessage("删除成功！");
        }
        LogUtil.intoLog("用户：删除了一条数据！" + shopinfoup.getUsername());
        return result;
    }
}