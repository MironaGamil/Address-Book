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

public class MainAgent {
	private static JFrame guiFrame;
	private JPanel comboPanel;
	
	private JLabel search;
	private static JTextField searchField;
	private static JButton searchButton;

	private JLabel delete;
	private static JTextField deleteField;
	private static JButton deleteButton;
	
	private JLabel edit;
	private static JTextField editField;
	private static JButton editButton;
	
	private static JButton insertButton;
	

	public MainAgent() {
		add_components();
		on_upadate_text();
		on_button_click();

	}
	public void add_components(){
		guiFrame = new JFrame();

		// make sure the program exits when the frame closes
		guiFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		guiFrame.setTitle("Agent");
		guiFrame.setSize(300, 250);

		// This will center the JFrame in the middle of the screen
		guiFrame.setLocationRelativeTo(null);
		
		NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
		DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
		decimalFormat.setGroupingUsed(false);

		comboPanel = new JPanel();
		comboPanel.setLayout(new BoxLayout(comboPanel, BoxLayout.Y_AXIS));

		search = new JLabel("Search:");
		searchField = new JTextField(20);
		searchButton = new JButton("Search");
		searchButton.setEnabled(false);

		delete = new JLabel("Delete by id:");
		deleteField = new JTextField(20);
		deleteButton = new JButton("Delete");
		deleteButton.setEnabled(false);
		
		edit = new JLabel("Edit by id:");
		editField = new JTextField(20);
		editButton = new JButton("Edit");
		editButton.setEnabled(false);
		
		insertButton = new JButton("Insert");

		comboPanel.add(search);
		comboPanel.add(searchField);
		comboPanel.add(searchButton);

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

		searchField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				enableButton(searchField, searchButton);
			}

			public void removeUpdate(DocumentEvent e) {
				enableButton(searchField, searchButton);
			}

			public void insertUpdate(DocumentEvent e) {
				enableButton(searchField, searchButton);
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
		searchButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				MySQLAccess.search_agent_by_name(searchField.getText());
			}
		});

		deleteButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				boolean execute= MySQLAccess.delete_agent(Integer.parseInt(deleteField.getText()));
				if(execute){
					JOptionPane.showMessageDialog(guiFrame, "Agent deleted!");
				}
				else{
					JOptionPane.showMessageDialog(guiFrame, "can't delete agent!");
				}
			}
		});
		
		editButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String agent = MySQLAccess.search_agent_by_id(Integer.parseInt(editField.getText()));
				if(agent.equals("")){
					JOptionPane.showMessageDialog(guiFrame, "not found!");
				}
				else{
					new EditAgentGUI(agent);
				}
			}
		});
		
		insertButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
					new insertAgentGUI();
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
