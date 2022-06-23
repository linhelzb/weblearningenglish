package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/NoidunghdnguphapFw")
public class NoidunghdnguphapFw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public NoidunghdnguphapFw() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ma_ngu_phap = request.getParameter("ma_ngu_phap");
		request.setAttribute("ma_ngu_phap", ma_ngu_phap);
		RequestDispatcher rd = request.getRequestDispatcher("View/admin/Chennoidunghdnguphap.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
