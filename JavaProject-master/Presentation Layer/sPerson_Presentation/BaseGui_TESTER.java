package sPerson_Presentation;

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

import rLogin_Data.Session;
import sPerson_Data.accessMode;


public class BaseGui_TESTER extends JFrame{
	//creating components
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BaseGui_TESTER base = new BaseGui_TESTER();
		base.setSize(400,800);
		base.setLocationRelativeTo(null);
		base.setVisible(true);
	}
	
	public BaseGui_TESTER() {
		
		//setBackground(Color.GREEN);
		
		AddMember_Panel panel = new AddMember_Panel(new Session(accessMode.Guest,"",null,0,0), 2313, accessMode.Member);
		add(panel);
		
	}
}
	
