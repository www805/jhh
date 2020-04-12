package com.jiehuihui.admin.req;

import com.jiehuihui.common.utils.Page;
import lombok.Data;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * (Friendstype)表查询请求参数接收类
 *
 * @author zhuang
 * @since 2020-04-11 23:09:49
 */

@Data
public class GetFriendstypePageParam extends Page {

    private String typename; //类型名称
    private Integer sortnum; //排序

}