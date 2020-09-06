package com.jiehuihui.web.service;

import com.jiehuihui.admin.req.GetCardPageParam;
import com.jiehuihui.admin.req.UseCardParam;
import com.jiehuihui.common.utils.RResult;

public interface UpCardService {

    //获取所有充值卡
    RResult getCardPage(RResult result, GetCardPageParam param);

    //用户充值
    RResult useCard(RResult result, UseCardParam param);
}
