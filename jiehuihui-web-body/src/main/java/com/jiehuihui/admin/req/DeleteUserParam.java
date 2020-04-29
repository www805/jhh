package com.jiehuihui.admin.req;

import lombok.Data;
import javax.validation.constraints.NotBlank;

/**
 * (User)表删除参数接收类
 *
 * @author zhuang
 * @since 2020-04-19 09:54:34
 */

@Data
public class DeleteUserParam {

    @NotBlank(message = "ssid不能为空")
    private String ssid; //唯一ID

}