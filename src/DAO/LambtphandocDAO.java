package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import BEAN.BaiTapPhanDoc;
import BEAN.CauHoiPhanDoc;


public class LambtphandocDAO 
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
						request.setAttribute("tbdsbtdoc","Không có bài nào trong danh sách");
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
			
			//hien thi danh sach de thi va phan trang
			
			public static List<CauHoiPhanDoc> Hienthicauhoibtdoc(HttpServletRequest request,int start, int count,Connection conn,int ma_bai_tap_doc)
			{
				List<CauHoiPhanDoc> dscauhoiphandoc = new ArrayList<CauHoiPhanDoc>();
				
				String sql = "select * from cau_hoi_phan_doc where ma_bai_tap_doc= "+ma_bai_tap_doc+" limit "+(start-1)+", "+count+"";
				try 
				{
					PreparedStatement ptmt = conn.prepareStatement(sql);
					
					ResultSet rs = ptmt.executeQuery();
					
					
						while (rs.next())
						{
							CauHoiPhanDoc cau_hoi_phan_doc = new CauHoiPhanDoc();
							
							int so_thu_tu = rs.getInt("so_thu_tu");
							String cau_hoi = rs.getString("cau_hoi");
							String lua_chon_1 = rs.getString("lua_chon_1");
							String lua_chon_2 = rs.getString("lua_chon_2");
							String lua_chon_3 = rs.getString("lua_chon_3");
							String lua_chon_4 = rs.getString("lua_chon_4");
						
							
							cau_hoi_phan_doc.setSo_thu_tu(so_thu_tu);
							cau_hoi_phan_doc.setCau_hoi(cau_hoi);
							cau_hoi_phan_doc.setLua_chon_1(lua_chon_1);
							cau_hoi_phan_doc.setLua_chon_2(lua_chon_2);
							cau_hoi_phan_doc.setLua_chon_3(lua_chon_3);
							cau_hoi_phan_doc.setLua_chon_4(lua_chon_4);
							
							dscauhoiphandoc.add(cau_hoi_phan_doc);
						}
					
				} 
				catch (SQLException e) 
				{
					request.setAttribute("tblambtphandoc",e.getMessage());
				}
						
				return dscauhoiphandoc;
			}
			
			//dem so hang cua 1 bang
			public static int Demcauhoitheoma(Connection conn,int ma_bai_tap_doc)
			{
				int dem = 0;
				
				
				String sql = "select count(*) from cau_hoi_phan_doc where ma_bai_tap_doc="+ma_bai_tap_doc;
				
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
			
			//xuat dap an dung theo cau hoi bt doc
			
			public static List<CauHoiPhanDoc> Xuatdapanbtdoc(HttpServletRequest request,Connection conn,int ma_bai_tap_doc,int so_thu_tu)
			{
				List<CauHoiPhanDoc> dscauhoiphandoc = new ArrayList<CauHoiPhanDoc>();
				
				String sql = "select * from cau_hoi_phan_doc where ma_bai_tap_doc= "+ma_bai_tap_doc+" and so_thu_tu="+so_thu_tu;
				try 
				{
					PreparedStatement ptmt = conn.prepareStatement(sql);
					
					ResultSet rs = ptmt.executeQuery();
					
					
						while (rs.next())
						{
							CauHoiPhanDoc cau_hoi_phan_doc = new CauHoiPhanDoc();
							
							int so_thu_tu1 = rs.getInt("so_thu_tu");
							String cau_hoi = rs.getString("cau_hoi");
							String lua_chon_1 = rs.getString("lua_chon_1");
							String lua_chon_2 = rs.getString("lua_chon_2");
							String lua_chon_3 = rs.getString("lua_chon_3");
							String lua_chon_4 = rs.getString("lua_chon_4");
							String dap_an = rs.getString("dap_an");
							
							cau_hoi_phan_doc.setSo_thu_tu(so_thu_tu1);
							cau_hoi_phan_doc.setCau_hoi(cau_hoi);
							cau_hoi_phan_doc.setLua_chon_1(lua_chon_1);
							cau_hoi_phan_doc.setLua_chon_2(lua_chon_2);
							cau_hoi_phan_doc.setLua_chon_3(lua_chon_3);
							cau_hoi_phan_doc.setLua_chon_4(lua_chon_4);
							cau_hoi_phan_doc.setDap_an(dap_an);
							
							dscauhoiphandoc.add(cau_hoi_phan_doc);
						}
					
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
						
				return dscauhoiphandoc;
			}
			
}
