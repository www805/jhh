package com.jiehuihui.gateway.filter;

import com.jiehuihui.gateway.util.VersionJhh;
import com.jiehuihui.gateway.utils.Code;
import com.jiehuihui.gateway.utils.DateUtil;
import org.json.JSONObject;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**

 * @ClassName FallBack

 * @Description 我们提供咨询和培训服务，关于本文有任何困惑，请关注并联系“码农星球”

 * @Author 码农星球

 **/

@RestController
@RequestMapping("/fallback")
public class FallBack {

    @RequestMapping("")
    public String fallback(){
        JSONObject object = new JSONObject();
        object.put("version", VersionJhh.v1);
        object.put("actioncode", Code.FAIL.toString());
        object.put("data", "");
        object.put("endtime", DateUtil.getDateAndMinute());
        object.put("message", "当前访问人数过多，请稍后...");
        return object.toString();
    }

}