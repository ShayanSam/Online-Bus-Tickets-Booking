package com.maktab.online_bus_ticket_booking.controller;

import com.maktab.online_bus_ticket_booking.dao.TicketUtil;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/ShowTicketsControllerServlet")
public class ShowTicketsControllerServlet extends HttpServlet {
    @Resource(name = "jdbc/bus_tickets_booking")
    private DataSource dataSource;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int iD = 0;
        HttpSession session = req.getSession();
        TicketUtil ticketUtil = new TicketUtil(dataSource);
            Cookie[] cookies = req.getCookies();
            for(Cookie cookie : cookies) {
                if ("userId".equals(cookie.getName())) {
                    String userId = cookie.getValue();
                    iD = Integer.parseInt(userId);
                    break;
                }
            }
        try {
            List<Object[]> purchasedList = ticketUtil.ticketsPurchased((Integer) iD);
            session.setAttribute("pList",purchasedList);
            RequestDispatcher dispatch = req.getRequestDispatcher("tickets.jsp");
            dispatch.forward(req,resp);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
