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
import DAO.QuanlybtngheDAO;
import DB.DBConnection;


@WebServlet("/Themhinhbtnghe")
public class Themhinhbtnghe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Themhinhbtnghe() {
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
		
		String ma_bai_tap_nghestr = request.getParameter("ma_bai_tap_nghe");
		
		int ma_bai_tap_nghe = Integer.parseInt(ma_bai_tap_nghestr);
		
		
		
		
		String kiemtra = QuanlybtngheDAO.Themhinhbtnghe(conn, request, response, ma_bai_tap_nghe);
		
		if (kiemtra.equals("Success"))
		{
			RequestDispatcher rd = request.getRequestDispatcher("Hienthidsquanlybtnghe?pageid=1");
			rd.forward(request,response);	
		}
		else 
		{
			request.setAttribute("msgthemhinhbtnghe",kiemtra);
			request.setAttribute("ma_bai_tap_nghe", ma_bai_tap_nghe);
	    	RequestDispatcher rd = request.getRequestDispatcher("View/admin/Themhinhbtnghe.jsp");
			rd.forward(request,response);		 
		}
	}

}
