package com.jiehuihui.admin.vo;

import com.jiehuihui.common.entity.Permission;
import com.jiehuihui.admin.req.GetPermissionPageParam;
import java.util.List;
import lombok.Data;

/**
 * (Permission)表返回类
 *
 * @author zhuang
 * @since 2020-04-12 20:58:38
 */

@Data
public class GetPermissionVO {

    private GetPermissionPageParam pageparam;
    private List<Permission> pagelist;

}