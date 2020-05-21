package com.jiehuihui.admin.req;

import lombok.Data;
import javax.validation.constraints.NotBlank;

/**
 * (Shopinfoup)表删除参数接收类
 *
 * @author zhuang
 * @since 2020-05-05 20:57:00
 */

@Data
public class DeleteShopinfoupParam {

    @NotBlank(message = "ssid不能为空")
    private String ssid; //唯一ID

}