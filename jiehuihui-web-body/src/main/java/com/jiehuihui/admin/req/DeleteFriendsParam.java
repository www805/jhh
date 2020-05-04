package com.jiehuihui.admin.req;

import lombok.Data;
import javax.validation.constraints.NotBlank;

/**
 * (Friends)表删除参数接收类
 *
 * @author zhuang
 * @since 2020-05-02 14:39:37
 */

@Data
public class DeleteFriendsParam {

    @NotBlank(message = "ssid不能为空")
    private String ssid; //唯一ID

    private Integer topnum = 0; //大于0都是置顶

}