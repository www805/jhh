package com.jiehuihui.admin.req.home;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DeleteHomeGgParam {

    @NotBlank(message = "ssid不能为空")
    private String ssid; //唯一ID

}
