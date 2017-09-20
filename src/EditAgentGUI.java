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

public class EditAgentGUI {
	
	private static JFrame guiFrame;

	private JPanel fileds;
	private JPanel buttons;
	private JPanel main;
	
	private JLabel name;
	private JLabel address;
	private JLabel phone;
	
	private JTextField nameField;
	private JTextField addressField;
	private JTextField phoneField;

	private JButton okay;
	private JButton cancel;
	
	private String agent;
	private String id;


	EditAgentGUI(String agent) {
		this.agent=agent;
		add_components();
		on_button_click();
	}

	public void add_components() {

		guiFrame = new JFrame();
		guiFrame.setTitle("Edit Agent");
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

		name = new JLabel("Agent Name");
		address = new JLabel("Address");
		phone = new JLabel("Cellular Phone");

		nameField = new JTextField(20);
		addressField = new JTextField(20);
		phoneField = new JTextField(20);
		
		String [] columns= agent.split(",");
		id= columns[0];
		nameField.setText(columns[1]);
		addressField.setText(columns[2]);
		phoneField.setText(columns[3]);


		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

		hGroup.addGroup(layout.createParallelGroup().addComponent(name)
				.addComponent(address).addComponent(phone));
		hGroup.addGroup(layout.createParallelGroup().addComponent(nameField)
				.addComponent(addressField).addComponent(phoneField));
		layout.setHorizontalGroup(hGroup);

		vGroup.addGroup(layout.createParallelGroup().addComponent(name)
				.addComponent(nameField));
		vGroup.addGroup(layout.createParallelGroup().addComponent(address)
				.addComponent(addressField));
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
		String address=addressField.getText();
		String phone=phoneField.getText();
		
		execute = MySQLAccess.update_agent(id, name, address, phone);
		if (execute){
			JOptionPane.showMessageDialog(guiFrame, "Agent updated!");	
		}
		else{
			JOptionPane.showMessageDialog(guiFrame, "failed to update!");
		}
	}
	


}
