package com.voc.api.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/24 09:46
 */
@Getter
@Setter
@NoArgsConstructor
public class Demo {
    private String name;
    private Integer age;
    private LocalDateTime birthday;
    private LocalDate birthday2;
    private LocalTime birthday3;
    private Date birthday4;
    private String other;
    private int i1;
    private Integer i2;
    private boolean b1;
    private Boolean b2;
    private List<Object> list;
    private Set<Object> set;
    private Map<String, Object> map;
}
