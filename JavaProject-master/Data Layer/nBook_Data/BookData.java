
package nBook_Data;

public class BookData {
	private String bookName,authorName,iSBN,genre,publisher,collection;
	private int copyCount;
	BookData(){
		
	}
	public BookData( String iSBN,String bookName,String an,String genre, String p, String c, int cc) {
		setBookName(bookName);
		setAuthorName(an);
		setISBN(iSBN);
		setGenre(genre);
		setPublisher(p);
		setCollection(c);
		setCopyCount(cc);
	}
	
	public void setBookName(String bookName) {
		this.bookName=bookName;
	}
	public String getBookName() {
		return bookName;
	}
	
	public void setAuthorName(String authorName) {
		this.authorName=authorName;
	}
	public String getAuthorName() {
		return authorName;
	}
	
	public void setISBN(String iSBN) {
		this.iSBN=iSBN;
	}
	public String getISBN() {
		return iSBN;
	}
	public void setCopyCount(int copyCount) {
		this.copyCount=copyCount;
	}
	public int getCopyCount() {
		return copyCount;
	}
	
	public void setPublisher(String publisher) {
		this.publisher=publisher;
	}
	public String getpublisher() {
		return publisher;
	}
	
	public void setGenre(String genre) {
		this.genre=genre;
	}
	public String getGenre() {
		return genre;
	}
	
	public void setCollection(String collection) {
		this.collection=collection;
	}
	public String getCollection() {
		return collection;
	}

	public boolean searchName(String bookSearched){
		if (bookName.contains(bookSearched))
		{
			return true;
		}
		return false;

	}

}