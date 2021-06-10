package com.voc.dingtalk;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/17 18:48
 */
@Getter
@AllArgsConstructor
public enum UrlConst {

    /* ------- 获取凭证 ------- */

    /**
     * 获取调用企业接口凭证
     */
    GET_TOKEN("/gettoken"),
    /**
     * 获取 jsapi_ticket
     */
    GET_JSAPI_TICKET("/get_jsapi_ticket"),

    /* ------- 获取凭证 ------- */

    /* ------- 身份验证 ------- */
    /**
     * 通过免登码获取用户信息
     */
    USER_GET_USER_INFO("/user/getuserinfo"),
    /**
     * 通过免登码获取用户信息(v2)
     */
    V2_USER_GET_USER_INFO("/v2/user/getuserinfo"),
    /**
     * 通过临时授权码获取用户信息
     */
    SNS_GET_USER_INFO_BY_CODE("/sns/getuserinfo_bycode"),

    /* ------- 身份验证 ------- */



    /* ------- 通讯录管理 - 用户管理 ------- */
    /**
     * 根据 unionid 获取用户 userid
     */
    TOP_API_USER_GET_BY_UNION_ID("/topapi/user/getbyunionid"),
    /**
     * 根据 userid 获取用户详情
     */
    TOP_API_V2_USER_GET("/topapi/v2/user/get"),
    /* ------- 通讯录管理 - 用户管理 ------- */



    ;



    private final String url;

}
