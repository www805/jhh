package com.jiehuihui.admin.req;

import com.jiehuihui.common.utils.Page;
import lombok.Data;

import java.util.List;

@Data
public class GetSpecialPageParam extends Page {

    private String specialtitle; //特价标题
    private String shopname; //店铺名字
    private String typeid; //类型

    private List<String> cityList;//城市地区id

}
