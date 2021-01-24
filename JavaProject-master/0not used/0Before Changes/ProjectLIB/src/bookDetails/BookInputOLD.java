package bookDetails;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class BookInputOLD extends JFrame{
	public static void main(String[] args) {
		//BookDetails frame = new BookDetails();
		BookInputOLD frame = new BookInputOLD();
		frame.setTitle("Taking Inputs");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,300);
		frame.setVisible(true);
	}
	
	
	public BookInputOLD() {

		BookInput_Panel panel = new BookInput_Panel();
		add(panel);

		
		
//		this.getContentPane().setBackground(Color.lightGray);
	}
	
	private JLabel lblIsbn,lblBookName,lblAuthor,lblPublisher,lblGenre, lblCollection;
	private JTextField txtIsbn, txtBookName,txtAuthor,txtPublisher,txtGenre,txtCollection;
	private JButton butSave, butClear;
	
	public class BookInput_Panel extends JPanel {
		public BookInput_Panel() {
		this.initialize();
		butSave.addActionListener(new SaveButtonHandler());
		butClear.addActionListener(new ClearButtonHandler());
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
			
			lblCollection = new JLabel("Collection: ");
			txtCollection = new JTextField();
			
			
			butClear =  new JButton("Clear");
			butSave = new JButton("Save");
			
			this.setLayout(new GridLayout(7,2,15,15));
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
			
			this.add(lblCollection);
			this.add(txtCollection);
			
			this.add(butClear);
			this.add(butSave);
			
//			this.setTitle("Taking Inputs");
//			this.setLocationRelativeTo(null);
//			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setSize(600,300);
//			this.setVisible(true);
//			this.getContentPane().
			setBackground(Color.lightGray);
		}
		private class SaveButtonHandler implements ActionListener{
			public void actionPerformed(ActionEvent e) {
			
				String bookName = txtBookName.getText();
				String isbn = txtIsbn.getText();
				String author = txtAuthor.getText();
				String genre = txtGenre.getText();
				String publisher= txtPublisher.getText();
				String collection = txtCollection.getText();
				
				//Function to save onto the database
			}
		}
		private class ClearButtonHandler implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				txtBookName.setText("");
				txtIsbn.setText("");
				txtAuthor.setText("");
				txtGenre.setText("");
				txtPublisher.setText("");
				txtCollection.setText("");
				
			}
		}
	}
}
