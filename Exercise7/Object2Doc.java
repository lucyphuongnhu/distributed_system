import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Object2Doc {
	public static Node createNode(Document doc){
		//Create sub-element
		Element bookNode = doc.createElement("book");
		
		//Create Title Node
		Element titleNode = doc.createElement("title");
		titleNode.appendChild(doc.createTextNode("Java Programming"));
		bookNode.appendChild(titleNode);
		
		//Create Publisher Node
		Element publisherNode = doc.createElement("publisher");
		publisherNode.appendChild(doc.createTextNode("MIT Press"));
		bookNode.appendChild(publisherNode);
		
		//Create Author Node
		Element authorNode = doc.createElement("author");
		
		//Create Name node
		Element nameNode = doc.createElement("name");
		nameNode.appendChild(doc.createTextNode("John"));
		authorNode.appendChild(nameNode);

		//Create Name node
		Element ageNode = doc.createElement("age");
		ageNode.appendChild(doc.createTextNode("40"));
		authorNode.appendChild(ageNode);

		bookNode.appendChild(authorNode);

		return bookNode;
	}


	public static void main(String[] args) throws ParserConfigurationException, TransformerException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.newDocument();

		//Create root element
		Element root = doc.createElement("books");
		doc.appendChild(root);

		//Create sub-node	
		root.appendChild(createNode(doc));
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer;

		try {
			transformer = transformerFactory.newTransformer();
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(writer));
			System.out.println();

		} 
		catch (TransformerException e) {
			e.printStackTrace();
		}
	}
}