package com.maktab.online_bus_ticket_booking.controller;

import com.maktab.online_bus_ticket_booking.bean.User;
import com.maktab.online_bus_ticket_booking.dao.UserUtil;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Collection;

@WebServlet("/RegisterControllerServlet")
public class RegisterControllerServlet extends HttpServlet {

        @Resource(name="jdbc/bus_tickets_booking")
        private DataSource dataSource;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String  username = request.getParameter("username");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");

        User user = new User();
        user.setFirstName(name);
        user.setLastName(lastname);
        user.setUsername(username);
        user.setPassword(password);
        user.setGender(gender);
        UserUtil userUtil = new UserUtil(dataSource);
        try {
            userUtil.addUser(user);
            response.sendRedirect("login.html");
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
