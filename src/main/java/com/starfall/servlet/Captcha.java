package com.starfall.servlet;


import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
public class Captcha extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        resp.setContentType("image/jpeg");
        //创建一个不带透明色的对象 图片对象 三原色
        BufferedImage bufferedImage = new BufferedImage(80,30,BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.getGraphics();//获取画布对象
        Color bgcolor = getBackColor();
        //画背景
        g.setColor(bgcolor);
        g.fillRect(0,0,80,30);//位置大小
        //设置前景色
        Color foreColor = getForeColor(bgcolor);
        g.setColor(foreColor);
        //设置字体
        g.setFont(new Font("黑体",Font.BOLD,26));
        //获取验证码
        String code = getCode();
        session.setAttribute("code",code);
        g.drawString(code,10,28);
        for (int i = 0; i < 100; i++) {
            g.setColor(Color.white);
            int x = r.nextInt(80);
            int y = r.nextInt(30);
            g.fillRect(x,y,1,1);
        }
        g.setColor(Color.white);
        int x = r.nextInt(50)+10;
        int y = r.nextInt(15)+10;
        int width = r.nextInt(10)+20;
        g.fillRect(x,y,width,2);
        ServletOutputStream sos = resp.getOutputStream();
        ImageIO.write(bufferedImage,"jpeg",sos);
    }
    Random r = new Random();
    private String getCode(){
        char[] str = "23456789ABCDEFGHIGKMNPQSTUVWXYZabcdefghgklmnpqstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append(str[r.nextInt(str.length)]);
        }
        return sb.toString();
    }
    public Color getBackColor(){
        int red = r.nextInt(256);
        int green = r.nextInt(256);
        int blue = r.nextInt(256);
        return new Color(red,green,blue);
    }
    public Color getForeColor(Color bgColor){
        int red = 255-bgColor.getRed();
        int green = 255-bgColor.getGreen();
        int blue = 255-bgColor.getBlue();
        return new Color(red,green,blue);
    }
}
