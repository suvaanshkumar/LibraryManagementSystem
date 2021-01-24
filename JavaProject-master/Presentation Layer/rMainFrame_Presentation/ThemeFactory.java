package rMainFrame_Presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

//A theme class that has some constants
//and some universally used methods
public class ThemeFactory {

	public static final int TEXTBOX_HEIGHT = 50;
	
	public static final Font THEMED_BUTTON__FONT = new Font ("Courier New", 1, 13);
	public static final Font VIEW_AS__FONT = new Font ("Courier New", 0, 17);
	public static final Font PERSON_NAME__FONT = new Font ("Courier New", 1, 24);
	public static final Font TEXTBOX__FONT = new Font ("Courier New", 1, 20);
	public static final Font BIGGER_FONT = new Font ("Courier New", 0, 20);
	public static final Font LABEL__FONT = new Font ("Courier New", 0, 24);
	
	public static void setButtonTheme(JButton target, int length, int width) {
		target.setPreferredSize(new Dimension(length, width));
		target.setForeground(Color.WHITE);
		target.setBackground(new Color(26,84,180));
		target.setFont(THEMED_BUTTON__FONT);
	}

	public static void setButtonTheme(JButton target) {
		target.setForeground(Color.WHITE);
		target.setBackground(new Color(26,84,180));
		target.setFont(THEMED_BUTTON__FONT);
	}
	
	

			
}
