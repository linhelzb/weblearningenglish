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


import BEAN.HuongDanHocNguPhap;
import DAO.BaihdnguphapDAO;
import DAO.HuongDanNguPhapDAO;
import DB.DBConnection;


@WebServlet("/Danhsachhdnguphap")
public class Danhsachhdnguphap extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public Danhsachhdnguphap() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Connection conn = DBConnection.CreateConnection();
			List<HuongDanHocNguPhap> huongdanhocnguphap = HuongDanNguPhapDAO.HienThiQLHuongDanNguPhap(request, conn);
			request.setAttribute("dsqlhuongdannguphap", huongdanhocnguphap);
			conn.close();
		} catch (SQLException e) {
			request.setAttribute("tbdsqlhuongdannguphap", e.getMessage());
		}
		RequestDispatcher rd = request.getRequestDispatcher("View/admin/Dshdnguphap.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
