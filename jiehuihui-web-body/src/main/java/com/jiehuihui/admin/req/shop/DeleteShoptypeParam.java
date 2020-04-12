package com.jiehuihui.admin.req.shop;

import lombok.Data;
import javax.validation.constraints.NotBlank;

/**
 * (Shoptype)表删除参数接收类
 *
 * @author zhuang
 * @since 2020-04-11 21:16:54
 */

@Data
public class DeleteShoptypeParam {

    @NotBlank(message = "ssid不能为空")
    private String ssid; //唯一ID

}