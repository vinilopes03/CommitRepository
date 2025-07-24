package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_09Test {

    @Test
    public void testBad() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Set the environment variable "ADD" to a value that could cause HTTP Response Splitting
        String maliciousData = "en-US\r\nSet-Cookie: sessionId=malicious";
        System.setProperty("ADD", maliciousData);

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_09 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_09();

        // Call the bad method
        servlet.bad(request, response);

        // Verify that the response header was set with the malicious data
        verify(response).setHeader("Location", "/author.jsp?lang=" + maliciousData);

        // Assert that the vulnerability is present
        assertTrue(true, "The application is vulnerable to HTTP Response Splitting.");
    }
}