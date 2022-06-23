package Controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BEAN.ThanhVien;
import DAO.DangnhapDAO;
import DB.DBConnection;


@WebServlet("/DangnhapCtl")
public class DangnhapCtl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public DangnhapCtl() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("View/user/Dangnhap.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DBConnection.CreateConnection();
		
		String ten_thanh_vien = request.getParameter("ten_thanh_vien");
		String mat_khau = request.getParameter("mat_khau");
		ThanhVien thanh_vien = new ThanhVien();
		thanh_vien.setTen_thanh_vien(ten_thanh_vien);
		thanh_vien.setMat_khau(mat_khau);
		
		String name = DangnhapDAO.XuatTenThanhVien(request, conn, thanh_vien);
		
		boolean authentication = DangnhapDAO.XacThucThanhVien(request, conn, thanh_vien);
		
		if (authentication) {
			int authorization = DangnhapDAO.PhanQuyenThanhVien(request, conn, thanh_vien);
			if (authorization==1){		
				HttpSession session = request.getSession(true);				
				session.setAttribute("sessionnguoidung", ten_thanh_vien);
				RequestDispatcher rd = request.getRequestDispatcher("TrangchuFw");
				rd.forward(request, response);
			}
			else if (authorization==2) {
				HttpSession session = request.getSession(true);				
				session.setAttribute("sessionquantrivien", ten_thanh_vien);
				RequestDispatcher rd = request.getRequestDispatcher("QuantriFw");
				rd.forward(request, response);
			}
			
		}
		else {
			request.setAttribute("thongbaodangnhap", "Tài khoản hoặc mật khẩu không chính xác");
			RequestDispatcher rd = request.getRequestDispatcher("View/user/Dangnhap.jsp");
			rd.forward(request, response);
		}
	}

}
