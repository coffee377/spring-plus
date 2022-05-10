package com.voc.restful.core.entity;

import com.voc.restful.core.bean.IJsonBean;

import java.io.Serializable;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/16 19:50
 */
public interface IJsonEntity<ID extends Serializable> extends IEntity<ID>, IJsonBean {
}