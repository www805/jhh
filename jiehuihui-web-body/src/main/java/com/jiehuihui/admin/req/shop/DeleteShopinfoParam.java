package com.jiehuihui.admin.req.shop;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DeleteShopinfoParam {

    @NotBlank(message = "ssid不能为空")
    private String ssid; //唯一ID

}
