package com.voc.restful.core.entity.impl;

import com.voc.restful.core.entity.BaseJsonEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/25 11:36
 */
@Getter
@Setter
public class OAuth2Params extends BaseJsonEntity {
    private String clientId;
}
