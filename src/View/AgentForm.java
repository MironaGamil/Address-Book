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

public abstract class AgentForm {
	protected static JFrame guiFrame;

	protected JPanel fileds;
	protected JPanel buttons;
	protected JPanel main;

	protected JLabel name;
	protected JLabel address;
	protected JLabel phone;

	protected JTextField nameField;
	protected JTextField addressField;
	protected JTextField phoneField;

	protected JButton okay;
	protected JButton cancel;
	private String title;

	
	public AgentForm(String title){
		this.title=title;
		add_components();
		on_button_click();
		on_textfield_write();
	}
	
	public void add_components() {

		guiFrame = new JFrame();

		guiFrame.setTitle(title);
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
	
	public boolean validate(){
		if (nameField.getText().equals("")){
			JOptionPane.showMessageDialog(guiFrame,
					"Name can't be empty!", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
			
		}else if (addressField.getText().equals("")){
			JOptionPane.showMessageDialog(guiFrame,
					"Address can't be empty!", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
			
		}else if(phoneField.getText().equals("")){
			JOptionPane.showMessageDialog(guiFrame,
					"Phone number can't be empty!", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
			
		}
		return true;
	}



}
