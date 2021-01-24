package bookDetails;

import java.awt.*;
import javax.swing.*;

import mainFrame.SignInPanel;

public class BookDetailsoLD extends JFrame {
	public static void main(String[] args) {
		//BookDetails frame = new BookDetails();
		BookDetailsoLD frame = new BookDetailsoLD();
		frame.setTitle("BookDetails");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,300);
		frame.getContentPane().setBackground(Color.lightGray);
		frame.setVisible(true);
	}
	
	public BookDetailsoLD() {

	BookDetails_Panel panel = new BookDetails_Panel();
	add(panel);
	
	}

	
	
	public class BookDetails_Panel extends JPanel{
		private JLabel lblIsbn,lblBookName,lblAuthor,lblPublisher,lblGenre, lblStatus;
		private JTextField txtIsbn, txtBookName,txtAuthor,txtPublisher,txtGenre, txtStatus;
		
		public BookDetails_Panel() {
			this.initialize();
			
		}
		public void initialize() {
			lblIsbn=new JLabel("ISBN: ");
			txtIsbn = new JTextField();
			
			lblBookName=new JLabel("Book name: ");
			txtBookName = new JTextField();
			
			lblAuthor=new JLabel("Author: ");
			txtAuthor = new JTextField();
			
			lblGenre=new JLabel("Genre: ");
			txtGenre = new JTextField();
			
			lblPublisher=new JLabel("Publisher: ");
			txtPublisher = new JTextField();
			
			lblStatus = new JLabel("Status: ");
			txtStatus = new JTextField();
			
			
			this.setLayout(new GridLayout(6,2,15,15));
			this.add(lblIsbn);
			this.add(txtIsbn);
			
			this.add(lblBookName);
			this.add(txtBookName);
			
			this.add(lblAuthor);
			this.add(txtAuthor);
			
			this.add(lblGenre);
			this.add(txtGenre);
			
			this.add(lblPublisher);
			this.add(txtPublisher);
			
			this.add(lblStatus);
			this.add(txtStatus);
			
			txtIsbn.setEditable(false);
			txtBookName.setEditable(false);
			txtAuthor.setEditable(false);
			txtGenre.setEditable(false);
			txtPublisher.setEditable(false);
			txtStatus.setEditable(false);
			
	//		this.setTitle("BookDetails");
	//		this.setLocationRelativeTo(null);
	//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setSize(600,300);
	//		this.setVisible(true);
			//this.getContentPane().
			setBackground(Color.lightGray);
		}
	
	
	
	}
}
