package com.jiehuihui.common.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jiehuihui.common.entity.shop.Shopinfo;
import com.jiehuihui.common.entity.shop.Shoptype;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * (JhhBaseHomespecial)实体类
 *
 * @author zhuang
 * @since 2020-07-09 18:29:15
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@TableName("jhh_base_homespecial")
public class Homespecial implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 401975492567758536L;
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 特价标题
     */
    private String specialtitle;
    /**
     * 关联店铺名
     */
    private String shopname;
    /**
     * 封面图片集合
     */
    private String fmimglist;
    /**
     * 图片上标签文字
     */
    private String imgtag;
    /**
     * 标题下方描述
     */
    private String titledescribe;
    /**
     * 原价
     */
    private Double oldprice;
    /**
     * 当前价钱
     */
    private Double nowprice;
    /**
     * 下单指南json形式
     */
    private String ordertext;
    /**
     * 活动介绍
     */
    private String hddescribe;
    /**
     * 活动图片集合
     */
    private String hdimglist;
    /**
     * 任务说明
     */
    private String tasktext;
    /**
     * 任务介绍
     */
    private String taskdescribe;
    /**
     * 任务图片集合
     */
    private String taskimglist;
    /**
     * 优惠标签集
     */
    private String dynamictags;
    /**
     * 进店描述标签
     */
    private String gototag;
    /**
     * 点赞数量
     */
    private Long likesize;
    /**
     * 置顶
     */
    private Integer topnum;
    /**
     * 首页置顶
     */
    private Integer hometopnum;
    /**
     * 特价类型关联id
     */
    private String specialtypessid;
    /**
     * 店铺关联id
     */
    private String shopid;
    /**
     * 关联城市ssid
     */
    private String cityzhongid;

    /**
     * 排序
     */
    private Integer sortnum;
    /**
     * 唯一id
     */
    private String ssid;
    /**
     * 设置优惠时间
     */
    private String settime;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createtime;
    /**
     * 状态 0禁用 1正常 2删除
     */
    private Integer state;


    @TableField(exist = false)
    private Shopinfo shopinfo;//关联的发表用户

    @TableField(exist = false)
    private Shoptype shoptype;//店铺类型

    @TableField(exist = false)
    private ProvinceCityArea city;//关联的城市

}