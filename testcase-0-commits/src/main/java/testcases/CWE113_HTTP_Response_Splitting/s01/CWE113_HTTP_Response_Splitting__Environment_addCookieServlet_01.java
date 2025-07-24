package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01 extends AbstractTestCaseServlet {

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;

        // POTENTIAL FLAW: Read data from an environment variable
        data = System.getenv("ADD");

        if (data != null) {
            // POTENTIAL FLAW: Input not verified before inclusion in the cookie
            Cookie cookieSink = new Cookie("lang", data);
            response.addCookie(cookieSink);
        }
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        goodG2B(request, response);
        goodB2G(request, response);
    }

    private void goodG2B(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;

        // FIX: Use a hardcoded string
        data = "foo";

        if (data != null) {
            // POTENTIAL FLAW: Input not verified before inclusion in the cookie
            Cookie cookieSink = new Cookie("lang", data);
            response.addCookie(cookieSink);
        }
    }

    private void goodB2G(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;

        // POTENTIAL FLAW: Read data from an environment variable
        data = System.getenv("ADD");

        if (data != null) {
            // FIX: use URLEncoder.encode to hex-encode non-alphanumerics
            Cookie cookieSink = new Cookie("lang", URLEncoder.encode(data, "UTF-8"));
            response.addCookie(cookieSink);
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}