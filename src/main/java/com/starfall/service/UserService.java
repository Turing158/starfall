package com.starfall.service;

import com.starfall.dao.UserDao;
import com.starfall.enity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    public String login(String user){
        return userDao.Login(user);
    }
    public String getName(String user){
        return userDao.getName(user);
    }
    public User getInfo(String user){
        return userDao.getInfo(user);
    }
    public void reg(String user, String password, String name, String date, String email){
        userDao.Reg(user,password,name,date,email);
    }
    public boolean checkUserRepeat(String user){
        return userDao.checkUserRepeat(user);
    }
    public boolean checkEmailRepeat(String email){
        return userDao.checkEmailRepeat(email);
    }
    public void updateInformation(String user,String name,String introduce){
        userDao.updateInformation(user,name,introduce);
    }
    public boolean checkOldPassword(String user,String password) {
        return userDao.checkOldPassword(user).equals(password);
    }
    public void setNewPassword(String user, String password){
        userDao.setNewPassword(user,password);
    }

}
