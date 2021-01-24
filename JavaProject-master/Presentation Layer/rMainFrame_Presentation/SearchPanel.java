package rMainFrame_Presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
//sset
import javax.swing.KeyStroke;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

import rLogin_Data.Session;
import rMainFrame_Buiness.Search_Business;
import rMainFrame_Data.PanelType;


//the right top side of the MainFrame that shows the search items
public class SearchPanel extends JPanel implements rLogin_Data.LoginConstants {
	
	protected searchOptions srchOptions ;
	protected Session currentSession; 
	protected JTextField txtSearch;
	
	//the two radio buttons
	public searchOptions getSearchOptions() {
		return srchOptions;
	}
	
	public SearchPanel(Session currentSession) {
		this.currentSession = currentSession;
		setLayout(new BorderLayout());
		setSize(500,200);
		setBackground(Color.WHITE);
		SearchBar searchBr = new SearchBar(currentSession);
		add(searchBr, BorderLayout.NORTH);
		srchOptions = new searchOptions(currentSession);
		add(srchOptions, BorderLayout.CENTER);
	}
	
	public class SearchBar extends JPanel{
		private JButton btnSearch;
		private JLabel lblSearch;
		Session currentSession;
		
		public SearchBar(Session currentSession) {
			this.currentSession = currentSession;
			setLayout(new FlowLayout(FlowLayout.RIGHT));
			setPreferredSize(new Dimension(400,55));
			setBackground(Color.WHITE);
			

			lblSearch = new JLabel("Search");
			lblSearch.setSize(50, ThemeFactory.TEXTBOX_HEIGHT);
			lblSearch.setFont(ThemeFactory.LABEL__FONT);
			add(lblSearch);
			

			txtSearch = new JTextField();
			txtSearch.setPreferredSize(new Dimension(300,ThemeFactory.TEXTBOX_HEIGHT));
			txtSearch.setFont(ThemeFactory.TEXTBOX__FONT);
			add(txtSearch);
			
			btnSearch = new JButton(new ImageIcon("images/search_30px.png"));
			btnSearch.setPreferredSize(new Dimension(50, ThemeFactory.TEXTBOX_HEIGHT));
			btnSearch.setMnemonic(KeyEvent.VK_S);
			btnSearch.setToolTipText("Search Database - Shortcut: ALT + \"S\" ");
			ThemeFactory.setButtonTheme(btnSearch);
			add(btnSearch);
			
			btnSearch.addActionListener(new searchClassAction());
			
			
		}
		
		
		
		
		
		
		
		
	}
	
	//also sends which radiobutton(books or members) selected
	public class searchClassAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			Search_Business.searchAction(currentSession, srchOptions.radioBook.isSelected(), txtSearch.getText());
		}
		
	}
	
	
	public class searchOptions extends JPanel {
		private JRadioButton radioMember, radioBook;
		private ButtonGroup radioBtnGp;
		
		public searchOptions(Session currentSession) {
			setLayout(new FlowLayout(FlowLayout.RIGHT));
			setSize(500,ThemeFactory.TEXTBOX_HEIGHT);
			setBackground(Color.WHITE);
			radioMember = new JRadioButton("Members");
			setButtonTheme(radioMember);
			radioBook = new JRadioButton("Books");
			setButtonTheme(radioBook);
			
			radioBtnGp = new ButtonGroup();
			radioBtnGp.add(radioMember);
			radioBtnGp.add(radioBook);
			radioBook.setSelected(true);
			
			reform(currentSession);
			
			
		}
		
	//Method to add/remove the radio buttons depending on who is accessing the program
		public void reform(Session currentSession) {
			switch(currentSession.getAccessMode())
			{
				case Guest:
					if (isAncestorOf(radioBook)) remove(radioBook);
					if (isAncestorOf(radioMember)) remove(radioMember);
					break;
				case Member:
					if (isAncestorOf(radioBook)) remove(radioBook);
					if (isAncestorOf(radioMember)) remove(radioMember);
					break;
				case Librarian:
					if (!isAncestorOf(radioBook)) add(radioBook);
					if (!isAncestorOf(radioMember)) add(radioMember);
					break;
			}
			radioBook.setSelected(true);
		}
		
		private JRadioButton getBookButton() {
			return radioBook;
		}
		
	//Amatuer Theme Method
		private void setButtonTheme(JRadioButton btn) {
			btn.setFont(ThemeFactory.BIGGER_FONT);
			btn.setBackground(Color.WHITE);
		}
	}
}



