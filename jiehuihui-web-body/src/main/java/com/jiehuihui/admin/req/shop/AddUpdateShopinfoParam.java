package com.jiehuihui.admin.req.shop;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import com.jiehuihui.common.entity.Shopyhmd;
import com.jiehuihui.common.entity.Zhiding;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Data
public class AddUpdateShopinfoParam {

    @NotBlank(message = "商家名称不能为空", groups = {Create.class, Update.class})
    private String shopname;//商家名称
    @NotBlank(message = "用户关联名称不能为空", groups = {Create.class, Update.class})
    private String username;//用户关联名称

    private String address;//详细地址

    private String dpdescribe;//经营描述

    private String phone;//联系电话

    private String wxnum;//微信号码
    @NotNull(message = "店铺封面不能为空", groups = {Create.class, Update.class})
    private List<String> fmimglist;//店铺封面集合

    private List<String> jgimglist;//价格指南图片集合

    @NotNull(message = "必须上传身份证", groups = {Create.class, Update.class})
    private List<String> sfzimglist;//身份证图片集合
    @NotNull(message = "必须上传营业执照", groups = {Create.class, Update.class})
    private List<String> yyzzimgs;//营业执照图片集合

    private List<String> qtimglist;//其他图片集合

    private String yhtaglist;//优惠标签

    @NotBlank(message = "店铺类型不能为空", groups = {Create.class, Update.class})
    private String shoptypessid;//店铺类型关联id
    @NotNull(message = "城市地区不能为空", groups = {Create.class, Update.class})
    @Size(min = 3,max = 3,message = "城市地区长度错误", groups = {Create.class, Update.class})
    private List<String> cityList;//城市地区

    private Long gzcout;//关注数

    private String yytime;//营业时间

    private Long browsecout;//浏览数量

    private Integer zztop;//街惠惠认证

    private Integer soutop;//搜索置顶

    private Integer hometop;//首页置顶

    @Min(value = 0,message = "排序不能为空，且必须为正数字", groups = {Create.class, Update.class})
    private Integer sortnum;//排序
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date hometopendtime;//置顶到期时间
    @Max(value = 2,message = "状态 0禁用 1正常不能为空", groups = {Create.class, Update.class})
    private Integer state;//状态 0禁用 1正常 2删除
    @NotBlank(message = "唯一id不能为空", groups = { Update.class})
    private String ssid;//唯一id
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createtime;//创建时间

    private Shopyhmd shopyhmd;//关联的免单

}
