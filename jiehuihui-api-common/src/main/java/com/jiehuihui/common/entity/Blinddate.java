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
 * (Blinddate)实体类
 *
 * @author zhuang
 * @since 2020-05-04 18:12:51
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("jhh_blinddate")
public class Blinddate extends Model<Blinddate> implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = -42330332321089980L;

    /**
    * 主键
    */
    @TableId(value = "id", type = IdType.AUTO)     
    private Long id;

    /**
    * 名字
    */
        
    private String username;

    /**
    * 手机号码
    */
        
    private String phone;

    /**
    * 微信号码
    */
        
    private String wxnum;

    /**
    * 备注
    */
        
    private String xqdescribe;

    /**
    * 红娘关联id
    */
        
    private String matchmakerid;

    /**
     * 相亲帖关联id
     */

    private String blinddateid;

    /**
     * 城市中间表关联id
     */

    private String cityzhongid;


    /**
    * 状态 0禁用 1正常 2删除 3已处理
    */

    private Integer state;

    /**
     * 排序
     */

    private Integer sortnum;


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
    private User matchmakeruser;//关联红娘

    @TableField(exist = false)
    private Blinddateinfo blinddateinfo;//关联的相亲帖

    @TableField(exist = false)
    private ProvinceCityArea city;//关联的城市
}