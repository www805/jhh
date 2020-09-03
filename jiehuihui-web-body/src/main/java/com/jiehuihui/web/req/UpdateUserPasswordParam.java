package com.jiehuihui.web.req;

import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateUserPasswordParam {

    @NotBlank(message = "账号ssid不能为空")
    private String userssid;
    @NotBlank(message = "账号不能为空")
    private String userlogin;
    @NotBlank(message = "旧密码不能为空")
    private String oldpassword;
    @NotBlank(message = "新密码不能为空")
    private String newpassword;
    @NotBlank(message = "确认密码不能为空")
    private String newpassword2;

}
