package com.jiehuihui.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiehuihui.admin.mapper.city.CityRegionListMapper;
import com.jiehuihui.admin.mapper.AreaMapper;
import com.jiehuihui.admin.mapper.city.CityMapper;
import com.jiehuihui.admin.mapper.city.CityzhongMapper;
import com.jiehuihui.admin.mapper.ProvinceMapper;
import com.jiehuihui.admin.req.city.GetCityListParam;
import com.jiehuihui.admin.req.city.UpdateAddCityParam;
import com.jiehuihui.admin.service.city.CityRegionListService;
import com.jiehuihui.admin.vo.city.GetCityListVO;
import com.jiehuihui.common.entity.city.*;
import com.jiehuihui.common.utils.PinYinUtil;
import com.jiehuihui.common.utils.RResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("CityRegionListService")
public class CityRegionListServiceImpl implements CityRegionListService {

    @Resource
    private CityRegionListMapper cityRegionListMapper;

    @Resource
    private AreaMapper areaMapper;

    @Resource
    private CityMapper cityMapper;

    @Resource
    private ProvinceMapper provinceMapper;

    @Resource
    private CityzhongMapper cityzhongMapper;


    @Override
    public RResult getCityList(RResult result, GetCityListParam param) {

        UpdateWrapper<ProvinceVO> ew = new UpdateWrapper<>();
        ew.ne("p.state", -1);//隐藏可显示没区域的省市
        ew.ne("c.state", -1);
        ew.ne("a.state", -1);
        ew.orderByDesc("psortnum");
        ew.orderByDesc("csortnum");
        ew.orderByDesc("asortnum");

        List<ProvinceVO> cityParentList = cityRegionListMapper.getCityList(ew);
        result.changeToTrue(cityParentList);

        return result;
    }

    @Override
    public RResult getCityListPage(RResult result, GetCityListParam param) {

        GetCityListVO getCityListVO = new GetCityListVO();

        UpdateWrapper<ProvinceVO> ew = new UpdateWrapper<>();

//        Integer count = cityRegionListMapper.getCityListCount(ew);
        Page<ProvinceVO> page = new Page<>(param.getCurrPage(), param.getPageSize());

        ew.orderByDesc("psortnum");
        ew.orderByDesc("csortnum");
        ew.orderByDesc("asortnum");
        IPage<ProvinceVO> sqCacheList = cityRegionListMapper.getCityListPage(page, ew);
        param.setRecordCount(Integer.parseInt(sqCacheList.getTotal() + ""));

        getCityListVO.setPagelist(sqCacheList.getRecords());
        getCityListVO.setPageparam(param);

        result.changeToTrue(getCityListVO);
        return result;
    }

