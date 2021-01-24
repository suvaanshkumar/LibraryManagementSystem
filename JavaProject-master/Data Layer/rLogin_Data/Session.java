package rLogin_Data;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import rMainFrame_Presentation.MainFrame;
import sPerson_Data.accessMode;

//Entity class for Session, 
// has two key things, 
//		accessMode, which determines what user can access from the program
// 		MainFrame reference, from which dev can access reference to basically all the program

//thus you will see an object frequently named currentSession being passed around between methods, to retain these two references

//TO CHANGE: sessions will log time used, and will be created and destroyed when user logs out, and using finalize will logout and save info in Database
public class Session {
	private accessMode accessType;
	private String username;
	private MainFrame frameRef;
	private int sessionID; 
	private int iD;
	private List<SessionEvent> events = new ArrayList<SessionEvent>(); 


	//more can be added here, like: 
		//login time, ... 
	
	
	

	
	public Session(accessMode cMode, String cName, MainFrame cframeRef, int sessionID, int iD) {
		setAccessMode(cMode);
		setUserName(cName);
		setFrameRef(cframeRef);
		this.sessionID = sessionID;
		this.iD = iD;
	}

	public int getSessionID() {
		return sessionID;
	}

	public void setSessionID(int sessionID) {
		this.sessionID = sessionID;
	}

	public int getID() {
		return iD;
	}

	public void seID(int iD) {
		this.iD = iD;
	}

	public List<SessionEvent> getEvents() {
		return events;
	}

	public void addEvent(SessionEvent event) {
		events.add(event);
	}
	
	

	public void setFrameRef(MainFrame cframeRef) {
		this.frameRef = cframeRef;
	}
	
	public MainFrame getFrameRef() {
		return frameRef;
	}
	
	public void setAccessMode(accessMode cMode) {
		accessType = cMode;
	}
	
	public void setUserName(String cName) {
		username = cName;
	}
	
	public accessMode getAccessMode() {
		return accessType;
	}
	
	public String getUsername() {
		return username;
	}
	
	
}


