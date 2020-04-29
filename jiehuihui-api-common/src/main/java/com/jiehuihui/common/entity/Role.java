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
import java.util.List;

/**
 * (Role)实体类
 *
 * @author zhuang
 * @since 2020-04-16 21:56:13
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("jhh_base_role")
public class Role extends Model<Role> implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 357727296453458168L;

    /**
    * 主键
    */
    @TableId(value = "id", type = IdType.AUTO)     
    private Long id;

    /**
    * 角色名
    */
        
    private String rolename;

    /**
    * 用户关联数
    */
        
    private Long usersize;

    /**
    * 创建时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")     
    private Date createtime;

    /**
    * 状态0禁用 1正常
    */
        
    private Integer state;

    @TableField(exist = false)
    private List<Permission> permissions;
}