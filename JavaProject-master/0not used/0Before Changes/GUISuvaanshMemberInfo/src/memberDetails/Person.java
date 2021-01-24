package memberDetails;

import java.util.Date;

import javax.swing.JTextField;

import data.accessMode;

public class Person {

private int id;
private accessMode accessType;
private String firstName;
private String lastName;
private String email;
private long number;
private Date dateJoined;
private String address;

public Person() {
//	super(); ??
}

public Person(String firstName, String lastName, String email, long number, String address) {
	super();
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.number = number;
	this.address = address;
}

public Person(int id, accessMode accessType, String firstName, String lastName, String email, long number, Date dateJoined,
		String address) {
	//super();
	this.id = id;
	this.accessType = accessType;
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.number = number;
	this.dateJoined = dateJoined;
	this.address = address;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return firstName;
}
public void setName(String name) {
	this.firstName = name;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}


public accessMode getAccessType() {
	return accessType;
}
public void setAccessType(accessMode accessType) {
	this.accessType = accessType;
}
public Date getDateJoined() {
	return dateJoined;
}
public void setDateJoined(Date dateJoined) {
	this.dateJoined = dateJoined;
}
public long getNumber() {
	return number;
}
public void setNumber(long number) {
	this.number = number;
}


}
