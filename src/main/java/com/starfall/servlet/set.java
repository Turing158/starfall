package com.starfall.servlet;

import com.starfall.Util.ViewBaseServlet;
import com.starfall.config.sf_config;
import com.starfall.enity.User;
import com.starfall.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/set")
public class set extends ViewBaseServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(sf_config.class);
        UserService userService = context.getBean("userService", UserService.class);
        HttpSession session = req.getSession();
        if (session.getAttribute("display_me") == null){
            session.setAttribute("display_me","block");
            session.setAttribute("display_i","none");
            session.setAttribute("display_p","none");
            session.setAttribute("display_h","none");
        }
        String user_session = (String) session.getAttribute("user");
        User user = userService.getInfo(user_session);
        session.setAttribute("name",user.getName());
        session.setAttribute("date",user.getData());
        session.setAttribute("introduce",user.getIntroduce());
        session.setAttribute("email",user.getEmail());
        session.setAttribute("level",user.getLevel());
        super.processTemplate("set",req,resp);
    }
}
