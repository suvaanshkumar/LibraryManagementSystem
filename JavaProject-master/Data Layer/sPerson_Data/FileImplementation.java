package sPerson_Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class FileImplementation {
	private File file;
	private RandomAccessFile in;
	private ArrayList<Person> personRecordList = new ArrayList<Person>();
	private ArrayList<String> nameIndex = new ArrayList<String>();
	private ArrayList<Integer> idIndex = new ArrayList<Integer>();
	
	
	public ArrayList<Person> getPersonRecordList() {
		return personRecordList;
	}
	public void setPersonRecordList(ArrayList<Person> personRecordList) {
		this.personRecordList = personRecordList;
	}
	public ArrayList<String> getNameIndex() {
		return nameIndex;
	}
	public void setNameIndex(ArrayList<String> nameIndex) {
		this.nameIndex = nameIndex;
	}
	public ArrayList<Integer> getIdIndex() {
		return idIndex;
	}
	public void setIdIndex(ArrayList<Integer> idIndex) {
		this.idIndex = idIndex;
	}
	public FileImplementation() {
		super(); //?
		file = new File(PersonConstants.MEMBER_BIN_FILENAME); 
		getAll();

	} 
	public ArrayList<Person> getAll(){
		try {
			personRecordList.clear();
			nameIndex.clear();
			idIndex.clear();

			in = new RandomAccessFile(file,"rw");
			int count = ((int)in.length())/PersonConstants.C_MEMBER_RECORD_SIZE;
			for (int i = 0; i < count; i++) {
				in.seek(i*PersonConstants.C_MEMBER_RECORD_SIZE);
				int id=in.readInt();
				idIndex.add(id);
				accessMode accessType=accessMode.values()[in.readInt()];
				String name=readString(in,PersonConstants.FIRST_NAME_SIZE);
				String lastName=readString(in,PersonConstants.LAST_NAME_SIZE);
				nameIndex.add(name + " " + lastName);
				String email=readString(in,PersonConstants.EMAIL_SIZE);
				long number=in.readLong();
				String address=readString(in,PersonConstants.ADDRESS_SIZE);

		//		Date dateJoined = java.text.DateFormat.getDateInstance().parse(readString(in,PersonConstants.DATE_JOINED_SIZE));

				personRecordList.add(new Person(id, accessType, name, lastName, email, number, new Date(), address));
				
			}
			in.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return personRecordList;


	}
	public Person getMember(int byIndex) {
		try {
			RandomAccessFile in = new RandomAccessFile(file, "rw");
			in.seek(byIndex*PersonConstants.C_MEMBER_RECORD_SIZE);
			
			int id=in.readInt();
			accessMode accessType=accessMode.values()[in.readInt()];
			String name=readString(in,PersonConstants.FIRST_NAME_SIZE);
			String lastName=readString(in,PersonConstants.LAST_NAME_SIZE);
			String email=readString(in,PersonConstants.EMAIL_SIZE);
			long number=in.readLong();
			String address=readString(in,PersonConstants.ADDRESS_SIZE);

			//Date dateJoined = java.text.DateFormat.getDateInstance().parse(readString(in,PersonConstants.DATE_JOINED_SIZE));

			in.close();
			return new Person(id, accessType, name, lastName, email, number, new Date(), address);
			
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
	
	//adding a new person
	public boolean addNewPerson(Person mbi) {
		if (getIndex(mbi.getId()) != -1) return false; //was registered before
		
		try {
			RandomAccessFile out = new RandomAccessFile(file, "rw");
			out.seek(out.length());
			out.writeInt(mbi.getId());
			out.writeInt(mbi.getAccessType().ordinal());
			writeString(out, PersonConstants.FIRST_NAME_SIZE, mbi.getName());
			writeString(out, PersonConstants.LAST_NAME_SIZE, mbi.getLastName());
			writeString(out, PersonConstants.EMAIL_SIZE, mbi.getEmail());
			out.writeLong(mbi.getNumber());
			writeString(out, PersonConstants.ADDRESS_SIZE, mbi.getAddress());
			DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
			writeString(out, PersonConstants.DATE_JOINED_SIZE, df.format(mbi.getDateJoined()).toString());
			
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return true;
	}
		
	//writting to file
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
		FileImplementation testObj = new FileImplementation();
		Date newDate= new Date();;
		try {
			newDate = java.text.DateFormat.getDateInstance().parse("Jan 12, 1952");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//generating new object
		Person litest = new Person(7, accessMode.Librarian,"suvaansh" , "kumar", "", 0, newDate, "sadsfasf");
//	testing 	Person litest = new Person(7, accessMode.Librarian,"suvaansh" , "kumar", "", 0, "sadsfasf");
		Person mitest = new Person(213, accessMode.Member,"Joe" , "TheMan", "JoeTheMan@mm.com", 6470001111L, newDate, "2309 finch ave, Etobicoke");
		System.out.println(testObj.addNewPerson(litest));
		System.out.println(testObj.addNewPerson(mitest));
		//getting all the objects
		ArrayList<Person> persons = testObj.getAll();
		
		for (Person person : persons) {
			//System.out.println(person.toString());
			System.out.println(person.getName() +" " + person.getLastName() + " (ID: "+ person.getId() + ")\n"
					+ person.getAccessType().toString() + "\n"
					+ person.getEmail() + "\n"
					+ person.getNumber() + "\n"
					+ person.getAddress() + "\n"
					+ person.getDateJoined().toString() + "\n");
			
		}
		System.out.println();
		System.out.println("");
		Person person = testObj.getMember(1);
		System.out.println(person.getName() +" " + person.getLastName() + " (ID: "+ person.getId() + ")\n"
				+ person.getAccessType().toString() + "\n"
				+ person.getEmail() + "\n"
				+ person.getNumber() + "\n"
				+ person.getAddress() + "\n"
				+ person.getDateJoined().toString() + "\n");
		
		
	  
		//testObj.addNewRegisteredUser(new LoginCredential(329823, "Navdeep", "hehe"));
		//testObj.getAll();
		
	}
	}

