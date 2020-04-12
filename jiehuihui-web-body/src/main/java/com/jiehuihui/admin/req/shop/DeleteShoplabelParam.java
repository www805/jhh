package com.jiehuihui.admin.req.shop;

import lombok.Data;
import javax.validation.constraints.NotBlank;

/**
 * (Shoplabel)表删除参数接收类
 *
 * @author zhuang
 * @since 2020-04-11 12:41:26
 */

@Data
public class DeleteShoplabelParam {

    @NotBlank(message = "ssid不能为空")
    private String ssid; //唯一ID

}