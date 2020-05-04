package com.jiehuihui.admin.req;

import com.jiehuihui.common.utils.Page;
import lombok.Data;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * (Blinddateinfo)表查询请求参数接收类
 *
 * @author zhuang
 * @since 2020-05-03 22:48:58
 */

@Data
public class GetBlinddateinfoPageParam extends Page {

    private String username; //发表用户名
    private String myname; //相亲名字
    private String phone; //择偶条件描述

    private List<String> cityList;//城市地区id

}