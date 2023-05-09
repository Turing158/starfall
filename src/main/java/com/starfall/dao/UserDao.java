package com.starfall.dao;

import com.starfall.enity.User;

public interface UserDao {
    String Login(String user);
    String getName(String user);
    User getInfo(String user);
    void Reg(String user, String password, String name, String date, String email);
    boolean checkUserRepeat(String user);
    boolean checkEmailRepeat(String email);
    void updateInformation(String user,String name,String introduce);
    String checkOldPassword(String user);
    void setNewPassword(String user,String password);
}
