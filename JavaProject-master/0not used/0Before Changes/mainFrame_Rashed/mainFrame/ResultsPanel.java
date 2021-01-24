package mainFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import bookDetails.BookData;
import data.PanelType;
import memberDetails.Person;

public class ResultsPanel extends JPanel {
	private JList resultsList;
	private JPanel tableWrapper;
	private ResultStatusPanel statusPanel;
	private JScrollPane listScrollPane;
	private PanelType resultType = PanelType.memberDisplay; 
	private ArrayList<Person> personResults = new ArrayList<Person>();
	private ArrayList<BookData> bookResults = new ArrayList<BookData>();
	private int previousSelectedValue; //reason of implementing this is to prevent double clicks
	Session currentSession;
	
	
	public ResultsPanel(boolean show, String Statusmsg , String[] results, Session currentSession) {
		this.currentSession = currentSession;
		setLayout(new BorderLayout());
		setBackground(new Color(77, 135, 230));
		setPreferredSize(new Dimension(300,800));
		statusPanel = new ResultStatusPanel(Statusmsg);
		if(show) 
			{if (!isAncestorOf(statusPanel)) add(statusPanel, BorderLayout.NORTH);}
		else
			{if (isAncestorOf(statusPanel)) remove(statusPanel);}	
		resultsList = new JList(results);
		resultsList.setFont( new Font(this.getFont().getFamily(),Font.BOLD,20));
		resultsList.setBorder(BorderFactory.createLineBorder(new Color(26,84,180),4));
		resultsList.setBackground(new Color(187,217,233));
		resultsList.setFixedCellHeight(30);
		resultsList.setFixedCellWidth(50);
		resultsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listScrollPane = new JScrollPane(resultsList);
		resultsList.addListSelectionListener(new ListSelectionListener() {
			
		
			
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				if (previousSelectedValue != resultsList.getSelectedIndex());//getSelectedValue())
					{
						if (resultsList.getSelectedIndex() == -1) return;
						
						previousSelectedValue = resultsList.getSelectedIndex();
						switch (resultType)
						{
						case memberDisplay:
							currentSession.getFrameRef().getMemberPanel().inflate(personResults.get(resultsList.getSelectedIndex()));
							break;
							
						case bookDisplay:
							currentSession.getFrameRef().getBookDetailsPanel().fillData(bookResults.get(resultsList.getSelectedIndex()));
							
						default:
							break;
						}
						
						currentSession.getFrameRef().displayPanel(resultType);
						
						}
					}
			
		});
		
		
		if(show) 
			{if (!isAncestorOf(listScrollPane)) add(listScrollPane, BorderLayout.CENTER);}
		else
			{if (isAncestorOf(listScrollPane)) remove(listScrollPane);}
	
	}
	
	public void setResultType(PanelType target) {
		resultType = target;
	}
	
	public void setPersonResults(ArrayList<Person> personResults) {
		this.personResults = personResults;
	}
	
	public void setBookResults(ArrayList<BookData> bookResults) {
		this.bookResults = bookResults;
	}
	
	public JList getResultsList() {
		return resultsList;
	}
	
	public void changeStatusColor(Color newcolor) {
		statusPanel.setBackground(newcolor);
	}
	
	public ResultStatusPanel getResultStatusPanel() {
		return statusPanel;
	}
	
	public void showPanel(boolean show) {
		if(show) 
			{if (!isAncestorOf(listScrollPane)) add(listScrollPane, BorderLayout.CENTER);
			if (!isAncestorOf(statusPanel)) add(statusPanel, BorderLayout.NORTH);}
		else
			{if (isAncestorOf(listScrollPane)) remove(listScrollPane);
			if (isAncestorOf(statusPanel)) remove(statusPanel);}		
			
	}
	
	 
	public class ResultStatusPanel extends JPanel {
		private JLabel lblStatus;
		String lblStatusCaption = "";
		public ResultStatusPanel(String msg) {
			setPreferredSize(new Dimension(300,30));
			setBackground(new Color(238,238,238));
			setLayout(new FlowLayout());
			lblStatus = new JLabel(msg);
			lblStatus.setFont( new Font(this.getFont().getFamily(),Font.BOLD,20) );
			add(lblStatus);
			
			
		}

		public void changeStatus(Boolean showmsg, int resultCount, String msg) {
			lblStatus.setText(resultCount + " " + msg);
		}
		
		
	}


}
	
	

	
	
	
	
	
	
	



