// License: Commons Clause License Condition v1.0[LGPL-2.1-only]
// https://semgrep.dev/playground/r/rxTyLdl/java.lang.security.audit.xxe.documentbuilderfactory-external-general-entities-true.documentbuilderfactory-external-general-entities-true

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import org.jdom2.input.SAXBuilder;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

class GoodDocumentBuilderFactory {
    public void GoodDocumentBuilderFactory1() throws ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        // ok:java_xxe_rule-ExternalGeneralEntitiesTrue
        dbf.setFeature("http://xml.org/sax/features/external-general-entities", false);
    }
}

class BadDocumentBuilderFactory {
    public void BadDocumentBuilderFactory1() throws ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        // ruleid:java_xxe_rule-ExternalGeneralEntitiesTrue
        dbf.setFeature("http://xml.org/sax/features/external-general-entities", true);
    }
}

class GoodSAXParserFactory {
    public void GoodSAXParserFactory1() throws ParserConfigurationException {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        // ok:java_xxe_rule-ExternalGeneralEntitiesTrue
        spf.setFeature("http://xml.org/sax/features/external-general-entities", false);
    }
}

class BadSAXParserFactory {
    public void BadSAXParserFactory1() throws ParserConfigurationException {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        // ruleid:java_xxe_rule-ExternalGeneralEntitiesTrue
        spf.setFeature("http://xml.org/sax/features/external-general-entities", true);
    }
}

class GoodSAXBuilder {
    public void GoodSAXBuilder1() throws ParserConfigurationException {
        SAXBuilder saxBuilder = new SAXBuilder();
        // ok:java_xxe_rule-ExternalGeneralEntitiesTrue
        saxBuilder.setFeature("http://xml.org/sax/features/external-general-entities", false);
    }
}

class BadSAXBuilder {
    public void BadSAXBuilder1() throws ParserConfigurationException {
        SAXBuilder saxBuilder = new SAXBuilder();
        // ruleid:java_xxe_rule-ExternalGeneralEntitiesTrue
        saxBuilder.setFeature("http://xml.org/sax/features/external-general-entities", true);
    }
}

class GoodSAXReader {
    public void GoodSAXReader1() throws ParserConfigurationException {
        SAXReader saxReader = new SAXReader();
        // ok:java_xxe_rule-ExternalGeneralEntitiesTrue
        saxReader.setFeature("http://xml.org/sax/features/external-general-entities", false);
    }
}

class BadSAXReader {
    public void BadSAXReader1() throws ParserConfigurationException {
        SAXReader saxReader = new SAXReader();
        // ruleid:java_xxe_rule-ExternalGeneralEntitiesTrue
        saxReader.setFeature("http://xml.org/sax/features/external-general-entities", true);
    }
}

class GoodXMLReader {
    public void GoodXMLReader1() throws SAXException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
        SAXParser saxParser = factory.newSAXParser();
        // Obtain an XMLReader from the SAXParser
        XMLReader xmlReader = saxParser.getXMLReader();
        // ok:java_xxe_rule-ExternalGeneralEntitiesTrue
        xmlReader.setFeature("http://xml.org/sax/features/external-general-entities", false);
    }
}

class BadXMLReader {
    public void BadXMLReader1() throws ParserConfigurationException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
        SAXParser saxParser = factory.newSAXParser();
        // Obtain an XMLReader from the SAXParser
        XMLReader xmlReader = saxParser.getXMLReader();
        // ruleid:java_xxe_rule-ExternalGeneralEntitiesTrue
        xmlReader.setFeature("http://xml.org/sax/features/external-general-entities", true);
    }
}
