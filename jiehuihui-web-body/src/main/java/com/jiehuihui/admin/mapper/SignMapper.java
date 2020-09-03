package com.jiehuihui.admin.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiehuihui.common.entity.Sign;
import org.apache.ibatis.annotations.Param;

public interface SignMapper extends BaseMapper<Sign> {

    int getSignNowCount(@Param("ew") Wrapper wrapper);

}
