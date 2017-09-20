import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SearchResultGUI {
	private static JFrame guiFrame;

	private String[] columns;
	private Object[][] data;

	public SearchResultGUI(String [] columns, Object[][] data) {
		this.columns=columns;
		this.data=data;
		add_components();
	}

	public void add_components() {
		guiFrame = new JFrame();

		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setTitle("Edit");
		guiFrame.setSize(300, 300);

		// This will center the JFrame in the middle of the screen
		guiFrame.setLocationRelativeTo(null);
		guiFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		
		JTable table = new JTable(data, columns);
		JScrollPane tableScrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		table.setShowGrid(true);


		
		guiFrame.add(tableScrollPane);
		guiFrame.setVisible(true);
		
	}

}
