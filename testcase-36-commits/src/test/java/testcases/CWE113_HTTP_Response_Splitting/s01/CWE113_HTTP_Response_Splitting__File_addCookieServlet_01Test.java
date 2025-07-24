package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_01Test {

    @Test
    public void testBad() throws Throwable {
        // Mocking HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Creating a ByteArrayOutputStream to capture the response output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(outputStream);
        Mockito.when(response.getWriter()).thenReturn(writer);

        // Mocking the file reading to return a malicious input
        String maliciousInput = "value%0d%0aSet-Cookie:sessionId=malicious";
        File file = new File("C:\\data.txt");
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(maliciousInput);
        }

        // Instantiate the class and call the bad method
        CWE113_HTTP_Response_Splitting__File_addCookieServlet_01 servlet = new CWE113_HTTP_Response_Splitting__File_addCookieServlet_01();
        servlet.bad(request, response);

        // Flush the writer to ensure all data is written to the output stream
        writer.flush();

        // Check if the response contains the malicious input, indicating a vulnerability
        String responseContent = outputStream.toString();
        boolean isVulnerable = responseContent.contains("Set-Cookie:sessionId=malicious");

        // Assert that the vulnerability is present
        assertTrue(isVulnerable);

        // Clean up the file
        file.delete();
    }

    @Test
    public void testGoodB2G() throws Throwable {
        // Mocking HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Creating a ByteArrayOutputStream to capture the response output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(outputStream);
        Mockito.when(response.getWriter()).thenReturn(writer);

        // Mocking the file reading to return a malicious input
        String maliciousInput = "value%0d%0aSet-Cookie:sessionId=malicious";
        File file = new File("C:\\data.txt");
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(maliciousInput);
        }

        // Instantiate the class and call the goodB2G method
        CWE113_HTTP_Response_Splitting__File_addCookieServlet_01 servlet = new CWE113_HTTP_Response_Splitting__File_addCookieServlet_01();
        servlet.goodB2G(request, response);

        // Flush the writer to ensure all data is written to the output stream
        writer.flush();

        // Check if the response contains the malicious input, indicating a vulnerability
        String responseContent = outputStream.toString();
        boolean isVulnerable = responseContent.contains("Set-Cookie:sessionId=malicious");

        // Assert that the vulnerability is not present
        assertFalse(isVulnerable);

        // Clean up the file
        file.delete();
    }
}