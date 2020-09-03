package com.jiehuihui.web.controller;

import com.jiehuihui.common.utils.RResult;
import com.jiehuihui.web.req.GetMiandanPageParam;
import com.jiehuihui.web.service.MiandanService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(value = "首页免单模块", description = "首页免单的接口信息(前台)")
@RestController
@CrossOrigin
@RequestMapping("/miandan")
public class MiandanController {

    @Autowired
    private MiandanService miandanService;

    //获取免单信息
    @PostMapping("/getMiandanAll")
    public RResult getMiandanAll(@RequestBody GetMiandanPageParam param){
        RResult result = new RResult<>();
        return miandanService.getMiandanAll(result, param);
    }


}
