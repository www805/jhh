package com.jiehuihui.admin.service.impl.shop;

import com.jiehuihui.common.entity.shop.Shoplabel;
import com.jiehuihui.admin.mapper.shop.ShoplabelMapper;
import com.jiehuihui.admin.service.shop.ShoplabelService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiehuihui.admin.req.shop.AddUpdateShoplabelParam;
import com.jiehuihui.admin.req.shop.DeleteShoplabelParam;
import com.jiehuihui.admin.req.shop.GetShoplabelPageParam;
import com.jiehuihui.admin.vo.shop.GetShoplabelVO;
import com.jiehuihui.common.utils.LogUtil;
import com.jiehuihui.common.utils.OpenUtil;
import com.jiehuihui.common.utils.RResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Shoplabel)表服务实现类
 *
 * @author zhuang
 * @since 2020-04-11 12:41:26
 */
@Service("shoplabelService")
public class ShoplabelServiceImpl implements ShoplabelService {

    @Resource
    private ShoplabelMapper shoplabelMapper;

    @Override
    public RResult getShoplabel(RResult result) {
        UpdateWrapper<Shoplabel> ew = new UpdateWrapper<>();
        ew.eq("state", 1);
        ew.orderByDesc("sortnum");
        List<Shoplabel> shoplabels = shoplabelMapper.selectList(ew);
        result.changeToTrue(shoplabels);
        return result;
    }

    @Override
    public RResult getShoplabelByssid(RResult result, DeleteShoplabelParam param) {
        UpdateWrapper<Shoplabel> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());
        Shoplabel shoplabel = shoplabelMapper.selectList(ew).get(0);
        if (null != shoplabel) {
            result.changeToTrue(shoplabel);
        }else{
            result.setMessage("获取失败，该条数据不存在");
        }
        return result;
    }

    @Override
    public RResult getShoplabelPage(RResult result, GetShoplabelPageParam param) {

        GetShoplabelVO shoplabelVO = new GetShoplabelVO();

        UpdateWrapper<Shoplabel> ew = new UpdateWrapper<>();
        if(StringUtils.isNotEmpty(param.getLabelname())){
            ew.like("labelname", param.getLabelname());
        }

        ew.orderByDesc("sortnum");

        Integer count = shoplabelMapper.selectCount(ew);
        param.setRecordCount(count);

        Page<Shoplabel> page = new Page<>(param.getCurrPage(), param.getPageSize());
        page.setTotal(count);
        IPage<Shoplabel> sqCacheList = shoplabelMapper.selectPage(page, ew);

        shoplabelVO.setPagelist(sqCacheList.getRecords());
        shoplabelVO.setPageparam(param);

        result.changeToTrue(shoplabelVO);
        return result;
    }

    @Override
    public RResult addShoplabel(RResult result, AddUpdateShoplabelParam param) {
        //先校验添加的ssid是否已经存在，在判断添加的参数是否已经存在
        String ssid = OpenUtil.getUUID_32();
        if(StringUtils.isNoneBlank(param.getSsid())){
            UpdateWrapper<Shoplabel> ew = new UpdateWrapper<>();
            ew.eq("ssid", param.getSsid());
            if(null != shoplabelMapper.selectList(ew)){
                result.setMessage("该ssid已经存在，不能添加");
                return result;
            }
            ssid = param.getSsid();
        }
        UpdateWrapper<Shoplabel> ew = new UpdateWrapper<>();
        ew.eq("labelname", param.getLabelname());
        List<Shoplabel> TypeList = shoplabelMapper.selectList(ew);
        if (null != TypeList && TypeList.size() > 0) {
            result.setMessage("该数据已经存在，不能添加");
            return result;
        }

        Shoplabel shoplabel = new Shoplabel();
        shoplabel.setLabelname(param.getLabelname());
        shoplabel.setSortnum(param.getSortnum());
        shoplabel.setSsid(ssid);
        shoplabel.setState(param.getState());
        int insert = shoplabelMapper.insert(shoplabel);
        if (insert > 0) {
            result.changeToTrue(insert);
        }
        return result;
    }

    @Override
    public RResult updateShoplabel(RResult result, AddUpdateShoplabelParam param) {
        //先校验是否已经存在
        UpdateWrapper<Shoplabel> ewcheck = new UpdateWrapper<>();
        ewcheck.eq("labelname", param.getLabelname());
        ewcheck.ne("ssid", param.getSsid());
        List<Shoplabel> shoplabels = shoplabelMapper.selectList(ewcheck);
        if (null != shoplabels && shoplabels.size() > 0) {
            result.setMessage("修改的数据已存在，不能修改");
            return result;
        }

        UpdateWrapper<Shoplabel> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());

        Shoplabel shoplabel = new Shoplabel();
        shoplabel.setLabelname(param.getLabelname());
        shoplabel.setSortnum(param.getSortnum());
        shoplabel.setState(param.getState());

        int update = shoplabelMapper.update(shoplabel, ew);
        if (update > 0) {
            result.changeToTrue(update);
            result.setMessage("修改成功！");
        }

        LogUtil.intoLog("用户：修改了数据！" + param.getLabelname());

        return result;
    }

    @Override
    public RResult deleteShoplabel(RResult result, DeleteShoplabelParam param) {
        UpdateWrapper<Shoplabel> ew = new UpdateWrapper();
        ew.eq("ssid", param.getSsid());

        Shoplabel shoplabel = shoplabelMapper.selectOne(ew);
        if(null == shoplabel){
            result.setMessage("删除的数据不存在");
            return result;
        }

        int delete = shoplabelMapper.delete(ew);
        if (delete > 0) {
            result.changeToTrue(delete);
            result.setMessage("删除成功！");
        }
        LogUtil.intoLog("用户：删除了一条数据！" + shoplabel.getLabelname());
        return result;
    }
}