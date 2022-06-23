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


@WebServlet("/ChitietbaihdnguphapFw")
public class ChitietbaihdnguphapFw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ChitietbaihdnguphapFw() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DBConnection.CreateConnection();
		String ma_ngu_phapstr = request.getParameter("ma_ngu_phap");
		int ma_ngu_phap = Integer.parseInt(ma_ngu_phapstr);
		List<HuongDanHocNguPhap> list = BaihdnguphapDAO.HienThiNDHDNguPhap(conn,ma_ngu_phap);
		request.setAttribute("ma_ngu_phap", ma_ngu_phap);
		request.setAttribute("dshdnpct", list);
		request.setAttribute("kitutrongdatabase1", "\n");
		request.setAttribute("kitutronghtml1", "<br>");
		request.setAttribute("kitutrongdatabase2", "**");
		request.setAttribute("kitutronghtml2", "<b>");
		request.setAttribute("kitutrongdatabase3", "**");
		request.setAttribute("kitutronghtml3", "</b>");
		
		RequestDispatcher rd = request.getRequestDispatcher("View/user/Chitietbaihdnguphap.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
