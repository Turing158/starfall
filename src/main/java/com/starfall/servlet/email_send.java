package com.starfall.servlet;


import com.starfall.Util.GetCode;
import com.starfall.Util.MailUtil;
import com.starfall.Util.ViewBaseServlet;
import com.starfall.config.sf_config;
import com.starfall.service.UserService;
import org.apache.commons.mail.EmailException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/verify_code")
public class email_send extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(sf_config.class);
        UserService userService = context.getBean("userService", UserService.class);
        HttpSession session = req.getSession();
        GetCode getCode = new GetCode();
        MailUtil mail = new MailUtil();
        String email_code = "";
        session.setAttribute("reg","block");
        String user = req.getParameter("vreg_user");
        String password = req.getParameter("vreg_password");
        String email = req.getParameter("vreg_email");
        String code = req.getParameter("vreg_code");
        session.setAttribute("reg_user",user);
        session.setAttribute("reg_password",password);
        session.setAttribute("reg_email",email);
        session.setAttribute("code_tips",".");
        session.setAttribute("reg_notice",null);
        boolean user_flag = userService.checkUserRepeat(user);
        boolean flag = userService.checkEmailRepeat(email);
        session.setAttribute("submit","button");
        if ((boolean) session.getAttribute("enter_flag")){
            email_code = getCode.getcode();
            session.setAttribute("email_code",email_code);
            try {
                mail.reg_mail(email,email_code);
            } catch (EmailException e) {
                e.printStackTrace();
            }
            session.setAttribute("code_tips","邮箱已重新发送，请注意查收");
            super.processTemplate("reg_emailCode",req,resp);
        }
        else{
            if(Objects.equals(user,"")){
                session.setAttribute("reg_tips","用户名不能为空");
                session.setAttribute("submit","submit");
                super.processTemplate("login",req,resp);
            }
            else if(Objects.equals(password,"")){
                session.setAttribute("reg_tips","密码不能为空");
                session.setAttribute("submit","submit");
                super.processTemplate("login",req,resp);
            }
            else if(email == null){
                session.setAttribute("reg_tips","邮箱不能为空");
                session.setAttribute("submit","submit");
                super.processTemplate("login",req,resp);
            }
            else if(user.length() < 3){
                session.setAttribute("reg_tips","用户名不能小于3个字符");
                session.setAttribute("submit","submit");
                super.processTemplate("login",req,resp);
            }
            else if(password.length() < 6){
                session.setAttribute("reg_tips","密码不能小于6个字符");
                session.setAttribute("submit","submit");
                super.processTemplate("login",req,resp);
            }
            else if(!user_flag){
                session.setAttribute("reg_tips","用户已存在");
                session.setAttribute("submit","submit");
                super.processTemplate("login",req,resp);
            }
            else if(!flag){
                session.setAttribute("reg_tips","邮箱已存在");
                session.setAttribute("submit","button");
                super.processTemplate("login",req,resp);
            }
            else if(flag && user_flag && Objects.equals(code,session.getAttribute("code"))){
                session.setAttribute("enter_flag",true);
                email_code = getCode.getcode();
                session.setAttribute("email_code",email_code);
                try {
                    mail.reg_mail(email,email_code);
                } catch (EmailException e) {
                    e.printStackTrace();
                }
                super.processTemplate("reg_emailCode",req,resp);
            }
            else if (!Objects.equals(code,session.getAttribute("code"))){
                session.setAttribute("reg_tips","验证码错误");
                session.setAttribute("submit","submit");
                super.processTemplate("login",req,resp);
            }
        }
        session.setAttribute("code",null);
    }

//    public boolean check_repeat(String user)throws SQLException {
//        Connection con = new Connection_SQL().getCon();
//        String cmd = "select user from login_web.web_user where user=\""+user+"\"";
//        boolean flag = true;
//        PreparedStatement run = con.prepareStatement(cmd);
//        ResultSet result = run.executeQuery(cmd);
//        while(result.next()){
//            flag = false;
//        }
//        return flag;
//    }
//
//    public boolean check_repeat_email(String email)throws SQLException {
//        Connection con = new Connection_SQL().getCon();
//        String cmd = "select email from login_web.web_user where email=\""+email+"\"";
//        PreparedStatement run = con.prepareStatement(cmd);
//        ResultSet result = run.executeQuery(cmd);
//        return result.next();
//    }
}
