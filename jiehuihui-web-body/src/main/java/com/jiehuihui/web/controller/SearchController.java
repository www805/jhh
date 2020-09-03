package com.jiehuihui.web.controller;

import com.jiehuihui.common.utils.RResult;
import com.jiehuihui.web.req.GetAreaidParam;
import com.jiehuihui.web.req.GetSearchParam;
import com.jiehuihui.web.req.GetSourecordsParam;
import com.jiehuihui.web.service.SearchService;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(value = "搜索模块", description = "搜索的接口信息(前台)")
@RestController
@CrossOrigin
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    /**
     * 获取搜索类型
     * @return
     */
    @PostMapping("/getSouType")
    public RResult getSouType(){
        RResult result = new RResult<>();
        return searchService.getSouType(result);
    }

    /**
     * 获取热门搜索
     * @return
     */
    @PostMapping("/getHotsou")
    public RResult getHotsou(){
        RResult result = new RResult<>();
        return searchService.getHotsou(result);
    }

    /**
     * 获取搜索历史记录
     * @return
     */
    @PostMapping("/getSourecords")
    public RResult getSourecords(@RequestBody @Validated GetSourecordsParam param){
        RResult result = new RResult<>();
        return searchService.getSourecords(result, param);
    }

    /**
     * 搜索商品
     * @param param
     * @return
     */
    @PostMapping("/getSearch")
    public RResult getSearch(@RequestBody @Validated GetSearchParam param){
        RResult result = new RResult<>();
        return searchService.getSearch(result, param);
    }

    /**
     * 获取该城市下的所有区域
     * @param param
     * @return
     */
    @PostMapping("/getAreaid")
    public RResult getAreaid(@RequestBody @Validated GetAreaidParam param){
        RResult result = new RResult<>();
        return searchService.getAreaid(result, param);
    }

    /**
     * 获取商铺类型
     * @return
     */
    @PostMapping("/getShopType")
    public RResult getShopType(){
        RResult result = new RResult<>();
        return searchService.getShopType(result);
    }

}
