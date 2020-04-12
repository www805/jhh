package com.jiehuihui.admin.req.shop;

import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import lombok.Data;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * (Shoplabel)表添加删除参数类
 *
 * @author zhuang
 * @since 2020-04-11 12:41:26
 */

@Data
public class AddUpdateShoplabelParam {

    
    
    @NotBlank(message = "标签文字不能为空", groups = {Create.class, Update.class}) 
    private String labelname; //标签文字 
    @Min(value = 0,message = "排序不能为空，且必须为正数字", groups = {Create.class, Update.class})
    private Integer sortnum; //排序 
    @NotBlank(message = "唯一id不能为空", groups = { Update.class}) 
    private String ssid; //唯一id 
    @Max(value = 2,message = "状态 0禁用 1正常不能为空", groups = {Create.class, Update.class}) 
    private Integer state; //状态 0禁用 1正常 

}