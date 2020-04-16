package com.jiehuihui.admin.req;

import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import lombok.Data;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * (Permission)表添加删除参数类
 *
 * @author zhuang
 * @since 2020-04-12 20:58:38
 */

@Data
public class AddUpdatePermissionParam {

    @Min(value = 0,message = "id不能为空", groups = {Update.class})
    private Long id;
    @NotBlank(message = "父级权限不能为空", groups = {Create.class, Update.class}) 
    private String permissionsuper; //父级权限 
    @NotBlank(message = "权限名称不能为空", groups = {Create.class, Update.class}) 
    private String permissionname; //权限名称
    @Min(value = 0,message = "权限类型不能为空", groups = {Create.class, Update.class}) 
    private Integer permissionnametype; //权限类型 
    @NotBlank(message = "标识不能为空", groups = {Create.class, Update.class}) 
    private String permissionnamezy; //标识 
    @Min(value = 0,message = "排序不能为空", groups = {Create.class, Update.class}) 
    private Integer sortnum; //排序 
    @Min(value = 0,message = "状态不能为空", groups = {Create.class, Update.class}) 
    private Integer state; //状态 

}