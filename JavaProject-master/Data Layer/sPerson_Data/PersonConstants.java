package sPerson_Data;

import java.util.Date;

import javax.swing.JTextField;

public class PersonConstants {
	
		
		
		
		
		//LOGIN DATA CONSTANTS
		
		final static String MEMBER_BIN_FILENAME = "Files/person.dat";
		
		//constatnts for file implementations
		public final static int C_ID_SIZE=4;			//int id; 			//integer is 4 bytes FIXED
		public final static int ACCESS_TYPE_SIZE=4; 	//int accessType; 	//integer is 4 bytes FIXED
		public final static int FIRST_NAME_SIZE=20; 	//String name; 		//string length will be anything chosen here 20 characters each char 2 bytes thus calculation below 
		public final static int LAST_NAME_SIZE=20; 	//String lastName;
		public final static int EMAIL_SIZE=20; 		//String email;
		public final static int NUMBER_SIZE=8;		//long number;		//long is 8 bytes FIXED
		public final static int ADDRESS_SIZE=30;		//String address;
		public final static int DATE_JOINED_SIZE=20;	//String Date_dateJoined; 
		
		
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
