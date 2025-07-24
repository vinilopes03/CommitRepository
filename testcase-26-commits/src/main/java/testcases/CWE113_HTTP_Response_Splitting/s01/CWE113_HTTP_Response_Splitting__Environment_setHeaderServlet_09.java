package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;
import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_09 extends AbstractTestCaseServlet
{
    // 'bad' and 'goodG2B1' methods remain unchanged

    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (IO.STATIC_FINAL_TRUE)
        {
            // Potential flaw: Read data from an environment variable
            data = System.getenv("ADD");
        }
        else
        {
            data = null; // Dead code, will never run
        }

        if (IO.STATIC_FINAL_TRUE)
        {
            if (data != null)
            {
                // Fix: use URLEncoder.encode to hex-encode non-alphanumerics
                data = URLEncoder.encode(data, "UTF-8");
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    // Remaining methods

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}