package com.jiehuihui.admin.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import com.jiehuihui.common.entity.Role;
import com.jiehuihui.common.entity.city.City;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

/**
 * (User)表添加删除参数类
 *
 * @author zhuang
 * @since 2020-04-19 09:54:34
 */

@Data
public class AddUpdateUserParam {


    private Long id; //主键id
    @NotBlank(message = "登录名不能为空", groups = {Create.class, Update.class}) 
    private String userlogin; //登录名 
    @NotBlank(message = "用户名不能为空", groups = {Create.class, Update.class}) 
    private String username; //用户名 
    @NotBlank(message = "真实名字不能为空", groups = {Create.class, Update.class}) 
    private String myname; //真实名字 
    @NotBlank(message = "密码不能为空", groups = {Create.class, Update.class}) 
    private String password; //密码 
    @NotBlank(message = "头像图片地址不能为空", groups = {Create.class, Update.class}) 
    private String tximg; //头像图片地址 
    @NotBlank(message = "手机号码不能为空", groups = {Create.class, Update.class}) 
    private String phone; //手机号码
    private Long sign; //积分
    @Min(value = 0,message = "状态0禁用 1正常 2删除不能为空", groups = {Create.class, Update.class}) 
    private Integer state; //状态0禁用 1正常 2删除 
    @NotBlank(message = "唯一id不能为空", groups = { Update.class}) 
    private String ssid; //唯一id

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date vipendtime; //使用时间

    @NotNull(message = "角色不能为空", groups = {Create.class, Update.class})
    @Size(min = 1,message = "城市地区长度错误", groups = {Create.class, Update.class})
    private List<Role> roleList;//角色数组
    @NotNull(message = "城市地区不能为空", groups = {Create.class, Update.class})
    @Size(min = 3,max = 3,message = "城市地区长度错误", groups = {Create.class, Update.class})
    private List<String> cityList;//城市地区id

//    @NotBlank(message = "请选择关联的城市", groups = {Create.class, Update.class})
    private String cityssid;
}