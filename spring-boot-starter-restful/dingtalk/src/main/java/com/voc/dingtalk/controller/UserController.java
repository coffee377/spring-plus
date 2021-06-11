package com.voc.dingtalk.controller;

import com.voc.dingtalk.service.IUserService;
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
    private IUserService userService;

    @GetMapping("/{appName}/scan")
    public Object login(@PathVariable(name = "appName") String appName,
                        @RequestParam("code") String code,
                        @RequestParam(required = false) String state) {
        Object userInfo = userService.getUserDetailInfo(appName, code);
        return userInfo;
    }
}
