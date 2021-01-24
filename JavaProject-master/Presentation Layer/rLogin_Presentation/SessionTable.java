package rLogin_Presentation;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import rLogin_Business.Session_Business;
import rMainFrame_Data.PanelType;
import rMainFrame_Presentation.MainFrame;

public class SessionTable extends JPanel {

	private JTable table;
	private JScrollPane sp;
	
	public SessionTable() {
	

  
        // Initializing the JTable 
        table = Session_Business.getSessionReportData(); 
        
        table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        TableColumnModel colModel=table.getColumnModel();
        colModel.getColumn(0).setPreferredWidth(5);  
        colModel.getColumn(1).setPreferredWidth(5);
        colModel.getColumn(2).setPreferredWidth(5);  
        colModel.getColumn(3).setPreferredWidth(300);

        table.setPreferredScrollableViewportSize(new Dimension(700,800));
        
        // adding it to JScrollPane 
        JScrollPane sp = new JScrollPane(table);
        add(sp); 

	}
	
	public JTable getTable() {
		return table;
	}
	
	public JScrollPane getScrollPane() {
		return sp;
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainFrame createdMain = new MainFrame();
		createdMain.setVisible(true);
		createdMain.displayPanel(PanelType.sessionTable);
//		SessionTable sd = new SessionTable();
		createdMain.repaint();
		createdMain.revalidate();
		
		
	}

}