    @Transactional
    @Override
    public RResult addCityRegion(RResult result, UpdateAddCityParam param) {

        int insert = 0;
        int insert1 = 0;

        Integer cityregiontype = param.getCityregiontype();
        //1省 2市 3区域
        if(cityregiontype == 1){

            UpdateWrapper<Province> ew = new UpdateWrapper<>();
            ew.eq("provincename", param.getCityregionname());

            Integer count = provinceMapper.selectCount(ew);
            if (count > 0) {
                result.setMessage("该省份已经存在，无法添加");
                return result;
            }

            Province province = new Province();
            province.setProvincename(param.getCityregionname());
            province.setTypeid(param.getCityregiontype() + "");//按照城市类型id关联
            province.setId(IdWorker.getId());
            province.setSsid(IdWorker.get32UUID());
            province.setSortnum(param.getSortnum());
            province.setState(param.getState());
            insert = provinceMapper.insert(province);

            Cityzhong cityzhong = new Cityzhong();
            cityzhong.setProvinceid(province.getId() + "");
            insert1 = cityzhongMapper.insert(cityzhong);

        }else if(cityregiontype == 2){

            UpdateWrapper<City> ew = new UpdateWrapper<>();
            ew.eq("cityname", param.getCityregionname());

            Integer count = cityMapper.selectCount(ew);
            if (count > 0) {
                result.setMessage("该城市已经存在，无法添加");
                return result;
            }

            String cityfirst = PinYinUtil.toFirstChar(param.getCityregionname()).toUpperCase();
            if(StringUtils.isNotBlank(cityfirst)){
                cityfirst = cityfirst.substring(0,1);
            }

            City city = new City();
            city.setCityname(param.getCityregionname());
            city.setTypeid(param.getCityregiontype()+"");//按照城市类型id关联
            city.setCityfirst(cityfirst);
            city.setId(IdWorker.getId());
            city.setSsid(IdWorker.get32UUID());
            city.setSortnum(param.getSortnum());
            city.setState(param.getState());
            insert = cityMapper.insert(city);

            //还要添加中间表
            UpdateWrapper<Cityzhong> wq = new UpdateWrapper<>();
            wq.eq("provinceid",param.getCityregionsuper());
            wq.eq("cityid", null);
            wq.eq("areaid", null);
            List<Cityzhong> cityzhongList = cityzhongMapper.selectList(wq);
            if(cityzhongList.size() > 0){
                Cityzhong cityzhong = cityzhongList.get(0);

                UpdateWrapper<Cityzhong> wqz = new UpdateWrapper<>();
                wqz.eq("id", cityzhong.getId());

                Cityzhong updateCityZhong = new Cityzhong();
                updateCityZhong.setCityid(city.getId() + "");

                insert1 = cityzhongMapper.update(updateCityZhong, wqz);
            }else{
                Cityzhong cityzhong = new Cityzhong();
                cityzhong.setProvinceid(param.getCityregionsuper());
                cityzhong.setCityid(city.getId() + "");
                insert1 = cityzhongMapper.insert(cityzhong);
            }

        }else if(cityregiontype == 3){

            UpdateWrapper<Area> ew = new UpdateWrapper<>();
            ew.eq("areaname", param.getCityregionname());

            Integer count = areaMapper.selectCount(ew);
            if (count > 0) {
                result.setMessage("该区域已经存在，无法添加");
                return result;
            }

            UpdateWrapper<Cityzhong> wqzCheck = new UpdateWrapper<>();
            wqzCheck.eq("cityid", param.getCityregionsuper());

            List<Cityzhong> citySupers = cityzhongMapper.selectList(wqzCheck);
            if (citySupers.size() == 0) {
                result.setMessage("无法获取城市的上级省对象，添加失败");
                return result;
            }


            Area area = new Area();
            area.setAreaname(param.getCityregionname());
            area.setTypeid(param.getCityregiontype()+"");//按照城市类型id关联
            area.setId(IdWorker.getId());
            area.setSsid(IdWorker.get32UUID());
            area.setSortnum(param.getSortnum());
            area.setState(param.getState());
            insert = areaMapper.insert(area);


            Cityzhong provinceZ = citySupers.get(0);


            //还要添加中间表
            UpdateWrapper<Cityzhong> wq = new UpdateWrapper<>();
            wq.eq("provinceid", provinceZ.getProvinceid());
            wq.eq("cityid", param.getCityregionsuper());
            wq.eq("areaid", null);
            List<Cityzhong> cityzhongList = cityzhongMapper.selectList(wq);
            if(cityzhongList.size() > 0){
                Cityzhong cityzhong = cityzhongList.get(0);

                UpdateWrapper<Cityzhong> wqz = new UpdateWrapper<>();
                wqz.eq("id", cityzhong.getId());

                Cityzhong updateCityZhong = new Cityzhong();
                updateCityZhong.setAreaid(area.getId() + "");

                insert1 = cityzhongMapper.update(updateCityZhong, wqz);
            }else{
                Cityzhong cityzhong = new Cityzhong();
                cityzhong.setProvinceid(provinceZ.getProvinceid());
                cityzhong.setCityid(param.getCityregionsuper());
                cityzhong.setAreaid(area.getId() + "");
                insert1 = cityzhongMapper.insert(cityzhong);
            }
        }

        result.changeToTrue(insert);
        result.setMessage("添加成功！");
        return result;
    }

