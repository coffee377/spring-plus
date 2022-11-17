package com.voc.dingtalk.controller;

import com.dingtalk.api.response.OapiV2UserGetResponse;
import com.voc.dingtalk.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/04/21 21:27
 */
@RestController("dingTalkUserController")
@RequestMapping("/dingtalk")
public class UserController {

    @Resource
    private UserService dingTalkUserService;

    @GetMapping("/{appName}/scan")
    public Object login(@PathVariable(name = "appName") String appName,
                        @RequestParam("code") String code,
                        @RequestParam(required = false) String state) {
        Object userInfo = dingTalkUserService.getUserDetailInfo(appName, code);
        return userInfo;
    }

    @GetMapping("/user/info")
    public Object getUserInfo() {
        OapiV2UserGetResponse.UserGetResponse info = dingTalkUserService.getUserDetailInfo("", "", "");
        return info;
    }
}
