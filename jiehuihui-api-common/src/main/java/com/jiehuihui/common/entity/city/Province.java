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
@TableName("jhh_base_province")//省份表
public class Province implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = -2812339461975235596L;


    private Long id;

    private String provincename;
    private String typeid;
    private Integer sortnum;
    private String ssid;
    private Integer state;


}
