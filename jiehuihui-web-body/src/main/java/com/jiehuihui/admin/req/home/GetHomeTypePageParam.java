package com.jiehuihui.admin.req.home;

import com.jiehuihui.common.utils.Page;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Data
public class GetHomeTypePageParam extends Page {

    private String keyword; //公告关键字
    private String cityid; //城市ssid
    private Integer num; //排序

}
