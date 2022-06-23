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


import BEAN.HuongDanHocNguPhap;
import DAO.HuongDanNguPhapDAO;
import DB.DBConnection;

@WebServlet("/Chentenhdnguphap")
public class Chentenhdnguphap extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Chentenhdnguphap() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getCharacterEncoding()==null) {
			request.setCharacterEncoding("UTF-8");
		}
		String ten_ngu_phap = request.getParameter("ten_ngu_phap");		
		Connection conn = DBConnection.CreateConnection();
		HuongDanHocNguPhap huongdanhocnguphap = new HuongDanHocNguPhap();
		huongdanhocnguphap.setTen_ngu_phap(ten_ngu_phap);
		try {
			boolean kt = HuongDanNguPhapDAO.ThemTenBaiHuongDanNguPhap(request, conn, huongdanhocnguphap);
			
			if (kt) {
				int ma_ngu_phap = HuongDanNguPhapDAO.getIdHuongDanNguPhap(request, conn, huongdanhocnguphap);
				
				request.setAttribute("ma_ngu_phap", ma_ngu_phap);
				
				RequestDispatcher rd = request.getRequestDispatcher("View/admin/Chenhinhanhhdnguphap.jsp");
				rd.forward(request, response);
			}
			else {
				request.setAttribute("tbqldsnguphap", "Thêm không thành công");
				RequestDispatcher rd = request.getRequestDispatcher("DsbaihdnguphapFw");
				rd.forward(request, response);
			}
			conn.close();
		} catch (SQLException e) {
			request.setAttribute("tbqldsnguphap", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("DsbaihdnguphapFw");
			rd.forward(request, response);
		}
		
	}

}
