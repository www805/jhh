package com.jiehuihui.web.service;


import com.jiehuihui.common.utils.RResult;
import com.jiehuihui.web.req.GetMiandanPageParam;

public interface MiandanService {

    //获取免单信息
    RResult getMiandanAll(RResult result, GetMiandanPageParam param);


}
