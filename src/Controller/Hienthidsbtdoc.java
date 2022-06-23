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

import BEAN.BaiTapPhanDoc;

import DAO.QuanlybtdocDAO;

import DB.DBConnection;




@WebServlet("/Hienthidsbtdoc")
public class Hienthidsbtdoc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Hienthidsbtdoc() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		try 
		{
			String pageidstr = request.getParameter("pageid");
			
			
			int pageid = Integer.parseInt(pageidstr);
			int count = 3;
			
			
			if (pageid == 1)
			{
				
			}
			else 
			{
				pageid = pageid -1;
				pageid = pageid * count +1;
			}
			
			
			Connection conn = DBConnection.CreateConnection();
			
			List<BaiTapPhanDoc> dsbaitapdoc = QuanlybtdocDAO.Hienthidsbtdoc(request, pageid, count, conn);
			
			
			int sumrow = QuanlybtdocDAO.DemSoHang(conn);
			
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
			
			request.setAttribute("danhsachqlbtdoc",dsbaitapdoc);
			
			request.setAttribute("numberpage",Integer.parseInt(pageidstr));
			
			conn.close();
			
		} 
		catch (SQLException e) 
		{
			request.setAttribute("tbquanlydsbtdoc",e.getMessage());
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("View/admin/Quanlylambtdoc.jsp");
		rd.forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
