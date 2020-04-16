package com.jiehuihui.admin.service.impl;

import com.jiehuihui.admin.entity.Role;
import com.jiehuihui.admin.mapper.RoleMapper;
import com.jiehuihui.admin.service.RoleService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiehuihui.admin.req.AddUpdateRoleParam;
import com.jiehuihui.admin.req.DeleteRoleParam;
import com.jiehuihui.admin.req.GetRolePageParam;
import com.jiehuihui.admin.vo.GetRoleVO;
import com.jiehuihui.common.utils.LogUtil;
import com.jiehuihui.common.utils.OpenUtil;
import com.jiehuihui.common.utils.RResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Override
    public RResult getRole(RResult result) {
        UpdateWrapper<Role> ew = new UpdateWrapper<>();
        ew.eq("state", 1);
        ew.orderByDesc("sortnum");
        List<Role> roles = roleMapper.selectList(ew);
        result.changeToTrue(roles);
        return result;
    }

    @Override
    public RResult getRoleByssid(RResult result, DeleteRoleParam param) {
        UpdateWrapper<Role> ew = new UpdateWrapper();
        ew.eq("id", param.getId());
        Role role = roleMapper.selectList(ew).get(0);
        if (null != role) {
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
            ew.like("r.rolename", param.getRolename());
        }

        ew.orderByDesc("r.createtime");

        Integer count = roleMapper.selectRoleCount(ew);
        param.setRecordCount(count);

        Page<Role> page = new Page<>(param.getCurrPage(), param.getPageSize());
        page.setTotal(count);
        IPage<Role> sqCacheList = roleMapper.getRolePage(page, ew);

        roleVO.setPagelist(sqCacheList.getRecords());
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
            result.changeToTrue(update);
            result.setMessage("修改成功！");
        }

        LogUtil.intoLog("用户：修改了数据！" + param.getRolename());

        return result;
    }

    @Override
    public RResult deleteRole(RResult result, DeleteRoleParam param) {
        UpdateWrapper<Role> ew = new UpdateWrapper();
        ew.eq("id", param.getId());

        Role role = roleMapper.selectOne(ew);
        if(null == role){
            result.setMessage("删除的数据不存在");
            return result;
        }

        int delete = roleMapper.delete(ew);
        if (delete > 0) {
            result.changeToTrue(delete);
            result.setMessage("删除成功！");
        }
        LogUtil.intoLog("用户：删除了一条数据！" + role.getRolename());
        return result;
    }
}