package rMainFrame_Presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;

//import bookDetails.BookDetailsoLD;
//import bookDetails.BookDetailsoLD.BookDetails_Panel;
//import bookDetails.BookInputOLD.BookInput_Panel;
import nBook_Presentation.BookDetails;
import nBook_Presentation.BookInput;
import rLogin_Business.Login_Business;
import rLogin_Data.Session;
import rLogin_Data.Session_DB;
import rLogin_Presentation.LoginPage;
import rLogin_Presentation.SessionTable;
import rMainFrame_Data.PanelType;
//import rMainFrame_Presentation.ResultsPanel.ResultStatusPanel;
import sPerson_Data.accessMode;
import sPerson_Presentation.AddMember_Panel;
//import sPerson_Presentation.BaseGui_TESTER;
import sPerson_Presentation.BaseGui_Panel;
           
enum northside {NE, NW}


public class MainFrame extends JFrame implements rLogin_Data.LoginConstants {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Session currentSession; //passed around between methods and contains important references,
									//check Session.java for more info
	
	public static void main(String[] args) {
		//MAIN METHOD TO LAUNCH THE PROGRAM//
		MainFrame createdMain = new MainFrame();
		createdMain.setVisible(true);
	}
	

	private JMenuBar menubar;
	private JMenu menuUser,menuHelp, menuLibrarian;
	private JMenuItem miRegister, miLogin, mLogout, mExit;
	private JMenuItem miAddBook, miAddMember, miSession;
	private JMenuItem miAbout;
	private ResultsPanel resultsPanel; 
	private AddMember_Panel memberAddPanel;
	private BaseGui_Panel memberPanel;
	private BookDetails bookDetailsPanel;
	private BookInput bookInputPanel;
	private JPanel bookDetWrapper, BookAddWrapper,sessionWrapper;
	protected JPanel loginPage, registerPage;
	private SignInPanel signPanel;
	private SearchPanel searchPanel;
	SessionTable sessionTable;
	NorthPanel northPanel;//has both signPanel and SearchPanel  
	
