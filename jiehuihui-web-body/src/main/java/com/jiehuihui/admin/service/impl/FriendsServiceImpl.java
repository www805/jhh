package com.jiehuihui.admin.service.impl;

import com.jiehuihui.common.entity.Friends;
import com.jiehuihui.admin.mapper.FriendsMapper;
import com.jiehuihui.admin.mapper.UserMapper;
import com.jiehuihui.admin.mapper.city.CityzhongMapper;
import com.jiehuihui.admin.service.FriendsService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiehuihui.admin.req.AddUpdateFriendsParam;
import com.jiehuihui.admin.req.DeleteFriendsParam;
import com.jiehuihui.admin.req.GetFriendsPageParam;
import com.jiehuihui.admin.vo.GetFriendsVO;
import com.jiehuihui.common.entity.User;
import com.jiehuihui.common.entity.city.Cityzhong;
import com.jiehuihui.common.utils.DateUtil;
import com.jiehuihui.common.utils.LogUtil;
import com.jiehuihui.common.utils.OpenUtil;
import com.jiehuihui.common.utils.RResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (Friends)表服务实现类
 *
 * @author zhuang
 * @since 2020-05-02 14:39:37
 */
@Service("friendsService")
public class FriendsServiceImpl implements FriendsService {

    @Resource
    private FriendsMapper friendsMapper;

    @Autowired
    private CityzhongMapper cityzhongMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public RResult getFriends(RResult result) {
        UpdateWrapper<Friends> ew = new UpdateWrapper<>();
        ew.eq("state", 1);
        ew.orderByDesc("sortnum");
        List<Friends> friendss = friendsMapper.selectList(ew);
        result.changeToTrue(friendss);
        return result;
    }

