package com.jiehuihui.admin.vo;

import com.jiehuihui.common.entity.Friends;
import com.jiehuihui.admin.req.GetFriendsPageParam;
import java.util.List;
import lombok.Data;

/**
 * (Friends)表返回类
 *
 * @author zhuang
 * @since 2020-05-02 14:39:37
 */

@Data
public class GetFriendsVO {

    private GetFriendsPageParam pageparam;
    private List<Friends> pagelist;

}