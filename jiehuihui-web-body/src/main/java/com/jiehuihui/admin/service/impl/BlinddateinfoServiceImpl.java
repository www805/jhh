package com.jiehuihui.admin.service.impl;

import com.jiehuihui.common.entity.Blinddateinfo;
import com.jiehuihui.admin.mapper.BlinddateinfoMapper;
import com.jiehuihui.admin.mapper.UserMapper;
import com.jiehuihui.admin.mapper.city.CityzhongMapper;
import com.jiehuihui.admin.service.BlinddateinfoService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiehuihui.admin.req.AddUpdateBlinddateinfoParam;
import com.jiehuihui.admin.req.DeleteBlinddateinfoParam;
import com.jiehuihui.admin.req.GetBlinddateinfoPageParam;
import com.jiehuihui.admin.vo.GetBlinddateinfoVO;
import com.jiehuihui.common.entity.User;
import com.jiehuihui.common.entity.city.Cityzhong;
import com.jiehuihui.common.utils.LogUtil;
import com.jiehuihui.common.utils.OpenUtil;
import com.jiehuihui.common.utils.RResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Blinddateinfo)表服务实现类
 *
 * @author zhuang
 * @since 2020-05-03 22:48:58
 */
@Service("blinddateinfoService")
public class BlinddateinfoServiceImpl implements BlinddateinfoService {

    @Resource
    private BlinddateinfoMapper blinddateinfoMapper;

    @Autowired
    private CityzhongMapper cityzhongMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public RResult getBlinddateinfo(RResult result) {
        UpdateWrapper<Blinddateinfo> ew = new UpdateWrapper<>();
        ew.eq("state", 1);
        ew.orderByDesc("sortnum");
        List<Blinddateinfo> blinddateinfos = blinddateinfoMapper.selectList(ew);
        result.changeToTrue(blinddateinfos);
        return result;
    }

