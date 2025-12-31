// License: Commons Clause License Condition v1.0[LGPL-2.1-only]
// https://github.com/semgrep/semgrep-rules/blob/release/java/lang/security/audit/xxe/documentbuilderfactory-disallow-doctype-decl-missing.java

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

class GoodDocumentBuilderFactory {
    public void GoodDocumentBuilderFactory1() throws ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        // ok:java_xxe_rule-DocumentBuilderFactoryDisallowDoctypeDeclMissing
        dbf.newDocumentBuilder();
    }

    public void GoodDocumentBuilderFactory2() throws ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        dbf.setFeature("http://xml.org/sax/features/external-general-entities", false);
        // ok:java_xxe_rule-DocumentBuilderFactoryDisallowDoctypeDeclMissing
        dbf.newDocumentBuilder();
    }

    public void GoodDocumentBuilderFactory3() throws ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setFeature("http://xml.org/sax/features/external-general-entities", false);
        dbf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        // ok:java_xxe_rule-DocumentBuilderFactoryDisallowDoctypeDeclMissing
        dbf.newDocumentBuilder();
    }

    public void GoodDocumentBuilderFactory4() throws ParserConfigurationException {
        DocumentBuilderFactory factory = XmlUtils.getSecureDocumentBuilderFactory();
        // Deep semgrep could find issues like this
        // ok:java_xxe_rule-DocumentBuilderFactoryDisallowDoctypeDeclMissing
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
    }
}

class BadDocumentBuilderFactory {
    public void BadDocumentBuilderFactory1() throws ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        // ruleid:java_xxe_rule-DocumentBuilderFactoryDisallowDoctypeDeclMissing
        dbf.newDocumentBuilder();
    }

    public void BadDocumentBuilderFactory2() throws ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setFeature("somethingElse", true);
        // ruleid:java_xxe_rule-DocumentBuilderFactoryDisallowDoctypeDeclMissing
        dbf.newDocumentBuilder();
    }
}

class GoodDocumentBuilderFactoryStatic {

    private static DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

    static {
        try {
            dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            // ok:java_xxe_rule-DocumentBuilderFactoryDisallowDoctypeDeclMissing
            dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e){
            System.out.println(e.getMessage());
        }
    }

    public void doSomething() throws ParserConfigurationException {
        // ok:java_xxe_rule-DocumentBuilderFactoryDisallowDoctypeDeclMissing
        dbf.newDocumentBuilder();
    }

}

class BadDocumentBuilderFactoryStatic {

    private static DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

    static {
        try {
            dbf.setFeature("not-a-secure-feature", true);
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        }
    }

    public void doSomething() throws ParserConfigurationException {
        // ruleid:java_xxe_rule-DocumentBuilderFactoryDisallowDoctypeDeclMissing
        dbf.newDocumentBuilder();
    }

}

class OneMoreGoodDocumentBuilderFactory {

    public void GoodDocumentBuilderFactory(boolean condition) throws ParserConfigurationException {
        DocumentBuilderFactory dbf = null;

        if (condition) {
            dbf = DocumentBuilderFactory.newInstance();
        } else {
            dbf = newFactory();
        }
        dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        // ok:java_xxe_rule-DocumentBuilderFactoryDisallowDoctypeDeclMissing
        dbf.newDocumentBuilder();
    }

    private DocumentBuilderFactory newFactory() {
        return DocumentBuilderFactory.newInstance();
    }

}

class OneMoreBadDocumentBuilderFactory {

    public void GoodDocumentBuilderFactory(boolean condition) throws ParserConfigurationException {
        DocumentBuilderFactory dbf = null;

        if (condition) {
            dbf = DocumentBuilderFactory.newInstance();
        } else {
            dbf = newFactory();
        }
        // ruleid:java_xxe_rule-DocumentBuilderFactoryDisallowDoctypeDeclMissing
        dbf.newDocumentBuilder();
    }

    private DocumentBuilderFactory newFactory() {
        return DocumentBuilderFactory.newInstance();
    }

}

class GoodDocumentBuilderFactoryCtr {

    private final DocumentBuilderFactory dbf;

    public GoodDocumentBuilderFactoryCtr() throws Exception {
        dbf = DocumentBuilderFactory.newInstance();
        dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        // ok:java_xxe_rule-DocumentBuilderFactoryDisallowDoctypeDeclMissing
        dbf.newDocumentBuilder();
    }
}

class GoodDocumentBuilderFactoryCtr2 {
    public void somemethod() throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        setFeatures(dbf);
        // ok:java_xxe_rule-DocumentBuilderFactoryDisallowDoctypeDeclMissing
        dbf.newDocumentBuilder();
    }

    private void setFeatures(DocumentBuilderFactory dbf) throws Exception {
        dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
    }

}

class GoodDocumentBuilderFactoryCtr3 {
    public void somemethod() throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        setFeatures(dbf);
        // ok:java_xxe_rule-DocumentBuilderFactoryDisallowDoctypeDeclMissing
        dbf.newDocumentBuilder();
    }

    private void setFeatures(DocumentBuilderFactory dbf) throws Exception {
        dbf.setFeature("http://xml.org/sax/features/external-general-entities", false);
        dbf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
    }

}
