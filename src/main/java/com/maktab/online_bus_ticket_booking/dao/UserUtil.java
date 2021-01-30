package com.maktab.online_bus_ticket_booking.dao;


import com.maktab.online_bus_ticket_booking.bean.User;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserUtil {

    private DataSource dataSource;

    public UserUtil(DataSource theDataSource) {
        dataSource = theDataSource;
    }

    public List<User> getUsers() throws Exception {

        List<User> userList = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {

            myConn = dataSource.getConnection();
            String sql = "select * from user";
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery(sql);
            while (myRs.next()) {

                int id = myRs.getInt("id");
                String firstName = myRs.getString("first_name");
                String lastName = myRs.getString("last_name");
                String gender = myRs.getString("gender");
                String userName = myRs.getString("username");
                String password = myRs.getString("password");
                User tempUser = new User(id, firstName, lastName,gender,userName,password);
                userList.add(tempUser);
            }

            return userList;
        }
        finally {
            close(myConn, myStmt, myRs);
        }
    }

    private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

        try {
            if (myRs != null) {
                myRs.close();
            }

            if (myStmt != null) {
                myStmt.close();
            }

            if (myConn != null) {
                myConn.close();
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void addUser(User user) throws Exception {

        String name = user.getFirstName();
        String lastname = user.getLastName();
        String  username = user.getUsername();
        String password = user.getPassword();
        String gender = user.getGender();

            Connection myConn = dataSource.getConnection();
            PreparedStatement ps= myConn .prepareStatement(
                    "INSERT INTO user\n" +
                            "(first_name,last_name,gender,username,password)\n" +
                            "VALUES\n" +
                            "(?,?,?,?,?)");
            ps.setString(1,name);
            ps.setString(2,lastname);
            ps.setString(3,gender);
            ps.setString(4,username);
            ps.setString(5,password);
            ps.executeUpdate();
            myConn.close();
            ps.close();


    }

}
