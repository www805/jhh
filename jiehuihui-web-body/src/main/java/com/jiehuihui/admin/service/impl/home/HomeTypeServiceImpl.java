package com.jiehuihui.admin.service.impl.home;

import com.jiehuihui.admin.mapper.home.HomeTypeMapper;
import com.jiehuihui.admin.req.home.AddUpdateHomeTypeParam;
import com.jiehuihui.admin.req.home.DeleteHomeTypeParam;
import com.jiehuihui.admin.req.home.GetHomeTypePageParam;
import com.jiehuihui.admin.service.home.HomeTypeService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiehuihui.admin.vo.home.GetHomeTypePageVO;
import com.jiehuihui.common.entity.home.HomeType;
import com.jiehuihui.common.utils.LogUtil;
import com.jiehuihui.common.utils.OpenUtil;
import com.jiehuihui.common.utils.RResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (HomeType)表服务实现类
 *
 * @author zhuang
 * @since 2020-04-11 20:58:44
 */
@Service("HomeTypeService")
public class HomeTypeServiceImpl implements HomeTypeService {

    @Resource
    private HomeTypeMapper hometypeMapper;

    @Override
    public RResult getHomeType(RResult result) {
        UpdateWrapper<HomeType> ew = new UpdateWrapper<>();
        ew.eq("state", 1);
        ew.orderByDesc("num");
        List<HomeType> hometypes = hometypeMapper.selectList(ew);
        result.changeToTrue(hometypes);
        return result;
    }

    @Override
    public RResult getHomeTypeByssid(RResult result, DeleteHomeTypeParam param) {
        UpdateWrapper<HomeType> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());
        HomeType hometype = hometypeMapper.selectList(ew).get(0);
        if (null != hometype) {
            result.changeToTrue(hometype);
        }else{
            result.setMessage("获取失败，该条数据不存在");
        }
        return result;
    }

    @Override
    public RResult getHomeTypePage(RResult result, GetHomeTypePageParam param) {

        GetHomeTypePageVO hometypeVO = new GetHomeTypePageVO();

        UpdateWrapper<HomeType> ew = new UpdateWrapper<>();
        if(StringUtils.isNotEmpty(param.getKeyword())){
            ew.like("h.keyword", param.getKeyword());
        }
        if(StringUtils.isNotEmpty(param.getCityid())){
            ew.eq("h.cityid", param.getCityid());
        }

        ew.orderByDesc("h.num");
        ew.orderByDesc("h.createtime");

        Integer count = hometypeMapper.selectHomeTypeCount(ew);
        param.setRecordCount(count);

        Page<HomeType> page = new Page<>(param.getCurrPage(), param.getPageSize());
        page.setTotal(count);
        IPage<HomeType> sqCacheList = hometypeMapper.getHomeTypePage(page, ew);

        hometypeVO.setPagelist(sqCacheList.getRecords());
        hometypeVO.setPageparam(param);

        result.changeToTrue(hometypeVO);
        return result;
    }

    @Override
    public RResult addHomeType(RResult result, AddUpdateHomeTypeParam param) {
        //先校验添加的ssid是否已经存在，在判断添加的参数是否已经存在
        String ssid = OpenUtil.getUUID_32();
        if(StringUtils.isNoneBlank(param.getSsid())){
            UpdateWrapper<HomeType> ew = new UpdateWrapper<>();
            ew.eq("ssid", param.getSsid());
            if(null != hometypeMapper.selectList(ew) && hometypeMapper.selectList(ew).size() > 0){
                result.setMessage("该ssid已经存在，不能添加");
                return result;
            }
            ssid = param.getSsid();
        }
        UpdateWrapper<HomeType> ew = new UpdateWrapper<>();
        ew.eq("keyword", param.getKeyword());
        ew.eq("cityid", param.getCityssid());
        List<HomeType> TypeList = hometypeMapper.selectList(ew);
        if (null != TypeList && TypeList.size() > 0) {
            result.setMessage("该数据已经存在，不能添加");
            return result;
        }

        HomeType hometype = new HomeType();
        hometype.setKeyword(param.getKeyword());
        hometype.setIco(param.getIco());
        hometype.setNum(param.getNum());
        hometype.setSsid(ssid);
        hometype.setCityid(param.getCityssid());
        hometype.setGotourl(param.getGotourl());
        hometype.setState(param.getState());
        int insert = hometypeMapper.insert(hometype);
        if (insert > 0) {
            result.changeToTrue(insert);
        }
        return result;
    }

    @Override
    public RResult updateHomeType(RResult result, AddUpdateHomeTypeParam param) {
        //先校验是否已经存在
        UpdateWrapper<HomeType> ewcheck = new UpdateWrapper<>();
        ewcheck.eq("keyword", param.getKeyword());
        ewcheck.ne("ssid", param.getSsid());
        List<HomeType> hometypes = hometypeMapper.selectList(ewcheck);
        if (null != hometypes && hometypes.size() > 0) {
            result.setMessage("修改的数据已存在，不能修改");
            return result;
        }

        UpdateWrapper<HomeType> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());

        HomeType hometype = new HomeType();
        hometype.setKeyword(param.getKeyword());
        hometype.setNum(param.getNum());
        hometype.setCityid(param.getCityssid());
        hometype.setIco(param.getIco());
        hometype.setGotourl(param.getGotourl());
        hometype.setState(param.getState());

        int update = hometypeMapper.update(hometype, ew);
        if (update > 0) {
            result.changeToTrue(update);
            result.setMessage("修改成功！");
        }

        LogUtil.intoLog("用户：修改了数据！" + param.getKeyword());

        return result;
    }

    @Override
    public RResult deleteHomeType(RResult result, DeleteHomeTypeParam param) {
        UpdateWrapper<HomeType> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());

        HomeType hometype = hometypeMapper.selectOne(ew);
        if(null == hometype){
            result.setMessage("删除的数据不存在");
            return result;
        }

        int delete = hometypeMapper.delete(ew);
        if (delete > 0) {
            result.changeToTrue(delete);
            result.setMessage("删除成功！");
        }
        LogUtil.intoLog("用户：删除了一条数据！" + hometype.getKeyword());
        return result;
    }
}