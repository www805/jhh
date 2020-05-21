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
 * (Blinddateinfo)表添加删除参数类
 *
 * @author zhuang
 * @since 2020-05-03 22:48:58
 */

@Data
public class AddUpdateBlinddateinfoParam {

    
    
    @NotBlank(message = "发表用户名不能为空", groups = {Create.class, Update.class})
    private String username; //名字
    @NotBlank(message = "相亲人名字不能为空", groups = {Create.class, Update.class})
    private String myname; //相亲人名字名字
    @NotBlank(message = "基本信息不能为空", groups = {Create.class, Update.class})
    private String tabs; //基本信息|分割
    @NotBlank(message = "择偶条件描述不能为空", groups = {Create.class, Update.class}) 
    private String xqdescribe; //择偶条件描述
    @Min(value = 0,message = "性别不能为空", groups = {Create.class, Update.class})
    private Integer sex; //性别
    private String phone;//相亲手机
    private String wxnum;//相亲微信
    @NotBlank(message = "红娘用户名不能为空", groups = {Create.class, Update.class})
    private String matchmake; //红娘关联名字
    private String userid; //发表用户关联id

    private String tximg; //发表用户关联id
    private List<String> fmimglist; //图片集合
    @NotNull(message = "城市地区不能为空", groups = {Create.class, Update.class})
    @Size(min = 3,max = 3,message = "城市地区长度错误", groups = {Create.class, Update.class})
    private List<String> cityList;//城市地区

    private Integer topnum; //置顶
    @Min(value = 0,message = "排序不能为空", groups = {Create.class, Update.class}) 
    private Integer sortnum; //排序 

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date topendtime; //置顶到期时间
    @Min(value = 0,message = "状态删除不能为空", groups = {Create.class, Update.class})
    private Integer state; //状态 0禁用 1正常 2删除 
    @NotBlank(message = "唯一id不能为空", groups = { Update.class}) 
    private String ssid; //唯一id 
    

}