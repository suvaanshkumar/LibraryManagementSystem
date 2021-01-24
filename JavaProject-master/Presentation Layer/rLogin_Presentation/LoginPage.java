package rLogin_Presentation;

import java.awt.Dimension;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import rLogin_Business.Login_Business;
import rLogin_Data.Session;
import rMainFrame_Presentation.ThemeFactory;

//Login Page, used both for Register and Login Pages, 
//the difference is the buttons loaded at the end

public class LoginPage extends JPanel implements rLogin_Data.LoginConstants{
	protected boolean registerMode;
	protected JPanel insidePanel;
	protected JButton btnLogin , btnClear,btnRegister ;
	protected JTextField txtName;
	protected JCheckBox chxPassword;
	protected JPasswordField txtPassword;
	protected JLabel lblName,lblPassword;
	protected Session currentSession;
	protected RegisterAction registaAction; 

	//register boolean determines what form it is
	public LoginPage(boolean register, Session currentSession) {
		// TODO Auto-generated constructor stub
		this.currentSession = currentSession;
		registerMode = register;
		insidePanel = new JPanel();
	    	
		
	    insidePanel.setSize(600,ThemeFactory.TEXTBOX_HEIGHT*4);
	    insidePanel.setMinimumSize(new Dimension(600,ThemeFactory.TEXTBOX_HEIGHT*4));
	    insidePanel.setLayout(new FlowLayout());
	    
	    //Layout panels to keep textboxes looking nice
	    JPanel labelPanel = new JPanel();
	    labelPanel.setLayout(new GridLayout(4, 1));
	    labelPanel.setPreferredSize(new Dimension(insidePanel.getWidth()/4,insidePanel.getHeight()));
	    
	    JPanel BoxesPanel = new JPanel();
	    BoxesPanel.setLayout(new GridLayout(4, 1));
	    BoxesPanel.setPreferredSize(new Dimension(insidePanel.getWidth()*2/4,insidePanel.getHeight()));
	    
	    JPanel ButtonsPanel = new JPanel();
	    ButtonsPanel.setLayout(new GridLayout(1, 2,5,5));
	    ButtonsPanel.setPreferredSize(new Dimension(BoxesPanel.getWidth()/2,BoxesPanel.getHeight()/4));
	    
	    
	        lblName =new JLabel("Name");
	        lblName.setHorizontalAlignment(SwingConstants.RIGHT);
	        lblName.setFont(ThemeFactory.BIGGER_FONT);
	        
	        lblPassword=new JLabel("Password");
	        lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
	        lblPassword.setFont(ThemeFactory.BIGGER_FONT);
	        
	        txtName=new JTextField();
	        txtName.setFont(ThemeFactory.TEXTBOX__FONT);

	         txtPassword=new JPasswordField();
	         
	         txtPassword.setEchoChar('*');
	         txtPassword.setFont(ThemeFactory.TEXTBOX__FONT);

	         chxPassword = new JCheckBox("Show Password");
	         chxPassword.setFont(ThemeFactory.BIGGER_FONT);
	         if (registerMode) chxPassword.setSelected(true); else chxPassword.setSelected(false);
	         chxPassword.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					if (chxPassword.isSelected()) txtPassword.setEchoChar((char)0); else  txtPassword.setEchoChar('*');
				}
			});
	         
	        btnLogin =new JButton("Log In");
	        ThemeFactory.setButtonTheme(btnLogin);
	        btnLogin.addActionListener(new LoginAction());
	        
	        btnRegister =new JButton("Register");
	        ThemeFactory.setButtonTheme(btnRegister);
	        registaAction = new RegisterAction();
	        btnRegister.addActionListener(registaAction);
	        
	        btnClear =new JButton("Clear");
	        ThemeFactory.setButtonTheme(btnClear);
	        btnClear.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					clearFields();
				}
			});
	        
	        //placing components in the boxes
	        labelPanel.add(lblName);
	        labelPanel.add(lblPassword);
	        BoxesPanel.add(txtName);
	        BoxesPanel.add(txtPassword);
	        BoxesPanel.add(chxPassword);
	        BoxesPanel.add(ButtonsPanel);
	        ButtonsPanel.add(btnClear);
	        
	        //key point between register form and log. simple.
	        if (register) 
	        {
	        	ButtonsPanel.add(btnRegister);
	        }
	        else
	        {
	        	ButtonsPanel.add(btnLogin);
	        }

	        insidePanel.add(labelPanel);
	        insidePanel.add(BoxesPanel);
//	        insidePanel.setBackground(Color.red);
	        
	        
	 		setSize(850, 700);
			setMinimumSize(new Dimension(800,400));
			setLayout(new FlowLayout());
			add(insidePanel);

	    
	}
	
	public void clearFields() {
		txtName.setText("");
		txtPassword.setText("");
		if (registerMode) chxPassword.setSelected(true); else chxPassword.setSelected(false);
		if (chxPassword.isSelected()) txtPassword.setEchoChar((char)0); else  txtPassword.setEchoChar('*');
	}
	
	//Some Validation
	public boolean validateForm() {
		if (txtName.getText().equalsIgnoreCase("")) 
		{
			JOptionPane.showMessageDialog(null, "Username can't be empty", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if (String.valueOf(txtPassword.getPassword()).equalsIgnoreCase("")) 
		{
			JOptionPane.showMessageDialog(null, "Password can't be empty", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if (txtName.getText().length()>10) 
			{
				JOptionPane.showMessageDialog(null, "Username can't be longer than 10 characters", "Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		
		if (String.valueOf(txtPassword.getPassword()).length()>10) 
		{
			JOptionPane.showMessageDialog(null, String.copyValueOf(txtPassword.getPassword()));
			JOptionPane.showMessageDialog(null, "Password can't be longer than 10 characters", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	
	//All Documentation of these methods are in Login_business
	public RegisterAction getRegistaAction() {
		return registaAction;
	}
	
	
	
	//All Documentation of these methods are in Login_business
	public class LoginAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Login_Business.loginAction(currentSession, txtName.getText(),  String.valueOf(txtPassword.getPassword()));
		}
	}
	
	//All Documentation of these methods are in Login_business
	public class RegisterAction implements ActionListener {
		private boolean fillProfileAfter;
		
		public void setFillProfileAfter(boolean target) {
			fillProfileAfter = target;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if (validateForm())
				Login_Business.registerAction(fillProfileAfter,currentSession, txtName.getText(), String.valueOf(txtPassword.getPassword()));

		}
	}
	

}
