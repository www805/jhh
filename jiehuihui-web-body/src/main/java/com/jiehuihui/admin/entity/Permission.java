package com.jiehuihui.admin.entity;

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
import java.util.List;

/**
 * (Permission)实体类
 *
 * @author zhuang
 * @since 2020-04-12 20:58:37
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("jhh_base_permission")
public class Permission extends Model<Permission> implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = -23333529607603286L;

    /**
    * 主键
    */
    @TableId(value = "id", type = IdType.AUTO)     
    private Long id;

    /**
    * 父级权限
    */
        
    private String permissionsuper;

    /**
    * 权限名称
    */
        
    private String permissionname;

    /**
    * 权限类型
    */
        
    private Integer permissionnametype;

    /**
    * 标识
    */
        
    private String permissionnamezy;

    /**
    * 排序
    */
        
    private Integer sortnum;

    /**
    * 状态
    */
        
    private Integer state;

    /**
     * 下级权限集合
     */
    @TableField(exist = false)
    private List<Permission> children;
}