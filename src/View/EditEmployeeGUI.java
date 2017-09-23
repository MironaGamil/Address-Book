package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class EditEmployeeGUI extends EmployeeForm {

	private String employee;
	private String faxNumberStr;
	private String id;

	EditEmployeeGUI(String employee, String faxNumberStr) {
		super("Edit Employee");
		this.employee = employee;
		this.faxNumberStr = faxNumberStr;
		add_information();
		click_update_button();

	}

	public void add_information() {

		String[] columns = employee.split(",");
		id = columns[0];
		nameField.setText(columns[1]);
		companyField.setText(columns[2]);
		homeAddressField.setText(columns[3]);
		homePhoneField.setText(columns[4]);
		busAddressField.setText(columns[5]);
		busPhoneField.setText(columns[6]);
		phoneField.setText(columns[7]);
		faxNumbersField.setText(faxNumberStr);

	}
	
	public void click_update_button(){
		okay.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(validate())
					update();
			}
		});
	}

	public void update() {
		boolean execute = false;

		String name = nameField.getText();
		String company = companyField.getText();
		String home = homeAddressField.getText();
		String homephone = homePhoneField.getText();
		String business = busAddressField.getText();
		String busphone = busPhoneField.getText();
		String phone = phoneField.getText();

		boolean valid = handler.Validations
				.validate_length(homephone, guiFrame)
				&& handler.Validations.validate_length(busphone, guiFrame)
				&& handler.Validations.validate_length(phone, guiFrame);
		if (valid) {
			execute = control.MySQLAccess.update_employee(id, name, company,
					home, homephone, business, busphone, phone);
			if (execute) {
				update_fax(id);
			}
		}

	}

	public void update_fax(String id) {
		control.MySQLAccess.update_fax(Integer.parseInt(id));
		String numbers = faxNumbersField.getText();
		String[] parts = numbers.split(",");
		boolean execute = false;
		for (int i = 0; i < parts.length; i++) {
			if (!parts[i].equals("")) {
				execute = control.MySQLAccess.insert_to_faxnumbers(
						Integer.parseInt(id), parts[i]);
				if (!execute) {
					JOptionPane
							.showMessageDialog(guiFrame, "failed to update!");
					break;
				}
			}
		}
		if (execute){
			guiFrame.setVisible(false);
			JOptionPane.showMessageDialog(guiFrame, "employee updated!");
		}

	}

}
