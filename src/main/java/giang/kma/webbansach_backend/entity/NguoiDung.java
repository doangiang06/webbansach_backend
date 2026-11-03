package giang.kma.webbansach_backend.entity;

import java.util.List;

import lombok.Data;

/**
 * NguoiDung
 */
@Data 
public class NguoiDung {
private int maNguoiDung;
private String hoDem;
private String ten;
private String tenDangNhap;
private String matKhau;
private char gioiTinh;
private String email;
private String soDienThoai;
private String diaChiMuaHang;
private String diaChiGiaoHang;
private List<SachYeuThich> danhSachSachYeuThich;
private List<SuDanhGia> danhSachSuDanhGia;
private List<Quyen> danhSachQuyen;

private List<DonHang> danhSachDonHang;
}
