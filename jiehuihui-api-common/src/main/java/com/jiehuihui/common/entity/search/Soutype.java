package com.jiehuihui.common.entity.search;

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
 * (Soutype)实体类
 *
 * @author zhuang
 * @since 2020-04-11 21:27:44
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("jhh_base_soutype")
public class Soutype extends Model<Soutype> implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 411771805289142088L;

    /**
    * 主键
    */
    @TableId(value = "id", type = IdType.AUTO)     
    private Long id;

    /**
    * 搜索类型
    */
        
    private String typename;

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