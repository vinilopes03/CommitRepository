// ... (previous code)

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01 extends AbstractTestCaseServlet
{
    // ... (previous methods)

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        goodG2B(request, response);
        goodB2G(request, response);
    }

    // ... (main method)
}