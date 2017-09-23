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

public class insertAgentGUI {

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

	insertAgentGUI() {
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

		name = new JLabel("Agent Name");
		address = new JLabel("Address");
		phone = new JLabel("Cellular Phone");

		nameField = new JTextField(20);
		addressField = new JTextField(20);
		phoneField = new JTextField(20);

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
				insert();
			}
		});
		cancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				guiFrame.setVisible(false);
			}
		});
	}

	public void on_textfield_write() {
		phoneField.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});
		;

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
				JOptionPane.showMessageDialog(guiFrame, "Agent inserted!");

			} else {
				JOptionPane.showMessageDialog(guiFrame, "failed to insert!");
			}
		}
	}

}
