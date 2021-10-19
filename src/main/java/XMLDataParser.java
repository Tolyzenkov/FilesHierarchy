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


    private static ArrayList<String> paths = new ArrayList<>();
    private static int count = 0;

    public static void main(String[] args) {
        File file = new File("C:\\Users\\tolyzenkov_dn\\IdeaProjects\\FilesHierarchy\\src\\main\\resources\\hierarchy.xml");
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
            getPaths(nameList);
            for (String a:paths) {
                System.out.println(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getPaths(List<Child> nameList) {
        while (count <= nameList.size()) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Child ch:nameList) {
            stringBuilder.append(ch.toString());
                if (ch.getName().startsWith("file")){
                    paths.add(stringBuilder.toString());
                    nameList.remove(ch);
//                    System.out.println(stringBuilder);
                    count++;
                    getPaths(nameList);
                }

            }
        }
        }

    private static Child getName(Node node) {
        Child children = new Child();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            children.setName(getTagValue(element));
        }
        return children;
    }

    // получаем значение элемента по указанному тегу
    private static String getTagValue(Element element) {
        NodeList nodeList = element.getElementsByTagName("name").item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }

    // проверка атрибута тега child
    private static String getTagAttr(Element element) {
        return element.getAttribute("is-file");
    }



}
