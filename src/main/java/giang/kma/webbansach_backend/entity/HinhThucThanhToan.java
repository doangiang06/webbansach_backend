package giang.kma.webbansach_backend.entity;

import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * HinhThucGiaoHang
 */
@Entity 
@Data
@Table (name = "hinh_thuc_thanh_toan")
public class HinhThucThanhToan {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "ma_hinh_thuc_thanh_toan")
      private int maHinhThucThanhToan;
      @Column (name = "ten_hinh_thuc_thanh_toan", length = 256)
    private String tenHinhThucThanhToan;
    @Column (name = "mo_ta", length = 512)
    private String moTa;
    @Column (name = "chi_phi_thanh_toan")
    private double chiPhiThanhToan;
        @OneToMany (mappedBy = "hinhThucThanhToan", fetch=FetchType.LAZY,cascade ={
        CascadeType.PERSIST,
        CascadeType.MERGE,CascadeType.DETACH
        ,CascadeType.REFRESH
        
    })
    private List<DonHang> danhSachDonHang;

}
