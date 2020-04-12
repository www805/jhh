package com.jiehuihui.admin.vo.city;

import com.jiehuihui.admin.req.city.GetCityListParam;
import com.jiehuihui.common.entity.city.ProvinceVO;
import lombok.Data;

import java.util.List;

@Data
public class GetCityListVO {

    private GetCityListParam pageparam;
    private List<ProvinceVO> pagelist;
}
