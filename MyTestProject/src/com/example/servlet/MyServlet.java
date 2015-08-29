package com.example.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
/**
 * Servlet implementation class MyServlet
 */
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public MyServlet() {
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(new java.util.Date().toString());
		//response.getWriter().println("Hello "+ request.getParameter("name"));
		

	  /*  String url = "jdbc:mysql://localhost/prashantdb";
	 
	    String user = "root";
	 
	    String password = "prashant";*/
	 
		
		


			// Class.forName("com.mysql.jdbc.Driver").newInstance();
			// Connection con = DriverManager.getConnection(url, user,
			// password);
			// Statement s = con.createStatement();

			//helper.setUserName("testing");
			//helper.setPassword("pass");
			//session.get(Helper.class, session);
			//session.save(helper);

		
	/*	HttpSession session = request.getSession();
		ServletContext servletContext = request.getServletContext();
		
		session.setAttribute("username", userName);
		if(password !=null && password.equals("pass"))
			response.getWriter().println("Hello "+userName);
		else
		{
			response.getWriter().println("Wrong password!!");
			response.sendRedirect("MyJSPFile.jsp");
		} */
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String username = request.getParameter("name");
		String pswd = request.getParameter("pass");
		
		System.out.println(username);
		System.out.println(pswd);
		
		AuthenticateUser authUser = new AuthenticateUser();
		boolean logSuccess = authUser.authenticate(username, pswd);
		
		
		
		if(logSuccess)
		{
			//response.setStatus(200);
			//response.getWriter().println("Hello "+username);
			//response.getWriter().println("yes");
			
			//StringBuilder JSONObject = new StringBuilder("{\"status\":\"yes\", \"info\": { \"name\": \""+username+"\"} }");
			
			FetchLoginTimes loginTime = new FetchLoginTimes();
			//StringBuilder JSONObject = loginTime.getLoginTimes(username);
			String lastLogin = loginTime.getLoginTime(username);
			//response.getWriter().println(JSONObject);

			
			request.getSession().setAttribute("username", username);
			if(lastLogin !=null)
				request.getSession().setAttribute("lastLogin", lastLogin);
			RequestDispatcher dispatcher = request.getRequestDispatcher("LoginSuccess.jsp");
			dispatcher.forward(request, response);
			//response.sendRedirect("LoginSuccess.jsp");

		}
		else
		{
			StringBuilder JSONObject = new StringBuilder("{\"status\":\"no\"}");
			//response.getWriter().println(JSONObject);
			response.sendRedirect("MyJSPFile.jsp");
		}
	}

}
