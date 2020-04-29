package com.jiehuihui.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * (Permission)角色列表里的权限
 *
 * @author zhuang
 * @since 2020-04-12 20:58:37
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("jhh_base_roletopermission")
public class RolePermission extends Model<RolePermission> implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 3518904132598304984L;


    /**
    * 主键
    */
    private Long id;

    /**
    * 角色关联id
    */
    private String roleid;


    /**
     * 权限关联id
     */
    private String permissionid;

}