package com.jiehuihui.admin.service;

import com.jiehuihui.admin.req.AddUpdateBlinddateParam;
import com.jiehuihui.admin.req.DeleteBlinddateParam;
import com.jiehuihui.admin.req.GetBlinddatePageParam;
import com.jiehuihui.common.utils.RResult;

/**
 * (Blinddate)表服务接口
 *
 * @author zhuang
 * @since 2020-05-04 18:12:51
 */
public interface BlinddateService {

    //获取相亲申请
    RResult getBlinddate(RResult result);

    //获取一条相亲申请
    RResult getBlinddateByssid(RResult result, DeleteBlinddateParam param);

    //获取相亲申请，分页
    RResult getBlinddatePage(RResult result, GetBlinddatePageParam param);

    //添加相亲申请
    RResult addBlinddate(RResult result, AddUpdateBlinddateParam param);

    //修改相亲申请
    RResult updateBlinddate(RResult result, AddUpdateBlinddateParam param);

    //删除相亲申请
    RResult deleteBlinddate(RResult result, DeleteBlinddateParam param);

}