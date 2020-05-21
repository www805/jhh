package com.jiehuihui.admin.service;

import com.jiehuihui.admin.req.AddUpdateShopinfoupParam;
import com.jiehuihui.admin.req.DeleteShopinfoupParam;
import com.jiehuihui.admin.req.GetShopinfoupPageParam;
import com.jiehuihui.common.utils.RResult;

/**
 * (Shopinfoup)表服务接口
 *
 * @author zhuang
 * @since 2020-05-05 20:56:59
 */
public interface ShopinfoupService {

    //获取店铺申请
    RResult getShopinfoup(RResult result);

    //获取一条店铺申请
    RResult getShopinfoupByssid(RResult result, DeleteShopinfoupParam param);

    //获取店铺申请，分页
    RResult getShopinfoupPage(RResult result, GetShopinfoupPageParam param);

    //添加店铺申请
    RResult addShopinfoup(RResult result, AddUpdateShopinfoupParam param);

    //修改店铺申请
    RResult updateShopinfoup(RResult result, AddUpdateShopinfoupParam param);

    //删除店铺申请
    RResult deleteShopinfoup(RResult result, DeleteShopinfoupParam param);

}