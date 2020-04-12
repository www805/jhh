package com.jiehuihui.admin.vo.shop;

import com.jiehuihui.common.entity.shop.Shopcolor;
import java.util.List;

/**
 * (Shopcolor)表返回类
 *
 * @author zhuang
 * @since 2020-04-11 17:27:28
 */
import com.jiehuihui.admin.req.shop.GetShopcolorPageParam;
import lombok.Data;

@Data
public class GetShopcolorVO {

    private GetShopcolorPageParam pageparam;
    private List<Shopcolor> pagelist;

}