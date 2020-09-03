package com.jiehuihui.web.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiehuihui.common.entity.search.SouRecords;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SourecordsMapper extends BaseMapper<SouRecords> {

    List<SouRecords> selectSouRecordsList(@Param("ew") Wrapper wrapper);

    int souRecordsCount(@Param("ew") Wrapper wrapper);

}
