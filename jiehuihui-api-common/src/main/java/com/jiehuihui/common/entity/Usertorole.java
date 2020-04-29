package com.jiehuihui.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.jiehuihui.common.entity.city.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

            
import java.io.Serializable;

/**
 * (Usertorole)实体类
 *
 * @author zhuang
 * @since 2020-04-20 23:21:37
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("jhh_base_usertorole")
public class Usertorole extends Model<Usertorole> implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 7747400966348742461L;


    /**
    * 主键
    */
    @TableId(value = "id", type = IdType.AUTO)     
    private Long id;

    /**
    * 角色关联id
    */
        
    private String roleid;

    /**
    * 用户关联id
    */
        
    private String userid;

}