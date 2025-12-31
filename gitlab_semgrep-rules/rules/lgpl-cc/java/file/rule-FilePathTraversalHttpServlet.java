// License: Commons Clause License Condition v1.0[LGPL-2.1-only]

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;

public class FilePathTraversalHttpServlet extends HttpServlet {
    private static org.apache.log4j.Logger log = Logger.getLogger(Register.class);

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String image = request.getParameter("image");
        // ruleid:java_file_rule_rule-FilePathTraversalHttpServlet
        File file = new File("static/images/", image);

        if (!file.exists()) {
            log.info(image + " could not be created.");
            response.sendError(500);
        }

        response.sendRedirect("/index.html");
    }

    public void ok(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // ok:java_file_rule_rule-FilePathTraversalHttpServlet
        String image = request.getParameter("image");
        File file = new File("static/images/", FilenameUtils.getName(image));

        if (!file.exists()) {
            log.info(image + " could not be created.");
            response.sendError(500);
        }

        response.sendRedirect("/index.html");
    }

    @Override
    public void doPost2(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // some code
        response.setContentType("text/html;charset=UTF-8");

        String[] values = request.getParameterValues("BenchmarkTest00045");
        String param;
        if (values != null && values.length > 0) param = values[0];
        else param = "";

        String fileName = "TESTFILES_DIR" + param;

        try (
                // Create the file first so the test won't throw an exception if it doesn't exist.
                // Note: Don't actually do this because this method signature could cause a tool to find
                // THIS file constructor
                // as a vuln, rather than the File signature we are trying to actually test.
                // If necessary, just run the benchmark twice. The 1st run should create all the necessary
                // files.
                // new java.io.File("TESTFILES_DIR" +
                // param).createNewFile();

                // ruleid: java_file_rule_rule-FilePathTraversalHttpServlet
                java.io.FileOutputStream fos = new java.io.FileOutputStream(new java.io.FileInputStream(fileName).getFD());) {
            response.getWriter()
                    .println(
                            "Now ready to write to file: "
                                    + org.owasp.esapi.ESAPI.encoder().encodeForHTML(fileName));

        } catch (Exception e) {
            System.out.println("Couldn't open FileOutputStream on file: '" + fileName + "'");
        }
    }
}