package giang.kma.webbansach_backend.entity;

import jakarta.annotation.Generated;
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
 * SachYeuThich
 */
@Data 
@Entity 
@Table (name = "sach_yeu_thich")
public class SachYeuThich {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "ma_sach_yeu_thich")
    private int maSachYeuThich;
         @ManyToOne (cascade ={
        CascadeType.PERSIST,
        CascadeType.MERGE,
        CascadeType.DETACH,
        CascadeType.REFRESH
     })
     @JoinColumn (name = "ma_nguoi_dung",nullable = false)
     private NguoiDung nguoiDung;
     // một sách nhieeuf người yêu thích
     @ManyToOne (cascade ={
        CascadeType.PERSIST,
        CascadeType.MERGE,
        CascadeType.DETACH,
        CascadeType.REFRESH
     })
     @JoinColumn (name = "ma_sach",nullable = false)//bắt buộc xác định sách mà yêu thích thuộc về
    private Sach sach;
   

}
