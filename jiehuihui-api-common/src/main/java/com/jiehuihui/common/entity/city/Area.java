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
@TableName("jhh_base_area")//区域表
public class Area implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 6693041221908461824L;

    private Long id;

    private String areaname;
    private String typeid;
    private Integer sortnum;
    private String ssid;
    private Integer state;

}
