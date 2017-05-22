/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.obs.controller;

import com.obs.models.AccountType;
import com.obs.models.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SignUpServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String fullName = request.getParameter("name");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("pnum");
        String city = request.getParameter("city");
        String localAddress = request.getParameter("laddress");
        String street = request.getParameter("street");
        String occupation = request.getParameter("occupation");
        String accountType = request.getParameter("acctype");
        String identityType = request.getParameter("idtype");
        String identityNum = request.getParameter("idnum");
        String birthMonth = request.getParameter("BirthMonth");
        String birthDay = request.getParameter("BirthDay");
        String birthYear = request.getParameter("BirthYear");
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("repassword");

        User user = new User();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setCity(city);
        user.setLocalAddress(localAddress);
        user.setStreet(street);
        user.setOccupation(occupation);

        
        user.setIdentityNum(identityNum);

        String dob = birthDay + birthMonth + birthYear; //setting up the date of birth

        user.setDOB(dob);

        user.setUserName(userName);
        user.setPassword(password);
        user.setRePassword(rePassword);
        
        AccountType savingAccount = new AccountType();
        AccountType currentAccount = new AccountType();
        AccountType fixedAccount = new AccountType();

        savingAccount.setAccountType("Saving");
        currentAccount.setAccountType("current");
        fixedAccount.setAccountType("fixed account");

        user.getAccounts().add(savingAccount);
        user.getAccounts().add(fixedAccount);
        user.getAccounts().add(currentAccount);

        savingAccount.setUser(user);
        currentAccount.setUser(user);
        fixedAccount.setUser(user);


        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.save(savingAccount);
        session.save(fixedAccount);
        session.save(currentAccount);

        session.getTransaction().commit();
        session.close();

        out.println("Data Written in Database Successfully");

    }

}
