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

@WebServlet("/Themhinhbtdoc")
public class Themhinhbtdoc extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	
    public Themhinhbtdoc() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		Connection conn = DBConnection.CreateConnection();
		
		String ma_bai_tap_docstr = request.getParameter("ma_bai_tap_doc");
		int ma_bai_tap_doc = Integer.parseInt(ma_bai_tap_docstr);
		
		//String kiemtra = QuanlydethiDAO.Themhinhdethi(conn, request, response, examinationid);
		
		String kiemtra = QuanlybtdocDAO.Themhinhbtdoc(conn, request, response, ma_bai_tap_doc);
		
		if (kiemtra.equals("Success"))
		{
			RequestDispatcher rd = request.getRequestDispatcher("Hienthidsbtdoc?pageid=1");
			rd.forward(request,response);	
		}
		else 
		{
			request.setAttribute("tbthemhinhbtdoc",kiemtra);
			request.setAttribute("ma_bai_tap_doc", ma_bai_tap_doc);
	    	RequestDispatcher rd = request.getRequestDispatcher("View/admin/Themhinhbtdoc.jsp");
			rd.forward(request,response);		 
		}
	}

}
