/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_04.java
Label Definition File: CWE113_HTTP_Response_Splitting.label.xml
Template File: sources-sinks-04.tmpl.java
*/
/*
* @description
* CWE: 113 HTTP Response Splitting
* BadSource: Environment Read data from an environment variable
* GoodSource: A hardcoded string
* Sinks: addCookieServlet
*    GoodSink: URLEncode input
*    BadSink : querystring to addCookie()
* Flow Variant: 04 Control flow: if(PRIVATE_STATIC_FINAL_TRUE) and if(PRIVATE_STATIC_FINAL_FALSE)
*
* */

package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_04 extends AbstractTestCaseServlet
{
    private static final boolean PRIVATE_STATIC_FINAL_TRUE = true;
    private static final boolean PRIVATE_STATIC_FINAL_FALSE = false;

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method signature for bad
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method signature for goodG2B1
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method signature for goodG2B2
    }

    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method signature for goodB2G1
    }

    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method signature for goodB2G2
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method signature for good
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}

// Previous code...

public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
{
    String data;
    if (PRIVATE_STATIC_FINAL_TRUE)
    {
        /* get environment variable ADD */
        /* POTENTIAL FLAW: Read data from an environment variable */
        data = System.getenv("ADD");
    }
    else
    {
        /* INCIDENTAL: CWE 561 Dead Code, the code below will never run
         * but ensure data is inititialized before the Sink to avoid compiler errors */
        data = null;
    }

    if (PRIVATE_STATIC_FINAL_TRUE)
    {
        if (data != null)
        {
            Cookie cookieSink = new Cookie("lang", data);
            /* POTENTIAL FLAW: Input not verified before inclusion in the cookie */
            response.addCookie(cookieSink);
        }
    }
}

// Previous code...

private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable
{
    String data;
    if (PRIVATE_STATIC_FINAL_FALSE)
    {
        /* INCIDENTAL: CWE 561 Dead Code, the code below will never run
         * but ensure data is inititialized before the Sink to avoid compiler errors */
        data = null;
    }
    else
    {
        /* FIX: Use a hardcoded string */
        data = "foo";
    }

    if (PRIVATE_STATIC_FINAL_TRUE)
    {
        if (data != null)
        {
            Cookie cookieSink = new Cookie("lang", data);
            /* POTENTIAL FLAW: Input not verified before inclusion in the cookie */
            response.addCookie(cookieSink);
        }
    }
}

// Previous code...

private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable
{
    String data;
    if (PRIVATE_STATIC_FINAL_TRUE)
    {
        /* FIX: Use a hardcoded string */
        data = "foo";
    }
    else
    {
        /* INCIDENTAL: CWE 561 Dead Code, the code below will never run
         * but ensure data is inititialized before the Sink to avoid compiler errors */
        data = null;
    }

    if (PRIVATE_STATIC_FINAL_TRUE)
    {
        if (data != null)
        {
            Cookie cookieSink = new Cookie("lang", data);
            /* POTENTIAL FLAW: Input not verified before inclusion in the cookie */
            response.addCookie(cookieSink);
        }
    }
}

// Previous code...

private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable
{
    String data;
    if (PRIVATE_STATIC_FINAL_TRUE)
    {
        /* get environment variable ADD */
        /* POTENTIAL FLAW: Read data from an environment variable */
        data = System.getenv("ADD");
    }
    else
    {
        /* INCIDENTAL: CWE 561 Dead Code, the code below will never run
         * but ensure data is inititialized before the Sink to avoid compiler errors */
        data = null;
    }

    if (PRIVATE_STATIC_FINAL_FALSE)
    {
        /* INCIDENTAL: CWE 561 Dead Code, the code below will never run */
        IO.writeLine("Benign, fixed string");
    }
    else
    {
        if (data != null)
        {
            Cookie cookieSink = new Cookie("lang", URLEncoder.encode(data, "UTF-8"));
            /* FIX: use URLEncoder.encode to hex-encode non-alphanumerics */
            response.addCookie(cookieSink);
        }
    }
}

// Previous code...

private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable
{
    String data;
    if (PRIVATE_STATIC_FINAL_TRUE)
    {
        /* get environment variable ADD */
        /* POTENTIAL FLAW: Read data from an environment variable */
        data = System.getenv("ADD");
    }
    else
    {
        /* INCIDENTAL: CWE 561 Dead Code, the code below will never run
         * but ensure data is inititialized before the Sink to avoid compiler errors */
        data = null;
    }

    if (PRIVATE_STATIC_FINAL_TRUE)
    {
        if (data != null)
        {
            Cookie cookieSink = new Cookie("lang", URLEncoder.encode(data, "UTF-8"));
            /* FIX: use URLEncoder.encode to hex-encode non-alphanumerics */
            response.addCookie(cookieSink);
        }
    }
}