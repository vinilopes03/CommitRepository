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
            data = System.getenv("ADD");
        }
        else
        {
            data = null;
        }

        if (IO.staticReturnsTrue())
        {
            if (data != null)
            {
                Cookie cookieSink = new Cookie("lang", data);
                response.addCookie(cookieSink);
            }
        }
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (IO.staticReturnsFalse())
        {
            data = null; // Dead code
        }
        else
        {
            // FIX: Use a hardcoded string
            data = "foo";
        }

        if (IO.staticReturnsTrue())
        {
            if (data != null)
            {
                Cookie cookieSink = new Cookie("lang", data);
                response.addCookie(cookieSink);
            }
        }
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;

        if (IO.staticReturnsTrue())
        {
            // FIX: Use a hardcoded string
            data = "foo";
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
                response.addCookie(cookieSink);
            }
        }
    }

    // Placeholders for remaining methods
    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable {}
    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable {}
    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {}

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}