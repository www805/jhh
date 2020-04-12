package com.jiehuihui.admin.service.shop;

import com.jiehuihui.admin.req.shop.AddUpdateShoplabelParam;
import com.jiehuihui.admin.req.shop.DeleteShoplabelParam;
import com.jiehuihui.admin.req.shop.GetShoplabelPageParam;
import com.jiehuihui.common.utils.RResult;

/**
 * (Shoplabel)表服务接口
 *
 * @author zhuang
 * @since 2020-04-11 12:41:26
 */
public interface ShoplabelService {

    //获取店铺标签文字
    RResult getShoplabel(RResult result);

    //获取一条店铺标签文字
    RResult getShoplabelByssid(RResult result, DeleteShoplabelParam param);

    //获取店铺标签文字，分页
    RResult getShoplabelPage(RResult result, GetShoplabelPageParam param);

    //添加店铺标签文字
    RResult addShoplabel(RResult result, AddUpdateShoplabelParam param);

    //修改店铺标签文字
    RResult updateShoplabel(RResult result, AddUpdateShoplabelParam param);

    //删除店铺标签文字
    RResult deleteShoplabel(RResult result, DeleteShoplabelParam param);

}