package sPerson_Presentation;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import nBook_Data.BookBin;
import nBook_Data.BookData;
import rLogin_Data.Session;
import rMainFrame_Presentation.ThemeFactory;
import sPersonBookRecord.PersonBookRecord;
import sPersonBookRecord.PersonBookRecordDBAccess;
import sPerson_Data.Person;

public class BaseGui_Panel extends JPanel{
	Session currentSession;
	protected JTextField name=new JTextField();
	protected JTextField lastName=new JTextField();
	protected JTextField address=new JTextField();
	protected JTextField email=new JTextField();
	protected JTextField phone=new JTextField();
	protected JLabel labelAddress= new JLabel("Address");
	protected JLabel labelName= new JLabel("Name");
	protected JLabel labelLastName= new JLabel("Last Name");
	protected JLabel labelEmail= new JLabel("Email");
	protected JLabel labelPhone= new JLabel("Phone");
	protected JLabel labelHeading= new JLabel("Member Info!");
	//protected JButton showHistory= new JButton("Show History");
	protected JButton ShowBooksDue= new JButton("Show Books due");
	protected JLabel jl3=new JLabel("books due listbox");
	protected JLabel jl4=new JLabel("transaction history listbox");
	private int iD;

	protected JList<BookData> showBooksList=new JList<BookData>();
	public BaseGui_Panel(Session currentSession) {
		this.currentSession = currentSession;
		setSize(400,800);
		//wanted to use set bounds to add stuff as per pixels
		setLayout(null);
		//to check if the file exists
		//System.out.println(new File("ic.png").exists());
        labelHeading.setFont(new Font("Serif", Font.ITALIC+Font.BOLD, 25));
        labelHeading.setBounds(120, 5, 200, 30);
        add(labelHeading);
		labelName.setBounds(50, 40, 100, 50);
		name.setBounds(150, 40, 180, 40);
		name.setEnabled(false);
		name.setDisabledTextColor(Color.blue);
		//Making enabled false to not to let the customer change edit the field
		
		
		labelLastName.setBounds(50, 80, 100, 50);
		lastName.setBounds(150, 80, 180, 40);
		lastName.setEnabled(false);
		lastName.setDisabledTextColor(Color.blue);
		
		labelPhone.setBounds(50, 120, 100, 50);
		phone.setBounds(150, 120, 180, 40);
		phone.setEnabled(false);
		phone.setDisabledTextColor(Color.blue);
		
		labelEmail.setBounds(50, 160, 100, 50);
		email.setBounds(150, 160, 180, 40);
		email.setEnabled(false);
		email.setDisabledTextColor(Color.blue);
		
		labelAddress.setBounds(50, 200, 100, 50);
		address.setBounds(150, 200, 180, 40);
		address.setEnabled(false);
		address.setDisabledTextColor(Color.blue);
		
		//showHistory.setBounds(100, 280, 200, 50);
		//add(showHistory);
		//ThemeFactory.setButtonTheme(showHistory);
		ShowBooksDue.setBounds(100, 340, 200, 50);
		ThemeFactory.setButtonTheme(ShowBooksDue);
		add(ShowBooksDue);
		showBooksList.setBounds(100, 650, 200, 50);
		JLabel jl= new JLabel(new ImageIcon("images/rsz_su.png"));
		jl.setBounds(60, 320, 250, 400);
		this.add(jl);
		jl3.setBounds(100, 630, 200, 50);
		add(jl3);
		jl3.setVisible(false);
		jl4.setBounds(100, 630, 200, 50);
		add(jl4);
		jl4.setVisible(false);

		add(showBooksList);
		
		showBooksList.setVisible(false);
		//adding functions to button click
		/*showHistory.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "No History yet");
				jl4.setVisible(true);
				showBooksList.setVisible(true);
				jl3.setVisible(false);
			}
		});
		*/
		ShowBooksDue.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				jl3.setVisible(true);
				jl4.setVisible(false);
				showBooksList.setVisible(true); 
				  JFrame f;    
				        
				    f=new JFrame();    
				    String data[][] = new String[5][6];
				    ArrayList<String> al=new PersonBookRecordDBAccess().getRecord(iD);
				    for (String isbn : al) {
				    	BookData bd=new BookBin().getBook(isbn);
				
				    	data[0][0]= bd.getBookName();
				    	data[0][1]=bd.getAuthorName();
				    	data[0][2]=bd.getISBN();
				    	data[0][3]=bd.getGenre();
				    	data[0][4]=bd.getpublisher();
				    	data[0][5]=bd.getCollection();
					
				    }
				    String column[]={"bookName","authorName","iSBN","genre","publisher","collection"};         
				    JTable jt=new JTable(data,column);    
				    jt.setBounds(30,40,200,300);          
				    JScrollPane sp=new JScrollPane(jt);    
				    f.add(sp);          
				    f.setSize(300,400);    
				    f.setVisible(true); 
			    
				 
				//showBooksList.add(new Book());
				//System.out.println("hihi");
			}
		});
		//showHistory.setMnemonic('h');
		ShowBooksDue.setMnemonic('b');
		add(labelPhone);
		add(phone);
		add(labelName);
		add(name);
		add(labelLastName);
		add(lastName);
		add(labelAddress);
		add(address);
		add(labelEmail);
		add(email);
		
		//this.getContentPane().
		setBackground(Color.lightGray);	
		revalidate();
		
	}
	public void inflate(Person mm) {
		name.setText(mm.getName());
		lastName.setText(mm.getLastName());
		phone.setText((mm.getNumber())+"");
		address.setText(mm.getAddress());
		email.setText(mm.getEmail());
		
		
	}
	
	public void setID(int iD)
	{
		this.iD = iD;
	}
	
	
}