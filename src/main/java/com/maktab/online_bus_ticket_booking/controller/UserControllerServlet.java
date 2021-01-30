package com.maktab.online_bus_ticket_booking.controller;

import com.maktab.online_bus_ticket_booking.bean.User;
import com.maktab.online_bus_ticket_booking.dao.TicketUtil;
import com.maktab.online_bus_ticket_booking.dao.UserUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.*;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet("/UserControllerServlet")
public class UserControllerServlet extends HttpServlet {

    @Resource(name = "jdbc/bus_tickets_booking")
    private DataSource dataSource;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UserUtil userUtil = new UserUtil(dataSource);
        TicketUtil ticketUtil = new TicketUtil(dataSource);

        Map<String, String> userPassword = new HashMap<>();
        try {
            Integer userId = ticketUtil.findUserId(username);
            String id = String.format(userId.toString());
            Cookie cookie = new Cookie("userId", id);
            cookie.setMaxAge(60*60*24*365);
            resp.addCookie(cookie);
            List<User> userList = userUtil.getUsers();
            for (User user: userList){
                userPassword.put(user.getUsername(),user.getPassword());
            }
            if (userPassword.containsKey(username) && password.equals(userPassword.get(username)) &&
                    !username.equals("") && !password.equals("")) {
                HttpSession session = req.getSession();
                session.setAttribute("username",username);
                RequestDispatcher dispatcher = req.getRequestDispatcher("travel.html");
                dispatcher.forward(req,resp);


            } else
            out.println("<script type='text/javascript'>");
            out.println("alert(" + "'" + "Username or Password Incorrect...!!" + "'" + ");</script>");
            out.println("</head><body></body></html>");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
