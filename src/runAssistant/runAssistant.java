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
	
	private static void prettyPrint(Node node, String tab)
    {
        if (node.getNodeType() == Node.TEXT_NODE)
        {
            System.out.print(tab);
            System.out.println(node.getNodeValue());
        }
        else if (node.getNodeType() == Node.ELEMENT_NODE)
        {
            System.out.print(tab);
            System.out.println("<" + node.getNodeName() + ">");
            NodeList kids = node.getChildNodes();
            for (int i = 0; i < kids.getLength(); i++)
            {
                prettyPrint(kids.item(i), tab + "  ");
            }
            System.out.print(tab);
            System.out.println("</" + node.getNodeName() + ">");
        }
    }
	
	private static final String WINDOWXML = "windows.xml";
	
	private static String nodeToString(Node node) throws IOException, TransformerException
	{
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		
		StringWriter writer = new StringWriter();
		transformer.transform(new DOMSource(node), new StreamResult(writer));
		return writer.toString();
	}
	
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
	          /*
	          NodeList list = doc.getElementsByTagName("leftSidePanel");
			
	          String lsp_width = "";
	          String lsp_height = "";
	          */
	          
	          NodeList lspNodeList = doc.getElementsByTagName("leftSidePanel");
	          Node lspNode = lspNodeList.item(0);
	          Element lspElement = (Element) lspNode;

	          String lsp_width = lspElement.getElementsByTagName("lsp_width").item(0).getTextContent();
	          String lsp_height = lspElement.getElementsByTagName("lsp_height").item(0).getTextContent();
	          
	          int x = 0;
	          int y = 0;
	          int height = 0;
	          int width = 0;
	          String name = "";
	          String desctription = "";
	          String target = "";
	          
	          
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
	          
	          
	          /*
	          for (int i = 0; i < list.getLength(); i++) {
	        	  Node node = list.item(i);
	        	  if (node.getNodeType() == Node.ELEMENT_NODE) {
	        		  Element element = (Element) node;
		        	  lsp_width = element.getElementsByTagName("lsp_width").item(0).getTextContent();
		        	  lsp_height = element.getElementsByTagName("lsp_height").item(0).getTextContent();
	        	  }
	        	  
	        	  NodeList buttons = node.getChildNodes();
	        	  for (int j = 0)
	          }
	          */

		      System.out.println("lsp_width : " + lsp_width);
		      System.out.println("lsp_height : " + lsp_height);
	          

	      } catch (ParserConfigurationException | SAXException | IOException e) {
	          e.printStackTrace();
	      }

	}

}
