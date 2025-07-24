package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02Test {

    @Test
    public void testBad() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02 servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Set up the environment variable
        String maliciousData = "en-US\r\nSet-Cookie: sessionId=abc123";
        System.setProperty("ADD", maliciousData);

        // Act
        servlet.bad(request, response);

        // Assert
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> {
            // Check if the cookie value contains CRLF characters
            return cookie.getValue().contains("\r") || cookie.getValue().contains("\n");
        }));
    }

    @Test
    public void testGoodG2B1() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02 servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Act
        servlet.goodG2B1(request, response);

        // Assert
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> {
            // Ensure the cookie value does not contain CRLF characters
            return !cookie.getValue().contains("\r") && !cookie.getValue().contains("\n");
        }));
    }

    @Test
    public void testGoodG2B2() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02 servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Act
        servlet.goodG2B2(request, response);

        // Assert
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> {
            // Ensure the cookie value does not contain CRLF characters
            return !cookie.getValue().contains("\r") && !cookie.getValue().contains("\n");
        }));
    }

    @Test
    public void testGoodB2G1() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02 servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Set up the environment variable
        String maliciousData = "en-US\r\nSet-Cookie: sessionId=abc123";
        System.setProperty("ADD", maliciousData);

        // Act
        servlet.goodB2G1(request, response);

        // Assert
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> {
            // Ensure the cookie value does not contain CRLF characters
            return !cookie.getValue().contains("\r") && !cookie.getValue().contains("\n");
        }));
    }

    @Test
    public void testGoodB2G2() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02 servlet = new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Set up the environment variable
        String maliciousData = "en-US\r\nSet-Cookie: sessionId=abc123";
        System.setProperty("ADD", maliciousData);

        // Act
        servlet.goodB2G2(request, response);

        // Assert
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> {
            // Ensure the cookie value does not contain CRLF characters
            return !cookie.getValue().contains("\r") && !cookie.getValue().contains("\n");
        }));
    }
}