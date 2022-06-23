package DAO;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import BEAN.HuongDanHocNguPhap;

public class HuongDanNguPhapDAO {
	
	public static List<HuongDanHocNguPhap> HienThiQLHuongDanNguPhap(HttpServletRequest request,Connection conn){
		List<HuongDanHocNguPhap> dshuongdanhocnguphap = new ArrayList<>();
		
		String sql = "select * from huong_dan_hoc_ngu_phap";
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			if (rs.isBeforeFirst()) {
				while(rs.next()) {
					HuongDanHocNguPhap gg = new HuongDanHocNguPhap();
					
					int ma_ngu_phap  = rs.getInt("ma_ngu_phap");
					String ten_ngu_phap  = rs.getString("ten_ngu_phap");
					String hinh_anh_ngu_phap  = rs.getString("hinh_anh_ngu_phap");
					String noi_dung_ngu_phap  = rs.getString("noi_dung_ngu_phap");
					
					gg.setMa_ngu_phap(ma_ngu_phap);
					gg.setTen_ngu_phap(ten_ngu_phap);
					gg.setHinh_anh_ngu_phap(hinh_anh_ngu_phap);
					gg.setNoi_dung_ngu_phap(noi_dung_ngu_phap);
					
					dshuongdanhocnguphap.add(gg);
					
				}
			}
			else {
				request.setAttribute("tbdsqlhdnguphap", "Không có bài hướng dẫn ngữ pháp nào");

			}
			
		} catch (SQLException e) {
			request.setAttribute("tbdsqlhdnguphap", e.getMessage());
		}
		
		
		return dshuongdanhocnguphap;
	}
	
	// them ten bai huong dan vao csdl
	public static boolean ThemTenBaiHuongDanNguPhap(HttpServletRequest request,Connection conn, HuongDanHocNguPhap ggl) {
		PreparedStatement ptmt = null;
		String sql = "insert into huong_dan_hoc_ngu_phap(ten_ngu_phap) value (?)";
		
		try {
			ptmt = conn.prepareStatement(sql);
			
			String ten_ngu_phap = ggl.getTen_ngu_phap();
			ptmt.setString(1, ten_ngu_phap);
			
			int kt = ptmt.executeUpdate();
			if (kt != 0) {
				return true;
			}
			ptmt.close();
			
		} catch (SQLException e) {
			request.setAttribute("tbdsqlhdnguphap", e.getMessage());
		}
		return false;
		
	}
	
	
	
	public static void UploadAnhHuongDanNguPhap(Connection conn,HttpServletRequest request,HttpServletResponse response,int ma_ngu_phap) throws ServletException, IOException {
		
		ServletContext context = request.getServletContext();
		final  String Address = context.getRealPath("/Imageupload/");
		final int yourMaxMemorySize = 1024 * 1024 * 3 ;
		final int yourMaxRequestSize = 1024* 1024 * 50;
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		if (!isMultipart) {
			request.setAttribute("tbhinhanhhdnguphap", "Not have enctype: multipart/form-data");
			request.setAttribute("ma_ngu_phap", ma_ngu_phap);
			RequestDispatcher rd = request.getRequestDispatcher("HinhanhhdnguphapFw");
			rd.forward(request, response);
		}
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		factory.setSizeThreshold(yourMaxMemorySize);
		factory.setRepository(new java.io.File(System.getProperty("java.io.tmpdir")));
		
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		// Set overall request size constraint
		upload.setSizeMax(yourMaxRequestSize);
		
		try {
			List<FileItem> items = upload.parseRequest(new ServletRequestContext(request));
			// Process the uploaded items
			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {
			    FileItem item = iter.next();

			    if (!item.isFormField()) {
			    	String fileName = item.getName();
			    	
			    	
			    	String pathFile = Address + File.separator + fileName;
			    	
			    	File uploadedFile = new File(pathFile);
			    	boolean kt = uploadedFile.exists();
			    	try {
			    		if (kt==true) {
			    			request.setAttribute("tbhinhanhhdnguphap", "File exists. Require : test file again");
			    			request.setAttribute("ma_ngu_phap", ma_ngu_phap);
			    			RequestDispatcher rd = request.getRequestDispatcher("HinhanhhdnguphapFw");
			    			rd.forward(request, response);
			    			
			    		}
			    		else {
			    			item.write(uploadedFile);
			    			HuongDanNguPhapDAO.ThemHinhAnhNguPhap(request, conn,fileName , ma_ngu_phap);
							request.setAttribute("tbhinhanhhdnguphap", "Upload File Success");
							RequestDispatcher rd = request.getRequestDispatcher("Danhsachhdnguphap");
							rd.forward(request, response);
		
			    		}
						
					} catch (Exception e) {
						request.setAttribute("tbhinhanhhdnguphap", e.getMessage());
						request.setAttribute("ma_ngu_phap", ma_ngu_phap);
						RequestDispatcher rd = request.getRequestDispatcher("HinhanhhdnguphapFw");
						rd.forward(request, response);
					}
			    } else {
			    	request.setAttribute("tbhinhanhhdnguphap", "Upload File Fail");
			    	request.setAttribute("ma_ngu_phap", ma_ngu_phap);
			    	RequestDispatcher rd = request.getRequestDispatcher("HinhanhhdnguphapFw");
					rd.forward(request, response);
			    }
			}
		} catch (FileUploadException e) {
				request.setAttribute("tbhinhanhhdnguphap", e.getMessage());
				request.setAttribute("ma_ngu_phap", ma_ngu_phap);
				RequestDispatcher rd = request.getRequestDispatcher("HinhanhhdnguphapFw");
				rd.forward(request, response);
			
			
		}

		
	}
	
	public static int getIdHuongDanNguPhap(HttpServletRequest request,Connection conn, HuongDanHocNguPhap huong_dan_hoc_ngu_phap) {
		int ma_ngu_phap = 0;
		
		PreparedStatement ptmt = null;
		
		String sql = "select ma_ngu_phap from huong_dan_hoc_ngu_phap where ten_ngu_phap='"+huong_dan_hoc_ngu_phap.getTen_ngu_phap() +"'";
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			
			while(rs.next()) {
				ma_ngu_phap = rs.getInt("ma_ngu_phap");
				
			}
			
			ptmt.close();
			rs.close();
			
		} catch (SQLException e) {
			request.setAttribute("tbhinhanhhdnguphap", e.getMessage());
			
		}
		
		return ma_ngu_phap;
	}
	
	// them ten hinh dua theo id
	
	public static void ThemHinhAnhNguPhap(HttpServletRequest request,Connection conn, String hinh_anh_ngu_phap, int ma_ngu_phap) {
		PreparedStatement ptmt = null;
		String sql = "update huong_dan_hoc_ngu_phap set hinh_anh_ngu_phap=? where ma_ngu_phap="+ma_ngu_phap;
		
		try {
			ptmt = conn.prepareStatement(sql);
			
			ptmt.setString(1, hinh_anh_ngu_phap);			
			ptmt.executeUpdate();			
			ptmt.close();
			
		} catch (SQLException e) {
			request.setAttribute("tbdsqlhdnguphap", e.getMessage());
		}
		
		
	}
	
	// them noi dung vao bai huong dan
	public static boolean ThemNoiDungHDNguPhap(HttpServletRequest request,Connection conn, HuongDanHocNguPhap ggl,int ma_ngu_phap) {
		PreparedStatement ptmt = null;
		String sql = "update huong_dan_hoc_ngu_phap set noi_dung_ngu_phap=? where ma_ngu_phap="+ma_ngu_phap;
		
		try {
			ptmt = conn.prepareStatement(sql);
			
			String noi_dung_ngu_phap = ggl.getNoi_dung_ngu_phap();
			ptmt.setString(1, noi_dung_ngu_phap);
			
			int kt = ptmt.executeUpdate();
			if (kt != 0) {
				return true;
			}
			ptmt.close();
			
		} catch (SQLException e) {
			request.setAttribute("tbnoidunghdnguphap", e.getMessage());
		}
		return false;
		
	}
	
	public static void XoaBinhLuanNguPhap(Connection conn, int ma_ngu_phap) {
		String sql = "delete from binh_luan_ngu_phap where ma_ngu_phap = "+ma_ngu_phap;
		try {
			PreparedStatement ptpm = conn.prepareStatement(sql);
			
			ptpm.executeUpdate();
			ptpm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void XoaBaiHuongDanNguPhap(Connection conn, int ma_ngu_phap) {
		HuongDanNguPhapDAO.XoaBinhLuanNguPhap(conn, ma_ngu_phap);
		String sql = "delete from huong_dan_hoc_ngu_phap where ma_ngu_phap ="+ma_ngu_phap;
		try {
			PreparedStatement ptpm = conn.prepareStatement(sql);
			
			ptpm.executeUpdate();
			ptpm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
