package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BEAN.HuongDanHocNguPhap;


public class BaihdnguphapDAO {
	public static List<HuongDanHocNguPhap> HienThiBaiHDNguPhap(int start, int count,Connection conn){
		List<HuongDanHocNguPhap> dshuongdanhocnguphap = new ArrayList<>();
		
		String sql = "select * from huong_dan_hoc_ngu_phap limit " + (start-1) + ", " + count +"";
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()) {
				HuongDanHocNguPhap huong_dan_hoc_ngu_phap = new HuongDanHocNguPhap();
				int ma_ngu_phap = rs.getInt("ma_ngu_phap");
				String ten_ngu_phap = rs.getString("ten_ngu_phap");
				String hinh_anh_ngu_phap = rs.getString("hinh_anh_ngu_phap");
				
				huong_dan_hoc_ngu_phap.setMa_ngu_phap(ma_ngu_phap);
				huong_dan_hoc_ngu_phap.setTen_ngu_phap(ten_ngu_phap);
				huong_dan_hoc_ngu_phap.setHinh_anh_ngu_phap(hinh_anh_ngu_phap);
				
				dshuongdanhocnguphap.add(huong_dan_hoc_ngu_phap);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dshuongdanhocnguphap;
	}
	
	public static int DemSoHang(Connection conn) {
		int dem = 0;
		String sql = "select count(*) from huong_dan_hoc_ngu_phap";
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			rs.next();
			dem = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dem;
	}
	
	public static List<HuongDanHocNguPhap> HienThiNDHDNguPhap(Connection conn, int mabaihd){
		List<HuongDanHocNguPhap> dshuongdanhocnguphap = new ArrayList<>();
		
		String sql = "select * from huong_dan_hoc_ngu_phap where ma_ngu_phap=" + mabaihd;
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()) {
				HuongDanHocNguPhap huong_dan_hoc_ngu_phap = new HuongDanHocNguPhap();
				int ma_ngu_phap = rs.getInt("ma_ngu_phap");
				String ten_ngu_phap = rs.getString("ten_ngu_phap");
				String hinh_anh_ngu_phap = rs.getString("hinh_anh_ngu_phap");
				String noi_dung_ngu_phap = rs.getString("noi_dung_ngu_phap");
				huong_dan_hoc_ngu_phap.setMa_ngu_phap(ma_ngu_phap);
				huong_dan_hoc_ngu_phap.setTen_ngu_phap(ten_ngu_phap);
				huong_dan_hoc_ngu_phap.setHinh_anh_ngu_phap(hinh_anh_ngu_phap);
				huong_dan_hoc_ngu_phap.setNoi_dung_ngu_phap(noi_dung_ngu_phap);
				dshuongdanhocnguphap.add(huong_dan_hoc_ngu_phap);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dshuongdanhocnguphap;
	}
	
}