	public MainFrame() {
		setLayout(new BorderLayout());
		setSize(1050, 900);
		setMinimumSize(new Dimension(1000,600));
		setTitle("Library Management System");
		setLocationRelativeTo(null);;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//By default Guest Session is started, note the reference to the MainFrame is sent in
		currentSession = new Session(accessMode.Guest,"", this,0,0);
		
	//North Panels
		signPanel = new SignInPanel(currentSession);
		searchPanel = new SearchPanel(currentSession);
		northPanel = new NorthPanel();
		this.add(northPanel, BorderLayout.NORTH);
		northPanel.add(signPanel, northside.NE);
		northPanel.add(searchPanel,northside.NW);		;
		
	//Side Panel
		String[] results = {"Member Example","Book Example"};
		resultsPanel = new ResultsPanel(false,"2 results", results, currentSession);
		add(resultsPanel, BorderLayout.WEST);
		
	//Center Panels
		memberAddPanel = new AddMember_Panel(currentSession, 0, accessMode.Librarian);
		memberPanel = new BaseGui_Panel(currentSession);
		
		loginPage = new LoginPage(false, currentSession); 
		registerPage = new LoginPage(true, currentSession);
				
		bookDetWrapper = new JPanel();
		bookDetWrapper.setLayout(new FlowLayout(FlowLayout.CENTER));
		bookDetWrapper.setPreferredSize(new Dimension(800,800));
		bookDetailsPanel = new BookDetails(currentSession);
		bookDetailsPanel.setPreferredSize(new Dimension(700,500));
		bookDetWrapper.add(bookDetailsPanel, BorderLayout.CENTER);
		
		BookAddWrapper = new JPanel();
		BookAddWrapper.setLayout(new FlowLayout(FlowLayout.CENTER));
		BookAddWrapper.setPreferredSize(new Dimension(800,800));
		bookInputPanel = new BookInput();
		bookInputPanel.setPreferredSize(new Dimension(700,500));
		BookAddWrapper.add(bookInputPanel, BorderLayout.CENTER);
		
		sessionWrapper = new JPanel();
		sessionWrapper.setLayout(new FlowLayout(FlowLayout.CENTER));
		sessionWrapper.setPreferredSize(new Dimension(800,800));
		sessionTable = new SessionTable();
		sessionWrapper.add(sessionTable, BorderLayout.CENTER);
		
		
		
		
		
		
		
	///MENUS
		menubar = new JMenuBar();
		this.setJMenuBar(menubar);
		menubar.setVisible(true);
		
		menuUser = new JMenu("User");
		menuHelp = new JMenu("Help");
		menuLibrarian = new JMenu("Librarian");
		
		miRegister = new JMenuItem("Register");
		miRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentSession.getFrameRef().getRegisterPage().getRegistaAction().setFillProfileAfter(false);
				displayPanel(PanelType.register);
			}
		});
		
		miLogin= new JMenuItem("Login");
		miLogin.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				displayPanel(PanelType.login);
			}
		});
		
		mLogout = new JMenuItem("Logout");
		mLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Login_Business.logoutAction();
			}
		});
		
		miAddBook = new JMenuItem("Add a book");
		miAddBook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentSession.getFrameRef().displayPanel(PanelType.bookAdd);
			}
		});
		
		miAddMember = new JMenuItem("Add Member");
		miAddMember.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentSession.getFrameRef().getRegisterPage().getRegistaAction().setFillProfileAfter(true);
				currentSession.getFrameRef().displayPanel(PanelType.register);
			}
		});
		
		miSession = new JMenuItem("View Session Report");
		miSession.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentSession.getFrameRef().displayPanel(PanelType.sessionTable); 
			}
		});
		
		miAbout = new JMenuItem("About");
		mExit = new JMenuItem("Exit");
		
		menuUser.add(miRegister); 
		miRegister.setMnemonic(KeyEvent.VK_R);
		miRegister.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.CTRL_MASK));
		
		menuUser.add(miLogin);
		miLogin.setMnemonic(KeyEvent.VK_L);
		miLogin.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,ActionEvent.CTRL_MASK));
		
		//menuUser.add(mLogout);
		mLogout.setMnemonic(KeyEvent.VK_O);
		mLogout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
		
		
		menuLibrarian.add(miAddBook);
		miAddBook.setMnemonic(KeyEvent.VK_A);
		miAddBook.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
		
		
		menuLibrarian.add(miSession);
		miSession.setMnemonic(KeyEvent.VK_S);
		miSession.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
		
		
		
		
		menuLibrarian.add(miAddMember);
		miAddMember.setMnemonic(KeyEvent.VK_I);
		miAddMember.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,ActionEvent.CTRL_MASK));
		
		menuHelp.add(miAbout);
		miAbout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,"Made by: \n  Rashed\n  Navdeep\n  Suvaansh","Author",JOptionPane.INFORMATION_MESSAGE);
			}
			
		});
		
		menuUser.add(mExit);
		mExit.setMnemonic(KeyEvent.VK_E);
		mExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.CTRL_MASK));
		mExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		menubar.add(menuUser);
		menubar.add(menuHelp); 
		
	}
	
	@Override
    protected void finalize() throws Throwable
    {
        try
        {
        	Session_DB.getInstance().syncAllEvents(currentSession.getEvents());
            //release resources here
        } 
        catch(Throwable t) 
        {
            throw t;
        } 
        finally
        {
            super.finalize();
        }
    }
	
	
	//Method to add/remove the menu items according to user accessing
	public void menuReform(Session currentSession) {
		switch(currentSession.getAccessMode()) {
		case Guest:
				if (menuUser.getPopupMenu().isAncestorOf(mLogout)) menuUser.remove(mLogout);
				if (!menuUser.getPopupMenu().isAncestorOf(miLogin)) menuUser.add(miLogin,0);
				if (!menuUser.getPopupMenu().isAncestorOf(miRegister)) menuUser.add(miRegister,0);
				if (menubar.isAncestorOf(menuLibrarian)) menubar.remove(menuLibrarian);
				
			break;
		
		case Member:
			if (menuUser.getPopupMenu().isAncestorOf(miLogin)) menuUser.remove(miLogin);
			if (menuUser.getPopupMenu().isAncestorOf(miRegister)) menuUser.remove(miRegister);
			if (!menuUser.getPopupMenu().isAncestorOf(menuUser)) menuUser.add(mLogout,0);				
			break;
			
		case Librarian:
			if (menuUser.getPopupMenu().isAncestorOf(miLogin)) menuUser.remove(miLogin);
			if (menuUser.getPopupMenu().isAncestorOf(miRegister)) menuUser.remove(miRegister);
			if (!menuUser.getPopupMenu().isAncestorOf(menuUser)) menuUser.add(mLogout,0);
			if (!menubar.isAncestorOf(menuLibrarian)) menubar.add(menuLibrarian,1);
		
		
		break;	
		}
		revalidate();
		repaint();
	}
	
	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}
	
	//Method to show/hide different Center Panels
	public void displayPanel(PanelType panel) {
		if (isAncestorOf(bookDetWrapper)) remove(bookDetWrapper);
		if (isAncestorOf(memberPanel)) remove(memberPanel);
		if (isAncestorOf(loginPage)) {remove(loginPage);((LoginPage) loginPage).clearFields();};
		if (isAncestorOf(registerPage)) {remove(registerPage);((LoginPage) registerPage).clearFields();};
		if (isAncestorOf(BookAddWrapper)) {remove(BookAddWrapper);}
		if (isAncestorOf(memberAddPanel)) {remove(memberAddPanel);}
		if (isAncestorOf(sessionWrapper)) {remove(sessionWrapper);}
		
	//Changes color of background to match the panel color (only statusPanel?)
		JPanel target = null;
		
		switch (panel) {
			case login:			 
				target = loginPage;
				break;
		
			case register:
				target = registerPage;
				break;
				
			case bookDisplay:	
				bookDetWrapper.remove(bookDetailsPanel);
				bookDetailsPanel = new BookDetails(currentSession);
				bookDetailsPanel.setPreferredSize(new Dimension(700,500));
				bookDetWrapper.add(bookDetailsPanel, BorderLayout.CENTER);
				target = bookDetWrapper;
				break;
					
			case memberDisplay:
				target = memberPanel;
				break;
			
			case bookAdd:
				target = BookAddWrapper;
				break;
				
			case memberAdd:
				target = memberAddPanel;
				break;
				
			case sessionTable:
				sessionWrapper.remove(sessionTable);
				sessionTable = new SessionTable();
				sessionWrapper.add(sessionTable, BorderLayout.CENTER);
				target = sessionWrapper;
				break;
				
			case none:
				paintBackground(new Color(238,238,238));
				break;
			default:
				break;
		}

		if (target != null)
			{add(target, BorderLayout.CENTER);
			paintBackground(target.getBackground());}
		revalidate();
		repaint();
		
		
	}
	
