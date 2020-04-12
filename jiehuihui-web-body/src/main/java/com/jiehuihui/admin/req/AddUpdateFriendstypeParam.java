package com.jiehuihui.admin.req;

import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import lombok.Data;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * (Friendstype)表添加删除参数类
 *
 * @author zhuang
 * @since 2020-04-11 23:09:49
 */

@Data
public class AddUpdateFriendstypeParam {

    
    
    @NotBlank(message = "类型名称不能为空", groups = {Create.class, Update.class}) 
    private String typename; //类型名称 
    @Min(value = 0,message = "排序不能为空", groups = {Create.class, Update.class}) 
    private Integer sortnum; //排序 
    @NotBlank(message = "唯一id不能为空", groups = { Update.class}) 
    private String ssid; //唯一id 

}