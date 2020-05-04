package com.jiehuihui.admin.service;

import com.jiehuihui.admin.req.AddUpdateBlinddateinfoParam;
import com.jiehuihui.admin.req.DeleteBlinddateinfoParam;
import com.jiehuihui.admin.req.GetBlinddateinfoPageParam;
import com.jiehuihui.common.utils.RResult;

/**
 * (Blinddateinfo)表服务接口
 *
 * @author zhuang
 * @since 2020-05-03 22:48:58
 */
public interface BlinddateinfoService {

    //获取相亲
    RResult getBlinddateinfo(RResult result);

    //获取一条相亲
    RResult getBlinddateinfoByssid(RResult result, DeleteBlinddateinfoParam param);

    //获取相亲，分页
    RResult getBlinddateinfoPage(RResult result, GetBlinddateinfoPageParam param);

    //添加相亲
    RResult addBlinddateinfo(RResult result, AddUpdateBlinddateinfoParam param);

    //修改相亲
    RResult updateBlinddateinfo(RResult result, AddUpdateBlinddateinfoParam param);

    //删除相亲
    RResult deleteBlinddateinfo(RResult result, DeleteBlinddateinfoParam param);

    //
    RResult gettopBlinddate(RResult result, DeleteBlinddateinfoParam param);
}