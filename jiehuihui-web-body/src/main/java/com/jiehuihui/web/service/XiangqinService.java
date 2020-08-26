package com.jiehuihui.web.service;


import com.jiehuihui.admin.req.GetBlinddateinfoPageParam;
import com.jiehuihui.common.utils.RResult;
import com.jiehuihui.web.req.PutXiangqinParam;

public interface XiangqinService {

    //获取相亲信息
    RResult getXiangqininfo(RResult result, GetBlinddateinfoPageParam param);

    //申请相亲
    RResult putXiangqin(RResult result, PutXiangqinParam param);
}
