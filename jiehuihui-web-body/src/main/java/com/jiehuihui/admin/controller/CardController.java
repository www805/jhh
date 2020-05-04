package com.jiehuihui.admin.controller;

import com.jiehuihui.common.entity.Card;
import com.jiehuihui.admin.req.UseCardParam;
import com.jiehuihui.admin.service.CardService;
import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.base.check.Update;
import org.springframework.web.bind.annotation.*;

import com.jiehuihui.admin.req.AddUpdateCardParam;
import com.jiehuihui.admin.req.DeleteCardParam;
import com.jiehuihui.admin.req.GetCardPageParam;
import com.jiehuihui.common.utils.RResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

/**
 * (Card)表控制层
 *
 * @author zhuang
 * @since 2020-04-23 23:34:41
 */
@Api(value = "充值卡模块", description = "充值卡的接口信息(后台)")
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/admin/card")
public class CardController {

    
    /**
     * 服务对象
     */
    @Resource
    private CardService cardService;

    //使用一张充值卡
    @ApiOperation(value = "使用一张充值卡", notes = "使用一张充值卡")
    @PostMapping("/useCard")
    public RResult useCard(@ApiParam(name="使用一张充值卡参数",value="传入json格式",required=true) @RequestBody UseCardParam param){
        RResult<Card> result = new RResult<>();
        return cardService.useCard (result, param);
    }

    //获取充值卡
    @ApiOperation(value = "获取充值卡", notes = "获取所有充值卡,没分页")
    @GetMapping("/getCard")
    public RResult getCard(){
        RResult<Card> result = new RResult<>();
        return cardService.getCard (result);
    }

    //获取一条充值卡
    @ApiOperation(value = "获取一条充值卡", notes = "获取一条充值卡")
    @PostMapping("/getCardByssid")
    public RResult getCardByssid(@ApiParam(name="获取一条充值卡参数",value="传入json格式",required=true) @RequestBody DeleteCardParam param){
        RResult<Card> result = new RResult<>();
        return cardService.getCardByssid (result,param);
    }

    //获取充值卡，分页
    @ApiOperation(value = "获取充值卡", notes = "分页获取所有充值卡")
    @PostMapping("/getCardPage")
    public RResult getCardPage(@ApiParam(name="分页获取充值卡参数",value="传入json格式",required=true) @RequestBody GetCardPageParam param){
        RResult<Card> result = new RResult<>();
        return cardService.getCardPage (result, param);
    }

    //新增充值卡
    @ApiOperation(value = "新增充值卡", notes = "新增充值卡")
    @PostMapping("/addCard")
    public RResult addCard(@ApiParam(name="新增充值卡参数",value="传入json格式",required=true) @RequestBody @Validated(Create.class) AddUpdateCardParam param){
        RResult<Card> result = new RResult<>();
        return cardService.addCard (result, param);
    }

    //修改充值卡
    @ApiOperation(value = "修改充值卡", notes = "修改充值卡")
    @PostMapping("/updateCard")
    public RResult updateCard(@ApiParam(name="修改充值卡参数",value="传入json格式",required=true) @RequestBody @Validated(Update.class) AddUpdateCardParam param){
        RResult<Card> result = new RResult<>();
        return cardService.updateCard (result,param);
    }

    //删除一条充值卡
    @ApiOperation(value = "删除充值卡", notes = "删除一条充值卡")
    @PostMapping("/deleteCard")
    public RResult deleteCard(@ApiParam(name="充值卡删除参数",value="传入json格式",required=true) @RequestBody DeleteCardParam param){
        RResult<Card> result = new RResult<>();
        return cardService.deleteCard (result, param);
    }

}