package com.jiehuihui.common.entity.shop;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

                
import java.io.Serializable;

/**
 * (Shoptype)实体类
 *
 * @author zhuang
 * @since 2020-04-11 21:16:54
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("jhh_base_shoptype")
public class Shoptype extends Model<Shoptype> implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = -40995005698942052L;

    /**
    * 主键
    */
    @TableId(value = "id", type = IdType.AUTO)     
    private Long id;

    /**
    * 类型名称
    */
        
    private String typename;

    /**
    * 排序
    */
        
    private Integer sortnum;

    /**
    * 唯一id
    */
        
    private String ssid;


}