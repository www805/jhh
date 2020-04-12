package com.jiehuihui.admin.req.shop;

import com.jiehuihui.common.utils.Page;
import lombok.Data;
import javax.validation.constraints.NotBlank;

/**
 * (Shopcolor)表查询请求参数接收类
 *
 * @author zhuang
 * @since 2020-04-11 17:27:28
 */

@Data
public class GetShopcolorPageParam extends Page {

    @NotBlank(message = "十六进制颜色不能为空")
    private String color; //十六进制颜色
    @NotBlank(message = "颜色中文名不能为空")
    private String colorname; //颜色中文名

}