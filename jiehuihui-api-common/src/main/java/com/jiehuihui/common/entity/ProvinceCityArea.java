package com.jiehuihui.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProvinceCityArea implements Serializable {

    private static final long serialVersionUID = -840012275549213152L;

    private Long id;
    private String pid;
    private String cid;
    private String aid;
    private String provincename;
    private String cityname;
    private String areaname;


}
