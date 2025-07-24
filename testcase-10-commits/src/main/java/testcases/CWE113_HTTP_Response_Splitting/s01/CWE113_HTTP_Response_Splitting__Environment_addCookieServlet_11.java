package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_11 extends AbstractTestCaseServlet
{
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (IO.staticReturnsTrue())
        {
            // POTENTIAL FLAW: Read data from an environment variable
            data = System.getenv("ADD");
        }
        else
        {
            data = null; // Dead code
        }

        if (IO.staticReturnsTrue())
        {
            if (data != null)
            {
                Cookie cookieSink = new Cookie("lang", data);
                // POTENTIAL FLAW: Input not verified before inclusion in the cookie
                response.addCookie(cookieSink);
            }
        }
    }

    // Placeholders for other methods
    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable {}
    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable {}
    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable {}
    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable {}
    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {}

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}