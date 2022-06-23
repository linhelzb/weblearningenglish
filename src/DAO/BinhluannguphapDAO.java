package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import BEAN.Binhluannguphap;




public class BinhluannguphapDAO {
	public static void ThemBinhLuanNguPhap(HttpServletRequest request, Connection conn, Binhluannguphap binh_luan_ngu_phap)
	{
		PreparedStatement ptmt = null;
		
		String sql = "insert into binh_luan_ngu_phap(noi_dung_binh_luan,ma_thanh_vien,ma_ngu_phap) values (?,?,?)";
		
		try 
		{
			ptmt = conn.prepareStatement(sql);
			
			String noi_dung_binh_luan = binh_luan_ngu_phap.getNoi_dung_binh_luan();
			int ma_thanh_vien = binh_luan_ngu_phap.getMa_thanh_vien();
			int ma_ngu_phap = binh_luan_ngu_phap.getMa_ngu_phap();
			
			
			ptmt.setString(1,noi_dung_binh_luan);
			ptmt.setInt(2,ma_thanh_vien);
			ptmt.setInt(3,ma_ngu_phap);
			
			ptmt.executeUpdate();
			
			ptmt.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public static List<Binhluannguphap> HienThiBinhLuanNguPhap (Connection conn, int ma_ngu_phap)
	{
		List<Binhluannguphap> dsbinhluannguphap = new ArrayList<Binhluannguphap>();
		
		String sql = "select * from binh_luan_ngu_phap where ma_ngu_phap="+ma_ngu_phap;
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			
			while (rs.next())
			{
				Binhluannguphap cmt = new Binhluannguphap();
				int ma_thanh_vien = rs.getInt("ma_thanh_vien");
				String noi_dung_binh_luan = rs.getString("noi_dung_binh_luan");
				
				String ten_day_du = BinhluannguphapDAO.getTenThanhVien(conn,ma_thanh_vien);
				
				cmt.setNoi_dung_binh_luan(noi_dung_binh_luan);
				cmt.setTen_day_du(ten_day_du);
				
				dsbinhluannguphap.add(cmt);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return dsbinhluannguphap;
	}
	
	//xuat ten cua thanh vien theo ma
	public static String getTenThanhVien(Connection conn,int ma_thanh_vien)
	{
		String ten_day_du = "";
		
		
		String sql = "select ten_day_du from thanh_vien where ma_thanh_vien="+ma_thanh_vien;
		
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			rs.next();
			
			ten_day_du = rs.getString("ten_day_du");
			
				
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		
		return ten_day_du;
	}
	
	public static int DemSoHang(Connection conn,int ma_ngu_phap)
	{
		int count = 0;
		
		
		String sql = "select count(*) from binh_luan_ngu_phap where ma_ngu_phap="+ma_ngu_phap;
		
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			rs.next();
			
			count = rs.getInt(1);
			
				
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		
		return count;
	}
}
