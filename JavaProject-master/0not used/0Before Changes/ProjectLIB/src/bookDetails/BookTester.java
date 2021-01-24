package bookDetails;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

public class BookTester extends JFrame {
	
	public static void main(String[] args) {
		new BookTester().setVisible(true);
	
		
	}
	
	public BookTester() {
		
		this.setTitle("BookDetails");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(new BookInput(),BorderLayout.CENTER);
	//	this.add(new BookDetails(), BorderLayout.CENTER);
		this.setSize(600,300);
		
		this.getContentPane().setBackground(Color.lightGray);
	}
	

}
