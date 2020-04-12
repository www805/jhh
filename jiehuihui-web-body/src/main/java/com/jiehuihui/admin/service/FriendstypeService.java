package com.jiehuihui.admin.service;

import com.jiehuihui.admin.req.AddUpdateFriendstypeParam;
import com.jiehuihui.admin.req.DeleteFriendstypeParam;
import com.jiehuihui.admin.req.GetFriendstypePageParam;
import com.jiehuihui.common.utils.RResult;

/**
 * (Friendstype)表服务接口
 *
 * @author zhuang
 * @since 2020-04-11 23:09:49
 */
public interface FriendstypeService {

    //获取朋友圈类型
    RResult getFriendstype(RResult result);

    //获取一条朋友圈类型
    RResult getFriendstypeByssid(RResult result, DeleteFriendstypeParam param);

    //获取朋友圈类型，分页
    RResult getFriendstypePage(RResult result, GetFriendstypePageParam param);

    //添加朋友圈类型
    RResult addFriendstype(RResult result, AddUpdateFriendstypeParam param);

    //修改朋友圈类型
    RResult updateFriendstype(RResult result, AddUpdateFriendstypeParam param);

    //删除朋友圈类型
    RResult deleteFriendstype(RResult result, DeleteFriendstypeParam param);

}