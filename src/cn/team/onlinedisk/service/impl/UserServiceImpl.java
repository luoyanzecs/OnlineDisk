package cn.team.onlinedisk.service.impl;

import cn.team.onlinedisk.dao.UserFileInfoDao;
import cn.team.onlinedisk.dao.impl.UserDaoImpl;
import cn.team.onlinedisk.dao.impl.UserFileInfoDaoImpl;
import cn.team.onlinedisk.domain.User;
import cn.team.onlinedisk.service.UserService;

import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author luoyanze
 * @Date 2020/7/29 9:29 下午
 * @Version 1.0
 */


public class UserServiceImpl implements UserService {

    @Override
    public boolean register(User user) {
        UserDaoImpl userDao = new UserDaoImpl();
        UserFileInfoDao ufiDao = new UserFileInfoDaoImpl();
        ufiDao.creatTable(user);
        System.out.println(user.getUsername() + "注册成功");
        return userDao.addNewUser(user);
    }

    @Override
    public int register(List<User> list) {
        UserDaoImpl userDao = new UserDaoImpl();
        UserFileInfoDao ufiDao = new UserFileInfoDaoImpl();
        int count = 0;
        for (User user : list) {
            if (userDao.addNewUser(user)){
                ufiDao.creatTable(user);
                System.out.println(user.getUsername() + "注册成功");
                count++;
            }
        }
        return count;
    }

    @Override
    public boolean login(User user) {
        UserDaoImpl userDao = new UserDaoImpl();
        return userDao.isInfoRight(user);
    }

    @Override
    public boolean modify(User user) {
        return false;
    }
}
