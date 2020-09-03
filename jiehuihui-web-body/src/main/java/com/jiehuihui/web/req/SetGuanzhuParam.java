package com.jiehuihui.web.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SetGuanzhuParam {

    @NotBlank(message = "ssid不能为空")
    private String ssid; //店铺唯一ID

    @NotBlank(message = "用户不能为空")
    private String userssid; //店铺唯一ID

    @NotBlank(message = "状态不能为空")
    private Integer state; //关注1，不关注0 状态

}
