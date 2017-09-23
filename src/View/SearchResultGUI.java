package View;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SearchResultGUI {
	private static JFrame guiFrame;

	private String[] columns;
	private Object[][] data;
	private boolean found;

	public SearchResultGUI(String[] columns, Object[][] data, boolean found) {
		this.columns = columns;
		this.data = data;
		this.found = found;
		add_components();
	}

	public void add_components() {

		guiFrame = new JFrame();
		guiFrame.setTitle("Result");
		guiFrame.setSize(1000, 300);

		// This will center the JFrame in the middle of the screen
		guiFrame.setLocationRelativeTo(null);
		guiFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		if (!found) {
			JOptionPane.showMessageDialog(guiFrame,
				    "Not Found!",
				    "Error",
				    JOptionPane.ERROR_MESSAGE);
		}
		else{
			JTable table = new JTable(data, columns);
			JScrollPane tableScrollPane = new JScrollPane(table);
			table.setFillsViewportHeight(true);
			table.setShowGrid(true);

			guiFrame.add(tableScrollPane);
			guiFrame.setVisible(true);

		}
		

	}

}
