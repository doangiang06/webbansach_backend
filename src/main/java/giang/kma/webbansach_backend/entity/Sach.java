package giang.kma.webbansach_backend.entity;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
@Data    
@Entity 
@Table (name = "sach")
public class Sach {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "ma_sach")
    private int maSach;
    @Column (name = "ten_sach",length=256)
    private String tenSach;
    @Column (name = "ten_tac_gia",length=512)
    private String tenTacGia;
    @Column (name = "isbn",length=256)
    private String isbn;
    @Column (name = "mo_ta", columnDefinition = "TEXT")//định nghĩa kiểu dữ liệu là TEXT trong cơ sở dữ liệu
    private String moTa;
    @Column (name = "gia_niem_yet")
    private double giaNiemYet;
    @Column (name = "gia_ban")
    private double giaBan;
    @Column (name = "so_luong")
    private int soLuong;
    @Column (name = "trung_binh_xep_hang")
    private double trungBinhXepHang;
    @ManyToMany (fetch=FetchType.LAZY,cascade ={//nhiều nhiều
        CascadeType.PERSIST,
        CascadeType.MERGE,CascadeType.DETACH
        ,CascadeType.REFRESH// xóa đi 1 sách ko xóa thể laoij ngược lại tương tự -> ko dùng remove
    })
@JoinTable (name ="sach_theloai",
    joinColumns=@JoinColumn (name="ma_sach"),
    inverseJoinColumns=@JoinColumn (name="ma_the_loai")// cấu hình bảng trung gian để liên kết giữa hai thực thể Sach và TheLoai, với các cột ma_sach và ma_the_loai
)
    List<TheLoai> danhSachTheLoai;
    //một quyển sách có nhiều cái hình
    @OneToMany (mappedBy = "sach", fetch=FetchType.LAZY,cascade ={//nhiều nhiều
        CascadeType.PERSIST,
        CascadeType.MERGE,CascadeType.DETACH
        ,CascadeType.REFRESH,
        CascadeType.REMOVE// xóa đi 1 sách xóa luôn hình ảnh, ngược lại xóa hình ảnh ko xóa sách
    })
    List<HinhAnh> danhSachHinhAnh;
    // một quyển sách có nhiều đánh giá
    @OneToMany (mappedBy = "sach", fetch=FetchType.LAZY,cascade ={//nhiều nhiều
        CascadeType.PERSIST,
        CascadeType.MERGE,CascadeType.DETACH
        ,CascadeType.REFRESH,
        CascadeType.REMOVE// xóa đi 1 sách xóa luôn đánh giá, ngược lại xóa đánh giá ko xóa sách
    })
    List<SuDanhGia> danhSachDanhGia;
    //một quyển sách có nhiều chi tiết đơn hàng
     @OneToMany (mappedBy = "sach", fetch=FetchType.LAZY,cascade ={//nhiều nhiều
        CascadeType.PERSIST,
        CascadeType.MERGE,CascadeType.DETACH
        ,CascadeType.REFRESH//ko nên xóa vì ảnh hưởng đền việc báo cáo

    })
    List<ChiTietDonHang> danhSachChiTietDonHang;//đã đặt
   //trong quá trình dặt  có thể gộp hai thằng làm 1
    @OneToMany (mappedBy = "sach", fetch=FetchType.LAZY,cascade ={//nhiều nhiều
        CascadeType.PERSIST,
        CascadeType.MERGE,CascadeType.DETACH
        ,CascadeType.REFRESH,
        CascadeType.REMOVE// xóa đi 1 sách xóa luôn đánh giá, ngược lại xóa đánh giá ko xóa sách
    })
    List<SachYeuThich> danhSachSachYeuThich;
    
}
