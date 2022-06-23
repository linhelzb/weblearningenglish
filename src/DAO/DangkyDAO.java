package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import BEAN.ThanhVien;



public class DangkyDAO {
	public static boolean ThemTaiKhoan(HttpServletRequest request,Connection conn, ThanhVien thanh_vien) {
		PreparedStatement ptmt = null;
		String sql = "insert into thanh_vien(ten_day_du,ten_thanh_vien, mat_khau,ma_loai_thanh_vien) value (?,?,?,?)";
		
		try {
			ptmt = conn.prepareStatement(sql);
			
			String ten_day_du = thanh_vien.getTen_day_du();
			String ten_thanh_vien = thanh_vien.getTen_thanh_vien();
			String mat_khau = thanh_vien.getMat_khau();
			int ma_loai_thanh_vien = 1;
			
			ptmt.setString(1, ten_day_du);
			ptmt.setString(2, ten_thanh_vien);
			ptmt.setString(3, mat_khau);
			ptmt.setInt(4, ma_loai_thanh_vien);
			
			int kt = ptmt.executeUpdate();
			if (kt != 0) {
				return true;
			}
			ptmt.close();
			
		} catch (SQLException e) {
			request.setAttribute("thongbaodangki", e.getMessage());
		}
		return false;
		
	}
	
	public static int KiemTraTaiKhoanDangKi(String ten_day_du, String ten_dang_nhap, String mat_khau) {
		if (ten_day_du == "" || ten_dang_nhap == "" || mat_khau == "") {
			return 0;
		}
		if (mat_khau.length() < 8) {
			return 1;
		}
		return 2;
	}
}
