package bookDetails;
import java.awt.*;
import javax.swing.*;

public class BookDetails extends JPanel{
	private JLabel lblIsbn,lblBookName,lblAuthor,lblPublisher,lblGenre, lblStatus;
	private JTextField txtIsbn, txtBookName,txtAuthor,txtPublisher,txtGenre, txtStatus;
	
	public BookDetails() {
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
	}
	
	public void fillData(BookData bd) {
		txtIsbn.setText(bd.getISBN());
		txtBookName.setText(bd.getBookName());
		txtAuthor.setText(bd.getAuthorName());
		txtGenre.setText(bd.getGenre());
		txtPublisher.setText(bd.getpublisher());
		if (bd.getCopyCount() == 0)
			txtStatus.setText("Not Available");
		else
			txtStatus.setText("Available");
	}

}
