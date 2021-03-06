package com.luv2code.testdb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

@WebServlet(name = "/TestDbServlet")
public class TestDbServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Setup connection Variables

        String user = "springstudent";
        String pass = "springstudent";

        String jdbcUrl= "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&serverTimezone=UTC";
        String driver = "com.mysql.jdbc.Driver";

        //get a connection to db.

        try{

            PrintWriter out = response.getWriter();

            out.println("Connecting to DB: " + jdbcUrl);

            Class.forName(driver);

            Properties info;
            Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
            out.println("Success!");

            myConn.close();


        }catch (Exception e){
            e.printStackTrace();
            throw new ServletException(e);
        }

    }
}
