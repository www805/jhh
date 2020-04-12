package com.jiehuihui.common.entity.city;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("jhh_base_city")//城市表
public class City implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = -677980142105528951L;

    private Long id;

    private String cityname;
    private String cityfirst; //大写字母
    private String typeid;
    private Integer sortnum;
    private String ssid;
    private Integer state;

}
