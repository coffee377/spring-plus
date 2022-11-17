package com.voc.boot.result.properties;

import lombok.Data;

/**
 * {@link com.voc.boot.result.Result} 序列化后字段名称配置
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/04/25 13:40
 */
@Data
public class JsonField {

    /**
     * 请求是否成功字段名称
     */
    private String success = "success";

    /**
     * 响应状态字段名称
     */
    private String code = "code";

    /**
     * 错误信息字段名称
     */
    private String message = "message";

    /**
     * 数据字段名称
     */
    private String data = "data";

    /**
     * 总记录数字段名称
     */
    private String total = "total";
}