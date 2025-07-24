package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_09 extends AbstractTestCaseServlet
{
    // 'bad' method remains unchanged

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (IO.STATIC_FINAL_FALSE)
        {
            data = null; // Dead code, will never run
        }
        else
        {
            // Fix: Use a hardcoded string
            data = "foo";
        }

        if (IO.STATIC_FINAL_TRUE)
        {
            if (data != null)
            {
                // Potential flaw: Input not verified before inclusion in header
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    // Other methods remain unchanged

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}