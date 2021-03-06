package com.jiehuihui.admin.service.impl;

import com.jiehuihui.admin.mapper.UserMapper;
import com.jiehuihui.admin.mapper.UserToRoleMapper;
import com.jiehuihui.common.entity.*;
import com.jiehuihui.admin.mapper.RoleMapper;
import com.jiehuihui.admin.mapper.RoletopermissionMapper;
import com.jiehuihui.admin.req.AddUpdateRolePermissionParam;
import com.jiehuihui.admin.service.RoleService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiehuihui.admin.req.AddUpdateRoleParam;
import com.jiehuihui.admin.req.DeleteRoleParam;
import com.jiehuihui.admin.req.GetRolePageParam;
import com.jiehuihui.admin.vo.GetRoleVO;
import com.jiehuihui.common.utils.LogUtil;
import com.jiehuihui.common.utils.RResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (Role)表服务实现类
 *
 * @author zhuang
 * @since 2020-04-16 21:56:13
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserToRoleMapper userToRoleMapper;

    @Autowired
    private RoletopermissionMapper roletopermissionMapper;

    @Override
    public RResult getRole(RResult result) {
        UpdateWrapper<Role> ew = new UpdateWrapper<>();
        ew.eq("state", 1);
        List<Role> roles = roleMapper.selectList(ew);
        result.changeToTrue(roles);
        return result;
    }

    @Override
    public RResult getRoleByssid(RResult result, DeleteRoleParam param) {
        UpdateWrapper<Role> ew = new UpdateWrapper();
        ew.eq("id", param.getId());
        List<Role> roles = roleMapper.selectList(ew);
        if (null != roles && roles.size() > 0) {
            Role role = roles.get(0);
            result.changeToTrue(role);
        }else{
            result.setMessage("获取失败，该条数据不存在");
        }
        return result;
    }

    @Override
    public RResult getRolePage(RResult result, GetRolePageParam param) {

        GetRoleVO roleVO = new GetRoleVO();

        UpdateWrapper<Role> ew = new UpdateWrapper<>();
        if(StringUtils.isNotEmpty(param.getRolename())){
            ew.like("rolename", param.getRolename());
        }

        ew.orderByDesc("createtime");

//        Integer count = roleMapper.selectRoleCount(ew);
//        param.setRecordCount(count);

        Page<Role> page = new Page<>(param.getCurrPage(), param.getPageSize());
//        page.setTotal(count);
        IPage<Role> sqCacheList = roleMapper.selectPage(page, ew);
        param.setRecordCount(Integer.parseInt(sqCacheList.getTotal() + ""));

        List<Role> roleList = sqCacheList.getRecords();
        for (Role role : roleList) {
            UpdateWrapper<Role> ewq = new UpdateWrapper<>();
            ewq.eq("rp.roleid", role.getId());
            ewq.eq("p.permissionnametype", 2);//不是菜单的
            List<Permission> permissions = roleMapper.getRoleToPermission(ewq);
            role.setPermissions(permissions);

            UpdateWrapper<Usertorole> urwq = new UpdateWrapper<>();
            urwq.eq("roleid", role.getId());
            Integer usersize = userToRoleMapper.selectCount(urwq);
            long userCount = Long.parseLong(usersize + "");
            if(role.getUsersize() != userCount){
                role.setUsersize(userCount);
            }

        }

        roleVO.setPagelist(roleList);
        roleVO.setPageparam(param);

        result.changeToTrue(roleVO);
        return result;
    }


    @Override
    public RResult addRole(RResult result, AddUpdateRoleParam param) {
        //先校验添加的ssid是否已经存在，在判断添加的参数是否已经存在
        UpdateWrapper<Role> ew = new UpdateWrapper<>();
        ew.eq("rolename", param.getRolename());
        List<Role> TypeList = roleMapper.selectList(ew);
        if (null != TypeList && TypeList.size() > 0) {
            result.setMessage("该数据已经存在，不能添加");
            return result;
        }

        Role role = new Role();
        role.setRolename(param.getRolename());
        role.setState(param.getState());
        int insert = roleMapper.insert(role);
        if (insert > 0) {
            result.changeToTrue(insert);
        }
        return result;
    }

    @Transactional
    @Override
    public RResult updateRole(RResult result, AddUpdateRoleParam param) {
        //先校验是否已经存在
        UpdateWrapper<Role> ewcheck = new UpdateWrapper<>();
        ewcheck.eq("rolename", param.getRolename());
        ewcheck.ne("id", param.getId());
        List<Role> roles = roleMapper.selectList(ewcheck);
        if (null != roles && roles.size() > 0) {
            result.setMessage("修改的数据已存在，不能修改");
            return result;
        }

        UpdateWrapper<Role> ew = new UpdateWrapper();
        ew.eq("id", param.getId());

        Role role = new Role();
        role.setRolename(param.getRolename());
        role.setState(param.getState());
        int update = roleMapper.update(role, ew);
        if (update > 0) {
            if(param.getState() == 0){
                removeRoleId(param.getId());//执行角色删除操作
            }
            result.changeToTrue(update);
            result.setMessage("修改成功！");
        }

        LogUtil.intoLog("用户：修改了数据！" + param.getRolename());

        return result;
    }

    @Transactional
    @Override
    public RResult deleteRole(RResult result, DeleteRoleParam param) {
        UpdateWrapper<Role> ew = new UpdateWrapper();
        ew.eq("id", param.getId());

        Role role = roleMapper.selectOne(ew);
        if(null == role){
            result.setMessage("删除的数据不存在");
            return result;
        }


        UpdateWrapper<RolePermission> ewq = new UpdateWrapper();
        ewq.eq("roleid", param.getId());
        int del = roletopermissionMapper.delete(ewq);

        int delete = roleMapper.delete(ew);
        if (delete > 0) {

            removeRoleId(param.getId());//执行角色删除操作

            result.changeToTrue(delete);
            result.setMessage("删除成功！");
        }
        LogUtil.intoLog("用户：删除了一条数据！" + role.getRolename());
        return result;
    }

    @Transactional
    @Override
    public RResult addRolePermission(RResult result, AddUpdateRolePermissionParam param) {
        UpdateWrapper<RolePermission> ew = new UpdateWrapper();
        ew.eq("roleid", param.getId());

        RolePermission rolePermission = new RolePermission();
        rolePermission.delete(ew);

        if(null != param.getPermissions() && param.getPermissions().size() > 0){
            int i = roletopermissionMapper.addRoletopermission(param.getId(), param.getPermissions());
            result.changeToTrue(i);
        }else{
            result.changeToTrue();
        }

        return result;
    }

    //删除用户里角色成员
    @Transactional
    public void removeRoleId(Long roleId){

        //修改用户里面的角色
        UpdateWrapper<User> uew = new UpdateWrapper<User>();
        uew.like("rolelistid", roleId);
        List<User> users = userMapper.selectList(uew);
        for (User user : users) {
            String rolelistid = user.getRolelistid();
            rolelistid = rolelistid.replace("[", "").replace("]", "").replace(roleId + "", "");
            if(StringUtils.isNoneBlank(rolelistid)){
                String[] roles = rolelistid.split(",");
                List<String> rlist = new ArrayList<>();
                for (int k = 0; k < roles.length; k++) {
                    String r = roles[k];
                    if(StringUtils.isNoneBlank(r)){
                        rlist.add(r);
                    }
                }
                rolelistid = rlist.toString();
            }
            UpdateWrapper<User> uuew = new UpdateWrapper<>();
            uuew.eq("id", user.getId());
            User u = new User();
            u.setRolelistid(rolelistid.trim());
            int update = userMapper.update(u, uuew);
            if(update > 0){
                UpdateWrapper<Usertorole> ewRole = new UpdateWrapper<>();
                ewRole.eq("userid", user.getId());
                ewRole.eq("roleid", roleId);
                userToRoleMapper.delete(ewRole);
            }
        }

    }
}