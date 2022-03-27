package com.voc.restful.core.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/03/27 18:52
 */
@Getter
@Setter
public class BizException extends RuntimeException {
    private long code;
    private HttpStatus httpStatus;

    /**
     * @param code 错误编码
     * @param message 错误信息
     * @since 0.0.4
     */
    public BizException(long code, String message) {
        this(code, message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public BizException(IBizStatus bizStatus) {
        this(bizStatus.getCode(), bizStatus.getMessage(), bizStatus.getStatus());
    }

    public BizException(long code, String message, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }

}
