package com.jiehuihui.admin.service.impl;

import com.jiehuihui.admin.mapper.BlinddateinfoMapper;
import com.jiehuihui.admin.mapper.UserMapper;
import com.jiehuihui.admin.mapper.city.CityzhongMapper;
import com.jiehuihui.admin.service.BlinddateinfoService;
import com.jiehuihui.common.entity.Blinddate;
import com.jiehuihui.admin.mapper.BlinddateMapper;
import com.jiehuihui.admin.service.BlinddateService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiehuihui.admin.req.AddUpdateBlinddateParam;
import com.jiehuihui.admin.req.DeleteBlinddateParam;
import com.jiehuihui.admin.req.GetBlinddatePageParam;
import com.jiehuihui.admin.vo.GetBlinddateVO;
import com.jiehuihui.common.entity.Blinddateinfo;
import com.jiehuihui.common.entity.Friendstype;
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
 * (Blinddate)表服务实现类
 *
 * @author zhuang
 * @since 2020-05-04 18:12:51
 */
@Service("blinddateService")
public class BlinddateServiceImpl implements BlinddateService {
    @Resource
    private BlinddateMapper blinddateMapper;

    @Autowired
    private CityzhongMapper cityzhongMapper;

    @Autowired
    private BlinddateinfoMapper blinddateinfoMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public RResult getBlinddate(RResult result) {
        UpdateWrapper<Blinddate> ew = new UpdateWrapper<>();
        ew.eq("state", 1);
        List<Blinddate> blinddates = blinddateMapper.selectList(ew);
        result.changeToTrue(blinddates);
        return result;
    }

