package api.entity;

import api.bean.Identify;

import java.io.Serializable;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/07/16 20:07
 */
public interface IEntity<ID extends Serializable> extends Identify<ID>, ICommonEntity {
}
