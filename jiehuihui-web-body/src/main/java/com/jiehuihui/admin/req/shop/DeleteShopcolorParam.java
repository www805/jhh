package com.jiehuihui.admin.req.shop;

import lombok.Data;
import javax.validation.constraints.NotBlank;

/**
 * (Shopcolor)表删除参数接收类
 *
 * @author zhuang
 * @since 2020-04-11 17:27:28
 */

@Data
public class DeleteShopcolorParam {

    @NotBlank(message = "ssid不能为空")
    private String ssid; //唯一ID

}