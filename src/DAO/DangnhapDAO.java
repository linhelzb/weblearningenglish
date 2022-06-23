package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import BEAN.ThanhVien;



public class DangnhapDAO {
	public static boolean XacThucThanhVien(HttpServletRequest request,Connection conn, ThanhVien thanh_vien) {
		PreparedStatement ptmt = null;
		String sql = "select * from thanh_vien";
		
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			
			while(rs.next()) {
				String ten_thanh_vien = rs.getString("ten_thanh_vien");
				String mat_khau = rs.getString("mat_khau");
			
				if (thanh_vien.getTen_thanh_vien().equals(ten_thanh_vien)&& thanh_vien.getMat_khau().equals(mat_khau)) {
					return true;
				}
			}
			
			ptmt.close();
			rs.close();
			
		} catch (SQLException e) {
			request.setAttribute("thongbaodangnhap", e.getMessage());
		}
		return false;
		
	}
	
	public static int PhanQuyenThanhVien(HttpServletRequest request,Connection conn, ThanhVien thanh_vien) {
		PreparedStatement ptmt = null;
		
		String sql = "select ma_loai_thanh_vien from thanh_vien where ten_thanh_vien='"+thanh_vien.getTen_thanh_vien()+"' AND mat_khau='" + thanh_vien.getMat_khau() + "'";
		int ma_loai_thanh_vien = 0;
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			
			while(rs.next()) {
				ma_loai_thanh_vien = rs.getInt("ma_loai_thanh_vien");
				
			}
			
			ptmt.close();
			rs.close();
			
		} catch (SQLException e) {
			request.setAttribute("thongbaodangnhap", e.getMessage());
		}
		return ma_loai_thanh_vien;
	}
	
	public static String XuatTenThanhVien(HttpServletRequest request,Connection conn, ThanhVien thanh_vien) {
		PreparedStatement ptmt = null;
		
		String sql = "select ten_day_du from thanh_vien where ten_thanh_vien='"+thanh_vien.getTen_thanh_vien()+"' AND mat_khau='" + thanh_vien.getMat_khau() + "'";
		String ten_day_du = "";
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			
			while(rs.next()) {
				ten_day_du = rs.getString("ten_day_du");
			}
			
			ptmt.close();
			rs.close();
			
		} catch (SQLException e) {
			request.setAttribute("thongbaodangnhap", e.getMessage());
		}
		return ten_day_du;
	}
}
