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

import BEAN.BaiTapPhanNghe;
import BEAN.CauHoiPhanNghe;


public class QuanlybtngheDAO 
{
			//hien thi danh sach de thi va phan trang
	
			public static List<BaiTapPhanNghe> Hienthidsbtnghe(HttpServletRequest request,int start, int count,Connection conn)
			{
				List<BaiTapPhanNghe> dsbaitapphannghe = new ArrayList<BaiTapPhanNghe>();
				
				String sql = "select * from bai_tap_phan_nghe limit "+(start-1)+", "+count+"";
				try 
				{
					PreparedStatement ptmt = conn.prepareStatement(sql);
					
					ResultSet rs = ptmt.executeQuery();
					
					if (rs.isBeforeFirst())
					{
						while (rs.next())
						{
							BaiTapPhanNghe bai_tap_phan_nghe = new BaiTapPhanNghe();
							
							int ma_bai_tap_nghe = rs.getInt("ma_bai_tap_nghe");
							String ten_bai_tap_nghe = rs.getString("ten_bai_tap_nghe");
							String anh_bai_tap_nghe = rs.getString("anh_bai_tap_nghe");
							int kiem_tra_cau_hoi = rs.getInt("kiem_tra_cau_hoi");
							
							bai_tap_phan_nghe.setMa_bai_tap_nghe(ma_bai_tap_nghe);
							bai_tap_phan_nghe.setTen_bai_tap_nghe(ten_bai_tap_nghe);
							bai_tap_phan_nghe.setAnh_bai_tap_nghe(anh_bai_tap_nghe);
							bai_tap_phan_nghe.setKiem_tra_cau_hoi(kiem_tra_cau_hoi);
							dsbaitapphannghe.add(bai_tap_phan_nghe);
						}
					}
					else 
					{
						request.setAttribute("tbquanlydsbtnghe","KhÃ´ng tá»“n táº¡i bÃ i nghe nÃ o trong danh sÃ¡ch");
					}
					
				} 
				catch (SQLException e) 
				{
					request.setAttribute("tbquanlydsbtnghe",e.getMessage());
				}
						
				return dsbaitapphannghe;
			}
			
