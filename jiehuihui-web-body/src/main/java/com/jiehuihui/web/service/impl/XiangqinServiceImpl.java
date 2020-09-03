package com.jiehuihui.web.service.impl;

import com.jiehuihui.admin.req.AddUpdateBlinddateParam;
import com.jiehuihui.admin.req.GetBlinddateinfoPageParam;
import com.jiehuihui.admin.service.BlinddateService;
import com.jiehuihui.admin.service.BlinddateinfoService;
import com.jiehuihui.common.utils.RResult;
import com.jiehuihui.web.req.PutXiangqinParam;
import com.jiehuihui.web.service.XiangqinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class XiangqinServiceImpl implements XiangqinService {

    @Autowired
    private BlinddateinfoService blinddateinfoService;

    @Autowired
    private BlinddateService blinddateService;

    @Override
    public RResult getXiangqininfo(RResult result, GetBlinddateinfoPageParam param) {
        param.setTopnum(1);
        RResult blinddateinfoPage = blinddateinfoService.getBlinddateinfoPage(result, param);
        return blinddateinfoPage;
    }

    @Override
    public RResult putXiangqin(RResult result, PutXiangqinParam param) {

        AddUpdateBlinddateParam addUpdateBlinddateParam = new AddUpdateBlinddateParam();
        addUpdateBlinddateParam.setUsername(param.getUsername());
        addUpdateBlinddateParam.setPhone(param.getIphone());
        addUpdateBlinddateParam.setWxnum(param.getWxnum());
        addUpdateBlinddateParam.setXqdescribe(param.getXqdescribe());
        addUpdateBlinddateParam.setMatchmakeruserid(param.getMatchmakeruserid());
        addUpdateBlinddateParam.setBlinddateid(param.getBlinddateid());
        addUpdateBlinddateParam.setCityList(param.getCityList());

        RResult rResult = blinddateService.addBlinddate(result, addUpdateBlinddateParam);

        return rResult;
    }

}
