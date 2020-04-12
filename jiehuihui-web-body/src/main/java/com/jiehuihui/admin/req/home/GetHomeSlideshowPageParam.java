package com.jiehuihui.admin.req.home;

import com.jiehuihui.common.utils.Page;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Data
public class GetHomeSlideshowPageParam extends Page {

    @NotBlank(message = "城市ssid不能为空")
    private String cityid; //城市ssid
    @Max(value = 2,message = "排序必须为0-2数字")
    private Integer num; //排序

}
