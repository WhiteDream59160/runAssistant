package runAssistant;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class runAssistant {
	
	private static final String WINDOWXML = "windows.xml";
	
	public static void main(String[] args) {
		System.out.println("Hello World");
		
		// Instantiate the Factory
	      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

	      try {

	          // parse XML file
	          DocumentBuilder db = dbf.newDocumentBuilder();

	          Document doc = db.parse(new File(WINDOWXML));
	          
	          doc.getDocumentElement().normalize();
	          
	          NodeList lspNodeList = doc.getElementsByTagName("leftSidePanel");
	          Node lspNode = lspNodeList.item(0);
	          Element lspElement = (Element) lspNode;

	          String lsp_width = lspElement.getElementsByTagName("lsp_width").item(0).getTextContent();
	          String lsp_height = lspElement.getElementsByTagName("lsp_height").item(0).getTextContent();
	          
	          
	          NodeList lspButtonList = lspElement.getElementsByTagName("button");
	          for (int i = 0; i < lspButtonList.getLength(); i++) {
	        	  Node lspButtonNode = lspButtonList.item(i);
	        	  if (lspButtonNode.getNodeType() == Node.ELEMENT_NODE) {
	        		  Element button = (Element) lspButtonNode;
	        		  
	    		      System.out.println("x : " + (String) button.getElementsByTagName("x").item(0).getTextContent());
	    		      System.out.println("y : " + (String) button.getElementsByTagName("y").item(0).getTextContent());
	    		      System.out.println("height : " + (String) button.getElementsByTagName("height").item(0).getTextContent());
	    		      System.out.println("width : " + (String) button.getElementsByTagName("width").item(0).getTextContent());
	    		      System.out.println("name : " + button.getElementsByTagName("name").item(0).getTextContent());
	    		      System.out.println("desctription : " + button.getElementsByTagName("desctription").item(0).getTextContent());
	    		      System.out.println("target : " + button.getElementsByTagName("target").item(0).getTextContent());
	        	  }
	          }
	          

	      } catch (ParserConfigurationException | SAXException | IOException e) {
	          e.printStackTrace();
	      }

	}

}
