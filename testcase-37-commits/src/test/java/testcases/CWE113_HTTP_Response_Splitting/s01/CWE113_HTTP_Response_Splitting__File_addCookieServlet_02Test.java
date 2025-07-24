package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_02Test {

    @Test
    public void testBad() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__File_addCookieServlet_02 servlet = new CWE113_HTTP_Response_Splitting__File_addCookieServlet_02();

        // Mock the behavior of the response to capture cookies added
        Mockito.doAnswer(invocation -> {
            Cookie cookie = invocation.getArgument(0);
            // Check if the cookie value is not URL encoded, indicating a vulnerability
            assertTrue(cookie.getValue().contains("\r\n") || cookie.getValue().contains("\n"), "Vulnerability present: HTTP Response Splitting");
            return null;
        }).when(response).addCookie(Mockito.any(Cookie.class));

        // Simulate reading from a file with malicious input
        File file = new File("C:\\data.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("malicious\r\nheader");
        }

        // Call the bad method
        servlet.bad(request, response);

        // Clean up the file
        file.delete();
    }

    @Test
    public void testGoodB2G2() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__File_addCookieServlet_02 servlet = new CWE113_HTTP_Response_Splitting__File_addCookieServlet_02();

        // Mock the behavior of the response to capture cookies added
        Mockito.doAnswer(invocation -> {
            Cookie cookie = invocation.getArgument(0);
            // Check if the cookie value is URL encoded, indicating no vulnerability
            assertFalse(cookie.getValue().contains("\r\n") || cookie.getValue().contains("\n"), "No vulnerability: Input is URL encoded");
            return null;
        }).when(response).addCookie(Mockito.any(Cookie.class));

        // Simulate reading from a file with malicious input
        File file = new File("C:\\data.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("malicious\r\nheader");
        }

        // Call the goodB2G2 method
        servlet.goodB2G2(request, response);

        // Clean up the file
        file.delete();
    }
}