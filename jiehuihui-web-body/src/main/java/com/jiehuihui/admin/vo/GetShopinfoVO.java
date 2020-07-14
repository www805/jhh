package com.jiehuihui.admin.vo;

import com.jiehuihui.admin.req.shop.GetShopinfoPageParam;
import com.jiehuihui.common.entity.shop.Shopinfo;
import lombok.Data;

import java.util.List;

/**
 * (Shopinfoup)表返回类
 *
 * @author zhuang
 * @since 2020-05-05 20:57:00
 */

@Data
public class GetShopinfoVO {

    private GetShopinfoPageParam pageparam;
    private List<Shopinfo> pagelist;

}