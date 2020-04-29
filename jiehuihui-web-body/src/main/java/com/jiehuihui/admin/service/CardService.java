package com.jiehuihui.admin.service;

import com.jiehuihui.admin.entity.Card;
import com.jiehuihui.admin.req.AddUpdateCardParam;
import com.jiehuihui.admin.req.DeleteCardParam;
import com.jiehuihui.admin.req.GetCardPageParam;
import com.jiehuihui.admin.req.UseCardParam;
import com.jiehuihui.common.utils.RResult;

/**
 * (Card)表服务接口
 *
 * @author zhuang
 * @since 2020-04-23 23:34:41
 */
public interface CardService {

    //使用一张充值卡
    RResult useCard(RResult result, UseCardParam param);

    //获取所有充值卡
    RResult getCard(RResult result);

    //获取一条充值卡
    RResult getCardByssid(RResult result, DeleteCardParam param);

    //获取充值卡，分页
    RResult getCardPage(RResult result, GetCardPageParam param);

    //添加充值卡
    RResult addCard(RResult result, AddUpdateCardParam param);

    //修改充值卡
    RResult updateCard(RResult result, AddUpdateCardParam param);

    //删除充值卡
    RResult deleteCard(RResult result, DeleteCardParam param);

}