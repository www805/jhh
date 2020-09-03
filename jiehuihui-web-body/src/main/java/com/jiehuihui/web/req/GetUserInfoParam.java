package com.jiehuihui.web.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GetUserInfoParam {

    @NotBlank(message = "用户标识不能为空")
    private String ssid;


}
