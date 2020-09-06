package com.jiehuihui.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiehuihui.admin.mapper.UserMapper;
import com.jiehuihui.admin.mapper.city.CityzhongMapper;
import com.jiehuihui.admin.mapper.home.HomespecialMapper;
import com.jiehuihui.admin.mapper.shop.ShopinfoMapper;
import com.jiehuihui.admin.req.AddUpdateSpecialParam;
import com.jiehuihui.admin.req.DeleteSpecialParam;
import com.jiehuihui.admin.req.GetSpecialListParam;
import com.jiehuihui.admin.req.GetSpecialPageParam;
import com.jiehuihui.admin.vo.GetShopinfoVO;
import com.jiehuihui.admin.vo.home.GetSpecialPageVO;
import com.jiehuihui.common.entity.Homespecial;
import com.jiehuihui.admin.service.home.HomespecialService;
import com.jiehuihui.common.entity.Role;
import com.jiehuihui.common.entity.Shopinfoup;
import com.jiehuihui.common.entity.User;
import com.jiehuihui.common.entity.city.Cityzhong;
import com.jiehuihui.common.entity.shop.Shopinfo;
import com.jiehuihui.common.utils.DateUtil;
import com.jiehuihui.common.utils.LogUtil;
import com.jiehuihui.common.utils.OpenUtil;
import com.jiehuihui.common.utils.RResult;
import com.jiehuihui.config.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (JhhBaseHomespecial)特价服务实现类
 *
 * @author zhuang
 * @since 2020-07-09 18:29:16
 */
@Service("homespecialService")
public class HomespecialServiceImpl implements HomespecialService {

    @Autowired
    private HomespecialMapper homespecialMapper;

    @Autowired
    private CityzhongMapper cityzhongMapper;

    @Autowired
    private ShopinfoMapper shopinfoMapper;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 通过ID查询单条数据
     *
     * @param param 主键
     * @return 实例对象
     */

