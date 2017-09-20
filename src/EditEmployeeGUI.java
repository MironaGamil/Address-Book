import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class EditEmployeeGUI {

	private static JFrame guiFrame;

	private JPanel fileds;
	private JPanel buttons;
	private JPanel main;
	
	private JLabel name;
	private JLabel companyName;
	private JLabel homeAddress;
	private JLabel homePhone;
	private JLabel businessAddress;
	private JLabel businessPhone;
	private JLabel faxNumbers;
	private JLabel phone;
	
	private JTextField nameField;
	private JTextField companyField;
	private JTextField homeAddressField;
	private JTextField homePhoneField;
	private JTextField busAddressField;
	private JTextField faxNumbersField;
	private JTextField busPhoneField;
	
	private static JTextField phoneField;

	private JButton okay;
	private JButton cancel;
	
	private String employee;
	private String faxNumberStr;
	private String id;


	EditEmployeeGUI(String employee, String faxNumberStr) {
		this.employee=employee;
		this.faxNumberStr = faxNumberStr;
		add_components();
		on_button_click();
	}

	public void add_components() {

		guiFrame = new JFrame();

		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setTitle("Edit");
		guiFrame.setSize(300, 300);

		// This will center the JFrame in the middle of the screen
		guiFrame.setLocationRelativeTo(null);
		guiFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		main = new JPanel();
		fileds = new JPanel();
		buttons = new JPanel();

		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

		GroupLayout layout = new GroupLayout(fileds);
		fileds.setLayout(layout);

		// Turn on automatically adding gaps between components
		layout.setAutoCreateGaps(true);

		// Turn on automatically creating gaps between components that touch
		// the edge of the container and the container.
		layout.setAutoCreateContainerGaps(true);

		name = new JLabel("Employee Name");
		companyName = new JLabel("Company Name");
		homeAddress = new JLabel("Home Address");
		homePhone = new JLabel("Home Phone");
		businessAddress = new JLabel("Business Address");
		businessPhone = new JLabel("Business Phone");
		faxNumbers = new JLabel("Fax Number");
		phone = new JLabel("Cellular Phone");

		nameField = new JTextField(20);
		companyField = new JTextField(20);
		homeAddressField = new JTextField(40);
		homePhoneField = new JTextField(20);
		busAddressField = new JTextField(20);
		busPhoneField = new JTextField(20);
		faxNumbersField = new JTextField(20);
		phoneField = new JTextField(20);
		
		String [] columns= employee.split(",");
		id= columns[0];
		nameField.setText(columns[1]);
		companyField.setText(columns[2]);
		homeAddressField.setText(columns[3]);
		homePhoneField.setText(columns[4]);
		busAddressField.setText(columns[5]);
		busPhoneField.setText(columns[6]);
		phoneField.setText(columns[7]);
		faxNumbersField.setText(faxNumberStr);


		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

		hGroup.addGroup(layout.createParallelGroup().addComponent(name)
				.addComponent(companyName).addComponent(homeAddress)
				.addComponent(homePhone).addComponent(businessAddress)
				.addComponent(businessPhone).addComponent(faxNumbers).addComponent(phone));
		hGroup.addGroup(layout.createParallelGroup().addComponent(nameField)
				.addComponent(companyField).addComponent(homeAddressField)
				.addComponent(homePhoneField).addComponent(busAddressField)
				.addComponent(busPhoneField).addComponent(faxNumbersField).addComponent(phoneField));
		layout.setHorizontalGroup(hGroup);

		vGroup.addGroup(layout.createParallelGroup().addComponent(name)
				.addComponent(nameField));
		vGroup.addGroup(layout.createParallelGroup().addComponent(companyName)
				.addComponent(companyField));
		vGroup.addGroup(layout.createParallelGroup().addComponent(homeAddress)
				.addComponent(homeAddressField));
		vGroup.addGroup(layout.createParallelGroup().addComponent(homePhone)
				.addComponent(homePhoneField));
		vGroup.addGroup(layout.createParallelGroup()
				.addComponent(businessAddress).addComponent(busAddressField));
		vGroup.addGroup(layout.createParallelGroup()
				.addComponent(businessPhone).addComponent(busPhoneField));
		vGroup.addGroup(layout.createParallelGroup()
				.addComponent(faxNumbers).addComponent(faxNumbersField));
		vGroup.addGroup(layout.createParallelGroup().addComponent(phone)
				.addComponent(phoneField));
		layout.setVerticalGroup(vGroup);

		okay = new JButton("Submit");
		cancel = new JButton("cancel");

		buttons.add(okay);
		buttons.add(cancel);

		main.add(fileds);
		main.add(buttons);
		guiFrame.add(main);
		guiFrame.setVisible(true);
		JOptionPane.showMessageDialog(guiFrame, "insert fax numbers separeted by comma!");


	}

	public void on_button_click() {
		okay.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		cancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				guiFrame.setVisible(false);
			}
		});
	}
	
	public void update(){
		boolean execute =false;
		
		String name=nameField.getText();
		String company = companyField.getText();
		String home=homeAddressField.getText();
		String homephone=homePhoneField.getText();
		String business=busAddressField.getText();
		String busphone=busPhoneField.getText();
		String phone=phoneField.getText();
		
		execute = MySQLAccess.update_employee(id, name, company, home, homephone, business, busphone, phone);
		if (execute){
			update_fax(id);
		}	
	}
	
	public void update_fax(String id){
		MySQLAccess.update_fax(Integer.parseInt(id));
		String numbers =faxNumbersField.getText();
		String[] parts = numbers.split(",");
		boolean execute= false;
		for(int i=0; i< parts.length; i++){
			if(!parts[i].equals("")){
				execute = MySQLAccess.insert_to_faxnumbers(Integer.parseInt(id), parts[i]);
				if (!execute){
					JOptionPane.showMessageDialog(guiFrame, "failed to update!");
					break;
				}
			}
		}
		if (execute)
		
			JOptionPane.showMessageDialog(guiFrame, "employee updated!");	

	}
	

}
