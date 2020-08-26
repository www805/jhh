package com.jiehuihui.web.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GetXiangqininfoParam {

    private String type; //相亲类型

    @NotBlank(message = "城市ssid不能为空")
    private String cityid; //城市ssid

}
