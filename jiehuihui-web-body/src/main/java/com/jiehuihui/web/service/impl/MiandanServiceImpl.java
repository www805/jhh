package com.jiehuihui.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiehuihui.admin.mapper.ShopyhmdMapper;
import com.jiehuihui.admin.vo.GetMiandanPageVO;
import com.jiehuihui.common.entity.Shopyhmd;
import com.jiehuihui.common.utils.DateUtil;
import com.jiehuihui.common.utils.RResult;
import com.jiehuihui.web.req.GetMiandanPageParam;
import com.jiehuihui.web.service.MiandanService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MiandanServiceImpl implements MiandanService {

    @Autowired
    private ShopyhmdMapper shopyhmdMapper;

    @Override
    public RResult getMiandanAll(RResult result, GetMiandanPageParam param) {

        GetMiandanPageVO miandanPageVO = new GetMiandanPageVO();
        UpdateWrapper<Shopyhmd> ew = new UpdateWrapper<>();

        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
        String week = simple.format((new Date()).getTime());
        if (null != param.getTopnum() && param.getTopnum() > 0) {
            if (StringUtils.isNoneBlank(param.getProvinceid())) {
                ew.eq("z.provinceid", param.getProvinceid());
            }
            if (StringUtils.isNoneBlank(param.getCityid())) {
                ew.eq("z.cityid", param.getCityid());
            }
            if (StringUtils.isNoneBlank(param.getAreaid())) {
                ew.eq("z.areaid", param.getAreaid());
            }
            if (StringUtils.isNoneBlank(param.getKeyword())) {
                ew.like("md.mdtitle", param.getKeyword());
            }
            ew.eq("md.topnum", 1);
            ew.eq("md.state", 1);
            ew.le("md.starttime", week);
            ew.ge("md.endtime", week);
            ew.ge("md.topendtime", week).or();
        }

        if (StringUtils.isNoneBlank(param.getProvinceid())) {
            ew.eq("z.provinceid", param.getProvinceid());
        }
        if (StringUtils.isNoneBlank(param.getCityid())) {
            ew.eq("z.cityid", param.getCityid());
        }
        if (StringUtils.isNoneBlank(param.getAreaid())) {
            ew.eq("z.areaid", param.getAreaid());
        }

        if (StringUtils.isNoneBlank(param.getKeyword())) {
            ew.like("md.mdtitle", param.getKeyword());
        }
        ew.eq("md.state", 1);
        ew.le("md.starttime", week);
        ew.ge("md.endtime", week);

        ew.orderByDesc("md.createtime");
        ew.orderByDesc("md.topendtime");

        Integer count = shopyhmdMapper.selectShopyhmdCount(ew);
        param.setRecordCount(count);

        Page<Shopyhmd> page = new Page<>(param.getCurrPage(), param.getPageSize());
        page.setTotal(count);
        IPage<Shopyhmd> shopyhmdIPage = shopyhmdMapper.selectShopyhmdPage(page, ew);

        miandanPageVO.setPagelist(shopyhmdIPage.getRecords());
        miandanPageVO.setPageparam(param);

        result.changeToTrue(miandanPageVO);
        return result;
    }

}
