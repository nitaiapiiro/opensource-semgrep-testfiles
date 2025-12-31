// License: Commons Clause License Condition v1.0[LGPL-2.1-only]

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;

class XMLStreamRdr {

    public void parseXMLSafe1(InputStream input) throws XMLStreamException {

        XMLInputFactory factory = XMLInputFactory.newFactory();
        factory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false);
        factory.setProperty(XMLInputFactory.SUPPORT_DTD, false);
        // ok: java_xxe_rule-XMLStreamRdr
        XMLStreamReader reader = factory.createXMLStreamReader(input);
        while (reader.hasNext()) {
            reader.next();
        }
    }

    public void parseXMLSafe2(InputStream input) throws XMLStreamException {

        XMLInputFactory factory = XMLInputFactory.newFactory();
        factory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, Boolean.FALSE);
        // ok: java_xxe_rule-XMLStreamRdr
        XMLStreamReader reader = factory.createXMLStreamReader(input);
        while (reader.hasNext()) {
            reader.next();
        }
    }

    public void parseXMLSafe3(InputStream input) throws XMLStreamException {

        XMLInputFactory factory = XMLInputFactory.newFactory();
        factory.setProperty(XMLInputFactory.SUPPORT_DTD, false);
        // ok: java_xxe_rule-XMLStreamRdr
        XMLStreamReader reader = factory.createXMLStreamReader(input);
        while (reader.hasNext()) {
            reader.next();
        }
    }

    public void parseXMLSafe4(InputStream input) throws XMLStreamException {

        XMLInputFactory factory = XMLInputFactory.newFactory();
        factory.setProperty(XMLInputFactory.SUPPORT_DTD, Boolean.FALSE);
        // ok: java_xxe_rule-XMLStreamRdr
        XMLStreamReader reader = factory.createXMLStreamReader(input);
        while (reader.hasNext()) {
            reader.next();
        }
    }

    public void parseXMLSafe5(InputStream input) throws XMLStreamException {

        XMLInputFactory factory = XMLInputFactory.newFactory();
        factory.setProperty("javax.xml.stream.isSupportingExternalEntities", false);
        // ok: java_xxe_rule-XMLStreamRdr
        XMLStreamReader reader = factory.createXMLStreamReader(input);
        while (reader.hasNext()) {
            reader.next();
        }
    }

    public void parseXMLSafe6(InputStream input) throws XMLStreamException {

        XMLInputFactory factory = XMLInputFactory.newFactory();
        factory.setProperty("javax.xml.stream.isSupportingExternalEntities", Boolean.FALSE);
        // ok: java_xxe_rule-XMLStreamRdr
        XMLStreamReader reader = factory.createXMLStreamReader(input);
        while (reader.hasNext()) {
            reader.next();
        }
    }

    public void parseXMLSafe7(InputStream input) throws XMLStreamException {

        XMLInputFactory factory = XMLInputFactory.newFactory();
        factory.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        // ok: java_xxe_rule-XMLStreamRdr
        XMLStreamReader reader = factory.createXMLStreamReader(input);
        while (reader.hasNext()) {
            reader.next();
        }
    }

    public void parseXMLUnsafe1(InputStream input) throws XMLStreamException {

        XMLInputFactory factory = XMLInputFactory.newFactory();
        // ruleid: java_xxe_rule-XMLStreamRdr
        XMLStreamReader reader = factory.createXMLStreamReader(input);
        while (reader.hasNext()) {
            reader.next();
        }
    }

    public void parseXMLUnsafe2(InputStream input) throws XMLStreamException {

        XMLInputFactory factory = XMLInputFactory.newFactory();
        factory.setProperty("javax.xml.stream.isSupportingExternalEntities", true);
        // ruleid: java_xxe_rule-XMLStreamRdr
        XMLStreamReader reader = factory.createXMLStreamReader(input);
        while (reader.hasNext()) {
            reader.next();
        }
    }

    public void parseXMLUnsafe3(InputStream input) throws XMLStreamException {

        XMLInputFactory factory = XMLInputFactory.newFactory();
        factory.setProperty(XMLInputFactory.SUPPORT_DTD, true);
        // ruleid: java_xxe_rule-XMLStreamRdr
        XMLStreamReader reader = factory.createXMLStreamReader(input);
        while (reader.hasNext()) {
            reader.next();
        }
    }

    public void parseXMLUnsafe4(InputStream input) throws XMLStreamException {

        XMLInputFactory factory = XMLInputFactory.newFactory();
        factory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, true);
        // ruleid: java_xxe_rule-XMLStreamRdr
        XMLStreamReader reader = factory.createXMLStreamReader(input);
        while (reader.hasNext()) {
            reader.next();
        }
    }

}
