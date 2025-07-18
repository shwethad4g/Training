package Day10Task;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;


public class ParseXMLFromString {
    public static void main(String[] args) {
        String xmlData = "<person><name>John</name><age>25</age><gender>Male</gender>" +
                "<qualification>B.Tech</qualification></person>";

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new java.io.ByteArrayInputStream(xmlData.getBytes()));
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList list = doc.getDocumentElement().getChildNodes();

            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    System.out.println(node.getNodeName() + ": " + node.getTextContent());
                }
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
