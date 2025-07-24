package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_05Test {

    @Test
    public void testBadMethodVulnerability() throws Throwable {
        // Mocking HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Creating an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__File_addCookieServlet_05 servlet = new CWE113_HTTP_Response_Splitting__File_addCookieServlet_05();

        // Mocking the file reading process to simulate reading a malicious input
        File file = new File("C:\\data.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("maliciousInput\r\nSet-Cookie: sessionId=12345");
        }

        // Invoking the bad method
        servlet.bad(request, response);

        // Verifying if the response contains a cookie with unencoded data
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> {
            String value = cookie.getValue();
            return value.contains("\r") || value.contains("\n");
        }));
    }

    @Test
    public void testGoodMethodNoVulnerability() throws Throwable {
        // Mocking HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Creating an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__File_addCookieServlet_05 servlet = new CWE113_HTTP_Response_Splitting__File_addCookieServlet_05();

        // Invoking the good methods
        servlet.goodG2B1(request, response);
        servlet.goodG2B2(request, response);
        servlet.goodB2G1(request, response);
        servlet.goodB2G2(request, response);

        // Verifying that no cookies with unencoded data are added
        Mockito.verify(response, Mockito.never()).addCookie(Mockito.argThat(cookie -> {
            String value = cookie.getValue();
            return value.contains("\r") || value.contains("\n");
        }));
    }
}