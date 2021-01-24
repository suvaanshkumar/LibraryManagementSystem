
package business;

import javax.swing.JOptionPane;

import data.LoginCredential;
import data.Login_Data;
import data.PanelType;
import data.accessMode;
import mainFrame.Session;
import memberDetails.FileImplementation;

public class Login_Business {
	public static Session currentSession;
	public static void  loginAction(Session ccurrentSession, String username, String password) {

		// TODO Auto-generated method stub
		currentSession = ccurrentSession;
		
		Login_Data loginData = new Login_Data();
		loginData.getAll();
		int userIndexTest = loginData.getIndex(username);
		if (userIndexTest == -1) 
			JOptionPane.showMessageDialog(null,"Username not Registered","Not Registered",JOptionPane.INFORMATION_MESSAGE);
		else
		{
			LoginCredential loginCred = loginData.getLoginCredential(userIndexTest);
			if (!loginCred.isPassword(password))
				JOptionPane.showMessageDialog(null,"Incorrect Password","You are Wrong",JOptionPane.INFORMATION_MESSAGE);
			else
			{
				FileImplementation personDataObj = new FileImplementation();
				
				//This will change when Person is properly implemented
//				switch (username.toLowerCase()) {    	/////////
//				case "navdeep":			/////////
//				case "rashed":			/////////
//				case "suvaansh":		/////////
				if (personDataObj.getIndex(loginCred.getID()) == -1)
					currentSession.setAccessMode(accessMode.Member);
				else
					currentSession.setAccessMode(personDataObj.getMember(personDataObj.getIndex(loginCred.getID())).getAccessType());
//					break;
//				default:
//					
//					break;
//				}
				currentSession.setUserName(username);
				logAsEffects(currentSession);
				
				if (personDataObj.getIndex(loginCred.getID())== -1)
				{
					JOptionPane.showMessageDialog(null, "Please Provide your profile information", "Missing Profile Information", JOptionPane.INFORMATION_MESSAGE);
					currentSession.getFrameRef().getMemberAddPanel().getActionLisnta().redirectTo(loginCred.getID(),accessMode.Member);
					currentSession.getFrameRef().displayPanel(PanelType.memberAdd);
					
				}
					
			}
		}
		
	}
	
	
	
	public static void  registerAction(boolean fillProfileAfter, Session ccurrentSession, String username, String password) {

		// TODO Auto-generated method stub
		currentSession = ccurrentSession;
		
		Login_Data loginData = new Login_Data();
		loginData.getAll();
		int userIndexTest = loginData.getIndex(username);
		if (userIndexTest > -1) 
			JOptionPane.showMessageDialog(null,"Username is not Available","Used",JOptionPane.INFORMATION_MESSAGE);
		else
		{
			int newID = loginData.getNewID();
			loginData.addNewRegisteredUser(new LoginCredential(newID,username,password));
			if (!fillProfileAfter)  //only called by Librarians
				loginAction(ccurrentSession,username,password);
			else
			{
				currentSession.getFrameRef().getMemberAddPanel().getActionLisnta().redirectTo(newID, accessMode.Librarian);
				currentSession.getFrameRef().displayPanel(PanelType.memberAdd);
			}
		}
		
	}
	
	public static void logAsEffects(Session currentSession) {
		currentSession.getFrameRef().getSignInPanel().reform(currentSession);
		currentSession.getFrameRef().menuReform(currentSession);
		currentSession.getFrameRef().getSignInPanel().getUserLabel().setUserName(currentSession);
		currentSession.getFrameRef().clearPanels();
		currentSession.getFrameRef().getSearchPanel().getSearchOptions().reform(currentSession);;
		
		
	}
	
	public static void logoutAction() {
		currentSession.setAccessMode(accessMode.Guest);
		currentSession.setUserName("Guest");
		logAsEffects(currentSession);
	}
	
	
}
