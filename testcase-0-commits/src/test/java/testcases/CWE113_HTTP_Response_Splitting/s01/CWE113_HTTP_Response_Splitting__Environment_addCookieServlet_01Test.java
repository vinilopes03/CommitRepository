package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01Test {

    @Test
    public void testBadMethodVulnerability() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01 servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_01();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Mock environment variable
        String maliciousInput = "value\r\nSet-Cookie: sessionId=malicious";
        Mockito.mockStatic(System.class);
        when(System.getenv("ADD")).thenReturn(maliciousInput);

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify that a cookie was added with the malicious input
        verify(response).addCookie(argThat(cookie -> cookie.getValue().equals(maliciousInput)));
    }
}