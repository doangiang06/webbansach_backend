package giang.kma.webbansach_backend.entity;

import java.util.List;

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
import lombok.Getter;
import lombok.Setter;

/**
 * TheLoai
 */
@Data 
@Table (name = "the_loai")
@Entity 

// @Getter 
// @Setter 
public class TheLoai {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "ma_the_loai")
    private int maTheLoai;
    @Column (name = "ten_the_loai", length = 256)
    private String tenTheLoai;
        @ManyToMany (fetch=FetchType.LAZY,cascade ={//nhiều nhiều
        CascadeType.PERSIST,
        CascadeType.MERGE,CascadeType.DETACH
        ,CascadeType.REFRESH
    })
@JoinTable (name ="sach_theloai",
    joinColumns=@JoinColumn (name="ma_the_loai"),
    inverseJoinColumns=@JoinColumn (name="ma_sach")//cấu hình quan hệ tương tự bên sách chỉ ngược lại, với các cột ma_the_loai và ma_sach
// 
)
    private List<Sach> danhSachQuyenSach;

}
