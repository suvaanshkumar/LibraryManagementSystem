package business;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

import javax.swing.JOptionPane;

import bookDetails.BookBin;
import bookDetails.BookData;
import data.PanelType;
import mainFrame.Session;
import memberDetails.FileImplementation;
import memberDetails.Person;

public class Search_Business {
	public static void searchAction(Session currentSession, boolean BookSearch, String textToSearch) {
		//
		//currentSession.getFrameRef().displayPanel(PanelType.none);
		
		//generic for any type of matches
		List<String> allMatches = new ArrayList<String>();
		currentSession.getFrameRef().getResultsPanel().getResultsList().clearSelection();
		currentSession.getFrameRef().getResultsPanel().getResultsList().removeAll();;		
		currentSession.getFrameRef().displayPanel(PanelType.none);
		currentSession.getFrameRef().getResultsPanel().getResultStatusPanel().changeStatus(false, 0, "");
		currentSession.getFrameRef().loadSearchBar(false);
		if (textToSearch.equalsIgnoreCase("")) return;
		
		
		if (BookSearch) 
		{
			//BOOK search
			currentSession.getFrameRef().getResultsPanel().setResultType(PanelType.bookDisplay);
			
			BookBin bookDataObj = new BookBin();

			//Note that getNameIndex() returns arraylist of "firstname lastname" 
			ArrayList<String> bookNames = bookDataObj.getNameIndex(); 
			
			ArrayList<BookData> bookData = bookDataObj.getAll();
			ArrayList<BookData> bookResults = new ArrayList<BookData>(); 
			
			
			for (int i = 0; i < bookNames.size(); i++) {
				if (bookNames.get(i).toLowerCase().contains(textToSearch.toLowerCase())) 
				{
					bookResults.add(bookData.get(i));
					allMatches.add(bookNames.get(i));
				}
					
		}
			currentSession.getFrameRef().getResultsPanel().getResultStatusPanel().changeStatus(true, allMatches.size(), "Books");
			currentSession.getFrameRef().getResultsPanel().setBookResults(bookResults);
			currentSession.getFrameRef().getResultsPanel().getResultsList().setListData(allMatches.toArray());
			
		

		}
		else
		{
			
			
			//MEMBER SEARCH
			currentSession.getFrameRef().getResultsPanel().setResultType(PanelType.memberDisplay);
			
			FileImplementation personDataObj = new FileImplementation();

			//Note that getNameIndex() returns arraylist of "firstname lastname" 
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
			currentSession.getFrameRef().getResultsPanel().setPersonResults(personResults);
			currentSession.getFrameRef().getResultsPanel().getResultsList().setListData(allMatches.toArray());
			
		
		}
		currentSession.getFrameRef().loadSearchBar(true); 
		
	}
}
