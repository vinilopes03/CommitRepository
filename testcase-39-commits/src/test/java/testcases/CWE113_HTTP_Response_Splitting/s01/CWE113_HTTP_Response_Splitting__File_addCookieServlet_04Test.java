package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_04Test {

    @Test
    public void testBad() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(outputStream);
        Mockito.when(response.getWriter()).thenReturn(writer);

        // Create a temporary file with malicious content
        File tempFile = File.createTempFile("data", ".txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {
            bw.write("malicious%0D%0ASet-Cookie:sessionId=malicious");
        }

        // Set the file path to the temporary file
        CWE113_HTTP_Response_Splitting__File_addCookieServlet_04 servlet =
                new CWE113_HTTP_Response_Splitting__File_addCookieServlet_04() {
                    @Override
                    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
                        String data;
                        data = ""; /* Initialize data */
                        {
                            File file = tempFile;
                            FileInputStream streamFileInput = null;
                            InputStreamReader readerInputStream = null;
                            BufferedReader readerBuffered = null;
                            try {
                                /* read string from file into data */
                                streamFileInput = new FileInputStream(file);
                                readerInputStream = new InputStreamReader(streamFileInput, "UTF-8");
                                readerBuffered = new BufferedReader(readerInputStream);
                                /* POTENTIAL FLAW: Read data from a file */
                                data = readerBuffered.readLine();
                            } catch (IOException exceptIO) {
                                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
                            } finally {
                                /* Close stream reading objects */
                                try {
                                    if (readerBuffered != null) {
                                        readerBuffered.close();
                                    }
                                } catch (IOException exceptIO) {
                                    IO.logger.log(Level.WARNING, "Error closing BufferedReader", exceptIO);
                                }

                                try {
                                    if (readerInputStream != null) {
                                        readerInputStream.close();
                                    }
                                } catch (IOException exceptIO) {
                                    IO.logger.log(Level.WARNING, "Error closing InputStreamReader", exceptIO);
                                }

                                try {
                                    if (streamFileInput != null) {
                                        streamFileInput.close();
                                    }
                                } catch (IOException exceptIO) {
                                    IO.logger.log(Level.WARNING, "Error closing FileInputStream", exceptIO);
                                }
                            }
                        }

                        if (data != null) {
                            Cookie cookieSink = new Cookie("lang", data);
                            /* POTENTIAL FLAW: Input not verified before inclusion in the cookie */
                            response.addCookie(cookieSink);
                        }
                    }
                };

        // Invoke the bad method
        servlet.bad(request, response);

        // Verify if the response contains the malicious header
        writer.flush();
        String responseOutput = outputStream.toString();
        assertTrue(responseOutput.contains("Set-Cookie:sessionId=malicious"), "Vulnerability present: HTTP Response Splitting");
    }

    @Test
    public void testGood() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(outputStream);
        Mockito.when(response.getWriter()).thenReturn(writer);

        // Create a temporary file with malicious content
        File tempFile = File.createTempFile("data", ".txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {
            bw.write("malicious%0D%0ASet-Cookie:sessionId=malicious");
        }

        // Set the file path to the temporary file
        CWE113_HTTP_Response_Splitting__File_addCookieServlet_04 servlet =
                new CWE113_HTTP_Response_Splitting__File_addCookieServlet_04() {
                    @Override
                    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {
                        String data;
                        data = ""; /* Initialize data */
                        {
                            File file = tempFile;
                            FileInputStream streamFileInput = null;
                            InputStreamReader readerInputStream = null;
                            BufferedReader readerBuffered = null;
                            try {
                                /* read string from file into data */
                                streamFileInput = new FileInputStream(file);
                                readerInputStream = new InputStreamReader(streamFileInput, "UTF-8");
                                readerBuffered = new BufferedReader(readerInputStream);
                                /* POTENTIAL FLAW: Read data from a file */
                                data = readerBuffered.readLine();
                            } catch (IOException exceptIO) {
                                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
                            } finally {
                                /* Close stream reading objects */
                                try {
                                    if (readerBuffered != null) {
                                        readerBuffered.close();
                                    }
                                } catch (IOException exceptIO) {
                                    IO.logger.log(Level.WARNING, "Error closing BufferedReader", exceptIO);
                                }

                                try {
                                    if (readerInputStream != null) {
                                        readerInputStream.close();
                                    }
                                } catch (IOException exceptIO) {
                                    IO.logger.log(Level.WARNING, "Error closing InputStreamReader", exceptIO);
                                }

                                try {
                                    if (streamFileInput != null) {
                                        streamFileInput.close();
                                    }
                                } catch (IOException exceptIO) {
                                    IO.logger.log(Level.WARNING, "Error closing FileInputStream", exceptIO);
                                }
                            }
                        }

                        if (data != null) {
                            Cookie cookieSink = new Cookie("lang", URLEncoder.encode(data, "UTF-8"));
                            /* FIX: use URLEncoder.encode to hex-encode non-alphanumerics */
                            response.addCookie(cookieSink);
                        }
                    }
                };

        // Invoke the good method
        servlet.good(request, response);

        // Verify if the response does not contain the malicious header
        writer.flush();
        String responseOutput = outputStream.toString();
        assertFalse(responseOutput.contains("Set-Cookie:sessionId=malicious"), "No vulnerability: HTTP Response Splitting mitigated");
    }
}