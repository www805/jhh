package com.jiehuihui.admin.req;

import lombok.Data;
import javax.validation.constraints.NotBlank;

/**
 * (Permission)表删除参数接收类
 *
 * @author zhuang
 * @since 2020-04-12 20:58:38
 */

@Data
public class DeletePermissionParam {

    @NotBlank(message = "id不能为空")
    private Long id; //唯一ID

}