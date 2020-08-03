package com.jiehuihui.admin.service.impl.home;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiehuihui.admin.mapper.home.HomeGgMapper;
import com.jiehuihui.admin.req.home.AddUpdateHomeGgParam;
import com.jiehuihui.admin.req.home.DeleteHomeGgParam;
import com.jiehuihui.admin.req.home.GetHomeggPageParam;
import com.jiehuihui.admin.service.home.HomeGgService;
import com.jiehuihui.admin.vo.home.GetHomeggPageVO;
import com.jiehuihui.common.entity.home.HomeGg;
import com.jiehuihui.common.utils.LogUtil;
import com.jiehuihui.common.utils.OpenUtil;
import com.jiehuihui.common.utils.RResult;
import com.jiehuihui.web.req.GetHomeWebParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class HomeGgServiceImpl implements HomeGgService {

    @Resource
    private HomeGgMapper homeggMapper;

    @Override
    public RResult getHomegg(RResult result, GetHomeWebParam param) {
        UpdateWrapper<HomeGg> ew = new UpdateWrapper<>();
        if(StringUtils.isNotEmpty(param.getCityid())){
            ew.eq("cityssid", param.getCityid());
        }
        ew.orderByDesc("sortnum");
        List<HomeGg> homeGgs = homeggMapper.selectList(ew);
        result.changeToTrue(homeGgs);
        return result;
    }

    @Override
    public RResult getHomeggByssid(RResult result, DeleteHomeGgParam param) {
        UpdateWrapper<HomeGg> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());
        List<HomeGg> homeGgs = homeggMapper.selectList(ew);
        if (null != homeGgs && homeGgs.size() > 0) {
            HomeGg homeGg = homeGgs.get(0);
            result.changeToTrue(homeGg);
        }else{
            result.setMessage("获取失败，该条数据不存在");
        }
        return result;
    }

    @Override
    public RResult getHomeggPage(RResult result, GetHomeggPageParam param) {

        GetHomeggPageVO homeggPageVO = new GetHomeggPageVO();

        UpdateWrapper<HomeGg> ew = new UpdateWrapper<>();
        if(StringUtils.isNotEmpty(param.getKeyword())){
            ew.like("h.keyword", param.getKeyword());
        }
        if(StringUtils.isNotEmpty(param.getCityssid())){
            ew.eq("h.cityssid", param.getCityssid());
        }

        ew.orderByDesc("h.sortnum");
        ew.orderByDesc("h.createtime");

        Integer count = homeggMapper.selectHomeggCount(ew);
        param.setRecordCount(count);

        Page<HomeGg> page = new Page<>(param.getCurrPage(), param.getPageSize());
        page.setTotal(count);
        IPage<HomeGg> sqCacheList = homeggMapper.getHomeggPage(page, ew);

        homeggPageVO.setPagelist(sqCacheList.getRecords());
        homeggPageVO.setPageparam(param);

        result.changeToTrue(homeggPageVO);
        return result;
    }

    @Override
    public RResult addHomegg(RResult result, AddUpdateHomeGgParam param) {
        //先校验添加的ssid是否已经存在，在判断添加的参数是否已经存在
        String ssid = OpenUtil.getUUID_32();
        if(StringUtils.isNoneBlank(param.getSsid())){
            UpdateWrapper<HomeGg> ew = new UpdateWrapper<>();
            ew.eq("ssid", param.getSsid());
            List<HomeGg> homeGgs = homeggMapper.selectList(ew);
            if(null != homeGgs && homeGgs.size() > 0){
                result.setMessage("该ssid已经存在，不能添加");
                return result;
            }
            ssid = param.getSsid();
        }
        UpdateWrapper<HomeGg> ew = new UpdateWrapper<>();
        ew.eq("keyword", param.getKeyword());
        ew.eq("cityid", param.getCityssid());
//        ew.eq("sortnum", param.getSortnum());
        List<HomeGg> ggList = homeggMapper.selectList(ew);
        if (null != ggList && ggList.size() > 0) {
            result.setMessage("该公告已经存在，不能添加");
            return result;
        }

        HomeGg homeGg = new HomeGg();
        homeGg.setKeyword(param.getKeyword());
        homeGg.setSortnum(param.getSortnum());
        homeGg.setUpdatetime(new Date());
        homeGg.setSsid(ssid);
        homeGg.setCityssid(param.getCityssid());
        int insert = homeggMapper.insert(homeGg);
        if (insert > 0) {
            result.changeToTrue(insert);
        }
        return result;
    }

    @Override
    public RResult updateHomegg(RResult result, AddUpdateHomeGgParam param) {
        //先校验是否已经存在
        UpdateWrapper<HomeGg> ewcheck = new UpdateWrapper<>();
        ewcheck.eq("keyword", param.getKeyword());
//        ewcheck.eq("sortnum", param.getSortnum());
        ewcheck.ne("ssid", param.getSsid());
        List<HomeGg> homeGgs = homeggMapper.selectList(ewcheck);
        if (null != homeGgs && homeGgs.size() > 0) {
            result.setMessage("修改的公告已经存在，不能修改");
            return result;
        }


        HomeGg homeGg = new HomeGg();

        UpdateWrapper<HomeGg> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());
        if (StringUtils.isNotBlank(param.getKeyword())) {
            homeGg.setKeyword(param.getKeyword());
        }
        if (null != param.getSortnum() && param.getSortnum() > 0) {
            homeGg.setSortnum(param.getSortnum());
        }
        if (StringUtils.isNotBlank(param.getCityssid())) {
            homeGg.setCityssid(param.getCityssid());
        }

        homeGg.setUpdatetime(new Date());
        int update = homeggMapper.update(homeGg, ew);
        if (update > 0) {
            result.changeToTrue(update);
            result.setMessage("修改成功！");
        }

        LogUtil.intoLog("用户：修改了首页公告！" + param.getKeyword());

        return result;
    }

    @Override
    public RResult deleteHomegg(RResult result, DeleteHomeGgParam param) {
        UpdateWrapper<HomeGg> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());

        HomeGg homeGg = homeggMapper.selectOne(ew);
        if(null == homeGg){
            result.setMessage("删除的公告不存在");
            return result;
        }

        int delete = homeggMapper.delete(ew);
        if (delete > 0) {
            result.changeToTrue(delete);
            result.setMessage("删除成功！");
        }
        LogUtil.intoLog("用户：删除了一条首页公告！" + homeGg.getKeyword());
        return result;
    }


}
