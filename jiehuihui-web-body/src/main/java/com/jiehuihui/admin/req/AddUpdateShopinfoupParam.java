package com.jiehuihui.admin.req;

import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;

/**
 * (Shopinfoup)表添加删除参数类
 *
 * @author zhuang
 * @since 2020-05-05 20:57:00
 */

@Data
public class AddUpdateShopinfoupParam {

    
    
    @NotBlank(message = "商家名称不能为空", groups = {Create.class, Update.class}) 
    private String shopname; //商家名称 
    @NotBlank(message = "用户关联名称不能为空", groups = {Create.class, Update.class}) 
    private String username; //用户关联名称 
    @NotBlank(message = "详细地址不能为空", groups = {Create.class, Update.class}) 
    private String address; //详细地址 
    @NotBlank(message = "经营描述不能为空", groups = {Create.class, Update.class}) 
    private String dpdescribe; //经营描述
    @NotBlank(message = "联系电话不能为空", groups = {Create.class, Update.class}) 
    private String phone; //联系电话 

    @NotBlank(message = "店铺类型关联id不能为空", groups = {Create.class, Update.class}) 
    private String shoptypessid; //店铺类型关联id
    @Min(value = 0,message = "状态 0禁用 1正常 2删除不能为空", groups = {Create.class, Update.class}) 
    private Integer state; //状态 0禁用 1正常 2删除 
    @NotBlank(message = "唯一id不能为空", groups = { Update.class}) 
    private String ssid; //唯一id 

    @NotNull(message = "必须上传身份证", groups = {Create.class, Update.class})
    private List<String> sfzimg; //身份证图片地址集合
    @NotNull(message = "必须上传营业执照", groups = {Create.class, Update.class})
    private List<String> yyzzimg; //营业执照图片地址集合

    @NotNull(message = "城市地区不能为空", groups = {Create.class, Update.class})
    @Size(min = 3,max = 3,message = "城市地区长度错误", groups = {Create.class, Update.class})
    private List<String> cityList;//城市地区
}