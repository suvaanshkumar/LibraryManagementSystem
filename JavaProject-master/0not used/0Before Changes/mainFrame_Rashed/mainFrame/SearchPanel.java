package mainFrame;

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

import business.Search_Business;
import data.PanelType;
import data.ThemeInterface;



public class SearchPanel extends JPanel implements data.Constants {
	
	protected searchOptions srchOptions ;
	protected Session currentSession; 
	protected JTextField txtSearch;
	
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
			lblSearch.setSize(50, ThemeInterface.TEXTBOX_HEIGHT);
			lblSearch.setFont(ThemeInterface.LABEL__FONT);
			add(lblSearch);
			

			txtSearch = new JTextField();
			txtSearch.setPreferredSize(new Dimension(300,ThemeInterface.TEXTBOX_HEIGHT));
			txtSearch.setFont(ThemeInterface.TEXTBOX__FONT);
			add(txtSearch);
			
			btnSearch = new JButton(new ImageIcon("images/search_30px.png"));
			btnSearch.setPreferredSize(new Dimension(50, ThemeInterface.TEXTBOX_HEIGHT));
			btnSearch.setMnemonic(KeyEvent.VK_S);
			btnSearch.setToolTipText("Search Database - Shortcut: ALT + \"S\" ");
			ThemeInterface.setButtonTheme(btnSearch);
			add(btnSearch);
			
			btnSearch.addActionListener(new searchClassAction());
			
			
		}
		
		
		
		
		
		
		
		
	}
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
			setSize(500,ThemeInterface.TEXTBOX_HEIGHT);
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
		
		
		private void setButtonTheme(JRadioButton btn) {
			btn.setFont(ThemeInterface.BIGGER_FONT);
			btn.setBackground(Color.WHITE);
		}
	}
}



