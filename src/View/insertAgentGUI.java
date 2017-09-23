package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class insertAgentGUI extends AgentForm {

	insertAgentGUI() {
		super("Insert Agent");
		click_insert_button();
	}
	
	public void click_insert_button(){
		okay.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				insert();
			}
		});
	}

	public void insert() {
		boolean execute = false;

		String name = nameField.getText();
		String home = addressField.getText();
		String phone = phoneField.getText();
		boolean valid = handler.Validations.validate_length(phone, guiFrame);
		if (valid) {
			execute = control.MySQLAccess.insert_to_agents(name, home, phone);
			if (execute) {
				guiFrame.setVisible(false);
				JOptionPane.showMessageDialog(guiFrame, "Agent inserted!");

			} else {
				JOptionPane.showMessageDialog(guiFrame, "failed to insert!");
			}
		}
	}

}
