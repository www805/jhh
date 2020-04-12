package com.jiehuihui.admin.req.search;

import com.jiehuihui.common.utils.Page;
import lombok.Data;

/**
 * (Soutype)表查询请求参数接收类
 *
 * @author zhuang
 * @since 2020-04-11 21:27:45
 */

@Data
public class GetSoutypePageParam extends Page {

    private String typename; //搜索类型
    private Integer sortnum; //排序
    private Integer state; //状态 0禁用 1正常 2删除

}