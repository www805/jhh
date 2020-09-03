package com.jiehuihui.web.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GetAreaidParam {

    @NotBlank(message = "城市不能为空")
    private String cityid;

}
