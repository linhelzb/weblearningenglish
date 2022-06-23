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

import BEAN.BaiTapPhanNghe;

import DAO.QuanlybtngheDAO;
import DB.DBConnection;

@WebServlet("/Themtenbtnghe")
public class Themtenbtnghe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Themtenbtnghe() {
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
		
		String ten_bai_tap_nghe = request.getParameter("ten_bai_tap_nghe");
		
		BaiTapPhanNghe bai_tap_phan_nghe = new BaiTapPhanNghe();
		bai_tap_phan_nghe.setTen_bai_tap_nghe(ten_bai_tap_nghe);
		
		
		Connection conn = DBConnection.CreateConnection();
		try 
		{
			boolean kt = QuanlybtngheDAO.Themtenbtnghe(request, conn, bai_tap_phan_nghe);
			
			if (kt)
			{
				
				
				int ma_bai_tap_nghe = QuanlybtngheDAO.Xuatmabtnghe(request, conn, bai_tap_phan_nghe);
				
				QuanlybtngheDAO.Kiemtracauhoibtnghe(request, conn,0, ma_bai_tap_nghe);
				
				
				request.setAttribute("ma_bai_tap_nghe", ma_bai_tap_nghe);
				
				RequestDispatcher rd = request.getRequestDispatcher("View/admin/Themhinhbtnghe.jsp");
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
