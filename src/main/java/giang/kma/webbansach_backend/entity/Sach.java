package giang.kma.webbansach_backend.entity;

import java.util.List;

import lombok.Data;
@Data    
public class Sach {
    private int maSach;
    private String tenSach;
    private String tenTacGia;
    private String ISBN;
    private String moTa;
    private double giaNiemYet;
    private double giaBan;
    private int soLuong;
    private double trungBinhXepHang;
    List<TheLoai> danhSachTheLoai;
    List<HinhAnh> danhSachHinhAnh;
    List<SuDanhGia> danhSachDanhGia;
    List<ChiTietDonHang> danhSachChiTietDonHang;//đã đặt
   //trong quá trình dặt  có thể gộp hai thằng làm 1
    List<SachYeuThich> danhSachSachYeuThich;
    
}
