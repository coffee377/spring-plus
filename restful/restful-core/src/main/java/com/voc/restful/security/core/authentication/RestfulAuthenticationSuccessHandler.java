package com.voc.restful.security.core.authentication;

import com.voc.restful.core.response.Result;
import com.voc.restful.core.response.impl.BaseBizStatus;
import com.voc.restful.core.response.impl.ResponseHandler;
import com.voc.restful.security.core.event.LoginSuccessEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2017/12/27 17:58
 */
@Slf4j
public class RestfulAuthenticationSuccessHandler extends ResponseHandler implements AuthenticationSuccessHandler,
        ApplicationContextAware,
        ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    private ApplicationContext applicationContext;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        publisher.publishEvent(new LoginSuccessEvent(authentication, "用户登录成功"));
        if (authentication instanceof OAuth2AccessTokenAuthenticationToken) {
            Object principal = authentication.getPrincipal();
            log.debug("{}", principal);
            String name = authentication.getName();
            log.debug("{}", name);
        }

        Result result = Result.success("");
        this.setResult(result);

        this.setBizStatus(BaseBizStatus.OK);

        this.write(response);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

//    private TokenResult genToken(Authentication authentication) {
//        Object principal = authentication.getPrincipal();
//        UserDetails userDetails;
//        if (principal instanceof UserDetails) {
//            userDetails = (UserDetails) principal;
//        } else {
//            String username = authentication.getName();
//            UserDetailsService userDetailsService = applicationContext.getBean(UserDetailsService.class);
//            try {
//                userDetails = userDetailsService.loadUserByUsername(username);
//            } catch (UsernameNotFoundException ignored) {
//                return null;
//            }
//        }
//        TokenGenerator tokenGenerator = applicationContext.getBean(TokenGenerator.class);
//        TokenProperties tokenProperties = applicationContext.getBean(TokenProperties.class);
//        return tokenGenerator.create(Instant.now(), userDetails, !tokenProperties.isDualToken());
//    }

}
