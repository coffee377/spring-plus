package com.voc.security.core.authentication.restful;

import com.voc.restful.core.response.impl.BaseBizStatus;
import com.voc.restful.core.response.impl.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/03/19 14:11
 */
@Slf4j
public class RestfulAuthenticationEntryPoint extends ResponseHandler implements AuthenticationEntryPoint {

    /**
     * 认证异常处理
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param e        AuthenticationException
     * @throws IOException      IOException
     * @throws ServletException ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        if (log.isDebugEnabled()) {
            log.debug("访问资源 {} 需要用户身份认证", request.getRequestURL().toString());
        }

        this.setBizStatus(BaseBizStatus.UNAUTHORIZED);
        this.write(response);
//        if (e instanceof InsufficientAuthenticationException) {
//            failure = Result.failure(BaseBizStatus.UNAUTHORIZED);
//        } else if (e instanceof InvalidBearerTokenException) {
//            failure = Result.failure(BaseBizStatus.INVALID_BEARER_TOKEN);
//        } else if (e instanceof UsernameNotFoundException) {
//            failure = Result.failure(BaseBizStatus.USERNAME_NOT_FOUND);
//        } else if (e instanceof BadCredentialsException) {
//            failure = Result.failure(BaseBizStatus.BAD_CREDENTIALS);
//        }
//        if (log.isDebugEnabled()) {
//            log.debug("响应 JSON 数据为：{}", failure.toString());
//        }
//        response.getWriter().write(failure.toString());

    }

}
