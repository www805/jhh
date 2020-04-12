package com.jiehuihui.common.entity.home;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jiehuihui.common.entity.city.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 首页公告实体类
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("jhh_base_homegg")
public class HomeGg implements Serializable{

    @TableField(exist = false)
    private static final long serialVersionUID = -8266837503804684695L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id; //主键ID
    private String keyword; //公告关键字
    private Integer sortnum; //排序
    private String ssid; //唯一id
    private String cityssid; //城市关联id
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createtime; //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatetime; //修改时间

    @TableField(exist = false)
    private City city;//关联的城市
}
