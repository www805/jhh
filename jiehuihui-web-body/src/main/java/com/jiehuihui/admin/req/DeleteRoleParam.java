package com.jiehuihui.admin.req;

import lombok.Data;
import javax.validation.constraints.NotBlank;

/**
 * (Role)表删除参数接收类
 *
 * @author zhuang
 * @since 2020-04-16 21:56:13
 */

@Data
public class DeleteRoleParam {

    @NotBlank(message = "id不能为空")
    private Long id; //唯一ID

}