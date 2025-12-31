// License: Commons Clause License Condition v1.0[LGPL-2.1-only]
// https://semgrep.dev/playground/r/ZRTQN09/java.lang.security.xmlinputfactory-external-entities-enabled.xmlinputfactory-external-entities-enabled

import javax.xml.stream.XMLInputFactory;

class GoodXMLInputFactory {
    public GoodXMLInputFactory() {
        final XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
        xmlInputFactory.setProperty(XMLInputFactory.SUPPORT_DTD, false);
        // ok: java_xxe_rule-XMLInputFactoryExternalEntitiesEnabled
        xmlInputFactory.setProperty("javax.xml.stream.isSupportingExternalEntities", false);
    }
}

class BadXMLInputFactory {
    public BadXMLInputFactory() {
        final XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
        // ruleid: java_xxe_rule-XMLInputFactoryExternalEntitiesEnabled
        xmlInputFactory.setProperty("javax.xml.stream.isSupportingExternalEntities", true);
    }
}
