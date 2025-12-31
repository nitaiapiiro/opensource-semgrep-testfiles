// License: Commons Clause License Condition v1.0[LGPL-2.1-only]
// https://github.com/semgrep/semgrep-rules/blob/release/java/lang/security/audit/xxe/saxparserfactory-disallow-doctype-decl-missing.java

import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.ParserConfigurationException;

class GoodSAXParserFactory {
    public void GoodSAXParserFactory1() throws ParserConfigurationException {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        // ok:java_xxe_rule-SAXParserFactoryDisallowDoctypeDeclMissing
        spf.newSAXParser();
    }

    public void GoodSAXParserFactory2() throws ParserConfigurationException {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        spf.setFeature("http://xml.org/sax/features/external-general-entities", false);
        // ok:java_xxe_rule-SAXParserFactoryDisallowDoctypeDeclMissing
        spf.newSAXParser();
    }

    public void GoodSAXParserFactory3() throws ParserConfigurationException {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setFeature("http://xml.org/sax/features/external-general-entities", false);
        spf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        // ok:java_xxe_rule-SAXParserFactoryDisallowDoctypeDeclMissing
        spf.newSAXParser();
    }

    public void GoodSAXParserFactory4() throws ParserConfigurationException {
        SAXParserFactory factory = XmlUtils.getSecureSAXParserFactory();
        // Deep semgrep could find issues like this
        // ok:java_xxe_rule-SAXParserFactoryDisallowDoctypeDeclMissing
        saxparser = factory.newSAXParser();
    }
}

class BadSAXParserFactory {
    public void BadSAXParserFactory1() throws ParserConfigurationException {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        // ruleid:java_xxe_rule-SAXParserFactoryDisallowDoctypeDeclMissing
        spf.newSAXParser();
    }

    public void BadSAXParserFactory2() throws ParserConfigurationException {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setFeature("somethingElse", true);
        // ruleid:java_xxe_rule-SAXParserFactoryDisallowDoctypeDeclMissing
        spf.newSAXParser();
    }
}

class GoodSAXParserFactoryStatic {

    private static SAXParserFactory spf = SAXParserFactory.newInstance();

    static {
        spf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        // ok:java_xxe_rule-SAXParserFactoryDisallowDoctypeDeclMissing
        spf.newSAXParser();
    }

}

class BadSAXParserFactoryStatic {

    private static SAXParserFactory spf = SAXParserFactory.newInstance();

    static {
        spf.setFeature("not-a-secure-feature", true);
    }

    public void doSomething() {
        // ruleid:java_xxe_rule-SAXParserFactoryDisallowDoctypeDeclMissing
        spf.newSAXParser();
    }

}

class OneMoreGoodSAXParserFactory {

    public void GoodSAXParserFactory(boolean condition) throws ParserConfigurationException {
        SAXParserFactory spf = null;

        if (condition) {
            spf = SAXParserFactor.newInstance();
        } else {
            spf = newFactory();
        }
        spf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        // ok:java_xxe_rule-SAXParserFactoryDisallowDoctypeDeclMissing
        spf.newSAXParser();
    }

    private SAXParserFactory newFactory() {
        return SAXParserFactory.newInstance();
    }

}

class OneMoreBadSAXParserFactory {

    public void GoodSAXParserFactory(boolean condition) throws ParserConfigurationException {
        SAXParserFactory spf = null;

        if (condition) {
            spf = SAXParserFactory.newInstance();
        } else {
            spf = newFactory();
        }
        // ruleid:java_xxe_rule-SAXParserFactoryDisallowDoctypeDeclMissing
        spf.newSAXParser();
    }

    private SAXParserFactory newFactory() {
        return SAXParserFactory.newInstance();
    }

}

class GoodSAXParserFactoryCtr {

    private final SAXParserFactory spf;

    public GoodSAXParserFactoryCtr() throws Exception {
        spf = SAXParserFactory.newInstance();
        spf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        // ok:java_xxe_rule-SAXParserFactoryDisallowDoctypeDeclMissing
        spf.newSAXParser();
    }
}

class GoodSAXParserFactoryCtr2 {
    public void somemethod() throws Exception {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        setFeatures(spf);
        // ok:java_xxe_rule-SAXParserFactoryDisallowDoctypeDeclMissing
        spf.newSAXParser();
    }

    private void setFeatures(SAXParserFactory spf) throws Exception {
        spf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
    }

}

class GoodSAXParserFactoryCtr3 {
    public void somemethod() throws Exception {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        setFeatures(spf);
        // ok:java_xxe_rule-SAXParserFactoryDisallowDoctypeDeclMissing
        spf.newSAXParser();
    }

    private void setFeatures(SAXParserFactory spf) throws Exception {
        spf.setFeature("http://xml.org/sax/features/external-general-entities", false);
        spf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
    }

}
