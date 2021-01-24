package rLogin_Business;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import rLogin_Data.Session_DB;

public class Session_Business {

	public static JTable getSessionReportData() {
		Session_DB database = Session_DB.getInstance();
		ResultSet resultset = database.getAllSessions();

		
        
		DefaultTableModel model = new DefaultTableModel(){

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		model.addColumn("User ID");
        model.addColumn("Session ID");
        model.addColumn("Time");
        model.addColumn("Event");
        
		
//		List<String[]> table = new ArrayList<>();
		try {
			while( resultset.next()) {
			            model.addRow(new Object[]{resultset.getString(1), resultset.getString(2), resultset.getString(3),resultset.getString(4)});
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			database.disconnect();
		}
		
		return new JTable(model);
		

	}
}
