package com.example.servlet;

import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class AuthenticateUser {
	
	public boolean authenticate(String username, String password)
	{
		Session session = null; 
		boolean logSuccess = false;
		
		try {
			
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			
			Transaction tx = session.beginTransaction();

			ArrayList list = (ArrayList) session.createQuery("FROM Helper").list();

			for(Iterator iterator = list.iterator(); iterator.hasNext();)
			{
				Helper h = (Helper)iterator.next();
				if(h.getUserName().equals(username) && h.getPassword().equals(password))
				{
					logSuccess = true;
					break;
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		
		return logSuccess;
	}

}
