package com.jiehuihui.admin.service.impl;

import com.jiehuihui.admin.entity.Permission;
import com.jiehuihui.admin.mapper.PermissionMapper;
import com.jiehuihui.admin.service.PermissionService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiehuihui.admin.req.AddUpdatePermissionParam;
import com.jiehuihui.admin.req.DeletePermissionParam;
import com.jiehuihui.admin.req.GetPermissionPageParam;
import com.jiehuihui.admin.vo.GetPermissionVO;
import com.jiehuihui.common.utils.LogUtil;
import com.jiehuihui.common.utils.OpenUtil;
import com.jiehuihui.common.utils.RResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Permission)表服务实现类
 *
 * @author zhuang
 * @since 2020-04-12 20:58:38
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public RResult getPermission(RResult result) {
        UpdateWrapper<Permission> ew = new UpdateWrapper<>();
        ew.eq("p.state", 1);
        ew.eq("p2.state", 1);
        ew.eq("p3.state", 1);
        ew.orderByDesc("p.sortnum");
        ew.orderByDesc("p2.sortnum");
        ew.orderByDesc("p3.sortnum");
        List<Permission> permissions = permissionMapper.getPermissionList(ew);
        result.changeToTrue(permissions);
        return result;
    }

    @Override
    public RResult getPermissionByssid(RResult result, DeletePermissionParam param) {
        UpdateWrapper<Permission> ew = new UpdateWrapper();
        ew.eq("id", param.getId());
        Permission permission = permissionMapper.selectList(ew).get(0);
        if (null != permission) {
            result.changeToTrue(permission);
        }else{
            result.setMessage("获取失败，该条数据不存在");
        }
        return result;
    }

    @Override
    public RResult getPermissionPage(RResult result, GetPermissionPageParam param) {

        GetPermissionVO permissionVO = new GetPermissionVO();

        UpdateWrapper<Permission> ew = new UpdateWrapper<>();

        ew.orderByDesc("p.sortnum");
        ew.orderByDesc("p2.sortnum");
        ew.orderByDesc("p3.sortnum");

        Integer count = permissionMapper.selectPermissionCount(ew);
        param.setRecordCount(count);

        Page<Permission> page = new Page<>(param.getCurrPage(), param.getPageSize());
        page.setTotal(count);
        IPage<Permission> sqCacheList = permissionMapper.getPermissionPage(page, ew);

        permissionVO.setPagelist(sqCacheList.getRecords());
        permissionVO.setPageparam(param);

        result.changeToTrue(permissionVO);
        return result;
    }

    @Override
    public RResult addPermission(RResult result, AddUpdatePermissionParam param) {
        //先校验添加的ssid是否已经存在，在判断添加的参数是否已经存在
        UpdateWrapper<Permission> ew = new UpdateWrapper<>();
        ew.eq("permissionname", param.getPermissionname());
        List<Permission> TypeList = permissionMapper.selectList(ew);
        if (null != TypeList && TypeList.size() > 0) {
            result.setMessage("该数据已经存在，不能添加");
            return result;
        }

        Permission permission = new Permission();
        permission.setPermissionsuper(param.getPermissionsuper());
        permission.setPermissionname(param.getPermissionname());
        permission.setPermissionnametype(param.getPermissionnametype());
        permission.setPermissionnamezy(param.getPermissionnamezy());
        permission.setSortnum(param.getSortnum());
        permission.setState(param.getState());
        int insert = permissionMapper.insert(permission);
        if (insert > 0) {
            result.changeToTrue(insert);
        }
        return result;
    }

    @Override
    public RResult updatePermission(RResult result, AddUpdatePermissionParam param) {
        //先校验是否已经存在
        UpdateWrapper<Permission> ewcheck = new UpdateWrapper<>();
        ewcheck.eq("permissionname", param.getPermissionname());
        ewcheck.ne("id", param.getId());
        List<Permission> permissions = permissionMapper.selectList(ewcheck);
        if (null != permissions && permissions.size() > 0) {
            result.setMessage("修改的数据已存在，不能修改");
            return result;
        }

        UpdateWrapper<Permission> ew = new UpdateWrapper();
        ew.eq("id", param.getId());

        Permission permission = new Permission();
        permission.setPermissionsuper(param.getPermissionsuper());
        permission.setPermissionname(param.getPermissionname());
        permission.setPermissionnametype(param.getPermissionnametype());
        permission.setPermissionnamezy(param.getPermissionnamezy());
        permission.setSortnum(param.getSortnum());
        permission.setState(param.getState());

        int update = permissionMapper.update(permission, ew);
        if (update > 0) {
            result.changeToTrue(update);
            result.setMessage("修改成功！");
        }

        LogUtil.intoLog("用户：修改了数据！" + param.getPermissionname());

        return result;
    }

    @Override
    public RResult deletePermission(RResult result, DeletePermissionParam param) {
        UpdateWrapper<Permission> ew = new UpdateWrapper();
        ew.eq("id", param.getId());

        Permission permission = permissionMapper.selectOne(ew);
        if(null == permission){
            result.setMessage("删除的数据不存在");
            return result;
        }

        int delete = permissionMapper.delete(ew);
        if (delete > 0) {
            result.changeToTrue(delete);
            result.setMessage("删除成功！");
        }
        LogUtil.intoLog("用户：删除了一条数据！" + permission.getPermissionname());
        return result;
    }
}