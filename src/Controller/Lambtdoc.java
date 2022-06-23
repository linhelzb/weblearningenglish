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

import BEAN.CauHoiPhanDoc;

import DAO.LambtphandocDAO;

import DB.DBConnection;


@WebServlet("/Lambtdoc")
public class Lambtdoc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Lambtdoc() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		try 
		{
			String ma_bai_tap_docstr = request.getParameter("ma_bai_tap_doc");
			int ma_bai_tap_doc = Integer.parseInt(ma_bai_tap_docstr);
			
			
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
			
			List<CauHoiPhanDoc> dscauhoiphandoc = LambtphandocDAO.Hienthicauhoibtdoc(request, pageid, count, conn, ma_bai_tap_doc);
			
			
			int sumrow = LambtphandocDAO.Demcauhoitheoma(conn, ma_bai_tap_doc);
			
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
			request.setAttribute("ma_bai_tap_doc",ma_bai_tap_doc);
			
			request.setAttribute("danhsachcauhoibtdoc",dscauhoiphandoc);
			
			request.setAttribute("numberpage",Integer.parseInt(pageidstr));
			
			conn.close();
			
		} 
		catch (SQLException e) 
		{
			request.setAttribute("tblambtphandoc",e.getMessage());
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("View/user/Lambtphandoc.jsp");
		rd.forward(request,response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		
		String kq = request.getParameter("kq");
		
		Connection conn = DBConnection.CreateConnection();
		
		String ma_bai_tap_docstr = request.getParameter("ma_bai_tap_doc");
		int ma_bai_tap_doc = Integer.parseInt(ma_bai_tap_docstr);
		
		String so_thu_tustr = request.getParameter("so_thu_tu");
		int so_thu_tu = Integer.parseInt(so_thu_tustr);
		
		
		if (kq == "")
		{
			request.setAttribute("tblambtphandoc","Yêu cầu chọn đáp án");
			
		}
		else
		{	
			List<CauHoiPhanDoc> dsdapanbtdoc = LambtphandocDAO.Xuatdapanbtdoc(request, conn, ma_bai_tap_doc, so_thu_tu);
			
			request.setAttribute("dapandungbtdoc",dsdapanbtdoc);
			request.setAttribute("kq",kq);
			
			
			RequestDispatcher rd = request.getRequestDispatcher("View/user/Ketquabtdoc.jsp");
			rd.forward(request,response);
		}
		
		
	}

}
