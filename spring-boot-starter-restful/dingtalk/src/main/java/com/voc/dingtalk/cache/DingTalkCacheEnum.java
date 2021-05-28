package com.voc.dingtalk.cache;

import java.time.Duration;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/18 18:19
 */
public enum DingTalkCacheEnum implements ICache, DingTalkCache {

    /**
     *
     */
    ACCESS_TOKEN(DingTalkCache.ACCESS_TOKEN, Duration.ofSeconds(7200)),
    JS_TICKET(DingTalkCache.JS_TICKET, Duration.ofSeconds(7200));

    private final String name;

    private final Duration expirationTime;

    DingTalkCacheEnum(String name, Duration expirationTime) {
        this.name = name;
        this.expirationTime = expirationTime;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Duration getExpirationTime() {
        return expirationTime;
    }

}