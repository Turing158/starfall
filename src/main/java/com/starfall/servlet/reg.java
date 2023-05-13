package com.starfall.servlet;

import com.starfall.Util.ViewBaseServlet;
import com.starfall.config.sf_config;
import com.starfall.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/confirm_reg")
public class reg extends ViewBaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(sf_config.class);
        UserService userService = context.getBean("userService", UserService.class);
        HttpSession session = req.getSession();
//        session.setAttribute("reg","block");
        String user = (String) session.getAttribute("reg_user");
        String password = (String) session.getAttribute("reg_password");
        String email = (String) session.getAttribute("reg_email");
        String email_code = req.getParameter("reg_email_code");
        if(email_code.equals("")){
            session.setAttribute("code_tips","验证码不能为空");
            super.processTemplate("reg_emailCode",req,resp);
        }
        else if(!email_code.equals(session.getAttribute("email_code"))){
            session.setAttribute("code_tips","验证码错误，请仔细检查邮箱与验证码是否正确");
            super.processTemplate("reg_emailCode",req,resp);
        }
        else if(email_code.equals(session.getAttribute("email_code"))){

            char[] id = session.getId().toCharArray();
            StringBuilder id_last6 = new StringBuilder();
            for (int i=0;i < 6;i++) {
                id_last6.insert(0,id[id.length-1-i]);
            }
            String name = "新用户"+ id_last6;
            LocalDate date = LocalDate.now();
            userService.reg(user,password,name,String.valueOf(date),email);
            session.setAttribute("reg_notice","block");
            super.processTemplate("reg_emailCode",req,resp);
            session.setAttribute("reg_notice",null);
            session.setAttribute("email_code",null);
            session.setAttribute("reg_email",null);
            session.setAttribute("reg_user",null);
            session.setAttribute("reg_password",null);
            session.setAttribute("reg_tips",null);
            session.setAttribute("reg",null);
            session.setAttribute("tips","成功注册账号");
        }
    }

//    public void add_user(String user, String password, String name, String date, String email)throws SQLException{
//        Connection con = new Connection_SQL().getCon();
//        String cmd = "insert into login_web.web_user (user,password,name,date,level,email) values ('"+user+"','"+password+"','"+name+"','"+date+"',1,'"+email+"')";
//        PreparedStatement run = con.prepareStatement(cmd);
//        run.executeUpdate();
//        run.close();
//        con.close();
//    }
}
