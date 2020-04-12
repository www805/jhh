package com.jiehuihui.common.entity.search;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.jiehuihui.common.entity.city.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import com.fasterxml.jackson.annotation.JsonFormat;
    
import java.io.Serializable;

/**
 * (Hotsou)实体类
 *
 * @author zhuang
 * @since 2020-04-11 19:41:22
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("jhh_base_hotsou")
public class Hotsou extends Model<Hotsou> implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = -79294505521495850L;

    /**
    * 主键
    */
    @TableId(value = "id", type = IdType.AUTO)     
    private Long id;

    /**
    * 关键字
    */
        
    private String keyword;

    /**
    * 排序
    */
        
    private Integer sortnum;

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

}