// License: Commons Clause License Condition v1.0[LGPL-2.1-only]

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.Runtime;

public class bad1 extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String param = "";
        if (request.getHeader("UserDefined") != null) {
            param = request.getHeader("UserDefined");
        }

        param = java.net.URLDecoder.decode(param, "UTF-8");

        String cmd =
                org.owasp.benchmark.helpers.Utils.getInsecureOSCommandString(
                        this.getClass().getClassLoader());
        String[] args = {cmd};
        String[] argsEnv = {param};

        Runtime r = Runtime.getRuntime();

        try {
            // ruleid: java_inject_rule-EnvInjection
            Process p = r.exec(args, argsEnv);
            org.owasp.benchmark.helpers.Utils.printOSCommandResults(p, response);
        } catch (IOException e) {
            System.out.println("Problem executing cmdi - TestCase");
            response.getWriter()
                    .println(org.owasp.esapi.ESAPI.encoder().encodeForHTML(e.getMessage()));
            return;
        }
    }
}


public class bad2 extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String val = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null)
		{
			for (Cookie cookie : cookies)
			{
				if (name.equals(cookie.getName()))
				{
					val = cookie.getValue();
					break;
				}
			}
		}

        String cmd =
                org.owasp.benchmark.helpers.Utils.getInsecureOSCommandString(
                        this.getClass().getClassLoader());
        String[] args = {cmd};
        String[] argsEnv = {val};

        Runtime r = Runtime.getRuntime();

        try {
            // ruleid: java_inject_rule-EnvInjection
            Process p = r.exec(args, argsEnv);
            org.owasp.benchmark.helpers.Utils.printOSCommandResults(p, response);
        } catch (IOException e) {
            System.out.println("Problem executing cmdi - TestCase");
            response.getWriter()
                    .println(org.owasp.esapi.ESAPI.encoder().encodeForHTML(e.getMessage()));
            return;
        }
    }

public class bad3 extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String param = "";
        if (request.getHeader("UserDefined") != null) {
            param = request.getHeader("UserDefined");
        }

        param = java.net.URLDecoder.decode(param, "UTF-8");

        String cmd =
                org.owasp.benchmark.helpers.Utils.getInsecureOSCommandString(
                        this.getClass().getClassLoader());

        try {
        ProcessBuilder process =
            new ProcessBuilder().command(cmd);

          process
              .environment()
              // ruleid: java_inject_rule-EnvInjection
              .put("PATH",param);

        return process.start().waitFor();
      } catch (IOException e) {
        System.out.println("Problem executing cmdi - TestCase");
        response.getWriter()
                .println(org.owasp.esapi.ESAPI.encoder().encodeForHTML(e.getMessage()));
        return;
      }
    }
}

public class bad4 extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String param = "";
        if (request.getHeader("UserDefined") != null) {
            param = request.getHeader("UserDefined");
        }

        param = java.net.URLDecoder.decode(param, "UTF-8");

        String cmd =
                org.owasp.benchmark.helpers.Utils.getInsecureOSCommandString(
                        this.getClass().getClassLoader());

        try {
        ProcessBuilder process =
            new ProcessBuilder().command(cmd);

        Map<String, String> env = process.environment();
        // ruleid: java_inject_rule-EnvInjection
        env.put("PATH",param);

        return process.start().waitFor();
      } catch (IOException e) {
        System.out.println("Problem executing cmdi - TestCase");
        response.getWriter()
                .println(org.owasp.esapi.ESAPI.encoder().encodeForHTML(e.getMessage()));
        return;
      }
    }
}

public class good1 extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String param = "";
        if (request.getHeader("UserDefined") != null) {
            param = request.getHeader("UserDefined");
        }

        param = java.net.URLDecoder.decode(param, "UTF-8");

        String cmd =
                org.owasp.benchmark.helpers.Utils.getInsecureOSCommandString(
                        this.getClass().getClassLoader());
        String[] args = {cmd};
        String[] argsEnv = {cmd};

        Runtime r = Runtime.getRuntime();

        try {
            // ok: java_inject_rule-EnvInjection
            Process p = r.exec(args, argsEnv);
            org.owasp.benchmark.helpers.Utils.printOSCommandResults(p, response);

            // ok: java_inject_rule-EnvInjection
            Process p = r.exec(param, argsEnv);
        } catch (IOException e) {
            System.out.println("Problem executing cmdi - TestCase");
            response.getWriter()
                    .println(org.owasp.esapi.ESAPI.encoder().encodeForHTML(e.getMessage()));
            return;
        }
    }
}
public class good2 extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String param = "";
        if (request.getHeader("UserDefined") != null) {
            param = request.getHeader("UserDefined");
        }

        param = java.net.URLDecoder.decode(param, "UTF-8");
        String cmd =
                org.owasp.benchmark.helpers.Utils.getInsecureOSCommandString(
                        this.getClass().getClassLoader());
        String[] args = {cmd};

        String[] allowList = {"FOO=true","FOO=false","BAR=true", "BAR=false"}
        if(Arrays.asList(allowList).contains(param)){
            String[] argsEnv = {param};
        }
        Runtime r = Runtime.getRuntime();

        try {
            // ok: java_inject_rule-EnvInjection
            Process p = r.exec(args, argsEnv);
            org.owasp.benchmark.helpers.Utils.printOSCommandResults(p, response);        
        } catch (IOException e) {
            System.out.println("Problem executing cmdi - TestCase");
            response.getWriter()
                    .println(org.owasp.esapi.ESAPI.encoder().encodeForHTML(e.getMessage()));
            return;
        }
    }
}
