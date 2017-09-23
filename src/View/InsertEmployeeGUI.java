package View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InsertEmployeeGUI  {

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
	private JTextField phoneField;

	private JButton okay;
	private JButton cancel;
	

	InsertEmployeeGUI() {
		add_components();
		on_button_click();
		on_textfield_write();
	}

	public void add_components() {

		guiFrame = new JFrame();
		guiFrame.setTitle("Insert");
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
				insert();
			}
		});
		cancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				guiFrame.setVisible(false);
			}
		});
	}
	
	public void on_textfield_write(){
		homePhoneField.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {
				char c= e.getKeyChar();
				if(!Character.isDigit(c)){
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});;
		
		busPhoneField.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {
				char c= e.getKeyChar();
				if(!Character.isDigit(c)){
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});;
		
		faxNumbersField.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {
				char c= e.getKeyChar();
				if(!Character.isDigit(c) && c != ','){
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});;
		
		phoneField.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {
				char c= e.getKeyChar();
				if(!Character.isDigit(c)){
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});;
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
			execute = control.MySQLAccess.insert_to_faxnumbers(id, parts[i]);
			if (!execute){
				JOptionPane.showMessageDialog(guiFrame, "failed to insert!");
				break;
			}

		}
		if (execute)
		
			JOptionPane.showMessageDialog(guiFrame, "employee inserted!");

		

	}
	
}
