package TTT;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(name = "Servlet123")
public class Servlet123 extends HttpServlet {

    private String message;

    public void init() throws ServletException
    {
        //执行不要的初始化
        message = "Hello World";
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        //设置响应内容类型
        response.setContentType("text/html");

        //实际的逻辑
        PrintWriter out = response.getWriter();
        out.println("<h1>" + message + "</h1>");
    }

    }


