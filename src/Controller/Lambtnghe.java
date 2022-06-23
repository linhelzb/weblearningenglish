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

import BEAN.CauHoiPhanNghe;

import DAO.LambtngheDAO;

import DB.DBConnection;


@WebServlet("/Lambtnghe")
public class Lambtnghe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Lambtnghe() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		try 
		{
			String ma_bai_tap_nghestr = request.getParameter("ma_bai_tap_nghe");
			int ma_bai_tap_nghe = Integer.parseInt(ma_bai_tap_nghestr);
			
			
			String pageidstr = request.getParameter("pageid");
			int pageid = Integer.parseInt(pageidstr);
			
			
			int count = 1;
			
			
			if (pageid == 1)
			{
				
			}
			else 
			{
				pageid = pageid -1;
				pageid = pageid * count +1;
			}
			
			
			Connection conn = DBConnection.CreateConnection();
			
			List<CauHoiPhanNghe> dsbaitapnghe = LambtngheDAO.Hienthicauhoibtnghe(request, pageid, count, conn, ma_bai_tap_nghe);
			
			
			int sumrow = LambtngheDAO.Demcauhoitheoma(conn, ma_bai_tap_nghe);
			
			int maxpageid= 0;
			
			if ((sumrow/count)%2==0)
			{
				maxpageid = (sumrow/count);
			}
			else
			{
				maxpageid = (sumrow/count)+1;
			}
			
			request.setAttribute("maxpageid",maxpageid);
			request.setAttribute("ma_bai_tap_nghe",ma_bai_tap_nghe);
			
			request.setAttribute("danhsachcauhoibtnghe",dsbaitapnghe);
			
			request.setAttribute("numberpage",Integer.parseInt(pageidstr));
			
			conn.close();
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("View/user/Lambtphannghe.jsp");
		rd.forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		String kq = request.getParameter("kq");
		
		Connection conn = DBConnection.CreateConnection();
		
		String ma_bai_tap_nghestr = request.getParameter("ma_bai_tap_nghe");
		int ma_bai_tap_nghe = Integer.parseInt(ma_bai_tap_nghestr);
		
		String so_thu_tustr = request.getParameter("so_thu_tu");
		int so_thu_tu = Integer.parseInt(so_thu_tustr);
		
		
		if (kq == "")
		{
			request.setAttribute("tblambtphannghe","Yêu cầu chọn lại đáp án");
			
		}
		else
		{	
			List<CauHoiPhanNghe> dsdapanbtnghe = LambtngheDAO.Xuatdapanbtnghe(request, conn, ma_bai_tap_nghe, so_thu_tu);
			
			request.setAttribute("dapandungbtnghe",dsdapanbtnghe);
			request.setAttribute("kq",kq);
			
			
			RequestDispatcher rd = request.getRequestDispatcher("View/user/Ketquabtnghe.jsp");
			rd.forward(request,response);
		}
		
	}

}
