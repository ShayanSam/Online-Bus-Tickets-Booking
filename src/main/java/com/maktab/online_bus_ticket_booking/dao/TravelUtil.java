package com.maktab.online_bus_ticket_booking.dao;


import com.maktab.online_bus_ticket_booking.bean.Travel;

import javax.servlet.RequestDispatcher;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TravelUtil {

    private DataSource dataSource;
    public TravelUtil(DataSource theDataSource) {
        dataSource = theDataSource;
    }

    public List<Travel> searchTravel(Travel travel) throws Exception {
        String travelFrom = travel.getTravelFrom();
        String travelTo = travel.getTravelTo();
        String year = travel.getYear();
        String month = travel.getMonth();
        String day = travel.getDay();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(year).append("-").append(month).append("-").append(day);
        String date = stringBuilder.toString();
        List<Travel> travelList = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement("select * from travel\n" +
                "where travel_from = ? and travel_to = ? and date = ? order by date ");
        ps.setString(1, travelFrom);
        ps.setString(2, travelTo);
        ps.setString(3, date);
        ResultSet resultSet = ps.executeQuery();

        while (resultSet.next()) {
            Travel travel1 = new Travel();
            travel1.setId(resultSet.getInt("id"));
            travel1.setTravelFrom(resultSet.getString("travel_from"));
            travel1.setTravelTo(resultSet.getString("travel_to"));
            travel1.setDate(resultSet.getString("date"));
            travel1.setTime(resultSet.getString("time"));
            travel1.setTravelId(resultSet.getInt("travel_id"));
            travelList.add(travel1);
        }
        ps.close();
        connection.close();
        return travelList;
    }

    }




