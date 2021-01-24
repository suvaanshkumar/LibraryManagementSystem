package memberDetails;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.GregorianCalendar;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import data.accessMode;
import mainFrame.Session;


public class BaseGui extends JFrame{
	//creating components
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BaseGui base = new BaseGui();
		base.setSize(400,800);
		base.setLocationRelativeTo(null);
		base.setVisible(true);
	}
	
	public BaseGui() {
		
		//setBackground(Color.GREEN);
		
		AddMember_Panel panel = new AddMember_Panel(new Session(accessMode.Guest,"",null), 2313, accessMode.Member);
		add(panel);
		
	}
}
	
