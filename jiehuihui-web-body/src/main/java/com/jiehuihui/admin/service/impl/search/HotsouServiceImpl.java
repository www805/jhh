package com.jiehuihui.admin.service.impl.search;

import com.jiehuihui.common.entity.search.Hotsou;
import com.jiehuihui.admin.mapper.search.HotsouMapper;
import com.jiehuihui.admin.service.search.HotsouService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiehuihui.admin.req.search.AddUpdateHotsouParam;
import com.jiehuihui.admin.req.search.DeleteHotsouParam;
import com.jiehuihui.admin.req.search.GetHotsouPageParam;
import com.jiehuihui.admin.vo.search.GetHotsouVO;
import com.jiehuihui.common.utils.LogUtil;
import com.jiehuihui.common.utils.OpenUtil;
import com.jiehuihui.common.utils.RResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Hotsou)表服务实现类
 *
 * @author zhuang
 * @since 2020-04-11 19:41:22
 */
@Service("hotsouService")
public class HotsouServiceImpl implements HotsouService {
    @Resource
    private HotsouMapper hotsouMapper;

    @Override
    public RResult getHotsou(RResult result) {
        UpdateWrapper<Hotsou> ew = new UpdateWrapper<>();
        ew.eq("state", 1);
        ew.orderByDesc("sortnum");
        List<Hotsou> hotsous = hotsouMapper.selectList(ew);
        result.changeToTrue(hotsous);
        return result;
    }

    @Override
    public RResult getHotsouByssid(RResult result, DeleteHotsouParam param) {
        UpdateWrapper<Hotsou> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());
        Hotsou hotsou = hotsouMapper.selectList(ew).get(0);
        if (null != hotsou) {
            result.changeToTrue(hotsou);
        }else{
            result.setMessage("获取失败，该条数据不存在");
        }
        return result;
    }

    @Override
    public RResult getHotsouPage(RResult result, GetHotsouPageParam param) {

        GetHotsouVO hotsouVO = new GetHotsouVO();

        UpdateWrapper<Hotsou> ew = new UpdateWrapper<>();
        if(StringUtils.isNotEmpty(param.getKeyword())){
            ew.like("keyword", param.getKeyword());
        }

        ew.orderByDesc("sortnum");
        ew.orderByDesc("createtime");

        Integer count = hotsouMapper.selectCount(ew);
        param.setRecordCount(count);

        Page<Hotsou> page = new Page<>(param.getCurrPage(), param.getPageSize());
        page.setTotal(count);
        IPage<Hotsou> sqCacheList = hotsouMapper.selectPage(page, ew);

        hotsouVO.setPagelist(sqCacheList.getRecords());
        hotsouVO.setPageparam(param);

        result.changeToTrue(hotsouVO);
        return result;
    }

    @Override
    public RResult addHotsou(RResult result, AddUpdateHotsouParam param) {
        //先校验添加的ssid是否已经存在，在判断添加的参数是否已经存在
        String ssid = OpenUtil.getUUID_32();
        if(StringUtils.isNoneBlank(param.getSsid())){
            UpdateWrapper<Hotsou> ew = new UpdateWrapper<>();
            ew.eq("ssid", param.getSsid());
            if(null != hotsouMapper.selectList(ew)){
                result.setMessage("该ssid已经存在，不能添加");
                return result;
            }
            ssid = param.getSsid();
        }
        UpdateWrapper<Hotsou> ew = new UpdateWrapper<>();
        ew.eq("keyword", param.getKeyword());
        List<Hotsou> TypeList = hotsouMapper.selectList(ew);
        if (null != TypeList && TypeList.size() > 0) {
            result.setMessage("该数据已经存在，不能添加");
            return result;
        }

        Hotsou hotsou = new Hotsou();
        hotsou.setKeyword(param.getKeyword());
        hotsou.setSortnum(param.getSortnum());
        hotsou.setSsid(ssid);
        hotsou.setState(param.getState());
        int insert = hotsouMapper.insert(hotsou);
        if (insert > 0) {
            result.changeToTrue(insert);
        }
        return result;
    }

    @Override
    public RResult updateHotsou(RResult result, AddUpdateHotsouParam param) {
        //先校验是否已经存在
        UpdateWrapper<Hotsou> ewcheck = new UpdateWrapper<>();
        ewcheck.eq("keyword", param.getKeyword());
        ewcheck.ne("ssid", param.getSsid());
        List<Hotsou> hotsous = hotsouMapper.selectList(ewcheck);
        if (null != hotsous && hotsous.size() > 0) {
            result.setMessage("修改的数据已存在，不能修改");
            return result;
        }

        UpdateWrapper<Hotsou> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());

        Hotsou hotsou = new Hotsou();
        hotsou.setKeyword(param.getKeyword());
        hotsou.setSortnum(param.getSortnum());
        hotsou.setState(param.getState());

        int update = hotsouMapper.update(hotsou, ew);
        if (update > 0) {
            result.changeToTrue(update);
            result.setMessage("修改成功！");
        }

        LogUtil.intoLog("用户：修改了数据！" + param.getKeyword());

        return result;
    }

    @Override
    public RResult deleteHotsou(RResult result, DeleteHotsouParam param) {
        UpdateWrapper<Hotsou> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());

        Hotsou hotsou = hotsouMapper.selectOne(ew);
        if(null == hotsou){
            result.setMessage("删除的数据不存在");
            return result;
        }

        int delete = hotsouMapper.delete(ew);
        if (delete > 0) {
            result.changeToTrue(delete);
            result.setMessage("删除成功！");
        }
        LogUtil.intoLog("用户：删除了一条数据！" + hotsou.getKeyword());
        return result;
    }
}