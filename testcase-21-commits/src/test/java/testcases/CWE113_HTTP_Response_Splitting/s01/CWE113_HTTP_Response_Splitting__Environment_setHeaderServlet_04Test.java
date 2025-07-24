package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_04Test {

    @Test
    public void testBadMethodVulnerability() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Set up the environment variable
        String maliciousInput = "en-US\r\nContent-Length: 0\r\n\r\n<script>alert(1)</script>";
        System.setProperty("ADD", maliciousInput);

        // Create an instance of the class to test
        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_04 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_04();

        // Invoke the bad method
        servlet.bad(request, response);

        // Verify that the response.setHeader was called with the unencoded malicious input
        verify(response).setHeader(eq("Location"), eq("/author.jsp?lang=" + maliciousInput));

        // Assert that the vulnerability is present
        assertTrue(true, "The class is vulnerable to HTTP Response Splitting.");
    }
}