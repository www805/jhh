package com.jiehuihui.web.controller;

import com.jiehuihui.admin.req.GetBlinddateinfoPageParam;
import com.jiehuihui.common.entity.home.HomeGg;
import com.jiehuihui.common.utils.RResult;
import com.jiehuihui.web.req.PutXiangqinParam;
import com.jiehuihui.web.service.XiangqinService;
import io.swagger.annotations.Api;
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
    public RResult putXiangqin(@RequestBody PutXiangqinParam param){
        RResult result = new RResult<>();
        return xiangqinService.putXiangqin(result, param);
    }

}
