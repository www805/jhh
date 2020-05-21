package com.jiehuihui.admin.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

/**
 * (Friends)表添加删除参数类
 *
 * @author zhuang
 * @since 2020-05-02 14:39:37
 */

@Data
public class AddUpdateFriendsParam {

    @NotBlank(message = "用户名称不能为空", groups = {Create.class, Update.class})
    private String username; //用户关联
    @NotBlank(message = "类型关联id不能为空", groups = {Create.class, Update.class}) 
    private String typeid; //类型关联id 
    @NotBlank(message = "朋友圈文字内容不能为空", groups = {Create.class, Update.class}) 
    private String content; //朋友圈文字内容 
    
    private Double price; //价钱
    private String address; //发表地址

    private Integer topnum; //置顶 
    @Min(value = 0,message = "排序不能为空", groups = {Create.class, Update.class}) 
    private Integer sortnum; //排序 

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date topendtime; //置顶到期时间 
    @Min(value = 0,message = "状态0禁用 1正常2删除不能为空", groups = {Create.class, Update.class}) 
    private Integer state; //状态0禁用 1正常2删除 
    @NotBlank(message = "唯一id不能为空", groups = { Update.class}) 
    private String ssid; //唯一id

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date starttime; //活动开始时间 

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endtime; //活动结束时间 

    @NotNull(message = "城市地区不能为空", groups = {Create.class, Update.class})
    @Size(min = 3,max = 3,message = "城市地区长度错误", groups = {Create.class, Update.class})
    private List<String> cityList;//城市地区
    private List<String> fmimglist;//角色数组
}