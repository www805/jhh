package com.jiehuihui.web.service;


import com.jiehuihui.admin.req.AddUpdateBlinddateinfoParam;
import com.jiehuihui.admin.req.DeleteBlinddateinfoParam;
import com.jiehuihui.admin.req.GetBlinddateinfoPageParam;
import com.jiehuihui.common.utils.RResult;
import com.jiehuihui.web.req.PutXiangqinParam;

public interface XiangqinService {

    //获取相亲信息
    RResult getXiangqininfo(RResult result, GetBlinddateinfoPageParam param);

    //申请相亲
    RResult putXiangqin(RResult result, PutXiangqinParam param);

    //获取相亲信息
    RResult getBlinddateinfoByssid(RResult result, DeleteBlinddateinfoParam param);

    //添加相亲信息
    RResult addOrUpdateBlinddateinfo(RResult result, AddUpdateBlinddateinfoParam param);

    //删除相亲信息
    RResult delBlinddateinfo(RResult result, DeleteBlinddateinfoParam param);
}
