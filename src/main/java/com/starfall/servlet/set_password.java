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

@WebServlet("/set_password")
public class set_password extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(sf_config.class);
        UserService userService = context.getBean("userService", UserService.class);
        HttpSession session = req.getSession();
        req.setCharacterEncoding("utf-8");
        String user = (String) session.getAttribute("user");
        String old_password = req.getParameter("old_password");
        String new_password = req.getParameter("new_password");
        String code = req.getParameter("seti_VerifyCode");
        session.setAttribute("display_me","none");
        session.setAttribute("display_p","block");
        session.setAttribute("display_i","none");
        boolean flag = userService.checkOldPassword(user,old_password);
        if(flag && Objects.equals(code,session.getAttribute("code"))){
            session.setAttribute("p_tips","密码修改成功");
            session.setAttribute("p_tips_color","lightgreen");
            userService.setNewPassword(user,new_password);
            session.setAttribute("code",null);
        }
        else if(!Objects.equals(code,session.getAttribute("code"))){
            session.setAttribute("p_tips","验证码错误");
            session.setAttribute("p_tips_color","rgb(255, 125, 125)");
        }
        else{
            session.setAttribute("p_tips","原密码错误");
            session.setAttribute("p_tips_color","rgb(255, 125, 125)");
        }
        resp.sendRedirect("/set");
    }
//    public boolean check(String user,String password) throws SQLException {
//        Connection con = new Connection_SQL().getCon();
//        String cmd = "select password from login_web.web_user where user=\""+user+"\"";
//        boolean flag = false;
//        PreparedStatement run = con.prepareStatement(cmd);
//        ResultSet result = run.executeQuery(cmd);
//        while(result.next()){
//            flag = Objects.equals(password,result.getString("password"));
//        }
//        result.close();
//        run.close();
//        con.close();
//        return flag;
//    }
//    public void set(String user,String password)throws SQLException{
//        Connection con = new Connection_SQL().getCon();
//        String cmd = "update login_web.web_user set password=\""+password+"\" where user=\""+user+"\"";
//        PreparedStatement run = con.prepareStatement(cmd);
//        run.executeUpdate();
//        run.close();
//        con.close();
//    }

}
