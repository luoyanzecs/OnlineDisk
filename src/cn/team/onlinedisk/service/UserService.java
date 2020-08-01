package cn.team.onlinedisk.service;

import cn.team.onlinedisk.domain.User;

import java.util.List;

public interface UserService {

    /**
     * 用户注册功能
     *
     * @param user: 用户信息类
     * @return: java.lang.Boolean
     */
    boolean register(User user);

    /**
     *
     *
     * @param list: 用户集合
     * @return: int 返回注册成功的数量
     */
    int register(List<User> list);

    /**
     * 用户登陆功能
     * 如果登陆成功: 1.则创建一个他自己的数据库（如果已经创建了就不需要创建 if not exist）
     *              2.将用户的信息存储在缓存里面
     *
     * @param user: 用户信息类
     * @return: java.lang.Boolean
     */
    boolean login(User user);

    /**
     * 修改用户信息
     *
     * @param user: 用户信息类
     * @return: java.lang.Boolean
     */
    boolean modify(User user);
}
