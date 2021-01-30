package com.maktab.online_bus_ticket_booking.controller;

import com.maktab.online_bus_ticket_booking.bean.Travel;
import com.maktab.online_bus_ticket_booking.dao.TravelUtil;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@WebServlet("/TravelcontrollerServlet")
public class TravelcontrollerServlet extends HttpServlet {

    @Resource(name = "jdbc/bus_tickets_booking")
    private DataSource dataSource;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String travelFrom = request.getParameter("travel_from");
        String travelTo = request.getParameter("travel_to");
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String day = request.getParameter("day");

        Travel travel = new Travel();
        travel.setTravelFrom(travelFrom);
        travel.setTravelTo(travelTo);
        travel.setYear(year);
        travel.setMonth(month);
        travel.setDay(day);
        TravelUtil travelUtil = new TravelUtil(dataSource);
        try {
            List<Travel> travelList = travelUtil.searchTravel(travel);
            if (travelList.size() == 0) {
                response.sendRedirect("nomatch.html");
            } else {
                request.setAttribute("travelList", travelList);
                HttpSession session = request.getSession();
                session.setAttribute("travelIdList",travelList);
                RequestDispatcher dispatch = request.getRequestDispatcher("travel.jsp");
                dispatch.forward(request,response);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}
