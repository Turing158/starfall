package com.starfall.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection_SQL {
    Connection con;

    public Connection getCon() throws SQLException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_web?","root","ice13672682@");
        return con;
    }
}
