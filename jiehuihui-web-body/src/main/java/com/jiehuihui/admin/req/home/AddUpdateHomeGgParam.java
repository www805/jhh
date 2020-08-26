package com.jiehuihui.admin.req.home;

import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class AddUpdateHomeGgParam {


    @NotBlank(message = "公告关键字不能为空", groups = {Create.class, Update.class})
    private String keyword; //公告关键字
    @Min(value = 0,message = "排序必须为数字", groups = {Create.class, Update.class})
    private Integer sortnum; //排序
    @NotBlank(message = "唯一id不能为空", groups = {Update.class})
    private String ssid; //唯一id

    @NotBlank(message = "请选择关联的城市", groups = {Update.class})
    private String cityid;
}
