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

import javax.servlet.RequestDispatcher;
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

import BEAN.BaiTapPhanDoc;
import BEAN.CauHoiPhanDoc;



@SuppressWarnings("unused")
public class QuanlybtdocDAO 
{
		//hien thi danh sach de thi va phan trang
	
	public static List<BaiTapPhanDoc> Hienthidsbtdoc(HttpServletRequest request,int start, int count,Connection conn)
	{
		List<BaiTapPhanDoc> dsbaitapphandoc = new ArrayList<BaiTapPhanDoc>();
		
		String sql = "select * from bai_tap_phan_doc limit "+(start-1)+", "+count+"";
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			if (rs.isBeforeFirst())
			{
				while (rs.next())
				{
					BaiTapPhanDoc bai_tap_phan_doc = new BaiTapPhanDoc();
					
					int ma_bai_tap_doc = rs.getInt("ma_bai_tap_doc");
					String ten_bai_tap_doc = rs.getString("ten_bai_tap_doc");
					String hinh_anh_bai_tap_doc = rs.getString("hinh_anh_bai_tap_doc");
					int kiem_tra_cau_hoi = rs.getInt("kiem_tra_cau_hoi");
					
					bai_tap_phan_doc.setMa_bai_tap_doc(ma_bai_tap_doc);
					bai_tap_phan_doc.setTen_bai_tap_doc(ten_bai_tap_doc);
					bai_tap_phan_doc.setHinh_anh_bai_tap_doc(hinh_anh_bai_tap_doc);
					bai_tap_phan_doc.setKiem_tra_cau_hoi(kiem_tra_cau_hoi);
					
					dsbaitapphandoc.add(bai_tap_phan_doc);
				}
			}
			else 
			{
				request.setAttribute("tbdsbtdoc","Kh�ng c� file n�o trong danh s�ch");
			}
			
		} 
		catch (SQLException e) 
		{
			request.setAttribute("tbdsbtdoc",e.getMessage());
		}
				
