package com.starfall.service;

import com.starfall.dao.DiscussDao;
import com.starfall.enity.Discuss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussService {
    @Autowired
    private DiscussDao discussDao;
    public List<Discuss> getDiscuss(int page,String user){
        return discussDao.getDiscuss((page-1)*5,user);
    }
    public int getPage(String user){
        return (discussDao.getPage(user)+4)/5;
    }
    public void addComment(String user, String content, String date,String name){
        discussDao.addComment(user,content,date,name);
    }
}