    @Override
    public RResult getBlinddateinfoByssid(RResult result, DeleteBlinddateinfoParam param) {
        UpdateWrapper<Blinddateinfo> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());
        Blinddateinfo blinddateinfo = blinddateinfoMapper.selectList(ew).get(0);
        if (null != blinddateinfo) {
            result.changeToTrue(blinddateinfo);
        }else{
            result.setMessage("获取失败，该条数据不存在");
        }
        return result;
    }

    @Override
    public RResult getBlinddateinfoPage(RResult result, GetBlinddateinfoPageParam param) {

        GetBlinddateinfoVO blinddateinfoVO = new GetBlinddateinfoVO();

        UpdateWrapper<Blinddateinfo> ew = new UpdateWrapper<>();
        if(StringUtils.isNotEmpty(param.getUsername())){
            ew.like("b.myname", param.getUsername());
        }
        if(StringUtils.isNotEmpty(param.getPhone())){
            ew.like("b.phone", param.getPhone());
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
                ew.eq("b.cityzhongid", cityzhong.getId());
            }
        }

        ew.orderByDesc("b.sortnum");
        ew.orderByDesc("b.createtime");

        Integer count = blinddateinfoMapper.selectBlinddateinfoCount(ew);
        param.setRecordCount(count);

        Page<Blinddateinfo> page = new Page<>(param.getCurrPage(), param.getPageSize());
        page.setTotal(count);
        IPage<Blinddateinfo> sqCacheList = blinddateinfoMapper.getBlinddateinfoPage(page, ew);

        blinddateinfoVO.setPagelist(sqCacheList.getRecords());
        blinddateinfoVO.setPageparam(param);

        result.changeToTrue(blinddateinfoVO);
        return result;
    }

    @Override
    public RResult addBlinddateinfo(RResult result, AddUpdateBlinddateinfoParam param) {
        //先校验添加的ssid是否已经存在，在判断添加的参数是否已经存在
        String ssid = OpenUtil.getUUID_32();
        if(StringUtils.isNoneBlank(param.getSsid())){
            UpdateWrapper<Blinddateinfo> ew = new UpdateWrapper<>();
            ew.eq("ssid", param.getSsid());
            if(null != blinddateinfoMapper.selectList(ew) && blinddateinfoMapper.selectList(ew).size() > 0){
                result.setMessage("该ssid已经存在，不能添加");
                return result;
            }
            ssid = param.getSsid();
        }

        //查询发布人信息
        UpdateWrapper<User> uew = new UpdateWrapper<User>();
        uew.eq("username", param.getUsername());
        List<User> users = userMapper.selectList(uew);
        if(users.size() == 0){
            result.setMessage("没找到发布人用户，发布相亲失败");
            return result;
        }

        //查询红娘信息
        UpdateWrapper<User> muew = new UpdateWrapper<User>();
        muew.eq("username", param.getMatchmake());
        List<User> musers = userMapper.selectList(muew);
        if(musers.size() == 0){
            result.setMessage("没找到红娘用户，发布相亲失败");
            return result;
        }

        Blinddateinfo blinddateinfo = new Blinddateinfo();

        List<String> cityList = param.getCityList();
        if(null != cityList && cityList.size() == 3){
            UpdateWrapper<Cityzhong> zew = new UpdateWrapper<>();
            zew.eq("provinceid", cityList.get(0));
            zew.eq("cityid", cityList.get(1));
            zew.eq("areaid", cityList.get(2));
            List<Cityzhong> cityzhongs = cityzhongMapper.selectList(zew);
            if(cityzhongs.size() > 0){
                Cityzhong cityzhong = cityzhongs.get(0);
                blinddateinfo.setCityzhongid(cityzhong.getId() + "");
            }else{
                result.setMessage("城市没找到，无法处理");
                return result;
            }
        }

        blinddateinfo.setMyname(param.getMyname());
        blinddateinfo.setXqdescribe(param.getXqdescribe());
        blinddateinfo.setTabs(param.getTabs());
        blinddateinfo.setSex(param.getSex());
        blinddateinfo.setPhone(param.getPhone());
        blinddateinfo.setWxnum(param.getWxnum());
        blinddateinfo.setMatchmakerid(musers.get(0).getSsid());
        blinddateinfo.setUserid(users.get(0).getSsid());
        blinddateinfo.setTximg(param.getTximg());
        blinddateinfo.setFmimglist(param.getFmimglist().toString().trim());
        blinddateinfo.setTopnum(param.getTopnum());
        blinddateinfo.setSortnum(param.getSortnum());
        blinddateinfo.setTopendtime(param.getTopendtime());
        blinddateinfo.setSsid(ssid);
        blinddateinfo.setState(param.getState());
        int insert = blinddateinfoMapper.insert(blinddateinfo);
        if (insert > 0) {
            result.changeToTrue(insert);
            result.setMessage("新增相亲帖成功！");
        }
        return result;
    }

    @Override
    public RResult updateBlinddateinfo(RResult result, AddUpdateBlinddateinfoParam param) {

        UpdateWrapper<Blinddateinfo> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());

        //查询发布人信息
        UpdateWrapper<User> uew = new UpdateWrapper<User>();
        uew.eq("username", param.getUsername());
        List<User> users = userMapper.selectList(uew);
        if(users.size() == 0){
            result.setMessage("没找到发布人用户，修改相亲失败");
            return result;
        }

        //查询红娘信息
        UpdateWrapper<User> muew = new UpdateWrapper<User>();
        muew.eq("username", param.getMatchmake());
        List<User> musers = userMapper.selectList(muew);
        if(musers.size() == 0){
            result.setMessage("没找到红娘用户，修改相亲失败");
            return result;
        }

        Blinddateinfo blinddateinfo = new Blinddateinfo();

        List<String> cityList = param.getCityList();
        if(null != cityList && cityList.size() == 3){
            UpdateWrapper<Cityzhong> zew = new UpdateWrapper<>();
            zew.eq("provinceid", cityList.get(0));
            zew.eq("cityid", cityList.get(1));
            zew.eq("areaid", cityList.get(2));
            List<Cityzhong> cityzhongs = cityzhongMapper.selectList(zew);
            if(cityzhongs.size() > 0){
                Cityzhong cityzhong = cityzhongs.get(0);
                blinddateinfo.setCityzhongid(cityzhong.getId() + "");
            }else{
                result.setMessage("城市没找到，无法处理");
                return result;
            }
        }

        blinddateinfo.setMyname(param.getMyname());
        blinddateinfo.setXqdescribe(param.getXqdescribe());
        blinddateinfo.setTabs(param.getTabs());
        blinddateinfo.setSex(param.getSex());
        blinddateinfo.setPhone(param.getPhone());
        blinddateinfo.setWxnum(param.getWxnum());
        blinddateinfo.setMatchmakerid(musers.get(0).getSsid());
        blinddateinfo.setUserid(users.get(0).getSsid());
        blinddateinfo.setTximg(param.getTximg());
        blinddateinfo.setFmimglist(param.getFmimglist().toString().trim());
        blinddateinfo.setTopnum(param.getTopnum());
        blinddateinfo.setSortnum(param.getSortnum());
        blinddateinfo.setTopendtime(param.getTopendtime());
        blinddateinfo.setState(param.getState());

        int update = blinddateinfoMapper.update(blinddateinfo, ew);
        if (update > 0) {
            result.changeToTrue(update);
            result.setMessage("修改成功！");
        }

        LogUtil.intoLog("用户：修改了数据！" + param.getMyname());

        return result;
    }

    @Override
    public RResult deleteBlinddateinfo(RResult result, DeleteBlinddateinfoParam param) {
        UpdateWrapper<Blinddateinfo> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());

        Blinddateinfo blinddateinfo = blinddateinfoMapper.selectOne(ew);
        if(null == blinddateinfo){
            result.setMessage("删除的数据不存在");
            return result;
        }

        Blinddateinfo blinddate = new Blinddateinfo();
        blinddate.setState(2);

        int delete = blinddateinfoMapper.update(blinddate,ew);
        if (delete > 0) {
            result.changeToTrue(delete);
            result.setMessage("删除成功！");
        }
        LogUtil.intoLog("用户：删除了一条数据！" + blinddateinfo.getMyname());
        return result;
    }

    @Override
    public RResult gettopBlinddate(RResult result, DeleteBlinddateinfoParam param) {
        UpdateWrapper<Blinddateinfo> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());

        Blinddateinfo blinddateinfo = new Blinddateinfo();
        blinddateinfo.setTopnum(param.getTopnum());

        int update = blinddateinfoMapper.update(blinddateinfo, ew);
        if (update > 0) {
            result.changeToTrue(update);
            result.setMessage("修改置顶成功！");
        }

        LogUtil.intoLog("置顶相亲信息：" + blinddateinfo.getMyname());
        return result;
    }
}