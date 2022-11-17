package com.voc.boot.result.properties;

import com.voc.boot.result.annotation.ResponseResult;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/05/06 21:37
 */
@Data
public class ResultWrapper {

    /**
     * <p>是否启用响应结果自动包装，默认 true</p>
     * <p>局部控制请使用 {@link ResponseResult} 注解</p>
     */
    private boolean enable = true;

    /**
     * 全局包装需要忽略的类名称(完全限定名)
     */
    List<String> ignoredClass = new ArrayList<>();

    /**
     * 是否 springdoc 项目，若是则自动添加忽略的类
     */
    boolean springdoc = false;

    public void setSpringdoc(boolean springdoc) {
        this.springdoc = springdoc;
        List<String> springDoc = Arrays.asList(
                "org.springdoc.webmvc.api.OpenApiWebMvcResource",
                "org.springdoc.webmvc.api.OpenApiActuatorResource"
        );
        if (springdoc) {
            this.ignoredClass.addAll(springDoc);
        } else {
            this.ignoredClass.removeAll(springDoc);
        }
    }
}