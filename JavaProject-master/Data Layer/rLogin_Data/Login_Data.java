package rLogin_Data;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Login_Data implements rLogin_Data.LoginConstants{
	private File file;
	private ArrayList<LoginCredential> loginData = new ArrayList<LoginCredential>();
	private ArrayList<String> usernameIndex = new ArrayList<String>();
	private ArrayList<Integer> idIndex = new ArrayList<Integer>();
	
	
	public Login_Data() {
		file = new File(LoginConstants.LOGIN_BIN_FILENAME); 
		//DESIGN CHOICE: by default when object is created, lists are processd
		getAll(); 
	}
	
	
	//arraylist accessors//
	public ArrayList<LoginCredential> getLoginData() {
		return loginData;
	}
	
	public ArrayList<Integer> getIDIndex() {
		return idIndex; 
	}
	
	public  ArrayList<String> usernameIndex() {
		return usernameIndex;
	}
	
	public ArrayList<LoginCredential> getAll(){
		try {
			loginData.clear();
			usernameIndex.clear();
			idIndex.clear();
			RandomAccessFile in = new RandomAccessFile(file,"rw");
			int count = ((int)in.length())/C_LOGIN_RECORD_SIZE;
			for (int i = 0; i < count; i++) {
				in.seek(i*C_LOGIN_RECORD_SIZE);
				int currentID= in.readInt();
				String currentUserName = readString(in, C_USERNAME_SIZE);
				String currentPassword = readString(in, C_PASSWORD_SIZE);
				loginData.add(new LoginCredential(currentID, currentUserName, currentPassword));
				usernameIndex.add(currentUserName);
				idIndex.add(currentID);
			}
			in.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Testing Only
//		for (LoginCredential log : loginData) {
//			System.out.print(log.getID() + "\t");
//			System.out.print(log.getUsername() + "\t");
//			System.out.println(log.getPassword() + "\t");
//		}
		
		return loginData;
	}
	
	
	
	//Implemented for the sake of showing the process or Random access, 
	//but in practice it is useless, when you getAll in an arrayList 
	//it is more convenient accessing the data from there.
	
	//and later DB is even more efficient
	public LoginCredential getLoginCredential(int byIndex) {
		try {
			RandomAccessFile in = new RandomAccessFile(file, "rw");
			in.seek(byIndex*C_LOGIN_RECORD_SIZE);
			int currentID= in.readInt();
			String currentUserName = readString(in, C_USERNAME_SIZE);
			String currentPassword = readString(in, C_PASSWORD_SIZE);
			in.close();
			return (new LoginCredential(currentID, currentUserName, currentPassword));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	//the following two methods will assist in case we need to get the 
	//index from ID or username, so you can use it later to use the method above
	public int getIndex(int byID) {
		int count = 0;
		for (Integer integer : idIndex) {
			if (byID == integer.intValue()) 
				return count;
			else
				count++; //
		}
		return -1; //not found
		
	}
	
	
	//this method is also useful to check if the username was registered or not
	public int getIndex(String byUsername) {
		int count = 0;
		for (String string : usernameIndex) {
			if (string.equalsIgnoreCase(byUsername)) 
				return count;
			else
				count++; 
		}
		return -1; //not registered
		
	}
	
	public int getNewID() {
		Random random = new Random();
		int newID = 0;
		boolean repeat = true;
		while(repeat)
		{
			newID = random.nextInt(1000000000);
			repeat = false;
			for (Integer oldID : idIndex) {
				if (oldID == newID) {
					//Found Same
					repeat = true;
					break;
				}
			}
		}
		return newID;
	}
	
	
	//Adds a new user at the end of the file
	public boolean addNewRegisteredUser(LoginCredential loginCredential) {
		if (getIndex(loginCredential.getUsername()) != -1) return false; //was registered before
		
		try {
			RandomAccessFile out = new RandomAccessFile(file, "rw");
			out.seek(out.length());
			out.writeInt(loginCredential.getID());
			writeString(out, C_USERNAME_SIZE, loginCredential.getUsername());
			writeString(out, C_PASSWORD_SIZE, loginCredential.getPassword());
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return true;
	}
		
	
	//standard methods for writing and reading strings used ' ' as filler
	public void writeString(RandomAccessFile out, int stringMaxLength, String target) throws IOException {
		//for (char letter : target.toCharArray()) {
			out.writeChars(target);
		//}
		for (int i = 0; i < (stringMaxLength - target.length()); i++) {
			out.writeChar(' ');
		}
	}
	
	public String readString(RandomAccessFile in, int stringLength) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < stringLength; i++) {
			try {
				sb.append(in.readChar());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sb.toString().trim();
	}
	
	
	public static void main(String[] args) {
		Login_Data testObj = new Login_Data();
		//////////////////////////
		//  TESTING METHODS /////
		/////////////////////////
		
		//LoginCredential loginCredential = new LoginCredential(732983, "Rashed", "haha");
		
	//	testObj.addNewRegisteredUser(new LoginCredential(732983, "Rashed", "haha"));
		//testObj.addNewRegisteredUser(new LoginCredential(329823, "Navdeep", "hehe"));
		testObj.getAll();
		for (LoginCredential log : testObj.getLoginData()) {
			System.out.print(log.getID() + "\t");
			System.out.print(log.getUsername() + "\t");
			System.out.println(log.getPassword() + "\t");
		}
		
		
		//System.out.println(testObj.getNewID());
		
//		System.out.println(testObj.getLoginCredential(0).getUsername());
//		System.out.println(testObj.getIndex("Navdeep"));
//		LoginCredential test = new LoginCredential(4324234, "Navdeep", "hehe");
//		System.out.println(testObj.addNewRegisteredUser(test));
//		
//		test = new LoginCredential(2323, "Joe", "hehe"); //try to change name before
//		System.out.println(testObj.addNewRegisteredUser(test));
		
		
	}
	
}
