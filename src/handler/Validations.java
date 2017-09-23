package handler;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Validations {
	public static boolean validate_length(String number, JFrame frame){
		if(number.length()== 11)
			return true;
		JOptionPane.showMessageDialog(frame,
				"Phone number must be 11 numbers!", "Error",
				JOptionPane.ERROR_MESSAGE);
		return false;
	}
	
	public static boolean validate_fax(String number, JFrame frame){
		System.out.println(number.length());
		if(number.length()== 5)
			return true;
		JOptionPane.showMessageDialog(frame,
				"Fax number must be 5 numbers!", "Error",
				JOptionPane.ERROR_MESSAGE);
		return false;
	}

}
