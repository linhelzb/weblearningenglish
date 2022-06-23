package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.Binhluannguphap;


import DAO.BinhluannguphapDAO;

import DB.DBConnection;


@WebServlet("/BinhluannguphapCtl")
public class BinhluannguphapCtl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public BinhluannguphapCtl() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getCharacterEncoding()!= null)
		{
			request.setCharacterEncoding("UTF-8");
		}
		
		try 
		{
			String ma_thanh_vienstr = request.getParameter("ma_thanh_vien");
			String noi_dung_binh_luan = request.getParameter("noi_dung_binh_luan");
			String ma_ngu_phapstr = request.getParameter("ma_ngu_phap");
			
			int ma_ngu_phap = Integer.parseInt(ma_ngu_phapstr);
			int ma_thanh_vien = Integer.parseInt(ma_thanh_vienstr);
			
			Connection conn = DBConnection.CreateConnection();
			
			
			Binhluannguphap bl = new Binhluannguphap();
			
			bl.setNoi_dung_binh_luan(noi_dung_binh_luan);
			bl.setMa_thanh_vien(ma_thanh_vien);
			bl.setMa_ngu_phap(ma_ngu_phap);
			
			BinhluannguphapDAO.ThemBinhLuanNguPhap(request, conn, bl);
			
			List<Binhluannguphap> dsbinhluannguphap = BinhluannguphapDAO.HienThiBinhLuanNguPhap(conn, ma_ngu_phap);
			
			request.setAttribute("dsbinhluannguphap",dsbinhluannguphap);
			
			RequestDispatcher rd = request.getRequestDispatcher("View/user/Chitietbaihdnguphap.jsp");
			rd.forward(request,response);
			conn.close();
			
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
	}

}
