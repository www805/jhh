package com.jiehuihui.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 图片表
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("jhh_base_concern")
public class Concern implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = -6116869432542683164L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id; //主键ID
    private String shopssid; //店铺id
    private String userssid; //用户id
    private Integer state; //关注状态


}
