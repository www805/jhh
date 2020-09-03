package com.jiehuihui.web.req;

import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class UpdateUserInfoParam {

    @NotBlank(message = "昵称不能为空")
    private String username;
    @Min(value = 0,message = "性别不能为空", groups = {Create.class, Update.class})
    private Integer sex;
    @NotBlank(message = "账号ssid不能为空")
    private String userssid;
    @NotBlank(message = "头像不能为空")
    private String tximg;

    @NotNull(message = "城市地区不能为空")
    @Size(min = 3,max = 3,message = "城市地区长度错误")
    private List<String> cityList;//城市地区id


}
