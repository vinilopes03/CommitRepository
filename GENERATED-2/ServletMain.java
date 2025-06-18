

package testcases.CWE835_Infinite_Loop;

import javax.servlet.http.*;

public class ServletMain extends HttpServlet {

	private static final long serialVersionUID = 1L; /* needed since Servlets are serializable */
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
