/**
 * 
 */
package memberDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lenovo
 *
 */
public class RegisteredMember extends Person {
	protected ArrayList<Book> bookarr=new ArrayList<>();
	public RegisteredMember() {
		
		// TODO Auto-generated constructor stub
		
	}
	public void issueBook(Book bk) {
		bookarr.add(bk);
	}
}
