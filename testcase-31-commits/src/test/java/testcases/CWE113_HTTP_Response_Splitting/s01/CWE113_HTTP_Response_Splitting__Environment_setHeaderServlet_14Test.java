package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_14Test {

    @Test
    public void testVulnerabilityPresent() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Set the environment variable to a malicious value
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=abc123";
        System.setProperty("ADD", maliciousInput);

        // Set IO.staticFive to 5 to trigger the bad path
        IO.staticFive = 5;

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_14 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_14();

        // Call the bad method
        servlet.bad(request, response);

        // Verify that the response header was set with the malicious input
        Mockito.verify(response).setHeader("Location", "/author.jsp?lang=" + maliciousInput);

        // Assert that the vulnerability is present
        assertTrue(true, "The class is vulnerable to HTTP Response Splitting.");
    }

    @Test
    public void testVulnerabilityNotPresent() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Set the environment variable to a malicious value
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=abc123";
        System.setProperty("ADD", maliciousInput);

        // Set IO.staticFive to 5 to trigger the good path
        IO.staticFive = 5;

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_14 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_14();

        // Call the goodB2G2 method
        servlet.goodB2G2(request, response);

        // Verify that the response header was set with the encoded input
        Mockito.verify(response).setHeader("Location", "/author.jsp?lang=" + java.net.URLEncoder.encode(maliciousInput, "UTF-8"));

        // Assert that the vulnerability is not present
        assertFalse(false, "The class is not vulnerable to HTTP Response Splitting when using goodB2G2.");
    }
}