package com.jiehuihui.admin.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UseCardParam {

    private String tupupssid;//充值人ssid
    @NotBlank(message = "使用人ssid不能为空")
    private String username;//充值人账号名称
    @NotBlank(message = "卡号码不能为空")
    private String cardNum;//卡号码

}