    @Override
    public RResult getBlinddateByssid(RResult result, DeleteBlinddateParam param) {
        UpdateWrapper<Blinddate> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());
        List<Blinddate> blinddates = blinddateMapper.selectList(ew);
        if (null != blinddates && blinddates.size() > 0) {
            Blinddate blinddate = blinddates.get(0);
            result.changeToTrue(blinddate);
        }else{
            result.setMessage("获取失败，该条数据不存在");
        }
        return result;
    }

    @Override
    public RResult getBlinddatePage(RResult result, GetBlinddatePageParam param) {

        GetBlinddateVO blinddateVO = new GetBlinddateVO();

        UpdateWrapper<Blinddate> ew = new UpdateWrapper<>();
        if(StringUtils.isNotEmpty(param.getUsername())){
            ew.like("b.username", param.getUsername());
        }
        if(StringUtils.isNotEmpty(param.getPhone())){
            ew.like("b.phone", param.getPhone());
        }
        if(null != param.getState() && param.getState() >= 0 && param.getState() != 2){
            ew.eq("b.state", param.getState());
        }else{
            ew.ne("b.state", 2);
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

        ew.orderByDesc("b.createtime");

        Integer count = blinddateMapper.selectBlinddateCount(ew);
        param.setRecordCount(count);

        Page<Blinddate> page = new Page<>(param.getCurrPage(), param.getPageSize());
        page.setTotal(count);
        IPage<Blinddate> sqCacheList = blinddateMapper.getBlinddatePage(page, ew);

        blinddateVO.setPagelist(sqCacheList.getRecords());
        blinddateVO.setPageparam(param);

        result.changeToTrue(blinddateVO);
        return result;
    }

    @Override
    public RResult addBlinddate(RResult result, AddUpdateBlinddateParam param) {
        //先校验添加的ssid是否已经存在，在判断添加的参数是否已经存在
        String ssid = OpenUtil.getUUID_32();
        if(StringUtils.isNoneBlank(param.getSsid())){
            UpdateWrapper<Blinddate> ew = new UpdateWrapper<>();
            ew.eq("ssid", param.getSsid());
            List<Blinddate> blinddates = blinddateMapper.selectList(ew);
            if(null != blinddates && blinddates.size() > 0){
                result.setMessage("该ssid已经存在，不能添加");
                return result;
            }
            ssid = param.getSsid();
        }

        //查询红娘信息
        UpdateWrapper<User> muew = new UpdateWrapper<User>();
        muew.eq("username", param.getMatchmake());
        List<User> musers = userMapper.selectList(muew);
        if(musers.size() == 0){
            result.setMessage("没找到红娘用户，修改申请相亲申请失败");
            return result;
        }

        UpdateWrapper<Blinddateinfo> bew = new UpdateWrapper<Blinddateinfo>();
        bew.eq("ssid", param.getBlinddateid());
        List<Blinddateinfo> blinddateinfos = blinddateinfoMapper.selectList(bew);
        if(blinddateinfos.size() == 0){
            result.setMessage("没找到相亲帖，修改申请相亲申请失败");
            return result;
        }

        Blinddate blinddate = new Blinddate();

        List<String> cityList = param.getCityList();
        if(null != cityList && cityList.size() == 3){
            UpdateWrapper<Cityzhong> zew = new UpdateWrapper<>();
            zew.eq("provinceid", cityList.get(0));
            zew.eq("cityid", cityList.get(1));
            zew.eq("areaid", cityList.get(2));
            List<Cityzhong> cityzhongs = cityzhongMapper.selectList(zew);
            if(cityzhongs.size() > 0){
                Cityzhong cityzhong = cityzhongs.get(0);
                blinddate.setCityzhongid(cityzhong.getId() + "");
            }else{
                result.setMessage("城市没找到，无法处理");
                return result;
            }
        }

        blinddate.setUsername(param.getUsername());
        blinddate.setPhone(param.getPhone());
        blinddate.setWxnum(param.getWxnum());
        blinddate.setXqdescribe(param.getXqdescribe());
        blinddate.setMatchmakerid(musers.get(0).getSsid());
        blinddate.setBlinddateid(blinddateinfos.get(0).getSsid());
        blinddate.setSortnum(param.getSortnum());
        blinddate.setSsid(param.getSsid());
        blinddate.setState(param.getState());

        int insert = blinddateMapper.insert(blinddate);
        if (insert > 0) {
            result.changeToTrue(insert);
            result.setMessage("相亲申请成功！");
        }

        return result;
    }

    @Override
    public RResult updateBlinddate(RResult result, AddUpdateBlinddateParam param) {
        //先校验是否已经存在

        //查询红娘信息
        UpdateWrapper<User> muew = new UpdateWrapper<User>();
        muew.eq("username", param.getMatchmake());
        List<User> musers = userMapper.selectList(muew);
        if(musers.size() == 0){
            result.setMessage("没找到红娘用户，修改申请相亲申请失败");
            return result;
        }

        UpdateWrapper<Blinddateinfo> bew = new UpdateWrapper<Blinddateinfo>();
        bew.eq("ssid", param.getBlinddateid());
        List<Blinddateinfo> blinddateinfos = blinddateinfoMapper.selectList(bew);
        if(blinddateinfos.size() == 0){
            result.setMessage("没找到相亲帖，修改申请相亲申请失败");
            return result;
        }

        UpdateWrapper<Blinddate> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());

        Blinddate blinddate = new Blinddate();

        List<String> cityList = param.getCityList();
        if(null != cityList && cityList.size() == 3){
            UpdateWrapper<Cityzhong> zew = new UpdateWrapper<>();
            zew.eq("provinceid", cityList.get(0));
            zew.eq("cityid", cityList.get(1));
            zew.eq("areaid", cityList.get(2));
            List<Cityzhong> cityzhongs = cityzhongMapper.selectList(zew);
            if(cityzhongs.size() > 0){
                Cityzhong cityzhong = cityzhongs.get(0);
                blinddate.setCityzhongid(cityzhong.getId() + "");
            }else{
                result.setMessage("城市没找到，无法处理");
                return result;
            }
        }

        blinddate.setUsername(param.getUsername());
        blinddate.setPhone(param.getPhone());
        blinddate.setWxnum(param.getWxnum());
        blinddate.setXqdescribe(param.getXqdescribe());
        blinddate.setMatchmakerid(musers.get(0).getSsid());
        blinddate.setBlinddateid(blinddateinfos.get(0).getSsid());
        blinddate.setSortnum(param.getSortnum());
        blinddate.setState(param.getState());

        int update = blinddateMapper.update(blinddate, ew);
        if (update > 0) {
            result.changeToTrue(update);
            result.setMessage("修改成功！");
        }

        LogUtil.intoLog("用户：修改了数据！" + param.getUsername());

        return result;
    }

    @Override
    public RResult deleteBlinddate(RResult result, DeleteBlinddateParam param) {
        UpdateWrapper<Blinddate> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());

        Blinddate blinddate = blinddateMapper.selectOne(ew);
        if(null == blinddate){
            result.setMessage("删除的数据不存在");
            return result;
        }

        Blinddate blind = new Blinddate();
        blind.setState(2);

        int delete = blinddateMapper.update(blind,ew);
        if (delete > 0) {
            result.changeToTrue(delete);
            result.setMessage("删除成功！");
        }

        LogUtil.intoLog("用户：删除了一条数据！" + blinddate.getUsername());
        return result;
    }
}