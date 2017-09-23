package View;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainMenu {
	private JFrame guiFrame;
	private JPanel mainPanel;

	private JButton employees;
	private JButton agents;

	public MainMenu() {
		control.MySQLAccess access = new control.MySQLAccess();
		try {
			access.create_tables();
			add_components();
			buttons_listner();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void add_components() {
		guiFrame = new JFrame();
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		guiFrame.setTitle("Welcome");
		guiFrame.setSize(300, 300);

		// This will center the JFrame in the middle of the screen
		guiFrame.setLocationRelativeTo(null);
		guiFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		employees = new JButton("Employees");
		gbc.insets = new Insets(0, 0, 0, 10);
		mainPanel.add(employees, gbc);

		agents = new JButton("Agents");
		gbc.insets = new Insets(0, 10, 0, 0);
		mainPanel.add(agents, gbc);

		guiFrame.add(mainPanel);
		guiFrame.setVisible(true);
	}

	public void buttons_listner(){
		
		employees.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new	MainEmployee();		
			}
		});
		
		agents.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new	MainAgent();		
				
			}
		});
		
	}

	public static void main(String[] args) {

		new MainMenu();
	}
}
