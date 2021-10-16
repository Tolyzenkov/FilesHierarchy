import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class XMLDataParser {

    private static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) {
        File file = new File("D:\\Zona Downloads\\ITVDN\\Java\\hierarchy.xml");
        System.out.println(Arrays.toString(args));
//        File file = new File(args[0]);
        DocumentBuilderFactory  documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;

        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            NodeList nodeList = document.getElementsByTagName("child");
            List<Child> nameList = new ArrayList<>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                nameList.add(getName(nodeList.item(i)));
            }

            // печатаем в консоль информацию
            for (Child ch : nameList) {
                stringBuilder.append(ch.toString());
            }
            System.out.println(stringBuilder);


        } catch (Exception e) {
            System.err.println("FAIL");
        }

    }

    private static Child getName(Node node) {
        Child children = new Child();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            children.setName(getTagAttr(element));
        }
        return children;
    }

    // получаем значение элемента по указанному тегу
    private static String getTagValue(Element element) {
        NodeList nodeList = element.getElementsByTagName("name").item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }

    private static String getTagAttr(Element element) {
        String attr = element.getAttribute("is-file");
//        System.out.println(attr);
        return getTagValue(element);
    }

}
