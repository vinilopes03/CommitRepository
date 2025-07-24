package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_11Test {

    @Test
    public void testBadMethodForVulnerability() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Set up the environment variable
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=abc123";
        System.setProperty("ADD", maliciousInput);

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_11 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_11();

        // Invoke the bad method
        servlet.bad(request, response);

        // Verify that the setHeader method was called with a potentially malicious input
        verify(response).setHeader(eq("Location"), contains(maliciousInput));

        // Clean up the environment variable
        System.clearProperty("ADD");

        // Assert that the vulnerability is present
        assertTrue(true, "The method is vulnerable to HTTP Response Splitting.");
    }
}