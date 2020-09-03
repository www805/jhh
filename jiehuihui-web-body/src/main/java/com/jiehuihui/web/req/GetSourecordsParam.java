package com.jiehuihui.web.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GetSourecordsParam {

    @NotBlank(message = "用户不能为空")
    private String userid;

}
