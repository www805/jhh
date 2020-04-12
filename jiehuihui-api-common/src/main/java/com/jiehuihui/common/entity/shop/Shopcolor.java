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
 * (Shopcolor)实体类
 *
 * @author zhuang
 * @since 2020-04-11 17:27:28
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("jhh_base_shopcolor")
public class Shopcolor extends Model<Shopcolor> implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 760745813364051297L;

    /**
    * 主键
    */
    @TableId(value = "id", type = IdType.AUTO)     
    private Long id;

    /**
    * 十六进制颜色
    */
        
    private String color;

    /**
    * 颜色中文名
    */
        
    private String colorname;

    /**
    * 排序
    */
        
    private Integer sortnum;

    /**
    * 唯一id
    */
        
    private String ssid;

    /**
    * 状态 0禁用 1正常
    */
        
    private Integer state;

}