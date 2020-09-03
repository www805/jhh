package com.jiehuihui.admin.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class AddUpdateSpecialParam {

    /**
     * 特价标题
     */
    @NotBlank(message = "特价标题不能为空", groups = {Create.class, Update.class})
    private String specialtitle="";

    @NotBlank(message = "关联店铺名不能为空", groups = {Create.class, Update.class})
    private String shopname="";
    /**
     * 封面图片集合
     */
    @NotNull(message = "封面图片不能为空", groups = {Create.class, Update.class})
    private List<String> fmimglist=new ArrayList<>();
    /**
     * 图片上标签文字
     */
    private String imgtag="";
    /**
     * 标题下方描述
     */
    private String titledescribe="";
    /**
     * 原价
     */
    private Double oldprice = 0d;
    /**
     * 当前价钱
     */
    private Double nowprice = 0d;
    /**
     * 下单指南json形式
     */
    private String ordertext="";
    /**
     * 活动介绍
     */
    private String hddescribe="";
    /**
     * 活动图片集合
     */
    @NotNull(message = "活动图片不能为空", groups = {Create.class, Update.class})
    private List<String> hdimglist=new ArrayList<>();
    /**
     * 任务说明
     */
    private String tasktext="";
    /**
     * 任务介绍
     */
    private String taskdescribe="";
    /**
     * 任务图片集合
     */
    @NotNull(message = "任务图片不能为空", groups = {Create.class, Update.class})
    private List<String> taskimglist=new ArrayList<>();
    /**
     * 优惠标签集
     */
    private String dynamictags="";
    /**
     * 进店描述标签
     */
    private String gototag="";
    /**
     * 点赞数量
     */
    private Long likesize=0L;
    /**
     * 置顶
     */
    private Integer topnum=0;
    /**
     * 首页置顶
     */
    private Integer hometopnum=0;
    /**
     * 置顶时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date topendtime;
    /**
     * 首页置顶时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date hometoptime;
    /**
     * 类型关联Id
     */
    private String specialtypessid="";

    private String shopid="";

    @Min(value = 0,message = "排序不能为空，且必须为正数字", groups = {Create.class, Update.class})
    private Integer sortnum=0;
    @NotBlank(message = "唯一id不能为空", groups = { Update.class})
    private String ssid="";
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private String settime="";
    @Max(value = 2,message = "状态 0禁用 1正常不能为空", groups = {Create.class, Update.class})
    private Integer state=1;

    @NotNull(message = "城市地区不能为空", groups = {Create.class, Update.class})
    @Size(min = 3,max = 3,message = "城市地区长度错误", groups = {Create.class, Update.class})
    private List<String> cityList=new ArrayList<>();//城市地区

}
