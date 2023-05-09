package com.starfall.To;

import com.starfall.Util.ViewBaseServlet;
import com.starfall.config.sf_config;
import com.starfall.service.DiscussService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.thymeleaf.util.StringUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.util.Objects;

@WebServlet("/home")
public class toHome extends ViewBaseServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(sf_config.class);
        DiscussService discussService = context.getBean("discussService", DiscussService.class);
        HttpSession session = req.getSession();
        String only_user = req.getParameter("only_user");
        if (Objects.equals(only_user,"null")){
            only_user = "";
        }
        session.setAttribute("only_user",only_user);
        Integer page = 1;
        session.setAttribute("page",1);
        String page_str = req.getParameter("page");

        if (!StringUtils.isEmpty(page_str)){
            page = Integer.valueOf(page_str);
        }
        session.getAttribute("user");
        session.getAttribute("login");
        Integer last_page = discussService.getPage(only_user);
//        last_page = count_page(only_user);
        session.setAttribute("comment",discussService.getDiscuss(page,only_user));
        session.setAttribute("last_page",last_page);
        session.setAttribute("page",page);
        session.setAttribute("page_center",page+"/"+last_page);
        if(page > last_page){
            session.setAttribute("page",page-1);
        }
        else if(page <= 0){
            session.setAttribute("page",1);
        }

        super.processTemplate("index",req,resp);
    }

}

