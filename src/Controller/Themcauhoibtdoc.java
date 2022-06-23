package Controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.QuanlybtdocDAO;

import DB.DBConnection;


@WebServlet("/Themcauhoibtdoc")
public class Themcauhoibtdoc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Themcauhoibtdoc() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		String ma_bai_tap_doc = request.getParameter("ma_bai_tap_doc");
		request.setAttribute("ma_bai_tap_doc",ma_bai_tap_doc);
		
		RequestDispatcher rd = request.getRequestDispatcher("View/admin/Themcauhoibtdoc.jsp");
		rd.forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		Connection conn = DBConnection.CreateConnection();
		
		String ma_bai_tap_docstr = request.getParameter("ma_bai_tap_doc");
		int ma_bai_tap_doc = Integer.parseInt(ma_bai_tap_docstr);
		
		
		String kiemtra = QuanlybtdocDAO.Uploadcauhoibtdoc(conn, request, response, ma_bai_tap_doc);
		
		if (kiemtra.equals("Success"))
		{
			QuanlybtdocDAO.Kiemtracauhoibtdoc(request, conn, 1 , ma_bai_tap_doc); 
			
			RequestDispatcher rd = request.getRequestDispatcher("Hienthidsbtdoc?pageid=1");
			rd.forward(request,response);	
		}
		else 
		{
			request.setAttribute("tbthemcauhoibtdoc",kiemtra);
			request.setAttribute("ma_bai_tap_doc", ma_bai_tap_doc);
	    	RequestDispatcher rd = request.getRequestDispatcher("View/admin/Themcauhoibtdoc.jsp");
			rd.forward(request,response);		 
		}
	}

}
