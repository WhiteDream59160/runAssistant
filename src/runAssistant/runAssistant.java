package runAssistant;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class runAssistant {
	
	private static final String WINDOWXML = "windows.xml";
	
	public static void main(String[] args) {
		System.out.println("Hello World");
		
		// Instantiate the Factory
	      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

	      try {

	          // optional, but recommended
	          // process XML securely, avoid attacks like XML External Entities (XXE)
	          //dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

	          // parse XML file
	          DocumentBuilder db = dbf.newDocumentBuilder();

	          Document doc = db.parse(new File(WINDOWXML));

	          // optional, but recommended
	          // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	          doc.getDocumentElement().normalize();

	          System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
	          System.out.println("------");

	          // get <staff>

	          NodeList list = doc.getElementsByTagName("leftSidePanel");

	          String lsp_width = "";
	          String lsp_height = "";
	          
	          for (int i = 0; i < list.getLength(); i++) {
	        	  Node node = list.item(i);
	        	  if (node.getNodeType() == Node.ELEMENT_NODE) {
	        		  Element element = (Element) node;
		        	  lsp_width = element.getElementsByTagName("lsp_width").item(0).getTextContent();
		        	  lsp_height = element.getElementsByTagName("lsp_height").item(0).getTextContent();
	        	  }
	          }

		      System.out.println("lsp_width : " + lsp_width);
		      System.out.println("lsp_height : " + lsp_height);
	          

	      } catch (ParserConfigurationException | SAXException | IOException e) {
	          e.printStackTrace();
	      }

	}

}
