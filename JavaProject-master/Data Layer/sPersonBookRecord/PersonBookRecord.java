package sPersonBookRecord;

public class PersonBookRecord {
	//declaring variables
	private int personID;
	private String bookID;
	//constructors
	public PersonBookRecord(int personID, String bookID) {
		super();
		this.personID = personID;
		this.bookID = bookID;
	}
	
	public PersonBookRecord() {
		// TODO Auto-generated constructor stub
	}
	//getters and setters
	public int getPersonID() {
		return personID;
	}
	public void setPersonID(int personID) {
		this.personID = personID;
	}
	public String getBookID() {
		return bookID;
	}
	public void setBookID(String bookID) {
		this.bookID = bookID;
	}
	
}
