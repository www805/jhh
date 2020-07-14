package com.jiehuihui.admin.service.impl;

import com.jiehuihui.common.entity.Friendstype;
import com.jiehuihui.admin.mapper.FriendstypeMapper;
import com.jiehuihui.admin.service.FriendstypeService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiehuihui.admin.req.AddUpdateFriendstypeParam;
import com.jiehuihui.admin.req.DeleteFriendstypeParam;
import com.jiehuihui.admin.req.GetFriendstypePageParam;
import com.jiehuihui.admin.vo.GetFriendstypeVO;
import com.jiehuihui.common.entity.home.HomeType;
import com.jiehuihui.common.utils.LogUtil;
import com.jiehuihui.common.utils.OpenUtil;
import com.jiehuihui.common.utils.RResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Friendstype)表服务实现类
 *
 * @author zhuang
 * @since 2020-04-11 23:09:49
 */
@Service("friendstypeService")
public class FriendstypeServiceImpl implements FriendstypeService {
    @Resource
    private FriendstypeMapper friendstypeMapper;

    @Override
    public RResult getFriendstype(RResult result) {
        UpdateWrapper<Friendstype> ew = new UpdateWrapper<>();
        ew.orderByDesc("sortnum");
        List<Friendstype> friendstypes = friendstypeMapper.selectList(ew);
        result.changeToTrue(friendstypes);
        return result;
    }

    @Override
    public RResult getFriendstypeByssid(RResult result, DeleteFriendstypeParam param) {
        UpdateWrapper<Friendstype> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());
        List<Friendstype> Friendstypes = friendstypeMapper.selectList(ew);
        if (null != Friendstypes && Friendstypes.size() > 0) {
            Friendstype friendstype = Friendstypes.get(0);
            result.changeToTrue(friendstype);
        }else{
            result.setMessage("获取失败，该条数据不存在");
        }
        return result;
    }

    @Override
    public RResult getFriendstypePage(RResult result, GetFriendstypePageParam param) {

        GetFriendstypeVO friendstypeVO = new GetFriendstypeVO();

        UpdateWrapper<Friendstype> ew = new UpdateWrapper<>();
        if(StringUtils.isNotEmpty(param.getTypename())){
            ew.like("typename", param.getTypename());
        }

        ew.orderByDesc("sortnum");

        Integer count = friendstypeMapper.selectCount(ew);
        param.setRecordCount(count);

        Page<Friendstype> page = new Page<>(param.getCurrPage(), param.getPageSize());
        page.setTotal(count);
        IPage<Friendstype> sqCacheList = friendstypeMapper.selectPage(page, ew);

        friendstypeVO.setPagelist(sqCacheList.getRecords());
        friendstypeVO.setPageparam(param);

        result.changeToTrue(friendstypeVO);
        return result;
    }

    @Override
    public RResult addFriendstype(RResult result, AddUpdateFriendstypeParam param) {
        //先校验添加的ssid是否已经存在，在判断添加的参数是否已经存在
        String ssid = OpenUtil.getUUID_32();
        if(StringUtils.isNoneBlank(param.getSsid())){
            UpdateWrapper<Friendstype> ew = new UpdateWrapper<>();
            ew.eq("ssid", param.getSsid());
            if(null != friendstypeMapper.selectList(ew) && friendstypeMapper.selectList(ew).size() > 0){
                result.setMessage("该ssid已经存在，不能添加");
                return result;
            }
            ssid = param.getSsid();
        }
        UpdateWrapper<Friendstype> ew = new UpdateWrapper<>();
        ew.eq("typename", param.getTypename());
        List<Friendstype> TypeList = friendstypeMapper.selectList(ew);
        if (null != TypeList && TypeList.size() > 0) {
            result.setMessage("该数据已经存在，不能添加");
            return result;
        }

        Friendstype friendstype = new Friendstype();
        friendstype.setTypename(param.getTypename());
        friendstype.setSortnum(param.getSortnum());
        friendstype.setSsid(ssid);
        int insert = friendstypeMapper.insert(friendstype);
        if (insert > 0) {
            result.changeToTrue(insert);
        }
        return result;
    }

    @Override
    public RResult updateFriendstype(RResult result, AddUpdateFriendstypeParam param) {
        //先校验是否已经存在
        UpdateWrapper<Friendstype> ewcheck = new UpdateWrapper<>();
        ewcheck.eq("typename", param.getTypename());
        ewcheck.ne("ssid", param.getSsid());
        List<Friendstype> friendstypes = friendstypeMapper.selectList(ewcheck);
        if (null != friendstypes && friendstypes.size() > 0) {
            result.setMessage("修改的数据已存在，不能修改");
            return result;
        }

        UpdateWrapper<Friendstype> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());

        Friendstype friendstype = new Friendstype();
        friendstype.setTypename(param.getTypename());
        friendstype.setSortnum(param.getSortnum());

        int update = friendstypeMapper.update(friendstype, ew);
        if (update > 0) {
            result.changeToTrue(update);
            result.setMessage("修改成功！");
        }

        LogUtil.intoLog("用户：修改了数据！" + param.getTypename());

        return result;
    }

    @Override
    public RResult deleteFriendstype(RResult result, DeleteFriendstypeParam param) {
        UpdateWrapper<Friendstype> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());

        Friendstype friendstype = friendstypeMapper.selectOne(ew);
        if(null == friendstype){
            result.setMessage("删除的数据不存在");
            return result;
        }

        int delete = friendstypeMapper.delete(ew);
        if (delete > 0) {
            result.changeToTrue(delete);
            result.setMessage("删除成功！");
        }
        LogUtil.intoLog("用户：删除了一条数据！" + friendstype.getTypename());
        return result;
    }
}