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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Quyen
 */
@Entity 
@Table (name = "quyen")
@Data 
public class Quyen {
    @Id 
   @GeneratedValue (strategy = GenerationType.IDENTITY)
   @Column (name = "ma_quyen")
    private int maQuyen;
    @Column (name = "ten_quyen")
    private String tenQuyen;
    //nhiều người dùng nhiều quyền
    @ManyToMany (fetch=FetchType.LAZY,cascade ={//nhiều nhiều
        CascadeType.PERSIST,
        CascadeType.MERGE,CascadeType.DETACH
        ,CascadeType.REFRESH// xóa đi 1 sách ko xóa thể laoij ngược lại tương tự -> ko dùng remove
    })
@JoinTable (name ="nguoidung_quyen",
    joinColumns=@JoinColumn (name="ma_quyen"),
    inverseJoinColumns=@JoinColumn (name="ma_nguoi_dung")
)
    private List<NguoiDung> danhSachNguoiDung;

}
