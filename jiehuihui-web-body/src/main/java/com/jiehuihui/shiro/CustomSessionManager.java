package com.jiehuihui.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;


public class CustomSessionManager extends DefaultWebSessionManager {

    /**
     * 头信息中具有sessionid
     *      请求头：Authorization: sessionid
     *
     *      指定sessionId的获取方式
     * @param request
     * @param response
     * @return
     */
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {

        String id = WebUtils.toHttp(request).getHeader("Authorization");
        if(StringUtils.isBlank(id)){
            //如果没有携带，生成新的sessionId
            return super.getSessionId(request, response);
        }else{
            //返回sessionId
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, "header");
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return id;
        }
    }
}
