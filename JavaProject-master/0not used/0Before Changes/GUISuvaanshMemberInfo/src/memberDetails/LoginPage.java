package memberDetails;

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

import data.LoginCredential;
import data.Login_Data;
import data.PanelType;
import data.ThemeInterface;
import mainFrame.Session;
import data.accessMode;

import business.Login_Business;

public class LoginPage extends JPanel implements data.Constants{
	protected boolean registerMode;
	protected JPanel insidePanel;
	protected JButton btnLogin , btnClear,btnRegister ;
	protected JTextField txtName;
	protected JCheckBox chxPassword;
	protected JPasswordField txtPassword;
	protected JLabel lblName,lblPassword;
	protected Session currentSession;
	protected RegisterAction registaAction; 

	
	public LoginPage(boolean register, Session currentSession) {
		// TODO Auto-generated constructor stub
		this.currentSession = currentSession;
		registerMode = register;
		insidePanel = new JPanel();
	        //f=new JFrame("LOG IN FORM");
	     	//f.setLocation(500,300);
	    	
	    insidePanel.setSize(600,ThemeInterface.TEXTBOX_HEIGHT*4);
	    insidePanel.setMinimumSize(new Dimension(600,ThemeInterface.TEXTBOX_HEIGHT*4));
	    insidePanel.setLayout(new FlowLayout());
	    //insidePanel.setPreferredSize(new Dimension(600,500));
	        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
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
	        lblName.setFont(ThemeInterface.BIGGER_FONT);
	        
	        lblPassword=new JLabel("Password");
	        lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
	        lblPassword.setFont(ThemeInterface.BIGGER_FONT);
	        
	        txtName=new JTextField();
	        txtName.setFont(ThemeInterface.TEXTBOX__FONT);

	         txtPassword=new JPasswordField();
	         
	         txtPassword.setEchoChar('*');
	         txtPassword.setFont(ThemeInterface.TEXTBOX__FONT);

	         chxPassword = new JCheckBox("Show Password");
	         chxPassword.setFont(ThemeInterface.BIGGER_FONT);
	         if (registerMode) chxPassword.setSelected(true); else chxPassword.setSelected(false);
	         chxPassword.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					if (chxPassword.isSelected()) txtPassword.setEchoChar((char)0); else  txtPassword.setEchoChar('*');
				}
			});
	         
	        btnLogin =new JButton("Log In");
	        ThemeInterface.setButtonTheme(btnLogin);
	        btnLogin.addActionListener(new LoginAction());
	        
	        btnRegister =new JButton("Register");
	        ThemeInterface.setButtonTheme(btnRegister);
	        registaAction = new RegisterAction();
	        btnRegister.addActionListener(registaAction);
	        
	        btnClear =new JButton("Clear");
	        ThemeInterface.setButtonTheme(btnClear);
	        btnClear.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					clearFields();
				}
			});
	        

	        labelPanel.add(lblName);
	        labelPanel.add(lblPassword);
	        BoxesPanel.add(txtName);
	        BoxesPanel.add(txtPassword);
	        BoxesPanel.add(chxPassword);
	        BoxesPanel.add(ButtonsPanel);
	        ButtonsPanel.add(btnClear);
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
	
	public RegisterAction getRegistaAction() {
		return registaAction;
	}
	
	
	
	
	public class LoginAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Login_Business.loginAction(currentSession, txtName.getText(),  String.valueOf(txtPassword.getPassword()));
		}
	}
	
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
