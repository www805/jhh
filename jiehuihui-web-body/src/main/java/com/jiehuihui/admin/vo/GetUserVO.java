package com.jiehuihui.admin.vo;

import com.jiehuihui.admin.req.GetUserPageParam;
import com.jiehuihui.common.entity.User;
import lombok.Data;

import java.util.List;

/**
 * (User)表返回类
 *
 * @author zhuang
 * @since 2020-04-19 09:54:34
 */

@Data
public class GetUserVO {

    private GetUserPageParam pageparam;
    private List<User> pagelist;

}