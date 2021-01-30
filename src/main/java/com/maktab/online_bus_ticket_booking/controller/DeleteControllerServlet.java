package com.maktab.online_bus_ticket_booking.controller;

import com.maktab.online_bus_ticket_booking.dao.TicketUtil;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/DeleteControllerServlet")
public class DeleteControllerServlet extends HttpServlet {
    @Resource(name="jdbc/bus_tickets_booking")
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        String travelId = req.getParameter("travelId");
        Integer tId = Integer.parseInt(travelId);
        TicketUtil ticketUtil = new TicketUtil(dataSource);
        try {
            ticketUtil.deleteTicket(tId);
            RequestDispatcher dispatcher = req.getRequestDispatcher("delete.html");
            dispatcher.forward(req,resp);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }
}
