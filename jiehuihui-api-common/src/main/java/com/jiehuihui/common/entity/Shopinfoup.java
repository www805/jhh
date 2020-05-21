package com.jiehuihui.common.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.jiehuihui.common.entity.shop.Shoptype;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import com.fasterxml.jackson.annotation.JsonFormat;
    
import java.io.Serializable;

/**
 * (Shopinfoup)实体类
 *
 * @author zhuang
 * @since 2020-05-05 20:56:59
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("jhh_shopinfoup")
public class Shopinfoup extends Model<Shopinfoup> implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = -43156565621791154L;

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
    * 身份证图片地址
    */
        
    private String sfzimg;

    /**
    * 营业执照图片地址
    */
        
    private String yyzzimg;

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