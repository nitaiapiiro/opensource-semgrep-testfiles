// License: Commons Clause License Condition v1.0[LGPL-2.1-only]
package com.test.servlet.xpath;

import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

//import static com.test.servlet.xpath.XpathUtils.getFileFromClasspath;

class XPassInjection extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String param = "";
        if (req.getHeader("ID") != null) {
            param = req.getHeader("ID");
        }
        // URL Decode the header value since req.getHeader() doesn't. Unlike req.getParameter().
        param = java.net.URLDecoder.decode(param, "UTF-8");
        String bar = "";
        if (param != null) {
            bar = new String(org.apache.commons.codec.binary.Base64.decodeBase64(org.apache.commons.codec.binary.Base64.encodeBase64(param.getBytes())));
        }
       try {
          
            java.io.FileInputStream file = new java.io.FileInputStream(getFileFromClasspath("xpath/employee.xml", this.getClass().getClassLoader()));
            javax.xml.parsers.DocumentBuilderFactory builderFactory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
            // Prevent XXE
            builderFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            javax.xml.parsers.DocumentBuilder builder = builderFactory.newDocumentBuilder();
            org.w3c.dom.Document xmlDocument = builder.parse(file);
            javax.xml.xpath.XPathFactory xpf = javax.xml.xpath.XPathFactory.newInstance();
            javax.xml.xpath.XPath xp = xpf.newXPath();

            final String expression = "/Employees/Employee[@ID='" + bar + "']";
            final String expression_safe = "/Employees/Employee[@ID='1']";
            // ruleid: java_xpathi_rule-XpathInjection
            String result1 = xp.evaluate(expression, xmlDocument);
            String result2 = evaluate(expression);
            // ok: java_xpathi_rule-XpathInjection
            String result3 = xp.evaluate(expression_safe, xmlDocument);
            resp.getWriter().println("Your query results 1 are: " + result1 + "<br/>");
            resp.getWriter().println("Your query results 2 are: " + result2 + "<br/>");
            resp.getWriter().println("Your query results 3 are: " + result3 + "<br/>");
            org.w3c.dom.NodeList nodeList1 = compileEvaluate(expression);
            resp.getWriter().println("Your query results 4 are: <br/>");
            for (int i = 0; i < nodeList1.getLength(); i++) {
                org.w3c.dom.Element value = (org.w3c.dom.Element) nodeList1.item(i);
                resp.getWriter().println(value.getTextContent() + "<br/>");
            }


            private final Map<String, String> xpathVariableMap = new HashMap<String, String>();
            xpathVariableMap.put("1", "1");
			xpathVariableMap.put("default", "1");
			xpathVariableMap.put("2", "2");
            String newbar = xpathVariableMap.getOrDefault(bar,"");

            AllowList = Arrays.asList("1","2");
            final String expr = "/Employees/Employee[@ID='" + bar + "']";
            if(AllowList.contains(bar)){
                // ok: java_xpathi_rule-XpathInjection
                xp.evaluate(expr);
            }
            boolean validation = AllowList.contains(bar);
            // ruleid: java_xpathi_rule-XpathInjection
            xp.evaluate(expr);
            if(validation){
                // ok: java_xpathi_rule-XpathInjection
                xp.evaluate(expr);
            }
            
            for(String s:new String[]{"1", "2"}) 
                if(s.equals(bar))
                    // ok: java_xpathi_rule-XpathInjection
                    xp.evaluate(expr);
                    
            final String expr = "/Employees/Employee[@ID='" + newbar + "']";
            // ok: java_xpathi_rule-XpathInjection
            String result1 = xp.evaluate(expr);

            String newbar2 = xpathVariableMap.getOrDefault(bar, bar);
            final String expr = "/Employees/Employee[@ID='" + newbar2 + "']";
            // ruleid: java_xpathi_rule-XpathInjection
            String result1 = xp.evaluate(expr);

        } catch (javax.xml.xpath.XPathExpressionException | javax.xml.parsers.ParserConfigurationException |
                 org.xml.sax.SAXException e) {
            resp.getWriter().println("Error parsing XPath input: '" + bar + "'");
            throw new ServletException(e);
        }
    }

    private String evaluate(String expression) throws IOException, SAXException, ParserConfigurationException, XPathExpressionException {
        java.io.FileInputStream file = new java.io.FileInputStream(getFileFromClasspath("xpath/employee.xml", this.getClass().getClassLoader()));
        javax.xml.parsers.DocumentBuilderFactory builderFactory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
        javax.xml.parsers.DocumentBuilder builder = builderFactory.newDocumentBuilder();
        org.w3c.dom.Document xmlDocument = builder.parse(file);
        javax.xml.xpath.XPathFactory xpf = javax.xml.xpath.XPathFactory.newInstance();
        // Prevent XXE
        builderFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        javax.xml.xpath.XPath xp = xpf.newXPath();
        // ruleid: java_xpathi_rule-XpathInjection
        String result = xp.evaluate(expression, xmlDocument);
        return result;
    }

    private org.w3c.dom.NodeList compileEvaluate(String expression) throws IOException, SAXException, ParserConfigurationException, XPathExpressionException {
        java.io.FileInputStream file = new java.io.FileInputStream(getFileFromClasspath("xpath/employee.xml", this.getClass().getClassLoader()));
        javax.xml.parsers.DocumentBuilderFactory builderFactory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
        // Prevent XXE
        builderFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        javax.xml.parsers.DocumentBuilder builder = builderFactory.newDocumentBuilder();
        org.w3c.dom.Document xmlDocument = builder.parse(file);
        javax.xml.xpath.XPathFactory xpf = javax.xml.xpath.XPathFactory.newInstance();
        javax.xml.xpath.XPath xp = xpf.newXPath();
        // ruleid: java_xpathi_rule-XpathInjection
        return (org.w3c.dom.NodeList) xp.compile(expression).evaluate(xmlDocument, javax.xml.xpath.XPathConstants.NODESET);
    }

    public static File getFileFromClasspath(String fileName, ClassLoader classLoader) {
        URL url = classLoader.getResource(fileName);
        try {
            return new File(url.toURI().getPath());
        } catch (URISyntaxException e) {
            System.out.println("The file form the classpath cannot be loaded.");
        }
        return null;
    }
    
    private void safe_1(javax.xml.xpath.XPath xp, String bar) throws XPathExpressionException, Exception {
        SimpleXPathVariableResolver resolver = new SimpleXPathVariableResolver();
        // ok: java_xpathi_rule-XpathInjection
        resolver.setVariable(new QName("author"), bar);
        xp.setXPathVariableResolver(resolver);
        final String expression_res = "/Employees/Employee[@ID=$id]";
        String result = xp.evaluate(expression_res);
    }

    private void unsafe_1(javax.xml.xpath.XPath xp, String bar) throws XPathExpressionException, Exception {
        SimpleXPathVariableResolver resolver = new SimpleXPathVariableResolver();
        resolver.setVariable(new QName("author"), bar);
        xp.setXPathVariableResolver(resolver);
        final String expression_res = "/Employees/Employee[@ID='" + bar + "']";
        // ruleid: java_xpathi_rule-XpathInjection
        String result = xp.evaluate(expression_res);
    }
}

class SimpleXPathVariableResolver implements XPathVariableResolver {
    // Use a map or lookup table to store variables for resolution
    private HashMap<QName, String> variables = new HashMap<>();
    // Allow caller to set variables
    public void setVariable(QName name, String value) {
        variables.put(name, value);
    }
    // Implement the resolveVariable to return the value
    @Override
    public Object resolveVariable(QName name) {
        return variables.getOrDefault(name, "");
    }
}