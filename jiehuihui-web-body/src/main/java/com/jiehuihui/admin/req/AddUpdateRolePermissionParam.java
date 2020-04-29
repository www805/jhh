package com.jiehuihui.admin.req;

import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * (Role)表添加删除参数类
 *
 * @author zhuang
 * @since 2020-04-16 21:56:13
 */

@Data
public class AddUpdateRolePermissionParam {


    @Min(value = 0,message = "id不能为空")
    private Long id;

    private List<Long> permissions; //权限id集合



}