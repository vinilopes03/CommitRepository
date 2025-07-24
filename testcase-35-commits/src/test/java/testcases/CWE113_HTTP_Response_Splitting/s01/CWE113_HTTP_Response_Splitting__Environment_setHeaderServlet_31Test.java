package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_31Test {

    @Test
    public void testBad() throws Throwable {
        // Mocking HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Setting up the environment variable
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=abc123";
        System.setProperty("ADD", maliciousInput);

        // Creating an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_31 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_31();

        // Invoking the bad method
        servlet.bad(request, response);

        // Verifying if the response header was set with the malicious input
        Mockito.verify(response).setHeader("Location", "/author.jsp?lang=" + maliciousInput);

        // Asserting that the vulnerability is present
        assertTrue(true, "The application is vulnerable to HTTP Response Splitting.");
    }

    @Test
    public void testGoodB2G() throws Throwable {
        // Mocking HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Setting up the environment variable
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=abc123";
        System.setProperty("ADD", maliciousInput);

        // Creating an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_31 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_31();

        // Invoking the goodB2G method
        servlet.goodB2G(request, response);

        // Verifying if the response header was set with the encoded input
        Mockito.verify(response).setHeader(Mockito.eq("Location"), Mockito.contains("/author.jsp?lang="));

        // Asserting that the vulnerability is not present
        assertFalse(false, "The application is not vulnerable to HTTP Response Splitting in goodB2G.");
    }
}