    @Override
    public RResult updateCityRegion(RResult result, UpdateAddCityParam param) {

        int update = 0;
        Integer cityregiontype = param.getCityregiontype();

        //1省 2市 3区域
        if(cityregiontype == 1){
            UpdateWrapper<Province> ew = new UpdateWrapper<>();
            ew.eq("id", param.getId());

            Province province = new Province();
            province.setProvincename(param.getCityregionname());
            province.setSortnum(param.getSortnum());

            update = provinceMapper.update(province, ew);

        }else if(cityregiontype == 2){
            UpdateWrapper<City> ew = new UpdateWrapper<>();
            ew.eq("id", param.getId());

            City city = new City();
            city.setCityname(param.getCityregionname());
            city.setSortnum(param.getSortnum());

            update = cityMapper.update(city, ew);

        }else if(cityregiontype == 3){
            UpdateWrapper<Area> ew = new UpdateWrapper<>();
            ew.eq("id", param.getId());

            Area area = new Area();
            area.setAreaname(param.getCityregionname());
            area.setSortnum(param.getSortnum());

            update = areaMapper.update(area, ew);
        }

        result.changeToTrue(update);
        result.setMessage("修改成功！");
        return result;
    }

    @Transactional
    @Override
    public RResult deleteCity(RResult result, UpdateAddCityParam param) {

        int delete = 0;
        int delete1 = 0;
        Integer cityregiontype = param.getCityregiontype();

        //1省 2市 3区域
        if(cityregiontype == 1){
            UpdateWrapper<Province> ew = new UpdateWrapper<>();
            ew.eq("id", param.getId());
            delete = provinceMapper.delete(ew);


            //删除省底下的全部城市
            UpdateWrapper<Cityzhong> ewz = new UpdateWrapper<>();
            ewz.eq("provinceid", param.getId());
            List<Cityzhong> cityzhongs = cityzhongMapper.selectList(ewz);

            for (Cityzhong cityzhong : cityzhongs) {
                UpdateWrapper<City> ewcity = new UpdateWrapper<>();
                ewcity.eq("id", cityzhong.getCityid());
                cityMapper.delete(ewcity);

                UpdateWrapper<Cityzhong> ewarea = new UpdateWrapper<>();
                ewarea.eq("cityid", cityzhong.getCityid());
                List<Cityzhong> ewareazhongs = cityzhongMapper.selectList(ewarea);

                for (Cityzhong ewareazhong : ewareazhongs) {
                    UpdateWrapper<Area> uwarea = new UpdateWrapper<>();
                    uwarea.eq("id", ewareazhong.getAreaid());
                    areaMapper.delete(uwarea);
                }

            }

            //删除中间表
            delete1 = cityzhongMapper.delete(ewz);

        }else if(cityregiontype == 2){

            UpdateWrapper<City> ew = new UpdateWrapper<>();
            ew.eq("id", param.getId());
            delete = cityMapper.delete(ew);

            //删除城市底下的全部区域
            UpdateWrapper<Cityzhong> ewz = new UpdateWrapper<>();
            ewz.eq("cityid", param.getId());
            List<Cityzhong> cityzhongs = cityzhongMapper.selectList(ewz);

            for (Cityzhong cityzhong : cityzhongs) {
                UpdateWrapper<Area> ewarea = new UpdateWrapper<>();
                ewarea.eq("id", cityzhong.getAreaid());
                areaMapper.delete(ewarea);
            }

            //删除中间表
            delete1 = cityzhongMapper.delete(ewz);

        }else if(cityregiontype == 3){

            UpdateWrapper<Area> ew = new UpdateWrapper<>();
            ew.eq("id", param.getId());
            delete = areaMapper.delete(ew);

            UpdateWrapper<Cityzhong> ewz = new UpdateWrapper<>();
            ewz.eq("areaid", param.getId());
            //删除中间表
            delete1 = cityzhongMapper.delete(ewz);

        }

        result.changeToTrue(delete);
        return result;
    }

    @Override
    public RResult getCityParentList(RResult result) {
        List<ProvinceVO> cityParentList = cityRegionListMapper.getCityParentList(null);
        result.changeToTrue(cityParentList);
        return result;
    }

    @Override
    public RResult getCityAllList(RResult result) {
        List<City> cityList = cityMapper.selectList(null);
        result.changeToTrue(cityList);
        return result;
    }

}
