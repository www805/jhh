package com.jiehuihui.web.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginParam {

    @NotBlank(message = "账号不能为空")
    private String userlogin;
    @NotBlank(message = "密码不能为空")
    private String password;


}
