package com.jiehuihui.admin.service.shop;

import com.jiehuihui.admin.req.shop.AddUpdateShopinfoParam;
import com.jiehuihui.admin.req.shop.DeleteShopinfoParam;
import com.jiehuihui.admin.req.shop.GetShopinfoPageParam;
import com.jiehuihui.common.entity.shop.Shopinfo;
import com.jiehuihui.common.utils.RResult;

/**
 * (JhhShopinfo)店铺表服务接口
 *
 * @author zhuang
 * @since 2020-07-12 16:40:14
 */
public interface ShopinfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param param 实例参数对象
     * @return 返回对象
     */
    RResult getShopinfoByssid(RResult<Shopinfo> result, DeleteShopinfoParam param);

    /**
     * 查询多条数据
     * @param param  实例参数对象
     * @return 返回对象
     */
    RResult getShopinfoPage(RResult<Shopinfo> result, GetShopinfoPageParam param);

    /**
     * 新增数据
     *
     * @param param 实例参数对象
     * @return 返回对象
     */
    RResult addShopinfo(RResult<Shopinfo> result, AddUpdateShopinfoParam param);

    /**
     * 修改数据
     *
     * @param param 实例参数对象
     * @return 返回对象
     */
    RResult updateShopinfo(RResult<Shopinfo> result, AddUpdateShopinfoParam param);

    /**
     * 通过主键删除数据
     *
     * @param param 实例参数对象
     * @return 是否成功返回对象
     */
    RResult deleteShopinfo(RResult<Shopinfo> result, DeleteShopinfoParam param);


}