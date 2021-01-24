package nBook_Presentation;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

import rLogin_Data.Session;
import sPerson_Data.accessMode;

public class Book_TESTER extends JFrame {
	
	public static void main(String[] args) {
		new Book_TESTER().setVisible(true);
	
		
	}
	
	public Book_TESTER() {
		
		this.setTitle("BookDetails");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//	this.add(new BookInput(),BorderLayout.CENTER);
		Session currentSession = new Session(accessMode.Guest,"",null,0,0);
		this.add(new BookDetails(currentSession), BorderLayout.CENTER);
		this.setSize(600,300);
		
		this.getContentPane().setBackground(Color.lightGray);
	}
	

}
