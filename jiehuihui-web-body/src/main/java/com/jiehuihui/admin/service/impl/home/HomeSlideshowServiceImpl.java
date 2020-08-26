package com.jiehuihui.admin.service.impl.home;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiehuihui.admin.mapper.home.HomeSlideshowMapper;
import com.jiehuihui.admin.req.home.AddUpdateHomeSlideshowParam;
import com.jiehuihui.admin.req.home.DeleteHomeSlideshowParam;
import com.jiehuihui.admin.req.home.GetHomeSlideshowPageParam;
import com.jiehuihui.admin.service.home.HomeSlideshowService;
import com.jiehuihui.admin.vo.home.GetHomeSlideshowPageVO;
import com.jiehuihui.common.entity.Shopinfoup;
import com.jiehuihui.common.entity.home.HomeSlideshow;
import com.jiehuihui.common.utils.LogUtil;
import com.jiehuihui.common.utils.OpenUtil;
import com.jiehuihui.common.utils.RResult;
import com.jiehuihui.web.req.GetHomeWebParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HomeSlideshowServiceImpl implements HomeSlideshowService {

    @Resource
    private HomeSlideshowMapper homeSlideshowMapper;

    @Override
    public RResult getHomeSlideshow(RResult result, GetHomeWebParam param) {
        UpdateWrapper<HomeSlideshow> ew = new UpdateWrapper<>();
        if(StringUtils.isNotEmpty(param.getCityid())){
            ew.eq("cityid", param.getCityid());
        }
        ew.orderByDesc("num");
        List<HomeSlideshow> homeSlideshows = homeSlideshowMapper.selectList(ew);
        result.changeToTrue(homeSlideshows);
        return result;
    }

    @Override
    public RResult getHomeSlideshowByssid(RResult result, DeleteHomeSlideshowParam param) {
        UpdateWrapper<HomeSlideshow> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());
        List<HomeSlideshow> homeSlideshows = homeSlideshowMapper.selectList(ew);
        if (null != homeSlideshows && homeSlideshows.size() > 0) {
            HomeSlideshow homeSlideshow = homeSlideshows.get(0);
            result.changeToTrue(homeSlideshow);
        }else{
            result.setMessage("获取失败，该条数据不存在");
        }
        return result;
    }

    @Override
    public RResult getHomeSlideshowPage(RResult result, GetHomeSlideshowPageParam param) {

        GetHomeSlideshowPageVO homeSlideshowPageVO = new GetHomeSlideshowPageVO();

        UpdateWrapper<HomeSlideshow> ew = new UpdateWrapper<>();
        if(StringUtils.isNotEmpty(param.getCityid())){
            ew.eq("h.cityid", param.getCityid());
        }

        ew.orderByDesc("h.num");
        ew.orderByDesc("h.createtime");

        Integer count = homeSlideshowMapper.selectHomeSlideshowCount(ew);
        param.setRecordCount(count);

        Page<HomeSlideshow> page = new Page<>(param.getCurrPage(), param.getPageSize());
        page.setTotal(count);
        IPage<HomeSlideshow> sqCacheList = homeSlideshowMapper.getHomeSlideshowPage(page, ew);

        homeSlideshowPageVO.setPagelist(sqCacheList.getRecords());
        homeSlideshowPageVO.setPageparam(param);

        result.changeToTrue(homeSlideshowPageVO);
        return result;
    }

    @Override
    public RResult addHomeSlideshow(RResult result, AddUpdateHomeSlideshowParam param) {
        //先校验添加的ssid是否已经存在，在判断添加的参数是否已经存在
        String ssid = OpenUtil.getUUID_32();
        if(StringUtils.isNoneBlank(param.getSsid())){
            UpdateWrapper<HomeSlideshow> ew = new UpdateWrapper<>();
            ew.eq("ssid", param.getSsid());
            List<HomeSlideshow> homeSlideshows = homeSlideshowMapper.selectList(ew);
            if(null != homeSlideshows && homeSlideshows.size() > 0){
                result.setMessage("该ssid已经存在，不能添加");
                return result;
            }
            ssid = param.getSsid();
        }
        UpdateWrapper<HomeSlideshow> ew = new UpdateWrapper<>();
        ew.eq("cityid", param.getCityid());
        ew.eq("img", param.getImg());
        List<HomeSlideshow> slideshowList = homeSlideshowMapper.selectList(ew);
        if (null != slideshowList && slideshowList.size() > 0) {
            result.setMessage("该轮播图已经存在，不能添加");
            return result;
        }

        HomeSlideshow homeSlideshow = new HomeSlideshow();
        homeSlideshow.setImg(param.getImg());
        homeSlideshow.setNum(param.getNum());
        homeSlideshow.setSsid(ssid);
        homeSlideshow.setCityid(param.getCityid());
        homeSlideshow.setGotourl(param.getGotourl());
        int insert = homeSlideshowMapper.insert(homeSlideshow);
        if (insert > 0) {
            result.changeToTrue(insert);
        }
        return result;
    }

    @Override
    public RResult updateHomeSlideshow(RResult result, AddUpdateHomeSlideshowParam param) {
        //先校验是否已经存在
        UpdateWrapper<HomeSlideshow> ewcheck = new UpdateWrapper<>();
        ewcheck.ne("ssid", param.getSsid());
        ewcheck.eq("img", param.getImg());
        ewcheck.eq("cityid", param.getCityid());
        List<HomeSlideshow> homeSlideshows = homeSlideshowMapper.selectList(ewcheck);
        if (null != homeSlideshows && homeSlideshows.size() > 0) {
            result.setMessage("修改的轮播图已经存在，不能修改");
            return result;
        }

        UpdateWrapper<HomeSlideshow> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());

        HomeSlideshow homeSlideshow = new HomeSlideshow();
        homeSlideshow.setNum(param.getNum());
        homeSlideshow.setCityid(param.getCityid());
        homeSlideshow.setImg(param.getImg());
        homeSlideshow.setGotourl(param.getGotourl());

        int update = homeSlideshowMapper.update(homeSlideshow, ew);
        if (update > 0) {
            result.changeToTrue(update);
            result.setMessage("修改成功！");
        }

        LogUtil.intoLog("用户：修改了首页轮播图！" + param.getImg());

        return result;
    }

    @Override
    public RResult deleteHomeSlideshow(RResult result, DeleteHomeSlideshowParam param) {
        UpdateWrapper<HomeSlideshow> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());

        HomeSlideshow homeSlideshow = homeSlideshowMapper.selectOne(ew);
        if(null == homeSlideshow){
            result.setMessage("删除的轮播图不存在");
            return result;
        }

        int delete = homeSlideshowMapper.delete(ew);
        if (delete > 0) {
            result.changeToTrue(delete);
            result.setMessage("删除成功！");
        }
        LogUtil.intoLog("用户：删除了一条首页轮播图！" + homeSlideshow.getImg());
        return result;
    }


}
