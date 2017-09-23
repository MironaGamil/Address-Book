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

}
