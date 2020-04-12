package com.jiehuihui.admin.req.city;

import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class UpdateAddCityParam {

    @NotBlank(message = "id不能为空", groups = {Update.class})
    private String id;

    /**
     * 父级区域
     */
    @NotBlank(message = "父级区域不能为空", groups = {Create.class})
    private String cityregionsuper;
    /**
     * 城市区域名称
     */
    @NotBlank(message = "城市区域名称不能为空", groups = {Create.class, Update.class})
    private String cityregionname;
    /**
     * 类型
     */
    @Min(value = 1,message = "类型必须为数字", groups = {Create.class, Update.class})
    private Integer cityregiontype;
    /**
     * 排序
     */
    @Min(value = 0,message = "排序必须为0-3位数字", groups = {Create.class, Update.class})
    private Integer sortnum;
    /**
     * 状态
     */
    @Min(value = 0,message = "状态不能为空", groups = {Create.class, Update.class})
    private Integer state;

}
