commit 4d5e6f7g8h
Author: Developer <developer@example.com>
Date:   2023-10-13

    Implement goodG2B1 method logic

package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_05 extends AbstractTestCaseServlet
{
    private boolean privateTrue = true;
    private boolean privateFalse = false;

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (privateTrue)
        {
            data = System.getenv("ADD");
        }
        else
        {
            data = null;
        }

        if (privateTrue)
        {
            if (data != null)
            {
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (privateFalse)
        {
            data = null;
        }
        else
        {
            /* FIX: Use a hardcoded string */
            data = "foo";
        }

        if (privateTrue)
        {
            if (data != null)
            {
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}