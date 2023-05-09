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

@WebServlet("/set_information")
public class set_information extends ViewBaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(sf_config.class);
        UserService userService = context.getBean("userService", UserService.class);
        HttpSession session = req.getSession();
        session.setAttribute("display_me","none");
        session.setAttribute("display_p","none");
        session.setAttribute("display_i","block");
        req.setCharacterEncoding("utf-8");
        String user = (String) session.getAttribute("user");
        String name = req.getParameter("name");
        String introduce = req.getParameter("introduce");
        String code = req.getParameter("seti_code");
        if(Objects.equals(code,"")){
            session.setAttribute("i_tips","验证码不能为空");
        }
        else if(!Objects.equals(code,session.getAttribute("code"))){
            session.setAttribute("i_tips","验证码错误");
        }
        else if ((name != null || introduce != null )&& Objects.equals(code,session.getAttribute("code"))){
            session.setAttribute("i_tips","信息修改成功");
            userService.updateInformation(user,name,introduce);
        }
        session.setAttribute("code",null);
        resp.sendRedirect("/set");
    }
//    public void set(String user,String name,String introduce) throws SQLException {
//        Connection con = new Connection_SQL().getCon();
//        String cmd = "update login_web.web_user set name=\""+name+"\",introduce=\""+introduce+"\" where user=\""+user+"\"";
//        PreparedStatement run = con.prepareStatement(cmd);
//        run.executeUpdate(cmd);
//        run.close();
//        con.close();
//    }
}
