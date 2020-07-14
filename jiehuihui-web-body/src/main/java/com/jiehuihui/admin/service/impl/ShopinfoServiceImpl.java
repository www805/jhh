package com.jiehuihui.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiehuihui.admin.mapper.UserMapper;
import com.jiehuihui.admin.mapper.city.CityzhongMapper;
import com.jiehuihui.admin.mapper.shop.ShopinfoMapper;
import com.jiehuihui.admin.req.shop.AddUpdateShopinfoParam;
import com.jiehuihui.admin.req.shop.DeleteShopinfoParam;
import com.jiehuihui.admin.req.shop.GetShopinfoPageParam;
import com.jiehuihui.admin.service.shop.ShopinfoService;
import com.jiehuihui.admin.vo.GetShopinfoVO;
import com.jiehuihui.admin.vo.GetShopinfoupVO;
import com.jiehuihui.common.entity.Shopinfoup;
import com.jiehuihui.common.entity.User;
import com.jiehuihui.common.entity.city.Cityzhong;
import com.jiehuihui.common.entity.shop.Shopinfo;
import com.jiehuihui.common.utils.LogUtil;
import com.jiehuihui.common.utils.OpenUtil;
import com.jiehuihui.common.utils.RResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (JhhShopinfo)店铺表服务实现类
 *
 * @author zhuang
 * @since 2020-07-12 16:40:14
 */
@Service
public class ShopinfoServiceImpl implements ShopinfoService {

    @Autowired
    private ShopinfoMapper shopinfoMapper;

    @Autowired
    private CityzhongMapper cityzhongMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 通过ID查询单条数据
     * @param param 主键
     * @return 实例对象
     */

