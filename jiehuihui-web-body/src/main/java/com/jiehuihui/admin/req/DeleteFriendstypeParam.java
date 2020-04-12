package com.jiehuihui.admin.req;

import lombok.Data;
import javax.validation.constraints.NotBlank;

/**
 * (Friendstype)表删除参数接收类
 *
 * @author zhuang
 * @since 2020-04-11 23:09:49
 */

@Data
public class DeleteFriendstypeParam {

    @NotBlank(message = "ssid不能为空")
    private String ssid; //唯一ID

}