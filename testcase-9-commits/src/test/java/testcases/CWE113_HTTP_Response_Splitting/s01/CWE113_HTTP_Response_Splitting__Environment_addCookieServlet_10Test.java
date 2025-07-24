package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_10Test {

    @Test
    public void testBad() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_10 servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_10();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Mock environment variable
        String maliciousInput = "value\r\nSet-Cookie: sessionId=malicious";
        Mockito.mockStatic(System.class);
        when(System.getenv("ADD")).thenReturn(maliciousInput);

        // Act
        servlet.bad(request, response);

        // Assert
        verify(response).addCookie(argThat(cookie -> cookie.getValue().contains("\r\n")));
    }

    @Test
    public void testGoodB2G1() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_10 servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_10();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Mock environment variable
        String maliciousInput = "value\r\nSet-Cookie: sessionId=malicious";
        Mockito.mockStatic(System.class);
        when(System.getenv("ADD")).thenReturn(maliciousInput);

        // Act
        servlet.goodB2G1(request, response);

        // Assert
        verify(response).addCookie(argThat(cookie -> !cookie.getValue().contains("\r\n")));
    }

    @Test
    public void testGoodB2G2() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_10 servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_10();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Mock environment variable
        String maliciousInput = "value\r\nSet-Cookie: sessionId=malicious";
        Mockito.mockStatic(System.class);
        when(System.getenv("ADD")).thenReturn(maliciousInput);

        // Act
        servlet.goodB2G2(request, response);

        // Assert
        verify(response).addCookie(argThat(cookie -> !cookie.getValue().contains("\r\n")));
    }
}