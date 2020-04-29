package com.jiehuihui.admin.req;

import com.jiehuihui.common.entity.Role;
import com.jiehuihui.common.utils.Page;
import lombok.Data;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * (User)表查询请求参数接收类
 *
 * @author zhuang
 * @since 2020-04-19 09:54:34
 */

@Data
public class GetUserPageParam extends Page {

    
    
    @NotBlank(message = "登录名不能为空") 
    private String userlogin; //登录名 
    @NotBlank(message = "用户名不能为空") 
    private String username; //用户名 
    @NotBlank(message = "真实名字不能为空") 
    private String myname; //真实名字 
    @NotBlank(message = "密码不能为空") 
    private String password; //密码 
    @NotBlank(message = "头像图片地址不能为空") 
    private String tximg; //头像图片地址 
    @NotBlank(message = "手机号码不能为空") 
    private String phone; //手机号码 
    @Max(value = 2,message = "状态0禁用 1正常 2删除不能为空") 
    private Integer state; //状态0禁用 1正常 2删除 
    @NotBlank(message = "唯一id不能为空") 
    private String ssid; //唯一id 
    @NotBlank(message = "优惠卷关联id不能为空") 
    private String yhjid; //优惠卷关联id 
    @NotBlank(message = "城市中间表关联id不能为空") 
    private String cityzhongid; //城市中间表关联id

    private String roleid; //角色id
    private List<String> cityList;//城市地区id

    @NotBlank(message = "城市ssid不能为空")
    private String cityid; //城市ssid

}