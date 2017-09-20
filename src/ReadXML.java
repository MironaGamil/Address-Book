import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadXML {

	public static void read_employees() {
		try {

			File fXmlFile = new File("employees.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance(); // get xml trees
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder(); // get
																		// documents
			Document doc = dBuilder.parse(fXmlFile); // provides the primary
														// access to the
														// document's data

			System.out.println("Root element :"
					+ doc.getDocumentElement().getNodeName());
			System.out.println("----------------------------");

			NodeList nList = doc.getElementsByTagName("employee");
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					String name = eElement.getElementsByTagName("name").item(0)
							.getTextContent();
					String cName = eElement.getElementsByTagName("company_name").item(0)
							.getTextContent();
					String hAddr = eElement
							.getElementsByTagName("home_address").item(0)
							.getTextContent();
					String hNumber = eElement
							.getElementsByTagName("home_phone").item(0)
							.getTextContent();
					String bAddr = eElement
							.getElementsByTagName("business_address").item(0)
							.getTextContent();
					String bNumber = eElement
							.getElementsByTagName("business_phone").item(0)
							.getTextContent();
					String cNumber = eElement
							.getElementsByTagName("cellular_phone").item(0)
							.getTextContent();
					NodeList faxNumbers = eElement
							.getElementsByTagName("fax_numbers").item(0)
							.getChildNodes();

					System.out.println("name : " + name);
					System.out.println("home address : " + hAddr);
					System.out.println("home phone number : " + hNumber);
					System.out.println("business address : " + bAddr);
					System.out.println("business phone number: " + bNumber);
					System.out.println("cellular phone : " + cNumber);

					int id = MySQLAccess.insert_to_employee(name, cName, hAddr,
							hNumber, bAddr, bNumber, cNumber);

					if (id != -1)
						MySQLAccess.get_fax_numbers(faxNumbers, id);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void read_agents() {

		try {

			File fXmlFile = new File("agents.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance(); // get xml trees
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder(); // get
																		// documents
			Document doc = dBuilder.parse(fXmlFile); // provides the primary
														// access to the
														// document's data

			System.out.println("Root element :"
					+ doc.getDocumentElement().getNodeName());
			System.out.println("----------------------------");

			NodeList nList = doc.getElementsByTagName("agent");
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					String name = eElement.getElementsByTagName("name").item(0)
							.getTextContent();
					String address = eElement
							.getElementsByTagName("home_address").item(0)
							.getTextContent();
					String phone = eElement
							.getElementsByTagName("cellular_phone").item(0)
							.getTextContent();

					System.out.println("name : " + name);
					System.out.println("home address : " + address);
					System.out.println("cellular phone : " + phone);

					MySQLAccess.insert_to_agents(name, address, phone);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
