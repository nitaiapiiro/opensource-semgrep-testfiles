// License: LGPL-3.0 License (c) find-sec-bugs
package inject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import org.jboss.seam.log.Logging;
import org.jboss.seam.log.Log;

// ref: java_inject_rule-SeamLogInjection
public class SeamLogInjection extends HttpServlet {
    Log log = Logging.getLog(SeamLogInjection.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String input = request.getParameter("userInput");
        // ruleid: java_inject_rule-SeamLogInjection
        log.info("This is user controlled input ="+ input);
        // ok: java_inject_rule-SeamLogInjection
        log.info("This is user controlled input (safe) = #0", input);

        try (PrintWriter out = response.getWriter()) {
            out.println("RequestParamToHeader Test: " + response);
        }
    }

    public void logUser1(String userInput) {
        // ruleid: java_inject_rule-SeamLogInjection
        log.info("Current logged in user : " + userInput);
    }

    public void logUser2(String userInput) {
        // ok: java_inject_rule-SeamLogInjection
        log.info("Current logged in user : #0", userInput);
    }

}
