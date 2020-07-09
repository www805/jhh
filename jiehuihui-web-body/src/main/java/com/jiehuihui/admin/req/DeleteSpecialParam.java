package com.jiehuihui.admin.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DeleteSpecialParam {

    @NotBlank(message = "ssid不能为空")
    private String ssid; //唯一ID

}
