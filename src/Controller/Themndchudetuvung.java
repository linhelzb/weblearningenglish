package Controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import DAO.QuanlyhdtuvungDAO;
import DB.DBConnection;


@WebServlet("/Themndchudetuvung")
public class Themndchudetuvung extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Themndchudetuvung() 
    {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		String ma_loai_tu_vung = request.getParameter("ma_loai_tu_vung");
		request.setAttribute("ma_loai_tu_vung",ma_loai_tu_vung);
		
		RequestDispatcher rd = request.getRequestDispatcher("View/admin/Themndchudetuvung.jsp");
		rd.forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		Connection conn = DBConnection.CreateConnection();
		
		String ma_loai_tu_vungstr = request.getParameter("ma_loai_tu_vung");
		int ma_loai_tu_vung = Integer.parseInt(ma_loai_tu_vungstr);
		
		String kiemtra = QuanlyhdtuvungDAO.Uploadndchudetuvung(conn, request, response, ma_loai_tu_vung);
		
		if (kiemtra.equals("Success"))
		{
			QuanlyhdtuvungDAO.Kiemtrandchudetuvung(request, conn,1,ma_loai_tu_vung);
			
			RequestDispatcher rd = request.getRequestDispatcher("Hienthidstuvung?pageid=1");
			rd.forward(request,response);	
		}
		else 
		{
			request.setAttribute("msgthemndchudetuvung",kiemtra);
			request.setAttribute("ma_loai_tu_vung", ma_loai_tu_vung);
	    	RequestDispatcher rd = request.getRequestDispatcher("View/admin/Themndchudetuvung.jsp");
			rd.forward(request,response);		 
		}
	}

}
