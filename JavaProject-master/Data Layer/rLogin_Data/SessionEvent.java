package rLogin_Data;

import java.util.Date;

public class SessionEvent {
	private int sessionID, iD; 
	private String eventtime;
	private String event; 

	public int getSessionID() {
		return sessionID;
	}



	public SessionEvent(int sessionID, int iD, String eventtime,String event) {
		this.sessionID = sessionID;
		this.iD = iD;
		this.event = event;
		this.eventtime = eventtime;
	}
	
	



	public int getiD() {
		return iD;
	}

	public void setiD(int iD) {
		this.iD = iD;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getDate() {
		return eventtime;
	}

	public void setDate(String eventtime) {
		this.eventtime = eventtime;
	}
	
	
}
