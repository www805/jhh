package com.jiehuihui.admin.mapper.city;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiehuihui.common.entity.city.CityVO;
import com.jiehuihui.common.entity.city.ProvinceVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 城市区域
 */
public interface CityRegionListMapper extends BaseMapper<ProvinceVO> {

    Integer getCityListCount(@Param("ew") Wrapper wrapper);

    IPage<ProvinceVO> getCityListPage(Page<?> page, @Param("ew") Wrapper wrapper);

    List<ProvinceVO> getCityAll(@Param("ew") Wrapper wrapper);

    //获取所有城市区域无分页
    List<ProvinceVO> getCityList(@Param("ew") Wrapper wrapper);

    //获取父级目录
    List<ProvinceVO> getCityParentList(@Param("ew") Wrapper wrapper);

    //获取城市下的所有区域
    List<CityVO> getCityByAreaAll(@Param("ew") Wrapper wrapper);

}
