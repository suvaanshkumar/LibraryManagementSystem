package rMainFrame_Buiness;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import nBook_Data.BookBin;
import nBook_Data.BookData;
import rLogin_Data.Session;
import rLogin_Data.SessionEvent;
import rMainFrame_Data.PanelType;
import sPerson_Data.FileImplementation;
import sPerson_Data.Person;

public class Search_Business {
	
	//Action when User presses the Search button variable:textToSearch is textbox.getText()
	public static void searchAction(Session currentSession, boolean BookSearch, String textToSearch) {
		
		
		
		
		//Method is split to Book search vs Member search
		//These Command happen regardless, clearing List, clearing panel and amount of results
		List<String> allMatches = new ArrayList<String>();
		currentSession.getFrameRef().getResultsPanel().getResultsList().clearSelection();
		currentSession.getFrameRef().getResultsPanel().getResultsList().removeAll();;		
		currentSession.getFrameRef().displayPanel(PanelType.none);
		currentSession.getFrameRef().getResultsPanel().getResultStatusPanel().changeStatus(false, 0, "");
		currentSession.getFrameRef().loadSearchBar(false);
		if (textToSearch.equalsIgnoreCase("")) return;
		
		
		if (BookSearch) 
		{
			
			currentSession.addEvent(new SessionEvent(currentSession.getSessionID(), currentSession.getID(), new Date().toString(), currentSession.getUsername()+ " searched for " + textToSearch + " in books"));
			//BOOK search
			//sets results to books
			currentSession.getFrameRef().getResultsPanel().setResultType(PanelType.bookDisplay);
		
			//Access book.dat and gets book info
			BookBin bookDataObj = new BookBin();

			//Note that getNameIndex() returns arraylist of "firstname lastname"
			//gets arraylists to search within
			ArrayList<String> bookNames = bookDataObj.getNameIndex(); 
			ArrayList<BookData> bookData = bookDataObj.getAll();
			ArrayList<BookData> bookResults = new ArrayList<BookData>(); 
			
			//using contains to search through names
			for (int i = 0; i < bookNames.size(); i++) {
				if (bookNames.get(i).toLowerCase().contains(textToSearch.toLowerCase())) 
				{
					bookResults.add(bookData.get(i));
					allMatches.add(bookNames.get(i));
				}
					
			}
			currentSession.getFrameRef().getResultsPanel().getResultStatusPanel().changeStatus(true, allMatches.size(), "Books");
			//this is the one that has the data that will be loaded when value of list is changed 
			currentSession.getFrameRef().getResultsPanel().setBookResults(bookResults);
			currentSession.getFrameRef().getResultsPanel().getResultsList().setListData(allMatches.toArray());
			
		

		}
		else
		{
			
			
			//MEMBER SEARCH
			currentSession.getFrameRef().getResultsPanel().setResultType(PanelType.memberDisplay);
			
			currentSession.addEvent(new SessionEvent(currentSession.getSessionID(), currentSession.getID(), new Date().toString(), currentSession.getUsername()+ " searched for " + textToSearch + " in members."));
			
			//access person.dat
			FileImplementation personDataObj = new FileImplementation();

			//Note that getNameIndex() returns arraylist of "firstname lastname"
			//arraylists for searching
			ArrayList<String> personNames = personDataObj.getNameIndex(); 
			ArrayList<Person> personData = personDataObj.getAll();
			ArrayList<Person> personResults = new ArrayList<Person>(); 
			
			
			for (int i = 0; i < personNames.size(); i++) {
				if (personNames.get(i).toLowerCase().contains(textToSearch.toLowerCase())) 
				{
					personResults.add(personData.get(i));
					allMatches.add(personNames.get(i));
				}
					
			}
			currentSession.getFrameRef().getResultsPanel().getResultStatusPanel().changeStatus(true, allMatches.size(), "Members");
			//this contains person results data, and list will scroll through that data
			currentSession.getFrameRef().getResultsPanel().setPersonResults(personResults);
			currentSession.getFrameRef().getResultsPanel().getResultsList().setListData(allMatches.toArray());
			
		
		}
		//shows results side Jlist
		currentSession.getFrameRef().loadSearchBar(true); 
		
	}
}
