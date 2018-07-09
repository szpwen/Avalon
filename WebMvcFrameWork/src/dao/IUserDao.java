package dao;

import domain.User;

public interface IUserDao {
    /*
    根据用户名和密码来查找用户
    @param userName
    @param userPassword
     @return 查到到的用户
     */
    User find(String userName,String userPassword);

    /*
    添加用户
    @param user
     */
    void add(User user);

    /*
    根据用户名来查找用户
    @return 查到的用户
     */
    User find(String userName);
}
