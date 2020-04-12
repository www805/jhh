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
 * (Shoplabel)实体类
 *
 * @author zhuang
 * @since 2020-04-11 12:41:26
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("jhh_base_shoplabel")
public class Shoplabel extends Model<Shoplabel> implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = -23740812252345007L;

    /**
    * 主键
    */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
    * 标签文字
    */
        
    private String labelname;

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