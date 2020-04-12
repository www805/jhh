package com.jiehuihui.admin.vo.shop;

import com.jiehuihui.common.entity.shop.Shoptype;
import com.jiehuihui.admin.req.shop.GetShoptypePageParam;
import java.util.List;
import lombok.Data;

/**
 * (Shoptype)表返回类
 *
 * @author zhuang
 * @since 2020-04-11 21:16:54
 */

@Data
public class GetShoptypeVO {

    private GetShoptypePageParam pageparam;
    private List<Shoptype> pagelist;

}