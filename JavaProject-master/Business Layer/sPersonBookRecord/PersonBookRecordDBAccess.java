package sPersonBookRecord;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import sPerson_Business.PersonDataBaseImplementation;
import sPerson_Data.Person;

public class PersonBookRecordDBAccess {
	//creating connection
	private Connection con = null;
	//constructor
	public PersonBookRecordDBAccess() {
		// TODO Auto-generated constructor stub
		
	}
	
	//connecting to the database
	private void connect()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@calvin.humber.ca:1521:grok";
			String Username = "N01351862";
			String Password = "oracle";
			con = DriverManager.getConnection(url,Username,Password);
		}
		catch(SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	//
	public ResultSet getPersonrecords()
	{
		this.connect();
		ResultSet rs = null;
		try
		{
			String query = "Select * from PERSONMEMBERRECORD";
			Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = stm.executeQuery(query);
			//System.out.println(rs.getString("id"));
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		this.closeConnection();
		return rs;
	}
	//to find if the book has been issued or not
	public boolean getRecord(String bookid)
	{	
		this.connect();
		ResultSet rs = null;
		try
		{
			String query = "Select * from PERSONMEMBERRECORD where bookid ='"+bookid+"' ";
			Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = stm.executeQuery(query);
		}
		catch(SQLException e)
		{	
			e.printStackTrace();
		}
		try {
			
			return (rs.next());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		this.closeConnection();
		return false;
		
		
	}
	//returns the arraylist of all the books under a personid
	public ArrayList<String> getRecord(int personid)
	{	ArrayList<String> arrayOfBooks=new ArrayList<String>();
		ResultSet rs = null;
		this.connect();
		try
		{
			String query = "Select bookid from PERSONMEMBERRECORD where personid ='"+personid+"' ";
			Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = stm.executeQuery(query);
		}
		catch(SQLException e)
		{	
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				arrayOfBooks.add(rs.getString("bookid"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		this.closeConnection();
		return arrayOfBooks;
		
	}
	//adding a new record or issuing a book
	
	public int addRecord(PersonBookRecord personBookRecord) throws SQLException{
		this.connect();
		PreparedStatement stm = null;
		int count = 0;
		String sql = "Insert into personmemberrecord (PERSONID,BOOKID) values (?,?)";
		stm = con.prepareStatement(sql);
		stm.setString(1, personBookRecord.getPersonID()+"");
		stm.setString(2, personBookRecord.getBookID()+"");
		count = stm.executeUpdate();
		this.closeConnection();
		return count;
		
	}
	//to remove a record
	public int removeRecord(String bookID) {
		this.connect();
		PreparedStatement stm = null;
		int count = 0;
		String sql = "delete from personmemberrecord where BOOKID=(?)";
		try {
		stm = con.prepareStatement(sql);
		stm.setString(1,bookID);
		
		count = stm.executeUpdate();
		this.closeConnection();
		return count;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return count;
		
	}
	//closing th connection
	public void closeConnection() {
		try {
		    con.close();
		    }
		catch(Exception ex3) {
			ex3.printStackTrace();
		}
		
	}
	
		public static void main(String[] args) {
			//testing 
			PersonBookRecordDBAccess bookRecord=new PersonBookRecordDBAccess();
			PersonBookRecord pr= new PersonBookRecord(1,"2");
			try {
				System.out.println("Suvaansh "+bookRecord.addRecord(pr));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
	}
}
