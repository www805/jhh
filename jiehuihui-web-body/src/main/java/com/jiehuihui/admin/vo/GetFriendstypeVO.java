package com.jiehuihui.admin.vo;

import com.jiehuihui.common.entity.Friendstype;
import com.jiehuihui.admin.req.GetFriendstypePageParam;
import java.util.List;
import lombok.Data;

/**
 * (Friendstype)表返回类
 *
 * @author zhuang
 * @since 2020-04-11 23:09:49
 */

@Data
public class GetFriendstypeVO {

    private GetFriendstypePageParam pageparam;
    private List<Friendstype> pagelist;

}