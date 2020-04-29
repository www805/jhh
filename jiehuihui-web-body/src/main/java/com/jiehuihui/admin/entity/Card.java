package com.jiehuihui.admin.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.jiehuihui.common.entity.User;
import com.jiehuihui.common.entity.city.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

                                        
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
            
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
    
import java.io.Serializable;

/**
 * (Card)实体类
 *
 * @author zhuang
 * @since 2020-04-23 23:34:41
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("jhh_base_card")
public class Card extends Model<Card> implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 508579130419320337L;

    /**
    * 主键
    */
    @TableId(value = "id", type = IdType.AUTO)     
    private Long id;

    /**
    * 充值卡号码
    */
        
    private String cardnum;

    /**
    * 1月卡2季卡3年卡
    */
        
    private Integer cardtype;

    /**
     * 1vip商家2vip会员3管理员
     */

    private Integer cardsupertype;

    /**
    * 使用者关联id
    */
        
    private String userid;

    /**
    * 充值人关联id
    */
        
    private String tupupid;

    /**
    * 生成者关联id
    */
        
    private String parentuserid;

    /**
    * 状态 -1删除 0禁用 1未使用 2已被使用
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

    /**
    * 使用时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")     
    private Date employtime;

    @TableField(exist = false)
    private User parentuser; //生成人信息
    @TableField(exist = false)
    private User tupup; //充值者用户信息
    @TableField(exist = false)
    private User user; //使用者用户信息

}