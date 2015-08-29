package com.example.servlet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class FetchLoginTimes {

	public StringBuilder getLoginTimes(String username) {
		Session session = null;
		StringBuilder JSONObject = new StringBuilder("{\"status\":\"yes\", \"info\": { \"name\": \"" + username + "\"");

		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();

			Transaction tx = session.beginTransaction();

			ArrayList logTimes = (ArrayList) session
					.createQuery("Select login from LoginTimes where username = '" + username + "'").list();


			if (logTimes.size() > 0) {
				JSONObject.append(", \"login\" : [");

				for (Iterator it = logTimes.iterator(); it.hasNext();) {
					String loginTime = (String) it.next();
					JSONObject.append("{\"time\" : \"" + loginTime + "\" }");
					if (it.hasNext())
						JSONObject.append(",");

				}
				JSONObject.append("]");

				session.createQuery("update LoginTimes set login = '" + getDate() + "' where username = '"
						+ username + "'").executeUpdate();

			}

			else {
				LoginTimes lt = new LoginTimes();
				lt.setUsername(username);
				lt.setLogin(getDate());
				session.save(lt);
			}

			JSONObject.append("} }");

			tx.commit();
		} catch (Exception e) {

		} finally {
			session.flush();
			session.close();
		}

		return JSONObject;
	}
	
	public String getLoginTime(String username) {
		Session session = null;
		String lastLogin = null;
		try
		{
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			
			Transaction tx = session.beginTransaction();
			
			ArrayList al = (ArrayList)session.createQuery("select login from LoginTimes where username = '"+username+"'").list();
			
			if(al.size() > 0)
			{
				for(Iterator it = al.iterator();it.hasNext();)
				{
					lastLogin = (String)it.next();
				}
				
				session.createQuery("update LoginTimes set Login = '"+getDate() + "' where username = '"+username+"'").executeUpdate();
			}
			else
			{
				LoginTimes lt = new LoginTimes();
				lt.setUsername(username);
				lt.setLogin(getDate());
				session.save(lt);
			}
			
			tx.commit();
		}
		catch(Exception e)
		{
			
		}
		finally
		{
			session.flush();
			session.close();
		}
		
		return lastLogin;
	}
	
	private String getDate()
	{
		Calendar c = Calendar.getInstance();
		Date date = c.getTime();
		StringBuilder dateConv = new StringBuilder(date.toString());
		dateConv = new StringBuilder(dateConv.substring(0, 20));
		return dateConv.toString();
	}
}
