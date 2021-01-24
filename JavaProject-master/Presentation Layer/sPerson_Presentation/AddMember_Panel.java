package sPerson_Presentation;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import rLogin_Data.Session;
import rMainFrame_Data.PanelType;
import rMainFrame_Presentation.ThemeFactory;
import sPerson_Data.FileImplementation;
import sPerson_Data.Person;
import sPerson_Data.PersonConstants;
import sPerson_Data.accessMode;

public class AddMember_Panel extends JPanel {
	//creating components
	private Session currentSession;
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
	protected JLabel labelHeading= new JLabel("Add Member");
	protected JButton addMember= new JButton("Add member");
	protected AddMemberClassAction actionlistna;
	
	public AddMember_Panel(Session currentSession, int id, accessMode targetAccessType) {
		this.currentSession = currentSession;
		setSize(400,800);
		//wanted to use set bounds to add stuff as per pixels
		setLayout(null);
		//to check if the file exists
		//System.out.println(new File("rsz_su.png").exists());
        labelHeading.setFont(new Font("Serif", Font.ITALIC+Font.BOLD, 25));
        labelHeading.setBounds(120, 5, 200, 30);
        add(labelHeading);
		labelName.setBounds(50, 40, 100, 50);
		name.setBounds(150, 40, 180, 40);
		//name.setEnabled(true);
		//Making enabled false to not to let the customer change edit the field
		
		
		labelLastName.setBounds(50, 80, 100, 50);
		lastName.setBounds(150, 80, 180, 40);
		//lastName.setEnabled(false);
		
		labelPhone.setBounds(50, 120, 100, 50);
		phone.setBounds(150, 120, 180, 40);
		//phone.setEnabled(false);
		
		labelEmail.setBounds(50, 160, 100, 50);
		email.setBounds(150, 160, 180, 40);
		//email.setEnabled(false);
		
		labelAddress.setBounds(50, 200, 100, 50);
		address.setBounds(150, 200, 180, 40);
		//address.setEnabled(false);
		//showBooksList.setBounds(100, 650, 200, 50);
		JLabel jl= new JLabel(new ImageIcon("images/rsz_su.png"));
		jl.setBounds(60, 320, 250, 400);
		this.add(jl);
		

		//add(showBooksList);
		
		//showBooksList.setVisible(false);
		//adding functions to button click
		actionlistna = new AddMemberClassAction(id, targetAccessType);
		addMember.addActionListener(actionlistna);
		
		addMember.setMnemonic('a');
		ThemeFactory.setButtonTheme(addMember);
		addMember.setBounds(100, 650, 200, 50);
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
		add(addMember);
		//this.getContentPane().
		setBackground(Color.lightGray);	
		revalidate();
		
		// TODO Auto-generated constructor stub
		
	}
	
	public AddMemberClassAction getActionLisnta() {
		return actionlistna;
	}
	
	
	public JButton getAddMemberButton() {
		return addMember;
	}
	

	
	public void inflate(Person mm) {
		name.setText(mm.getName());
		lastName.setText(mm.getLastName());
		phone.setText((mm.getNumber())+"");
		address.setText(mm.getAddress());
		email.setText(mm.getEmail());
		
		
	}
	
	public class AddMemberClassAction implements ActionListener {
		private int id;
		private accessMode accessType; 
		public AddMemberClassAction(int id, accessMode targetAccessType) {
			this.id = id;
			this.accessType = targetAccessType;
		}
		
		public void redirectTo(int id, accessMode targetAccessType) {
			this.id = id;
			this.accessType = targetAccessType;
		}
		
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			PersonConstants pc=new PersonConstants();
			Date newDate= new Date();
			try {
				DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
				newDate = java.text.DateFormat.getDateInstance().parse(dateFormat.format(new Date()));
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(name.getText().length()<=pc.FIRST_NAME_SIZE && lastName.getText().length()<=pc.LAST_NAME_SIZE && email.getText().length()<=pc.EMAIL_SIZE && phone.getText().length()<=18 && address.getText().length()<=pc.ADDRESS_SIZE) {
			Person pp = new Person(id, accessType
					,name.getText(), lastName.getText()
					, email.getText()
					, Long.parseLong(phone.getText())
					, newDate
					, address.getText()) ;
			// TODO Auto-generated method stub
			FileImplementation fe=new FileImplementation();
			fe.addNewPerson(pp);
			name.setText("");
			lastName.setText("");
			email.setText("");
			phone.setText("");
			address.setText("");
			JOptionPane.showMessageDialog(null, "Profile Information updated Successfully");
			currentSession.getFrameRef().displayPanel(PanelType.none);
			}
			else
				JOptionPane.showMessageDialog(null, "invalid entry");
		}
	}
	
	
}


