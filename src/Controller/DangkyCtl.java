package Controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.ThanhVien;
import DAO.DangkyDAO;
import DB.DBConnection;


@WebServlet("/DangkyCtl")
public class DangkyCtl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public DangkyCtl() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("View/user/Dangky.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getCharacterEncoding()==null) {
			request.setCharacterEncoding("UTF-8");
		}
		Connection conn = DBConnection.CreateConnection();
		
		String ten_day_du = request.getParameter("ten_day_du");
		String ten_thanh_vien = request.getParameter("ten_thanh_vien");
		String mat_khau = request.getParameter("mat_khau");
		if (DangkyDAO.KiemTraTaiKhoanDangKi(ten_day_du, ten_day_du, mat_khau) ==0) {
			request.setAttribute("thongbaodangki", "Vui lòng nhập đủ các thông tin");
			RequestDispatcher rd = request.getRequestDispatcher("View/user/Dangky.jsp");
			rd.forward(request, response);
		}else { 
			if (DangkyDAO.KiemTraTaiKhoanDangKi(ten_day_du, ten_day_du, mat_khau) ==1) {
				request.setAttribute("thongbaodangki", "Vui lòng nhập mật khẩu từ 8 ký tự trở lên");
				RequestDispatcher rd = request.getRequestDispatcher("View/user/Dangky.jsp");
				rd.forward(request, response);
			} 
			if (DangkyDAO.KiemTraTaiKhoanDangKi(ten_day_du, ten_day_du, mat_khau)==2) {
				ThanhVien thanh_vien = new ThanhVien();
				thanh_vien.setTen_day_du(ten_day_du);
				thanh_vien.setTen_thanh_vien(ten_thanh_vien);
				thanh_vien.setMat_khau(mat_khau);
				
				boolean test = DangkyDAO.ThemTaiKhoan(request, conn, thanh_vien);
				
				if (test) {
					request.setAttribute("thongbaodangki","Dang ky thanh cong");
					RequestDispatcher rd = request.getRequestDispatcher("DangnhapCtl");
					rd.forward(request, response);
				}
				else {
					request.setAttribute("thongbaodangki", "Dang ky khong thanh cong");
					RequestDispatcher rd = request.getRequestDispatcher("DangkyCtl");
					rd.forward(request, response);
					}
				}
			}
	}
}
