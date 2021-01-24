package sPerson_Data;

import java.text.ParseException;

import nBook_Data.BookData;

public class Librarian extends Person {

	public Librarian() {
		// TODO Auto-generated constructor stub
		
	}
	//librarian has a right to issue a book
	public void issueBook(RegisteredMember rm,BookData bk) {
		rm.bookarr.add(bk);
	}
	
	public void searchMember(String searchItem){
		
	}
	

}
