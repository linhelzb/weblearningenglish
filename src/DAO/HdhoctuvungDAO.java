package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import BEAN.ChiTietTuVung;
import BEAN.HuongDanHocTuVung;

public class HdhoctuvungDAO 
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
						request.setAttribute("tbdschudetuvung","Không có bài hướng dẫn nào");
					}
					
				} 
				catch (SQLException e) 
				{
					request.setAttribute("tbdschudetuvung",e.getMessage());
				}
						
				return dshuongdanhoctuvung;
			}
			
			//dem so hang cua 1 bang
			public static int DemSoHang(Connection conn)
			{
				int count = 0;
				
				
				String sql = "select count(*) from huong_dan_hoc_tu_vung";
				
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
			
			//hien thi noi dung tu vung
			public static List<ChiTietTuVung> Hienthinoidungtuvung(HttpServletRequest request,Connection conn,int ma_loai_tu_vung)
			{
				List<ChiTietTuVung> dschitiettuvung = new ArrayList<ChiTietTuVung>();
				
				String sql = "select * from chi_tiet_tu_vung where ma_loai_tu_vung="+ma_loai_tu_vung;
				try 
				{
					PreparedStatement ptmt = conn.prepareStatement(sql);
					
					ResultSet rs = ptmt.executeQuery();
					
					if (rs.isBeforeFirst())
					{
						while (rs.next())
						{
							ChiTietTuVung chi_tiet_tu_vung = new ChiTietTuVung();
							
							
							String ten_tu_vung = rs.getString("ten_tu_vung");
							String phien_am = rs.getString("phien_am");
							String hinh_anh = rs.getString("hinh_anh");
							String audiomp3 = rs.getString("audiomp3");
							String audiogg = rs.getString("audiogg");
							String nghia = rs.getString("nghia");
							
							chi_tiet_tu_vung.setTen_tu_vung(ten_tu_vung);
							chi_tiet_tu_vung.setPhienam(phien_am);
							chi_tiet_tu_vung.setHinh_anh(hinh_anh);
							chi_tiet_tu_vung.setAudiomp3(audiomp3);
							chi_tiet_tu_vung.setAudiogg(audiogg);
							chi_tiet_tu_vung.setNghia(nghia);
							
							
							dschitiettuvung.add(chi_tiet_tu_vung);
						}
					}
					else 
					{
						request.setAttribute("msgndchudetuvung","Không có nội dung từ vựng nào");
					}
					
				} 
				catch (SQLException e) 
				{
					request.setAttribute("msgndchudetuvung",e.getMessage());
				}
						
				return dschitiettuvung;
			}
}
