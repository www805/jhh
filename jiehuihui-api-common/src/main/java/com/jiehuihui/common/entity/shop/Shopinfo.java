package com.jiehuihui.common.entity.shop;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jiehuihui.common.entity.ProvinceCityArea;
import com.jiehuihui.common.entity.Shopinfoup;
import com.jiehuihui.common.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * (JhhShopinfo)店铺实体类
 *
 * @author zhuang
 * @since 2020-07-12 16:40:14
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("jhh_shopinfo")
public class Shopinfo extends Model<Shopinfo> implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 844858060190370733L;
    /**
    * 主键
    */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
    * 商家名称
    */
    private String shopname;
    /**
    * 用户关联名称
    */
    private String username;
    /**
    * 详细地址
    */
    private String address;
    /**
    * 经营描述
    */
    private String dpdescribe;
    /**
    * 联系电话
    */
    private String phone;
    /**
    * 微信号码
    */
    private String wxnum;
    /**
    * 店铺封面集合
    */
    private String fmimglist;
    /**
    * 价格指南图片集合
    */
    private String jgimglist;
    /**
    * 身份证图片集合
    */
    private String sfzimglist;
    /**
    * 营业执照图片集合
    */
    private String yyzzimgs;
    /**
    * 其他图片集合
    */
    private String qtimglist;
    /**
    * 优惠标签
    */
    private String yhtaglist;
    /**
     * 用户关联id
     */
    private String userid;
    /**
    * 店铺类型关联id
    */
    private String shoptypessid;
    /**
    * 城市中间表关联id
    */
    private String cityzhongid;
    /**
    * 关注数
    */
    private Long gzcout;
    /**
    * 营业时间
    */
    private String yytime;
    /**
    * 浏览数量
    */
    private Long browsecout;
    /**
    * 街惠惠认证
    */
    private Integer zztop;
    /**
    * 搜索置顶
    */
    private Integer soutop;
    /**
    * 首页置顶
    */
    private Integer hometop;
    /**
    * 排序
    */
    private Integer sortnum;
    /**
    * 置顶到期时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date hometopendtime;
    /**
    * 状态 0禁用 1正常 2删除
    */
    private Integer state;
    /**
    * 唯一id
    */
    private String ssid;
    /**
    * 创建时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createtime;

    @TableField(exist = false)
    private User user;//关联的发表用户

    @TableField(exist = false)
    private Shoptype shoptype;//店铺类型

    @TableField(exist = false)
    private ProvinceCityArea city;//关联的城市
}