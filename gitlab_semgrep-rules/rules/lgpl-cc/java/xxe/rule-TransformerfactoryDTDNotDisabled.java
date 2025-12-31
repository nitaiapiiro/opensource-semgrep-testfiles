// License: Commons Clause License Condition v1.0[LGPL-2.1-only]
// https://github.com/semgrep/semgrep-rules/blob/release/java/lang/security/audit/xxe/transformerfactory-dtds-not-disabled.java

import javax.xml.transform.TransformerFactory;

class TransformerFactory {
    public void GoodTransformerFactory(String xmlContent) {
        TransformerFactory factory = TransformerFactory.newInstance();
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
        // ok:java_xxe_rule-TransformerfactoryDTDNotDisabled
        factory.newTransformer(new StreamSource(xmlContent));
    }

    public void GoodTransformerFactory2(String xmlContent) {
        TransformerFactory factory = TransformerFactory.newInstance();
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        // ok:java_xxe_rule-TransformerfactoryDTDNotDisabled
        factory.newTransformer(new StreamSource(xmlContent));
    }

    public void GoodTransformerFactory3(String xmlContent) {
        TransformerFactory factory = TransformerFactory.newInstance();
        factory.setAttribute("http://javax.xml.XMLConstants/property/accessExternalStylesheet", "");
        factory.setAttribute("http://javax.xml.XMLConstants/property/accessExternalDTD", "");
        // ok:java_xxe_rule-TransformerfactoryDTDNotDisabled
        factory.newTransformer(new StreamSource(xmlContent));
    }

    public void GoodTransformerFactory4(String xmlContent) {
        TransformerFactory factory = TransformerFactory.newInstance();
        factory.setAttribute("http://javax.xml.XMLConstants/property/accessExternalDTD", "");
        factory.setAttribute("http://javax.xml.XMLConstants/property/accessExternalStylesheet", "");
        // ok:java_xxe_rule-TransformerfactoryDTDNotDisabled
        factory.newTransformer(new StreamSource(xmlContent));
    }

    public void BadTransformerFactory(String xmlContent) {
        TransformerFactory factory = TransformerFactory.newInstance();
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        // ruleid:java_xxe_rule-TransformerfactoryDTDNotDisabled
        factory.newTransformer(new StreamSource(xmlContent));
    }

    public void BadTransformerFactory2(String xmlContent) {
        TransformerFactory factory = TransformerFactory.newInstance();
        factory.setAttribute("http://javax.xml.XMLConstants/property/accessExternalDTD", "");
        // ruleid:java_xxe_rule-TransformerfactoryDTDNotDisabled
        factory.newTransformer(new StreamSource(xmlContent));
    }

    public void BadTransformerFactory3(String xmlContent) {
        TransformerFactory factory = TransformerFactory.newInstance();
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
        // ruleid:java_xxe_rule-TransformerfactoryDTDNotDisabled
        factory.newTransformer(new StreamSource(xmlContent));
    }

    public void BadTransformerFactory4(String xmlContent) {
        TransformerFactory factory = TransformerFactory.newInstance();
        factory.setAttribute("http://javax.xml.XMLConstants/property/accessExternalStylesheet", "");
        // ruleid:java_xxe_rule-TransformerfactoryDTDNotDisabled
        factory.newTransformer(new StreamSource(xmlContent));
    }

}

class TransformerFactory2 {
    static TransformerFactory factory = TransformerFactory.newInstance();

    static {
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
    }

    public TransformerFactory2(String xmlContent) {
        goodTransformerFactory(xmlContent);
    }

    public void goodTransformerFactory(String xmlContent) {

        // ok:java_xxe_rule-TransformerfactoryDTDNotDisabled
        factory.newTransformer(new StreamSource(xmlContent));
    }

}

class TransformerFactory3 {
    static TransformerFactory factory = TransformerFactory.newInstance();

    static {
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
    }

    public TransformerFactory3(String xmlContent) {
        goodTransformerFactory(xmlContent);
    }

    public void goodTransformerFactory(String xmlContent) {

        // ok:java_xxe_rule-TransformerfactoryDTDNotDisabled
        factory.newTransformer(new StreamSource(xmlContent));
    }

}

class TransformerFactory4 {
    static TransformerFactory factory = TransformerFactory.newInstance();

    static {
        factory.setAttribute("http://javax.xml.XMLConstants/property/accessExternalDTD", "");
        factory.setAttribute("http://javax.xml.XMLConstants/property/accessExternalStylesheet", "");
    }

    public TransformerFactory4(String xmlContent) {
        goodTransformerFactory(xmlContent);
    }

    public void goodTransformerFactory(String xmlContent) {

        // ok:java_xxe_rule-TransformerfactoryDTDNotDisabled
        factory.newTransformer(new StreamSource(xmlContent));
    }

}

class TransformerFactory5 {
    static TransformerFactory factory = TransformerFactory.newInstance();

