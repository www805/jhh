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
@TableName("jhh_base_cityregiontype")//城市区域类型表
public class CityRegionType implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 8424988951056047743L;

    private Long id;
    private String typename;
    private Integer sortnum;
    private String ssid;


}
