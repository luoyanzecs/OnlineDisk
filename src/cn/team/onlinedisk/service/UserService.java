package cn.team.onlinedisk.service;

import cn.team.onlinedisk.domain.User;

public interface UserService {

    /**
     * 用户注册功能
     *
     * @param user: 用户信息类
     * @return: java.lang.Boolean
     */
    Boolean register(User user);

    /**
     * 用户登陆功能
     *
     * @param user: 用户信息类
     * @return: java.lang.Boolean
     */
    Boolean login(User user);

    /**
     * 修改用户信息
     *
     * @param user: 用户信息类
     * @return: java.lang.Boolean
     */
    Boolean modify(User user);
}
