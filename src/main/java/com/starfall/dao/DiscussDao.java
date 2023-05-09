package com.starfall.dao;

import com.starfall.enity.Discuss;

import java.util.List;

public interface DiscussDao {
    List<Discuss> getDiscuss(int page,String user);
    int getPage(String user);
    void addComment(String user,String content,String date,String name);
}
