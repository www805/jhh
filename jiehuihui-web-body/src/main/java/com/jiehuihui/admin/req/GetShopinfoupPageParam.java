package com.jiehuihui.admin.req;

import com.jiehuihui.common.utils.Page;
import lombok.Data;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * (Shopinfoup)表查询请求参数接收类
 *
 * @author zhuang
 * @since 2020-05-05 20:57:00
 */

@Data
public class GetShopinfoupPageParam extends Page {

    private String shopname; //名字
    private String phone; //手机号码
    private String wxnum; //微信号码
    private Integer state; //状态

    private List<String> cityList;//城市地区id

}