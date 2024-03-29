package com.voc.restful.security.core.authentication.token.api;

import com.voc.restful.security.core.authentication.token.model.TokenResult;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;

import java.time.Instant;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/16 09:24
 */
public interface TokenGenerator {

    /**
     * OAuth2 令牌生成
     *
     * @param userDetails 用户信息
     * @return OAuth2AccessTokenResponse
     */
    OAuth2AccessTokenResponse tokenResponse(UserDetails userDetails);

    /**
     * OAuth2 令牌生成
     *
     * @param userDetails 用户信息
     * @param issuedAt    令牌签发时间
     * @return OAuth2AccessTokenResponse
     */
    OAuth2AccessTokenResponse tokenResponse(Instant issuedAt, UserDetails userDetails);

    /**
     * 生成令牌
     *
     * @param issuedAt        签发时间
     * @param userDetails     用户信息
     * @param onlyAccessToken 仅生成访问令牌
     * @return TokenResult
     */
    TokenResult create(Instant issuedAt, UserDetails userDetails, boolean onlyAccessToken);

}
