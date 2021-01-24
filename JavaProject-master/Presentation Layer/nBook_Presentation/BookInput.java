package nBook_Presentation;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.*;

import nBook_Data.BookCons;
import rMainFrame_Presentation.ThemeFactory;


public class BookInput extends JPanel implements BookCons{
	private JLabel lblIsbn,lblBookName,lblAuthor,lblPublisher,lblGenre, lblCollection, lblCopyCount;
	private JTextField txtIsbn, txtBookName,txtAuthor,txtPublisher, txtCopyCount;
	private JButton butSave, butClear;
	private JComboBox<String> cbGenre,cbCollection;
	private File file;
	
	public BookInput() {
		this.initialize();
		file = new File(BookCons.FILENAME_BIN);
		butSave.addActionListener(new SaveButtonHandler());
		butClear.addActionListener(new ClearButtonHandler());
	}
	public void initialize() {
		String[] genre= {"Fantasy","Adventure","Romance","Contemporary","Dystopian","Mystery","Horror","Thriller"};
		String[] collection= {"Infants","Kids","Adults","Young Adults"};
		lblIsbn=new JLabel("ISBN: ");
		txtIsbn = new JTextField();
		
		lblBookName=new JLabel("Book name: ");
		txtBookName = new JTextField();
		
		lblAuthor=new JLabel("Author: ");
		txtAuthor = new JTextField();
		
		lblGenre=new JLabel("Genre: ");
		cbGenre = new JComboBox<>(genre);
		
		lblPublisher=new JLabel("Publisher: ");
		txtPublisher = new JTextField();
		
		lblCollection = new JLabel("Collection: ");
		cbCollection = new JComboBox<>(collection);
		
		lblCopyCount = new JLabel("Copy Count: ");
		txtCopyCount = new JTextField();
		
		butSave= new JButton("Save");
		butClear = new JButton("Clear");
		
		this.setLayout(new GridLayout(8,2,15,15));
		this.add(lblIsbn);
		this.add(txtIsbn);
		
		this.add(lblBookName);
		this.add(txtBookName);
		
		this.add(lblAuthor);
		this.add(txtAuthor);
		
		this.add(lblGenre);
		this.add(cbGenre);
		
		this.add(lblPublisher);
		this.add(txtPublisher);
		
		this.add(lblCollection);
		this.add(cbCollection);
		
		this.add(lblCopyCount);
		this.add(txtCopyCount);
		
		this.add(butClear);
		ThemeFactory.setButtonTheme(butClear);
		this.add(butSave);
		ThemeFactory.setButtonTheme(butSave);
		
		butSave.setMnemonic(KeyEvent.VK_S);
		butClear.setMnemonic(KeyEvent.VK_C);
		
	}
	private class SaveButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		
			String bookName = txtBookName.getText();
			String isbn = txtIsbn.getText();
			String author = txtAuthor.getText();
			String genre =(String)cbGenre.getSelectedItem();
			String publisher= txtPublisher.getText();
			String collection = (String)cbCollection.getSelectedItem();
			int copyCount=0;
			try {
			copyCount =Integer.parseInt(txtCopyCount.getText());
			}
			catch(NumberFormatException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null,"You Cannot Leave a field empty!","Error",JOptionPane.ERROR_MESSAGE);
			}
			
			//Validating the input
			
			
			//Function to save onto the file
			
			try {
				if(bookName.isBlank()||isbn.isBlank()||author.isBlank()||publisher.isBlank()) {
					JOptionPane.showMessageDialog(null,"You Cannot Leave a field empty!","Error",JOptionPane.ERROR_MESSAGE);
				}
			else if(bookName.length()>BNSIZE || isbn.length()>ISBNSIZE || author.length()>ANSIZE || publisher.length()>PUBSIZE) 
				{
				  JOptionPane.showMessageDialog(null,"Length of the Input is too long!","Error",JOptionPane.ERROR_MESSAGE);
					
				}
				else {
					RandomAccessFile out = new RandomAccessFile(file, "rw");
					out.seek(out.length());
		
					writeString(out, ISBNSIZE, isbn);
					writeString(out, BNSIZE, bookName);
					writeString(out, ANSIZE, author);
					writeString(out,GENSIZE,genre);
					writeString(out, PUBSIZE, publisher);
					writeString(out, COLSIZE, collection);
					out.writeInt(copyCount);
					out.close();
					
					txtBookName.setText("");
					txtIsbn.setText("");
					txtAuthor.setText("");
					txtPublisher.setText("");
					txtCopyCount.setText("");
					JOptionPane.showMessageDialog(null,"Data Saved into the file","Saved",JOptionPane.INFORMATION_MESSAGE);
				}
					
			} 
			catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
				
			}
			
		}
		public void writeString(RandomAccessFile out, int stringMaxLength, String target) throws IOException {
			//for (char letter : target.toCharArray()) {
				out.writeChars(target);
			//}
			for (int i = 0; i < (stringMaxLength - target.length()); i++) {
				out.writeChar(' ');
			}
		}
	}
	private class ClearButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			txtBookName.setText("");
			txtIsbn.setText("");
			txtAuthor.setText("");
			
			txtPublisher.setText("");
			txtCopyCount.setText("");
			
			
		}
	}
	
}
