package com.jiehuihui.admin.req;

import lombok.Data;
import javax.validation.constraints.NotBlank;

/**
 * (Card)表删除参数接收类
 *
 * @author zhuang
 * @since 2020-04-23 23:34:41
 */

@Data
public class DeleteCardParam {

    @NotBlank(message = "ssid不能为空")
    private String ssid; //唯一ID

}