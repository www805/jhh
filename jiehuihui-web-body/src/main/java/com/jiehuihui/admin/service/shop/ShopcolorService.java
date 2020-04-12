package com.jiehuihui.admin.service.shop;

import com.jiehuihui.admin.req.shop.AddUpdateShopcolorParam;
import com.jiehuihui.admin.req.shop.DeleteShopcolorParam;
import com.jiehuihui.admin.req.shop.GetShopcolorPageParam;
import com.jiehuihui.common.utils.RResult;

/**
 * (Shopcolor)表服务接口
 *
 * @author zhuang
 * @since 2020-04-11 17:27:28
 */
public interface ShopcolorService {

    //获取店铺标签颜色
    RResult getShopcolor(RResult result);

    //获取一条店铺标签颜色
    RResult getShopcolorByssid(RResult result, DeleteShopcolorParam param);

    //获取店铺标签颜色，分页
    RResult getShopcolorPage(RResult result, GetShopcolorPageParam param);

    //添加店铺标签颜色
    RResult addShopcolor(RResult result, AddUpdateShopcolorParam param);

    //修改店铺标签颜色
    RResult updateShopcolor(RResult result, AddUpdateShopcolorParam param);

    //删除店铺标签颜色
    RResult deleteShopcolor(RResult result, DeleteShopcolorParam param);

}