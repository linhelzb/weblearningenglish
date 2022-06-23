package Controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.QuanlybtngheDAO;
import DB.DBConnection;

/**
 * Servlet implementation class XoaCauHoiPhanNghe
 */
@WebServlet("/XoaCauHoiPhanNghe")
public class XoaCauHoiPhanNghe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XoaCauHoiPhanNghe() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int ma_bai_tap_nghe = Integer.parseInt(request.getParameter("ma_bai_tap_nghe"));
		Connection con = DBConnection.CreateConnection();
		QuanlybtngheDAO.XoaBaiTapPhanNghe(con, ma_bai_tap_nghe);
		RequestDispatcher rd = request.getRequestDispatcher("Hienthidsquanlybtnghe?pageid=1");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
