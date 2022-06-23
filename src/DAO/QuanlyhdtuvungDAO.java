package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import BEAN.ChiTietTuVung;

import BEAN.HuongDanHocTuVung;


public class QuanlyhdtuvungDAO 
{
		//hien thi danh sach de thi va phan trang
		public static List<HuongDanHocTuVung> Hienthidstuvung(HttpServletRequest request,int start, int count,Connection conn)
		{
			List<HuongDanHocTuVung> dshuongdanhoctuvung = new ArrayList<HuongDanHocTuVung>();
			
			String sql = "select * from huong_dan_hoc_tu_vung limit "+(start-1)+", "+count+"";
			try 
			{
				PreparedStatement ptmt = conn.prepareStatement(sql);
				
				ResultSet rs = ptmt.executeQuery();
				
				if (rs.isBeforeFirst())
				{
					while (rs.next())
					{
						HuongDanHocTuVung hdtv = new HuongDanHocTuVung();
						
						int ma_loai_tu_vung = rs.getInt("ma_loai_tu_vung");
						String ten_nhom_tu_vung = rs.getString("ten_nhom_tu_vung");
						String hinh_anh = rs.getString("hinh_anh");
						int kiem_tra_noi_dung = rs.getInt("kiem_tra_noi_dung");
						
						hdtv.setMa_loai_tu_vung(ma_loai_tu_vung);
						hdtv.setTen_nhom_tu_vung(ten_nhom_tu_vung);
						hdtv.setHinh_anh(hinh_anh);
						hdtv.setKiem_tra_noi_dung(kiem_tra_noi_dung);
						
						dshuongdanhoctuvung.add(hdtv);
					}
				}
				else 
				{
					request.setAttribute("tbdstuvung","Không có bài hướng dẫn nào trong danh sách");
				}
				
			} 
			catch (SQLException e) 
			{
				request.setAttribute("tbdstuvung",e.getMessage());
			}
					
			return dshuongdanhoctuvung;
		}
		
		//dem so hang cua 1 bang
		public static int DemSoHang(Connection conn)
		{
			int dem = 0;
			
			
			String sql = "select count(*) from huong_dan_hoc_tu_vung";
			
			try 
			{
				PreparedStatement ptmt = conn.prepareStatement(sql);
				
				ResultSet rs = ptmt.executeQuery();
				
				rs.next();
				
				dem = rs.getInt(1);
				
					
			} 
			catch (SQLException e) 
			{
				
				e.printStackTrace();
			}
			
			return dem;
		}
		
