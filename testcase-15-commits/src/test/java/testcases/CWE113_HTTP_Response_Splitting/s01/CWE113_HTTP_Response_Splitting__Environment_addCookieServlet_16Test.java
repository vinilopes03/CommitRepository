package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_16Test {

    @Test
    public void testBadMethodVulnerability() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_16 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_16();

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Simulate an environment variable that could lead to HTTP Response Splitting
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=abc123";
        Mockito.when(System.getenv("ADD")).thenReturn(maliciousInput);

        // Act
        servlet.bad(request, response);

        // Assert
        // Verify if a cookie with the malicious input was added
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> 
            cookie.getName().equals("lang") && cookie.getValue().equals(maliciousInput)
        ));
    }

    @Test
    public void testGoodB2GMethodNoVulnerability() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_16 servlet =
                new CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_16();

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Simulate an environment variable that could lead to HTTP Response Splitting
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=abc123";
        Mockito.when(System.getenv("ADD")).thenReturn(maliciousInput);

        // Act
        servlet.goodB2G(request, response);

        // Assert
        // Verify if the cookie value is URL encoded, thus preventing HTTP Response Splitting
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> 
            cookie.getName().equals("lang") && !cookie.getValue().equals(maliciousInput)
        ));
    }
}