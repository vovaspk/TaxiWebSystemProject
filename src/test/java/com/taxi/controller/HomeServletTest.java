package com.taxi.controller;

import com.taxi.dao.daoImpl.UserDaoImpl;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class HomeServletTest extends UserDaoImpl {
    private HomeServlet homeServlet;
    @Before
    public void setUp(){
        this.homeServlet = new HomeServlet();
    }


    @Test
    public void doGet() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        HttpSession session = mock(HttpSession.class);
        session.setAttribute("user", "vova");
        when(request.getSession()).thenReturn(session);



        final ServletContext servletContext = mock(ServletContext.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(servletContext.getRequestDispatcher("/view/home.jsp")).thenReturn(dispatcher);

        homeServlet.doGet(request, response);



        verify(dispatcher).forward(request,response);
    }

    @Test
    public void doPost() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        final ServletContext servletContext = mock(ServletContext.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(servletContext.getRequestDispatcher("/web/view/home.jsp")).thenReturn(dispatcher);

        when(request.getParameter("userName")).thenReturn("vova");
        homeServlet.doPost(request,response);
        verify(request, atLeast(1)).getParameter("userName");
    }

    @Test(expected = NullPointerException.class)
    public final void testDoPostPositive() throws ServletException, IOException {
        homeServlet.doPost(null, null);
    }

}