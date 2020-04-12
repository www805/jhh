package com.jiehuihui.admin.req.city;

import com.jiehuihui.common.base.check.Update;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DeleteCityRegionTypeParam {

    @NotBlank(message = "ssid不能为空", groups = {Update.class})
    private String ssid;

}
