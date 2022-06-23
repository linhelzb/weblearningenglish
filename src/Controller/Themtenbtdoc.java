package Controller;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.BaiTapPhanDoc;
import DAO.QuanlybtdocDAO;
import DB.DBConnection;


@WebServlet("/Themtenbtdoc")
public class Themtenbtdoc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Themtenbtdoc() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		if (request.getCharacterEncoding()==null)
		{
			request.setCharacterEncoding("UTF-8");
		}
		
		String ten_bai_tap_doc = request.getParameter("ten_bai_tap_doc");
		
		BaiTapPhanDoc bai_tap_phan_doc = new BaiTapPhanDoc();
		bai_tap_phan_doc.setTen_bai_tap_doc(ten_bai_tap_doc);
		
		Connection conn = DBConnection.CreateConnection();
		try 
		{
			boolean kt = QuanlybtdocDAO.Themtenbtdoc(request, conn, bai_tap_phan_doc);
			
			if (kt)
			{
				int ma_bai_tap_doc = QuanlybtdocDAO.Xuatmabtdoc(request, conn, bai_tap_phan_doc);
				
				QuanlybtdocDAO.Kiemtracauhoibtdoc(request, conn, 0, ma_bai_tap_doc);
				
				
				request.setAttribute("ma_bai_tap_doc", ma_bai_tap_doc);
				
				RequestDispatcher rd = request.getRequestDispatcher("View/admin/Themhinhbtdoc.jsp");
				rd.forward(request,response);
			}
			else
			{
				request.setAttribute("msgquanlydsbtdoc","Thêm không thành công");
				RequestDispatcher rd = request.getRequestDispatcher("Hienthidsbtdoc?pageid=1");
				rd.forward(request,response);
			}
			
			conn.close();
		} 
		catch (SQLException e) 
		{	
			request.setAttribute("msgquanlydsbtdoc",e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("Hienthidsbtdoc?pageid=1");
			rd.forward(request,response);
		}
	}

}
