package com.jiehuihui.admin.service.impl;

import com.jiehuihui.admin.mapper.UserMapper;
import com.jiehuihui.admin.mapper.UserToRoleMapper;
import com.jiehuihui.admin.mapper.city.CityzhongMapper;
import com.jiehuihui.admin.service.UserService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiehuihui.admin.req.AddUpdateUserParam;
import com.jiehuihui.admin.req.DeleteUserParam;
import com.jiehuihui.admin.req.GetUserPageParam;
import com.jiehuihui.admin.vo.GetUserVO;
import com.jiehuihui.common.entity.Role;
import com.jiehuihui.common.entity.User;
import com.jiehuihui.common.entity.Usertorole;
import com.jiehuihui.common.entity.city.Cityzhong;
import com.jiehuihui.common.utils.LogUtil;
import com.jiehuihui.common.utils.OpenUtil;
import com.jiehuihui.common.utils.RResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author zhuang
 * @since 2020-04-19 09:54:34
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private UserToRoleMapper userToRoleMapper;

    @Autowired
    private CityzhongMapper cityzhongMapper;

    @Override
    public RResult getUserList(RResult result) {
        UpdateWrapper<User> ew = new UpdateWrapper<>();
        ew.eq("state", 1);
        List<User> users = userMapper.selectList(ew);
        result.changeToTrue(users);
        return result;
    }

    @Override
    public RResult getUserByssid(RResult result, DeleteUserParam param) {
        UpdateWrapper<User> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());
        User user = userMapper.selectList(ew).get(0);
        if (null != user) {
            result.changeToTrue(user);
        }else{
            result.setMessage("获取失败，该条数据不存在");
        }
        return result;
    }

    @Override
    public RResult getUserPage(RResult result, GetUserPageParam param) {

        GetUserVO userVO = new GetUserVO();

        UpdateWrapper<User> ew = new UpdateWrapper<>();
        if(StringUtils.isNotEmpty(param.getUsername())){
            ew.like("u.username", param.getUsername());
        }
        if (StringUtils.isNotEmpty(param.getRoleid()) && !"0".equals(param.getRoleid())){
            ew.like("u.rolelistid", param.getRoleid());
        }

        List<String> cityList = param.getCityList();
        if(cityList.size() == 3){
            UpdateWrapper<Cityzhong> zew = new UpdateWrapper<>();
            zew.eq("provinceid", cityList.get(0));
            zew.eq("cityid", cityList.get(1));
            zew.eq("areaid", cityList.get(2));
            List<Cityzhong> cityzhongs = cityzhongMapper.selectList(zew);
            if(cityzhongs.size() > 0){
                Cityzhong cityzhong = cityzhongs.get(0);
                ew.eq("u.cityzhongid", cityzhong.getId());
            }
        }


        ew.orderByDesc("u.createtime");

//        Integer count = userMapper.selectUserCount(ew);


        Page<User> page = new Page<>(param.getCurrPage(), param.getPageSize());
//        page.setTotal(count);
        IPage<User> sqCacheList = userMapper.getUserPage(page, ew);
        param.setRecordCount(Integer.parseInt(sqCacheList.getTotal() + ""));

        userVO.setPagelist(sqCacheList.getRecords());
        userVO.setPageparam(param);

        result.changeToTrue(userVO);
        return result;
    }

    @Transactional
    @Override
    public RResult addUser(RResult result, AddUpdateUserParam param) {
        //先校验添加的ssid是否已经存在，在判断添加的参数是否已经存在
        String ssid = OpenUtil.getUUID_32();
        if(StringUtils.isNoneBlank(param.getSsid())){
            UpdateWrapper<User> ew = new UpdateWrapper<>();
            ew.eq("ssid", param.getSsid());
            if(null != userMapper.selectList(ew)){
                result.setMessage("该ssid已经存在，不能添加");
                return result;
            }
            ssid = param.getSsid();
        }
        UpdateWrapper<User> ew = new UpdateWrapper<>();
        ew.eq("username", param.getUsername());
        List<User> TypeList = userMapper.selectList(ew);
        if (null != TypeList && TypeList.size() > 0) {
            result.setMessage("该数据已经存在，不能添加");
            return result;
        }

        Cityzhong cityzhong = null;
        //处理用户所在城市
        List<String> cityList = param.getCityList();
        if(cityList.size() == 3){
            UpdateWrapper<Cityzhong> wrapper = new UpdateWrapper<>();
            wrapper.eq("provinceid",cityList.get(0));
            wrapper.eq("cityid",cityList.get(1));
            wrapper.eq("areaid",cityList.get(2));
            List<Cityzhong> cityzhongList = cityzhongMapper.selectList(wrapper);
            if (cityzhongList.size() > 0) {
                cityzhong = cityzhongList.get(0);
            }
        }


        User user = new User();
        user.setUserlogin(param.getUserlogin());
        user.setUsername(param.getUsername());
        user.setPassword(param.getPassword());
        user.setMyname(param.getMyname());
        user.setPhone(param.getPhone());
        user.setTximg(param.getTximg());
        user.setVipendtime(param.getVipendtime());
        user.setSsid(ssid);
        user.setState(param.getState());
        if(null != cityzhong){
            user.setCityzhongid(cityzhong.getId() + "");
        }

        int insert = userMapper.insert(user);
        if (insert > 0) {

            //角色往中间表插入数据
            List<Role> roleList = param.getRoleList();
            List<String> addrolelist = new ArrayList<>();
            for (Role role : roleList) {
                Usertorole usertorole = new Usertorole();
                usertorole.setUserid(user.getId() + "");
                usertorole.setRoleid(role.getId() + "");
                boolean insertRole = usertorole.insert();
                if(insertRole && usertorole.getId() > 0){
                    addrolelist.add(role.getId() + "");
                }
            }

            UpdateWrapper<User> uew = new UpdateWrapper<>();
            uew.eq("id", user.getId());
            User adduser = new User();
            adduser.setRolelistid(addrolelist.toString());
            userMapper.update(user, uew);

            result.changeToTrue(insert);
        }
        return result;
    }

    @Transactional
    @Override
    public RResult updateUser(RResult result, AddUpdateUserParam param) {
        //先校验是否已经存在
        UpdateWrapper<User> ewcheck = new UpdateWrapper<>();
        ewcheck.eq("username", param.getUsername());
        ewcheck.ne("ssid", param.getSsid());
        List<User> users = userMapper.selectList(ewcheck);
        if (null != users && users.size() > 0) {
            result.setMessage("修改的数据已存在，不能修改");
            return result;
        }

        Cityzhong cityzhong = null;
        //处理用户所在城市
        List<String> cityList = param.getCityList();
        if(cityList.size() == 3){
            UpdateWrapper<Cityzhong> wrapper = new UpdateWrapper<>();
            wrapper.eq("provinceid",cityList.get(0));
            wrapper.eq("cityid",cityList.get(1));
            wrapper.eq("areaid",cityList.get(2));
            List<Cityzhong> cityzhongList = cityzhongMapper.selectList(wrapper);
            if (cityzhongList.size() > 0) {
                cityzhong = cityzhongList.get(0);
            }
        }

        if(null != param.getVipendtime()){
            //判断是否是修改，如果是修改就查是否本来就会员，是就让他修改
            UpdateWrapper<User> uew = new UpdateWrapper<>();
            uew.eq("ssid", param.getSsid());
            uew.ne("vipendtime", param.getVipendtime());
            Integer ucount = userMapper.selectCount(uew);
            if(ucount > 0){
                UpdateWrapper<Usertorole> ewRolecache = new UpdateWrapper<>();
                ewRolecache.eq("roleid", "1").eq("userid",param.getId());
                ewRolecache.or().eq("roleid", "2").eq("userid",param.getId());
                ewRolecache.or().eq("roleid", "3").eq("userid",param.getId());
                Integer rolecout = userToRoleMapper.selectCount(ewRolecache);
                if(rolecout == 0){
                    result.setMessage("该用户不是会员身份，不能修改vip到期时间");
                    return result;
                }
            }
        }


        UpdateWrapper<User> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());

        //删除所有角色
        UpdateWrapper<Usertorole> ewRole = new UpdateWrapper<>();
        ewRole.eq("userid", param.getId());
        userToRoleMapper.delete(ewRole);
        //再重新添加角色
        List<Role> roleList = param.getRoleList();
        List<String> addrolelist = new ArrayList<>();
        for (Role role : roleList) {
            Usertorole usertorole = new Usertorole();
            usertorole.setUserid(param.getId() + "");
            usertorole.setRoleid(role.getId() + "");
            boolean insert = usertorole.insert();
            if(insert && usertorole.getId() > 0){
                addrolelist.add(role.getId() + "");
            }
        }

        //修改用户
        User user = new User();
        user.setUserlogin(param.getUserlogin());
        user.setUsername(param.getUsername());
        user.setPassword(param.getPassword());
        user.setMyname(param.getMyname());
        user.setPhone(param.getPhone());
        user.setTximg(param.getTximg());
        user.setSign(param.getSign());
        user.setVipendtime(param.getVipendtime());
        user.setState(param.getState());
        user.setRolelistid(addrolelist.toString());

        if(null != cityzhong){
            user.setCityzhongid(cityzhong.getId() + "");
        }

        int update = userMapper.update(user, ew);
        if (update > 0) {
            result.changeToTrue(update);
            result.setMessage("修改成功！");
        }

        LogUtil.intoLog("用户：修改了数据！" + param.getUsername());

        return result;
    }

    @Override
    public RResult deleteUser(RResult result, DeleteUserParam param) {
        UpdateWrapper<User> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());

        User user = userMapper.selectOne(ew);
        if(null == user){
            result.setMessage("删除的数据不存在");
            return result;
        }

        int delete = userMapper.delete(ew);
        if (delete > 0) {
            result.changeToTrue(delete);
            result.setMessage("删除成功！");
        }
        LogUtil.intoLog("用户：删除了一条数据！" + user.getUsername());
        return result;
    }
}