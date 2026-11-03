package giang.kma.webbansach_backend.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * ChiTietDonHang
 */
@Entity 
@Table (name = "chi_tiet_don_hang")
@Data 
public class ChiTietDonHang {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "ma_chi_tiet_don_hang")
    private long chiTietDonHang;
    @Column (name = "so_luong")
    private int soLuong;
    @Column (name = "gia_ban")
    private double giaBan;
    @ManyToOne(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE,CascadeType.DETACH
        ,CascadeType.REFRESH})
    @JoinColumn (name = "ma_sach",nullable = false)//bắt buộc xác định sách mà chi tiết đơn hàng thuộc về
    private Sach sach;
        @ManyToOne(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE,CascadeType.DETACH
        ,CascadeType.REFRESH})
    @JoinColumn (name = "ma_don_hang",nullable = false)
    private DonHang donHang;

}
