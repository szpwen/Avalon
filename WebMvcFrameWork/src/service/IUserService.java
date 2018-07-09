package service;

import domain.User;
import exception.UserExistException;


public interface IUserService {
    /*
    提供注册服务
    @param user
    @throw UserExistException
     */
    void registerUser(User user) throws UserExistException;

    /*
    提供登陆服务
    @param userName
    @param userPassword
    @return
     */
    User loginUser(String userName, String userPassword);
}