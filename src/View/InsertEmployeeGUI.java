package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class InsertEmployeeGUI  extends EmployeeForm{

	

	InsertEmployeeGUI() {
		super("Insert Employee");
		click_insert_button();
	}
	
	public void click_insert_button(){
		okay.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(validate())
					insert();
			}
		});
	}

	
	public void insert(){
		int id =-1;
		
		String name=nameField.getText();
		String comName =companyField.getText();
		String home=homeAddressField.getText();
		String homephone=homePhoneField.getText();
		String business=busAddressField.getText();
		String busphone=busPhoneField.getText();
		String phone=phoneField.getText();
		
		boolean valid = handler.Validations.validate_length(homephone, guiFrame) && 
				handler.Validations.validate_length(busphone, guiFrame)&&
				handler.Validations.validate_length(phone, guiFrame);
		if (valid){
			id = control.MySQLAccess.insert_to_employee(name, comName, home, homephone, business, busphone, phone);
			if (id != -1)
				insertFax(id);
		}	
	
	}
	
	public void insertFax(int id){
		String numbers =faxNumbersField.getText();
		String[] parts = numbers.split(",");
		boolean execute= false;
		for(int i=0; i< parts.length; i++){
			if(handler.Validations.validate_fax(parts[i], guiFrame)){
				execute = control.MySQLAccess.insert_to_faxnumbers(id, parts[i]);
				if (!execute){
					JOptionPane.showMessageDialog(guiFrame, "failed to insert!");
					break;
				}
			}
			else{
				System.out.println("hnaaaaa");
				control.MySQLAccess.delete_employee(id);
			}

		}
		if (execute){
			guiFrame.setVisible(false);
			JOptionPane.showMessageDialog(guiFrame, "employee inserted!");
		}
	}
	
}
