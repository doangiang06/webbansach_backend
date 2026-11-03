package giang.kma.webbansach_backend.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * HinhAnh
 */
@Data 
@Entity 
@Table (name = "hinh_anh")
public class HinhAnh {
    @Id 
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    @Column (name = "ma_hinh_anh")
    private int maHinhAnh;
    @Column (name = "ten_hinh_anh", length = 256)
    private String tenHinhAnh;
    @Column (name = "la_icon")
    private boolean laIcon;
    @Column (name = "duong_dan")
    private String duongDan;
    @Column (name = "du_lieu_anh")
    @Lob //lưu trữ dữ liệu lớn
    private String duLieuAnh;
    @ManyToOne(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE,CascadeType.DETACH
        ,CascadeType.REFRESH})//xóa hình ánh sách vẫn còn nhưng neus ko có sách thì hình ảnh ko tồn tại
    @JoinColumn (name = "ma_sach",nullable = false)//bắt buộc xác định sách mà hình ảnh thuộc về
    private Sach sach;
    

}
