package com.jiehuihui.admin.service.shop;

import com.jiehuihui.admin.req.shop.AddUpdateShoptypeParam;
import com.jiehuihui.admin.req.shop.DeleteShoptypeParam;
import com.jiehuihui.admin.req.shop.GetShoptypePageParam;
import com.jiehuihui.common.utils.RResult;

/**
 * (Shoptype)表服务接口
 *
 * @author zhuang
 * @since 2020-04-11 21:16:54
 */
public interface ShoptypeService {

    //获取店铺类型
    RResult getShoptype(RResult result);

    //获取一条店铺类型
    RResult getShoptypeByssid(RResult result, DeleteShoptypeParam param);

    //获取店铺类型，分页
    RResult getShoptypePage(RResult result, GetShoptypePageParam param);

    //添加店铺类型
    RResult addShoptype(RResult result, AddUpdateShoptypeParam param);

    //修改店铺类型
    RResult updateShoptype(RResult result, AddUpdateShoptypeParam param);

    //删除店铺类型
    RResult deleteShoptype(RResult result, DeleteShoptypeParam param);

}