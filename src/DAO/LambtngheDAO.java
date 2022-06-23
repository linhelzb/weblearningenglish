package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import BEAN.BaiTapPhanNghe;
import BEAN.CauHoiPhanNghe;


public class LambtngheDAO 
{
	
	
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
				request.setAttribute("tbquanlydsbtnghe","Không có bài nghe nào");
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
	
	//hien thi danh sach de thi va phan trang
	
	public static List<CauHoiPhanNghe> Hienthicauhoibtnghe(HttpServletRequest request,int start, int count,Connection conn,int ma_bai_tap_nghe)
	{
		List<CauHoiPhanNghe> dscauhoiphannghe = new ArrayList<CauHoiPhanNghe>();
		
		String sql = "select * from cau_hoi_phan_nghe where ma_bai_tap_nghe= "+ma_bai_tap_nghe+" limit "+(start-1)+", "+count+"";
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			
				while (rs.next())
				{
					CauHoiPhanNghe ex = new CauHoiPhanNghe();
					
					int so_thu_tu = rs.getInt("so_thu_tu");
					String hinh_anh  = rs.getString("hinh_anh");
					String file_nghe_mp3  = rs.getString("file_nghe_mp3");
					String file_nghe_ogg  = rs.getString("file_nghe_ogg");
					String cau_hoi = rs.getString("cau_hoi");
					String lua_chon_1 = rs.getString("lua_chon_1");
					String lua_chon_2 = rs.getString("lua_chon_2");
					String lua_chon_3 = rs.getString("lua_chon_3");
					String lua_chon_4 = rs.getString("lua_chon_4");
				
					
					ex.setSo_thu_tu(so_thu_tu);
					ex.setHinh_anh(hinh_anh);
					ex.setFile_nghe_mp3(file_nghe_mp3);
					ex.setFile_nghe_ogg(file_nghe_ogg);
					ex.setCau_hoi(cau_hoi);
					ex.setLua_chon_1(lua_chon_1);
					ex.setLua_chon_2(lua_chon_2);
					ex.setLua_chon_3(lua_chon_3);
					ex.setLua_chon_4(lua_chon_4);
					
					dscauhoiphannghe.add(ex);
				}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
				
		return dscauhoiphannghe;
	}
	
	//dem so hang cua 1 bang
	public static int Demcauhoitheoma(Connection conn,int ma_bai_tap_nghe)
	{
		int dem = 0;
		
		
		String sql = "select count(*) from cau_hoi_phan_nghe where ma_bai_tap_nghe="+ma_bai_tap_nghe;
		
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
	
	public static List<CauHoiPhanNghe> Xuatdapanbtnghe(HttpServletRequest request,Connection conn,int ma_bai_tap_nghe,int so_thu_tu)
	{
		List<CauHoiPhanNghe> dscauhoiphannghe = new ArrayList<CauHoiPhanNghe>();
		
		String sql = "select * from cau_hoi_phan_nghe where ma_bai_tap_nghe= "+ma_bai_tap_nghe+" and so_thu_tu="+so_thu_tu;
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			
				while (rs.next())
				{
					CauHoiPhanNghe cauhoiphannghe = new CauHoiPhanNghe();
					
					int so_thu_tu1 = rs.getInt("so_thu_tu");
					String hinh_anh  = rs.getString("hinh_anh");
					String file_nghe_mp3  = rs.getString("file_nghe_mp3");
					String file_nghe_ogg  = rs.getString("file_nghe_ogg");
					String cau_hoi = rs.getString("cau_hoi");
					String lua_chon_1 = rs.getString("lua_chon_1");
					String lua_chon_2 = rs.getString("lua_chon_2");
					String lua_chon_3 = rs.getString("lua_chon_3");
					String lua_chon_4 = rs.getString("lua_chon_4");
					String dap_an = rs.getString("dap_an");
					
					cauhoiphannghe.setSo_thu_tu(so_thu_tu1);
					cauhoiphannghe.setHinh_anh(hinh_anh);
					cauhoiphannghe.setFile_nghe_mp3(file_nghe_mp3);
					cauhoiphannghe.setFile_nghe_ogg(file_nghe_ogg);
					cauhoiphannghe.setCau_hoi(cau_hoi);
					cauhoiphannghe.setLua_chon_1(lua_chon_1);
					cauhoiphannghe.setLua_chon_2(lua_chon_2);
					cauhoiphannghe.setLua_chon_3(lua_chon_3);
					cauhoiphannghe.setLua_chon_4(lua_chon_4);
					cauhoiphannghe.setDap_an(dap_an);
					
					dscauhoiphannghe.add(cauhoiphannghe);
				}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
				
		return dscauhoiphannghe;
	}
	
}
