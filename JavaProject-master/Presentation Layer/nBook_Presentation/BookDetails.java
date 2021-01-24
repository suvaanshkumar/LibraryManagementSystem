package nBook_Presentation;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.*;

import nBook_Data.BookData;
import rLogin_Data.Session;
import rLogin_Data.SessionEvent;
import rLogin_Data.Session_DB;
import sPersonBookRecord.PersonBookRecordDBAccess;
import sPerson_Data.accessMode;
import sPersonBookRecord.PersonBookRecord;
import rLogin_Data.Session;

import rMainFrame_Presentation.ThemeFactory;

public class BookDetails extends JPanel{
	private JLabel lblIsbn,lblBookName,lblAuthor,lblPublisher,lblGenre, lblStatus;
	private JTextField txtIsbn, txtBookName,txtAuthor,txtPublisher,txtGenre, txtStatus;
	private JButton butAddBook, butReturnBook;
	private PersonBookRecordDBAccess personBook;

	Session currentSession; 
	
	public BookDetails(Session currentSession) {
		this.currentSession = currentSession;

		this.initialize();
		butAddBook.addActionListener(new AddBookHandler());
		butReturnBook.addActionListener(new ReturnBookHandler());

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
		
		butAddBook=new JButton("Issue Book");
		butReturnBook = new JButton("Return Book");
		
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
		
		this.add(lblStatus);
		this.add(txtStatus);
		//Guests can't reserve books
//		if (currentSession.getAccessMode().ordinal() !=  accessMode.Guest.ordinal())
		{
			
			this.add(butAddBook);
			this.add(butReturnBook);
		}
		ThemeFactory.setButtonTheme(butAddBook);
		ThemeFactory.setButtonTheme(butReturnBook);
		
		
		txtIsbn.setEditable(false);
		txtBookName.setEditable(false);
		txtAuthor.setEditable(false);
		txtGenre.setEditable(false);
		txtPublisher.setEditable(false);
		txtStatus.setEditable(false);
	}
	
	public void fillData(BookData bd) {
		personBook= new PersonBookRecordDBAccess();
		txtIsbn.setText(bd.getISBN());
		txtBookName.setText(bd.getBookName());
		txtAuthor.setText(bd.getAuthorName());
		txtGenre.setText(bd.getGenre());
		txtPublisher.setText(bd.getpublisher());
		
		boolean status =personBook.getRecord(bd.getISBN());
		
		if (status)
			{
			txtStatus.setText("Not Available");
			butAddBook.setBackground(Color.GRAY);;
			}
		else
			txtStatus.setText("Available");
		

	}
	private class AddBookHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			if(txtStatus.getText().equalsIgnoreCase("available")) {
				try {

				personBook.addRecord(new PersonBookRecord(currentSession.getID(),txtIsbn.getText()));

				
				
				JOptionPane.showMessageDialog(null,"Book Reserved!","DataBase Transaction made.",JOptionPane.INFORMATION_MESSAGE);
				txtStatus.setText("Not Available");
				
				currentSession.addEvent(new SessionEvent(currentSession.getSessionID(), currentSession.getID(), new Date().toString(), currentSession.getUsername()+ " Reserved " + txtBookName.getText() + " ("+ txtIsbn.getText()  + ") Successfully."));
				Session_DB.getInstance().syncAllEvents(currentSession.getEvents());
				
				}
				catch(Exception ex1) {
					ex1.printStackTrace();
			
				}
				
			}
			else {
				JOptionPane.showMessageDialog(null,"Book is Unavailable!","Wrong Operation!",JOptionPane.ERROR_MESSAGE);
				
				
			}				
		}
		}
	private class ReturnBookHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(txtStatus.getText().equalsIgnoreCase("available")) 
			{
				JOptionPane.showMessageDialog(null,"Book is Not Issued to you!","Wrong Operation!",JOptionPane.ERROR_MESSAGE);
			}
			else {
				try {
				personBook.removeRecord(lblIsbn.getName());
				JOptionPane.showMessageDialog(null,"Book Returned!","DataBase Transaction made.",JOptionPane.INFORMATION_MESSAGE);
				txtStatus.setText("Available");
				currentSession.addEvent(new SessionEvent(currentSession.getSessionID(), currentSession.getID(), new Date().toString(), currentSession.getUsername()+ " Returned " + txtBookName.getText() + " ("+ txtIsbn.getText()  + ") Successfully."));
				Session_DB.getInstance().syncAllEvents(currentSession.getEvents());
				personBook.closeConnection();
				}
				catch(Exception ex2) {
					ex2.printStackTrace();
				}
			}
			
		}
	}

}
