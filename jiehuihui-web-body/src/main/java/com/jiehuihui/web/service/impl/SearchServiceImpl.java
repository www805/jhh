package com.jiehuihui.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jiehuihui.admin.mapper.city.CityRegionListMapper;
import com.jiehuihui.admin.req.shop.GetShopinfoPageParam;
import com.jiehuihui.admin.service.search.HotsouService;
import com.jiehuihui.admin.service.search.SoutypeService;
import com.jiehuihui.admin.service.shop.ShopinfoService;
import com.jiehuihui.admin.service.shop.ShoptypeService;
import com.jiehuihui.common.entity.city.CityVO;
import com.jiehuihui.common.entity.city.ProvinceVO;
import com.jiehuihui.common.entity.search.SouRecords;
import com.jiehuihui.common.utils.Code;
import com.jiehuihui.common.utils.OpenUtil;
import com.jiehuihui.common.utils.RResult;
import com.jiehuihui.web.mapper.SourecordsMapper;
import com.jiehuihui.web.req.GetAreaidParam;
import com.jiehuihui.web.req.GetSearchParam;
import com.jiehuihui.web.req.GetSourecordsParam;
import com.jiehuihui.web.service.SearchService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SoutypeService soutypeService;

    @Autowired
    private HotsouService hotsouService;

    @Autowired
    private ShoptypeService shoptypeService;

    @Autowired
    private ShopinfoService shopinfoService;

    @Autowired
    private SourecordsMapper sourecordsMapper;

    @Autowired
    private CityRegionListMapper cityRegionListMapper;

    @Override
    public RResult getSouType(RResult result) {
        return soutypeService.getSoutype(result);
    }

    @Override
    public RResult getHotsou(RResult result) {
        return hotsouService.getHotsou(result);
    }

    @Override
    public RResult getSourecords(RResult result, GetSourecordsParam param) {
        UpdateWrapper<SouRecords> ew = new UpdateWrapper<>();
        ew.eq("userid", param.getUserid());
        ew.last("LIMIT 10");
        ew.orderByDesc("createtime");
        List<SouRecords> souRecords = sourecordsMapper.selectList(ew);
        result.changeToTrue(souRecords);
        return result;
    }

    @Override
    public RResult getSearch(RResult result, GetSearchParam param) {

        //根据类型，看搜索的是相亲，店铺，还是特价，免单
        //这个可以先搁置，做住一个先，等做完登录用户，再来处理这个问题
        if("0".equals(param.getTypeid())){
            //搜索店铺信息
            GetShopinfoPageParam pageParam = new GetShopinfoPageParam();
            pageParam.setCurrPage(param.getCurrPage());
            pageParam.setPageSize(param.getPageSize());
            pageParam.setShoptype(param.getShoptype());
            pageParam.setAreaid(param.getAreaid());
            pageParam.setTopnum(1);//置顶排在前面
            if(!"0".equals(param.getKeyword())){
                pageParam.setShopname(param.getKeyword());
            }
            if (StringUtils.isNoneBlank(param.getUserid())) {
                pageParam.setUserid(param.getUserid());
            }
            result = shopinfoService.getShopinfoPage(result, pageParam);
        }

        //如果成功，那就记录它的搜索
        if(Code.SUCCESS.toString().equals(result.getActioncode())){
            if(StringUtils.isNoneBlank(param.getUserid()) && !"0".equals(param.getKeyword())){
                UpdateWrapper<SouRecords> ew = new UpdateWrapper<>();
                ew.eq("s.userid", param.getUserid());
                ew.eq("s.soukeyword", param.getKeyword());
                List<SouRecords> souRecords = sourecordsMapper.selectSouRecordsList(ew);
                if (souRecords.size() == 0) {
                    SouRecords records = new SouRecords();
                    records.setUserid(param.getUserid());
                    records.setSoukeyword(param.getKeyword());
                    records.setSsid(OpenUtil.getUUID_32());
                    sourecordsMapper.insert(records);
                }
            }
        }


        return result;
    }

    @Override
    public RResult getAreaid(RResult result, GetAreaidParam param) {

        //城市id从中间表匹配所有区域的名字
        //一对多
        UpdateWrapper<ProvinceVO> ew = new UpdateWrapper<>();
        ew.eq("z.cityid", param.getCityid());

        List<CityVO> cityByAreaAll = cityRegionListMapper.getCityByAreaAll(ew);
        result.changeToTrue(cityByAreaAll);
        return result;
    }

    @Override
    public RResult getShopType(RResult result) {
        return shoptypeService.getShoptype(result);
    }
}
