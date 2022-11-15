package com.voc.security.oauth2.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.voc.common.api.entity.impl.BaseEntity;
import com.voc.common.api.entity.impl.BasePersistEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/11/08 15:43
 */
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@TableName(value = "oauth2_refresh_token")
public class OAuth2RefreshToken extends BasePersistEntity<String> {

    /**
     * 授权 ID
     */
    private String authorizedId;

    /**
     * 刷新令牌
     */
    private String refreshToken;

    /**
     * 签发时间
     */
    private Instant issuedAt;

    /**
     * 过期时间
     */
    private Instant expiresAt;

    /**
     * 元数据
     */
    private Object metadata;

}
