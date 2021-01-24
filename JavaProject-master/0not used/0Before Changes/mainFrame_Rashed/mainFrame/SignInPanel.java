package mainFrame;

import javax.swing.*;

import business.Login_Business;

import data.PanelType;
import data.ThemeInterface;
import memberDetails.AddMember_Panel;
import memberDetails.AddMember_Panel.AddMemberClassAction;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class SignInPanel extends JPanel implements data.Constants {
	
	private JButton btnLogin, btnRegister, btnLogout;
	private JLabel logo;
	private AdminButtons adminButtons;
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
			ThemeInterface.setButtonTheme(btnLogin , 100,100);
			btnLogin.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					currentSession.getFrameRef().displayPanel(PanelType.login);
				}
			});

			btnRegister = new JButton("Register");
			ThemeInterface.setButtonTheme(btnRegister, 100,100);
			btnRegister.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
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
			ThemeInterface.setButtonTheme(btnLogout , 100,100);
			
			userLabel = new UserLabel();
			adminButtons = new AdminButtons();
			
			
			reform(currentSession);
			userLabel.setUserName(currentSession);
			add(userLabel);
	}
	
	public UserLabel getUserLabel() {
		return userLabel;
	}
	
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
		private JButton addBook, addMember;
		private JLabel lblspacer;
		public AdminButtons() {
			setLayout(new BorderLayout());
			setBackground(Color.WHITE);
			setPreferredSize(new Dimension(130,100));
			addBook = new JButton("Add Book");
			ThemeInterface.setButtonTheme(addBook, 130,45);
			addBook.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					currentSession.getFrameRef().displayPanel(PanelType.bookAdd);
				}
			});
			addMember = new JButton("Add Librarian");
			addMember.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					currentSession.getFrameRef().getRegisterPage().getRegistaAction().setFillProfileAfter(true);
					currentSession.getFrameRef().displayPanel(PanelType.register);
				}
			});
			ThemeInterface.setButtonTheme(addMember, 130,45);
			lblspacer = new JLabel();
			lblspacer.setBackground(Color.WHITE);
			add(addBook,BorderLayout.NORTH);
			add(lblspacer, BorderLayout.CENTER);
			add(addMember,BorderLayout.SOUTH);
		}
		
		
		
	
	}
	
	
	
	public class UserLabel extends JPanel{
		private JLabel lblSigned, icoUser, lblUser;
		
		public UserLabel() {
			setBackground(Color.WHITE);
			setLayout(new BorderLayout());
			lblSigned = new JLabel("Signed as");
			setLabelTheme(lblSigned, false);
			lblSigned.setFont(ThemeInterface.VIEW_AS__FONT);
			add(lblSigned, BorderLayout.NORTH);
			
			
			icoUser = new JLabel(new ImageIcon("images/user.png"));
			add(icoUser, BorderLayout.WEST);
			lblUser = new JLabel("UserName");
			setLabelTheme(lblUser, true);
			add(lblUser, BorderLayout.CENTER);
			
		}
		
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
			lbl.setFont(new Font(ThemeInterface.PERSON_NAME__FONT.getFamily(),fontStyle,ThemeInterface.PERSON_NAME__FONT.getSize()));
		}
		
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
