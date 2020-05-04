package com.jiehuihui.common.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * (Friends)实体类
 *
 * @author zhuang
 * @since 2020-05-02 14:39:36
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("jhh_friends")
public class Friends extends Model<Friends> implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 993260907850283161L;

    /**
    * 主键
    */
    @TableId(value = "id", type = IdType.AUTO)     
    private Long id;

    /**
     * 用户关联id
     */

    private String userid;

    /**
     * 类型关联id
     */

    private String typeid;

    /**
     * 朋友圈文字内容
     */

    private String content;

    /**
     * 价钱
     */

    private Double price;

    /**
     * 城市中间表id
     */

    private String cityzhongid;

    /**
     * 图片集合
     */

    private String fmimglist;

    /**
     * 发表地址
     */

    private String address;

    /**
    * 置顶
    */
        
    private Integer topnum;

    /**
    * 排序
    */
        
    private Integer sortnum;

    /**
    * 置顶到期时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")     
    private Date topendtime;

    /**
    * 状态0禁用 1正常2删除
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
    * 活动开始时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")     
    private Date starttime;

    /**
    * 活动结束时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")     
    private Date endtime;

    @TableField(exist = false)
    private ProvinceCityArea city; //城市关联实体类

    @TableField(exist = false)
    private User user;//关联的用户

    @TableField(exist = false)
    private Friendstype friendstype;//关联的类型
}