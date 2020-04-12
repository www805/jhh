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
 * 首页类型表
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("jhh_base_hometype")
public class HomeType implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 3629974409296428964L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id; //主键ID
    private String keyword; //公告关键字
    private String ico; //图片关联id
    private String cityid; //城市关联id
    private String gotourl; //跳转地址
    private Integer num; //指定位置
    private String ssid; //唯一id
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createtime; //创建时间
    private Integer state; //状态

    @TableField(exist = false)
    private City city;//关联的城市

}
