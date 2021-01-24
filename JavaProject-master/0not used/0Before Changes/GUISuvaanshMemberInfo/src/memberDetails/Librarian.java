package memberDetails;

import java.text.ParseException;

public class Librarian extends Person {

	public Librarian() {
		// TODO Auto-generated constructor stub
		
	}
	public void issueBook(RegisteredMember rm,Book bk) {
		rm.bookarr.add(bk);
	}
	
	public void searchMember(String searchItem){
		
	}
	

}
