package giang.kma.webbansach_backend.entity;

import java.sql.Date;
import java.util.List;

import lombok.Data;

/**
 * DonHang
 */
@Data 
public class DonHang {
     private int maDonHang;
    private Date ngayTao;
    private String diaChiMuaHang;
    private String diaChiNhanHang;
    private double tongTienSanPham;
    private double chiPhiGiaoHang;
    private double chiPhiThanhToan;
    private double tongTien;
   
    private NguoiDung nguoiDung;
    private HinhThucThanhToan hinhThucThanhToan;
    private HinhThucGiaoHang hinhThucGiaoHang;

}
