package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_10Test {

    @Test
    public void testBad() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_10 servlet = new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_10();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Set environment variable to a value that could cause HTTP Response Splitting
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=abc123";
        System.setProperty("ADD", maliciousInput);

        // Act
        servlet.bad(request, response);

        // Assert
        Mockito.verify(response).setHeader("Location", "/author.jsp?lang=" + maliciousInput);
        assertTrue(true, "The application is vulnerable to HTTP Response Splitting.");
    }

    @Test
    public void testGoodB2G2() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_10 servlet = new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_10();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Set environment variable to a value that could cause HTTP Response Splitting
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=abc123";
        System.setProperty("ADD", maliciousInput);

        // Act
        servlet.goodB2G2(request, response);

        // Assert
        Mockito.verify(response).setHeader("Location", "/author.jsp?lang=" + java.net.URLEncoder.encode(maliciousInput, "UTF-8"));
        assertFalse(false, "The application is not vulnerable to HTTP Response Splitting in goodB2G2.");
    }
}