package com.jiehuihui.web.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GetShopFriendInfoParam {

    @NotBlank(message = "用户不能为空")
    private String userssid; //唯一ID

    @NotBlank(message = "类型不能为空")
    private String typeid; //唯一ID

    private Integer pageSize;//显示条数
    private Integer currPage;//当前页
}