//removes all Center Panels, and side one too
	public void clearPanels() {
		displayPanel(PanelType.none);
		resultsPanel.showPanel(false);
		this.repaint();
		revalidate();
	}
	
//Accessors
	public SearchPanel getSearchPanel() {
		return searchPanel;
	}
	
	public BaseGui_Panel getMemberPanel() {
		return memberPanel;
	}
	
	public AddMember_Panel getMemberAddPanel() {
		return memberAddPanel;
	}
	
	
	public BookDetails getBookDetailsPanel() {
		return bookDetailsPanel;
	}
	
	public ResultsPanel getResultsPanel() {
		return resultsPanel;
	}
	
	
	public SignInPanel getSignInPanel() {
		return signPanel;
	}
	
	public LoginPage getRegisterPage() {
		return (LoginPage)registerPage;
	}
	
//Amateur way to change search bar
	public void loadSearchBar(boolean show) {
		resultsPanel.showPanel(show);
		this.repaint();
		revalidate();
		//;
	}

	
//Changes only the panel that shows the result count to new color
	public void paintBackground(Color newcolor) {
		resultsPanel.changeStatusColor(newcolor);
	}
	

//Adding Panels to North Panel
	public class NorthPanel extends JPanel {
		private JPanel panelNE, panelNW;
		public NorthPanel() {
			JPanel panelNE = new JPanel();
			panelNE.setSize(500, 200);
			panelNE.setLayout(new FlowLayout());

			this.setBorder(BorderFactory.createLineBorder(new Color(26,84,180),4));
			JPanel panelNW = new JPanel();
			panelNW.setSize(1000, 200);
			panelNW.setLayout(new FlowLayout(FlowLayout.RIGHT));
			this.setSize(1000,400);
			this.setLayout(new GridLayout(1,2));
			this.setBackground(Color.GREEN);

				
		}
		
		public void hadd(Component comp,northside side){
			switch (side)
			{
			case NE:
				panelNE.add(comp);
			case NW:
				panelNW.add(comp);
			}
		}
	}
	

}


