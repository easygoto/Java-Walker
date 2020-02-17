package baseapi.xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ParserHandler extends DefaultHandler {
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println("解析元素开始");
        super.startElement(uri, localName, qName, attributes);
        if (qName.equals("book")) {
            int num = attributes.getLength();
            for (int i = 0; i < num; i++) {
                System.out.println(attributes.getQName(i) + " = " + attributes.getValue(i));
            }
        }
        if (!qName.equals("book") && !qName.equals("bookstore")) {
            System.out.println(qName);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("解析元素结束");
        super.endElement(uri, localName, qName);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        String value = new String(ch, start, length);
        if (!value.trim().equals("")) {
            System.out.println(value);
        }
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("解析xml开始");
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("解析xml结束");
        super.endDocument();
    }
}
