package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_06Test {

    @Test
    public void testBadMethodVulnerability() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_06 servlet = new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_06();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Set up the environment variable
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=abc123";
        System.setProperty("ADD", maliciousInput);

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify that the response header was set with the malicious input
        verify(response).setHeader(eq("Location"), contains(maliciousInput));
    }
}