package com.starfall.dao;

import com.starfall.enity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao{
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public String Login(String user) {
    String sql = "select password from web.user where user=?";
    try{
        return jdbcTemplate.queryForObject(sql,String.class,user);
    }
    catch (EmptyResultDataAccessException e){
        return null;
    }
    }

    @Override
    public String getName(String user) {
        String sql = "select name from web.user where user=?";
        return jdbcTemplate.queryForObject(sql,String.class,user);
    }

    @Override
    public User getInfo(String user) {
        String sql = "select * from web.user where user=?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),user);
    }

    @Override
    public void Reg(String user, String password, String name, String date, String email) {
        String sql = "insert into web.user (user,password,name,date,email,level) values (?,?,?,?,?,1)";
        jdbcTemplate.update(sql,user,password,name,date,email);
    }

    @Override
    public boolean checkUserRepeat(String user) {
        String sql = "select count(*) from web.user where user=?";
        Integer ints = jdbcTemplate.queryForObject(sql,Integer.class,user);
        return ints != 0;
    }

    @Override
    public boolean checkEmailRepeat(String email) {
        String sql = "select count(*) from web.user where email=?";
        Integer ints = jdbcTemplate.queryForObject(sql,Integer.class,email);
        return ints != 0;
    }

    @Override
    public void updateInformation(String user,String name,String introduce) {
        String sql = "update web.user set name=?, introduce=? where user=?";
        jdbcTemplate.update(sql,name,introduce,user);
    }

    @Override
    public String checkOldPassword(String user) {
        String sql = "select password from web.user where user=?";
        return jdbcTemplate.queryForObject(sql,String.class,user);
    }

    @Override
    public void setNewPassword(String user, String password) {
        String sql = "update web.user set password=? where user=?";
        jdbcTemplate.update(sql,password,user);
    }
}
