package com.jiehuihui.admin.service.search;

import com.jiehuihui.admin.req.search.AddUpdateHotsouParam;
import com.jiehuihui.admin.req.search.DeleteHotsouParam;
import com.jiehuihui.admin.req.search.GetHotsouPageParam;
import com.jiehuihui.common.utils.RResult;

/**
 * (Hotsou)表服务接口
 *
 * @author zhuang
 * @since 2020-04-11 19:41:22
 */
public interface HotsouService {

    //获取热门搜素
    RResult getHotsou(RResult result);

    //获取一条热门搜素
    RResult getHotsouByssid(RResult result, DeleteHotsouParam param);

    //获取热门搜素，分页
    RResult getHotsouPage(RResult result, GetHotsouPageParam param);

    //添加热门搜素
    RResult addHotsou(RResult result, AddUpdateHotsouParam param);

    //修改热门搜素
    RResult updateHotsou(RResult result, AddUpdateHotsouParam param);

    //删除热门搜素
    RResult deleteHotsou(RResult result, DeleteHotsouParam param);

}