package com.jiehuihui.admin.req.search;

import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import lombok.Data;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * (Soutype)表添加删除参数类
 *
 * @author zhuang
 * @since 2020-04-11 21:27:45
 */

@Data
public class AddUpdateSoutypeParam {

    
    
    @NotBlank(message = "搜索类型不能为空", groups = {Create.class, Update.class}) 
    private String typename; //搜索类型 
    @Min(value = 0,message = "排序不能为空", groups = {Create.class, Update.class})
    private Integer sortnum; //排序 
    @Max(value = 2,message = "状态不能为空", groups = {Create.class, Update.class})
    private Integer state; //状态 0禁用 1正常 2删除 
    @NotBlank(message = "唯一id不能为空", groups = { Update.class}) 
    private String ssid; //唯一id 

}