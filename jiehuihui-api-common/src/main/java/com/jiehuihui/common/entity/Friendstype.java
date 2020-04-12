package com.jiehuihui.common.entity;

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
 * (Friendstype)实体类
 *
 * @author zhuang
 * @since 2020-04-11 23:09:49
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("jhh_friendstype")
public class Friendstype extends Model<Friendstype> implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 412736535291612509L;

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