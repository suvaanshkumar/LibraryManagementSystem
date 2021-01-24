package bookDetails;

public interface BookCons {

	final String FILENAME_BIN="Files/books.dat";
	
	int ISBNSIZE = 15;
	int BNSIZE= 40;
	int ANSIZE= 25;
	int GENSIZE = 15;
	int PUBSIZE = 25;
	int COLSIZE = 15;
	int CCSIZE = 4;
	
	int BOOKINPUT_RECORDSIZE = ISBNSIZE*2+BNSIZE*2+ANSIZE*2+GENSIZE*2+PUBSIZE*2+COLSIZE*2+CCSIZE;
}
