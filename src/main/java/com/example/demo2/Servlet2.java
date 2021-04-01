package com.example.demo2;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@WebServlet(name = "Servlet2", value = "/Servlet2")
public class Servlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet Servlet2</title>");
        out.println("</head>");
        out.println("<body>");
        String btn1 = request.getParameter("showImages");
        String select = request.getParameter("lImages");
        if ((btn1 != null && btn1.equals("View Page")) || select != null) {
            String root = request.getServletContext().getRealPath("");
            String savePath = root + File.separator + Servlet1.SAVEDIR;
            File dir = new File(savePath);
            File f = null;
            out.println("<h2>Select image to view</h2>");
            out.println("<form method='POST' action='Servlet2' >");
            out.println("<select name='lImages'>");
            for (String fname : dir.list()) {
                f = new File(savePath + File.separator + fname);
                if (!f.isFile())
                    continue;
                out.println("<option>" + fname + "</option>");
            }
            out.println("</select>");
            out.println("<input type='submit' value='Show image'/>");
            out.println("</form>");
            if (select != null) {
                Path path = Paths.get(savePath + File.separator + select);
                byte[] data = Files.readAllBytes(path);
                StringBuilder sb = new StringBuilder();
                sb.append("data:image/png;base64,");
                sb.append(new String(Base64.getEncoder().encode(data)));

                out.println("<img src='" + sb.toString() + "'/>");
            }
        }
        out.println("<h4>The image is presented by Servlet2 at " + request.getContextPath() + "</h4>");
        out.println("</body>");
        out.println("</html>");

        out.flush();
        out.close();
    }
}


