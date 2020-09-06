package com.jiehuihui.web.service.impl;

import com.jiehuihui.admin.req.GetCardPageParam;
import com.jiehuihui.admin.req.UseCardParam;
import com.jiehuihui.admin.service.CardService;
import com.jiehuihui.common.utils.RResult;
import com.jiehuihui.web.service.UpCardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UpCardServiceImpl implements UpCardService {

    @Resource
    private CardService cardService;

    @Override
    public RResult getCardPage(RResult result, GetCardPageParam param) {
        param.setState(1);
        return cardService.getCardPage(result, param);
    }

    @Override
    public RResult useCard(RResult result, UseCardParam param) {
        return cardService.useCard(result, param);
    }
}
