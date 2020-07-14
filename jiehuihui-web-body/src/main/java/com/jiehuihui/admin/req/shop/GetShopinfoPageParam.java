package com.jiehuihui.admin.req.shop;

import com.jiehuihui.common.utils.Page;
import lombok.Data;

import java.util.List;

@Data
public class GetShopinfoPageParam extends Page {

    private String shopname; //店铺名字
    private String phone; //手机号码
    private String wxnum; //微信号码
    private Integer state; //状态

    private List<String> cityList;//城市地区id

}