    static {
        factory.setAttribute("http://javax.xml.XMLConstants/property/accessExternalStylesheet", "");
        factory.setAttribute("http://javax.xml.XMLConstants/property/accessExternalDTD", "");
    }

    public TransformerFactory5(String xmlContent) {
        goodTransformerFactory(xmlContent);
    }

    public void goodTransformerFactory(String xmlContent) {

        // ok:java_xxe_rule-TransformerfactoryDTDNotDisabled
        factory.newTransformer(new StreamSource(xmlContent));
    }

}

class TransformerFactory6 {

    TransformerFactory factory = null;

    public void parserFactory(boolean condition, String xmlContent) throws ParserConfigurationException {

        if (condition) {
            factory = TransformerFactory.newInstance();
            // ruleid: java_xxe_rule-TransformerfactoryDTDNotDisabled
            Transformer t1 = factory.newTransformer(new StreamSource(xmlContent));
        }

        factory.setAttribute("http://javax.xml.XMLConstants/property/accessExternalStylesheet", "");
        factory.setAttribute("http://javax.xml.XMLConstants/property/accessExternalDTD", "");

        // ok:java_xxe_rule-TransformerfactoryDTDNotDisabled
        Transformer t2 = factory.newTransformer(new StreamSource(xmlContent));
    }

}

class TransformerFactory7 {
    public void somemethod(String xmlContent) throws Exception {
        TransformerFactory factory = TransformerFactory.newInstance();
        setFeatures(factory);
        // ok:java_xxe_rule-TransformerfactoryDTDNotDisabled
        Transformer t1 = factory.newTransformer(new StreamSource(xmlContent));
    }

    private void setFeatures(TransformerFactory factory) throws Exception {
        factory.setAttribute("http://javax.xml.XMLConstants/property/accessExternalStylesheet", "");
        factory.setAttribute("http://javax.xml.XMLConstants/property/accessExternalDTD", "");
    }

}

class TransformerFactory8 {
    public void somemethod(String xmlContent) throws Exception {
        TransformerFactory factory = TransformerFactory.newInstance();
        setFeatures(factory);
        // ok:java_xxe_rule-TransformerfactoryDTDNotDisabled
        Transformer t1 = factory.newTransformer(new StreamSource(xmlContent));
    }

    private void setFeatures(TransformerFactory factory) throws Exception {
        factory.setAttribute("http://javax.xml.XMLConstants/property/accessExternalDTD", "");
        factory.setAttribute("http://javax.xml.XMLConstants/property/accessExternalStylesheet", "");
    }

}

class TransformerFactory9 {
    public void somemethod(String xmlContent) throws Exception {
        TransformerFactory factory = TransformerFactory.newInstance();
        setFeatures(factory);
        // ok:java_xxe_rule-TransformerfactoryDTDNotDisabled
        Transformer t1 = factory.newTransformer(new StreamSource(xmlContent));
    }

    private void setFeatures(TransformerFactory factory) throws Exception {
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
    }

}

class TransformerFactory10 {
    public void somemethod(String xmlContent) throws Exception {
        TransformerFactory factory = TransformerFactory.newInstance();
        setFeatures(factory);
        // ok:java_xxe_rule-TransformerfactoryDTDNotDisabled
        Transformer t1 = factory.newTransformer(new StreamSource(xmlContent));
    }

    private void setFeatures(TransformerFactory factory) throws Exception {
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
    }

}

class TransformerFactory11 {
    public void somemethod(String xmlContent) throws Exception {
        TransformerFactory factory = TransformerFactory.newInstance();
        setFeatures(factory);
        // ruleid:java_xxe_rule-TransformerfactoryDTDNotDisabled
        Transformer t1 = factory.newTransformer(new StreamSource(xmlContent));
    }

    private void setFeatures(TransformerFactory factory) throws Exception {
        factory.setAttribute("Random", "");
    }

}

// This works only with pro-engine. I was hoping it'll work with
// `pattern-sources: - by-side-effect: true`, but it doesn't.
// todo rules
class TransformerFactory12 {

    TransformerFactory factory = null;

    public void parserFactory(boolean condition, String xmlContent) throws ParserConfigurationException {

        if (condition) {
            factory = newFactory();
            // todoruleid: java_xxe_rule-TransformerfactoryDTDNotDisabled
            Transformer t1 = factory.newTransformer(new StreamSource(xmlContent));
        } else {
            factory = newFactory();
            factory.setAttribute("http://javax.xml.XMLConstants/property/accessExternalStylesheet", "");
            factory.setAttribute("http://javax.xml.XMLConstants/property/accessExternalDTD", "");
            // todook: java_xxe_rule-TransformerfactoryDTDNotDisabled
            Transformer t1 = factory.newTransformer(new StreamSource(xmlContent));
        }
    }

    private TransformerFactory newFactory() {
        TransformerFactory factory = TransformerFactory.newInstance();
        return factory;
    }

}
