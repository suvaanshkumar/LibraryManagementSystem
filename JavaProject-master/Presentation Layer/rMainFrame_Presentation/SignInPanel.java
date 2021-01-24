package rMainFrame_Presentation;

import javax.swing.*;

import rLogin_Business.Login_Business;
import rLogin_Data.Session;
import rMainFrame_Data.PanelType;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SignInPanel extends JPanel implements rLogin_Data.LoginConstants {
	
	private JButton btnLogin, btnRegister, btnLogout;
	private JLabel logo;
//Add Book and Add Librarian (only should be visible to Librarians)
	private AdminButtons adminButtons;
//Shows who is signed in
	private UserLabel userLabel;
	private Session currentSession;
	
	public SignInPanel(Session currentSession) {
			this.currentSession = currentSession;
			setLayout(new FlowLayout(FlowLayout.LEFT));
			this.setSize(500,200);
			this.setBackground(Color.WHITE);
			logo = new JLabel(new ImageIcon("images/library_100px.png"));
			logo.setSize(100,100);
			logo.setPreferredSize(new Dimension(100,100));
			add(logo);
			
			
			btnLogin = new JButton("Log In");
			ThemeFactory.setButtonTheme(btnLogin , 100,100);
			btnLogin.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					currentSession.getFrameRef().displayPanel(PanelType.login);
				}
			});

			btnRegister = new JButton("Register");
			ThemeFactory.setButtonTheme(btnRegister, 100,100);
			btnRegister.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					//inside RegisterAction Listener it tells if it wants to fill profile after or not, 
					//Program Design: only librarians can register librarians, so they dont have to sign out to fill profile of someone else
					currentSession.getFrameRef().getRegisterPage().getRegistaAction().setFillProfileAfter(false);
					currentSession.getFrameRef().displayPanel(PanelType.register);
				}
			});

			btnLogout = new JButton("Log Out");
			btnLogout.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					Login_Business.logoutAction();
				}
			});
			ThemeFactory.setButtonTheme(btnLogout , 100,100);
			
			userLabel = new UserLabel();
			adminButtons = new AdminButtons();
			
			
			reform(currentSession);
			userLabel.setUserName(currentSession);
			add(userLabel);
	}
	
	public UserLabel getUserLabel() {
		return userLabel;
	}
	
	
//Method to Add/Remove the buttons depending on who is signed in
	public void reform(Session currentSession) {
		switch(currentSession.getAccessMode()) {
		case Guest:
				if (this.isAncestorOf(btnLogout)) remove(btnLogout);
				if (this.isAncestorOf(adminButtons)) remove(adminButtons);
				if (!this.isAncestorOf(btnLogin)) add(btnLogin,1);
				if (!this.isAncestorOf(btnRegister)) add(btnRegister,1);
				
			break;
		
		case Member:
				if (this.isAncestorOf(btnLogin)) remove(btnLogin);
				if (this.isAncestorOf(btnRegister)) remove(btnRegister);
				if (this.isAncestorOf(adminButtons)) remove(adminButtons);//remove(adminButtons);
				if (!this.isAncestorOf(btnLogout)) add(btnLogout,1);
				
			break;
			
		case Librarian:
			if (this.isAncestorOf(btnLogin)) remove(btnLogin);
			if (this.isAncestorOf(btnRegister)) remove(btnRegister);
			if (!this.isAncestorOf(btnLogout)) add(btnLogout,1);
			if (!this.isAncestorOf(adminButtons)) add(adminButtons,1);
		
		break;
		
		}
		revalidate();
		repaint();
	}
	
	
	public class AdminButtons extends JPanel {
		private JButton addBook, addLibrarian;
		private JLabel lblspacer;
		public AdminButtons() {
			setLayout(new BorderLayout());
			setBackground(Color.WHITE);
			setPreferredSize(new Dimension(150,100));
			addBook = new JButton("Add Book");
			ThemeFactory.setButtonTheme(addBook, 150,45);
			addBook.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					currentSession.getFrameRef().displayPanel(PanelType.bookAdd);
				}
			});
			addLibrarian = new JButton("Add Librarian");
			addLibrarian.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					currentSession.getFrameRef().getRegisterPage().getRegistaAction().setFillProfileAfter(true);
					currentSession.getFrameRef().displayPanel(PanelType.register);
				}
			});
			ThemeFactory.setButtonTheme(addLibrarian, 150,45);
			lblspacer = new JLabel();
			lblspacer.setBackground(Color.WHITE);
			add(addBook,BorderLayout.NORTH);
			add(lblspacer, BorderLayout.CENTER);
			add(addLibrarian,BorderLayout.SOUTH);
		}
		
		
		
	
	}
	
	
	
	public class UserLabel extends JPanel{
		private JLabel lblSigned, icoUser, lblUser;
		
		public UserLabel() {
			setBackground(Color.WHITE);
			setLayout(new BorderLayout());
			lblSigned = new JLabel("Signed as");
			setLabelTheme(lblSigned, false);
			lblSigned.setFont(ThemeFactory.VIEW_AS__FONT);
			add(lblSigned, BorderLayout.NORTH);
			
			
			icoUser = new JLabel(new ImageIcon("images/user.png"));
			add(icoUser, BorderLayout.WEST);
			lblUser = new JLabel("UserName");
			setLabelTheme(lblUser, true);
			add(lblUser, BorderLayout.CENTER);
			
		}
	
		
	//depending on who is signed in there is a slight theme change
		public void setLabelTheme(JLabel lbl, boolean isUser) {
			int fontStyle;
			
			if (isUser) 
				{
					fontStyle = Font.BOLD;
					lbl.setForeground(Color.BLUE);
				}
			else 
				fontStyle  = Font.PLAIN;
			
			lbl.setBackground(Color.WHITE);
			lbl.setFont(new Font(ThemeFactory.PERSON_NAME__FONT.getFamily(),fontStyle,ThemeFactory.PERSON_NAME__FONT.getSize()));
		}
	
	//renames the logged in user with some theme changes
		public void setUserName(Session currentSession) 
		{
			switch(currentSession.getAccessMode()) {
			case Guest:
				lblUser.setText("Guest");
				lblUser.setForeground(Color.GRAY);
				lblSigned.setText("Viewed as");
				icoUser.setIcon(null);

				break;
			case Librarian:
				lblUser.setForeground(Color.BLUE);
				lblSigned.setText("Signed as");
				lblUser.setText(currentSession.getUsername());
				icoUser.setIcon(new ImageIcon("images/librarian_25px.png"));

				break;
			case Member:
				lblUser.setForeground(Color.BLUE);
				lblSigned.setText("Signed as");
				lblUser.setText(currentSession.getUsername());
				icoUser.setIcon(new ImageIcon("images/member_25px.png"));
				break;
			}
			
			setLabelTheme(lblUser, true);
			
		}
		
	}

   
	
}
