package nBook_Business;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public class Validation {
	public static boolean isPresent(JTextComponent c, String title) {
		if(c.getText().length()==0) {
			showMessage(c,title+" is required!!\nPlease re-enter!!");
			c.requestFocusInWindow();
			return false;
		}
		return true;
	}
	
	public static boolean isInteger(JTextComponent c, String title) {
		try {
			int i = Integer.parseInt(c.getText());
			return true;
		}
		catch(NumberFormatException e) {
			showMessage(c,title+" must be integer!!\nPlease re-enter!!");
			c.requestFocusInWindow();
			return false;
		}
	}
	public static boolean isDouble(JTextComponent c, String title) {
		try {
			double i = Double.parseDouble(c.getText());
			return true;
		}
		catch(NumberFormatException e) {
			showMessage(c,title+" must be Double!!\nPlease re-enter!!");
			c.requestFocusInWindow();
			return false;
		}
	}
	private static void showMessage(JTextComponent c, String message) {
		JOptionPane.showMessageDialog(c, message,"Invalid Entry", JOptionPane.ERROR_MESSAGE);
	}

}