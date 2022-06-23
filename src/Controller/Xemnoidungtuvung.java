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

import BEAN.ChiTietTuVung;

import DAO.HdhoctuvungDAO;
import DB.DBConnection;


@WebServlet("/Xemnoidungtuvung")
public class Xemnoidungtuvung extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Xemnoidungtuvung() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		String ma_loai_tu_vungstr = request.getParameter("ma_loai_tu_vung");
		
		int ma_loai_tu_vung = Integer.parseInt(ma_loai_tu_vungstr);
		
		Connection conn = DBConnection.CreateConnection();
		
		
		List<ChiTietTuVung> dsnoidungtuvung = HdhoctuvungDAO.Hienthinoidungtuvung(request, conn,ma_loai_tu_vung);
		
		
		request.setAttribute("noidungtuvung",dsnoidungtuvung);
		
		RequestDispatcher rd = request.getRequestDispatcher("View/user/Noidunghdtuvung.jsp");
		rd.forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
