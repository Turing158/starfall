package com.starfall.servlet;

import com.starfall.Util.ViewBaseServlet;
import com.starfall.config.sf_config;
import com.starfall.service.DiscussService;
import com.starfall.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;

@MultipartConfig
@WebServlet("/upload_head")
public class set_head extends ViewBaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(sf_config.class);
        UserService userService = context.getBean("userService", UserService.class);
        DiscussService discussService = context.getBean("discussService", DiscussService.class);
        HttpSession session = req.getSession();
        session.setAttribute("display_me","none");
        session.setAttribute("display_i","none");
        session.setAttribute("display_p","none");
        session.setAttribute("display_h","block");
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        Part part = req.getPart("head_img");
//        这个是保存头像的，现在保存只能临时的，这个填存服务器头像的存储位置，可永久保存
        String savePath = "";
        String user = (String) session.getAttribute("user");
        String fileType = part.getSubmittedFileName().substring(part.getSubmittedFileName().lastIndexOf("."));
        String path = getServletContext().getRealPath("head_img/");
//        System.out.println(req.getRealPath(req.getServletPath()));
        part.write(path+user+fileType);
//        永久保存头像，避免服务器崩溃导致丢失
//        part.write(savePath+user+fileType);
        userService.setHead(user,user+fileType);
        discussService.updateHead();
        session.setAttribute("head",user+fileType);
        resp.sendRedirect("/set");
    }
}
