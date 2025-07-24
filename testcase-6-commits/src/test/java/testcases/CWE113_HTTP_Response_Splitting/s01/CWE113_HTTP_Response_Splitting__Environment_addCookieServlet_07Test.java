package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_07Test {

    @Test
    public void testBad() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_07 servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_07();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Mock environment variable
        String maliciousInput = "value\r\nSet-Cookie: sessionId=malicious";
        Mockito.when(System.getenv("ADD")).thenReturn(maliciousInput);

        // Act
        servlet.bad(request, response);

        // Assert
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> {
            // Check if the cookie value contains a newline, indicating a potential HTTP Response Splitting
            return cookie.getValue().contains("\r\n");
        }));
    }

    @Test
    public void testGoodB2G2() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_07 servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_07();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Mock environment variable
        String maliciousInput = "value\r\nSet-Cookie: sessionId=malicious";
        Mockito.when(System.getenv("ADD")).thenReturn(maliciousInput);

        // Act
        servlet.goodB2G2(request, response);

        // Assert
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> {
            // Check if the cookie value is properly encoded, preventing HTTP Response Splitting
            return !cookie.getValue().contains("\r\n");
        }));
    }
}