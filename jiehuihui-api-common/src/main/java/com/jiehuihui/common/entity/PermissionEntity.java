package com.jiehuihui.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("jhh_base_permission")
public class PermissionEntity extends Model<PermissionEntity> implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 215440418377181191L;

    /**
    * 主键
    */
    private Long id;

    /**
    * 权限名称
    */
    private String label;


    /**
     * 排序
     */
    private Integer sortnum;

    /**
     * 下级权限集合
     */
    @TableField(exist = false)
    private List<PermissionEntity> children;
}