/**
 * 
 */
package sPerson_Data;

import java.util.ArrayList;
import java.util.List;

import nBook_Data.BookData;

/**
 * @author lenovo
 *
 */
public class RegisteredMember extends Person {
	protected ArrayList<BookData> bookarr=new ArrayList<BookData>();
	public RegisteredMember() {
		
		// TODO Auto-generated constructor stub
		
	}
	public void issueBook(BookData bk) {
		bookarr.add(bk);
	}
}