		return dsbaitapphandoc;
	}
		
		//dem so hang cua 1 bang
	public static int DemSoHang(Connection conn)
	{
		int dem = 0;
		
		
		String sql = "select count(*) from bai_tap_phan_doc";
		
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
		
				//them ten bai tap doc
	public static boolean Themtenbtdoc(HttpServletRequest request, Connection conn, BaiTapPhanDoc bai_tap_phan_doc)
	{
		PreparedStatement ptmt = null;
		
		String sql = "insert into bai_tap_phan_doc(ten_bai_tap_doc) values (?)";
		
		try 
		{
			ptmt = conn.prepareStatement(sql);
			
			String ten_bai_tap_doc = bai_tap_phan_doc.getTen_bai_tap_doc();
			
			
			ptmt.setString(1,ten_bai_tap_doc);
			
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
	
				//xuat ma bai tap doc
		public static int Xuatmabtdoc(HttpServletRequest request, Connection conn, BaiTapPhanDoc bai_tap_phan_doc)
		{
			int mabaitapdoc = 0;
			
			PreparedStatement ptmt = null;
			
			
			String sql = "select ma_bai_tap_doc from bai_tap_phan_doc where ten_bai_tap_doc='"+bai_tap_phan_doc.getTen_bai_tap_doc()+"'";
			
			try 
			{
				ptmt = conn.prepareStatement(sql);
				
				
				ResultSet rs = ptmt.executeQuery();
				
				while (rs.next())
				{
					mabaitapdoc = rs.getInt("ma_bai_tap_doc");		
				}
				
				ptmt.close();
				rs.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			
			return mabaitapdoc;
		}
				
				//update checked cau hoi bai tap doc
		public static void Kiemtracauhoibtdoc(HttpServletRequest request, Connection conn,int checkcauhoi,int ma_bai_tap_doc)
		{
			PreparedStatement ptmt = null;
			
			String sql = "update bai_tap_phan_doc set kiem_tra_cau_hoi=? where ma_bai_tap_doc="+ma_bai_tap_doc;
			
			try 
			{
				ptmt = conn.prepareStatement(sql);
					
				
				ptmt.setInt(1,checkcauhoi);
				
				ptmt.executeUpdate();
				
				ptmt.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
				
				//ham them file anh va update ten de thi
				
		public static String Themhinhbtdoc(Connection conn, HttpServletRequest request,HttpServletResponse response,int ma_bai_tap_doc) 
				throws ServletException, IOException 
		{
			String test = "";
			ServletContext context = request.getServletContext();
			response.setContentType("text/html; charset=UTF-8");
			
			final String Address = context.getRealPath("Imageandfilebtdoc/");
		
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
				    					    
				    			test = "File Ä‘Ã£ tá»“n táº¡i";
				    		}
				    		else
				    		{		    			
				    			item.write(uploadedFile);
				    			//QuanlydethiDAO.Updatetenhinhdethi(request, conn, fileName, examinationid);
				    			QuanlybtdocDAO.Updatetenhinhbtdoc(request, conn, fileName, ma_bai_tap_doc);
				    			
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
				    	test = "Them file thất bại";
				    }
				}
				
			} 
			catch (FileUploadException e) 
			{
				test = e.getMessage();
			}
			
			return test;
		}
				
				//update ten hinh cho de thi
	public static void Updatetenhinhbtdoc(HttpServletRequest request, Connection conn,String hinh_anh_bai_tap_doc,int ma_bai_tap_doc)
	{
		PreparedStatement ptmt = null;
		
		String sql = "update bai_tap_phan_doc set hinh_anh_bai_tap_doc=? where ma_bai_tap_doc="+ma_bai_tap_doc;
		
		try 
		{
			ptmt = conn.prepareStatement(sql);
			
			
			
			ptmt.setString(1,hinh_anh_bai_tap_doc);
			
			ptmt.executeUpdate();
			
			ptmt.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	//ham theo file excel vao thu muc fildethi trong project
	
	public static String Uploadcauhoibtdoc(Connection conn, HttpServletRequest request,HttpServletResponse response,int ma_bai_tap_doc) 
			throws ServletException, IOException 
	{
		String test = "";
		ServletContext context = request.getServletContext();
		response.setContentType("text/html; charset=UTF-8");
		
		final String Address = context.getRealPath("/Imageandfilebtdoc");
	
	
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
			    					    
			    			test = "File đã tồn tại";
			    		}
			    		else
			    		{		    			
			    			item.write(uploadedFile);
			    			try
			    			{
			    				
			    				
			    				QuanlybtdocDAO.Themcauhoituexcel(request, response, conn, pathFile, ma_bai_tap_doc);
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
	
	
	
	//them cau hoi de thi tu file excel
	public static void Themcauhoituexcel(HttpServletRequest request,HttpServletResponse response, Connection conn, String address, int ma_bai_tap_doc) 
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
				String noi_dung_phan_doc = row.getCell(1).getStringCellValue();						
				String cau_hoi = row.getCell(2).getStringCellValue();
				String lua_chon_1 = row.getCell(3).getStringCellValue();
				String lua_chon_2 = row.getCell(4).getStringCellValue();
				String lua_chon_3 = row.getCell(5).getStringCellValue();
				String lua_chon_4 = row.getCell(6).getStringCellValue();
				String dap_an = row.getCell(7).getStringCellValue();
				
				CauHoiPhanDoc cauhoiphandoc = new CauHoiPhanDoc();
				
				cauhoiphandoc.setSo_thu_tu(so_thu_tu);
				cauhoiphandoc.setNoi_dung_phan_doc(noi_dung_phan_doc);
				cauhoiphandoc.setCau_hoi(cau_hoi);
				cauhoiphandoc.setLua_chon_1(lua_chon_1);
				cauhoiphandoc.setLua_chon_2(lua_chon_2);
				cauhoiphandoc.setLua_chon_3(lua_chon_3);
				cauhoiphandoc.setLua_chon_4(lua_chon_4);
				cauhoiphandoc.setDap_an(dap_an);
				cauhoiphandoc.setMa_bai_tap_doc(ma_bai_tap_doc);
				
			
				QuanlybtdocDAO.Themcauhoivaomysql(request, cauhoiphandoc, conn);
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
	
	
	
	//Them cau hoi vao mysql
	public static void Themcauhoivaomysql(HttpServletRequest request,CauHoiPhanDoc ex, Connection conn)
	{
		String sql = "insert into toeicmyclass.cau_hoi_phan_doc(so_thu_tu,noi_dung_phan_doc,cau_hoi, lua_chon_1,lua_chon_2,lua_chon_3,lua_chon_4,dap_an,ma_bai_tap_doc) values (?,?,?,?,?,?,?,?,?)";
					try 
					{
						PreparedStatement ptmt = conn.prepareStatement(sql);
						
						
						ptmt.setInt(1,ex.getSo_thu_tu());
						
						ptmt.setString(2,ex.getNoi_dung_phan_doc());
						ptmt.setString(3,ex.getCau_hoi());
						ptmt.setString(4,ex.getLua_chon_1());
						ptmt.setString(5,ex.getLua_chon_2());
						ptmt.setString(6,ex.getLua_chon_3());
						ptmt.setString(7,ex.getLua_chon_4());
						ptmt.setString(8,ex.getDap_an());
						ptmt.setInt(9,ex.getMa_bai_tap_doc());
						
						ptmt.executeUpdate();
						
						ptmt.close();
						
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
				}
		
}
