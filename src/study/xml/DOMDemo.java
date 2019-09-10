package study.xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class DOMDemo {

    public static void main(String[] args) throws Exception {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse("bookstore.xml");

        NodeList bookList = document.getElementsByTagName("book");

        System.out.println(bookList.getLength());

        Element book = (Element) bookList.item(0);

        System.out.println(book.getElementsByTagName("title").item(0).getTextContent());

        System.out.println(bookList.item(0).getAttributes().getNamedItem("id").getNodeValue());

    }

}
