package com.starfall.enity;

public class Discuss {
    private String user;
    private String name;
    private String content;
    private String date;

    @Override
    public String toString() {
        return "Discuss{" +
                "user='" + user + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    Discuss(){}
    public Discuss(String user, String name, String content, String date) {
        this.user = user;
        this.name = name;
        this.content = content;
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
