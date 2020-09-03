package com.jiehuihui.web.service;

import com.jiehuihui.admin.req.DeleteSpecialParam;
import com.jiehuihui.admin.req.shop.DeleteShopinfoParam;
import com.jiehuihui.common.utils.RResult;
import com.jiehuihui.web.req.*;

public interface SearchService {

    /**
     * 获取搜索类型
     * @param result
     * @return
     */
    RResult getSouType(RResult result);

    /**
     * 获取热门搜索
     * @param result
     * @return
     */
    RResult getHotsou(RResult result);

    /**
     * 获取搜索历史记录
     *
     * @param result
     * @return
     */
    RResult getSourecords(RResult result, GetSourecordsParam param);

    /**
     * 搜索商品
     * @param result
     * @param param
     * @return
     */
    RResult getSearch(RResult result, GetSearchParam param);

    /**
     * 获取该城市下的所有区域
     * @param result
     * @param param
     * @return
     */
    RResult getAreaid(RResult result, GetAreaidParam param);

    /**
     * 获取商铺类型
     * @param result
     * @return
     */
    RResult getShopType(RResult result);
}