    @Override
    public RResult getShopinfoByssid(RResult<Shopinfo> result, DeleteShopinfoParam param) {
        UpdateWrapper<Shopinfo> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());
        List<Shopinfo> shopinfos = shopinfoMapper.selectList(ew);
        if (null != shopinfos && shopinfos.size() > 0) {
            Shopinfo homespecial = shopinfos.get(0);
            result.changeToTrue(homespecial);
        }else{
            result.setMessage("没找到该店铺");
        }
        return result;
    }

    /**
     * 查询多条数据
     * @param result
     * @param param
     * @return
     */
    @Override
    public RResult getShopinfoPage(RResult result, GetShopinfoPageParam param) {

        GetShopinfoVO shopinfoVO = new GetShopinfoVO();

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

        Integer count = shopinfoMapper.selectShopinfoCount(ew);
        param.setRecordCount(count);

        Page<Shopinfoup> page = new Page<>(param.getCurrPage(), param.getPageSize());
        page.setTotal(count);
        IPage<Shopinfo> shopinfoPage = shopinfoMapper.getShopinfoPage(page, ew);

        shopinfoVO.setPagelist(shopinfoPage.getRecords());
        shopinfoVO.setPageparam(param);

        result.changeToTrue(shopinfoVO);
        return result;
    }

    /**
     * 新增数据
     * @param result
     * @param param
     * @return
     */
    @Override
    public RResult addShopinfo(RResult result, AddUpdateShopinfoParam param) {
        //先校验添加的ssid是否已经存在，在判断添加的参数是否已经存在
        String ssid = OpenUtil.getUUID_32();
        if(StringUtils.isNoneBlank(param.getSsid())){
            UpdateWrapper<Shopinfo> ew = new UpdateWrapper<>();
            ew.eq("ssid", param.getSsid());
            List<Shopinfo> shopinfos = shopinfoMapper.selectList(ew);
            if (null != shopinfos && shopinfos.size() > 0) {
                result.setMessage("该ssid已经存在，不能添加");
                return result;
            }
            ssid = param.getSsid();
        }

        UpdateWrapper<Shopinfo> ew = new UpdateWrapper<>();
        ew.eq("shopname", param.getShopname());
        List<Shopinfo> TypeList = shopinfoMapper.selectList(ew);
        if (null != TypeList && TypeList.size() > 0) {
            result.setMessage("该店铺已经存在，不能添加");
            return result;
        }

        Shopinfo shopinfo = new Shopinfo();

        //查询关联用户信息
        UpdateWrapper<User> muew = new UpdateWrapper<User>();
        muew.eq("username", param.getUsername().trim());
        List<User> musers = userMapper.selectList(muew);
        if(musers.size() == 0){
            result.setMessage("没找到关联用户，添加店铺申请失败");
            return result;
        }
        shopinfo.setUserid(musers.get(0).getSsid());
        shopinfo.setUsername(param.getUsername().trim());

        List<String> cityList = param.getCityList();
        if(null != cityList && cityList.size() == 3){
            UpdateWrapper<Cityzhong> zew = new UpdateWrapper<>();
            zew.eq("provinceid", cityList.get(0));
            zew.eq("cityid", cityList.get(1));
            zew.eq("areaid", cityList.get(2));
            List<Cityzhong> cityzhongs = cityzhongMapper.selectList(zew);
            if(cityzhongs.size() > 0){
                Cityzhong cityzhong = cityzhongs.get(0);
                shopinfo.setCityzhongid(cityzhong.getId() + "");
            }else{
                result.setMessage("城市没找到，无法处理");
                return result;
            }
        }

        if(param.getSfzimglist().size() > 0){
            shopinfo.setSfzimglist(param.getSfzimglist().toString().trim());
        }
        if(param.getYyzzimgs().size() > 0){
            shopinfo.setYyzzimgs(param.getYyzzimgs().toString().trim());
        }
        if(param.getQtimglist().size() > 0){
            shopinfo.setQtimglist(param.getQtimglist().toString().trim());
        }
        if(param.getYhtaglist().size() > 0){
            shopinfo.setYhtaglist(param.getYhtaglist().toString().trim());
        }

        shopinfo.setShoptypessid(param.getShoptypessid());
        shopinfo.setShopname(param.getShopname());
        shopinfo.setAddress(param.getAddress());
        shopinfo.setPhone(param.getPhone());
        shopinfo.setWxnum(param.getWxnum());
        shopinfo.setDpdescribe(param.getDpdescribe());
        shopinfo.setFmimglist(param.getFmimglist());
        shopinfo.setJgimglist(param.getJgimglist());
        shopinfo.setGzcout(param.getGzcout());
        shopinfo.setYytime(param.getYytime());
        shopinfo.setBrowsecout(param.getBrowsecout());
        shopinfo.setZztop(param.getZztop());
        shopinfo.setSoutop(param.getSoutop());
        shopinfo.setHometop(param.getHometop());
        shopinfo.setSortnum(param.getSortnum());
        shopinfo.setHometopendtime(param.getHometopendtime());
        shopinfo.setSsid(param.getSsid());
        shopinfo.setState(param.getState());
        int insert = shopinfoMapper.insert(shopinfo);
        if (insert > 0) {
            result.changeToTrue(insert);
        }
        return result;
    }

    /**
     * 修改数据
     * @param result
     * @param param
     * @return
     */
    @Override
    public RResult updateShopinfo(RResult result, AddUpdateShopinfoParam param) {
        //先校验是否已经存在
        UpdateWrapper<Shopinfo> ewcheck = new UpdateWrapper<>();
        ewcheck.eq("shopname", param.getShopname());
        ewcheck.ne("ssid", param.getSsid());
        List<Shopinfo> shopinfos = shopinfoMapper.selectList(ewcheck);
        if (null != shopinfos && shopinfos.size() > 0) {
            result.setMessage("修改店名已经存在，不能修改");
            return result;
        }

        UpdateWrapper<Shopinfo> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());

        Shopinfo shopinfo = new Shopinfo();

        //查询关联用户信息
        UpdateWrapper<User> muew = new UpdateWrapper<User>();
        muew.eq("username", param.getUsername().trim());
        List<User> musers = userMapper.selectList(muew);
        if(musers.size() == 0){
            result.setMessage("没找到关联用户，修改店铺申请失败");
            return result;
        }
        shopinfo.setUserid(musers.get(0).getSsid());
        shopinfo.setUsername(param.getUsername().trim());

        List<String> cityList = param.getCityList();
        if(null != cityList && cityList.size() == 3){
            UpdateWrapper<Cityzhong> zew = new UpdateWrapper<>();
            zew.eq("provinceid", cityList.get(0));
            zew.eq("cityid", cityList.get(1));
            zew.eq("areaid", cityList.get(2));
            List<Cityzhong> cityzhongs = cityzhongMapper.selectList(zew);
            if(cityzhongs.size() > 0){
                Cityzhong cityzhong = cityzhongs.get(0);
                shopinfo.setCityzhongid(cityzhong.getId() + "");
            }else{
                result.setMessage("城市没找到，无法处理");
                return result;
            }
        }

        if(param.getSfzimglist().size() > 0){
            shopinfo.setSfzimglist(param.getSfzimglist().toString().trim());
        }
        if(param.getYyzzimgs().size() > 0){
            shopinfo.setYyzzimgs(param.getYyzzimgs().toString().trim());
        }
        if(param.getQtimglist().size() > 0){
            shopinfo.setQtimglist(param.getQtimglist().toString().trim());
        }
        if(param.getYhtaglist().size() > 0){
            shopinfo.setYhtaglist(param.getYhtaglist().toString().trim());
        }

        shopinfo.setShoptypessid(param.getShoptypessid());
        shopinfo.setShopname(param.getShopname());
        shopinfo.setAddress(param.getAddress());
        shopinfo.setPhone(param.getPhone());
        shopinfo.setDpdescribe(param.getDpdescribe());
        shopinfo.setState(param.getState());
        shopinfo.setWxnum(param.getWxnum());
        shopinfo.setFmimglist(param.getFmimglist());
        shopinfo.setJgimglist(param.getJgimglist());
        shopinfo.setGzcout(param.getGzcout());
        shopinfo.setYytime(param.getYytime());
        shopinfo.setBrowsecout(param.getBrowsecout());
        shopinfo.setZztop(param.getZztop());
        shopinfo.setSoutop(param.getSoutop());
        shopinfo.setHometop(param.getHometop());
        shopinfo.setSortnum(param.getSortnum());
        shopinfo.setHometopendtime(param.getHometopendtime());

        int update = shopinfoMapper.update(shopinfo, ew);
        if (update > 0) {
            result.changeToTrue(update);
            result.setMessage("修改成功！");
        }

        LogUtil.intoLog("用户：修改了数据！" + param.getShopname());

        return result;
    }

    /**
     * 通过条件删除数据
     * @param result
     * @param param
     * @return
     */
    @Override
    public RResult deleteShopinfo(RResult result, DeleteShopinfoParam param) {
        UpdateWrapper<Shopinfo> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());

        Shopinfo shopinfo = shopinfoMapper.selectOne(ew);
        if(null == shopinfo){
            result.setMessage("删除的店铺不存在");
            return result;
        }

        Shopinfo shop = new Shopinfo();
        shop.setState(2);

        int delete = shopinfoMapper.update(shop, ew);
        if (delete > 0) {
            result.changeToTrue(delete);
            result.setMessage("删除成功！");
        }
        LogUtil.intoLog("用户：删除了一条数据！" + shopinfo.getUsername());
        return result;
    }


}