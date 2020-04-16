package com.jiehuihui.admin.req;

import com.jiehuihui.common.utils.Page;
import lombok.Data;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * (Role)表查询请求参数接收类
 *
 * @author zhuang
 * @since 2020-04-16 21:56:13
 */

@Data
public class GetRolePageParam extends Page {

    
    
    @NotBlank(message = "角色名不能为空") 
    private String rolename; //角色名 
    @NotBlank(message = "用户关联id不能为空") 
    private String personnum; //用户关联id 

    @Max(value = 2,message = "状态0禁用 1正常不能为空") 
    private Integer state; //状态0禁用 1正常 


}