			//dem so hang cua 1 bang
			public static int DemSoHang(Connection conn)
			{
				int dem = 0;
				
				
				String sql = "select count(*) from bai_tap_phan_nghe";
				
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
			
			//them ten de bai tap nghe
			public static boolean Themtenbtnghe(HttpServletRequest request, Connection conn, BaiTapPhanNghe bai_tap_phan_nghe)
			{
				PreparedStatement ptmt = null;
				
				String sql = "insert into bai_tap_phan_nghe(ten_bai_tap_nghe) values (?)";
				
				try 
				{
					ptmt = conn.prepareStatement(sql);
					
					String ten_bai_tap_nghe = bai_tap_phan_nghe.getTen_bai_tap_nghe();
					
					
					ptmt.setString(1,ten_bai_tap_nghe);
					
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
			
			//xuat ma de thi
			public static int Xuatmabtnghe(HttpServletRequest request, Connection conn, BaiTapPhanNghe bai_tap_phan_nghe)
			{
				int ma_bai_tap_nghe = 0;
				
				PreparedStatement ptmt = null;
				
				
				String sql = "select ma_bai_tap_nghe from bai_tap_phan_nghe where ten_bai_tap_nghe='"+bai_tap_phan_nghe.getTen_bai_tap_nghe()+"'";
				
				try 
				{
					ptmt = conn.prepareStatement(sql);
					
					
					ResultSet rs = ptmt.executeQuery();
					
					while (rs.next())
					{
						ma_bai_tap_nghe = rs.getInt("ma_bai_tap_nghe");		
					}
					
					ptmt.close();
					rs.close();
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
				
				return ma_bai_tap_nghe;
			}
			
			//update checked cau hoi de thi
			public static void Kiemtracauhoibtnghe(HttpServletRequest request, Connection conn,int checkcauhoi,int ma_bai_tap_nghe)
			{
				PreparedStatement ptmt = null;
				
				String sql = "update bai_tap_phan_nghe set kiem_tra_cau_hoi=? where ma_bai_tap_nghe="+ma_bai_tap_nghe;
				
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
			
			public static String Themhinhbtnghe(Connection conn, HttpServletRequest request,HttpServletResponse response,int ma_bai_tap_nghe) 
					throws ServletException, IOException 
			{
				String test = "";
				ServletContext context = request.getServletContext();
				response.setContentType("text/html; charset=UTF-8");
				
				final String Address = context.getRealPath("Filebtphannghe/");
			
				//final String Address = "F://";
				final int MaxMemorySize = 1024 * 1024 * 3; //3MB
				final int MaxRequestSize = 1024 * 1024 * 50; //50 MB
				
				boolean isMultipart = ServletFileUpload.isMultipartContent(request);
				
				if (!isMultipart)
				{
					test = "Thiáº¿u multipart/form-data trong form";
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
					    					    
					    			test = "File Ä‘Ã£ tá»“n táº¡i. YÃªu cáº§u file khÃ¡c";
					    		}
					    		else
					    		{		    			
					    			item.write(uploadedFile);
					    		
					    			
					    			QuanlybtngheDAO.Updatetenhinhbtnghe(request, conn, fileName, ma_bai_tap_nghe);
					    			
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
			
			//update ten hinh cho de thi
			public static void Updatetenhinhbtnghe(HttpServletRequest request, Connection conn,String anh_bai_tap_nghe,int ma_bai_tap_nghe)
			{
				PreparedStatement ptmt = null;
				
				String sql = "update bai_tap_phan_nghe set anh_bai_tap_nghe=? where ma_bai_tap_nghe="+ma_bai_tap_nghe;
				
				try 
				{
					ptmt = conn.prepareStatement(sql);
					
					
					
					ptmt.setString(1,anh_bai_tap_nghe);
					
					ptmt.executeUpdate();
					
					ptmt.close();
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
			
			
			
			
			public static String Uploadcauhoibtnghe(Connection conn, HttpServletRequest request,HttpServletResponse response,int ma_bai_tap_nghe) 
					throws ServletException, IOException 
			{
				String test = "";
				ServletContext context = request.getServletContext();
				response.setContentType("text/html; charset=UTF-8");
				
				final String Address = context.getRealPath("/Filebtphannghe");
			
			
				final int MaxMemorySize = 1024 * 1024 * 3; //3MB
				final int MaxRequestSize = 1024 * 1024 * 50; //50 MB
				
				boolean isMultipart = ServletFileUpload.isMultipartContent(request);
				
				if (!isMultipart)
				{
					test = "Thiáº¿u multipart/form-data trong form";
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
					    					    
					    			test = "File đã tồn tại. Yêu cầu file khác";
					    		}
					    		else
					    		{		    			
					    			item.write(uploadedFile);
					    			try
					    			{
					    				
					    				
					    				QuanlybtngheDAO.Themcauhoituexcel(request, response, conn, pathFile, ma_bai_tap_nghe);
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
			
			
			
			
			public static void Themcauhoituexcel(HttpServletRequest request,HttpServletResponse response, Connection conn, String address, int ma_bai_tap_nghe) 
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
						String hinh_anh = row.getCell(1).getStringCellValue();				
						String file_nghe_mp3 = row.getCell(2).getStringCellValue();				
						String file_nghe_ogg = row.getCell(3).getStringCellValue();				
						String cau_hoi = row.getCell(4).getStringCellValue();
						String lua_chon_1 = row.getCell(5).getStringCellValue();
						String lua_chon_2 = row.getCell(6).getStringCellValue();
						String lua_chon_3 = row.getCell(7).getStringCellValue();
						String lua_chon_4 = row.getCell(8).getStringCellValue();
						String dap_an = row.getCell(9).getStringCellValue();
						
						CauHoiPhanNghe cauhoiphannghe = new CauHoiPhanNghe();
						
						cauhoiphannghe.setSo_thu_tu(so_thu_tu);
						cauhoiphannghe.setHinh_anh(hinh_anh);
						cauhoiphannghe.setFile_nghe_mp3(file_nghe_mp3);
						cauhoiphannghe.setFile_nghe_ogg(file_nghe_ogg);
						cauhoiphannghe.setCau_hoi(cau_hoi);
						cauhoiphannghe.setLua_chon_1(lua_chon_1);
						cauhoiphannghe.setLua_chon_2(lua_chon_2);
						cauhoiphannghe.setLua_chon_3(lua_chon_3);
						cauhoiphannghe.setLua_chon_4(lua_chon_4);
						cauhoiphannghe.setDap_an(dap_an);
						cauhoiphannghe.setMa_bai_tap_nghe(ma_bai_tap_nghe);
						
					
						QuanlybtngheDAO.Themcauhoivaomysql(request, cauhoiphannghe, conn);
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
			public static void Themcauhoivaomysql(HttpServletRequest request,CauHoiPhanNghe ex, Connection conn)
			{
				String sql = "insert into cau_hoi_phan_nghe(so_thu_tu,hinh_anh,file_nghe_mp3,file_nghe_ogg,cau_hoi,lua_chon_1,lua_chon_2,lua_chon_3,lua_chon_4,dap_an,ma_bai_tap_nghe) values (?,?,?,?,?,?,?,?,?,?,?)";
				try 
				{
					PreparedStatement ptmt = conn.prepareStatement(sql);
					
					
					ptmt.setInt(1,ex.getSo_thu_tu());
					
					ptmt.setString(2,ex.getHinh_anh());
					ptmt.setString(3,ex.getFile_nghe_mp3());
					ptmt.setString(4,ex.getFile_nghe_ogg());
					ptmt.setString(5,ex.getCau_hoi());
					ptmt.setString(6,ex.getLua_chon_1());
					ptmt.setString(7,ex.getLua_chon_2());
					ptmt.setString(8,ex.getLua_chon_3());
					ptmt.setString(9,ex.getLua_chon_4());
					ptmt.setString(10,ex.getDap_an());
					ptmt.setInt(11,ex.getMa_bai_tap_nghe());
					
					ptmt.executeUpdate();
					
					ptmt.close();
					
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
			
			
			
			public static String Themaudiohinhanhbtnghe(Connection conn, HttpServletRequest request,HttpServletResponse response) 
					throws ServletException, IOException 
			{
				String test = "";
				ServletContext context = request.getServletContext();
				response.setContentType("text/html; charset=UTF-8");
				
				final String Address = context.getRealPath("Filebtphannghe/");
			
				//final String Address = "F://";
				final int MaxMemorySize = 1024 * 1024 * 3; //3MB
				final int MaxRequestSize = 1024 * 1024 * 50; //50 MB
				
				boolean isMultipart = ServletFileUpload.isMultipartContent(request);
				
				if (!isMultipart)
				{
					test = "Thiáº¿u multipart/form-data trong form";
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
					    					    
					    			test = "File Ä‘Ã£ tá»“n táº¡i. YÃªu cáº§u file khÃ¡c";
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
			
			public static void XoaCauHoiPhanNghe(Connection conn, int ma_bai_tap_nghe) {
				String sql = "delete from cau_hoi_phan_nghe where ma_bai_tap_nghe ="+ma_bai_tap_nghe;
				try {
					PreparedStatement ptpm = conn.prepareStatement(sql);
					ptpm.executeUpdate();
					ptpm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			public static void XoaBaiTapPhanNghe(Connection conn, int ma_bai_tap_nghe) {
				QuanlybtngheDAO.XoaCauHoiPhanNghe(conn, ma_bai_tap_nghe);
				String sql = "delete from bai_tap_phan_nghe where ma_bai_tap_nghe ="+ma_bai_tap_nghe;
				try {
					PreparedStatement ptpm = conn.prepareStatement(sql);
					
					ptpm.executeUpdate();
					ptpm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
}
