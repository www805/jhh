package com.jiehuihui.web.controller;

import com.jiehuihui.admin.req.AddUpdateBlinddateinfoParam;
import com.jiehuihui.admin.req.DeleteBlinddateinfoParam;
import com.jiehuihui.admin.req.GetBlinddateinfoPageParam;
import com.jiehuihui.admin.req.shop.DeleteShopinfoParam;
import com.jiehuihui.common.base.check.Create;
import com.jiehuihui.common.utils.RResult;
import com.jiehuihui.web.req.PutXiangqinParam;
import com.jiehuihui.web.service.XiangqinService;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(value = "首页相亲模块", description = "首页相亲的接口信息(前台)")
@RestController
@CrossOrigin
@RequestMapping("/xiangqin")
public class XiangqinController {

    @Resource
    private XiangqinService xiangqinService;

    /**
     * 获取相亲信息
     * @param param
     * @return
     */
    @PostMapping("/getXiangqininfo")
    public RResult getXiangqininfo(@RequestBody GetBlinddateinfoPageParam param){
        RResult result = new RResult<>();
        return xiangqinService.getXiangqininfo(result, param);
    }

    /**
     * 申请相亲
     * @param param
     * @return
     */
    @PostMapping("/putXiangqin")
    public RResult putXiangqin(@RequestBody @Validated PutXiangqinParam param){
        RResult result = new RResult<>();
        return xiangqinService.putXiangqin(result, param);
    }

    /**
     * 获取一条相亲信息
     * @param param
     * @return
     */
    @PostMapping("/getBlinddateinfoByssid")
    public RResult getBlinddateinfoByssid(@RequestBody DeleteBlinddateinfoParam param){
        RResult result = new RResult<>();
        return xiangqinService.getBlinddateinfoByssid(result, param);
    }

    /**
     * 添加相亲信息
     * @param param
     * @return
     */
    @PostMapping("/addOrUpdateBlinddateinfo")
    public RResult addOrUpdateBlinddateinfo(@RequestBody @Validated(Create.class) AddUpdateBlinddateinfoParam param){
        RResult result = new RResult<>();
        return xiangqinService.addOrUpdateBlinddateinfo(result, param);
    }

    /**
     * 删除相亲信息
     * @param param
     * @return
     */
    @PostMapping("/delBlinddateinfo")
    public RResult delBlinddateinfo(@RequestBody @Validated DeleteBlinddateinfoParam param){
        RResult result = new RResult<>();
        return xiangqinService.delBlinddateinfo(result, param);
    }


}
