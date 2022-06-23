package Controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.HuongDanNguPhapDAO;
import DB.DBConnection;

/**
 * Servlet implementation class XoaBaiHuongDanNguPhap
 */
@WebServlet("/XoaBaiHuongDanNguPhap")
public class XoaBaiHuongDanNguPhap extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XoaBaiHuongDanNguPhap() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int ma_ngu_phap = Integer.parseInt(request.getParameter("ma_ngu_phap"));
		Connection con = DBConnection.CreateConnection();
		HuongDanNguPhapDAO.XoaBaiHuongDanNguPhap(con, ma_ngu_phap);
		RequestDispatcher rd = request.getRequestDispatcher("Danhsachhdnguphap?pageid=1");
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
