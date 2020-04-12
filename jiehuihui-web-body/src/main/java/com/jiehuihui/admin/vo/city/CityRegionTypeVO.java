package com.jiehuihui.admin.vo.city;

import com.jiehuihui.admin.req.city.GetCityRegionTypePageParam;
import com.jiehuihui.common.entity.city.CityRegionType;
import lombok.Data;

import java.util.List;

@Data
public class CityRegionTypeVO {

    private GetCityRegionTypePageParam pageparam;
    private List<CityRegionType> pagelist;

}
