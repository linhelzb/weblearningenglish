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

import BEAN.HuongDanHocTuVung;
import DAO.QuanlyhdtuvungDAO;
import DB.DBConnection;


@WebServlet("/Themchudetuvung")
public class Themchudetuvung extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Themchudetuvung() {
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
		if (request.getCharacterEncoding()==null)
		{
			request.setCharacterEncoding("UTF-8");
		}
		
		String ten_nhom_tu_vung = request.getParameter("ten_nhom_tu_vung");
		
		HuongDanHocTuVung hdtv = new HuongDanHocTuVung();
		hdtv.setTen_nhom_tu_vung(ten_nhom_tu_vung);
		
		Connection conn = DBConnection.CreateConnection();
		try 
		{
			boolean kt = QuanlyhdtuvungDAO.Themtenchudetuvung(request, conn, hdtv);
			
			if (kt)
			{
				int ma_loai_tu_vung = QuanlyhdtuvungDAO.Xuatmachudetuvung(request, conn, hdtv);
				
				QuanlyhdtuvungDAO.Kiemtrandchudetuvung(request, conn,0, ma_loai_tu_vung);
				
				request.setAttribute("ma_loai_tu_vung", ma_loai_tu_vung);
				
				
				RequestDispatcher rd = request.getRequestDispatcher("View/admin/Themhinhchudetuvung.jsp");
				rd.forward(request,response);
			}
			else
			{
				request.setAttribute("msgdstuvung","Thêm chủ đề từ vựng không thành công");
				
				RequestDispatcher rd = request.getRequestDispatcher("Hienthidstuvung?pageid=1");
				rd.forward(request,response);
			}
			
			conn.close();
		} 
		catch (SQLException e) 
		{	
			request.setAttribute("tbdstuvung",e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("Hienthidstuvung?pageid=1");
			rd.forward(request,response);
		}
	}

}
