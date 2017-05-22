package com.obs.controller;

import com.obs.models.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class LoginValidator extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        User user=new User();
        
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        user=session.get(User.class, 1);
//        out.println("Username : " + user.getUserName());
//        out.println("Password : " + user.getPassword());
        
        
        
        if(username.equals(user.getUserName())&&username.equals(user.getPassword())){
            out.println("Login is successfull");
            RequestDispatcher dispatcher=request.getRequestDispatcher("admin/admin.html");
            dispatcher.forward(request, response);
        }else{
            out.println("Login is unsuccssfull");
        }

        session.getTransaction().commit();
        session.close();

    }
}
