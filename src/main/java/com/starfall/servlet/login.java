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
import java.util.Objects;

@WebServlet("/confirm_login")
public class login extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(sf_config.class);
        UserService userService = context.getBean("userService", UserService.class);
        HttpSession session = req.getSession();
        String user = req.getParameter("user");
        String password = req.getParameter("password");
        String code = req.getParameter("login_code");
        session.setAttribute("user",user);
        String flag = userService.login(user);
        session.setAttribute("tips"," ");
        if(Objects.equals(code, "")){
            session.setAttribute("tips","验证码不能为空");
            resp.sendRedirect("/login");

        }
        else if(!Objects.equals(code,session.getAttribute("code"))){
            session.setAttribute("tips","验证码错误");
            resp.sendRedirect("/login");

        }
        else if(Objects.equals(flag, "null")){
            session.setAttribute("tips","用户名不存在");
            resp.sendRedirect("/login");

        }
        else if(!Objects.equals(flag, password)){
            session.setAttribute("tips","密码错误!");
            resp.sendRedirect("/login");

        }
        else if(Objects.equals(flag, password) && Objects.equals(code,session.getAttribute("code"))){
            String name = userService.getName(user);
            session.setAttribute("user",user);
            session.setAttribute("head",userService.getHead(user));
            session.setAttribute("password",password);
            session.setAttribute("login","1");
            session.setAttribute("name",name);
            resp.sendRedirect("/home");
        }
        session.setAttribute("code",null);
    }
}
