package com.activity.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PassbytePic
 */
@WebServlet("/PassbytePic")
public class PassbytePic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PassbytePic() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
//		res.setContentType("image/gif");
//		res.getWriter().append("Served at: ").append(req.getContextPath());
		String noFileUpdate=req.getParameter("activityPost");
		ServletOutputStream out=res.getOutputStream();
		String file = req.getPathTranslated();
		System.out.println(file);//測試
		String contentType = getServletContext().getMimeType(file);
	    res.setContentType(contentType); //設定檔案型態
	    
	    FileInputStream in = null;
		  try {
			  in = new FileInputStream(file);
			  byte[] buf = new byte[in.available()]; // buffer
			  in.read(buf);
			  out.write(buf);
		  } catch (FileNotFoundException e) {
			  out.println("File not found");
		  } catch (IOException e) {
			  out.println("Problem sending file: " + e.getMessage());
		  } finally {
			  if (in != null)
				  in.close();
		  }
		
	}
	 
}
