package data;

import java.awt.Font;

public interface Constants {
	
	
	
	
	//LOGIN DATA CONSTANTS
	
	final String LOGIN_BIN_FILENAME = "Files/login.dat";
	
	int C_USERNAME_SIZE=10;
	int C_PASSWORD_SIZE=10;
	int C_ID_SIZE=4;
	
    int C_LOGIN_RECORD_SIZE=C_USERNAME_SIZE*2+C_PASSWORD_SIZE*2+C_ID_SIZE;
}
