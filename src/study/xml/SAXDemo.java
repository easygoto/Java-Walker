package study.xml;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SAXDemo {

  public static void main(String[] args) throws Throwable {

    SAXParserFactory factory = SAXParserFactory.newInstance();
    
    SAXParser parser = factory.newSAXParser();
    
    SAXParserHandler handler = new SAXParserHandler();
    
    parser.parse("bookstore.xml", handler);
  }

}
