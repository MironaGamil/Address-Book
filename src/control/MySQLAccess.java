package control;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLAccess {
	private static Connection connection = null;
	private static CallableStatement CBstatement = null;

	public void create_tables() throws SQLException {
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/addressbook", System.getenv("DBusername"), System.getenv("password"));

			CBstatement = connection.prepareCall("{call addressbook ()}");
			CBstatement.execute();
			System.out.println("Table created!");
			model.ReadXML.read_employees();
			model.ReadXML.read_agents();


		} catch (SQLException sqlException) {
			if (sqlException.getErrorCode() != 1050) {
				CBstatement = connection.prepareCall("{call dropTables ()}");
				CBstatement.execute();
				System.out.println("Database another error!");
				sqlException.printStackTrace();
			}

		} catch (ClassNotFoundException e) {
			System.out.println("Class not found!");

		} finally {
			// close();
		}
	}

	public static int insert_to_employee(String name, String cName, String hAddr,
			String hNumber, String bAddr, String bNumber, String cNumber) {
		int id = -1;
		try {
			CBstatement = connection
					.prepareCall("{call addValuesToEmplyees (?,?,?,?,?,?,?,?)}");
			CBstatement.setString(1, name);
			CBstatement.setString(2, cName);
			CBstatement.setString(3, hAddr);
			CBstatement.setString(4, hNumber);
			CBstatement.setString(5, bAddr);
			CBstatement.setString(6, bNumber);
			CBstatement.setString(7, cNumber);
			CBstatement.execute();

			id = CBstatement.getInt(8);

			System.out.println("values inserted!" + id);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	public static boolean insert_to_faxnumbers(int id, String faxNumber)  {
		try {
			CBstatement = connection
					.prepareCall("{call addValuesToFaxnumbers (?,?)}");

			CBstatement.setInt(1, id);
			CBstatement.setString(2, faxNumber);
			CBstatement.execute();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				CBstatement = connection
						.prepareCall("{call deleteEmployee (?)}");
				CBstatement.setInt(1, id);
				CBstatement.execute();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			

		}
		return false;
	}
	
	public static boolean insert_to_agents(String name, String address, String phone){
		try {
			CBstatement = connection
					.prepareCall("{call addValuesToAgents (?,?,?)}");
			CBstatement.setString(1, name);
			CBstatement.setString(2, address);
			CBstatement.setString(3, phone);
			CBstatement.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
		
	}
	
	public static void get_Search_result(ResultSet rs, boolean fax){
		try {
			if (!rs.isBeforeFirst() ) {    
			    System.out.println("No data");
				new View.SearchResultGUI(null, null, false);

			}
			else{
				ResultSetMetaData metadata = rs.getMetaData();
			    int columnCount = metadata.getColumnCount();
			    String [] columns = new String [columnCount];
			    columns = fax? new String[columnCount+1] : new String[columnCount];
			    	
			    for (int i = 1; i <= columnCount; i++) {
			    	columns[i-1]= metadata.getColumnName(i); 
			    }
			    if(fax)
			    	columns[columnCount]="Fax Numbers";
			    
			    
			    List<List<String>> table = new ArrayList<List<String>>();
			    
		
				while (rs.next()) {
					ArrayList<String> table_row= new ArrayList<String>();
					for (int i = 1; i <= columnCount; i++) {
			           table_row.add(rs.getString(i));          
			        }
					if(fax)
						table_row.add(search_fax_by_id(Integer.parseInt(rs.getString(1))));
					table.add(table_row);
					System.out.println(rs.getString(1));
				}
				String[][] data = new String[table.size()][]; 
				for (int i = 0; i < data.length; i++) { 
					ArrayList<String> row = (ArrayList<String>) table.get(i);
				    data[i] = row.toArray(new String[row.size()]);
				}
				new View.SearchResultGUI(columns, data, true);
		
			}
				} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	public static void search_employee_by_name(String name) {
		try {
			CBstatement = connection.prepareCall("{call searchEmployeeByName (?)}");
			CBstatement.setString(1, name);
			// CBstatement.execute();
			ResultSet rs = CBstatement.executeQuery();
			get_Search_result(rs,true);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void search_employee_by_company_name(String cName){
		try {
			CBstatement = connection.prepareCall("{call searchEmployeeByCompany (?)}");
			CBstatement.setString(1, cName);
			// CBstatement.execute();
			ResultSet rs = CBstatement.executeQuery();
			get_Search_result(rs,true);
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void search_agent_by_name(String name){
		
		try {
			CBstatement = connection.prepareCall("{call searchAgentByName (?)}");
			CBstatement.setString(1, name);
			// CBstatement.execute();
			ResultSet rs = CBstatement.executeQuery();
			get_Search_result(rs,false);			
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static boolean delete_employee(int id) {
		try {
			CBstatement = connection.prepareCall("{call deleteEmployee (?)}");
			CBstatement.setInt(1, id);
			CBstatement.execute();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}
	
	public static boolean delete_agent(int id) {
		try {
			CBstatement = connection.prepareCall("{call deleteAgent (?)}");
			CBstatement.setInt(1, id);
			CBstatement.execute();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}
	
	

	public static String search_employee_by_id(int id) {
		String result = "";
		try {
			CBstatement = connection.prepareCall("{call searchEmployeeByID (?)}");
			CBstatement.setInt(1, id);
			// CBstatement.execute();
			ResultSet rs = CBstatement.executeQuery();
			ResultSetMetaData metadata = rs.getMetaData();
			int columnCount = metadata.getColumnCount();
			while (rs.next()){
				for (int i = 1; i <= columnCount; i++) {
					result += rs.getString(i) + ",";
				}
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}
	public static String search_fax_by_id(int id) {
		String result = "";
		try {
			CBstatement = connection.prepareCall("{call searchFaxbyID (?)}");
			CBstatement.setInt(1, id);
			// CBstatement.execute();
			ResultSet rs = CBstatement.executeQuery();
			ResultSetMetaData metadata = rs.getMetaData();
			int columnCount = metadata.getColumnCount();
			while (rs.next()){
				for (int i = 1; i <= columnCount; i++) {
					if(i%2==0)
						result += rs.getString(i) + ",";
				}
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}
	
	
	public static String search_agent_by_id(int id) {
		String result = "";
		try {
			CBstatement = connection.prepareCall("{call searchAgentByID (?)}");
			CBstatement.setInt(1, id);
			// CBstatement.execute();
			ResultSet rs = CBstatement.executeQuery();
			ResultSetMetaData metadata = rs.getMetaData();
			int columnCount = metadata.getColumnCount();
			while (rs.next()){
				for (int i = 1; i <= columnCount; i++) {
					result += rs.getString(i) + ",";
				}
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}
	
	

	public static boolean update_employee(String id, String name, String cName, String hAddr,
			String hNumber, String bAddr, String bNumber, String cNumber) {
		try {
			CBstatement = connection.prepareCall("{call updateEmployee (?,?,?,?,?,?,?,?)}");
			CBstatement.setInt(1,Integer.parseInt(id));
			CBstatement.setString(2, name);
			CBstatement.setString(3, cName);
			CBstatement.setString(4, hAddr);
			CBstatement.setString(5, hNumber);
			CBstatement.setString(6, bAddr);
			CBstatement.setString(7, bNumber);
			CBstatement.setString(8, cNumber);


			CBstatement.execute();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
			
	}
	
	public static boolean update_fax(int id){
		
		try {
			CBstatement = connection
					.prepareCall("{call updateFaxnumbers (?)}");

			CBstatement.setInt(1, id);
			CBstatement.execute();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	public static boolean update_agent(String id, String name, String address, String phone){
		try {
			CBstatement = connection.prepareCall("{call updateAgent (?,?,?,?)}");
			CBstatement.setInt(1,Integer.parseInt(id));
			CBstatement.setString(2, name);
			CBstatement.setString(3, address);
			CBstatement.setString(4, phone);


			CBstatement.execute();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}

	public static void close() {
		System.out.println("close");
		try {
			if (CBstatement != null) {
				CBstatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {

		}
	}

}
