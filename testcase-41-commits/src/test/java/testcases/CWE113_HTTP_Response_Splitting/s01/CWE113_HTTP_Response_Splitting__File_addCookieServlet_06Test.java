package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_06Test {

    @Test
    public void testBad() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__File_addCookieServlet_06 servlet = new CWE113_HTTP_Response_Splitting__File_addCookieServlet_06();

        // Create a temporary file to simulate reading from a file
        File tempFile = File.createTempFile("data", ".txt");
        tempFile.deleteOnExit();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("value%0d%0aSet-Cookie:sessionId=malicious");
        }

        // Redirect the file reading to the temporary file
        FileInputStream originalStream = new FileInputStream(tempFile);
        InputStreamReader readerInputStream = new InputStreamReader(originalStream, "UTF-8");
        BufferedReader readerBuffered = new BufferedReader(readerInputStream);

        // Mock the behavior of reading from the file
        Mockito.when(readerBuffered.readLine()).thenReturn("value%0d%0aSet-Cookie:sessionId=malicious");

        // Call the bad method
        servlet.bad(request, response);

        // Verify that a cookie was added with the untrusted data
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> {
            String value = cookie.getValue();
            return value.contains("%0d") || value.contains("%0a");
        }));
    }

    @Test
    public void testGoodB2G2() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Create an instance of the class to be tested
        CWE113_HTTP_Response_Splitting__File_addCookieServlet_06 servlet = new CWE113_HTTP_Response_Splitting__File_addCookieServlet_06();

        // Create a temporary file to simulate reading from a file
        File tempFile = File.createTempFile("data", ".txt");
        tempFile.deleteOnExit();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("value%0d%0aSet-Cookie:sessionId=malicious");
        }

        // Redirect the file reading to the temporary file
        FileInputStream originalStream = new FileInputStream(tempFile);
        InputStreamReader readerInputStream = new InputStreamReader(originalStream, "UTF-8");
        BufferedReader readerBuffered = new BufferedReader(readerInputStream);

        // Mock the behavior of reading from the file
        Mockito.when(readerBuffered.readLine()).thenReturn("value%0d%0aSet-Cookie:sessionId=malicious");

        // Call the goodB2G2 method
        servlet.goodB2G2(request, response);

        // Verify that a cookie was added with the encoded data
        Mockito.verify(response).addCookie(Mockito.argThat(cookie -> {
            String value = cookie.getValue();
            return !value.contains("%0d") && !value.contains("%0a");
        }));
    }
}