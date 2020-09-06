package com.jiehuihui.config;

import com.jiehuihui.admin.mapper.BlinddateMapper;
import com.jiehuihui.admin.mapper.BlinddateinfoMapper;
import com.jiehuihui.admin.mapper.FriendsMapper;
import com.jiehuihui.admin.mapper.ShopyhmdMapper;
import com.jiehuihui.admin.mapper.home.HomespecialMapper;
import com.jiehuihui.admin.mapper.shop.ShopinfoMapper;
import com.jiehuihui.admin.service.BlinddateinfoService;
import com.jiehuihui.admin.service.home.HomespecialService;
import com.jiehuihui.admin.service.shop.ShopinfoService;
import com.jiehuihui.common.utils.LogUtil;
import com.jiehuihui.web.service.MiandanService;
import com.jiehuihui.web.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;

@Component
public class JhhScheduler {

    //店铺、特价、免单、相亲
    @Autowired
    private FriendsMapper friendsMapper;

    @Autowired
    private BlinddateinfoMapper blinddateinfoMapper;

    @Autowired
    private HomespecialMapper homespecialMapper;

    @Autowired
    private ShopyhmdMapper shopyhmdMapper;

    @Autowired
    private ShopinfoMapper shopinfoMapper;


    //每隔60分钟执行一次  fixedRate = 2000
    @Scheduled(cron = "0 0/60 * * * ?")
    public void testTasks() {
        //检查所有置顶到期时间，如果过期，置顶状态，置顶时间就设置为空。
        LogUtil.intoLog("执行置顶过期清理");

        int bnum = blinddateinfoMapper.updateTopNum();
        int hnum = homespecialMapper.updateTopNum();
        int hnum2 = homespecialMapper.updateTopNum2();
        int snum = shopyhmdMapper.updateTopNum();
        int sfnum = shopinfoMapper.updateTopNum();
        int fnum = friendsMapper.updateTopNum();



    }

    //每天3：05执行
//    @Scheduled(cron = "0 05 03 ? * *")
//    public void testTasks2() {
//        System.out.println("定时任务执行时间：" + dateFormat.format(new Date()));
//    }


}