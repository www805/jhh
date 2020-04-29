package com.jiehuihui.admin.vo;

import com.jiehuihui.common.entity.Role;
import com.jiehuihui.admin.req.GetRolePageParam;
import java.util.List;
import lombok.Data;

/**
 * (Role)表返回类
 *
 * @author zhuang
 * @since 2020-04-16 21:56:13
 */

@Data
public class GetRoleVO {

    private GetRolePageParam pageparam;
    private List<Role> pagelist;

}