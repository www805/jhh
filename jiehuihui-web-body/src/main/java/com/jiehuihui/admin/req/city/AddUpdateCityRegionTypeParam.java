package com.jiehuihui.admin.req.city;

import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class AddUpdateCityRegionTypeParam {


    @NotBlank(message = "城市类型名称不能为空", groups = {Create.class, Update.class})
    private String typename;

    @Min(value = 0,message = "排序不能为空，且必须为正数字", groups = {Create.class, Update.class})
    private Integer sortnum;

    @NotBlank(message = "ssid不能为空", groups = {Update.class})
    private String ssid;

}
