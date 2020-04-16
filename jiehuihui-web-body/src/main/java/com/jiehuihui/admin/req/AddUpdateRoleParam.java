package com.jiehuihui.admin.req;

import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import lombok.Data;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * (Role)表添加删除参数类
 *
 * @author zhuang
 * @since 2020-04-16 21:56:13
 */

@Data
public class AddUpdateRoleParam {


    @Min(value = 0,message = "id不能为空", groups = {Update.class})
    private Long id;
    @NotBlank(message = "角色名不能为空", groups = {Create.class, Update.class}) 
    private String rolename; //角色名
    
    @Min(value = 0,message = "状态0禁用 1正常不能为空", groups = {Create.class, Update.class})
    private Integer state; //状态0禁用 1正常 


}