				//them ten de chu de tu vung
		public static boolean Themtenchudetuvung(HttpServletRequest request, Connection conn,HuongDanHocTuVung hdtv)
		{
			PreparedStatement ptmt = null;
			
			String sql = "insert into huong_dan_hoc_tu_vung(ten_nhom_tu_vung) values (?)";
			
			try 
			{
				ptmt = conn.prepareStatement(sql);
				
				String ten_nhom_tu_vung = hdtv.getTen_nhom_tu_vung();
				
				
				ptmt.setString(1,ten_nhom_tu_vung);
				
				int kt = ptmt.executeUpdate();
				
				if (kt != 0)
				{
					return true;
				}
				
				ptmt.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			
			return false;	
		}
		
		
		//xuat ma chu de tu vung
		public static int Xuatmachudetuvung(HttpServletRequest request, Connection conn, HuongDanHocTuVung hdtv)
		{
			int ma_loai_tu_vung = 0;
			
			PreparedStatement ptmt = null;
			
			
			String sql = "select ma_loai_tu_vung from huong_dan_hoc_tu_vung where ten_nhom_tu_vung='"+hdtv.getTen_nhom_tu_vung()+"'";
			
			try 
			{
				ptmt = conn.prepareStatement(sql);
				
				
				ResultSet rs = ptmt.executeQuery();
				
				while (rs.next())
				{
					ma_loai_tu_vung = rs.getInt("ma_loai_tu_vung");		
				}
				
				ptmt.close();
				rs.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			
			return ma_loai_tu_vung;
		}
	
		//update checked chu de tu vung
		public static void Kiemtrandchudetuvung(HttpServletRequest request, Connection conn,int kiem_tra_noi_dung,int ma_loai_tu_vung)
		{
			PreparedStatement ptmt = null;
			
			String sql = "update huong_dan_hoc_tu_vung set kiem_tra_noi_dung=? where ma_loai_tu_vung="+ma_loai_tu_vung;
			
			try 
			{
				ptmt = conn.prepareStatement(sql);
					
				
				ptmt.setInt(1,kiem_tra_noi_dung);
				
				ptmt.executeUpdate();
				
				ptmt.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		
		//ham them file anh va update ten chu de tu vung
		
		public static String Themhinhchudetuvung(Connection conn, HttpServletRequest request,HttpServletResponse response,int ma_loai_tu_vung) 
				throws ServletException, IOException 
		{
			String test = "";
			ServletContext context = request.getServletContext();
			response.setContentType("text/html; charset=UTF-8");
			
			final String Address = context.getRealPath("Imageaudiohdtuvung/");
		

			final int MaxMemorySize = 1024 * 1024 * 3; //3MB
			final int MaxRequestSize = 1024 * 1024 * 50; //50 MB
			
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			
			if (!isMultipart)
			{
				test = "Thiếu multipart/form-data trong form";
			}
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			
			
			factory.setSizeThreshold(MaxMemorySize);

			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
			
			
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			
			upload.setSizeMax(MaxRequestSize);
			
			
			
			try 
			{
		
				List<FileItem> items = upload.parseRequest(request);
				
				Iterator<FileItem> iter = items.iterator();
				
				while (iter.hasNext()) 
				{
				    FileItem item = iter.next();

				    if (!item.isFormField()) 
				    {
				    	 String fileName = item.getName();
				    	 
				    	 
				    	 
				    	String pathFile = Address + File.separator + fileName;
				    	 
				    	File uploadedFile = new File(pathFile);
				    	
				    	
				    	boolean kt = uploadedFile.exists();
				    	 
				    	try 
				    	{
				    		
				    		if (kt == true)
				    		{
				    					    
				    			test = "File đã tồn tại";
				    		}
				    		else
				    		{		    			
				    			item.write(uploadedFile);
				    			
				    			QuanlyhdtuvungDAO.Updatetenhinhchudetuvung(request, conn, fileName, ma_loai_tu_vung);
				    			test="Success";
				    		}
							
							
						} 
				    	catch (Exception e) 
				    	{ 		
				    		test = e.getMessage();
						}   	 
				    } 
				    else 
				    {
				    	test = "Thêm file thất bại";
				    }
				}
				
			} 
			catch (FileUploadException e) 
			{
				test = e.getMessage();
			}
			
			return test;
		}
		
		//update ten hinh cho chu de tu vung
		public static void Updatetenhinhchudetuvung(HttpServletRequest request, Connection conn,String hinh_anh,int ma_loai_tu_vung)
		{
			PreparedStatement ptmt = null;
			
			String sql = "update huong_dan_hoc_tu_vung set hinh_anh=? where ma_loai_tu_vung="+ma_loai_tu_vung;
			
			try 
			{
				ptmt = conn.prepareStatement(sql);
				
				
				
				ptmt.setString(1,hinh_anh);
				
				ptmt.executeUpdate();
				
				ptmt.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		//ham them noi dung tu file excel cho chu de tu vung
		
		public static String Uploadndchudetuvung(Connection conn, HttpServletRequest request,HttpServletResponse response,int ma_loai_tu_vung) 
				throws ServletException, IOException 
		{
			String test = "";
			ServletContext context = request.getServletContext();
			response.setContentType("text/html; charset=UTF-8");
			
			final String Address = context.getRealPath("/Filendchudetuvung");
		
		
			final int MaxMemorySize = 1024 * 1024 * 3; //3MB
			final int MaxRequestSize = 1024 * 1024 * 50; //50 MB
			
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			
			if (!isMultipart)
			{
				test = "Thiếu multipart/form-data trong form";
			}
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			
			
			// Set factory constraints
			factory.setSizeThreshold(MaxMemorySize);

			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
			
			
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			
			// Set overall request size constraint
			upload.setSizeMax(MaxRequestSize);
			
			
			
			try 
			{
				// Parse the request
				List<FileItem> items = upload.parseRequest(request);
				
				// Process the uploaded items
				Iterator<FileItem> iter = items.iterator();
				
				while (iter.hasNext()) 
				{
				    FileItem item = iter.next();

				    if (!item.isFormField()) 
				    {
				    	 String fileName = item.getName();
				    	 
				    	 //pathFile: vÃƒÂ¡Ã‚Â»Ã¢â‚¬Â¹ trÃƒÆ’Ã‚Â­ mÃƒÆ’Ã‚Â  chÃƒÆ’Ã‚Âºng ta muÃƒÂ¡Ã‚Â»Ã¢â‚¬Ëœn upload file vÃƒÆ’Ã‚Â o
				    	 //gÃƒÂ¡Ã‚Â»Ã‚Â­i cho server
				    	 
				    	String pathFile = Address + File.separator + fileName;
				    	 
				    	File uploadedFile = new File(pathFile);
				    	
				    	
				    	boolean kt = uploadedFile.exists();
				    	 
				    	try 
				    	{
				    		
				    		if (kt == true)
				    		{
				    					    
				    			test = "File Ä‘Ã£ tá»“n táº¡i";
				    		}
				    		else
				    		{		    			
				    			item.write(uploadedFile);
				    			try
				    			{
				    				
				    				QuanlyhdtuvungDAO.Themndchudetuvungtuexcel(request, response, conn, pathFile, ma_loai_tu_vung);
				    			}
				    			catch(NullPointerException e)
				    			{
				    				test = e.getMessage();
				    			}
				    			
				    			
				    			test="Success";
				    		}
							
							
						} 
				    	catch (Exception e) 
				    	{ 		
				    		test = e.getMessage();
						}   	 
				    } 
				    else 
				    {
				    	test = "ThÃªm file tháº¥t báº¡i";
				    }
				}
				
			} 
			catch (FileUploadException e) 
			{
				test = e.getMessage();
			}
			
			return test;
		}
		
	
		public static void Themndchudetuvungtuexcel(HttpServletRequest request,HttpServletResponse response, Connection conn, String address, int ma_loai_tu_vung) 
				throws ServletException, IOException
		{
			FileInputStream inp;
			try 
			{
				inp = new FileInputStream(address);
				HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
				
				Sheet sheet = wb.getSheetAt(0);
				
				
				
				for (int i=1; i<=sheet.getLastRowNum();i++)
				{
					Row row = sheet.getRow(i);
					
					int so_thu_tu = (int) row.getCell(0).getNumericCellValue();			
					String ten_tu_vung = row.getCell(1).getStringCellValue();						
					String phien_am = row.getCell(2).getStringCellValue();
					String hinh_anh = row.getCell(3).getStringCellValue();
					String audiomp3 = row.getCell(4).getStringCellValue();
					String audiogg = row.getCell(5).getStringCellValue();
					String nghia = row.getCell(6).getStringCellValue();
					
					
					ChiTietTuVung chitiettuvung = new ChiTietTuVung();
					
					chitiettuvung.setSo_thu_tu(so_thu_tu);
					chitiettuvung.setTen_tu_vung(ten_tu_vung);
					chitiettuvung.setPhienam(phien_am);
					chitiettuvung.setHinh_anh(hinh_anh);
					chitiettuvung.setAudiomp3(audiomp3);
					chitiettuvung.setAudiogg(audiogg);
					chitiettuvung.setNghia(nghia);
					chitiettuvung.setMa_loai_tu_vung(ma_loai_tu_vung);
					
					QuanlyhdtuvungDAO.Themndtuvungvaomysql(request, chitiettuvung, conn);
					
				}
				
				wb.close();
				
			} 
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
				
			}
			catch (IOException e) 
			{
				e.printStackTrace();
				
			}
		}
		
		
		//Them noi dung tu vung vao mysql
		public static void Themndtuvungvaomysql(HttpServletRequest request,ChiTietTuVung ex, Connection conn)
		{
			String sql = "insert into chi_tiet_tu_vung(so_thu_tu,ten_tu_vung,phien_am,hinh_anh,audiomp3,audiogg,nghia,ma_loai_tu_vung) values (?,?,?,?,?,?,?,?)";
					
			try 
			{
				PreparedStatement ptmt = conn.prepareStatement(sql);
				
				
				ptmt.setInt(1,ex.getSo_thu_tu());
				ptmt.setString(2,ex.getTen_tu_vung());
				ptmt.setString(3,ex.getPhienam());
				ptmt.setString(4,ex.getHinh_anh());
				ptmt.setString(5,ex.getAudiomp3());
				ptmt.setString(6,ex.getAudiogg());
				ptmt.setString(7,ex.getNghia());			
				ptmt.setInt(8,ex.getMa_loai_tu_vung());
				
				ptmt.executeUpdate();
				
				ptmt.close();
				
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		
		public static String Themaudiohinhanhtuvung(Connection conn, HttpServletRequest request,HttpServletResponse response) 
				throws ServletException, IOException 
		{
			String test = "";
			ServletContext context = request.getServletContext();
			response.setContentType("text/html; charset=UTF-8");
			
			final String Address = context.getRealPath("Imageaudiohdtuvung/");
		
			//final String Address = "F://";
			final int MaxMemorySize = 1024 * 1024 * 3; //3MB
			final int MaxRequestSize = 1024 * 1024 * 50; //50 MB
			
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			
			if (!isMultipart)
			{
				test = "Thiếu multipart/form-data trong form";
			}
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			
			
			// Set factory constraints
			factory.setSizeThreshold(MaxMemorySize);

			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
			
			
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			
			// Set overall request size constraint
			upload.setSizeMax(MaxRequestSize);
			
			
			
			try 
			{
				// Parse the request
				List<FileItem> items = upload.parseRequest(request);
				
				// Process the uploaded items
				Iterator<FileItem> iter = items.iterator();
				
				while (iter.hasNext()) 
				{
				    FileItem item = iter.next();

				    if (!item.isFormField()) 
				    {
				    	 String fileName = item.getName();
				    	 
				    	
				    	 
				    	String pathFile = Address + File.separator + fileName;
				    	 
				    	File uploadedFile = new File(pathFile);
				    	
				    	
				    	boolean kt = uploadedFile.exists();
				    	 
				    	try 
				    	{
				    		
				    		if (kt == true)
				    		{
				    					    
				    			test = "File đã tồn tại. Vui lòng chọn file khác";
				    		}
				    		else
				    		{		    			
				    			item.write(uploadedFile);					    			
				    			test="Success";
				    		}
							
							
						} 
				    	catch (Exception e) 
				    	{ 		
				    		test = e.getMessage();
						}   	 
				    } 
				    else 
				    {
				    	test = "Thêm file không thành công";
						    }
						}
						
					} 
					catch (FileUploadException e) 
					{
						test = e.getMessage();
					}
					
					return test;
				}	

				
}
