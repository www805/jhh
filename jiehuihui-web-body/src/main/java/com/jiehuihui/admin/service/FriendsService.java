package com.jiehuihui.admin.service;

import com.jiehuihui.common.entity.Friends;
import com.jiehuihui.admin.req.AddUpdateFriendsParam;
import com.jiehuihui.admin.req.DeleteFriendsParam;
import com.jiehuihui.admin.req.GetFriendsPageParam;
import com.jiehuihui.common.utils.RResult;

/**
 * (Friends)表服务接口
 *
 * @author zhuang
 * @since 2020-05-02 14:39:37
 */
public interface FriendsService {

    //获取朋友圈
    RResult getFriends(RResult result);

    //获取一条朋友圈
    RResult getFriendsByssid(RResult result, DeleteFriendsParam param);

    //获取朋友圈，分页
    RResult getFriendsPage(RResult result, GetFriendsPageParam param);

    //添加朋友圈
    RResult addFriends(RResult result, AddUpdateFriendsParam param);

    //修改朋友圈
    RResult updateFriends(RResult result, AddUpdateFriendsParam param);

    //删除朋友圈
    RResult deleteFriends(RResult result, DeleteFriendsParam param);

    //置顶朋友圈
    RResult gettopfriend(RResult<Friends> result, DeleteFriendsParam param);
}