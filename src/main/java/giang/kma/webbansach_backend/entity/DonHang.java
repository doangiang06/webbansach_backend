package giang.kma.webbansach_backend.entity;

import java.sql.Date;
import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * DonHang
 */
@Data 
@Entity 
@Table (name = "don_hang")
public class DonHang {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "ma_don_hang")
     private int maDonHang;
     @Column (name = "ngay_tao")
    private Date ngayTao;
    @Column (name="dia_chi_mua_hang",length=512)
    private String diaChiMuaHang;
    @Column (name="dia_chi_nhan_hang",length=512)
    private String diaChiNhanHang;
    @Column (name="tong_tien_san_pham")
    private double tongTienSanPham;
    @Column (name="chi_phi_giao_hang")
    private double chiPhiGiaoHang;
    @Column (name="chi_phi_thanh_toan")
    private double chiPhiThanhToan;
    @Column (name="tong_tien")
    private double tongTien;
        @OneToMany (mappedBy = "donHang", fetch=FetchType.LAZY,cascade ={
        CascadeType.PERSIST,
        CascadeType.MERGE,CascadeType.DETACH
        ,CascadeType.REFRESH ,
        CascadeType.REMOVE// xóa đi 1 đơn hàng xóa luôn chi tiết đơn hàng, ngược lại xóa chi tiết đơn hàng ko xóa đơn hàng

    })
    private List<ChiTietDonHang> danhSachChiTietDonHang;
        @ManyToOne (cascade ={
        CascadeType.PERSIST,
        CascadeType.MERGE,
        CascadeType.DETACH,
        CascadeType.REFRESH
     })
     @JoinColumn (name = "ma_nguoi_dung",nullable = false)
    private NguoiDung nguoiDung;
         @ManyToOne (cascade ={
        CascadeType.PERSIST,
        CascadeType.MERGE,
        CascadeType.DETACH,
        CascadeType.REFRESH
     })
     @JoinColumn (name = "ma_hinh_thuc_thanh_toan")// nhiều đơn hàng có thể có chung 1 hình thức thnh toán
    private HinhThucThanhToan hinhThucThanhToan; // 1 đon hàng chỉ có 1 hình thức thanh toán 
          @ManyToOne (cascade ={
        CascadeType.PERSIST,
        CascadeType.MERGE,
        CascadeType.DETACH,
        CascadeType.REFRESH
     })
     @JoinColumn (name = "ma_hinh_thuc_giao_hang")
    private HinhThucGiaoHang hinhThucGiaoHang;

}
