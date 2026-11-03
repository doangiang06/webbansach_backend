package giang.kma.webbansach_backend.entity;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * TheLoai
 */
@Data 
// @Getter 
// @Setter 
public class TheLoai {
    private int maTheLoai;
    private String tenTheLoai;
    private List<Sach> danhSachQuyenSach;

}
