package spms.listeners;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import spms.dao.MemberDao;

@WebListener
public class ContextLoaderListener implements ServletContextListener{

	Connection conn = null;
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		try{
			ServletContext sc = event.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(sc.getInitParameter("url"), 
					sc.getInitParameter("username"), 
					sc.getInitParameter("password"));
			sc.setAttribute("conn", conn);

			MemberDao dao = new MemberDao();
			dao.setConnection(conn);
			sc.setAttribute("memberDao", dao);
		}catch(Exception e){
			e.printStackTrace();
			try{ if(conn != null){ conn.close(); }}catch(Exception e2){}
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		try{
			if(conn != null){
				conn.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
