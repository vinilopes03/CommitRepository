package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;

import java.util.logging.Level;

public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_02 extends AbstractTestCaseServlet {

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        data = ""; // Initialize data
        File file = new File("C:\\data.txt");
        FileInputStream streamFileInput = null;
        InputStreamReader readerInputStream = null;
        BufferedReader readerBuffered = null;
        try {
            streamFileInput = new FileInputStream(file);
            readerInputStream = new InputStreamReader(streamFileInput, "UTF-8");
            readerBuffered = new BufferedReader(readerInputStream);
            data = readerBuffered.readLine(); // POTENTIAL FLAW: Read data from a file
        } catch (IOException exceptIO) {
            IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
        } finally {
            if (readerBuffered != null) {
                readerBuffered.close();
            }
            if (readerInputStream != null) {
                readerInputStream.close();
            }
            if (streamFileInput != null) {
                streamFileInput.close();
            }
        }

        if (data != null) {
            Cookie cookieSink = new Cookie("lang", data);
            response.addCookie(cookieSink); // POTENTIAL FLAW: Input not verified
        }
    }

    // Other methods...

    public static void main(String[] args) throws ClassNotFoundException,
            InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}