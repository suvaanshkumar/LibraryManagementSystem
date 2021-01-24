package memberDetails;

import java.util.Date;

import javax.swing.JTextField;

import data.accessMode;

public class PersonConstants {
	
		
		
		
		
		//LOGIN DATA CONSTANTS
		
		final static String MEMBER_BIN_FILENAME = "Files/person.dat";
		
		
		final static int C_ID_SIZE=4;			//int id; 			//integer is 4 bytes FIXED
		final static int ACCESS_TYPE_SIZE=4; 	//int accessType; 	//integer is 4 bytes FIXED
		final static int FIRST_NAME_SIZE=20; 	//String name; 		//string length will be anything chosen here 20 characters each char 2 bytes thus calculation below 
		final static int LAST_NAME_SIZE=20; 	//String lastName;
		final static int EMAIL_SIZE=20; 		//String email;
		final static int NUMBER_SIZE=8;		//long number;		//long is 8 bytes FIXED
		final static int ADDRESS_SIZE=30;		//String address;
		final static int DATE_JOINED_SIZE=20;	//String Date_dateJoined; 
		
		
		final static int C_MEMBER_RECORD_SIZE = C_ID_SIZE + 
	    						   ACCESS_TYPE_SIZE + 
	    						   FIRST_NAME_SIZE*2 +
	    						   LAST_NAME_SIZE*2 +
	    						   EMAIL_SIZE*2 + 
	    						   NUMBER_SIZE +
	    						   ADDRESS_SIZE*2 +
	    						   DATE_JOINED_SIZE*2;
		
	    //public MemberConstants() {
		//	super(); ?
			
		//}
	    
	    
}
