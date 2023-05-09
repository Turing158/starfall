package com.starfall.dao;

import com.starfall.enity.Discuss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@Repository
public class DiscussDaoImpl implements DiscussDao{
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public List<Discuss> getDiscuss(int page,String user) {
        String sql = "select * from web.discuss where user=? limit ?,5";
        if(StringUtils.isEmpty(user)){
            sql = "select * from web.discuss limit ?,5";
            return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Discuss>(Discuss.class),page);
        }
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Discuss>(Discuss.class),user,page);
    }

    @Override
    public int getPage(String user) {
        String sql = "select count(*) from web.discuss where user=?";
        if(StringUtils.isEmpty(user)){
            sql = "select count(*) from web.discuss";
            return jdbcTemplate.queryForObject(sql,Integer.class);
        }
        return jdbcTemplate.queryForObject(sql,Integer.class,user);
    }

    @Override
    public void addComment(String user, String content, String date,String name) {
        String sql = "insert into web.discuss (user,content,date,name) values(?,?,?,?)";
        jdbcTemplate.update(sql,user,content,date,name);
    }
}
