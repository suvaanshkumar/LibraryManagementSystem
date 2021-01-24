
package rLogin_Business;

import java.util.Date;
import java.util.Random;

import javax.swing.JOptionPane;

import rLogin_Data.LoginCredential;
import rLogin_Data.Login_Data;
import rLogin_Data.Session;
import rLogin_Data.SessionEvent;
import rLogin_Data.Session_DB;
import rMainFrame_Data.PanelType;
import rMainFrame_Presentation.MainFrame;
import sPerson_Data.FileImplementation;
import sPerson_Data.accessMode;

//Has Login Page Button Actions, reason was to be able to access them whether from JMenu or JButton



public class Login_Business {
	public static Session currentSession;
	
	//Action when Login Button is pressed after username and password are filled in the loginPage form
	public static void  loginAction(Session ccurrentSession, String username, String password) {
		// TODO Auto-generated method stub
		currentSession = ccurrentSession; //currentSession is passed around  as a shortcut to different program references
		
		//Accesses login.dat binary file and gets all credential (login) info, filling in the ArrayLists used for search
		Login_Data loginData = new Login_Data();
		loginData.getAll();
		
		//checkes if user has an index (present) in the list
		int userIndexTest = loginData.getIndex(username);
		if (userIndexTest == -1)
			//USER NOT FOUND//
			JOptionPane.showMessageDialog(null,"Username not Registered","Not Registered",JOptionPane.INFORMATION_MESSAGE);
		else
		{
			//USER PRESENT//
			//gets user into LoginCredential Class (entity class)
			LoginCredential loginCred = loginData.getLoginCredential(userIndexTest);
			if (!loginCred.isPassword(password))
				//WRONG PASSWORD//
				JOptionPane.showMessageDialog(null,"Incorrect Password","You are Wrong",JOptionPane.INFORMATION_MESSAGE);
			else
			{
				//Accesses person.dat binary file and gets all person (profile) info
				FileImplementation personDataObj = new FileImplementation();
				Session_DB database = Session_DB.getInstance();
				
				int newSessionID = database.getNewSessionID();
				//if getting new session ID failed, using a random number, so if it is negative then you dont trust the number that much, still can be an identifier
				if (newSessionID == -1) {
					Random random = new Random();
					newSessionID = random.nextInt(5000)- 10000;
				}
				
				MainFrame maintemp = currentSession.getFrameRef();
				
				
				//checks if user has index (present) in it
				if (personDataObj.getIndex(loginCred.getID()) == -1)
					
					//Program Design: only Librarians can register Librarians, 
					//				  thus if user is new (self-Registered), they are by default Members
				{
//					currentSession.setAccessMode(accessMode.Member);
					
					currentSession = new Session(accessMode.Member, loginCred.getUsername(), maintemp, newSessionID,loginCred.getID() );
				}
				else
					//had profile info: gets accessType (member or librarian) from person.dat and edits currentSession 
					//CHANGE THIS: this will be further implemented to be deleted and a new instance of Session to be created 
					//				and time in and time out logged
				{
					//currentSession.setAccessMode(personDataObj.getMember(personDataObj.getIndex(loginCred.getID())).getAccessType());
					currentSession = new Session(personDataObj.getMember(personDataObj.getIndex(loginCred.getID())).getAccessType(), loginCred.getUsername(), maintemp, newSessionID,loginCred.getID() );
				}
				maintemp.setCurrentSession(currentSession);
				
				logAsEffects(currentSession, true); 		  //Shortcut to list of Methods to be done when user changes
				
				//checks if user has filled in person info or not (members only)
				//if yes they will fill in their own profile info
				if (personDataObj.getIndex(loginCred.getID())== -1)
				{
					JOptionPane.showMessageDialog(null, "Please Provide your profile information", "Missing Profile Information", JOptionPane.INFORMATION_MESSAGE);
					currentSession.getFrameRef().getMemberAddPanel().getActionLisnta().redirectTo(loginCred.getID(),accessMode.Member);
					currentSession.getFrameRef().displayPanel(PanelType.memberAdd);
				}
					
			}
		}
		
	}
	
	
	
	//Action when user clicks on Register Button in the Register page(loginPage.java) 
	public static void  registerAction(boolean fillProfileAfter, Session ccurrentSession, String username, String password) {

		// TODO Auto-generated method stub
		currentSession = ccurrentSession; //currentSession is passed around  as a shortcut to different program references
		
		//Accesses login.dat binary file and gets all credential (login) info
		Login_Data loginData = new Login_Data();
		loginData.getAll();
		
		//checks if user was registered or not 
		int userIndexTest = loginData.getIndex(username);
		if (userIndexTest > -1) 
			//USERNAME USED BEFORE//
			JOptionPane.showMessageDialog(null,"Username is not Available","Used",JOptionPane.INFORMATION_MESSAGE);
		else
		{
			//USERNAME AVAILABLE//
			
			//creates a new random ID to be saved in LoginCredential (also shared in Session and Person)
			int newID = loginData.getNewID();
			
			//Writes into login.dat the new user
			loginData.addNewRegisteredUser(new LoginCredential(newID,username,password));
			
			
			if (!fillProfileAfter)  
				//Normally, after users register they are auto logged in
				loginAction(ccurrentSession,username,password);
			else
			{
				//only called by Librarians
				//redirect method will save the info filled in the Person (memberPanel) to person.dat 
				//				UNDER ID mentioned not under the librarians own ID
				// 				this allows librarian to register & fill information about others
				currentSession.getFrameRef().getMemberAddPanel().getActionLisnta().redirectTo(newID, accessMode.Librarian);
				currentSession.getFrameRef().displayPanel(PanelType.memberAdd);
			}
		}
		
	}
	
	//Multiple methods that add/remove different buttons and menu items from MainFrame depending on the 
	//AccessType the new user has.
	public static void logAsEffects(Session currentSession, boolean Login ) {
		
		
		currentSession.getFrameRef().getSignInPanel().reform(currentSession);
		currentSession.getFrameRef().menuReform(currentSession);
		currentSession.getFrameRef().getSignInPanel().getUserLabel().setUserName(currentSession);
		currentSession.getFrameRef().clearPanels();
		currentSession.getFrameRef().getSearchPanel().getSearchOptions().reform(currentSession);;
		
		if (Login) {
			currentSession.addEvent(new SessionEvent(currentSession.getSessionID(), currentSession.getID(), new Date().toString(), currentSession.getUsername()+ " Logged in."));
		}
		
	}
	
	public static void logoutAction() {
		currentSession.addEvent(new SessionEvent(currentSession.getSessionID(), currentSession.getID(), new Date().toString(), currentSession.getUsername()+ " Logged out."));
		
		Session_DB database = Session_DB.getInstance();
		database.syncAllEvents(currentSession.getEvents());
		
		//Change type and name of session //CHANGE THIS
		MainFrame mainref = currentSession.getFrameRef();
		currentSession = new Session(accessMode.Guest,"Guest", mainref,0,0);
		mainref.setCurrentSession(currentSession);
		//calling methods to refresh mainframe according to accessType
		logAsEffects(currentSession, false);
		
	}
	
	
}
