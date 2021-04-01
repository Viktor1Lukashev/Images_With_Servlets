package com.example.demo2;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "Servlet1", value = "/Servlet1")
@MultipartConfig (
        fileSizeThreshold = 1024*1024*2,
        maxFileSize = 1024*1024*10,
        maxRequestSize = 1024*1024*50
)
public class Servlet1 extends HttpServlet {
    public final static String SAVEDIR="Images";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParts().size()>0)
        {
            this.doUploud(request);
        }
        processRequest(request, response);
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.sendRedirect("index.html");
    }
    private void doUploud(HttpServletRequest request)
    {
        String root=request.getServletContext().getRealPath("");
        String savePath=root+ File.separator+Servlet1.SAVEDIR;
        File dir=new File(savePath);
        if(!dir.exists())
            dir.mkdir();
        String fn="";
        InputStream in=null;
        BufferedImage img=null;
        FileOutputStream fos=null;
        try
        {
            for(Part p : request.getParts())
            {
                fn=p.getSubmittedFileName();
                in=p.getInputStream();
                byte [] b=new byte[in.available()];
                in.read(b);
                fos = new FileOutputStream(savePath+File.separator+fn);
                fos.write(b);
                fos.close();
            }
        } catch (IOException ex)
        {
            Logger.getLogger(Servlet1.class.getName()).
                    log(Level.SEVERE, null, ex);
        } catch (ServletException ex)
        {
            Logger.getLogger(Servlet1.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }
}
