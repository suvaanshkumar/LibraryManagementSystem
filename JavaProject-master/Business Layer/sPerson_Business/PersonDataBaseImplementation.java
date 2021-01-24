package sPerson_Business;

import javax.swing.text.Document;

import sPerson_Data.Person;
import sPerson_Data.RegisteredMember;
import sPerson_Data.accessMode;

import java.sql.*;
import java.util.Date;

public class PersonDataBaseImplementation {
	//creating connection
	private Connection con = null;
	//constructor
	public PersonDataBaseImplementation() {
		// TODO Auto-generated constructor stub
		this.connect();
	}
	//connecting to the database
	private void connect()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@calvin.humber.ca:1521:grok";
			String Username = "*****";
			String Password = "oracle";
			con = DriverManager.getConnection(url,Username,Password);
		}
		catch(SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	//getting all the persons
	public ResultSet getPersons()
	{
		ResultSet rs = null;
		try
		{
			String query = "Select * from PERSON";
			Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = stm.executeQuery(query);
			//System.out.println(rs.getString("id"));
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	//to get the person by name
	public Person getPerson(String Name)
	{	
		ResultSet rs = null;
		try
		{
			String query = "Select * from PERSON where firstname ='"+Name+"' ";
			Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = stm.executeQuery(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		

		Person person=null;
		try {
			while(rs.next()) {
			try {
				person = new Person(rs.getString("firstname"),rs.getString("lastname"),rs.getString("email"), 98790007,rs.getString("address"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//check
				System.out.println("no");
			}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return person;
	}
	
		public static void main(String[] args) {
			//testing 
			PersonDataBaseImplementation p=new PersonDataBaseImplementation();
			
			
			Person p1=p.getPerson("suvaansh");
			System.out.println(p1.getName());
			
			
	}
}
