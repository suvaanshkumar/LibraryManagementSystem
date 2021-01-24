package mainFrame;

import javax.swing.JFrame;

import data.accessMode;

public class Session {
	private accessMode accessType;
	private String username;
	private MainFrame frameRef;
	//more can be added here, like: 
		//login time, ... 
	public Session(accessMode cMode, String cName, MainFrame cframeRef) {
		setAccessMode(cMode);
		setUserName(cName);
		setFrameRef(cframeRef);
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
