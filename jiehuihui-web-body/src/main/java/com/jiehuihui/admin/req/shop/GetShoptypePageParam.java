package com.jiehuihui.admin.req.shop;

import com.jiehuihui.common.utils.Page;
import lombok.Data;

/**
 * (Shoptype)表查询请求参数接收类
 *
 * @author zhuang
 * @since 2020-04-11 21:16:54
 */

@Data
public class GetShoptypePageParam extends Page {

    private String typename; //类型名称

}