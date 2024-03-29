package com.voc.boot.dict.autoconfigure;

import com.voc.boot.dict.DictItemConverterConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/08/06 22:45
 */
@Configuration
@Import({DictItemConverterConfiguration.class})
public class DictAutoConfiguration {
}
