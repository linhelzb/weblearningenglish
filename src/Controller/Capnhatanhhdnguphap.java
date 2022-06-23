package Controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.HuongDanNguPhapDAO;
import DB.DBConnection;


@WebServlet("/Capnhatanhhdnguphap")
public class Capnhatanhhdnguphap extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public Capnhatanhhdnguphap() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DBConnection.CreateConnection();
		int ma_ngu_phap = Integer.parseInt(request.getParameter("ma_ngu_phap"));
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		HuongDanNguPhapDAO.UploadAnhHuongDanNguPhap(conn,request, response,ma_ngu_phap);
	}

}
