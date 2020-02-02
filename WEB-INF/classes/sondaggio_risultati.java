import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import netscape.javascript.JSException;

public class sondaggio_risultati extends HttpServlet{
    public void doGet(HttpServletRequest request,HttpServletResponse response)
    throws IOException,JSException{
        Cookie[] cookieArr = request.getCookies();
        ServletContext application = getServletContext();
        String[] nomi = (String[])application.getAttribute("nomi");
        int[] voti = (int[])application.getAttribute("voti");
        

        PrintWriter out = response.getWriter();

        response.setContentType("text/html");

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Risultati Sondaggio</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Risultati</h1>");
       
       

        for (int i=0;i<voti.length;i++){
            out.println(nomi[i] + ":" +voti[i]+ "<br>");
        }

        out.println("</body>");
        out.println("</html>");
    }

    public void doPost(HttpServletRequest request,HttpServletResponse response)
    throws IOException,JSException{
        doGet(request, response);
    }
}
