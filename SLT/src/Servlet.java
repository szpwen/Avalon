import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HeaderServlet
 */
public class Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // out.println("HTTP headers sent by your client:<br>");

        Enumeration enums = request.getHeaderNames();
        while (enums.hasMoreElements()) {
            String headerName = (String) enums.nextElement();
            String headerValue = request.getHeader(headerName);
            out.print("<b>" + headerName + "</b>: ");
            out.println(headerValue + "<br/>");
        }
    }

}