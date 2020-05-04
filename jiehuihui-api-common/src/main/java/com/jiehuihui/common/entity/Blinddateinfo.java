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
 * (Blinddateinfo)实体类
 *
 * @author zhuang
 * @since 2020-05-03 22:48:58
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("jhh_blinddateinfo")
public class Blinddateinfo extends Model<Blinddateinfo> implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 623778407156984648L;

    /**
    * 主键
    */
    @TableId(value = "id", type = IdType.AUTO)     
    private Long id;

    /**
    * 名字
    */
        
    private String myname;

    /**
    * 择偶条件描述
    */
        
    private String xqdescribe;

    /**
    * 基本信息|分割
    */
        
    private String tabs;

    /**
    * 性别
    */
        
    private Integer sex;

    /**
     * 相亲手机
     */

    private String phone;

    /**
     * 相亲微信
     */

    private String wxnum;

    /**
    * 红娘关联id
    */
        
    private String matchmakerid;

    /**
    * 发表用户关联id
    */
        
    private String userid;

    /**
     * 相亲头像
     */

    private String tximg;

    /**
     * 省市区关联中间表
     */

    private String fmimglist;

    /**
     * 相亲图片集合
     */

    private String cityzhongid;

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
    private User matchmakeruser;//关联的红娘

    @TableField(exist = false)
    private User user;//关联的发表用户

    @TableField(exist = false)
    private ProvinceCityArea city;//关联的城市
}