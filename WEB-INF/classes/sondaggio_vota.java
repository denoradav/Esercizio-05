import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class sondaggio_vota extends HttpServlet{
    private String[] nomi = {"Alighieri","Buzzati","Calvino","Deledda","Eco"};
    private int[] voti;

    public void init(){
        voti = new int[5];
        for (int i=0;i<voti.length;i++) voti[i]=0;
    }

    public void doGet(HttpServletRequest request,HttpServletResponse response)
    throws IOException,ServletException{
        Cookie[] cookieArr = request.getCookies();

        for (int i=0;i<cookieArr.length;i++){
            if (cookieArr[i].getName().equals("voto")){
                RequestDispatcher rd=request.getRequestDispatcher("/sondaggio_risultati");  
                rd.forward(request,response);
            }
        }

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><title>Vota il sondaggio</title></head>");
        out.println("<body>");
        out.print("<h1>Vota il tuo sindaco</h1>");

        out.println("<form action=\"sondaggio_vota\" method=\"POST\">");

      

        for (int i=0;i<nomi.length;i++){
            out.println( nomi[i] + "<input type=\"radio\" name=\"voto\" value=\""+ nomi[i] +"\" /><br>");
           
        }

      
        out.println("<input type=\"submit\" value=\"Invia\">");
        out.println("</form>");


        out.println("</body>");
        out.println("</html>");




        out.close();
    }

    public void doPost(HttpServletRequest request,HttpServletResponse response)
    throws IOException,ServletException{
        String voto= request.getParameter("voto");
        String votato;
        Cookie[] cookieArr = request.getCookies();
        int nVoti;

        response.addCookie(new Cookie("voto","votato"));


        for (int i=0;i<nomi.length;i++){
            if (nomi[i].equals(voto)){
                voti[i]++;    
            }      
        }

        ServletContext application = getServletContext();
        application.setAttribute("nomi",nomi);
        application.setAttribute("voti",voti);
        RequestDispatcher rd = application.getRequestDispatcher("/sondaggio_risultati");
        rd.forward(request,response);
        
        /*
        response.addCookie(new Cookie("voto","ok"));
        RequestDispatcher rd=request.getRequestDispatcher("sondaggio_risultati");  
        rd.forward(request,response);
        */
    }
}

