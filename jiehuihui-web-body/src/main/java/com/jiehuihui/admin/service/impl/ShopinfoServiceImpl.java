package com.jiehuihui.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiehuihui.admin.mapper.ShopyhmdMapper;
import com.jiehuihui.admin.mapper.UserMapper;
import com.jiehuihui.admin.mapper.city.CityzhongMapper;
import com.jiehuihui.admin.mapper.shop.ShopinfoMapper;
import com.jiehuihui.admin.req.shop.AddUpdateShopinfoParam;
import com.jiehuihui.admin.req.shop.DeleteShopinfoParam;
import com.jiehuihui.admin.req.shop.GetShopinfoPageParam;
import com.jiehuihui.admin.service.shop.ShopinfoService;
import com.jiehuihui.admin.vo.GetShopinfoVO;
import com.jiehuihui.common.entity.Shopyhmd;
import com.jiehuihui.common.entity.User;
import com.jiehuihui.common.entity.city.Cityzhong;
import com.jiehuihui.common.entity.shop.Shopinfo;
import com.jiehuihui.common.utils.LogUtil;
import com.jiehuihui.common.utils.OpenUtil;
import com.jiehuihui.common.utils.RResult;
import com.jiehuihui.config.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Autowired
    private ShopyhmdMapper shopyhmdMapper;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 通过ID查询单条数据
     * @param param 主键
     * @return 实例对象
     */
    @Transactional
    @Override
    public RResult getShopinfoByssid(RResult<Shopinfo> result, DeleteShopinfoParam param) {

        Shopinfo shopinfo = (Shopinfo) redisUtil.get(param.getSsid());
        if (null == shopinfo) {
            synchronized (this) {
                if(null == shopinfo){
                    UpdateWrapper<Shopinfo> ew = new UpdateWrapper();
                    ew.eq("s.ssid", param.getSsid());
                    List<Shopinfo> shopinfos = shopinfoMapper.getShopinfoList(ew);
                    if (null != shopinfos && shopinfos.size() > 0) {
                        shopinfo = shopinfos.get(0);
                        shopinfoMapper.addCount(param.getSsid());//自增浏览量
                        redisUtil.set(param.getSsid(), shopinfo, (int) ((Math.random() * 14) + 6) * 3600);//随机6-24小时过期
                    }

                }
            }
        }

        result.changeToTrue(shopinfo);
        if(null == shopinfo){
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

        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
        String week = simple.format((new Date()).getTime());
        UpdateWrapper<Shopinfo> ew = new UpdateWrapper<>();
        if (null != param.getTopnum() && param.getTopnum() > 0) {
            if (StringUtils.isNoneBlank(param.getProvinceid())) {
                ew.eq("z.provinceid", param.getProvinceid());
            }
            if (StringUtils.isNoneBlank(param.getCityid())) {
                ew.eq("z.cityid", param.getCityid());
            }
            if (StringUtils.isNoneBlank(param.getAreaid())) {
                ew.eq("z.areaid", param.getAreaid());
            }
            if(StringUtils.isNoneBlank(param.getShoptype())){
                ew.eq("s.shoptypessid", param.getShoptype());
            }
            ew.eq("s.soutop", 1);
            ew.ge("s.hometopendtime", week).or();
            ew.eq("s.state", 1);
        }else{
            ew.le("s.state", 1);//小于等于
        }

        if(StringUtils.isNoneBlank(param.getUserid())){
            ew.eq("s.userid", param.getUserid());
        }
        if(StringUtils.isNotEmpty(param.getShopname())){
            ew.like("s.shopname", param.getShopname());
        }
        if(StringUtils.isNotEmpty(param.getUsername())){
            ew.like("s.username", param.getUsername());
        }
        if(StringUtils.isNotEmpty(param.getPhone())){
            ew.like("s.phone", param.getPhone());
        }
        if(StringUtils.isNotEmpty(param.getTypeid()) && !"0".equals(param.getTypeid())){
            ew.eq("s.shoptypessid", param.getTypeid());
        }
        if(StringUtils.isNoneBlank(param.getShoptype())){
            ew.eq("s.shoptypessid", param.getShoptype());
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

        if (StringUtils.isNoneBlank(param.getProvinceid())) {
            ew.eq("z.provinceid", param.getProvinceid());
        }
        if (StringUtils.isNoneBlank(param.getCityid())) {
            ew.eq("z.cityid", param.getCityid());
        }
        if (StringUtils.isNoneBlank(param.getAreaid())) {
            ew.eq("z.areaid", param.getAreaid());
        }

        if(null != param.getTopnum() && param.getTopnum() > 0){
            ew.orderByDesc("s.soutop");
            ew.orderByDesc("s.hometopendtime");
        }
        ew.orderByDesc("s.createtime");

        Integer count = shopinfoMapper.selectShopinfoCount(ew);
        param.setRecordCount(count);

        Page<Shopinfo> page = new Page<>(param.getCurrPage(), param.getPageSize());
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
    @Transactional
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

        if(null != param.getShopyhmd()){
            Shopyhmd shopyhmd = param.getShopyhmd();
            shopyhmd.setState(1);
            shopyhmd.setShopid(ssid);
            shopyhmdMapper.insert(shopyhmd);
        }

        if(param.getFmimglist().size() > 0){
            shopinfo.setFmimglist(param.getFmimglist().toString().trim());
        }
        if(param.getJgimglist().size() > 0){
            shopinfo.setJgimglist(param.getJgimglist().toString().trim());
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

        shopinfo.setYhtaglist(param.getYhtaglist());
        shopinfo.setShoptypessid(param.getShoptypessid());
        shopinfo.setShopname(param.getShopname());
        shopinfo.setAddress(param.getAddress());
        shopinfo.setPhone(param.getPhone());
        shopinfo.setWxnum(param.getWxnum());
        shopinfo.setDpdescribe(param.getDpdescribe());
        shopinfo.setGzcout(param.getGzcout());
        shopinfo.setYytime(param.getYytime());
        shopinfo.setBrowsecout(param.getBrowsecout());
        shopinfo.setZztop(param.getZztop());
        shopinfo.setSoutop(param.getSoutop());
        shopinfo.setHometop(param.getHometop());
        shopinfo.setSortnum(param.getSortnum());
        shopinfo.setHometopendtime(param.getHometopendtime());
        shopinfo.setSsid(ssid);
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
    @Transactional
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

        if(null != param.getShopyhmd()){
            Shopyhmd shopyhmd = param.getShopyhmd();

            if(null == shopyhmd.getStarttime() || null == shopyhmd.getEndtime()){
                result.setMessage("提交免单，活动时间不能为空！");
                return result;
            }

            UpdateWrapper<Shopyhmd> sew = new UpdateWrapper<Shopyhmd>();
            Shopyhmd shopmd = new Shopyhmd();
            if(StringUtils.isNoneBlank(shopyhmd.getMdtitle())){
                shopmd.setMdtitle(shopyhmd.getMdtitle());
            }
            if(StringUtils.isNoneBlank(shopyhmd.getMdtag())){
                shopmd.setMdtag(shopyhmd.getMdtag());
            }
            if(StringUtils.isNoneBlank(shopyhmd.getMddescribe())){
                shopmd.setMddescribe(shopyhmd.getMddescribe());
            }
            if(StringUtils.isNoneBlank(shopyhmd.getContent())){
                shopmd.setContent(shopyhmd.getContent());
            }
            if(StringUtils.isNoneBlank(shopyhmd.getMdcontent())){
                shopmd.setMdcontent(shopyhmd.getMdcontent());
            }
            if(null != shopyhmd.getTopnum()){
                shopmd.setTopnum(shopyhmd.getTopnum());
            }
            if(null != shopyhmd.getTopendtime()){
                shopmd.setTopendtime(shopyhmd.getTopendtime());
            }

            shopmd.setStarttime(shopyhmd.getStarttime());
            shopmd.setEndtime(shopyhmd.getEndtime());
            if(StringUtils.isNoneBlank(shopyhmd.getFmimglist())){
                shopmd.setFmimglist(shopyhmd.getFmimglist().trim());
            }

            if(null == shopyhmd.getId() || shopyhmd.getId() == 0){
                if(StringUtils.isBlank(param.getSsid())){
                    result.setMessage("免单参数有误，无法提交");
                    return result;
                }
                shopmd.setShopid(param.getSsid());
                shopyhmdMapper.insert(shopmd);//新增免单
            }else{
                sew.eq("id", shopyhmd.getId());
                shopyhmdMapper.update(shopmd, sew);//修改免单
            }

        }

        if(null != param.getFmimglist() && param.getFmimglist().size() > 0){
            shopinfo.setFmimglist(param.getFmimglist().toString().trim());
        }
        if(null != param.getJgimglist() && param.getJgimglist().size() > 0){
            shopinfo.setJgimglist(param.getJgimglist().toString().trim());
        }
        if(null != param.getSfzimglist() && param.getSfzimglist().size() > 0){
            shopinfo.setSfzimglist(param.getSfzimglist().toString().trim());
        }
        if(null != param.getYyzzimgs() && param.getYyzzimgs().size() > 0){
            shopinfo.setYyzzimgs(param.getYyzzimgs().toString().trim());
        }
        if(null != param.getQtimglist() && param.getQtimglist().size() > 0){
            shopinfo.setQtimglist(param.getQtimglist().toString().trim());
        }

        shopinfo.setYhtaglist(param.getYhtaglist());
        shopinfo.setShoptypessid(param.getShoptypessid());
        shopinfo.setShopname(param.getShopname());
        shopinfo.setAddress(param.getAddress());
        shopinfo.setPhone(param.getPhone());
        shopinfo.setDpdescribe(param.getDpdescribe());
        shopinfo.setState(param.getState());
        shopinfo.setWxnum(param.getWxnum());
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