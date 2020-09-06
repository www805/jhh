package com.jiehuihui.web.controller;

import com.jiehuihui.admin.req.GetCardPageParam;
import com.jiehuihui.admin.req.UseCardParam;
import com.jiehuihui.common.utils.RResult;
import com.jiehuihui.web.service.UpCardService;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(value = "充值卡模块", description = "充值卡的接口信息(前台)")
@RestController
@CrossOrigin
@RequestMapping("/upcard")
public class UpCardController {

    @Resource
    private UpCardService upCardService;

    /**
     * 获取所有充值卡
     * @return
     */
    @PostMapping("/getCardPage")
    public RResult getCardPage(@RequestBody GetCardPageParam param){
        RResult result = new RResult<>();
        return upCardService.getCardPage(result, param);
    }


    /**
     * 用户充值
     * @param param
     * @return
     */
    @PostMapping("/useCard")
    public RResult useCard(@RequestBody @Validated UseCardParam param){
        RResult result = new RResult<>();
        return upCardService.useCard(result, param);
    }


}
