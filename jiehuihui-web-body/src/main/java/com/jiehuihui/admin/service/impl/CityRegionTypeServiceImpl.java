package com.jiehuihui.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiehuihui.admin.mapper.city.CityRegionTypeMapper;
import com.jiehuihui.admin.req.city.AddUpdateCityRegionTypeParam;
import com.jiehuihui.admin.req.city.DeleteCityRegionTypeParam;
import com.jiehuihui.admin.req.city.GetCityRegionTypePageParam;
import com.jiehuihui.admin.service.city.CityRegionTypeService;
import com.jiehuihui.admin.vo.city.CityRegionTypeVO;
import com.jiehuihui.common.entity.city.CityRegionType;
import com.jiehuihui.common.utils.LogUtil;
import com.jiehuihui.common.utils.OpenUtil;
import com.jiehuihui.common.utils.RResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("CityRegionTypeService")
public class CityRegionTypeServiceImpl implements CityRegionTypeService {

    @Resource
    private CityRegionTypeMapper cityRegionTypeMapper;

    @Override
    public RResult getCityRegionTypePage(RResult result, GetCityRegionTypePageParam param) {

        CityRegionTypeVO cityRegionTypeVO = new CityRegionTypeVO();

        UpdateWrapper<CityRegionType> ew = new UpdateWrapper<>();
        if(StringUtils.isNotEmpty(param.getTypename())){
            ew.like("typename", param.getTypename());
        }
        ew.orderByDesc("sortnum");
        ew.orderByDesc("id");

        Integer count = cityRegionTypeMapper.selectCount(ew);
        param.setRecordCount(count);

        Page<CityRegionType> page = new Page<>(param.getCurrPage(), param.getPageSize());
        page.setTotal(count);

        IPage<CityRegionType> cityRegionTypeList = cityRegionTypeMapper.selectPage(page, ew);

        cityRegionTypeVO.setPagelist(cityRegionTypeList.getRecords());
        cityRegionTypeVO.setPageparam(param);

        result.changeToTrue(cityRegionTypeVO);
        return result;
    }

    @Override
    public RResult getCityRegionTypeByssid(RResult result, DeleteCityRegionTypeParam param) {
        UpdateWrapper<CityRegionType> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());
        CityRegionType cityRegionType = cityRegionTypeMapper.selectList(ew).get(0);
        if (null != cityRegionType) {
            result.changeToTrue(cityRegionType);
        }else{
            result.setMessage("获取失败，该条数据不存在");
        }
        return result;
    }

    @Override
    public RResult addCityRegionType(RResult result, AddUpdateCityRegionTypeParam param) {
        //先校验添加的ssid是否已经存在，在判断添加的参数是否已经存在
        String ssid = OpenUtil.getUUID_32();
        if(StringUtils.isNoneBlank(param.getSsid())){
            UpdateWrapper<CityRegionType> ew = new UpdateWrapper<>();
            ew.eq("ssid", param.getSsid());
            if(null != cityRegionTypeMapper.selectList(ew) && cityRegionTypeMapper.selectList(ew).size() > 0){
                result.setMessage("该ssid已经存在，不能添加");
                return result;
            }
            ssid = param.getSsid();
        }
        UpdateWrapper<CityRegionType> ew = new UpdateWrapper<>();
        ew.eq("typename", param.getTypename());
        List<CityRegionType> ggList = cityRegionTypeMapper.selectList(ew);
        if (null != ggList && ggList.size() > 0) {
            result.setMessage("该城市类型已经存在，不能添加");
            return result;
        }

        CityRegionType cityRegionType = new CityRegionType();
        cityRegionType.setTypename(param.getTypename());
        cityRegionType.setSortnum(param.getSortnum());
        cityRegionType.setSsid(ssid);
        int insert = cityRegionTypeMapper.insert(cityRegionType);
        if (insert > 0) {
            result.changeToTrue(insert);
        }
        return result;
    }

    @Override
    public RResult updateCityRegionType(RResult result, AddUpdateCityRegionTypeParam param) {

        //先校验是否已经存在
        UpdateWrapper<CityRegionType> ewcheck = new UpdateWrapper<>();
        ewcheck.eq("typename", param.getTypename());
//        ewcheck.eq("sortnum", param.getSortnum());
        ewcheck.ne("ssid", param.getSsid());
        List<CityRegionType> cityRegionTypeList = cityRegionTypeMapper.selectList(ewcheck);
        if (null != cityRegionTypeList && cityRegionTypeList.size() > 0) {
            result.setMessage("修改的类型已经存在，不能修改");
            return result;
        }


        CityRegionType cityRegionType = new CityRegionType();

        UpdateWrapper<CityRegionType> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());
        if (StringUtils.isNotBlank(param.getTypename())) {
            cityRegionType.setTypename(param.getTypename());
        }
        if (null != param.getSortnum() && param.getSortnum() > 0) {
            cityRegionType.setSortnum(param.getSortnum());
        }

        int update = cityRegionTypeMapper.update(cityRegionType, ew);
        if (update > 0) {
            result.changeToTrue(update);
            result.setMessage("修改成功！");
        }

        LogUtil.intoLog("用户：修改了城市类型！" + param.getTypename());
        return result;
    }

    @Override
    public RResult deleteCityRegionType(RResult result, DeleteCityRegionTypeParam param) {

        if ("1".equals(param.getSsid()) || "2".equals(param.getSsid()) || "3".equals(param.getSsid())) {
            result.setMessage("不能删除基础类型");
            return result;
        }

        UpdateWrapper<CityRegionType> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());

        CityRegionType cityRegionType = cityRegionTypeMapper.selectOne(ew);
        if(null == cityRegionType){
            result.setMessage("删除的类型不存在");
            return result;
        }

        int delete = cityRegionTypeMapper.delete(ew);
        if (delete > 0) {
            result.changeToTrue(delete);
            result.setMessage("删除成功！");
        }
        LogUtil.intoLog("用户：删除了一条城市类型！" + cityRegionType.getTypename());
        return result;
    }

    @Override
    public RResult getCityRegionType(RResult result) {
        UpdateWrapper<CityRegionType> ew = new UpdateWrapper<>();
        ew.orderByDesc("sortnum");
        List<CityRegionType> cityRegionTypeList = cityRegionTypeMapper.selectList(ew);
        result.changeToTrue(cityRegionTypeList);

        return result;
    }
}
