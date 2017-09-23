package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class EditAgentGUI extends AgentForm{
	private String agent;
	private String id;


	EditAgentGUI(String agent) {
		super("Edit Agent");
		this.agent=agent;
		add_information();
		click_update_button();
	}
	
	public void add_information(){
		String [] columns= agent.split(",");
		id= columns[0];
		nameField.setText(columns[1]);
		addressField.setText(columns[2]);
		phoneField.setText(columns[3]);
	}
	
	public void click_update_button(){
		okay.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (validate())
					update();
			}
		});
	}
	
	public void update(){
		boolean execute =false;
		
		String name=nameField.getText();
		String address=addressField.getText();
		String phone=phoneField.getText();
		boolean valid = handler.Validations.validate_length(phone, guiFrame);
		if (valid){
			execute = control.MySQLAccess.update_agent(id, name, address, phone);
			if (execute){
				guiFrame.setVisible(false);
				JOptionPane.showMessageDialog(guiFrame, "Agent updated!");	
			}
			else{
				JOptionPane.showMessageDialog(guiFrame, "failed to update!");
			}
		}
		
	}
	


}
