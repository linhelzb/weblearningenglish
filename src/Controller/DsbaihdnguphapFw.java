package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.HuongDanHocNguPhap;
import DAO.BaihdnguphapDAO;

import DB.DBConnection;


@WebServlet("/DsbaihdnguphapFw")
public class DsbaihdnguphapFw extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public DsbaihdnguphapFw() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DBConnection.CreateConnection();
		int pageid = Integer.parseInt(request.getParameter("pageid"));
		int count = 3;
		if (pageid ==1) {
			
		}
		else {
			pageid = pageid - 1;
			pageid = pageid*count + 1;
		}
		
		List<HuongDanHocNguPhap> list = BaihdnguphapDAO.HienThiBaiHDNguPhap(pageid, count, conn);
		int sumrow = BaihdnguphapDAO.DemSoHang(conn);
		int maxrow = 0;
		if ((sumrow/count)%2==0) {
			maxrow = (int) (sumrow/count);
		}
		else {
			maxrow = (int) (sumrow/count) + 1;
		}
		
		request.setAttribute("dshuongdannguphap", list);
		request.setAttribute("numberpage", Integer.parseInt(request.getParameter("pageid")));
		request.setAttribute("max", maxrow);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("View/user/Dsbaihdnguphap.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
