package com.jiehuihui.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("jhh_base_sign")
public class Sign implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = -5814069070359412085L;


    @TableId(value = "id", type = IdType.AUTO)
    private Long id; //主键ID

    private Integer num; //签到积分
    private String userid; //用户关联id
    private String sdescribe; //使用积分描述
    private Integer state; //状态0禁用 1正常
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createtime; //创建时间

}
