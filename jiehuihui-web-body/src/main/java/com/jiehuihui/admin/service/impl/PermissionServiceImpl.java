package com.jiehuihui.admin.service.impl;

import com.jiehuihui.common.entity.Permission;
import com.jiehuihui.admin.mapper.PermissionMapper;
import com.jiehuihui.admin.service.PermissionService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiehuihui.admin.req.AddUpdatePermissionParam;
import com.jiehuihui.admin.req.DeletePermissionParam;
import com.jiehuihui.admin.req.GetPermissionPageParam;
import com.jiehuihui.admin.vo.GetPermissionVO;
import com.jiehuihui.common.entity.PermissionEntity;
import com.jiehuihui.common.utils.LogUtil;
import com.jiehuihui.common.utils.RResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Comparator;
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
        ew.eq("permissionsuper", 0);
        ew.eq("permissionnametype", 1);
        ew.orderByDesc("sortnum");
        List<PermissionEntity> permissions = permissionMapper.getPermissionList(ew);
        findPermissionEntity(permissions);
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
        ew.eq("permissionsuper", 0);
        ew.eq("permissionnametype", 1);

        ew.orderByDesc("sortnum");
//        ew.orderByDesc("p2.sortnum");
//        ew.orderByDesc("p3.sortnum");

        Integer count = permissionMapper.selectCount(ew);
        param.setRecordCount(count);

        Page<Permission> page = new Page<>(param.getCurrPage(), param.getPageSize());
        page.setTotal(count);
        IPage<Permission> sqCacheList = permissionMapper.selectPage(page, ew);

        List<Permission> permissionList = sqCacheList.getRecords();
        findPermission(permissionList);


        permissionVO.setPagelist(permissionList);
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

    @Transactional
    @Override
    public RResult deletePermission(RResult result, DeletePermissionParam param) {
        UpdateWrapper<Permission> ew = new UpdateWrapper();
        ew.eq("id", param.getId());

        Permission permission = permissionMapper.selectOne(ew);
        if(null == permission){
            result.setMessage("删除的数据不存在");
            return result;
        }

        UpdateWrapper<Permission> ew2 = new UpdateWrapper<>();
        ew2.eq("permissionsuper", permission.getId());
        List<Permission> permissions = permission.selectList(ew2);
        deletes(permissions);

        int delete = permissionMapper.delete(ew);
        if (delete > 0) {
            result.changeToTrue(delete);
            result.setMessage("删除成功！");
        }
        LogUtil.intoLog("用户：删除了一条数据！" + permission.getPermissionname());
        return result;
    }


    /**
     * 查所有询子权限
     * @param permissionList
     */
    public void findPermission(List<Permission> permissionList){

        if(permissionList.size() == 0){
            return;
        }

        for (int i = 0; i < permissionList.size(); i++) {
            Permission permission = permissionList.get(i);
            UpdateWrapper<Permission> ew2 = new UpdateWrapper<>();
            ew2.eq("permissionsuper", permission.getId());
            List<Permission> selectList = permission.selectList(ew2);
            if (selectList.size() > 0) {
                Collections.sort(selectList, new Comparator<Permission>() {
                    @Override
                    public int compare(Permission o1, Permission o2) {
                        return o2.getSortnum() - o1.getSortnum();
                    }
                });
                permission.setChildren(selectList);
                findPermission(selectList);
            }
        }
    }

    /**
     * 查所有询子权限
     * @param permissionList
     */
    public void findPermissionEntity(List<PermissionEntity> permissionList){

        if(permissionList.size() == 0){
            return;
        }

        for (int i = 0; i < permissionList.size(); i++) {
            PermissionEntity permission = permissionList.get(i);
            UpdateWrapper<PermissionEntity> ew = new UpdateWrapper<>();
            ew.eq("permissionsuper", permission.getId());
            List<PermissionEntity> selectList = permissionMapper.getPermissionList(ew);
            if (selectList.size() > 0) {
                Collections.sort(selectList, new Comparator<PermissionEntity>() {
                    @Override
                    public int compare(PermissionEntity o1, PermissionEntity o2) {
                        return o2.getSortnum() - o1.getSortnum();
                    }
                });
                permission.setChildren(selectList);
                findPermissionEntity(selectList);
            }
        }
    }

    /**
     * 删除所属全部子权限
     * @param permissionList
     */
    public void deletes(List<Permission> permissionList){

        if(null == permissionList || permissionList.size() == 0){
            return;
        }

        for (int i = 0; i < permissionList.size(); i++) {
            Permission permission = permissionList.get(i);
            UpdateWrapper<Permission> ew2 = new UpdateWrapper<>();
            ew2.eq("permissionsuper", permission.getId());
            List<Permission> selectList = permission.selectList(ew2);
            if (selectList.size() > 0) {
                deletes(selectList);
            }
            permission.deleteById();
        }
    }
}