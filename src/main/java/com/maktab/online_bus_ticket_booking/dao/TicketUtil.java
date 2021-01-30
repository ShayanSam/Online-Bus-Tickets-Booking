package com.maktab.online_bus_ticket_booking.dao;

import com.maktab.online_bus_ticket_booking.bean.Ticket;
import com.maktab.online_bus_ticket_booking.bean.Travel;
import com.maktab.online_bus_ticket_booking.bean.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketUtil {

    private DataSource dataSource;

    public TicketUtil(DataSource theDataSource) {
        dataSource = theDataSource;
    }

    public void addTicket(int userId, int travelId) throws Exception {
        Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement("insert into ticket " +
                "(user_id,travel_id) value (?,?)");
        ps.setInt(1, userId);
        ps.setInt(2, travelId);
        ps.executeUpdate();
        ps.close();
        connection.close();
    }

    public Integer findUserId(String username) throws Exception {
        int id = 0;
        Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement("select id from user where username = ?");
        ps.setString(1, username);
        ps.executeQuery();
        ResultSet resultSet = ps.getResultSet();
        while (resultSet.next()) {
            id = resultSet.getInt("id");
        }
        return id;
    }

    public List<Object[]> ticketsPurchased(int userId) throws Exception {
        List<Object[]> list = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement("select travel_from,travel_to,date,time,first_name,last_name,t.travel_id,ti.t_id,gender \n" +
                "from travel t\n" +
                "join ticket ti on t.id = ti.travel_id\n" +
                "join user u on u.id = ti.user_id\n" +
                "where ti.user_id = ?");
        ps.setInt(1, userId);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            Object[] array = new Object[3];
            User user = new User();
            Travel travel = new Travel();
            Ticket ticket = new Ticket();
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setGender(resultSet.getString("gender"));
            travel.setTravelTo(resultSet.getString("travel_to"));
            travel.setTravelFrom(resultSet.getString("travel_from"));
            travel.setTime(resultSet.getString("time"));
            travel.setDate(resultSet.getString("date"));
            travel.setTravelId(resultSet.getInt("travel_id"));
            ticket.setId(resultSet.getInt("t_id"));
            array[0] = user;
            array[1] = travel;
            array[2] = ticket;
            list.add(array);
        }
        resultSet.close();
        ps.close();
        connection.close();
        return list;
    }

    public void deleteTicket(Integer ticketId) throws SQLException {

        Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement("delete from ticket where t_id = ?");
        ps.setInt(1, ticketId);
        ps.executeUpdate();

        ps.close();
        connection.close();

    }
    }



