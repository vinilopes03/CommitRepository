// Previous imports and package definitions...

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01 extends AbstractTestCaseServlet
{
    // bad method unchanged...
    // goodG2B method unchanged...

    private void goodB2G(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;

        // Get environment variable ADD
        // POTENTIAL FLAW: Read data from an environment variable
        data = System.getenv("ADD");

        if (data != null)
        {
            Cookie cookieSink = new Cookie("lang", URLEncoder.encode(data, "UTF-8"));
            // FIX: use URLEncoder.encode to hex-encode non-alphanumerics
            response.addCookie(cookieSink);
        }
    }

    // Other methods unchanged...
}