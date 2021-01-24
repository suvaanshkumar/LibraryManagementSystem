package bookDetails;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import data.LoginCredential;

public class BookBin implements BookCons {
	private File file;
	private ArrayList<BookData> bookData = new ArrayList<BookData>();
	private ArrayList<String> titlesList = new ArrayList<String>();
	
	public BookBin() {
		file = new File(BookCons.FILENAME_BIN);
		getAll();
	}
	
	public ArrayList<BookData> getBooks(){
		return bookData;
	}
	
	public ArrayList<String> getNameIndex() {
		return titlesList;
	}
	
	
	public ArrayList<BookData> getAll(){
		try {
			bookData.clear();
			titlesList.clear();
			RandomAccessFile in = new RandomAccessFile(file,"rw");
			int count = ((int)in.length())/BOOKINPUT_RECORDSIZE;
			for (int i = 0; i < count; i++) {
				in.seek(i*BOOKINPUT_RECORDSIZE);
				String currentISBN = readString(in,ISBNSIZE);
				String currentBookName = readString(in, BNSIZE);
				titlesList.add(currentBookName);
				String currentAuthorName = readString(in, ANSIZE);
				String currentGenre = readString(in, GENSIZE);
				String currentPublisher = readString(in, PUBSIZE);
				String currentCollection = readString(in, COLSIZE);
				int currentBookCount = in.readInt();
				bookData.add(new BookData(currentISBN,currentBookName,currentAuthorName,currentGenre,currentPublisher,currentCollection,currentBookCount));
				
			}
			in.close();
				
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookData;
	}
	
	public String readString(RandomAccessFile in, int sSize) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <sSize ; i++) {
			try {
				sb.append(in.readChar());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sb.toString().trim();
		
	}
	
	public BookData getBookData(int byIndex) {
		try {
			RandomAccessFile in = new RandomAccessFile(file, "rw");
			in.seek(byIndex*BOOKINPUT_RECORDSIZE);
			String currentISBN = readString(in,ISBNSIZE);
			String currentBookName = readString(in, BNSIZE);
			String currentAuthorName = readString(in, ANSIZE);
			String currentGenre = readString(in, GENSIZE);
			String currentPublisher = readString(in, PUBSIZE);
			String currentCollection = readString(in, COLSIZE);
			int currentBookCount = in.readInt();
			in.close();
			return (new BookData(currentISBN,currentBookName,currentAuthorName,currentGenre,currentPublisher,currentCollection,currentBookCount));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		BookBin bin = new BookBin();
		ArrayList<BookData> data = bin.getAll();
		for (BookData book : data) {
			System.out.println(book.getISBN() + "\n" 
					+ book.getBookName() + "\n"
					+ book.getAuthorName() + "\n"
					+ book.getCollection() + "\n"
					+ book.getCopyCount() + "\n"
					+ book.getGenre()+ "\n"
					+ book.getpublisher()+ "\n"
					);
		}
	}
	
	
}
