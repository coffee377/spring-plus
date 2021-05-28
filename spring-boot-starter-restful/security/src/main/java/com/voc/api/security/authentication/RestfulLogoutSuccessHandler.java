package com.voc.api.security.authentication;

import com.voc.restful.core.response.Result;
import com.voc.api.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/25 09:12
 */
@Slf4j
@Component
public class RestfulLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // TODO: 2020/9/25 9:17 注销处理
        log.debug("{} - 退出系统", authentication);
        if (RequestUtil.isRestfulRequest(request)) {
            response.setContentType("application/json;charset=utf-8");
            response.setCharacterEncoding("utf-8");
            response.setStatus(HttpStatus.OK.value());
            response.getWriter().write(Result.success(null).toString());
        } else {
            super.onLogoutSuccess(request, response, authentication);
        }
    }
}