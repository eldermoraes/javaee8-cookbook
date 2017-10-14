package com.eldermoraes.ch04.serverpush;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.PushBuilder;

/**
 *
 * @author eldermoraes
 */
@WebServlet(name = "ServerPushServlet", urlPatterns = {"/ServerPushServlet"})
public class ServerPushServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doRequest(request, response);
    }

    private void doRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String usePush = request.getParameter("usePush");
        
        if ("true".equalsIgnoreCase(usePush)){
            PushBuilder pb = request.newPushBuilder();
            if (pb != null) {
                for(int row=0; row < 5; row++){
                    for(int col=0; col < 8; col++){
                        pb.path("image/keyboard_buttons/keyboard_buttons-" + row + "-" + col + ".jpeg")
                          .addHeader("content-type", "image/jpeg")
                          .push();
                    }
                }
            }
        }

        try (PrintWriter writer = response.getWriter()) {
            StringBuilder html = new StringBuilder();
            html.append("<html>");
            html.append("<center>");
            
            html.append("<table cellspacing='0' cellpadding='0' border='0'>");

            for(int row=0; row < 5; row++){
                html.append("  <tr>");
                for(int col=0; col < 8; col++){
                    html.append("    <td>");
                    html.append("<img src='image/keyboard_buttons/keyboard_buttons-" + row + "-" + col + ".jpeg' style='width:100px; height:106.25px;'>");                    
                    html.append("    </td>");            
                }
                html.append("  </tr>");            
            }
            
            html.append("</table>");
            
            html.append("<br>");
            
            if ("true".equalsIgnoreCase(usePush)){
                html.append("<h2>Image pushed by ServerPush</h2>");
            } else{
                html.append("<h2>Image loaded using HTTP/1.0</h2>");
            }
            
            html.append("</center>");
            html.append("</html>");
            writer.write(html.toString());
        }
    }

}
