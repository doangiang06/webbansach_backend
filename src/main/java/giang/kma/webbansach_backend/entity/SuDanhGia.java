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
 * SuDanhGia
 */
@Data 
@Entity 
@Table (name = "su_danh_gia")
public class SuDanhGia {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "ma_danh_gia")
    private long maDanhGia;
    @Column (name = "diem_xep_hang")
    private float diemXepHang;
    @Column (name = "nhan_xet", columnDefinition = "TEXT")
    private String nhanXet;
    @ManyToOne(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE,CascadeType.DETACH
        ,CascadeType.REFRESH,
        CascadeType.REMOVE// phàn ngược lại xóa đánh giá ko xóa sách
    })
    @JoinColumn (name = "ma_sach", nullable = false)//bắt buộc xác định sách mà đánh giá thuộc về
    private Sach sach;
    // 1 người dùng nhiều sự đánh giá
        @ManyToOne(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE,CascadeType.DETACH
        ,CascadeType.REFRESH,
        CascadeType.REMOVE// phàn ngược lại xóa đánh giá ko xóa sách
    })
    @JoinColumn (name = "ma_nguoi_dung", nullable = false)//bắt buộc xác định người dùng mà đánh giá thuộc về
    private NguoiDung nguoiDung;

}
