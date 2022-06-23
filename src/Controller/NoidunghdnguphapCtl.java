package Controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.HuongDanHocNguPhap;
import DAO.HuongDanNguPhapDAO;
import DB.DBConnection;

@WebServlet("/NoidunghdnguphapCtl")
public class NoidunghdnguphapCtl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public NoidunghdnguphapCtl() {
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
		
		Connection conn = DBConnection.CreateConnection();
		String noi_dung_ngu_phap = request.getParameter("noi_dung_ngu_phap");
		int ma_ngu_phap = Integer.parseInt(request.getParameter("ma_ngu_phap"));
		HuongDanHocNguPhap huongdanhocnguphap = new HuongDanHocNguPhap();
		huongdanhocnguphap.setNoi_dung_ngu_phap(noi_dung_ngu_phap);
		boolean kt = HuongDanNguPhapDAO.ThemNoiDungHDNguPhap(request, conn, huongdanhocnguphap,ma_ngu_phap);
		if (kt) {
			RequestDispatcher rd = request.getRequestDispatcher("Danhsachhdnguphap");
			rd.forward(request, response);
		}else {
			request.setAttribute("tbnoidunghdnguphap", "Thêm nội dung không thành công");
			request.setAttribute("ma_ngu_phap", ma_ngu_phap);
			RequestDispatcher rd = request.getRequestDispatcher("View/admin/Chennoidunghdnguphap.jsp");
			rd.forward(request, response);
		}
	}

}