    @Override
    public RResult getFriendsByssid(RResult result, DeleteFriendsParam param) {
        UpdateWrapper<Friends> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());
        List<Friends> friends = friendsMapper.selectList(ew);
        if (null != friends && friends.size() > 0) {
            Friends friend = friends.get(0);
            result.changeToTrue(friend);
        }else{
            result.setMessage("获取失败，该条数据不存在");
        }
        return result;
    }

    @Override
    public RResult getFriendsPage(RResult result, GetFriendsPageParam param) {

        GetFriendsVO friendsVO = new GetFriendsVO();

        UpdateWrapper<Friends> ew = new UpdateWrapper<>();
        if(null != param.getTopnum() && param.getTopnum() > 0) {
            if (StringUtils.isNoneBlank(param.getTypeid()) && !"0".equalsIgnoreCase(param.getTypeid())) {
                ew.eq("f.typeid", param.getTypeid());
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
            if (StringUtils.isNoneBlank(param.getContent()) && !"0".equals(param.getContent())) {
                ew.like("f.content", param.getContent());
            }
            ew.eq("f.topnum",1);
            ew.ge("f.topendtime",DateUtil.getDateAndMinute()).or();
        }
        if(StringUtils.isNotEmpty(param.getUsername())){
            ew.like("u.username", param.getUsername());
        }
        if(StringUtils.isNotEmpty(param.getUserssid())){
            ew.eq("u.ssid", param.getUserssid());
        }
        if(StringUtils.isNotEmpty(param.getTypeid()) && !"0".equals(param.getTypeid())){
            ew.eq("t.ssid", param.getTypeid());
        }
        if (StringUtils.isNoneBlank(param.getContent()) && !"0".equals(param.getContent())) {
            ew.like("f.content", param.getContent());
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
                ew.eq("f.cityzhongid", cityzhong.getId());
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

        if (null != param.getState()) {
            ew.eq("f.state", param.getState());
        }else{
            ew.ne("f.state", 2);
        }
        if (StringUtils.isNoneBlank(param.getTypeid()) && !"0".equalsIgnoreCase(param.getTypeid())) {
            ew.eq("f.typeid", param.getTypeid());
        }

//        ew.orderByDesc("f.topnum");
        //先排置顶到期时间，再按照创建时间排序
        ew.orderByDesc("f.topendtime");//一定要定时把时间去掉设置成同一时间
        ew.orderByDesc("f.sortnum");
        ew.orderByDesc("f.createtime");

        Integer count = friendsMapper.selectFriendsCount(ew);
        param.setRecordCount(count);

        Page<Friends> page = new Page<>(param.getCurrPage(), param.getPageSize());
        page.setTotal(count);
        IPage<Friends> sqCacheList = friendsMapper.getFriendsPage(page, ew);

        friendsVO.setPagelist(sqCacheList.getRecords());
        friendsVO.setPageparam(param);

        result.changeToTrue(friendsVO);
        return result;
    }

    @Override
    public RResult addFriends(RResult result, AddUpdateFriendsParam param) {
        //先校验添加的ssid是否已经存在，在判断添加的参数是否已经存在
        String ssid = OpenUtil.getUUID_32();
        if(StringUtils.isNoneBlank(param.getSsid())){
            UpdateWrapper<Friends> ew = new UpdateWrapper<>();
            ew.eq("ssid", param.getSsid());
            List<Friends> friends = friendsMapper.selectList(ew);
            if(null != friends && friends.size() > 0){
                result.setMessage("该ssid已经存在，不能添加");
                return result;
            }
            ssid = param.getSsid();
        }

        if(param.getTopnum() == 0 && null != param.getTopendtime()){
            result.setMessage("不置顶的时候，不能修改置顶时间！");
            return result;
        }

        UpdateWrapper<User> uew = new UpdateWrapper<User>();
        uew.eq("username", param.getUsername());
        List<User> users = userMapper.selectList(uew);
        if(users.size() == 0){
            result.setMessage("没找到发布表的用户，不能朋友圈");
            return result;
        }

        Friends friends = new Friends();

        List<String> cityList = param.getCityList();
        if(null != cityList && cityList.size() == 3){
            UpdateWrapper<Cityzhong> zew = new UpdateWrapper<>();
            zew.eq("provinceid", cityList.get(0));
            zew.eq("cityid", cityList.get(1));
            zew.eq("areaid", cityList.get(2));
            List<Cityzhong> cityzhongs = cityzhongMapper.selectList(zew);
            if(cityzhongs.size() > 0){
                Cityzhong cityzhong = cityzhongs.get(0);
                friends.setCityzhongid(cityzhong.getId() + "");
            }else{
                result.setMessage("城市没找到，无法处理");
                return result;
            }
        }

        friends.setContent(param.getContent());
        friends.setUserid(users.get(0).getSsid());
        friends.setFmimglist(param.getFmimglist().toString().trim());
        friends.setSsid(ssid);
        friends.setPrice(param.getPrice());
        friends.setTypeid(param.getTypeid());
        friends.setAddress(param.getAddress());
        friends.setTopnum(param.getTopnum());
        friends.setTopendtime(param.getTopendtime());
        friends.setStarttime(param.getStarttime());
        friends.setEndtime(param.getEndtime());
        friends.setSortnum(param.getSortnum());
        friends.setState(param.getState());
        int insert = friendsMapper.insert(friends);
        if (insert > 0) {
            result.changeToTrue(insert);
        }
        return result;
    }

    @Override
    public RResult updateFriends(RResult result, AddUpdateFriendsParam param) {

        if(param.getTopnum() == 0 && null != param.getTopendtime()){
            result.setMessage("不置顶的时候，不能修改置顶时间！");
            return result;
        }

        UpdateWrapper<Friends> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());

        UpdateWrapper<User> uew = new UpdateWrapper<User>();
        uew.eq("username", param.getUsername());
        List<User> users = userMapper.selectList(uew);
        if(users.size() == 0){
            result.setMessage("没找到发布表的用户，不能朋友圈");
            return result;
        }

        Friends friends = new Friends();

        List<String> cityList = param.getCityList();
        if(null != cityList && cityList.size() == 3){
            UpdateWrapper<Cityzhong> zew = new UpdateWrapper<>();
            zew.eq("provinceid", cityList.get(0));
            zew.eq("cityid", cityList.get(1));
            zew.eq("areaid", cityList.get(2));
            List<Cityzhong> cityzhongs = cityzhongMapper.selectList(zew);
            if(cityzhongs.size() > 0){
                Cityzhong cityzhong = cityzhongs.get(0);
                friends.setCityzhongid(cityzhong.getId() + "");
            }else{
                result.setMessage("城市没找到，无法处理");
                return result;
            }
        }

        friends.setContent(param.getContent());
        friends.setUserid(users.get(0).getSsid());
        friends.setFmimglist(param.getFmimglist().toString().trim());
        friends.setPrice(param.getPrice());
        friends.setTypeid(param.getTypeid());
        friends.setAddress(param.getAddress());
        friends.setTopnum(param.getTopnum());
        friends.setTopendtime(param.getTopendtime());
        friends.setStarttime(param.getStarttime());
        friends.setEndtime(param.getEndtime());
        friends.setSortnum(param.getSortnum());
        friends.setState(param.getState());

        int update = friendsMapper.update(friends, ew);
        if (update > 0) {
            result.changeToTrue(update);
            result.setMessage("修改成功！");
        }

        LogUtil.intoLog("用户：修改了数据！" + users.get(0).getUsername());

        return result;
    }

    @Override
    public RResult deleteFriends(RResult result, DeleteFriendsParam param) {
        UpdateWrapper<Friends> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());

        Friends friends = friendsMapper.selectOne(ew);
        if(null == friends){
            result.setMessage("删除的数据不存在");
            return result;
        }

        Friends friend = new Friends();
        friend.setState(2);

        int delete = friendsMapper.update(friend, ew);
        if (delete > 0) {
            result.changeToTrue(delete);
            result.setMessage("删除成功！");
        }
        LogUtil.intoLog("用户：删除了一条数据！" + friends.getSsid());
        return result;
    }

    @Override
    public RResult gettopfriend(RResult result, DeleteFriendsParam param) {
        UpdateWrapper<Friends> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());

        Friends friend = new Friends();
        friend.setTopnum(param.getTopnum());

        int update = friendsMapper.update(friend, ew);
        if (update > 0) {
            result.changeToTrue(update);
            result.setMessage("修改置顶成功！");
        }

        LogUtil.intoLog("置顶朋友圈：" + param.getSsid());
        return result;
    }
}