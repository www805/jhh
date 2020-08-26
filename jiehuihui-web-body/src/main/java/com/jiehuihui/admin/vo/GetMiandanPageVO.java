package com.jiehuihui.admin.vo;

import com.jiehuihui.common.entity.Shopyhmd;
import com.jiehuihui.web.req.GetMiandanPageParam;
import lombok.Data;

import java.util.List;

/**
 * (免单)表返回类
 *
 * @author zhuang
 * @since 2020-05-03 22:48:58
 */

@Data
public class GetMiandanPageVO {

    private GetMiandanPageParam pageparam;
    private List<Shopyhmd> pagelist;

}