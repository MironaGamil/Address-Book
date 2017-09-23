package View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MainEmployee {
	
	private static JFrame guiFrame;
	private JPanel comboPanel;
	
	private JLabel searchName;
	private static JTextField searchNameField;
	private static JButton searchNameButton;
	
	private JLabel searchCompany;
	private static JTextField searchCompanyField;
	private static JButton searchCompanyButton;

	private JLabel delete;
	private static JTextField deleteField;
	private static JButton deleteButton;
	
	private JLabel edit;
	private static JTextField editField;
	private static JButton editButton;
	
	private static JButton insertButton;
	

	public MainEmployee() {
		add_components();
		on_upadate_text();
		on_button_click();

	}
	public void add_components(){
		guiFrame = new JFrame();

		// make sure the program exits when the frame closes
		guiFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		guiFrame.setTitle("Employee");
		guiFrame.setSize(300, 400);

		// This will center the JFrame in the middle of the screen
		guiFrame.setLocationRelativeTo(null);
		
		NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
		DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
		decimalFormat.setGroupingUsed(false);

		comboPanel = new JPanel();
		comboPanel.setLayout(new BoxLayout(comboPanel, BoxLayout.Y_AXIS));

		searchName = new JLabel("Search by employee name:");
		searchNameField = new JTextField(20);
		searchNameButton = new JButton("Search");
		searchNameButton.setEnabled(false);
		
		searchCompany = new JLabel("Search by company name:");
		searchCompanyField = new JTextField(20);
		searchCompanyButton = new JButton("Search");
		searchCompanyButton.setEnabled(false);

		delete = new JLabel("Delete by id:");
		deleteField = new JTextField(20);
		deleteButton = new JButton("Delete");
		deleteButton.setEnabled(false);
		
		edit = new JLabel("Edit by id:");
		editField = new JTextField(20);
		editButton = new JButton("Edit");
		editButton.setEnabled(false);
		
		insertButton = new JButton("Insert");

		comboPanel.add(searchName);
		comboPanel.add(searchNameField);
		comboPanel.add(searchNameButton);
		
		comboPanel.add(searchCompany);
		comboPanel.add(searchCompanyField);
		comboPanel.add(searchCompanyButton);

		comboPanel.add(delete);
		comboPanel.add(deleteField);
		comboPanel.add(deleteButton);
		
		comboPanel.add(edit);
		comboPanel.add(editField);
		comboPanel.add(editButton);
		
		comboPanel.add(insertButton);

		guiFrame.add(comboPanel);
		guiFrame.setVisible(true);
		
	}
	public static void on_upadate_text() {

		searchNameField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				enableButton(searchNameField, searchNameButton);
			}

			public void removeUpdate(DocumentEvent e) {
				enableButton(searchNameField, searchNameButton);
			}

			public void insertUpdate(DocumentEvent e) {
				enableButton(searchNameField, searchNameButton);
			}
		});
		
		searchCompanyField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				enableButton(searchCompanyField, searchCompanyButton);
			}

			public void removeUpdate(DocumentEvent e) {
				enableButton(searchCompanyField, searchCompanyButton);
			}

			public void insertUpdate(DocumentEvent e) {
				enableButton(searchCompanyField, searchCompanyButton);
			}
		});

		deleteField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				enableButton(deleteField, deleteButton);
			}

			public void removeUpdate(DocumentEvent e) {
				enableButton(deleteField, deleteButton);
			}

			public void insertUpdate(DocumentEvent e) {
				enableButton(deleteField, deleteButton);
			}
		});
		
		deleteField.addKeyListener(new KeyListener() {
			
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
		
		editField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				enableButton(editField, editButton);
			}

			public void removeUpdate(DocumentEvent e) {
				enableButton(editField, editButton);
			}

			public void insertUpdate(DocumentEvent e) {
				enableButton(editField, editButton);
			}
		});
		editField.addKeyListener(new KeyListener() {
			
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

	public static void on_button_click() {
		searchNameButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				control.MySQLAccess.search_employee_by_name(searchNameField.getText());
			}
		});
		
		searchCompanyButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				control.MySQLAccess.search_employee_by_company_name(searchCompanyField.getText());
			}
		});

		deleteButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				boolean execute= control.MySQLAccess.delete_employee(Integer.parseInt(deleteField.getText()));
				if(execute){
					JOptionPane.showMessageDialog(guiFrame, "employee deleted!");
				}
				else{
					JOptionPane.showMessageDialog(guiFrame, "can't delete employee!");
				}
			}
		});
		
		editButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String employee = control.MySQLAccess.search_by_id(Integer.parseInt(editField.getText()));
				if(employee.equals("")){
					JOptionPane.showMessageDialog(guiFrame, "not found!");
				}
				else{
					String faxnumbers = control.MySQLAccess.search_fax_by_id(Integer.parseInt(editField.getText()));
					new EditEmployeeGUI(employee,faxnumbers);
				}
			}
		});
		
		insertButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
					new InsertEmployeeGUI();
			}
		});
	}

	public static void enableButton(JTextField text, JButton button) {

		if (text.getText().equals("")) {
			button.setEnabled(false);
		} else {
			button.setEnabled(true);
		}
	}


}