    @Transactional
    @Override
    public RResult getSpecialByssid(RResult result, DeleteSpecialParam param) {

        Homespecial homespecial = (Homespecial) redisUtil.get(param.getSsid());
        if (null == homespecial) {
            synchronized (this) {
                if(null == homespecial){
                    UpdateWrapper<Homespecial> ew = new UpdateWrapper();
                    ew.eq("h.ssid", param.getSsid());
                    List<Homespecial> homespecials = homespecialMapper.getHomespecialList(ew);
                    if (null != homespecials && homespecials.size() > 0) {
                        homespecial = homespecials.get(0);
                        homespecialMapper.addCount(param.getSsid());//自增浏览量
                        redisUtil.set(param.getSsid(), homespecial, (int) ((Math.random() * 14) + 6) * 3600);//随机6-24小时过期
                    }

                }
            }
        }

        result.changeToTrue(homespecial);
        if(null == homespecial){
            result.setMessage("获取失败，该条数据不存在");
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
    public RResult getSpecialPage(RResult result, GetSpecialPageParam param) {

        GetSpecialPageVO getSpecialPageVO = new GetSpecialPageVO();

        UpdateWrapper<Homespecial> ew = new UpdateWrapper<>();
        if(null != param.getTopnum() && param.getTopnum() > 0) {
            if(StringUtils.isNotEmpty(param.getShopname())){
                ew.like("h.shopname", param.getShopname());
            }
            if(StringUtils.isNotEmpty(param.getSpecialtitle())){
                ew.like("h.specialtitle", param.getSpecialtitle());
            }
            if(StringUtils.isNotEmpty(param.getTypeid()) && !"0".equals(param.getTypeid())){
                ew.eq("h.specialtypessid", param.getTypeid());
            }
            if(null != param.getState()){
                ew.eq("h.state", 1);
                if (StringUtils.isNoneBlank(param.getWeek())) {
                    ew.like("h.settime", param.getWeek());
                }
            }else{
                ew.le("h.state", 1);
            }
            ew.eq("h.topnum",1);
            ew.ge("h.topendtime",DateUtil.getDateAndMinute()).or();
        }


        if(StringUtils.isNotEmpty(param.getShopname())){
            ew.like("h.shopname", param.getShopname());
        }
        if(StringUtils.isNotEmpty(param.getSpecialtitle())){
            ew.like("h.specialtitle", param.getSpecialtitle());
        }
        if(StringUtils.isNotEmpty(param.getTypeid()) && !"0".equals(param.getTypeid())){
            ew.eq("h.specialtypessid", param.getTypeid());
        }

        if(null != param.getState()){
            ew.eq("h.state", 1);
            if (StringUtils.isNoneBlank(param.getWeek())) {
                ew.like("h.settime", param.getWeek());
            }
        }else{
            ew.le("h.state", 1);
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
                ew.eq("h.cityzhongid", cityzhong.getId());
            }
        }

        if (StringUtils.isNoneBlank(param.getProvinceid())) {
            ew.eq("z.provinceid", param.getProvinceid());
        }
        if (StringUtils.isNoneBlank(param.getCityid())) {
            ew.eq("z.cityid", param.getCityid());
        }
        if (StringUtils.isNoneBlank(param.getAreaid())) {
            ew.eq("z.areaid", param.getAreaid());
        }

        if(null != param.getTopnum() && param.getTopnum() > 0) {
            ew.orderByDesc("h.topendtime");
        }else{
            ew.orderByDesc("h.hometoptime");
        }
        ew.orderByDesc("h.createtime");

        Integer count = homespecialMapper.selectHomespecialCount(ew);
        param.setRecordCount(count);

        Page<Homespecial> page = new Page<>(param.getCurrPage(), param.getPageSize());
        page.setTotal(count);
        IPage<Homespecial> homespecialPage = homespecialMapper.getHomespecialPage(page, ew);

        getSpecialPageVO.setPagelist(homespecialPage.getRecords());
        getSpecialPageVO.setPageparam(param);

        result.changeToTrue(getSpecialPageVO);
        return result;
    }

    /**
     * 查询多条数据list
     * @param result
     * @param param  实例参数对象
     * @return
     */
    @Override
    public RResult getSpecialList(RResult result, GetSpecialListParam param) {
        //根据星期去找到优惠
        String week = param.getWeek();
        //还要获取当前城市作为查询条件

        UpdateWrapper<Homespecial> ew = new UpdateWrapper<>();
        ew.eq("", week);
        List<Homespecial> list = homespecialMapper.selectList(ew);
        result.changeToTrue(list);


        return result;
    }

    /**
     * 新增数据
     * @param result
     * @param param
     * @return
     */
    @Override
    public RResult addSpecial(RResult result, AddUpdateSpecialParam param) {
        //先校验添加的ssid是否已经存在，在判断添加的参数是否已经存在
        String ssid = OpenUtil.getUUID_32();
        if(StringUtils.isNoneBlank(param.getSsid())){
            UpdateWrapper<Homespecial> ew = new UpdateWrapper<>();
            ew.eq("ssid", param.getSsid());
            List<Homespecial> homespecials = homespecialMapper.selectList(ew);
            if (null != homespecials && homespecials.size() > 0) {
                result.setMessage("该ssid已经存在，不能添加");
                return result;
            }
            ssid = param.getSsid();
        }

        UpdateWrapper<Homespecial> ew = new UpdateWrapper<>();
        ew.eq("specialtitle", param.getSpecialtitle());
        List<Homespecial> homespecialList = homespecialMapper.selectList(ew);
        if (null != homespecialList && homespecialList.size() > 0) {
            result.setMessage("该特价已经存在，不能添加");
            return result;
        }

        Homespecial homespecial = new Homespecial();

        //查询关联店铺信息
        UpdateWrapper<Shopinfo> muew = new UpdateWrapper<Shopinfo>();
        muew.eq("shopname", param.getShopname().trim());
        List<Shopinfo> musers = shopinfoMapper.selectList(muew);
        if(musers.size() == 0){
            result.setMessage("没找到关联店铺，添加特价失败");
            return result;
        }
        homespecial.setShopid(musers.get(0).getSsid());

        List<String> cityList = param.getCityList();
        if(null != cityList && cityList.size() == 3){
            UpdateWrapper<Cityzhong> zew = new UpdateWrapper<>();
            zew.eq("provinceid", cityList.get(0));
            zew.eq("cityid", cityList.get(1));
            zew.eq("areaid", cityList.get(2));
            List<Cityzhong> cityzhongs = cityzhongMapper.selectList(zew);
            if(cityzhongs.size() > 0){
                Cityzhong cityzhong = cityzhongs.get(0);
                homespecial.setCityzhongid(cityzhong.getId() + "");
            }else{
                result.setMessage("城市没找到，无法处理");
                return result;
            }
        }

        if(param.getFmimglist().size() > 0){
            homespecial.setFmimglist(param.getFmimglist().toString().trim());
        }else{
            homespecial.setFmimglist("");
        }
        if(param.getHdimglist().size() > 0){
            homespecial.setHdimglist(param.getHdimglist().toString().trim());
        }else{
            homespecial.setHdimglist("");
        }
        if(param.getTaskimglist().size() > 0){
            homespecial.setTaskimglist(param.getTaskimglist().toString().trim());
        }else{
            homespecial.setTaskimglist("");
        }

        homespecial.setSpecialtitle(param.getSpecialtitle());
        homespecial.setShopname(param.getShopname().trim());
        homespecial.setImgtag(param.getImgtag());
        homespecial.setTitledescribe(param.getTitledescribe());
        homespecial.setOldprice(param.getOldprice());
        homespecial.setNowprice(param.getNowprice());
        homespecial.setOrdertext(param.getOrdertext());
        homespecial.setHddescribe(param.getHddescribe());
        homespecial.setTasktext(param.getTasktext());
        homespecial.setTaskdescribe(param.getTaskdescribe());
        homespecial.setDynamictags(param.getDynamictags());
        homespecial.setGototag(param.getGototag());
        homespecial.setLikesize(param.getLikesize());
        homespecial.setTopnum(param.getTopnum());
        homespecial.setHometopnum(param.getHometopnum());
        homespecial.setSpecialtypessid(param.getSpecialtypessid());
        homespecial.setSettime(param.getSettime());
        homespecial.setSortnum(param.getSortnum());
        homespecial.setTopendtime(param.getTopendtime());
        homespecial.setHometoptime(param.getHometoptime());
        homespecial.setSsid(ssid);
        homespecial.setState(param.getState());
        int insert = homespecialMapper.insert(homespecial);
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
    public RResult updateSpecial(RResult result, AddUpdateSpecialParam param) {
        //先校验是否已经存在
        UpdateWrapper<Homespecial> ewcheck = new UpdateWrapper<>();
        ewcheck.eq("specialtitle", param.getSpecialtitle());
        ewcheck.ne("ssid", param.getSsid());
        List<Homespecial> homespecials = homespecialMapper.selectList(ewcheck);
        if (null != homespecials && homespecials.size() > 0) {
            result.setMessage("修改的特价标题不能重复！");
            return result;
        }

        UpdateWrapper<Homespecial> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());

        Homespecial homespecial = new Homespecial();

        //查询关联店铺信息
        UpdateWrapper<Shopinfo> muew = new UpdateWrapper<Shopinfo>();
        muew.eq("shopname", param.getShopname().trim());
        List<Shopinfo> musers = shopinfoMapper.selectList(muew);
        if(musers.size() == 0){
            result.setMessage("没找到关联店铺，添加特价失败");
            return result;
        }
        homespecial.setShopid(musers.get(0).getSsid());

        List<String> cityList = param.getCityList();
        if(null != cityList && cityList.size() == 3){
            UpdateWrapper<Cityzhong> zew = new UpdateWrapper<>();
            zew.eq("provinceid", cityList.get(0));
            zew.eq("cityid", cityList.get(1));
            zew.eq("areaid", cityList.get(2));
            List<Cityzhong> cityzhongs = cityzhongMapper.selectList(zew);
            if(cityzhongs.size() > 0){
                Cityzhong cityzhong = cityzhongs.get(0);
                homespecial.setCityzhongid(cityzhong.getId() + "");
            }else{
                result.setMessage("城市没找到，无法处理");
                return result;
            }
        }

        if(param.getFmimglist().size() > 0){
            homespecial.setFmimglist(param.getFmimglist().toString().trim());
        }else{
            homespecial.setFmimglist("");
        }
        if(param.getHdimglist().size() > 0){
            homespecial.setHdimglist(param.getHdimglist().toString().trim());
        }else{
            homespecial.setHdimglist("");
        }
        if(param.getTaskimglist().size() > 0){
            homespecial.setTaskimglist(param.getTaskimglist().toString().trim());
        }else{
            homespecial.setTaskimglist("");
        }
        if(param.getTaskimglist().size() > 0){
            homespecial.setTaskimglist(param.getTaskimglist().toString().trim());
        }else{
            homespecial.setTaskimglist("");
        }



        homespecial.setSpecialtitle(param.getSpecialtitle());
        homespecial.setShopname(param.getShopname());
        homespecial.setImgtag(param.getImgtag());
        homespecial.setTitledescribe(param.getTitledescribe());
        homespecial.setOldprice(param.getOldprice());
        homespecial.setNowprice(param.getNowprice());
        homespecial.setOrdertext(param.getOrdertext());
        homespecial.setHddescribe(param.getHddescribe());
        homespecial.setTasktext(param.getTasktext());
        homespecial.setTaskdescribe(param.getTaskdescribe());
        homespecial.setDynamictags(param.getDynamictags());
        homespecial.setGototag(param.getGototag());
        homespecial.setLikesize(param.getLikesize());
        homespecial.setTopnum(param.getTopnum());
        homespecial.setHometopnum(param.getHometopnum());
        homespecial.setSpecialtypessid(param.getSpecialtypessid());
        homespecial.setSettime(param.getSettime());
        homespecial.setSortnum(param.getSortnum());
        homespecial.setTopendtime(param.getTopendtime());
        homespecial.setHometoptime(param.getHometoptime());
        homespecial.setState(param.getState());

        int update = homespecialMapper.update(homespecial, ew);
        if (update > 0) {
            redisUtil.del(param.getSsid());//清除缓存
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
    public RResult deleteSpecial(RResult result, DeleteSpecialParam param) {
        UpdateWrapper<Homespecial> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());

        Homespecial special = homespecialMapper.selectOne(ew);
        if(null == special){
            result.setMessage("删除的特价不存在");
            return result;
        }

        Homespecial homespecial = new Homespecial();
        homespecial.setState(2);

        int delete = homespecialMapper.update(homespecial, ew);
        if (delete > 0) {
            result.changeToTrue(delete);
            result.setMessage("删除成功！");
        }
        LogUtil.intoLog("用户：删除了一条数据！" + homespecial.getSpecialtitle());
        return result;
    }
}