package rLogin_Data;

import java.sql.Connection;
import java.sql.Date;
//import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
public class Session_DB {
	
	private Connection connection = null;
	
	//ResultSet ds;
	private Statement statement;
	
	private static Session_DB instance = null;
	
	private Session_DB() {}
	
	public static Session_DB getInstance() {
		if (instance == null)
			instance = new Session_DB();
		return instance;
	}
	
	private void connect()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@calvin.humber.ca:1521:grok";
			String Username = "N01351862";
			String Password = "oracle";
			connection = DriverManager.getConnection(url,Username,Password);
		}
		catch(SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public int getNewSessionID() {
		try {
			connect();
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSet = statement.executeQuery
				      ("select Max(SessionID) from SESSIONS");
			
			resultSet.first();
			int newSessionID  = resultSet.getInt(1) + 1;
			
			return newSessionID;
		} catch( SQLException e) {
			
			System.err.println(e.toString());
			e.printStackTrace();
			return -1;
		} finally {
			disconnect();
		}
			
	}
	
	public void InsertEvent(SessionEvent event) {
		connect();
		try {
			PreparedStatement stm = null;
			String sql = "Insert into SESSIONS (ID, SESSIONID, EVENTTIME, EVENT) values (?, ?, ?, ?)";
			stm = connection.prepareStatement(sql);
			stm.setInt(1,event.getiD());
			stm.setInt(2,event.getSessionID());
			stm.setString(3, event.getDate());
			stm.setString(4,event.getEvent());
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  finally {
			disconnect();
		}
	}
	
	public void syncAllEvents(List<SessionEvent> events) {
		for (SessionEvent sessionEvent : events) {
			InsertEvent(sessionEvent);
		}
		events.clear();
		
	}
	
	public ResultSet getAllSessions()  {
		connect();
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			return statement.executeQuery
				      ("select * from Sessions order by EVENTTIME");	
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} 
		
		///BIG NOTICE UNCLOSED CONNECTION cuz returning resultset
		
			
		
		
	}
	
	public void disconnect() 
	{
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		Session_DB database = Session_DB.getInstance();
		Date date = Date.valueOf("2015-03-31");

		database.InsertEvent(new SessionEvent(2, 323, date.toString(),"sdad"));
//		ResultSet resultset = database.getAllSessions();
//		resultset.first();
//		resultset.getString("ID")
		System.out.println("sucess");

		
	}
	
}
