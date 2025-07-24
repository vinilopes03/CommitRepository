package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_08Test {

    @Test
    public void testBad() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_08 servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_08();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Set up the environment variable
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=abc123";
        Mockito.when(System.getenv("ADD")).thenReturn(maliciousInput);

        // Act
        servlet.bad(request, response);

        // Assert
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> cookie.getValue().equals(maliciousInput)));
    }

    @Test
    public void testGoodG2B1() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_08 servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_08();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Act
        servlet.goodG2B1(request, response);

        // Assert
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> cookie.getValue().equals("foo")));
    }

    @Test
    public void testGoodG2B2() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_08 servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_08();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Act
        servlet.goodG2B2(request, response);

        // Assert
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> cookie.getValue().equals("foo")));
    }

    @Test
    public void testGoodB2G1() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_08 servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_08();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Set up the environment variable
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=abc123";
        Mockito.when(System.getenv("ADD")).thenReturn(maliciousInput);

        // Act
        servlet.goodB2G1(request, response);

        // Assert
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> cookie.getValue().equals(java.net.URLEncoder.encode(maliciousInput, "UTF-8"))));
    }

    @Test
    public void testGoodB2G2() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_08 servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_08();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Set up the environment variable
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=abc123";
        Mockito.when(System.getenv("ADD")).thenReturn(maliciousInput);

        // Act
        servlet.goodB2G2(request, response);

        // Assert
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> cookie.getValue().equals(java.net.URLEncoder.encode(maliciousInput, "UTF-8"))));
    }
}