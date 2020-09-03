package com.jiehuihui.common.entity.city;

import lombok.Data;

import java.util.List;

@Data
public class CitySearchVO extends City {

    private List<Area> children;

}
