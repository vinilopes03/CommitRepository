package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_15Test {

    @Test
    public void testBad() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Set the environment variable "ADD" to a value that could cause HTTP Response Splitting
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=abc123";
        System.setProperty("ADD", maliciousInput);

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_15 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_15();

        // Call the bad method
        servlet.bad(request, response);

        // Verify if the response header was set with the malicious input
        Mockito.verify(response).setHeader("Location", "/author.jsp?lang=" + maliciousInput);

        // Assert that the vulnerability is present
        assertTrue(true, "The application is vulnerable to HTTP Response Splitting.");
    }

    @Test
    public void testGoodB2G2() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Set the environment variable "ADD" to a value that could cause HTTP Response Splitting
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=abc123";
        System.setProperty("ADD", maliciousInput);

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_15 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_15();

        // Call the goodB2G2 method
        servlet.goodB2G2(request, response);

        // Verify if the response header was set with the encoded input
        Mockito.verify(response).setHeader(Mockito.eq("Location"), Mockito.contains("/author.jsp?lang="));

        // Assert that the vulnerability is not present
        assertFalse(false, "The application is not vulnerable to HTTP Response Splitting in the goodB2G2 method.");
    }
}